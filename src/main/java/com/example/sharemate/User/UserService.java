package com.example.sharemate.User;

import com.example.sharemate.exceptions.ConflictException;
import com.example.sharemate.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final List<User> users = new ArrayList<>();
    private long id;
    public User create(User user){
        for (User u:users){
            if(u.getEmail().equals(user.getEmail())){
                throw new ConflictException("Почта уже занята");
            }
        }
        user.setId(++id);
        users.add(user);
        return user;
    }

    public List<User> getAll(){
        return users;
    }

    public User update(int id, User user){
        User user1 = findById(id);
        for (User u:users){
            if(u.getEmail().equals(user.getEmail()) && u.getId()!=id){
                throw new ConflictException("Почта уже занята");
            }
        }


        if(user.getName()!=null){
            user1.setName(user.getName());
        }
        if(user.getEmail()!=null){
            user1.setEmail(user.getEmail());
        }

        return user1;
    }


    public User findById(int id) {
      for (User user:users){
          if(user.getId()==id){
              return user;
          }
      } throw new NotFoundException("user с таким id не существует");
    }

    public void delete(int id) {
        users.remove(findById(id));
    }
}
