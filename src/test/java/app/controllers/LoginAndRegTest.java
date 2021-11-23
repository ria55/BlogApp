package app.controllers;

import app.dtos.AppUserDTO;
import app.returnModels.Feedback;
import app.testconfigs.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
public class LoginAndRegTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    /*@Test
    void register() throws Exception {
        AppUserDTO newUser = createNewUser();
        String body = mapper.writeValueAsString(newUser);

        mockMvc.perform(
                post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(status().isOk())
                .andReturn();
    }*/

    @Test
    void login() throws Exception {
        AppUserDTO user = new AppUserDTO("kiscica@gmail.com", "Kiscica", "admin");
        String body = mapper.writeValueAsString(user);

        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    void getLoggedInUser_withoutLogin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/use"))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        Feedback response = new Feedback(json);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatus());
        assertFalse(response.isSuccess());
    }

   /* @Test
    @WithUserDetails("kiscica@gmail.com")
    void getLoggedInUser_withLogin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        Feedback response = new Feedback(json);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertFalse(response.isSuccess());
    }*/

    private AppUserDTO createNewUser() {
        String randomName = UUID.randomUUID().toString();
        String username = randomName.substring(0, randomName.indexOf('-')) + "gmail.com";
        return new AppUserDTO(username, "test", "test");
    }

}
