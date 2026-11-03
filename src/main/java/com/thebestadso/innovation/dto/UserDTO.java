package com.thebestadso.innovation.dto;

import lombok.Data;

@Data
public class UserDTO {
    /**
     * Id del usuario
     */
    private Long id;

    /**
     * Nombre del usuario
     */
    private String username;

    /**
     * Email del usuario
     */
    private String email;

    /**
     * Rol del usuario
     */
    private Long rolId;
}
