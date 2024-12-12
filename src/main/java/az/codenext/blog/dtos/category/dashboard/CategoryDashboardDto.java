package az.codenext.blog.dtos.category.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDashboardDto {
    private Long id;
    private String name;
    private String seoUrl;

}
