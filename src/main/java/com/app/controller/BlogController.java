package com.app.controller;


import com.app.model.Blog;
import com.app.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("")
    public ResponseEntity<Iterable<Blog>>findAllBlog(){
        List<Blog> blogList = (List<Blog>) blogService.findAll();
        if(blogList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog>findById(@PathVariable Long id){
        Optional<Blog> blog = blogService.findById(id);
        if(!blog.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(blog.get(),HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Blog> save(@RequestBody Blog blog){
        Blog blog1 = blogService.save(blog);
        return new ResponseEntity<>(blog1,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog>update(@PathVariable Long id,@RequestBody Blog blog){
        Optional<Blog> blog1 = blogService.findById(id);
        if(!blog1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(blog1.get().getId());
        return new ResponseEntity<>(blogService.save(blog),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog>delete(@PathVariable Long id){
        Optional<Blog> blog1 = blogService.findById(id);
        if(!blog1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.delete(id);
        return new ResponseEntity<>(blog1.get(),HttpStatus.OK);
    }

}
