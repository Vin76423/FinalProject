package org.tms.finalproject.service.mapper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.tms.finalproject.dto.CommentDto;
import org.tms.finalproject.entity.Comment;

@Service
public class BaseCommentDtoDoMapperService implements CommentDtoDoMapperService {

    @Override
    public Comment buildDo(CommentDto dto) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment();

        comment.setAuthorUserName(principal.getUsername());
        comment.setOwner(new org.tms.finalproject.entity.User(dto.getOrderOwnerId()));

        comment.setRating(dto.getRating());
        comment.setTitle(dto.getTitle());
        comment.setDescription(dto.getDescription());
        return comment;
    }
}
