package com.example.sharemate.booking;

import com.example.sharemate.item.Item;
import com.example.sharemate.item.ItemResponseDto;
import com.example.sharemate.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDto {
    private Integer id;
    private BookingStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Item item;
    private User booker;
}
