package az.codenext.blog.dtos.article;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleTrendDto {
    private Long id;
    private String title;
    private String seoUrl;
    private String photoUrl;
}
