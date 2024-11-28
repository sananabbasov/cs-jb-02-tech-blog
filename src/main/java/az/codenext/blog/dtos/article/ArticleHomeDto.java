package az.codenext.blog.dtos.article;

import az.codenext.blog.dtos.category.CategoryDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleHomeDto {
    private Long id;
    private String title;
    private String description;
    private String seoUrl;
    private int view;
    private String photoUrl;
    private CategoryDto category;
}
