package com.bankstech.hrms.service;

import com.bankstech.hrms.model.EmployeeStatus;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.EmployeeStatusRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeStatusServiceTest {
    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;

    @Autowired
    private EmployeeStatusService underTest;

    private long mills = System.currentTimeMillis();

    @BeforeEach
    void setUp() {
        String name = "Active";
        EmployeeStatus es = new EmployeeStatus();
        es.setName(name);
        es.setCode(name.toUpperCase().replace(" ","_"));
        es.setCreatedAt(new Date(mills));
        employeeStatusRepository.save(es);

        name = "Retired";
        EmployeeStatus es2 = new EmployeeStatus();
        es2.setName(name);
        es2.setCode(name.toUpperCase().replace(" ","_"));
        es2.setCreatedAt(new Date(mills));
        employeeStatusRepository.save(es2);
    }

    @AfterEach
    void tearDown() {
        employeeStatusRepository.deleteAll();
    }

    @Test
    void create() {
        //given
        EmployeeStatus es = new EmployeeStatus();
        es.setName("Pending");

        //when
        ResponseMessage rm = underTest.create(es);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isNameExistOnCreate(){
        //given
        EmployeeStatus es = new EmployeeStatus();
        es.setName("Active");

        //when
        ResponseMessage rm = underTest.create(es);
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void update() {
        //given
        EmployeeStatus es = employeeStatusRepository.findByName("Active");
        es.setName("Pending");

        //when
        ResponseMessage rm = underTest.update(es);
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("success");
        
    }

    @Test
    void isNameExistOnUpdate(){
        //given
        EmployeeStatus es = employeeStatusRepository.findByName("Active");
        es.setName("Retired");

        //when
        ResponseMessage rm = underTest.update(es);
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void delete() {
        //given
        EmployeeStatus es = employeeStatusRepository.findByName("Active");

        //when
        ResponseMessage rm = underTest.delete(es.getId());
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void all() {
        //when
        List<EmployeeStatus> employeeStatuses = underTest.all();
        for(EmployeeStatus es : employeeStatuses){
            System.out.println(es.toString());
        }

        //then
        assertThat(employeeStatuses.size()).isGreaterThan(0);
    }

    @Test
    void getById() {
        //given
        EmployeeStatus es1 = employeeStatusRepository.findByName("Active");

        //when
        EmployeeStatus es2 = underTest.getById(es1.getId());
        System.out.println(es2.toString());


    }

    @Test
    void options() {
        //given
        EmployeeStatus es = employeeStatusRepository.findByName("Active");

        //when
        List<EmployeeStatus> employeeStatuses = underTest.options(es.getId());
        for(EmployeeStatus es1 : employeeStatuses){
            System.out.println(es1.toString());
        }

        //then
        assertThat(employeeStatuses.size()).isGreaterThan(0);
    }
}