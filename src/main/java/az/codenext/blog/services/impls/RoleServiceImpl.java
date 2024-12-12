package az.codenext.blog.services.impls;

import az.codenext.blog.dtos.role.RoleDto;
import az.codenext.blog.models.Role;
import az.codenext.blog.repositories.RoleRepository;
import az.codenext.blog.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleDto> roles = roleList.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public List<Role> getRolesByIds(List<Long> ids) {
        List<Role> roleList = roleRepository.findAllById(ids);
        return roleList;
    }
}
