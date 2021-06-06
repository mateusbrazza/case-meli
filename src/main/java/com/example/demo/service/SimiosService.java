package com.example.demo.service;

import com.example.demo.model.Simios;
import com.example.demo.repository.SimiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimiosService {

    @Autowired
    SimiosRepository simiosRepository;

    public HashMap getAll() {
        List<Simios> params = simiosRepository.findAll();
        Integer total = params.size();
        List<Simios> result = params.stream()
                .filter(name -> name.isStatus())
                .collect(Collectors.toList());
        Integer mutant = result.size();
        Integer human = total-mutant;
        HashMap jsonMessage= new HashMap();
        jsonMessage.put("count_mutant_dna", mutant);
        jsonMessage.put("count_human_dna",human);
        jsonMessage.put("ratio",mutant/human);
        return jsonMessage;
    }

   @Async
    public boolean check(String[] dna){
       Simios simios = new Simios();
       StringBuilder sb = new StringBuilder();
       for (String str : dna) {
           sb.append(str);
       }
       String dnaString = sb.toString();
       Simios mateus = simiosRepository.findByDna(dnaString);
            if ( validateSize(dna) && validateCharacters(dna) ) {
                if ((findLettersHorizontal(dna) || findLettersVertical(dna)) ||
                        (findLettersDiagonallyLeft(dna) || findLettersDiagonallyRight(dna))){
                    if(mateus == null) {
                        simios.setDna(dnaString);
                        simios.setStatus(true);
                        simiosRepository.save(simios);
                        return true;
                    }
                    return true;
                }
                if(mateus == null){
                    simios.setDna(dnaString);
                    simios.setStatus(false);
                    simiosRepository.save(simios);
                    return false;
                }
            }
        return false;
    }

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
