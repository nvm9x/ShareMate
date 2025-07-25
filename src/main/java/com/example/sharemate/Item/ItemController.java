package com.example.sharemate.Item;

import com.example.sharemate.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {


    private final ItemService itemService;


    @PostMapping
    public ItemResponseDto create(@Valid @RequestBody ItemCreateDto item, @RequestHeader("X-Sharer-User-Id") int sharerId) {
        return itemService.create(item,sharerId);
    }

    @GetMapping("/{id}")
    public ItemResponseDto findById(@PathVariable Integer id) {
        return itemService.getById(id);
    }

    @GetMapping
    public List<ItemResponseDto> getAll(@RequestHeader("X-Sharer-User-Id") Integer sharerId) {
        return itemService.getAll(sharerId);
    }

    @GetMapping("/search")
    public List<ItemResponseDto> search(@RequestParam String text){
        return itemService.search(text);
    }

    @PatchMapping("/{id}")
    public ItemResponseDto update(@PathVariable Integer id, @RequestBody Item item, @RequestHeader ("X-Sharer-User-Id") Integer sharerId) {

        return itemService.update(id, item,sharerId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        itemService.delete(id);
    }
}



