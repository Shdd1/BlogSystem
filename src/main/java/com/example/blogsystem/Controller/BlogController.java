package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    @GetMapping("/get-my")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));
    }
    @GetMapping("/get-blogs")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }
    //add
    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid  Blog blog){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog Added ,"+user.getUsername()));
    }
    //update
    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id , @RequestBody @Valid Blog blog){
        blogService.updateBlog(user.getId(),blog_id,blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated ,"+user.getUsername()));
    }
    //delete
    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user,@PathVariable Integer blog_id){
        blogService.deleteBlog(user.getId(),blog_id);
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted ,"+user.getUsername()));
    }
    //
    @GetMapping("/get-by-id/{blog_id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user,@PathVariable Integer blog_id){
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),blog_id));
    }
    //
    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }



}
