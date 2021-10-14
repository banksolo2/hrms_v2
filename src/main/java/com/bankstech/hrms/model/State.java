package com.bankstech.hrms.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity( name = "State")
@Table(
        name = "states"
)
public class State {
    @Id
    @SequenceGenerator(
            name = "state_sequence",
            sequenceName = "state_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "state_sequence"
    )
    @Column(
            name = "state_id",
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
