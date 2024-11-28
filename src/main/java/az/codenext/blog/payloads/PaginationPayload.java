package az.codenext.blog.payloads;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class PaginationPayload<T> {
    private Integer totalPage;
    private List<T> data;
}
