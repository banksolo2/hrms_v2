package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.LeaveStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase( replace = Replace.NONE)
class LeaveStatusRepositoryTest {

    @Autowired
    private LeaveStatusRepository underTest;

    @BeforeEach
    void setUp() {
        LeaveStatus leaveStatus = new LeaveStatus();
        leaveStatus.setName("Approved");
        leaveStatus.setCode("Approved".toLowerCase().replace(" ","_"));
        underTest.save(leaveStatus);

        LeaveStatus l2 = new LeaveStatus();
        l2.setName("Pending");
        l2.setCode("Pending".toLowerCase().replace(" ","_"));
        underTest.save(l2);
    }

    @Test
    void findByName() {
        //given
        String name = "Approved";

        //When
        LeaveStatus leaveStatus = underTest.findByName(name);
        System.out.println("ID: "+ leaveStatus.getId());
        System.out.println("Name: "+leaveStatus.getName());
        System.out.println("Code: "+leaveStatus.getCode());

        //then
        assertThat(leaveStatus).isNotNull();
    }

    @Test
    void findByCode() {
        //given
        String code = "approved";

        //when
        LeaveStatus leaveStatus = underTest.findByCode(code);
        System.out.println("ID: "+ leaveStatus.getId());
        System.out.println("Name: "+leaveStatus.getName());
        System.out.println("Code: "+leaveStatus.getCode());

        //then
        assertThat(leaveStatus).isNotNull();
    }

    @Test
    void existByName() {
        //given
        String name = "approved";

        //when
        boolean result = underTest.existByName(name);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void existByNameOnUpdate() {
        //given
        LeaveStatus leaveStatus = underTest.findByName("Approved");

        //when
        boolean result = underTest.existByNameOnUpdate(leaveStatus.getId(),leaveStatus.getName());

        //then
        assertThat(result).isFalse();

    }

    @Test
    void leaveStatusOptions() {
        //given
        LeaveStatus leaveStatus = underTest.findByName("Approved");

        //when
        List<LeaveStatus> leaveStatuses = underTest.leaveStatusOptions(leaveStatus.getId());
        for(LeaveStatus l : leaveStatuses){
            System.out.println("ID: "+l.getId());
            System.out.println("Name: "+l.getName());
            System.out.println("Code: "+l.getCode());
        }

        //then
        assertThat(leaveStatuses.size()).isGreaterThan(0);
    }
}