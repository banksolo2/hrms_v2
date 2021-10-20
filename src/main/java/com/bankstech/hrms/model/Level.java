package com.bankstech.hrms.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Level")
@Table(
        name = "levels",
        uniqueConstraints = @UniqueConstraint(
                name = "level_name_unique",
                columnNames = "name"
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Level {
    @Id
    @SequenceGenerator(
            name = "level_sequence",
            sequenceName = "level_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "level_sequence"
    )
    @Column(
            name = "level_id",
            nullable = false,
            updatable = false
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
