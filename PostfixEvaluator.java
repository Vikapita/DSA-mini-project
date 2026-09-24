
public class PostfixEvaluator {

    private static class DoubleStack {
        private double[] data;
        private int top; // index of the top element; -1 if empty

        DoubleStack(int capacity) {
            data = new double[capacity];
            top = -1;
        }

        void push(double value) {
            if (top == data.length - 1) {
                // grow the array if needed
                double[] newData = new double[data.length * 2];
                System.arraycopy(data, 0, newData, 0, data.length);
                data = newData;
            }
            data[++top] = value;
        }

        double pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack underflow - invalid postfix expression.");
            }
            return data[top--];
        }

        double peek() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty.");
            }
            return data[top];
        }

        boolean isEmpty() {
            return top == -1;
        }

        /** Returns a textual snapshot of the stack contents, bottom -> top. */
        String contents() {
            if (isEmpty()) return "[ ] (empty)";
            StringBuilder sb = new StringBuilder("[ ");
            for (int i = 0; i <= top; i++) {
                if (data[i] == Math.floor(data[i])) {
                    sb.append((long) data[i]);
                } else {
                    sb.append(data[i]);
                }
                sb.append(" ");
            }
            sb.append("]  (top -> ").append(top >= 0
                    ? (data[top] == Math.floor(data[top]) ? String.valueOf((long) data[top]) : String.valueOf(data[top]))
                    : "-").append(")");
            return sb.toString();
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("x") || token.equals("X") || token.equals("×")
                || token.equals("/") || token.equals("÷");
    }

    private static double applyOperator(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*":
            case "x":
            case "X":
            case "×": return a * b;
            case "/":
            case "÷":
                if (b == 0) throw new ArithmeticException("Division by zero in postfix expression.");
                return a / b;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    public static double evaluate(String expression, boolean verbose) {
        DoubleStack stack = new DoubleStack(10);
        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            if (isOperator(token)) {
                double b = stack.pop();
                double a = stack.pop();
                double result = applyOperator(a, b, token);
                stack.push(result);
                if (verbose) {
                    System.out.printf("Applied '%s' on %s and %s -> pushed %s%n",
                            token, trim(a), trim(b), trim(result));
                    System.out.println("Stack now: " + stack.contents());
                }
            } else {
                double value = Double.parseDouble(token);
                stack.push(value);
                if (verbose) {
                    System.out.println("Pushed " + trim(value));
                    System.out.println("Stack now: " + stack.contents());
                }
            }
        }

        double finalResult = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalStateException("Malformed postfix expression - extra operands left.");
        }
        return finalResult;
    }

    private static String trim(double d) {
        return (d == Math.floor(d)) ? String.valueOf((long) d) : String.valueOf(d);
    }



    }

