package az.codenext.blog.controller;


import az.codenext.blog.dtos.article.ArticleBannerDto;
import az.codenext.blog.dtos.article.ArticleHomeDto;
import az.codenext.blog.dtos.article.ArticlePopularDto;
import az.codenext.blog.dtos.article.ArticleTrendDto;
import az.codenext.blog.models.Article;
import az.codenext.blog.payloads.PaginationPayload;
import az.codenext.blog.services.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(Model model,Integer currentPage) {
        PaginationPayload<List<ArticleHomeDto>> articles = articleService.homeArticle(currentPage);
        List<ArticlePopularDto> articlePopularDtoList = articleService.getPopularArticles();
        List<ArticleTrendDto> articleTrendDtoList = articleService.getTrendVideoArticles();
        List<ArticleBannerDto> articleBannerDtoList = articleService.getBannerArticles();
        model.addAttribute("articles",articles.getData());
        model.addAttribute("popularArticles", articlePopularDtoList);
        model.addAttribute("trendArticles", articleTrendDtoList);
        model.addAttribute("banners", articleBannerDtoList);
        model.addAttribute("totalPage",articles.getTotalPage());
        return "home.html";
    }


    @GetMapping("/contact")
    public String contact(){
        return "contact.html";
    }



}
