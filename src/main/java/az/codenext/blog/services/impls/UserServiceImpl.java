package az.codenext.blog.services.impls;

import az.codenext.blog.dtos.auth.RegisterDto;
import az.codenext.blog.models.User;
import az.codenext.blog.repositories.UserRepository;
import az.codenext.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public boolean resister(RegisterDto registerDto) {

        User findUser = userRepository.findByEmail(registerDto.getEmail());
        if (findUser != null){
            return false;
        }
        String hashPassword = passwordEncoder.encode(registerDto.getPassword());
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(hashPassword);
        userRepository.save(user);
        return true;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
