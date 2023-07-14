# SpreadSheet

This is the code for take-home test of Orkes.  


## SpreadSheet

The `SpreadSheet` is the top-level class representing the spreadsheet application. It manages multiple `Sheet` instances and keeps track of the current active sheet.

## Sheet

A `Sheet` represents a single sheet in the spreadsheet application. It contains multiple `Cell` instances.

## Cell

A `Cell` represents a single cell in a sheet. It can contain either a direct value or a formula.

## FormulaEvaluator

The `FormulaEvaluator` is a utility class used to evaluate the formula present in a `Cell`.
