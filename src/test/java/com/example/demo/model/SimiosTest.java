package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SimiosTest {
    @Test
    public void testCanEqual() {
        assertFalse((new Simios()).canEqual("Other"));
    }

    @Test
    public void testCanEqual2() {
        Simios simios = new Simios();

        Simios simios1 = new Simios();
        simios1.setStatus(true);
        simios1.setDna("Dna");
        simios1.setId(123L);
        assertTrue(simios.canEqual(simios1));
    }

    @Test
    public void testConstructor() {
        Simios actualSimios = new Simios();
        actualSimios.setDna("Dna");
        actualSimios.setId(123L);
        actualSimios.setStatus(true);
        assertEquals("Dna", actualSimios.getDna());
        assertEquals(123L, actualSimios.getId().longValue());
        assertTrue(actualSimios.isStatus());
        assertEquals("Simios(id=123, dna=Dna, status=true)", actualSimios.toString());
    }

    @Test
    public void testConstructor2() {
        Simios actualSimios = new Simios(123L, "Dna", true);
        actualSimios.setDna("Dna");
        actualSimios.setId(123L);
        actualSimios.setStatus(true);
        assertEquals("Dna", actualSimios.getDna());
        assertEquals(123L, actualSimios.getId().longValue());
        assertTrue(actualSimios.isStatus());
        assertEquals("Simios(id=123, dna=Dna, status=true)", actualSimios.toString());
    }

    @Test
    public void testEquals() {
        assertFalse((new Simios()).equals("42"));
    }

    @Test
    public void testEquals2() {
        Simios simios = new Simios();

        Simios simios1 = new Simios();
        simios1.setStatus(true);
        simios1.setDna("Dna");
        simios1.setId(123L);
        assertFalse(simios.equals(simios1));
    }

    @Test
    public void testEquals3() {
        Simios simios = new Simios();
        assertTrue(simios.equals(new Simios()));
    }

    @Test
    public void testEquals4() {
        Simios simios = new Simios(123L, "Dna", true);

        Simios simios1 = new Simios();
        simios1.setStatus(true);
        simios1.setDna("Dna");
        simios1.setId(123L);
        assertTrue(simios.equals(simios1));
    }

    @Test
    public void testEquals5() {
        Simios simios = new Simios();

        Simios simios1 = new Simios();
        simios1.setStatus(false);
        simios1.setDna("Dna");
        simios1.setId(123L);
        assertFalse(simios.equals(simios1));
    }

    @Test
    public void testEquals6() {
        Simios simios = new Simios(0L, "Dna", true);

        Simios simios1 = new Simios();
        simios1.setStatus(true);
        simios1.setDna("Dna");
        simios1.setId(123L);
        assertFalse(simios.equals(simios1));
    }

    @Test
    public void testEquals7() {
        Simios simios = new Simios(123L, null, true);

        Simios simios1 = new Simios();
        simios1.setStatus(true);
        simios1.setDna("Dna");
        simios1.setId(123L);
        assertFalse(simios.equals(simios1));
    }

    @Test
    public void testEquals8() {
        Simios simios = new Simios(123L, "com.example.demo.model.Simios", true);

        Simios simios1 = new Simios();
        simios1.setStatus(true);
        simios1.setDna("Dna");
        simios1.setId(123L);
        assertFalse(simios.equals(simios1));
    }

    @Test
    public void testHashCode() {
        assertEquals(545616, (new Simios()).hashCode());
        assertEquals(556490, (new Simios(123L, "Dna", true)).hashCode());
    }
}

