package com.thebestadso.innovation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thebestadso.innovation.dto.HttpGlobalResponseDTO;
import com.thebestadso.innovation.dto.LoginResponseDTO;
import com.thebestadso.innovation.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor 
@RequestMapping("/auth/")
public class AuthController {

    private final AuthService authService;

    @PostMapping
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
}
