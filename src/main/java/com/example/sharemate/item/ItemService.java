package com.example.sharemate.item;

import com.example.sharemate.user.User;
import com.example.sharemate.user.UserService;
import com.example.sharemate.exceptions.BadRequestException;
import com.example.sharemate.exceptions.ForbiddenException;
import com.example.sharemate.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;
    private final UserService userService;
    private final ItemRepository itemRepository;



    public ItemResponseDto create (ItemCreateDto itemDto, int sharerId){

        User owner = userService.findById(sharerId);
        if (owner == null) {
            throw new NotFoundException("Пользователь не найден: id=" + sharerId);
        }
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwner(owner);

        itemRepository.save(item);

        return itemMapper.toDto(item);
    }

    private Item findById( int id){
        Item item = itemRepository.findById(id).orElseThrow(()-> new NotFoundException("Вещь не найдена"));
        return item;

    }
    public ItemResponseDto getById(int id){
        Item item = itemRepository.findById(id).orElseThrow(()-> new NotFoundException("Пост не найден"));
        return itemMapper.toDto(item);
    }

    public List<ItemResponseDto> search(String text) {
        if (text == null || text.isBlank()) {
            return Collections.emptyList();
        }
        List<Item> foundItems = itemRepository.searchAvailableItems(text);
        return itemMapper.toListDto(foundItems);
    }




    public List<ItemResponseDto> getAll(int sharerId){
        Item item = itemRepository.findById(sharerId).orElseThrow(()-> new NotFoundException("Owner is not found"));
        List<Item> items = itemRepository.findAll();
        List<Item> userItems = new ArrayList<>();
        for(Item i: items){
            if(i.getOwner().getId()==sharerId){
                userItems.add(i);
            }
        }
        return itemMapper.toListDto(userItems);
    }

    public ItemResponseDto update(int id, Item item,int sharerId) {
        Item item1 = findById(id);
        User owner = userService.findById(sharerId);
        if(owner==null){
            throw new BadRequestException("ID is null");

        }
        if(item1.getOwner()==null || item1.getOwner().getId()!=sharerId){
            throw new ForbiddenException("Продукт обновлять может только владелец");
        }
        if (item.getName()!=null){
            item1.setName(item.getName());
        }

        if (item.getDescription() != null) {

            item1.setDescription(item.getDescription());

        }
        if (item.getAvailable()!=null) {
            item1.setAvailable(item.getAvailable());

        }

        return itemMapper.toDto(item1);
    }

    public void delete(int id){
        Item item=findById(id);
        itemRepository.delete(item);
    }



}
//
//    //    public List<Item> items = new ArrayList<>();
//    private final ItemMapper itemMapper;
//    private final UserService userService;
//
////    private long id;
//
//    public ItemResponseDto create (ItemCreateDto itemDto, int sharerId){
//
//        User owner = userService.findById(sharerId);
//        if (owner == null) {
//            throw new NotFoundException("Пользователь не найден: id=" + sharerId);
//        }
//        Item item = new Item();
//        item.setId(++id);
//        item.setName(itemDto.getName());
//        item.setDescription(itemDto.getDescription());
//        item.setAvailable(itemDto.getAvailable());
//        item.setOwner(owner);
//        items.add(item);
//
//
//        return itemMapper.toDto(item);
//    }
//
//    private Item findById( int id){
//        for(Item item:items) {
//            if(item.getId()==id){
//                return item;
//            }
//
//        }   throw new NotFoundException("Пользователя с таким id не существует");
//
//    }
//    public ItemResponseDto getById(int id){
//        Item item = findById(id);
//        return itemMapper.toDto(item);
//    }
//
//    public List<ItemResponseDto> search(String text){
//        if (text == null || text.isBlank()) {
//            return Collections.emptyList();
//        }
//
//        List<Item> itemList = new ArrayList<>();
//        text=text.toLowerCase();
//        for (Item i: items){
//            if (i.getAvailable() && (i.getName().toLowerCase().contains(text)|| i.getDescription().toLowerCase().contains(text))
//            ){
//                itemList.add(i);
//            }
//        } return itemMapper.toListDto(itemList);
//    }
//
//
//
//    public List<ItemResponseDto> getAll(int sharerId){
//        List<Item> userItems = new ArrayList<>();
//        for(Item i: items){
//            if(i.getOwner().getId()==sharerId){
//                userItems.add(i);
//            }
//        }
//        return itemMapper.toListDto(userItems);
//    }
//
//    public ItemResponseDto update(int id, Item item,int sharerId) {
//        Item item1 = findById(id);
//        User owner = userService.findById(sharerId);
//        if(owner==null){
//            throw new BadRequestException("ID is null");
//
//        }
//        if(item1.getOwner()==null || item1.getOwner().getId()!=sharerId){
//            throw new ForbiddenException("Продукт обновлять может только владелец");
//        }
//        if (item.getName()!=null){
//            item1.setName(item.getName());
//        }
//
//        if (item.getDescription() != null) {
//
//            item1.setDescription(item.getDescription());
//
//        }
//        if (item.getAvailable()!=null) {
//            item1.setAvailable(item.getAvailable());
//
//        }
//
//        return itemMapper.toDto(item1);
//    }
//
//    public void delete(int id){
//        Item item=findById(id);
//        items.remove(item);
//    }

