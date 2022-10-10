package code.java.stacks;

import java.util.Stack;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {

    private static int calculateOptimal(String s) {
        Stack<Integer> stack = new Stack<>();
        String operators = "+-*/", priority_operators = "*/";
        int n = s.length(), num = 0, result = 0;
        // Initialize +, because always add first digit to result
        char sign = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // If c is a digit and convert to int
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            // Last char is a sign, do the math now
            if (operators.indexOf(c) != -1 || i == n - 1) {
                // For any */ operators, substract the last added num from the result
                if (priority_operators.indexOf(sign) != -1) {
                    result -= stack.peek();
                }
                switch (sign) {
                    case '+' -> stack.push(num);
                    case '-' -> stack.push(-num);
                    case '*' -> stack.push(stack.pop() * num);
                    case '/' -> stack.push(stack.pop() / num);
                    default -> throw new IllegalArgumentException();
                }
                num = 0; sign = c;
                result += stack.peek();
            }
        }
        return result;
    }

    private static int calculate(String s) {
        s = s.replace(" ", "");
        String[] nums = s.split("[+\\-*/]");
        s = '+' + s;
        String[] ops = s.split("\\d+");
        int n = nums.length, last = 1, result = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(nums[i]);
            char op = ops[i].charAt(0);
            if (op == '+' || op == '-') {
                num = op == '+' ? num : -num;
                result += num;
            } else {
                num = op == '*' ? last * num : last / num;
                result += -last + num;
            }
            last = num;
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (input, result) -> {
            System.out.println("Input - " + input + ", Result - " + result);
        };
        //
        String s = "3+2*2";
        int result = calculate(s);
        logger.accept(s, result);
        result = calculateOptimal(s);
        logger.accept(s, result);
        //
        s = "3/2";
        result = calculate(s);
        logger.accept(s, result);
        result = calculateOptimal(s);
        logger.accept(s, result);
        //
        s = "3+5 / 2";
        result = calculate(s);
        logger.accept(s, result);
        result = calculateOptimal(s);
        logger.accept(s, result);
    }

}
