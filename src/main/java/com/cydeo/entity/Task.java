package com.cydeo.entity;

import com.cydeo.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
@Where(clause = "is_deleted=false")
public class Task extends BaseEntity {
    private String taskSubject;
    private String taskDetail;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;
    private LocalDate assignedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User assignedEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;


}
