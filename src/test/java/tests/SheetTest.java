package tests;

import core.Sheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SheetTest {
    private Sheet sheet;

    @BeforeEach
    public void setup() {
        sheet = new Sheet();
    }

    @Test
    public void testSetCellValue() {
        sheet.setCellValue("A1", 5);
        assertEquals(5, sheet.getCellValue("A1"));
    }

    @Test
    public void testGetCellValueNonExistingCell() {
        assertNull(sheet.getCellValue("B2"));
    }

    @Test
    public void testHasCellTrue() {
        sheet.setCellValue("C3", "Test");
        assertTrue(sheet.hasCell("C3"));
    }

    @Test
    public void testHasCellFalse() {
        assertFalse(sheet.hasCell("D4"));
    }

    @Test
    public void testGetCells() {
        sheet.setCellValue("A1", 1);
        sheet.setCellValue("B2", 2);
        sheet.setCellValue("C3", 3);

        assertEquals(3, sheet.getCells().size());
    }
}

