package com.bankstech.hrms.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity( name = "MaritalStatus")
@Table(
        name = "marital_statues",
        uniqueConstraints = @UniqueConstraint(
                name = "marital_status_name_unique",
                columnNames = "name"
        )
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MaritalStatus {
    @Id
    @SequenceGenerator(
            name = "marital_status_sequence",
            sequenceName = "marital_status_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "marital_status_sequence"
    )
    @Column(
            name = "marital_status_id",
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
