package com.prady.blogWeb.controller;


import com.prady.blogWeb.dto.response.ArticleResponse;
import com.prady.blogWeb.dto.response.UserResponse;
import com.prady.blogWeb.entity.User;
import com.prady.blogWeb.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    //Get User
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){

        return ResponseEntity.ok().body(userService.getUser(id));
    }





    @GetMapping("/{userName}")
    public ResponseEntity<UserResponse> getUserByUserName(@PathVariable String userName){
        return new ResponseEntity<>(userService.getUserByUserName(userName),HttpStatus.OK);
    }

    // Get Articles of user
    @GetMapping("/{userId}/articles")
    public ResponseEntity<Page<ArticleResponse>> getUserArticles(@PathVariable Long userId,
                                                                 @PageableDefault(page = 0, size = 5) Pageable pageable){
        return ResponseEntity.ok().body(userService.getUserArticles(userId,pageable));
    }

    //Get Logged-In User
    @GetMapping("/me")
    public ResponseEntity<UserResponse> loggedInUser(){
        return ResponseEntity.ok().body(userService.loggedInUser());
    }
}
