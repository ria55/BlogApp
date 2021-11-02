package app.services;

import app.dtos.AppUserDTO;
import app.models.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void register() throws Exception {
        AppUserDTO newUser = new AppUserDTO("test2@gmail.com", "test2", "test2");
        String body = mapper.writeValueAsString(newUser);

        mockMvc.perform(
                post("/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithUserDetails("user@user.com")
    void getUserPageWithUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is(403))
                .andReturn();
    }

    @Test
    @WithUserDetails("admin@admin.com")
    void getUserPageWithAdmin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    /*@Test
    void countUser() {
        long count = service.count(AppUser.class);
        assertEquals(9, count);
    }

    @Test
    void countBlog() {
        long count = service.count(Blog.class);
        assertEquals(0, count);
    }

    @Test
    void countNonExistingTable() {
        long count = service.count(Feedback.class);
        assertEquals(0, count);
    }*/

}