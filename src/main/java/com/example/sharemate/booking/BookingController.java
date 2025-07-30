package com.example.sharemate.booking;

import com.example.sharemate.item.Item;
import com.example.sharemate.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponseDto create(@RequestBody BookingRequestDto bookingRequest, @RequestHeader("X-Sharer_User_id") Integer bookerId){
        return bookingService.create(bookerId,bookingRequest);

    }

    @PatchMapping("{bookingId}")
    public BookingResponseDto updateStatus(@PathVariable Integer bookingId,  @RequestHeader("X-Sharer-User-Id") Integer owner, @RequestParam boolean approved){
        return bookingService.updateStatus(bookingId,owner,approved);
    }

}
