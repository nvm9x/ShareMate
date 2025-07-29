package com.example.sharemate.user;

import com.example.sharemate.exceptions.ConflictException;
import com.example.sharemate.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public User create(User user){
        Optional<User> optional = userRepository.findByEmail(user.getEmail());
        if(optional.isPresent()){
             throw new ConflictException("Почта уже занята");

        } userRepository.save(user);
        return user;

    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User update(int id, User user){
        User user1 = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Пользователь не найден"));

            if(user1.getEmail().equals(user.getEmail()) && user1.getId()!=id){
                throw new ConflictException("Почта уже занята");
            }

            if(user.getName()!=null){
            user1.setName(user.getName());
        }
        if(user.getEmail()!=null){
            user1.setEmail(user.getEmail());
        }
        userRepository.save(user1);

        return user1;
    }


    public User findById(int id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Пользователь не найден"));
        return user;

    }

    public void delete(int id) {
        userRepository.delete(findById(id));

    }
}

//
//    //    private final List<User> users = new ArrayList<>();
////    private long id;
//    private final UserRepository userRepository;
//    public User create(User user){
//        Optional<User> optional = userRepository.findByEmail(user.getEmail());
//        if(optional.isPresent()){
//            throw new ConflictException("Почта уже занята");
//
//        } userRepository.save(user);
//        return user;
////        for (User u:users){
////            if(u.getEmail().equals(user.getEmail())){
////                throw new ConflictException("Почта уже занята");
////            }
////        }
////        user.setId(++id);
////        users.add(user);
//
//    }
//
//    public List<User> getAll(){
//        return userRepository.findAll();
//    }
//
//    public User update(int id, User user){
//        User user1 = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Пользователь не найден"));;
////        User user1 = findById(id);
//        // for(User u:users)
//        if(user1.getEmail().equals(user.getEmail()) && user1.getId()!=id){
//            throw new ConflictException("Почта уже занята");
//        }
//
//        if(user.getName()!=null){
//            user1.setName(user.getName());
//        }
//        if(user.getEmail()!=null){
//            user1.setEmail(user.getEmail());
//        }
//        userRepository.save(user1);
//
//        return user1;
//    }
//
//
//    public User findById(int id) {
//        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Пользователь не найден"));
//        return user;
////      for (User user:users){
////          if(user.getId()==id){
////              return user;
////          }
////      } throw new NotFoundException("user с таким id не существует");
//    }
//
//    public void delete(int id) {
//        userRepository.delete(findById(id));
////        users.remove(findById(id));
//    }
//}
