package com.bankstech.hrms.format;

public class Word {

    public String getFirstLetterInAWord(String sentence){
        String result="";
        String[] myName = sentence.split(" ");
        for (int i = 0; i < myName.length; i++) {
            String s = myName[i];
            result += s.charAt(0);
        }
        return result.toUpperCase();
    }

    public String getCode(String word){
        return word.trim().toUpperCase().replace(" ", "_");
    }
}
