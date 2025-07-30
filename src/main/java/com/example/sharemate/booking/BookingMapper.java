package com.example.sharemate.booking;

import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponseDto toDto(Booking booking){
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(booking.getId());
        bookingResponseDto.setItem(booking.getItem());
        bookingResponseDto.setBooker(booking.getBooker());
        bookingResponseDto.setStatus(booking.getStatus());
        bookingResponseDto.setStartDate(booking.getStart());
        bookingResponseDto.setEndDate(booking.getEnd());
        return bookingResponseDto;
    }
}
