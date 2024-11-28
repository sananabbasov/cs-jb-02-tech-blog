package az.codenext.blog.services;

import az.codenext.blog.dtos.article.*;
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
}
