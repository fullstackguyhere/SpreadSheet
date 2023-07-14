package tests;

import core.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testId() {
        Cell cell = new Cell("A1", 10);
        assertEquals("A1", cell.getId());
    }

    @Test
    void testValue() {
        Cell cell = new Cell("A1", 10);
        assertEquals(10, cell.getValue());
    }

    @Test
    void testSetValue() {
        Cell cell = new Cell("A1", 10);
        cell.setValue(20);
        assertEquals(20, cell.getValue());
    }

    @Test
    void testIsFormula() {
        Cell cell = new Cell("A1", "=A2+A3");
        assertTrue(cell.isFormula());
    }

    @Test
    void testIsNotFormula() {
        Cell cell = new Cell("A1", 10);
        assertFalse(cell.isFormula());
    }

    @Test
    void testGetFormula() {
        Cell cell = new Cell("A1", "=A2+A3");
        assertEquals("A2+A3", cell.getFormula());
    }

    @Test
    void testGetFormulaWhenNotFormula() {
        Cell cell = new Cell("A1", 10);
        assertNull(cell.getFormula());
    }
}

