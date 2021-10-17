package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Department;
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
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository underTest;

    private long millis=System.currentTimeMillis();

    @BeforeEach
    void setUp() {
        String name = "Information Technology";
        Department d = new Department();
        d.setName(name);
        d.setCode(name.toUpperCase().replace(" ","_"));
        d.setCreatedAt(new Date(millis));
        underTest.save(d);

        name = "Accounting";
        Department d2 = new Department();
        d2.setName(name);
        d2.setCode(name.toUpperCase().replace(" ","_"));
        d2.setCreatedAt(new Date(millis));
        underTest.save(d2);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByName() {
        //given
        String name = "Information Technology";

        //when
        Department d = underTest.findByName(name);
        System.out.println(d.toString());

        //then
        assertThat(d.getId()).isGreaterThan(0);
    }

    @Test
    void findByCode() {
        //given
        String code = "INFORMATION_TECHNOLOGY";

        //when
        Department d = underTest.findByCode(code);
        System.out.println(d.toString());

        //then
        assertThat(d.getId()).isGreaterThan(0);
    }

    @Test
    void existByName() {
        //given
        String name = "Information Technology";

        //when
        boolean result = underTest.existByName(name);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void existByNameOnUpdate() {
        //given
        Department d = underTest.findByName("Accounting");

        //when
        boolean result = underTest.existByNameOnUpdate(d.getId(), "Human Resource");

        //then
        assertThat(result).isFalse();
    }

    @Test
    void options() {
        //given
        Department d = underTest.findByName("Accounting");

        //when
        List<Department> departments = underTest.options(d.getId());
        for(Department d2 : departments){
            System.out.println(d2.toString());
        }

        //then
        assertThat(departments.size()).isGreaterThan(0);
    }
}