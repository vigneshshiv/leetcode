package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/goal-parser-interpretation/
 */
public class GoalParser {

    private static String interpret(String command) {
        return command.replace("()", "o").replace("(al)", "al");
    }

    public static void main(String[] args) {
        BiConsumer<String, String> parser_logger = (command, result) -> System.out.println("Command - " + command + ", Result - " + result);
        //
        String command = "G()(al)";
        String result = interpret(command);
        parser_logger.accept(command, result);
        //
        command = "G()()()()(al)";
        result = interpret(command);
        parser_logger.accept(command, result);
        //
        command = "(al)G(al)()()G";
        result = interpret(command);
        parser_logger.accept(command, result);
    }

}
