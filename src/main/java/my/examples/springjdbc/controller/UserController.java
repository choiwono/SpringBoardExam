package my.examples.springjdbc.controller;

import com.sun.deploy.net.HttpResponse;
import my.examples.springjdbc.dto.User;
import my.examples.springjdbc.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin2.message.helper.URLHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/loginform")
    public String loginform() {
        return "/member/loginform";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "email") String email,
                              @RequestParam(name = "passwd") String passwd,
                                                             Model model,
                                                             HttpServletRequest request,
                                                             HttpSession session) {
        User user = userService.getUserByEmail(email);
        session = request.getSession();
        if(user != null && user.getPasswd() != null) {
            PasswordEncoder passwordEncoder =
                    PasswordEncoderFactories.createDelegatingPasswordEncoder();
            boolean matches = passwordEncoder.matches(passwd, user.getPasswd());
            if(matches) {
                session.setAttribute("logininfo",user);
                model.addAttribute("message","로그인에 성공하셨습니다.");
            } else {
                model.addAttribute("message","로그인에 실패하셨습니다.");
            }
        }
        return "/member/sucess";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        model.addAttribute("message","로그아웃에 성공하셨습니다.");
        session.removeAttribute("logininfo");
        return "/member/sucess";
    }

    @GetMapping("/joinform")
    public String joinform(){
        return "/member/joinform";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user){
        // 값에 검증
        Assert.hasLength(user.getName(), "이름을 입력하세요.");
        userService.addUser(user);
        return "redirect:/list";
    }

    @PostMapping(value = "/checkEmail")
    public @ResponseBody String checkEmail(@RequestParam(name="email") String email,Model model){
        boolean result = false;
        User user = userService.getUserByEmail(email);
        if(user == null) {
            result = false;
        } else {
            result = true;
        }
        model.addAttribute("result",result);
        return "/joinform";
    }
}