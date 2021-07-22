package com.nt.rookie.post.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nt.rookie.post.exception.NotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/add-post.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/remove-post.sql")
public class PostControllerIntTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/posts")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(12)))
                .andExpect(jsonPath("$[*].id").isNotEmpty());
    }

    @ParameterizedTest(name = "Test get post with id {0} not found")
    @ValueSource(ints = {13, -1, 15})
    public void getByIdNotFound(int id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/posts/" + id)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> Assertions.assertEquals("No record found with id " + id, result.getResolvedException().getMessage()));
    }

    @ParameterizedTest(name = "Test get post with id {0} found")
    @ValueSource(ints = {1, 2, 3})
    public void getByIdFound(int id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/posts/" + id)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Post " + id));
    }
}
