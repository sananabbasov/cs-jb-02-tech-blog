package az.codenext.blog.services.impls;

import az.codenext.blog.dtos.comment.CommentCreateDto;
import az.codenext.blog.dtos.comment.CommentDetailDto;
import az.codenext.blog.models.Article;
import az.codenext.blog.models.Comment;
import az.codenext.blog.models.User;
import az.codenext.blog.repositories.CommentRepository;
import az.codenext.blog.services.ArticleService;
import az.codenext.blog.services.CommentService;
import az.codenext.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ArticleService articleService;
    private final ModelMapper modelMapper;


    @Override
    public void createComment(CommentCreateDto commentCreateDto, Long articleId, String email) {
        Comment comment = new Comment();
        Article article = articleService.findArticleById(articleId);
        User user = userService.findUserByEmail(email);
        comment.setContent(commentCreateDto.getText());
        comment.setUser(user);
        comment.setArticle(article);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDetailDto> getArticleComments(Long id) {
        List<Comment> commentList = commentRepository.findByArticleId(id);
        List<CommentDetailDto> commentDetailDtos = commentList.stream().map(comment -> modelMapper.map(comment, CommentDetailDto.class)).collect(Collectors.toList());
        return commentDetailDtos;
    }
}
