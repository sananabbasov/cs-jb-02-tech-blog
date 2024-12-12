package az.codenext.blog.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleUpdateDto {

    private List<Long> roleIds;
}
