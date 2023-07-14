package core;

import java.util.HashMap;
import java.util.Map;

/**
 * Top level SpreadSheet class that will contain many sub Sheets having numerous Cells to store value.
 * Has a currentSheet property to denote current active sheet
 */
public class SpreadSheet {
    private static SpreadSheet instance;
    private Map<String, Sheet> sheets;
    private Sheet currentSheet;

    private SpreadSheet() {
        sheets = new HashMap<>();
    }

    public static SpreadSheet getInstance() {
        if (instance == null) {
            instance = new SpreadSheet();
        }
        return instance;
    }

    public Sheet createSheet(String sheetName) {
        if (!sheets.containsKey(sheetName)) {
            Sheet sheet = new Sheet();
            sheets.put(sheetName, sheet);
            currentSheet = sheet;
            return sheet;
        } else {
            System.out.println("Sheet with the same name already exists.");
            return null;
        }
    }

    public void switchToSheet(String sheetName) {
        if (sheets.containsKey(sheetName)) {
            currentSheet = sheets.get(sheetName);
        } else {
            System.out.println("Sheet does not exist.");
        }
    }

    public void setCellValue(String cellId, Object value) {
        if (currentSheet != null) {
            currentSheet.setCellValue(cellId, value);
            evaluateFormulas(currentSheet);
        } else {
            System.out.println("No active sheet.");
        }
    }

    public Object getCellValue(String cellId) {
        if (currentSheet != null) {
            return currentSheet.getCellValue(cellId);
        } else {
            System.out.println("No active sheet.");
            return null;
        }
    }

    /**
     * Evaluates any cells that have formula and computes the value and stores it.
     * @param sheet Active Sheet
     */
    private void evaluateFormulas(Sheet sheet) {
        for (Cell cell : sheet.getCells()) {
            if (cell.isFormula()) {
                Object value = FormulaEvaluator.evaluateFormula(sheet, cell.getFormula());
                cell.setValue(value);
            }
        }
    }
}
