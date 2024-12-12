package az.codenext.blog.dtos.category;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String seoUrl;
}
