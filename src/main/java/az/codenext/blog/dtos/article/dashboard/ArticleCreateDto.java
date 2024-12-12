package az.codenext.blog.dtos.article.dashboard;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCreateDto {
    private String title;
    private String editor1;
    private String photoUrl;
    private Long categoryId;
    private List<Long> tags = new ArrayList<>();
}
