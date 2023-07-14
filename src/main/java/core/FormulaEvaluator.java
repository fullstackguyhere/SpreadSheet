package core;

import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class having the logic to evaluate forumalic expressions in spreadsheet
 */
public class FormulaEvaluator {
    private static final Map<String, Integer> OPERATOR_PRECEDENCE = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
    );

    /**
     * Evaluates formula and returns computed value
     * @param sheet Active Sheet
     * @param formula Formula to compute
     * @return computed value
     */
    public static Object evaluateFormula(Sheet sheet, String formula) {
        Stack<String> operatorStack = new Stack<>();
        Stack<Integer> operandStack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(formula, "+-*/ ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if(!processToken(sheet, token, operandStack, operatorStack, formula)) {
                return null;
            }
        }

        while (!operatorStack.empty()) {
            processAnOperator(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    /**
     * Process the token according to it's type
     *
     * @param sheet
     * @param token
     * @param operandStack
     * @param operatorStack
     * @param formula
     * @return true or false
     */
    private static boolean processToken(Sheet sheet, String token, Stack<Integer> operandStack,
                                        Stack<String> operatorStack, String formula) {
        if (sheet.hasCell(token)) {
            return processCellToken(sheet, token, operandStack, formula);
        } else if (token.matches("\\d+")) {
            operandStack.push(Integer.parseInt(token));
        } else if (token.matches("[+\\-*/]")) {
            processOperatorToken(token, operandStack, operatorStack);
        } else {
            System.out.println("Invalid token in formula: " + token);
            return false;
        }
        return true;
    }

    /**
     * Process actual values to compute and push it onto the stack
     * @param sheet
     * @param token
     * @param operandStack
     * @param formula
     * @return true or false
     */
    private static boolean processCellToken(Sheet sheet, String token, Stack<Integer> operandStack, String formula) {
        Object cellValue = sheet.getCellValue(token);
        if (cellValue instanceof Integer) {
            operandStack.push((Integer) cellValue);
            return true;
        } else {
            System.out.println("Invalid cell value in formula: " + formula);
            return false;
        }
    }

    /**
     * If operator encountered, compute the value and push it back onto the stack
     * @param token
     * @param operandStack
     * @param operatorStack
     */
    private static void processOperatorToken(String token, Stack<Integer> operandStack, Stack<String> operatorStack) {
        while (!operatorStack.empty() && hasPrecedence(token, operatorStack.peek())) {
            processAnOperator(operandStack, operatorStack);
        }
        operatorStack.push(token);
    }

    /**
     * Very basic precedence checker
     * @param op1
     * @param op2
     * @return true or false
     */
    private static boolean hasPrecedence(String op1, String op2) {
        return OPERATOR_PRECEDENCE.getOrDefault(op1, -1) <= OPERATOR_PRECEDENCE.getOrDefault(op2, -1);
    }

    /**
     * Actual computation logic
     * @param operandStack
     * @param operatorStack
     */
    private static void processAnOperator(Stack<Integer> operandStack, Stack<String> operatorStack) {
        String op = operatorStack.pop();
        int op1 = operandStack.pop();
        int op2 = operandStack.pop();
        switch (op) {
            case "+":
                operandStack.push(op2 + op1);
                break;
            case "-":
                operandStack.push(op2 - op1);
                break;
            case "*":
                operandStack.push(op2 * op1);
                break;
            case "/":
                if (op1 == 0) {
                    System.out.println("Division by zero error.");
                    operandStack.push(0);
                } else {
                    operandStack.push(op2 / op1);
                }
                break;
        }
    }
}
