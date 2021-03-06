package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Holiday;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
class HolidayRepositoryTest {

    @Autowired
    private HolidayRepository underTest;

    private long millis=System.currentTimeMillis();

    @BeforeEach
    void setUp() {
        String dateText = "2021-12-25";
        Holiday h1 = new Holiday();
        h1.setName("Christmas");
        h1.setDescription("Christmas day");
        Date dateAt = Date.valueOf(dateText);
        h1.setDateAt(dateAt);
        h1.setCreatedAt(new Date(millis));
        underTest.save(h1);

        dateText = "2021-12-26";
        Holiday h2 = new Holiday();
        h2.setName("Boxing Day");
        h2.setDescription("Boxing day holiday");
        dateAt = Date.valueOf(dateText);
        h2.setDateAt(dateAt);
        h2.setCreatedAt(new Date(millis));
        underTest.save(h2);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByName() {
        //given
        String name = "Christmas";

        //when
        Holiday h = underTest.findByName(name);
        System.out.println(h.toString());

        //then
        assertThat(h).isNotNull();

    }

    @Test
    void existByName() {
        //given
        String name = "Christmas";

        //when
        boolean result = underTest.existByName(name);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void existByNameOnUpdate() {
        //given
        String name = "Christmas";
        Holiday h = underTest.findByName(name);
        h.setName("Testing");

        //when
        boolean result = underTest.existByNameOnUpdate(h.getId(),h.getName());

        //then
        assertThat(result).isFalse();
    }

    @Test
    void existByDateAt(){
        //given
        String dateText = "2021-12-25";
        Date dateAt = Date.valueOf(dateText);

        //when
        boolean result = underTest.existByDateAt(dateAt);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void existByDateAtOnUpdate(){
        //given
        Holiday h = underTest.findByName("Christmas");
        Date dateAt = Date.valueOf("2021-12-27");
        h.setDateAt(dateAt);

        //when
        boolean result = underTest.existByDateAtOnUpdate(h.getId(), h.getDateAt());

        //then
        assertThat(result).isFalse();

    }
}