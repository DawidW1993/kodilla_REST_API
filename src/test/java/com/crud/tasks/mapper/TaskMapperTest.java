package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test","test_Content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L,task.getId().byteValue());
        assertEquals("test",task.getTitle());
        assertEquals("test_Content",task.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L,"test","test_Content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L,taskDto.getId().byteValue());
        assertEquals("test",taskDto.getTitle());
        assertEquals("test_Content",taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L,"test","test_Content");
        taskList.add(task);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        TaskDto taskDto = taskDtos.get(0);
        //Then
        assertEquals(1L,taskDto.getId().byteValue());
        assertEquals("test",taskDto.getTitle());
        assertEquals("test_Content",taskDto.getContent());
    }
}