package az.codenext.blog.controller.dashboard;

import az.codenext.blog.dtos.category.CategoryDto;
import az.codenext.blog.dtos.category.dashboard.CategoryCreateDto;
import az.codenext.blog.dtos.category.dashboard.CategoryUpdateDto;
import az.codenext.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/dashboard/category")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String index(Model model){

        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "/dashboard/category/index";
    }

    @GetMapping("/dashboard/category/create")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String create(){
        return "/dashboard/category/create";
    }

    @PostMapping("/dashboard/category/create")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String create(CategoryCreateDto categoryCreateDto){
        categoryService.createCategory(categoryCreateDto);
        return "redirect:/dashboard/category";
    }


    @GetMapping("/dashboard/category/update/{id}")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String update(@PathVariable Long id, Model model){
        CategoryUpdateDto category = categoryService.getUpdatedCategory(id);
        model.addAttribute("category",category);
        return "/dashboard/category/update";
    }

    @PostMapping("/dashboard/category/update/{id}")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String update(@PathVariable Long id,CategoryUpdateDto categoryUpdateDto){
        categoryService.updateCategory(id,categoryUpdateDto);
        return "redirect:/dashboard/category";
    }
}
