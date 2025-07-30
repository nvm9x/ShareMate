package com.example.sharemate.booking;

import com.example.sharemate.item.Item;
import com.example.sharemate.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BookingStatus status;
    @Column(name = "start_date")
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name="item_id")
     private Item item;
     @ManyToOne
     @JoinColumn(name = "owner_id")
     private User booker;


}
