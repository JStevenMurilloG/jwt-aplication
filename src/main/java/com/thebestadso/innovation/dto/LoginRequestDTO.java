package com.thebestadso.innovation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

/**
 * Datos necesarios para iniciar sesión. NotBlank rechaza valores nulos,
 * vacíos o compuestos únicamente por espacios.
 */
@Data
public class LoginRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    // Evita incluir la contraseña en el texto generado por toString de Lombok.
    @ToString.Exclude
    private String password;
}
