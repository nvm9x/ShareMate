package com.example.sharemate.booking;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookingRequestDto {
    private Integer itemId;
    private LocalDateTime start;
    private LocalDateTime end;
}
