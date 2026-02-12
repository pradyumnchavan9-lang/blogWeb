package com.prady.blogWeb.service;


import com.prady.blogWeb.dto.request.AuthRequest;
import com.prady.blogWeb.dto.request.CreateUser;
import com.prady.blogWeb.dto.response.ArticleResponse;
import com.prady.blogWeb.dto.response.AuthResponse;
import com.prady.blogWeb.dto.response.UserResponse;
import com.prady.blogWeb.entity.User;
import com.prady.blogWeb.exception.ResourceNotFoundException;
import com.prady.blogWeb.jwt.JwtUtility;
import com.prady.blogWeb.mapper.ArticleMapper;
import com.prady.blogWeb.mapper.UserMapper;
import com.prady.blogWeb.repository.ArticleRepository;
import com.prady.blogWeb.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager ;
    private final JwtUtility jwtUtility;
    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;


    public UserService(PasswordEncoder passwordEncoder, UserMapper userMapper, UserRepository userRepo,
                       AuthenticationManager authenticationManager,JwtUtility jwtUtility,
                       ArticleMapper articleMapper,ArticleRepository articleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtUtility = jwtUtility;
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
    }

    public void register(CreateUser createUser){

        User user  = userMapper.createUserToUser(createUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
    }

    public AuthResponse login(AuthRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtUtility.generateToken(request.getUsername());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        return authResponse;
    }

    //Get User
    public UserResponse getUser(Long id){
        User user =  userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "User not found: " + id
        ));
        return userMapper.userToUserResponse(user);
    }


    //Get User By UserName
    public UserResponse getUserByUserName(String userName){
        return userMapper.userToUserResponse(userRepo.findByUsername(userName).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User Not Found: " + userName
                )
        ));
    }

    //Get All Articles of a User
    public Page<ArticleResponse> getUserArticles(Long id, Pageable pageable){

        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "User not found: " + id
        ));
        return articleRepository.findAllByUser(user,pageable)
                .map(article -> articleMapper.articleToArticleResponse(article));
    }

    public UserResponse loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User Not Found: " + username
                ));
        return userMapper.userToUserResponse(user);
    }
}
