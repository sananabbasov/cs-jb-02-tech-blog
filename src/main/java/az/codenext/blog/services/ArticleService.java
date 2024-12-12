package az.codenext.blog.services;

import az.codenext.blog.dtos.article.*;
import az.codenext.blog.dtos.article.dashboard.ArticleCreateDto;
import az.codenext.blog.dtos.article.dashboard.ArticleDashboardDto;
import az.codenext.blog.dtos.article.dashboard.ArticleUpdateDto;
import az.codenext.blog.models.Article;
import az.codenext.blog.payloads.PaginationPayload;

import java.util.List;

public interface ArticleService {

    PaginationPayload<List<ArticleHomeDto>> homeArticle(Integer currentPage);
    List<ArticlePopularDto> getPopularArticles();
    List<ArticleBannerDto> getBannerArticles();
    List<ArticleTrendDto> getTrendVideoArticles();
    ArticleDetailDto getArticleDetail(Long id);
    ArticlePrevNextDto detailPrevPost(Long id);
    ArticlePrevNextDto detailNextPost(Long id);
    Article findArticleById(Long id);
    PaginationPayload<List<ArticleDashboardDto>> getDashboardArticles(Integer currentPage);
    boolean createArticle(ArticleCreateDto articleCreateDto);
    ArticleUpdateDto getUpdatedArticle(Long id);
    void updateArticle(Long id, ArticleUpdateDto articleUpdateDto);
}
