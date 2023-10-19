package tests.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ucr.ac.fitfun.security.api.types.OkResponse;
import ucr.ac.fitfun.security.api.types.RegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SecurityTests extends BaseIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void healthTests() throws Exception {
        this.mockMvc.perform(get("/health")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));
    }

    @Test
    public void registerAndLogin() throws Exception {
        RegisterRequest request = new RegisterRequest(
                "John",
                "john@test.com",
                "123456");

        var rawResponse = this.mockMvc
                .perform(
                        post("/register")
                                .content(mapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")))
                .andReturn();
        OkResponse response = mapper.readValue(rawResponse.getResponse().getContentAsString(), OkResponse.class);
        assertThat(response.status()).isEqualTo("ok");

    }

}
