import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String regex = "\\s*((\"[^\"]*\")\\s*([^\"\\s+*]*)\\s*((\\*?)\\s*([^+\\s\\d\"]*)\\s*(\\d*))\\s*([^+\"\\s]*)\\s*(\\+?)\\s*([^\"\\s]*)\\s*)\\s*|(\\s*(\\S*)\\s*)";
        Pattern pattern = Pattern.compile(regex);
        String line = input.nextLine();
        Matcher patternMatcher = pattern.matcher(line);
        StringBuilder result = new StringBuilder();
        while (patternMatcher.find()) {
            if (!lineChecker(line)) {
                System.out.println("Invalid Command.");
                line = input.nextLine();
                patternMatcher = pattern.matcher(line);
                continue;
            }
            StringBuilder finalExpression = new StringBuilder();
            String quotedExpression = patternMatcher.group(2);
            String extraExpression = patternMatcher.group(3);
            String multiplier = patternMatcher.group(5);
            String extraMultiplier = patternMatcher.group(6);
            String operand = patternMatcher.group(7);
            String extraOperand = patternMatcher.group(8);
            String adder = patternMatcher.group(9);
            String extraAdder = patternMatcher.group(10);
            String exit = patternMatcher.group(12);
            String expression;
            if ("exit".equals(exit))
                break;
            else if (exit != null) {
                System.out.println("Invalid Command.");
                result.setLength(0);
                line = input.nextLine();
                patternMatcher = pattern.matcher(line);
                continue;
            }
            if (!extraExpression.isEmpty()) {
                System.out.println("Invalid Command.");
                result.setLength(0);
                line = input.nextLine();
                patternMatcher = pattern.matcher(line);
                continue;
            }
            if (!extraMultiplier.isEmpty()) {
                System.out.println("Invalid Command.");
                result.setLength(0);
                line = input.nextLine();
                patternMatcher = pattern.matcher(line);
                continue;
            }
            if (!extraOperand.isEmpty()) {
                System.out.println("Invalid Command.");
                result.setLength(0);
                line = input.nextLine();
                patternMatcher = pattern.matcher(line);
                continue;
            }
            if (!extraAdder.isEmpty()) {
                System.out.println("Invalid Command.");
                result.setLength(0);
                line = input.nextLine();
                patternMatcher = pattern.matcher(line);
                continue;
            }
            if (!"".equals(quotedExpression))
                expression = quotedExpression.substring(1, quotedExpression.length() - 1);
            else
                expression = "";
            if ("*".equals(multiplier)) {
                if (!"".equals(operand)) {
                    int times = Integer.parseInt(operand);
                    finalExpression.append(expression.repeat(times));
                } else {
                    System.out.println("Invalid Command.");
                    result.setLength(0);
                    line = input.nextLine();
                    patternMatcher = pattern.matcher(line);
                    continue;
                }
            } else if ("".equals(multiplier)) {
                finalExpression.append(expression);
            }
            result.append(finalExpression);
            if (!"+".equals(adder)) {
                System.out.println(result);
                result.setLength(0);
                line = input.nextLine();
                patternMatcher = pattern.matcher(line);
            }
        }
    }

    public static boolean lineChecker(String line) {
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                counter++;
                if (counter != 1 &&
                        counter % 2 == 1) {
                    for (int j = i - 1; j > -1; j--) {
                        if (line.charAt(j) == '"') {
                            if (!line.substring(j, i).contains("+"))
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}