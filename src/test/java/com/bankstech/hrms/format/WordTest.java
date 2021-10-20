package com.bankstech.hrms.format;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    void getFirstLetterInAWord() {
        //given
        String sentence = "Custodian Life Insurance";

        //when
        Word w = new Word();
        String result = w.getFirstLetterInAWord(sentence);
        System.out.println(result);

        //then
        assertThat(result).isEqualTo("CLI");
    }
}