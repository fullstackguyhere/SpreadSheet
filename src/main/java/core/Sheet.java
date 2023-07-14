package core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Spreadsheet is divided into multiple sheets. This a basic Sheet class
 */
public class Sheet {
    private Map<String, Cell> cells;

    public Sheet() {
        cells = new HashMap<>();
    }

    public void setCellValue(String cellId, Object value) {
        cells.put(cellId, new Cell(cellId, value));
    }

    public Object getCellValue(String cellId) {
        Cell cell = cells.get(cellId);
        if (cell != null) {
            return cell.getValue();
        }
        return null;
    }

    public boolean hasCell(String cellId) {
        return cells.containsKey(cellId);
    }

    public Collection<Cell> getCells() {
        return cells.values();
    }
}
