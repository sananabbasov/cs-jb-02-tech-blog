package az.codenext.blog.dtos.comment;

import az.codenext.blog.dtos.article.ArticleDto;
import az.codenext.blog.dtos.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetailDto {
    private Long id;
    private String content;
    private ArticleDto article;
    private UserDto user;
}
