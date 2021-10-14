package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase( replace = Replace.NONE)
class StateRepositoryTest {
    @Autowired
    private StateRepository underTest;

    @BeforeEach
    void setUp() {
        String name = "Lagos";
        State state = new State();
        state.setName(name);
        state.setCode(name.toLowerCase().replace(" ", "_"));
        underTest.save(state);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    public void testFindStateByName(){
        //Given
        String name = "Lagos";

        //When
        State result = underTest.findByName(name);

        //then
        assertThat(result).isNotNull();

    }

    @Test
    public void testFindByCode(){
        //given
        String code = "lagos";

        //when
        State result = underTest.findByCode(code);
        System.out.println("State Name: "+result.getName());

        //then
        assertThat(result).isNotNull();
    }

    @Test
    public void testIsExistByName(){
        //given
        String name = "Lagos";

        //when
        boolean result = underTest.isExistByName(name);

        //then
        assertThat(result).isTrue();
    }

    @Test
    public void testIsExistByNameOnUpdate(){
        //given
        State state = underTest.findByName("Lagos");

        //when
        boolean result = underTest.isExistByNameOnUpdate(state.getId(), state.getName());

        //then
        assertThat(result).isFalse();
    }
}