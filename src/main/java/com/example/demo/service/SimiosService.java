package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SimiosService {

    public boolean isSimian(String dna) {
        int iCharCnt = 0;
        for(int i = 0; i < dna.length(); ++i) {
            char chCompare = dna.charAt(i);
            if (iCharCnt == 4) {
                return true;
            }
            if (i != dna.length() - 1) {
                if (chCompare == dna.charAt(i + 1)) {
                    iCharCnt += iCharCnt == 0 ? 2 : 1;
                } else {
                    iCharCnt = 0;
                }
            }
        }

        return false;
    }

    public boolean validateSize(String[] dna) {
        if (dna.length != 6) {
            return false;
        }
        for (String value : dna) {
            if (value.length() != 6){
                return false;
            }
        }
        return true;
    }

    public boolean validateCharacters(String[] dna) {
        for (String value : dna) {
            for (int i = 0; i < value.length(); i ++) {
                char ch = value.charAt(i);
                if (ch != 'A' && ch != 'T' && ch != 'C' && ch != 'G'){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean findLettersHorizontal(String[] dna) {
        for (String value : dna) {
            String  characters = "";
            for (int i = 0; i < value.length(); i ++) {
                characters += value.charAt(i);
            }
            if (isSimian(characters)){
                return true;
            }
        }
        return false;
    }


    public boolean findLettersVertical(String[] dna) {
        for (int i = 0; i < 6; i ++) {
            String characters = "";
            for (String value : dna) characters += value.charAt(i);
            if (isSimian(characters)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean findLettersDiagonallyLeft(String[] dna) {
        String  characters = "";
        int     i = 0;
        for (String value : dna) {
            characters += value.charAt(i);
            i ++;
        }
        for (i = 0; i < 6; i ++) {
            if (isSimian(characters)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean findLettersDiagonallyRight(String[] dna) {
        String  characters = "";
        int     i = 5;
        for (String value : dna) {
            characters += value.charAt(i);
            i --;
        }
        if (isSimian(characters)){
            return true;
        }
        return false;
    }
}
