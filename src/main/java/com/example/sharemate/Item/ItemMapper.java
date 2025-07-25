package com.example.sharemate.Item;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemMapper {

    public ItemResponseDto toDto(Item item){
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        itemResponseDto.setId(item.getId());
        itemResponseDto.setName(item.getName());
        itemResponseDto.setDescription(item.getDescription());
        itemResponseDto.setAvailable(item.getAvailable());
        return itemResponseDto;
    }

    public List<ItemResponseDto> toListDto(List<Item> items){
        List<ItemResponseDto> itemDto = new ArrayList<>();
        for (Item item:items){
            ItemResponseDto itemResponseDto = toDto(item);
            itemDto.add(itemResponseDto);
        }
        return itemDto;

    }

}
