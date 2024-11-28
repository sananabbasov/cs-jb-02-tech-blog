package az.codenext.blog.dtos.article;

import az.codenext.blog.dtos.category.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ArticleDetailDto {
    private String title;
    private String description;
    private int view;
    private Date createdDate;
    private String photoUrl;
    private CategoryDto category;
}
