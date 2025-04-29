package org.arai.Model.ErrorResponse;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String mensaje, Integer status, LocalDateTime timestamp) {

    public ErrorResponseDTO(String mensaje, Integer status){
        this( mensaje, status, LocalDateTime.now());
    }
}
