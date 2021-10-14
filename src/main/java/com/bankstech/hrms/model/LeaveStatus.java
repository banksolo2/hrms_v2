package com.bankstech.hrms.model;

import lombok.*;

import javax.persistence.*;

@Entity( name = "LeaveStatus")
@Table(
        name = "leave_status",
        uniqueConstraints = @UniqueConstraint(
                name = "leave_status_name_unique",
                columnNames = "name"
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class LeaveStatus {
    @Id
    @SequenceGenerator(
            name = "leave_status_sequence",
            sequenceName = "leave_status_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "leave_status_sequence"
    )
    @Column(
            name = "leave_status_id",
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
}
