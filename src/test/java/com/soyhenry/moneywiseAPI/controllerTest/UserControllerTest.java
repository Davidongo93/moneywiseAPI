package com.soyhenry.moneywiseAPI.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soyhenry.moneywiseAPI.controller.UserController;
import com.soyhenry.moneywiseAPI.repository.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Start Testing UserController")
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {
@Mock
private UserService userService;
    @InjectMocks
    private UserController userController;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("POST user ")
    void TestCreateUserHandler() throws Exception {
        //given
        UserRequestDto userRequestDto = new UserRequestDto("name","mail","pass");


        lenient().when(userService.createUser(any(UserRequestDto.class))).thenReturn("User created successfully");
        //when

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)))
        //then
         .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("User created successfully"));

    }

}
