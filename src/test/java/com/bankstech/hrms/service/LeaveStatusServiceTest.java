package com.bankstech.hrms.service;

import com.bankstech.hrms.model.LeaveStatus;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.LeaveStatusRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
class LeaveStatusServiceTest {

    @Autowired
    private LeaveStatusService underTest;

    @Autowired
    private LeaveStatusRepository leaveStatusRepository;

    @BeforeEach
    void setUp() {
        LeaveStatus l = new LeaveStatus();
        l.setName("Pending");
        l.setCode("Pending".toLowerCase().replace(" ","_"));
        leaveStatusRepository.save(l);

        LeaveStatus l2 = new LeaveStatus();
        l2.setName("Cancelled");
        l2.setCode("Cancelled".toLowerCase().replace(" ","_"));
        leaveStatusRepository.save(l2);

    }

    @AfterEach
    void tearDown() {
        leaveStatusRepository.deleteAll();
    }

    @Test
    public void testCreate(){
        //given
        LeaveStatus l1 = new LeaveStatus();
        l1.setName("Approved");

        //when
        ResponseMessage rm = underTest.create(l1);

        //then
        assertThat(rm.getType()).isEqualTo("success");

    }

    @Test
    public void testLeaveStatusExistOnCreate(){
        //given
        LeaveStatus l1 = new LeaveStatus();
        l1.setName("Pending");

        //when
        ResponseMessage rm = underTest.create(l1);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    public void testGetAll(){
        //when
        List<LeaveStatus> leaveStatuses = underTest.getAll();
        for(LeaveStatus l : leaveStatuses){
            System.out.println("ID: "+l.getId());
            System.out.println("Name: "+l.getName());
            System.out.println("Code: "+l.getCode());
        }

        //then
        assertThat(leaveStatuses.size()).isGreaterThan(0);
    }

    @Test
    public void testUpdate(){
        //given
        LeaveStatus l = leaveStatusRepository.findByName("Pending");
        l.setName("Approved");

        //when
        ResponseMessage rm = underTest.update(l);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    public void testIsNameExistOnUpdate(){
        //given
        LeaveStatus l = leaveStatusRepository.findByName("Pending");
        l.setName("Cancelled");
        l.setCode("Cancelled".toLowerCase().replace(" ", "_"));

        //when
        ResponseMessage rm = underTest.update(l);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    public void testDelete(){
        //given
        LeaveStatus l = leaveStatusRepository.findByName("Pending");

        //when
        ResponseMessage rm = underTest.delete(l.getId());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    public void testOptions(){
        //given
        LeaveStatus l = leaveStatusRepository.findByName("Pending");

        //when
        List<LeaveStatus> leaveStatuses = underTest.options(l.getId());
        for(LeaveStatus option : leaveStatuses){
            System.out.println("ID: "+option.getId());
            System.out.println("Name: "+option.getName());
            System.out.println("Code: "+option.getCode());
        }

        //then
        assertThat(leaveStatuses.size()).isGreaterThan(0);

    }
}