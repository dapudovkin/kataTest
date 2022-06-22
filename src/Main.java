import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String[] arabicNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public static void main(String[] args) {
        // Создаём сканер и вводим данные
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Передаём данные в метод calc
        System.out.println(calc(input));
    }

    public static String calc(String input) {
        String noMathExpr = "throws Exception //т.к. строка не является математической операцией";
        String invalidExpr = "throws Exception //т.к. формат математической операции не " +
                "удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        String diffSystems = "throws Exception //т.к. используются одновременно разные системы счисления";
        String noOperation = "throws Exception //т.к. калькулятор не поддерживает такую операцию";

        // Разделяем строку с математическим выражением
        String[] expression = input.split(" +");

        String[] numbers;
        String operator;

        // Обрабатываем ограничения
        if (expression.length < 3 | expression.length % 2 == 0) {
            return noMathExpr;
        } else if (expression.length > 3) {
            return invalidExpr;
        } else {
            numbers = new String[] {expression[0], expression[2]};
            operator = expression[1];
        }

        // Обрабатываем ограничения
        if (isDiffSystem(numbers)) {
            return diffSystems;
        }

        int num1 = 0;
        int num2 = 0;

        if (isArabic(numbers[0])) {
            num1 = strToInt(numbers[0]);
            num2 = strToInt(numbers[1]);
        }

        switch (operator) {
            case "+":
                return intToStr(num1 + num2);
            case "-":
                return intToStr(num1 - num2);
            case "*":
                return intToStr(num1 * num2);
            case "/":
                return intToStr(num1 / num2);
            default:
                return noOperation;
        }
    }

    // Числа из разных систем счисления?
    public static boolean isDiffSystem(String[] numbers) {
        return (isArabic(numbers[0]) & !(isArabic(numbers[1]))) |
                (isArabic(numbers[1]) & !(isArabic(numbers[0])));
    }

    // Арабская ли система счисления?
    public static boolean isArabic(String a) {
        return Arrays.asList(arabicNumbers).contains(a);
    }

    // Конвертируем String -> Int
    public static int strToInt(String number) {
        return Integer.parseInt(number);
    }

    // Конвертируем Int -> String
    public static String intToStr(int number) {
        return String.valueOf(number);
    }
}
