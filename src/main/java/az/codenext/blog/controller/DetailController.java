package az.codenext.blog.controller;

import az.codenext.blog.dtos.article.ArticleDetailDto;
import az.codenext.blog.dtos.article.ArticlePrevNextDto;
import az.codenext.blog.dtos.comment.CommentCreateDto;
import az.codenext.blog.dtos.comment.CommentDetailDto;
import az.codenext.blog.services.ArticleService;
import az.codenext.blog.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;


@Controller
public class DetailController {


    private final ArticleService articleService;
    private final CommentService commentService;

    public DetailController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/detail/{id}/{seo}")
    public String detail(@PathVariable Long id, Model model){
        ArticleDetailDto articleDetailDto = articleService.getArticleDetail(id);
        ArticlePrevNextDto next = articleService.detailNextPost(id);
        ArticlePrevNextDto prev = articleService.detailPrevPost(id);
        List<CommentDetailDto> commentDetail = commentService.getArticleComments(id);
        model.addAttribute("article",articleDetailDto);
        model.addAttribute("comments",commentDetail);
        model.addAttribute("prev",prev);
        model.addAttribute("next",next);
        return "detail.html";
    }


    @PostMapping("/detail/{id}/{seo}")
    public String detail(@PathVariable Long id, @PathVariable String seo, CommentCreateDto commentCreateDto, Principal principal){

        String useEmail = principal.getName();
        commentService.createComment(commentCreateDto, id, useEmail);
        return "redirect:/detail/"+id + "/" +seo;
    }
}
