package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
   public List<User> getAllUser(){
       return authRepository.findAll();
   }
   public void register(User user){
       user.setRole("USER");
       String hash= new BCryptPasswordEncoder().encode(user.getPassword());
       user.setPassword(hash);
       authRepository.save(user);
   }
   public void updateUser(Integer id,User user){
       User user1=authRepository.findUsersById(id);
       if(user1==null){
           throw new ApiException("user not found");
       }
       String hash=new BCryptPasswordEncoder().encode(user.getPassword());
       user.setPassword(hash);
       user1.setPassword(user.getPassword());
       user1.setPassword(hash);
       user1.setUsername(user.getUsername());
       authRepository.save(user1);
   }
   public void deleteUser(Integer id){
       User user1=authRepository.findUsersById(id);
       if(user1==null){
           throw new ApiException("user not found");
       }
       authRepository.delete(user1);
   }

}
