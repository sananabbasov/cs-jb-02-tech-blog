package az.codenext.blog.controller.dashboard;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public String index(){

        return "dashboard/index";
    }

}
