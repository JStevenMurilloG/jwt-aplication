package com.thebestadso.innovation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thebestadso.innovation.dto.HttpGlobalResponseDTO;
import com.thebestadso.innovation.dto.LoginRequestDTO;
import com.thebestadso.innovation.dto.LoginResponseDTO;
import com.thebestadso.innovation.dto.RegisterRequestDTO;
import com.thebestadso.innovation.dto.UserDTO;
import com.thebestadso.innovation.service.AuthService;
import com.thebestadso.innovation.exception.InvalidCredentialsException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class AuthController {

    private final AuthService authService;

    /**
     * Recibe las credenciales en POST /api/v1/auth/login y devuelve el JWT.
     * La anotación Valid comprueba los campos antes de llamar al servicio.
     */
    @PostMapping("login")
    public ResponseEntity<HttpGlobalResponseDTO<LoginResponseDTO>> login(
            @Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Responde con 401 cuando las credenciales son incorrectas,
     * conservando el formato de respuesta usado en la aplicación.
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<HttpGlobalResponseDTO<Void>> handleInvalidCredentials(InvalidCredentialsException exception) {
        HttpGlobalResponseDTO<Void> response = new HttpGlobalResponseDTO<>();
        response.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    /**
     * Responde con 400 cuando falta el usuario o la contraseña,
     * o alguno de los campos está vacío o contiene solo espacios.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpGlobalResponseDTO<Void>> handleInvalidLoginRequest() {
        HttpGlobalResponseDTO<Void> response = new HttpGlobalResponseDTO<>();
        response.setMessage("El usuario y la contraseña son obligatorios");
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("refresh")
    public ResponseEntity<HttpGlobalResponseDTO<LoginResponseDTO>> refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        HttpGlobalResponseDTO<LoginResponseDTO> response = new HttpGlobalResponseDTO<>();

        // Un token legal debe existir y empezar con Bearer (portador)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setMessage("Token is invalid");
            response.setData(null);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = authHeader.replace("Bearer ", "");

        try {
            response = authService.refreshToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Token is invalid or expired");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }

    @PostMapping("register")
    public ResponseEntity<HttpGlobalResponseDTO<UserDTO>> register(@RequestBody RegisterRequestDTO request) {
        try {
            HttpGlobalResponseDTO<UserDTO> response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
