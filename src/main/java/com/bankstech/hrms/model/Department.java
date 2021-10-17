package com.bankstech.hrms.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity( name = "Department")
@Table(
        name = "departments",
        uniqueConstraints = @UniqueConstraint(
                name = "department_name_unique",
                columnNames = "name"
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    @Column(
            name = "department_id",
            updatable = false,
            nullable = false
    )
    private Integer id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "code",
            nullable = false
    )
    private String code;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private Date createdAt;

    @Column(
            name = "updated_at"
    )
    private Date updatedAt;
}
