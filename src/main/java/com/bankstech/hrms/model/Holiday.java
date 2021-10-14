package com.bankstech.hrms.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity( name = "Holiday")
@Table(
        name = "holidays",
        uniqueConstraints = @UniqueConstraint(
                name = "holiday_name_unique",
                columnNames = "name"
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Holiday {
    @Id
    @SequenceGenerator(
            name = "holiday_sequence",
            sequenceName = "holiday_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "holiday_sequence"
    )
    @Column(
            name = "holiday_id",
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
            name = "description",
            columnDefinition = "Text"
    )
    private String description;

    @Column(
            name = "date_at",
            nullable = false
    )
    private Date dateAt;

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
