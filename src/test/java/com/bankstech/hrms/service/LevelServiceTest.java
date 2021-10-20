package com.bankstech.hrms.service;

import com.bankstech.hrms.model.Level;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.LevelRepository;
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
class LevelServiceTest {

    @Autowired
    private LevelService underTest;

    @Autowired
    private LevelRepository levelRepository;

    private long mills = System.currentTimeMillis();


    @BeforeEach
    void setUp() {
        String name = "Senior Manager";
        Level l1 = new Level();
        l1.setName(name);
        l1.setCode(name.toUpperCase().replace(" ","_"));
        l1.setCreatedAt(new Date(mills));
        levelRepository.save(l1);

        name = "Manager";
        Level l2 = new Level();
        l2.setName(name);
        l2.setCode(name.toUpperCase().replace(" ","_"));
        l2.setCreatedAt(new Date(mills));
        levelRepository.save(l2);
    }

    @AfterEach
    void tearDown() {
        levelRepository.deleteAll();
    }

    @Test
    void create() {
        //given
        Level l = new Level();
        l.setName("Managing Director");

        //when
        ResponseMessage rm = underTest.create(l);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isNameExistOnCreate(){
        //given
        Level l = new Level();
        l.setName("Manager");

        //when
        ResponseMessage rm = underTest.create(l);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void all() {
        //when
        List<Level> levels = underTest.all();

        //then
        assertThat(levels.size()).isGreaterThan(0);
    }

    @Test
    void getLevel() {
        //given
        Level l = levelRepository.findByName("Manager");

        //when
        Level l2 = underTest.getLevel(l.getId());

        //then
        assertThat(l2).isNotNull();
    }

    @Test
    void update() {
        //given
        Level l = levelRepository.findByName("Manager");
        l.setName("Deputy Manager");

        //when
        ResponseMessage rm = underTest.update(l);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isNameExistOnUpdate(){
        //given
        Level l = levelRepository.findByName("Manager");
        l.setName("Senior Manager");

        //when
        ResponseMessage rm = underTest.update(l);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void delete() {
        //given
        Level l = levelRepository.findByName("Manager");

        //when
        ResponseMessage rm = underTest.delete(l.getId());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void options() {
        //given
        Level l = levelRepository.findByName("Manager");

        //when
        List<Level> levels = underTest.options(l.getId());

        //then
        assertThat(levels.size()).isGreaterThan(0);
    }
}