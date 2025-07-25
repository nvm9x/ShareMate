package com.example.sharemate.Item;

import com.example.sharemate.User.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long id;

    private String name;

    private String description;
    private Boolean available;

    private User owner;

}
