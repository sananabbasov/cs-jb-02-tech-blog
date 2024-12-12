package az.codenext.blog.dtos.article.dashboard;

import az.codenext.blog.dtos.category.CategoryDto;
import az.codenext.blog.dtos.tag.TagDto;
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
public class ArticleDashboardDto {
    private Long id;
    private String title;
    private String seoUrl;
    private int view;
    private Date createdDate;
    private Date updatedDate;
    private String photoUrl;
    private CategoryDto category;
    private List<TagDto> tags = new ArrayList<>();
}
