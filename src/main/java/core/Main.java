package core;

/**
 * A few examples to check correctness of logic
 */
public class Main {
    public static void main(String[] args) {
        SpreadSheet spreadSheet = SpreadSheet.getInstance();
        spreadSheet.createSheet("Sheet1");

        spreadSheet.setCellValue("A1", 13);
        spreadSheet.setCellValue("A2", 14);

        System.out.println(spreadSheet.getCellValue("A1")); // Output: 13

        spreadSheet.setCellValue( "A3", "=A1+A2");

        System.out.println(spreadSheet.getCellValue("A3")); // Output: 27

        spreadSheet.setCellValue("A4", "=A1+A2*A3");

        System.out.println(spreadSheet.getCellValue("A4")); // Output: 54
    }
}
