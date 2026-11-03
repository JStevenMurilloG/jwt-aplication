package com.thebestadso.innovation.service;

import org.springframework.stereotype.Service;

import com.thebestadso.innovation.dto.HttpGlobalResponseDTO;
import com.thebestadso.innovation.dto.LoginResponseDTO;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {

    private final JwtService jwtService;
    
    public HttpGlobalResponseDTO<LoginResponseDTO> refreshToken(String token) throws Exception {
        HttpGlobalResponseDTO<LoginResponseDTO> response = new HttpGlobalResponseDTO<>();
        LoginResponseDTO refresh = new LoginResponseDTO();

        String jwt = jwtService.refreshToken(token);
        
        refresh.setJwt(jwt);
        response.setMessage("Token refrescado correctamente");
        response.setData(refresh);

        return response;
    }
}
