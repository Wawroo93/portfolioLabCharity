package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
class LoginController {

    private final UserService userService;

    @GetMapping
    String login(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            // Błąd logowania
        }
        return "login";
    }

    @PostMapping
    String loginPost(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Użytkownik jest uwierzytelniony
            return "redirect:/";
        } else {
            // Błędne dane logowania
            return "login";
        }
    }
}
