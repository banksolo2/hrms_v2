package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Branch;
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
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class BranchRepositoryTest {

    @Autowired
    private BranchRepository underTest;

    @BeforeEach
    void setUp() {
        String name = "Lagos";
        Branch b = new Branch();
        b.setName(name);
        b.setCode(name.toLowerCase().replace(" ","_"));
        long millis=System.currentTimeMillis();
        b.setCreatedAt(new Date(millis));
        underTest.save(b);

        name = "Ogun";
        Branch b1 = new Branch();
        b1.setName(name);
        b1.setCode(name.toLowerCase().replace(" ","_"));
        b1.setCreatedAt(new Date(millis));
        underTest.save(b1);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByName() {
        //given
        String name = "Lagos";

        //when
        Branch b = underTest.findByName(name);
        System.out.println(b.toString());

        //then
        assertThat(b).isNotNull();
    }

    @Test
    void findByCode() {
        //given
        String code = "lagos";

        //when
        Branch b = underTest.findByCode(code);
        System.out.println(b.toString());

        //then
        assertThat(b).isNotNull();
    }

    @Test
    void existByName() {
        //given
        String name = "lagos";

        //when
        boolean result = underTest.existByName(name);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void existByNameOnUpdate() {
        //given
        Branch b = underTest.findByName("Ogun");
        b.setName("Ikeja");

        //when
        boolean result = underTest.existByNameOnUpdate(b.getId(),b.getName());

        //then
        assertThat(result).isFalse();
    }

    @Test
    void options() {
        //given
        Branch b = underTest.findByName("Lagos");

        //when
        List<Branch> branches = underTest.options(b.getId());
        for(Branch branch : branches){
            System.out.println(branch.toString());
        }

        //then
        assertThat(branches.size()).isGreaterThan(0);
    }
}