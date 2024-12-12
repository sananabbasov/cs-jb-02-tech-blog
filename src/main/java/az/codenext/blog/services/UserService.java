package az.codenext.blog.services;

import az.codenext.blog.dtos.auth.RegisterDto;
import az.codenext.blog.dtos.user.UserDto;
import az.codenext.blog.models.User;

import java.util.List;

public interface UserService {

    boolean resister(RegisterDto registerDto);
    User findUserByEmail(String email);
    List<UserDto> getDashboardUsers();
    UserDto getUserDetail(Long id);
    void addRoles(Long id, List<Long> roleIds);
}
