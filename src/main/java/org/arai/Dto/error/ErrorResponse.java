package org.arai.Dto.error;

import java.time.LocalDateTime;

public record ErrorResponse(String mensaje, Integer status, LocalDateTime timestamp) {

    public ErrorResponse(String mensaje, Integer status){
        this( mensaje, status, LocalDateTime.now());
    }
}
