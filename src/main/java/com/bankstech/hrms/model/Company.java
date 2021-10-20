package com.bankstech.hrms.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity( name = "Company")
@Table(
        name = "companies",
        uniqueConstraints = @UniqueConstraint(
                name = "company_name_unique",
                columnNames = "name"
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    @Column(
            name = "company_id",
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
            name = "short_name",
            nullable = false
    )
    private String shortName;

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

    @Column(name = "updated_at")
    private Date updatedAt;
}
