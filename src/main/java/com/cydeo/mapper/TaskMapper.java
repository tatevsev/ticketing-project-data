package com.cydeo.mapper;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final ModelMapper modelMapper;


    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TaskDTO convertToDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task convertToEntity(TaskDTO dto) {
        return modelMapper.map(dto, Task.class);

    }


}