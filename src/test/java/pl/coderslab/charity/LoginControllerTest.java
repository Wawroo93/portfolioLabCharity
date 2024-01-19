package pl.coderslab.charity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderslab.charity.user.LoginController;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testLoginGet() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testLoginPostSuccess() throws Exception {
        User user = new User();
        user.setEmail("ooo@wp.pl");
        user.setPassword(new BCryptPasswordEncoder().encode("12345"));

        when(userService.findByEmail("ooo@wp.pl")).thenReturn(user);
        when(userService.checkPassword("12345", user.getPassword())).thenReturn(true);

        mockMvc.perform(post("/login")
                        .param("email", "ooo@wp.pl")
                        .param("password", "12345")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testLoginPostFailure() throws Exception {
        when(userService.findByEmail("test@test.com")).thenReturn(null);

        mockMvc.perform(post("/login")
                        .param("email", "test@test.com")
                        .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
