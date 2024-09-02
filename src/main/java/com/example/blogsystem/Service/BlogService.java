package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    //user can get list of bloge he have it
    public List<Blog> getMyBlogs(Integer id){
        User user=authRepository.findUsersById(id);
        return blogRepository.findAllByUser(user);

    }
    //Admin
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }
    //assign user in the blog
    //user
    public void addBlog(Integer auth_id, Blog blog){
        User user=authRepository.findUsersById(auth_id);
        if(user==null){
            throw  new ApiException("user not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }
    //user
    public void updateBlog(Integer auth_id,Integer blog_id,Blog blog){
        User user=authRepository.findUsersById(auth_id);
        Blog oldBlog=blogRepository.findBlogById(blog_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        if(oldBlog==null){
            throw new ApiException("blog not found");
        }
       else if(oldBlog.getUser().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
       oldBlog.setTitle(blog.getTitle());
       oldBlog.setBody(blog.getBody());
       oldBlog.setUser(user);
       blogRepository.save(oldBlog);
    }
    //user
    public void deleteBlog(Integer auth_id,Integer blog_id){
        User user=authRepository.findUsersById(auth_id);
        Blog blog=blogRepository.findBlogById(blog_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        if(blog==null){
            throw new ApiException("blog not found");
        }
        else if(blog.getUser().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
        blogRepository.delete(blog);
    }
    //user
    public Blog getBlogById(Integer auth_id, Integer blog_id){
        User user=authRepository.findUsersById(auth_id);
        Blog blog=blogRepository.findBlogById(blog_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        if(blog==null){
            throw new ApiException("blog not found");
        }
        else if(blog.getUser().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
        return blog;

    }
    public Blog getBlogByTitle(String title){
        Blog blog=blogRepository.findBlogByTitle(title);
        if(blog==null){
            throw new ApiException("blog not found");
        }
        return blog;

    }


}
