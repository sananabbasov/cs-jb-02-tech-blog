package az.codenext.blog.services;

import az.codenext.blog.dtos.role.RoleDto;
import az.codenext.blog.models.Role;

import java.util.List;

public interface RoleService {
    List<RoleDto> getRoles();
    List<Role> getRolesByIds(List<Long> ids);

}
