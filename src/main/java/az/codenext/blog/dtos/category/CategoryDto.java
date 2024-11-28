package az.codenext.blog.dtos.category;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private String seoUrl;
}
