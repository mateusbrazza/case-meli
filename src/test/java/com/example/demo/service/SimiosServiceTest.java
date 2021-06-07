package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.controller.SimiosController;
import com.example.demo.repository.SimiosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SimiosService.class})
@ExtendWith(SpringExtension.class)
public class SimiosServiceTest {


    @MockBean
    private SimiosRepository simiosRepository;

    @InjectMocks
    SimiosController simiosController;

    @Autowired
    private SimiosService simiosService;

    @Test
    public void testIsSimion() {
        assertTrue(this.simiosService.isSimian(new String[]{"CTGAAA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"}));
        assertFalse(this.simiosService.isSimian(new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"}));
    }

    @Test
    public void testCheckLetters() {
        assertFalse(this.simiosService.checkLetters("Dna"));
    }

    @Test
    public void testeValidateSize() {
        assertTrue(this.simiosService.validateSize(new String[]{"CTGJAA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"}));
        assertFalse(this.simiosService.validateSize(new String[]{"CTGJAA", "CTGAGC", "TATTGTT", "AGAGGG", "CCCCTA", "TCACTG"}));
        assertFalse(this.simiosService.validateSize(new String[]{"CTGJAA", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"}));
    }

    @Test
    public void testeValidateCharacters() {
        assertTrue(this.simiosService.validateCharacters(new String[]{"CTGAAA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"}));
        assertFalse(this.simiosService.validateCharacters(new String[]{"CTGJDA", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"}));
    }

    @Test
    public void testeFindLettersHorizontal() {
        assertTrue(this.simiosService.findLettersHorizontal(new String[]{"CTGGAA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"}));
        assertFalse(this.simiosService.findLettersHorizontal(new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"}));
    }
    @Test
    public void testeFindLettersVertical() {
        assertTrue(this.simiosService.findLettersVertical(new String[]{"CTGGGA", "CTGAGC", "TATTGT", "AGAGGG", "CCGCTA", "TCACTG"}));
        assertFalse(this.simiosService.findLettersVertical(new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"}));
    }
    @Test
    public void testeFindLettersDiagonallyLeft() {
        assertTrue(this.simiosService.findLettersDiagonallyLeft(new String[]{"CTGAAA", "CTGAGC", "TATTGT", "AGATGG", "CCGCTA", "TCACTG"}));
        assertFalse(this.simiosService.findLettersDiagonallyLeft(new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"}));
    }
    @Test
    public void testeFindLettersDiagonallyRight() {
        assertTrue(this.simiosService.findLettersDiagonallyRight(new String[]{"CAAAAA", "CAAAAA", "CAAAAA", "CAAAAA", "CCGCTA", "TCACTG"}));
        assertFalse(this.simiosService.findLettersDiagonallyRight(new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"}));
    }


}



