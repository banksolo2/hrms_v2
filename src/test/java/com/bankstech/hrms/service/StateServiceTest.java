package com.bankstech.hrms.service;

import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.model.State;
import com.bankstech.hrms.repository.StateRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
class StateServiceTest {

    @Autowired
    private StateService underTest;

    @Autowired
    private StateRepository stateRepository;




    @Test
    void create() {
        //given
        State state = new State();
        state.setName("Ogun");

        //when
        ResponseMessage rm = underTest.create(state);
        System.out.println("Type: "+ rm.getType());
        System.out.println("Message: "+ rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("success");

    }



    @Test
    void getAllStatesReport() {
        //when
        List<State> states = underTest.getAllStatesReport();
        for(State state : states){
            System.out.println("ID: "+ state.getId());
            System.out.println("Name: "+state.getName());
            System.out.println("Code: "+ state.getCode());
        }

        //then
        assertThat(states.size()).isGreaterThan(0);

    }

    @Test
    void update() {
        //given
        String name = "Lagos";
        State state = new State();
        state.setId(9);
        state.setName(name);

        //when
        ResponseMessage rm = underTest.update(state);

        //then
        assertThat(rm.getType()).isEqualTo("success");

    }

    @Test
    void delete() {
        //given
        State state = stateRepository.findByCode("ogun");

        //when
        ResponseMessage rm = underTest.delete(state.getId());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

}