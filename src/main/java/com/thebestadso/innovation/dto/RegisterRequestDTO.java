package com.thebestadso.innovation.dto;

import lombok.Data;

@Data 
public class RegisterRequestDTO {
    /**
     * Nombre del usuario
     */
    private String username;

    /**
     * Clave del usuario
     */
    private String password;

    /**
     * Email del usuario
     */
    private String email;

    /**
     * Rol del usuario
     */
    private Long rolId;
}
