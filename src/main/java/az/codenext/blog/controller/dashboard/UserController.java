package az.codenext.blog.controller.dashboard;

import az.codenext.blog.dtos.role.RoleDto;
import az.codenext.blog.dtos.user.UserDto;
import az.codenext.blog.dtos.user.UserRoleUpdateDto;
import az.codenext.blog.services.RoleService;
import az.codenext.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/dashboard/user")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String index(Model model){
        List<UserDto> users = userService.getDashboardUsers();
        model.addAttribute("users",users);
        return "/dashboard/user/index";
    }


    @GetMapping("/dashboard/user/update/{id}")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String update(@PathVariable Long id, Model model){
        List<RoleDto> roles = roleService.getRoles();
        model.addAttribute("roles",roles);
        return "/dashboard/user/update";
    }

    @PostMapping("/dashboard/user/update/{id}")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String update(@PathVariable Long id, UserRoleUpdateDto userRoleUpdateDto){
        userService.addRoles(id, userRoleUpdateDto.getRoleIds());
        return "redirect:/dashboard/user";
    }

}
