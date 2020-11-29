package org.tms.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tms.finalproject.dto.CommentDto;
import org.tms.finalproject.entity.Comment;
import org.tms.finalproject.repository.CommentRepository;
import org.tms.finalproject.service.database.CommentService;
import org.tms.finalproject.service.mapper.CommentDtoDoMapperService;

@Controller
@RequestMapping(path = "/reputation")
public class UserReputationApplicationController {

    public CommentService commentService;
    public CommentDtoDoMapperService commentDtoDoMapperService;

    public UserReputationApplicationController(CommentService commentService,
                                               CommentDtoDoMapperService commentDtoDoMapperService) {
        this.commentService = commentService;
        this.commentDtoDoMapperService = commentDtoDoMapperService;
    }

    //    @GetMapping(path = "/set-comment")
//    public ModelAndView getCommentForm(ModelAndView modelAndView) {
//        modelAndView.addObject("dto", new CommentDto());
//        modelAndView.setViewName("reputation/comment-form");
//        return modelAndView;
//    }

//    @PostMapping(path = "/set-comment")
//    public ModelAndView getCommentForm(@ModelAttribute("dto") CommentDto commentDto,
//                                       BindingResult bindingResult,
//                                       ModelAndView modelAndView) {
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("reputation/comment-form");
//        } else {
//            Comment comment = commentDtoDoMapperService.buildDo(commentDto);
//            commentRepository.save(comment);
//            modelAndView.setViewName("redirect:/home");
//        }
//        return modelAndView;
//    }

    @PostMapping(path = "/set-comment")
    public ModelAndView getCommentForm(@RequestParam("order-id") long orderId,
                                       CommentDto commentDto,
                                       ModelAndView modelAndView) {
        Comment comment = commentDtoDoMapperService.buildDo(commentDto);
        commentService.createComment(comment);
        String link = String.format("redirect:%s?order-id=%d", commentDto.getPagLink(), orderId);
        modelAndView.setViewName(link);
        return modelAndView;
    }
}
