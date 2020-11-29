package org.tms.finalproject.service.mapper;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.tms.finalproject.dto.CommentDto;
import org.tms.finalproject.entity.Comment;
import org.tms.finalproject.entity.User;

@Service
public class BaseCommentDtoDoMapperService implements CommentDtoDoMapperService {

    @Override
    public Comment buildDo(CommentDto dto) {
        SecurityContext context = SecurityContextHolder.getContext();
        String userName = ((org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal()).getUsername();

        Comment comment = new Comment();
        comment.setAuthorUserName(userName);
        comment.setOwner(new User(dto.getCommentOwnerId()));

        comment.setRating(dto.getRating());
        comment.setTitle(dto.getTitle());
        comment.setDescription(dto.getDescription());
        return comment;
    }
}
