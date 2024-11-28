package az.codenext.blog.services;

import az.codenext.blog.dtos.auth.RegisterDto;
import az.codenext.blog.models.User;

public interface UserService {

    boolean resister(RegisterDto registerDto);
    User findUserByEmail(String email);
}
