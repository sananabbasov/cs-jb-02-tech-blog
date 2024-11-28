package az.codenext.blog.controller;

import az.codenext.blog.dtos.auth.RegisterDto;
import az.codenext.blog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerDto", new RegisterDto());
        return "register.html";
    }


    @PostMapping("/register")
    public String register(@Valid  RegisterDto registerDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register";
        }
        userService.resister(registerDto);
        return "redirect:login";
    }

}
