# SpreadSheet

This is the code for take-home test of Orkes.  


- ## SpreadSheet

    The `SpreadSheet` is the top-level class representing the spreadsheet application. It manages multiple `Sheet` instances and keeps track of the current active sheet.

- ## Sheet

    A `Sheet` represents a single sheet in the spreadsheet application. It contains multiple `Cell` instances.

- ## Cell

    A `Cell` represents a single cell in a sheet. It can contain either a direct value or a formula.

- ## FormulaEvaluator

    The `FormulaEvaluator` is a utility class used to evaluate the formula present in a `Cell`.



---------------------------------------------------------------------------------------------------

### Handling Edge Cases

In this spreadsheet implementation, we have considered various edge cases. 

Here is how the system handles such situations:

- One such situation is when a user attempts to use a formula with a cell that contains a non-numeric value (e.g., a string) is used in a formula, an error message is logged, and the formula evaluation is halted immediately.
- For division, we are rounding off rather than returning a decimal value due to time constraints.
