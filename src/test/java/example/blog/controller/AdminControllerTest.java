package example.blog.controller;

import example.blog.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "123", roles = "ADMIN")
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
        jdbcTemplate.execute("delete from post;");
    }

    @Test
    void shouldCreatePost() throws Exception {

        // given
        String givenTitle = "title";
        String givenText = "text";

        // when
        mockMvc.perform(post("/admin/post/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(TestUtils.buildUrlEncodedFormEntity("title", givenTitle, "text", givenText)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());

        // then
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from post;");
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        Map<String, Object> post = result.get(0);
        assertThat(post.get("title")).isEqualTo(givenTitle);
        assertThat(post.get("text")).isEqualTo(givenText);
    }

}
