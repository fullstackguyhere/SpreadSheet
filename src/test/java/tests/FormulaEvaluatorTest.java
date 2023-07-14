package tests;

import core.FormulaEvaluator;
import core.Sheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class FormulaEvaluatorTest {
    private Sheet sheet;

    @BeforeEach
    public void setup() {
        sheet = Mockito.mock(Sheet.class);
    }

    @Test
    public void testEvaluateFormulaSubtraction() {
        when(sheet.hasCell("A1")).thenReturn(true);
        when(sheet.getCellValue("A1")).thenReturn(10);
        when(sheet.hasCell("A2")).thenReturn(true);
        when(sheet.getCellValue("A2")).thenReturn(4);

        Object result = FormulaEvaluator.evaluateFormula(sheet, "A1-A2");
        assertEquals(6, result);
    }

    @Test
    public void testEvaluateFormulaDivision() {
        when(sheet.hasCell("B1")).thenReturn(true);
        when(sheet.getCellValue("B1")).thenReturn(20);
        when(sheet.hasCell("B2")).thenReturn(true);
        when(sheet.getCellValue("B2")).thenReturn(5);

        Object result = FormulaEvaluator.evaluateFormula(sheet, "B1/B2");
        assertEquals(4, result);
    }

    @Test
    public void testEvaluateFormulaMultipleOperations() {
        when(sheet.hasCell("A1")).thenReturn(true);
        when(sheet.getCellValue("A1")).thenReturn(5);
        when(sheet.hasCell("A2")).thenReturn(true);
        when(sheet.getCellValue("A2")).thenReturn(3);
        when(sheet.hasCell("A3")).thenReturn(true);
        when(sheet.getCellValue("A3")).thenReturn(2);

        Object result = FormulaEvaluator.evaluateFormula(sheet, "A1+A2*A3");
        assertEquals(11, result);
    }

    @Test
    public void testEvaluateFormulaDivisionByZero() {
        when(sheet.hasCell("C1")).thenReturn(true);
        when(sheet.getCellValue("C1")).thenReturn(10);
        when(sheet.hasCell("C2")).thenReturn(true);
        when(sheet.getCellValue("C2")).thenReturn(0);

        Object result = FormulaEvaluator.evaluateFormula(sheet, "C1/C2");
        assertEquals(0, result); // As defined in FormulaEvaluator.applyOperation
    }

    @Test
    public void testEvaluateFormulaInvalidCellId() {
        when(sheet.hasCell("D1")).thenReturn(false);

        Object result = FormulaEvaluator.evaluateFormula(sheet, "D1+5");
        assertNull(result);
    }

    @Test
    public void testInvalidOperator() {
        Sheet sheet = new Sheet();
        sheet.setCellValue("A1", 2);
        sheet.setCellValue("A2", 3);

        // Test with an invalid operator
        assertNull(FormulaEvaluator.evaluateFormula(sheet, "A1#A2"), "Invalid operator should result in null value");
    }
}
