package com.bankstech.hrms.service;

import com.bankstech.hrms.model.Department;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentServiceTest {
    @Autowired
    private DepartmentService underTest;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        long millis = System.currentTimeMillis();
        String name = "Information Technology";
        Department d = new Department();
        d.setName(name);
        d.setCode(name.toUpperCase().replace(" ","_"));
        d.setCreatedAt(new Date(millis));
        departmentRepository.save(d);

        name = "Accounting";
        Department d2 = new Department();
        d2.setName(name);
        d2.setCode(name.toUpperCase().replace(" ","_"));
        d2.setCreatedAt(new Date(millis));
        departmentRepository.save(d2);
    }

    @AfterEach
    void tearDown() {
        departmentRepository.deleteAll();
    }

    @Test
    void create() {
        //given
        Department d = new Department();
        d.setName("Maintenance");

        //when
        ResponseMessage rm = underTest.create(d);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isNameExistOnCreate(){
        //given
        Department d = new Department();
        d.setName("Information Technology");

        //when
        ResponseMessage rm = underTest.create(d);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void all() {
        //when
        List<Department> departments = underTest.all();
        for(Department d : departments){
            System.out.println(d.toString());
        }

        //then
        assertThat(departments.size()).isGreaterThan(0);
    }

    @Test
    void update() {
        //given
        Department d = departmentRepository.findByName("Information Technology");
        d.setName("Maintenance");

        //when
        ResponseMessage rm = underTest.update(d);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isNameExistOnUpdate(){
        //given
        Department d = departmentRepository.findByName("Information Technology");
        d.setName("Accounting");

        //when
        ResponseMessage rm = underTest.update(d);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void delete() {
        //given
        Department d = departmentRepository.findByName("Accounting");

        //when
        ResponseMessage rm = underTest.delete(d.getId());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void options() {
        //given
        Department d = departmentRepository.findByName("Accounting");

        //when
        List<Department> departments = underTest.options(d.getId());
        for(Department d1 : departments){
            System.out.println(d1.toString());
        }

        //then
        assertThat(departments.size()).isGreaterThan(0);
    }
}