package com.example.sharemate.user;

import com.example.sharemate.booking.Booking;
import com.example.sharemate.item.Item;
import com.example.sharemate.request.Request;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    @NotBlank
    @Email
    private String email;

    @OneToMany(mappedBy = "owner")
    List<Item> itemList = new ArrayList<>();

    @OneToMany(mappedBy = "booker")
    List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "requestor")
    List<Request> requests = new ArrayList<>();



}
