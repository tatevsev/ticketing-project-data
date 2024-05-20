package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    @Override
    public void save(TaskDTO task) {

        task.setTaskStatus(Status.OPEN); //as there is no place for us to fill out those field on UI we  assign new Task default values
        task.setAssignedDate(LocalDate.now());


        taskRepository.save(taskMapper.convertToEntity(task));

    }

    @Override
    public void update(TaskDTO task) {

      Optional <Task> task1 = taskRepository.findById(task.getId());
        Task convertedTask = taskMapper.convertToEntity(task);

        if(task1.isPresent()){
            convertedTask.setTaskStatus(task1.get().getTaskStatus());
            convertedTask.setAssignedDate(task1.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }

    }

    @Override
    public void delete(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);

        if(foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }





    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List <Task> tasksList = taskRepository.findAll();


        return tasksList.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()){
            return taskMapper.convertToDTO(task.get());
        }
        return null;
    }

}
