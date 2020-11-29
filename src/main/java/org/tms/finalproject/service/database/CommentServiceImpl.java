package org.tms.finalproject.service.database;

import org.springframework.stereotype.Service;
import org.tms.finalproject.entity.Comment;
import org.tms.finalproject.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("The Comment is null!");
        }
        commentRepository.save(comment);
    }
}
