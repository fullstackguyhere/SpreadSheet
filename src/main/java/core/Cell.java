package core;

/**
 * Cell is the basic unit of a spreadsheet.
 */
public class Cell {
    private String id;
    private Object value;

    public Cell(String id, Object value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isFormula() {
        return value instanceof String && ((String) value).startsWith("=");
    }

    public String getFormula() {
        if (isFormula()) {
            return ((String) value).substring(1); // Remove the "=" sign
        }
        return null;
    }
}
