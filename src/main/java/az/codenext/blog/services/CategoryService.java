package az.codenext.blog.services;

import az.codenext.blog.dtos.category.CategoryDto;
import az.codenext.blog.dtos.category.dashboard.CategoryCreateDto;
import az.codenext.blog.dtos.category.dashboard.CategoryUpdateDto;
import az.codenext.blog.models.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategories();
    Category findCategory(Long id);
    CategoryUpdateDto getUpdatedCategory(Long id);
    void updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);
    void createCategory(CategoryCreateDto categoryCreateDto);
}
