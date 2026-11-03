package com.thebestadso.innovation.dto;

import lombok.Data;

@Data 
public class HttpGlobalResponseDTO<T> {
    /**
     * Mensaje de respuesta
     */
    private String message;

    /**
     * Objeto de datos a responder
     */
    private T data;
}