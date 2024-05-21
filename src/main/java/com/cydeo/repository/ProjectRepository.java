package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectCode(String code);
//    @Transactional
//    void deleteByProjectCode(String code);
    List<Project> findAllByAssignedManager(User manager);


}
