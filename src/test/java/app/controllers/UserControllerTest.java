package app.controllers;

import app.returnModels.Feedback;
import app.testconfigs.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
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
    void getHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getUserPage_withoutLogin() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    @WithUserDetails("user@user.com")
    void getUserPage_withUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is(403))
                .andReturn();
    }

    @Test
    @WithUserDetails("admin@admin.com")
    void getUserPage_withAdmin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        Feedback response = new Feedback(json);

        assertTrue(response.isSuccess());
        assertEquals(HttpStatus.OK, response.getStatus());
        assertTrue(json.contains("list"));
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