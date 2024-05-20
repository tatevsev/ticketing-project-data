package com.cydeo.mapper;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    private final ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Project convertToEntity(ProjectDTO dto){
        return modelMapper.map(dto,Project.class);
    }

    public ProjectDTO convertToDto(Project entity){
        return modelMapper.map(entity,ProjectDTO.class);
    }

}

/*
why we are adding @component ?
if a class has a dependency of other class, meaning being injected somewhere else we need to annotate that class with @component
 */
