package az.codenext.blog.dtos.user;


import az.codenext.blog.dtos.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private List<RoleDto> roles = new ArrayList<>();
}
