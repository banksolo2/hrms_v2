package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.EmployeeStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeStatusRepositoryTest {

    @Autowired
    private EmployeeStatusRepository underTest;

    private long mills = System.currentTimeMillis();

    @BeforeEach
    void setUp() {
        String name = "Active";
        EmployeeStatus es = new EmployeeStatus();
        es.setName(name);
        es.setCode(name.toUpperCase().replace(" ","_"));
        es.setCreatedAt(new Date(mills));
        underTest.save(es);

        name = "Retired";
        EmployeeStatus es2 = new EmployeeStatus();
        es2.setName(name);
        es2.setCode(name.toUpperCase().replace(" ","_"));
        es2.setCreatedAt(new Date(mills));
        underTest.save(es2);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByName() {
        //given
        String name = "Active";

        //when
        EmployeeStatus es = underTest.findByName(name);
        System.out.println(es.toString());

        //then
        assertThat(es).isNotNull();
    }

    @Test
    void findByCode() {
        //given
        String code = "ACTIVE";

        //when
        EmployeeStatus es = underTest.findByCode(code);
        System.out.println(es.toString());

        //then
        assertThat(es).isNotNull();
    }

    @Test
    void existByName() {
        //given
        String name = "Active";

        //when
        boolean result = underTest.existByName(name);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void existByNameOnUpdate() {
        //given
        EmployeeStatus es = underTest.findByName("Active");

        //when
        boolean result = underTest.existByNameOnUpdate(es.getId(), "Retired");

        //then
        assertThat(result).isTrue();
    }

    @Test
    void options() {
        //given
        EmployeeStatus es = underTest.findByName("Active");

        //when
        List<EmployeeStatus> employeeStatuses = underTest.options(es.getId());
        for(EmployeeStatus es2 : employeeStatuses){
            System.out.println(es2.toString());
        }

        //then
        assertThat(employeeStatuses.size()).isGreaterThan(0);
    }
}