package com.bankstech.hrms.repository;

import com.bankstech.hrms.format.Word;
import com.bankstech.hrms.model.Company;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository underTest;

    private Word w = new Word();
    private long millis = System.currentTimeMillis();

    @BeforeEach
    void setUp() {
        String name = "Custodian Life Insurance";
        Company c1 = new Company();
        c1.setName(name);
        c1.setShortName(w.getFirstLetterInAWord(name));
        c1.setCode(w.getCode(name));
        c1.setCreatedAt(new Date(millis));
        underTest.save(c1);

        name = "Custodian Life Assurance";
        Company c2 = new Company();
        c2.setName(name);
        c2.setShortName(w.getFirstLetterInAWord(name));
        c2.setCode(w.getCode(name));
        c2.setCreatedAt(new Date(millis));
        underTest.save(c2);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByName() {
        //given
        String name = "Custodian Life Assurance";

        //when
        Company c = underTest.findByName(name);
        System.out.println(c.toString());

        //then
        assertThat(c).isNotNull();
    }

    @Test
    void findByCode() {
        //given
        String code = w.getCode("Custodian Life Assurance");

        //when
        Company c = underTest.findByCode(code);
        System.out.println(c.toString());

        //then
        assertThat(c).isNotNull();
    }

    @Test
    void existByName() {
        //given
        String name = "Custodian Life Assurance";

        //when
        boolean result = underTest.existByName(name);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void existByNameOnUpdate() {
        //given
        Company c = underTest.findByName("Custodian Life Assurance");

        //when
        boolean result = underTest.existByNameOnUpdate(c.getId(), "Custodian Life Insurance");

        //then
        assertThat(result).isTrue();

    }

    @Test
    void getCompanyById() {
        //given
        Company c = underTest.findByName("Custodian Life Assurance");
        Integer id = c.getId();

        //when
        Company c2 = underTest.getCompanyById(id);
        System.out.println(c2.toString());

        //then
        assertThat(c2).isNotNull();

    }

    @Test
    void options() {
        //given
        Company c = underTest.findByName("Custodian Life Assurance");

        //when
        List<Company> companies = underTest.options(c.getId());
        for(Company c2 : companies){
            System.out.println(c2.toString());
        }

        //then
        assertThat(companies.size()).isGreaterThan(0);
    }
}