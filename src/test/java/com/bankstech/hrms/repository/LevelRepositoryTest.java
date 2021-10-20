package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Level;
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
class LevelRepositoryTest {

    @Autowired
    private LevelRepository underTest;
    private long mills = System.currentTimeMillis();

    @BeforeEach
    void setUp() {
        String name = "Senior Manager";
        Level l1 = new Level();
        l1.setName(name);
        l1.setCode(name.toUpperCase().replace(" ","_"));
        l1.setCreatedAt(new Date(mills));
        underTest.save(l1);

        name = "Manager";
        Level l2 = new Level();
        l2.setName(name);
        l2.setCode(name.toUpperCase().replace(" ","_"));
        l2.setCreatedAt(new Date(mills));
        underTest.save(l2);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByName() {
        //given
        String name = "Manager";

        //when
        Level l = underTest.findByName(name);

        //then
        assertThat(l).isNotNull();
    }

    @Test
    void findByCode() {
        //given
        String code = "MANAGER";

        //when
        Level l = underTest.findByCode(code);

        //then
        assertThat(l).isNotNull();
    }

    @Test
    void existByName() {
        //given
        String name = "Manager";

        //when
        boolean result = underTest.existByName(name);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void existByNameOnUpdate() {
        //given
        Level l = underTest.findByName("Manager");

        //when
        boolean result = underTest.existByNameOnUpdate(l.getId(), "Senior Manager");

        //then
        assertThat(result).isTrue();
    }

    @Test
    void options() {
        //given
        Level l = underTest.findByName("Manager");

        //when
        List<Level> levels = underTest.options(l.getId());
        for(Level l2 : levels){
            System.out.println(l2.toString());
        }

        //then
        assertThat(levels.size()).isGreaterThan(0);
    }
}