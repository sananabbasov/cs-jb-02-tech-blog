package az.codenext.blog.services.impls;

import az.codenext.blog.dtos.article.*;
import az.codenext.blog.dtos.article.dashboard.ArticleCreateDto;
import az.codenext.blog.dtos.article.dashboard.ArticleDashboardDto;
import az.codenext.blog.dtos.article.dashboard.ArticleUpdateDto;
import az.codenext.blog.models.Article;
import az.codenext.blog.models.Category;
import az.codenext.blog.models.Tag;
import az.codenext.blog.payloads.PaginationPayload;
import az.codenext.blog.repositories.ArticleRepository;
import az.codenext.blog.services.ArticleService;
import az.codenext.blog.services.CategoryService;
import az.codenext.blog.services.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final TagService tagService;


    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper, CategoryService categoryService, TagService tagService) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.tagService = tagService;
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

    @Override
    public PaginationPayload<List<ArticleDashboardDto>> getDashboardArticles(Integer currentPage) {
        currentPage = currentPage == null ? 0 : currentPage - 1;
        Pageable pageable = PageRequest.of(currentPage, 5, Sort.by("id").descending());
        Page<Article> articles = articleRepository.findAll(pageable);
        List<ArticleDashboardDto> result = articles.stream().map(a -> modelMapper.map(a, ArticleDashboardDto.class)).collect(Collectors.toList());
        PaginationPayload payload = new PaginationPayload();
        payload.setData(result);
        payload.setTotalPage(articles.getTotalPages());
        return payload;
    }

    @Override
    public boolean createArticle(ArticleCreateDto articleCreateDto) {
        try {
            Article article = new Article();
            article.setTitle(articleCreateDto.getTitle());
            article.setView(0);
            article.setCreatedDate(new Date());
            article.setDescription(articleCreateDto.getEditor1());
            article.setPhotoUrl(articleCreateDto.getPhotoUrl());
            article.setSeoUrl("falskjdflkasd");
            Category category = categoryService.findCategory(articleCreateDto.getCategoryId());
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : articleCreateDto.getTags()) {
                Tag tag = tagService.findTag(tagId);
                tags.add(tag);
            }
            article.setCategory(category);
            article.setTags(tags);
            articleRepository.save(article);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ArticleUpdateDto getUpdatedArticle(Long id) {
        Article findArticle = articleRepository.findById(id).orElseThrow();
        ArticleUpdateDto articleUpdateDto = modelMapper.map(findArticle, ArticleUpdateDto.class);
        articleUpdateDto.setEditor1(findArticle.getDescription());
        articleUpdateDto.setCategoryId(findArticle.getCategory().getId());

        return articleUpdateDto;
    }

    @Override
    public void updateArticle(Long id, ArticleUpdateDto articleUpdateDto) {
        Article findArticle = articleRepository.findById(id).orElseThrow();
        Category category = categoryService.findCategory(articleUpdateDto.getCategoryId());
        findArticle.setTitle(articleUpdateDto.getTitle());
        findArticle.setDescription(articleUpdateDto.getEditor1());
        findArticle.setSeoUrl(articleUpdateDto.getTitle().toLowerCase().replace(" ","-"));
        findArticle.setPhotoUrl(articleUpdateDto.getPhotoUrl());
        findArticle.setUpdatedDate(new Date());
        findArticle.setCategory(category);
        articleRepository.save(findArticle);
    }
}
