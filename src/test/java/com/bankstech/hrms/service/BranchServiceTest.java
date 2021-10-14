package com.bankstech.hrms.service;

import com.bankstech.hrms.model.Branch;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.BranchRepository;
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
class BranchServiceTest {

    @Autowired
    private BranchService underTest;

    @Autowired
    private BranchRepository branchRepository;

    private long millis=System.currentTimeMillis();

    @BeforeEach
    void setUp() {
        String name = "Lagos";
        Branch b1 = new Branch();
        b1.setName(name);
        b1.setCode(name.toLowerCase().replace(" ","_"));
        b1.setCreatedAt(new Date(millis));
        branchRepository.save(b1);

        name = "Ogun";
        Branch b2 = new Branch();
        b2.setName(name);
        b2.setCode(name.toLowerCase().replace(" ","_"));
        b2.setCreatedAt(new Date(millis));
        branchRepository.save(b2);

    }

    @AfterEach
    void tearDown() {
        branchRepository.deleteAll();
    }

    @Test
    void create() {
        //given
        String name = "Imo";
        Branch b = new Branch();
        b.setName(name);

        //when
        ResponseMessage rm = underTest.create(b);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isNameExist(){
        //given
        Branch b = new Branch();
        b.setName("Lagos");

        //when
        ResponseMessage rm = underTest.create(b);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void all() {
        //when
        List<Branch> branches = underTest.all();
        for(Branch b : branches){
            System.out.println(b.toString());
        }

        //then
        assertThat(branches.size()).isGreaterThan(0);
    }

    @Test
    void update() {
        //given
        Branch b = branchRepository.findByName("Lagos");
        b.setName("Oyo");

        //when
        ResponseMessage rm = underTest.update(b);

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isNameExistOnUpdate(){
        //given
        Branch b = branchRepository.findByName("Lagos");
        b.setName("Ogun");

        //when
        ResponseMessage rm = underTest.update(b);

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void delete() {
        //given
        Branch b = branchRepository.findByName("Lagos");

        //when
        ResponseMessage rm = underTest.delete(b.getId());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void options() {
        //given
        Branch b = branchRepository.findByName("Lagos");

        //when
        List<Branch> branches = underTest.options(b.getId());
        for(Branch branch : branches){
            System.out.println(branch.toString());
        }

        //then
        assertThat(branches.size()).isGreaterThan(0);
    }
}