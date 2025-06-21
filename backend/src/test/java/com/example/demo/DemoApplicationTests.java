package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTasksReturnsEmptyList() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
    }

	@Test
	void testAddTaskWithCreatedAt() throws Exception {
		String taskJson = "{\"taskdescription\":\"Test-Aufgabe\",\"createdAt\":\"2025-06-21\"}";

		mockMvc.perform(post("/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(taskJson))
				.andExpect(status().isOk());

		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"taskdescription\":\"Test-Aufgabe\",\"createdAt\":\"2025-06-21\"}]"));
	}

	@Test
	void contextLoads() {
		assertTrue(true, "alles gut");
	}

	@Test
	void testAddEmptyTaskShouldNotBeAdded() throws Exception {
		String emptyTaskJson = "{\"taskdescription\":\"\", \"createdAt\":\"2025-06-21\"}";

		mockMvc.perform(post("/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(emptyTaskJson))
				.andExpect(status().isOk());

		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

}
