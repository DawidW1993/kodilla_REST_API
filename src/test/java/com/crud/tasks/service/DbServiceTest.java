package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class DbServiceTest {
    @Autowired
    TaskRepository repository;

    @Autowired
    DbService dbService;

    @Test
    public void getAllTasks() {
        //Given
        repository.deleteAll();
        Task testTask = new Task();
        testTask.setTitle("test");
        testTask.setContent("test_Content");
        repository.save(testTask);
        //When
        List<Task> taskList = dbService.getAllTasks();
        Task resultTask = taskList.get(0);
        //Then
        assertEquals(1,taskList.size());
        assertTrue(!taskList.isEmpty());
        assertEquals("test",resultTask.getTitle());
        assertEquals("test_Content",resultTask.getContent());
    }

    @Test
    public void getTask() {
        //Given
        repository.deleteAll();
        Task testTask = new Task();
        testTask.setTitle("test");
        testTask.setContent("test_Content");
        long id = repository.save(testTask).getId();
        //When
        Task resultTask = dbService.getTask(id).get();
        //Then
        assertEquals(id,resultTask.getId().byteValue());
        assertEquals("test",resultTask.getTitle());
        assertEquals("test_Content",resultTask.getContent());
    }

    @Test
    public void saveTask() {
        //Given
        repository.deleteAll();
        Task testTask = new Task();
        testTask.setTitle("test");
        testTask.setContent("test_Content");
        long id = repository.save(testTask).getId();
        //When
        Task resultTask = dbService.getTask(id).get();
        //Then
        assertEquals(id,resultTask.getId().byteValue());
        assertEquals("test",resultTask.getTitle());
        assertEquals("test_Content",resultTask.getContent());
    }

    @Test
    public void deleteTask() {
        //Given
        repository.deleteAll();
        Task testTask = new Task();
        testTask.setTitle("test");
        testTask.setContent("test_content");
        repository.save(testTask);
        dbService.deleteTask(testTask);
        //When
        List<Task> taskList = repository.findAll();
        //Then
        assertTrue(taskList.isEmpty());
    }
}
