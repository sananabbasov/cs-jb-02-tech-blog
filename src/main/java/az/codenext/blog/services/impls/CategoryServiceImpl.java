package az.codenext.blog.services.impls;

import az.codenext.blog.dtos.category.CategoryDto;
import az.codenext.blog.dtos.category.dashboard.CategoryCreateDto;
import az.codenext.blog.dtos.category.dashboard.CategoryUpdateDto;
import az.codenext.blog.models.Category;
import az.codenext.blog.repositories.CategoryRepository;
import az.codenext.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<CategoryDto> getCategories() {
        List<Category> findCategories = categoryRepository.findAll();
        List<CategoryDto> categories = findCategories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categories;
    }

    @Override
    public Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public CategoryUpdateDto getUpdatedCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        CategoryUpdateDto categoryUpdateDto = modelMapper.map(category, CategoryUpdateDto.class);
        return categoryUpdateDto;
    }

    @Override
    public void updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(categoryUpdateDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public void createCategory(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto, Category.class);
        categoryRepository.save(category);
    }
}
