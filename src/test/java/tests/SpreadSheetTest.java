package tests;

import core.Sheet;
import core.SpreadSheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpreadSheetTest {

    private SpreadSheet spreadSheet;

    @BeforeEach
    public void setup() {
        spreadSheet = SpreadSheet.getInstance();
    }

    @Test
    public void testCreateSheet() {
        Sheet sheet = spreadSheet.createSheet("Sheet1");
        assertNotNull(sheet, "Sheet should be created");
    }

    @Test
    public void testSwitchToExistingSheet() {
        spreadSheet.createSheet("Sheet2");
        spreadSheet.switchToSheet("Sheet2");
        // If switch is successful, then setting a value should not be null
        spreadSheet.setCellValue("A1", 100);
        assertNotNull(spreadSheet.getCellValue("A1"));
    }

    @Test
    public void testSwitchToNonExistingSheet() {
        spreadSheet.switchToSheet("Sheet999");
        // If switch is not successful, then setting a value should be null
        spreadSheet.setCellValue("A1", 100);
        assertNull(spreadSheet.getCellValue("A1"));
    }

    @Test
    public void testSetCellValueWithoutActiveSheet() {
        // Without an active sheet, setting a value should result in null
        spreadSheet.setCellValue("A1", 100);
        assertNull(spreadSheet.getCellValue("A1"));
    }

    @Test
    public void testSetCellValueWithActiveSheet() {
        spreadSheet.createSheet("Sheet3");
        spreadSheet.switchToSheet("Sheet3");
        // With an active sheet, setting a value should not be null
        spreadSheet.setCellValue("A1", 100);
        assertNotNull(spreadSheet.getCellValue("A1"));
    }

    @Test
    public void testGetCellValueWithoutActiveSheet() {
        assertNull(spreadSheet.getCellValue("A1"), "Getting cell value without active sheet should return null");
    }

    @Test
    public void testGetCellValueWithActiveSheet() {
        spreadSheet.createSheet("Sheet4");
        spreadSheet.switchToSheet("Sheet4");
        spreadSheet.setCellValue("A1", 100);
        assertEquals(100, spreadSheet.getCellValue("A1"), "Getting cell value with active sheet should return correct value");
    }
}
