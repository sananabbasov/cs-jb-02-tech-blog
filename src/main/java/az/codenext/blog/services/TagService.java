package az.codenext.blog.services;

import az.codenext.blog.dtos.tag.TagDto;
import az.codenext.blog.models.Tag;

import java.util.List;

public interface TagService {
    List<TagDto> getTags();
    Tag findTag(Long id);
}
