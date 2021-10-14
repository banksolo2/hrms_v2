package com.bankstech.hrms.service;

import com.bankstech.hrms.model.Holiday;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.HolidayRepository;
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
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
class HolidayServiceTest {
    @Autowired
    private HolidayService underTest;

    @Autowired
    private HolidayRepository holidayRepository;

    @BeforeEach
    void setUp() {
        long millis=System.currentTimeMillis();
        Holiday h1 = new Holiday();
        h1.setName("Christmas");
        h1.setDescription("Christmas day");
        Date dateAt = Date.valueOf("2021-12-25");
        h1.setDateAt(dateAt);
        h1.setCreatedAt(new Date(millis));
        holidayRepository.save(h1);

        Holiday h2 = new Holiday();
        h2.setName("Easter");
        h2.setDescription("Easter day");
        dateAt = Date.valueOf("2021-04-26");
        h2.setDateAt(dateAt);
        h2.setCreatedAt(new Date(millis));
        holidayRepository.save(h2);
    }

    @AfterEach
    void tearDown() {
        holidayRepository.deleteAll();
    }

    @Test
    void create() {
        //given
        Holiday h = new Holiday();
        h.setName("Boxing Day");
        h.setDescription("A holiday after christmas");
        Date dateAt = Date.valueOf("2021-12-26");
        h.setDateAt(dateAt);

        //when
        ResponseMessage rm = underTest.create(h);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isDateAtExistOnCreate(){
        //given
        Holiday h = new Holiday();
        h.setName("Christmas");
        h.setDescription("Christmas day holiday");
        Date dateAt = Date.valueOf("2021-12-25");
        h.setDateAt(dateAt);

        //when
        ResponseMessage rm = underTest.create(h);

        //Then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void all() {
        //when
        List<Holiday> holidays = underTest.all();
        for(Holiday h : holidays){
            System.out.println(h.toString());
        }

        //then
        assertThat(holidays.size()).isGreaterThan(0);
    }

    @Test
    void update() {
        //given
        Holiday h = holidayRepository.findByName("Christmas");
        Date dateAt = Date.valueOf("2021-12-26");
        h.setDateAt(dateAt);

        //when
        ResponseMessage rm = underTest.update(h);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isDateAtExistOnUpdate(){
        //given
        Holiday h = holidayRepository.findByName("Christmas");
        Date dateAt = Date.valueOf("2021-04-26");
        h.setDateAt(dateAt);

        //when
        ResponseMessage rm = underTest.update(h);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void delete() {
        //given
        Holiday h = holidayRepository.findByName("Christmas");

        //when
        ResponseMessage rm = underTest.delete(h.getId());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }
}