package br.com.reward.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.reward.application.Application;
import br.com.reward.response.TokenResponse;
import br.com.reward.security.SecurityConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class AuthControllerTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void shouldRefreshValidToken() throws Exception {

        // User/Login body
        final String body = 
            "{" + 
                "\"login\" : \"barbara@gmail.com\"," +
                "\"password\" : \"123\"" +
            "}";

        // Create User
        final String userEndpoint =  SecurityConstants.API_URL + "/users/sign-up";
        mockMvc.perform(post(userEndpoint)
            .header("Content-Type", "application/json")
            .content(body))
            .andExpect(status().isCreated())
            .andReturn();

        // Send login request
        final String loginEndpoint = SecurityConstants.LOGIN_URL;
        MvcResult resp = mockMvc.perform(post(loginEndpoint)
            .header("Content-Type", "application/json")
            .content(body))
			.andExpect(status().isOk()).andReturn();

        // Get token
		ObjectMapper mapper = new ObjectMapper();
		TokenResponse tokenResp = mapper.readValue(resp.getResponse().getContentAsString(), TokenResponse.class);
        
        // Send refresh token request
        final String refreshEndpoint = SecurityConstants.API_URL + "/auth/refresh_token";
        mockMvc.perform(post(refreshEndpoint)
            .header("Authorization", tokenResp.getToken()))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldNotRefreshInvalidToken() throws Exception {

        // Send refresh token request
        final String refreshEndpoint = SecurityConstants.API_URL + "/auth/refresh_token";
        mockMvc.perform(post(refreshEndpoint)
            .header("Authorization", "authorization"))
            .andExpect(status().isForbidden());

    }
}