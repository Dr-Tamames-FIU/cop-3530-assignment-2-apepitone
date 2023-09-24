package org.assignment2;


import java.util.Stack;

public class FormulaCalc {

    private static int getAtomicNumber(String element) {
        switch (element) {
            case "H": return 1;
            case "C": return 6;
            case "O": return 8;
            case "N": return 7;
            case "K": return 19;
            case "Na": return 11;
            case "Cl": return 17;
            case "Br": return 35;
            default: return -1; // Invalid element
        }
    }

    public static int Algorithm(String a) {
        Stack<Integer> stack = new Stack<>();
        int protons = 0;

        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (Character.isUpperCase(c)) {
                StringBuilder element = new StringBuilder();
                element.append(c);
                while (i + 1 < a.length() && Character.isLowerCase(a.charAt(i + 1))) {
                    element.append(a.charAt(i + 1));
                    i++;
                }
                int atomicNumber = getAtomicNumber(element.toString());
                stack.push(atomicNumber);
            } else if (Character.isDigit(c)) {
                int count = c - '0';
                if (i + 1 < a.length() && Character.isDigit(a.charAt(i + 1))) {
                    count = count * 10 + (a.charAt(i + 1) - '0');
                    i++;
                }
                int top = stack.pop();
                stack.push(top * count);
            } else if (c == '(') {
                stack.push(-1); // Use -1 to mark the start of a subexpression
            } else if (c == ')') {
                int subTotal = 0;
                while (stack.peek() != -1) {
                    subTotal += stack.pop();
                }
                stack.pop(); // Pop the -1 marker
                stack.push(subTotal);
            }
        }

        while (!stack.isEmpty()) {
            protons += stack.pop();
        }

        return protons;
    }

    public static void main(String[] args) {
        String[] formulas = {
            "H",
            "KBr",
            "H2O",
            "NaCl",
            "C6H12O6"
        };

        for (String formula : formulas) {
            System.out.println(Algorithm(formula));
        }
    }
}