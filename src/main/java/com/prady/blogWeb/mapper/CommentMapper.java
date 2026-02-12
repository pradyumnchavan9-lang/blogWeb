package com.prady.blogWeb.mapper;

import com.prady.blogWeb.dto.request.CreateComment;
import com.prady.blogWeb.dto.response.CommentResponse;
import com.prady.blogWeb.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private final UserMapper userMapper;

    public CommentMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public  Comment createCommentToComment(CreateComment createComment) {

        Comment comment = new Comment();
        comment.setContent(createComment.getContent());
        return comment;
    }

    public  CommentResponse commentToCommentResponse(Comment comment) {

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setCreatedAt(comment.getCreatedAt());
        commentResponse.setUpdatedAt(comment.getUpdatedAt());
        if(comment.getUser() != null) {
            commentResponse.setAuthor(
                    userMapper.userToAuthorResponse(comment.getUser())
            );
        }
        return commentResponse;
    }
}
