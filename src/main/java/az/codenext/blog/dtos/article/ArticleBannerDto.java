package az.codenext.blog.dtos.article;

import az.codenext.blog.dtos.category.CategoryDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ArticleBannerDto {
    private Long id;
    private String title;
    private String description;
    private String seoUrl;
    private String photoUrl;
    private CategoryDto category;
}
