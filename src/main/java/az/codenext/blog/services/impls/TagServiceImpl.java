package az.codenext.blog.services.impls;

import az.codenext.blog.dtos.tag.TagDto;
import az.codenext.blog.models.Tag;
import az.codenext.blog.repositories.TagRepository;
import az.codenext.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TagDto> getTags() {

        List<Tag> findTags = tagRepository.findAll();
        List<TagDto> tags = findTags.stream().map(tag -> modelMapper.map(tag,TagDto.class)).collect(Collectors.toList());
        return tags;
    }

    @Override
    public Tag findTag(Long id) {
        return tagRepository.findById(id).orElseThrow();
    }
}
