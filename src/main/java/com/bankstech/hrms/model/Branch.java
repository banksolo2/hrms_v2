package com.bankstech.hrms.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Branch")
@Table(
        name = "branches",
        uniqueConstraints = @UniqueConstraint(
                name = "branch_name_unique",
                columnNames = "name"
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Branch {
    @Id
    @SequenceGenerator(
            name = "branch_sequence",
            sequenceName = "branch_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "branch_sequence"
    )
    @Column(
            name = "branch_id",
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
            nullable = false
    )
    private Date createdAt;

    @Column(
            name = "updated_at"
    )
    private Date updatedAt;
}
