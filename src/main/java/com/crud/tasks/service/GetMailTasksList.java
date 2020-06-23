package com.crud.tasks.service;

import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetMailTasksList {
    @Autowired
    private TaskRepository taskRepository;

    public List<String> get(){
        return taskRepository.findAll().stream()
                .map(task -> task.getTitle() + ": " + task.getContent())
                .collect(Collectors.toList());
    }

}
