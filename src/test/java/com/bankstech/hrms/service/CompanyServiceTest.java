package com.bankstech.hrms.service;

import com.bankstech.hrms.format.Word;
import com.bankstech.hrms.model.Company;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.CompanyRepository;
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
class CompanyServiceTest {
    @Autowired
    private CompanyService underTest;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        long millis = System.currentTimeMillis();
        Word word = new Word();

        String name = "Custodian Life Insurance";
        Company c1 = new Company();
        c1.setName(name);
        c1.setShortName(word.getFirstLetterInAWord(name));
        c1.setCode(word.getCode(name));
        c1.setCreatedAt(new Date(millis));
        companyRepository.save(c1);

        name = "Custodian Life Assurance";
        Company c2 = new Company();
        c2.setName(name);
        c2.setShortName(word.getFirstLetterInAWord(name));
        c2.setCode(word.getCode(name));
        c2.setCreatedAt(new Date(millis));
        companyRepository.save(c2);
    }

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    void create() {
        //given
        Company c = new Company();
        c.setName("Custodian Investment Plc");

        //when
        ResponseMessage rm = underTest.create(c);
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void isNameExistOnCreate(){
        //given
        Company c = new Company();
        c.setName("Custodian Life Insurance");

        //when
        ResponseMessage rm = underTest.create(c);
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void update() {
        //given
        Company c = companyRepository.findByName("Custodian Life Insurance");
        c.setName("Custodian Investment Plc");

        //when
        ResponseMessage rm = underTest.update(c);
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("success");

    }

    @Test
    void isNameExistOnUpdate(){
        //given
        Company c = companyRepository.findByName("Custodian Life Insurance");
        c.setName("Custodian Life Assurance");

        //when
        ResponseMessage rm = underTest.update(c);
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("error");
    }

    @Test
    void delete() {
        //given
        Company c = companyRepository.findByName("Custodian Life Insurance");

        //when
        ResponseMessage rm = underTest.delete(c.getId());
        System.out.println(rm.getMessage());

        //then
        assertThat(rm.getType()).isEqualTo("success");
    }

    @Test
    void all() {
        //when
        List<Company> companies = underTest.all();
        for(Company c : companies){
            System.out.println(c.toString());
        }

        //then
        assertThat(companies.size()).isGreaterThan(0);
    }

    @Test
    void getById() {
        //given
        Company c1 = companyRepository.findByName("Custodian Life Insurance");

        //when
        Company c2 = underTest.getById(c1.getId());
        System.out.println(c2.toString());

        //then
        assertThat(c2).isNotNull();
    }

    @Test
    void options() {
        //given
        Company c1 = companyRepository.findByName("Custodian Life Insurance");

        //when
        List<Company> companies = underTest.options(c1.getId());
        for(Company c2 : companies){
            System.out.println(c2.toString());
        }

        //then
        assertThat(companies.size()).isGreaterThan(0);
    }
}