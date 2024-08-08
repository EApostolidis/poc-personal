package com.example.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommonResponseDto <T>{

    private T data;
    private String correlationId;
    private ErrorDetails errorDetails;
    private String message;
}
