package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @GetMapping
    String login(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
        }
        return "login";
    }

    //    @PostMapping
//    String loginPost(@RequestParam String email, @RequestParam String password) {
//        User user = userService.findByEmail(email);
//        if (user != null && user.getPassword().equals(password)) {
//            return "redirect:/";
//        } else {
//            return "login";
//        }
//    }
    @PostMapping
    String loginPost(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);
        if (user != null && userService.checkPassword(password, user.getPassword())) {
            return "redirect:/";
        } else {
            return "redirect:/login?error";
        }
    }
}
