package az.codenext.blog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 10000)
    private String description;
    private String seoUrl;
    private int view;
    private Date createdDate;
    private Date updatedDate;
    private String photoUrl;
    @ManyToOne
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "articles_tags",
            joinColumns = @JoinColumn(name = "articles", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tags", referencedColumnName = "id")
    )
    private Set<Tag> tags = new HashSet<>();


    @OneToMany(mappedBy = "article")
    private Set<Comment> articles = new HashSet<>();

}
