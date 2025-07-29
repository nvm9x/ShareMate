package com.example.sharemate.item;

import com.example.sharemate.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    private Boolean available;


    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

}
