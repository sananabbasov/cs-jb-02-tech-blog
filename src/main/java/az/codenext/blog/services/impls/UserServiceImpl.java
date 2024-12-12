package az.codenext.blog.services.impls;

import az.codenext.blog.dtos.auth.RegisterDto;
import az.codenext.blog.dtos.user.UserDto;
import az.codenext.blog.models.Role;
import az.codenext.blog.models.User;
import az.codenext.blog.repositories.UserRepository;
import az.codenext.blog.services.RoleService;
import az.codenext.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


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

    @Override
    public List<UserDto> getDashboardUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUserDetail(Long id) {
        User findUser = userRepository.findById(id).orElseThrow();
        UserDto user = modelMapper.map(findUser, UserDto.class);
        return user;
    }

    @Override
    public void addRoles(Long id, List<Long> roleIds) {
        User findUser = userRepository.findById(id).orElseThrow();
        findUser.getRoles().clear();
        List<Role> roleList = roleService.getRolesByIds(roleIds);
        findUser.getRoles().addAll(roleList);
        userRepository.save(findUser);
    }
}
