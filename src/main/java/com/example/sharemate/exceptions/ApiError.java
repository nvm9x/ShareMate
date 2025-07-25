package com.example.sharemate.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private final String error;
    private final LocalDateTime timestamp;
}
