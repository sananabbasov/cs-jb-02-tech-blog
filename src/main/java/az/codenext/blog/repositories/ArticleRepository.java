package az.codenext.blog.repositories;

import az.codenext.blog.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.id > :currentId ORDER BY a.id ASC LIMIT 1") // 36 37 38 39 40
    Optional<Article> findNextArticle(Long currentId);
    @Query("SELECT a FROM Article a WHERE a.id < :currentId ORDER BY a.id DESC LIMIT 1") // 40 39 38 37 36
    Optional<Article> findPreviousArticle(Long currentId);

    @Query("SELECT a FROM Article a JOIN a.tags t WHERE t.id = :tagId ORDER BY a.view DESC LIMIT 3")
    List<Article> findTrendVideo(Long tagId);

    @Query("SELECT a FROM Article a ORDER BY a.view ASC LIMIT 3")
    List<Article> findBannerArticles();
}
