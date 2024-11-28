package az.codenext.blog.services.impls;

import az.codenext.blog.dtos.article.*;
import az.codenext.blog.dtos.category.CategoryDto;
import az.codenext.blog.models.Article;
import az.codenext.blog.payloads.PaginationPayload;
import az.codenext.blog.repositories.ArticleRepository;
import az.codenext.blog.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final ModelMapper modelMapper;
    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PaginationPayload<List<ArticleHomeDto>> homeArticle(Integer currentPage) {
        currentPage = currentPage == null ? 0 : currentPage - 1;
        Pageable pageable = PageRequest.of(currentPage, 5, Sort.by("id").descending());
        Page<Article> articles = articleRepository.findAll(pageable);
        List<ArticleHomeDto> result = articles.stream().map(a -> modelMapper.map(a, ArticleHomeDto.class)).collect(Collectors.toList());
        PaginationPayload payload = new PaginationPayload();
        payload.setData(result);
        payload.setTotalPage(articles.getTotalPages());
        return payload;
    }

    @Override
    public List<ArticlePopularDto> getPopularArticles() {
        Pageable pageable = PageRequest.of(1, 5, Sort.by("view").descending());
        Page<Article> articles = articleRepository.findAll(pageable);
        List<ArticlePopularDto> result = articles.stream().map(a -> modelMapper.map(a, ArticlePopularDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<ArticleBannerDto> getBannerArticles() {
        List<Article> articles = articleRepository.findTrendVideo(1L);
        List<ArticleBannerDto> result = articles.stream().map(a -> modelMapper.map(a, ArticleBannerDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<ArticleTrendDto> getTrendVideoArticles() {
        List<Article> articles = articleRepository.findBannerArticles();
        List<ArticleTrendDto> result = articles.stream().map(a -> modelMapper.map(a, ArticleTrendDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public ArticleDetailDto getArticleDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleDetailDto result = modelMapper.map(article, ArticleDetailDto.class);
        return result;
    }

    @Override
    public ArticlePrevNextDto detailPrevPost(Long id) {
        Optional<Article> article = articleRepository.findPreviousArticle(id);
        if (article.isEmpty()){
            Article a = articleRepository.findAll().stream().findFirst().get();
            ArticlePrevNextDto result = modelMapper.map(a, ArticlePrevNextDto.class);
            return result;
        }
        ArticlePrevNextDto res = modelMapper.map(article, ArticlePrevNextDto.class);
        return res;
    }

    @Override
    public ArticlePrevNextDto detailNextPost(Long id) {
        Article article = articleRepository.findNextArticle(id).orElseThrow();
        ArticlePrevNextDto res = modelMapper.map(article, ArticlePrevNextDto.class);
        return res;
    }

    @Override
    public Article findArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow();
    }
}
