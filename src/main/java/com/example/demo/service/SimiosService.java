package com.example.demo.service;

import com.example.demo.model.Simios;
import com.example.demo.repository.SimiosRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimiosService {

    final
    SimiosRepository simiosRepository;

    public SimiosService(SimiosRepository simiosRepository) {
        this.simiosRepository = simiosRepository;
    }

    public HashMap<String, Double> getStatusDNA() {
        List<Simios> params = simiosRepository.findAll();
        int total = params.size();
        List<Simios> result = params.stream()
                .filter(Simios::isStatus)
                .collect(Collectors.toList());
        double mutant = result.size();
        double human = total-mutant;
        if(mutant >= 1 && human >=1){
            HashMap<String, Double> jsonMessage= new HashMap<>();
            jsonMessage.put("count_mutant_dna", mutant);
            jsonMessage.put("count_human_dna",human);
            jsonMessage.put("ratio",mutant/human);
            return jsonMessage;
        }
        return null;

    }

   @Async
    public boolean isSimian(String[] dna){
       Simios simios = new Simios();
       StringBuilder sb = new StringBuilder();
       for (String str : dna) {
           sb.append(str);
       }
       String dnaString = sb.toString();
       Simios simian = simiosRepository.findByDna(dnaString);
            if ( validateSize(dna) && validateCharacters(dna) ) {
                if ((findLettersHorizontal(dna) || findLettersVertical(dna)) ||
                        (findLettersDiagonallyLeft(dna) || findLettersDiagonallyRight(dna))){
                    if(simian == null) {
                        simios.setDna(dnaString);
                        simios.setStatus(true);
                        simiosRepository.save(simios);
                        return true;
                    }
                    return true;
                }
                if(simian == null){
                    simios.setDna(dnaString);
                    simios.setStatus(false);
                    simiosRepository.save(simios);
                    return false;
                }
            }
        return false;
    }

    public boolean checkLetters(String dna) {
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
            StringBuilder characters = new StringBuilder();
            for (int i = 0; i < value.length(); i ++) {
                characters.append(value.charAt(i));
            }
            if (checkLetters(characters.toString())){
                return true;
            }
        }
        return false;
    }


    public boolean findLettersVertical(String[] dna) {
        for (int i = 0; i < 6; i ++) {
            StringBuilder characters = new StringBuilder();
            for (String value : dna) characters.append(value.charAt(i));
            if (checkLetters(characters.toString())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean findLettersDiagonallyLeft(String[] dna) {
        StringBuilder characters = new StringBuilder();
        int     i = 0;
        for (String value : dna) {
            characters.append(value.charAt(i));
            i ++;
        }
        for (i = 0; i < 6; i ++) {
            if (checkLetters(characters.toString())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean findLettersDiagonallyRight(String[] dna) {
        StringBuilder characters = new StringBuilder();
        int     i = 5;
        for (String value : dna) {
            characters.append(value.charAt(i));
            i --;
        }
        return checkLetters(characters.toString());
    }
}
