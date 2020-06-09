package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.card.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    public void getTasks() throws Exception {
        //Given
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(1L,"test", "Test_Content"));

        when(taskController.getTasks()).thenReturn(tasks);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title",is("test")))
                .andExpect(jsonPath("$[0].content",is("Test_Content")));
    }

    @Test
    public void getTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test", "Test_Content");

        when(taskController.getTask(1L)).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("test")))
                .andExpect(jsonPath("$.content",is("Test_Content")));
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test", "Test_Content");

        when(taskController.deleteTask(1L)).thenReturn(true);
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(true)));
    }

    @Test
    public void updateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test", "Test_Content");

        when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("test")))
                .andExpect(jsonPath("$.content",is("Test_Content")));
    }

    @Test
    public void createTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test", "Test_Content");

        when(taskController.createTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("test")))
                .andExpect(jsonPath("$.content",is("Test_Content")));
    }
}