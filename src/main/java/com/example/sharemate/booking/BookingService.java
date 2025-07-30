package com.example.sharemate.booking;

import com.example.sharemate.exceptions.BadRequestException;
import com.example.sharemate.exceptions.ForbiddenException;
import com.example.sharemate.exceptions.NotFoundException;
import com.example.sharemate.item.Item;
import com.example.sharemate.item.ItemRepository;
import com.example.sharemate.user.User;
import com.example.sharemate.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    public BookingResponseDto create(int bookerId, BookingRequestDto bookingRequest){
        User booker = userRepository.findById(bookerId).orElseThrow(()-> new NotFoundException("Пользователь не найден"));
        Item item = itemRepository.findById(bookingRequest.getItemId()).orElseThrow(()-> new NotFoundException("Предмет не найден"));

        if(!item.getAvailable()){
            throw new BadRequestException("Вещь недоступна для брони");
        }

        if(item.getOwner().getId().equals(bookerId)){
            throw new BadRequestException("Владелец не может забронировать свою же вещь");

        }

        Booking booking = new Booking();
        booking.setBooker(booker);
        booking.setItem(item);
        booking.setStart(bookingRequest.getStart());
        booking.setEnd(bookingRequest.getEnd());
        booking.setStatus(BookingStatus.WAITING);
        bookingRepository.save(booking);
        return bookingMapper.toDto(booking);

    }

    public BookingResponseDto updateStatus(int bookingId, int ownerId, boolean approved){
        User owner = userRepository.findById(ownerId).orElseThrow(()-> new NotFoundException("Пользователь не найден"));
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new NotFoundException("Бронь не найдена"));

        Item item = booking.getItem();

        if(item.getOwner().getId()!=ownerId){
            throw new ForbiddenException("Только владелец может изменить статус вещи");

        }

        if(booking.getStatus()!=BookingStatus.WAITING){
            throw new BadRequestException("Статус уэе либо подтвержден либо отклонен");

        }

        booking.setStatus(approved? BookingStatus.APPROVED : BookingStatus.REJECTED);
        Booking updated = bookingRepository.save(booking);
        return bookingMapper.toDto(updated);



    }
}
