package az.codenext.blog.dtos.article;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ArticlePopularDto {
    private Long id;
    private String title;
    private String seoUrl;
    private int view;
    private String photoUrl;
    private Date createdDate;
}
