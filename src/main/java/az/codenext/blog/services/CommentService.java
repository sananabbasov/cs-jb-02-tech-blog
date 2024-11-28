package az.codenext.blog.services;

import az.codenext.blog.dtos.comment.CommentCreateDto;
import az.codenext.blog.dtos.comment.CommentDetailDto;

import java.util.List;

public interface CommentService {

    void createComment(CommentCreateDto commentCreateDto, Long articleId, String email);
    List<CommentDetailDto> getArticleComments(Long id);
}
