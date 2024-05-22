package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final MapperUtil mapperUtil;


    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        //I have Role entities from DB
        //I need to convert those Role entitites to DTOs
        //I need to use ModelMapper
        //I already created a class RoleMapper and there are mathods for me that will make this conversions
        //roleList.stream().map(roleMapper::convertToDto).collect(Collectors.toList());

        //     without generic mapper
        //return    roleList.stream().map(roleMapper::convertToDto).collect(Collectors.toList());
        //     with generic mapper Type

        return    roleList.stream().map(role -> mapperUtil.convert(role,new RoleDTO())).collect(Collectors.toList());

        //        with generic mapper Class <T>
       // return    roleList.stream().map(role -> mapperUtil.convert(role,RoleDTO.class)).collect(Collectors.toList());

    }

    @Override
    public RoleDTO findById(Long id) {
        return roleMapper.convertToDto(roleRepository.findById(id).get());
    }
}
