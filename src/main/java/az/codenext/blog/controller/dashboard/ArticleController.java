package az.codenext.blog.controller.dashboard;


import az.codenext.blog.dtos.article.dashboard.ArticleCreateDto;
import az.codenext.blog.dtos.article.dashboard.ArticleDashboardDto;
import az.codenext.blog.dtos.article.dashboard.ArticleUpdateDto;
import az.codenext.blog.dtos.category.CategoryDto;
import az.codenext.blog.dtos.tag.TagDto;
import az.codenext.blog.payloads.PaginationPayload;
import az.codenext.blog.services.ArticleService;
import az.codenext.blog.services.CategoryService;
import az.codenext.blog.services.TagService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final Cloudinary cloudinary;

    @GetMapping("/dashboard/article")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String index(Model model, Integer currentPage){
        PaginationPayload<List<ArticleDashboardDto>> articles = articleService.getDashboardArticles(currentPage);
        model.addAttribute("articles",articles.getData());
        return "/dashboard/article/index";
    }


    @GetMapping("/dashboard/article/create")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String create(Model model){
        List<CategoryDto> categories = categoryService.getCategories();
        List<TagDto> tags = tagService.getTags();
        model.addAttribute("categories",categories);
        model.addAttribute("tags",tags);
        return "/dashboard/article/create";
    }


    @PostMapping("/dashboard/article/create")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String create(ArticleCreateDto articleCreateDto, MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        articleCreateDto.setPhotoUrl((String) uploadResult.get("url"));
        boolean result  = articleService.createArticle(articleCreateDto);
        if (result){
            return "redirect:/dashboard/article";
        }
        return "redirect:/dashboard/article/create";
    }


    @GetMapping("/dashboard/article/update/{id}")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String update(@PathVariable Long id, Model model){
        List<CategoryDto> categories = categoryService.getCategories();
        List<TagDto> tags = tagService.getTags();
        ArticleUpdateDto article = articleService.getUpdatedArticle(id);
        model.addAttribute("categories",categories);
        model.addAttribute("tags",tags);
        model.addAttribute("article",article);
        return "/dashboard/article/update";
    }

    @PostMapping("/dashboard/article/update/{id}")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String update(@PathVariable Long id, ArticleUpdateDto articleUpdateDto, MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            articleUpdateDto.setPhotoUrl((String) uploadResult.get("url"));
        }
        articleService.updateArticle(id, articleUpdateDto);
        return "redirect:/dashboard/article";
    }

}
