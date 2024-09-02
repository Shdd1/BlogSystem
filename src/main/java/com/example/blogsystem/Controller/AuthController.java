package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUser());
    }
    @PostMapping("/register")
    public ResponseEntity register(@Valid@RequestBody User user){
    authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("User register"));
    }
    @PutMapping("/update/{userid}")
    public ResponseEntity updateUser(@PathVariable Integer userid, @Valid@RequestBody User user){
        authService.updateUser(userid,user);
        return ResponseEntity.status(200).body(new ApiResponse("is updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        authService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("is deleted"));

    }
    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body(new ApiResponse("Successfully logout"));
    }
}
