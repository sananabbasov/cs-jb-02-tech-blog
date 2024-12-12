package az.codenext.blog.dtos.article.dashboard;

import az.codenext.blog.dtos.category.CategoryDto;
import az.codenext.blog.dtos.tag.TagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {
    private String title;
    private String editor1;
    private String photoUrl;
    private Long categoryId;
    private List<Long> tagsId = new ArrayList<>();
}
