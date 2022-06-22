import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String[] arabicNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public static String[] romeNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

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
        String invalidSys = "throws Exception //т.к. введено неподходящее число";
        String negRomeVal = "throws Exception //т.к. римские числа могут быть только положительными";

        // Разделяем строку с математическим выражением
        String[] expression = input.split(" +");

        String[] numbers;
        String operator;

        // Проверка на валидность математической операции
        if (expression.length < 3 | expression.length % 2 == 0) {
            return noMathExpr;
        // Проверка на формат математической операции
        } else if (expression.length > 3) {
            return invalidExpr;
        // В случае успешных проверок выделяем числа и оператор в отдельные переменные
        } else {
            numbers = new String[] {expression[0], expression[2]};
            operator = expression[1];
        }

        // 0 - Арабская система счисления, 1 - Римская система
        int sysType = -1;
        int count = 0;
        // Проверка на то, что числа входят в установленный диапазон
        // Дальше проверка на то, что оба числа из одной системы
        for (String num: numbers) {
            if (isArabic(num)) {
                if (count == 0) {
                    sysType = 0;
                    count++;
                    continue;
                }
                if (sysType != 0) {
                    return diffSystems;
                }
            } else if (isRome(num)) {
                if (count == 0) {
                    sysType = 1;
                    count++;
                    continue;
                }
                if (sysType != 1) {
                    return diffSystems;
                }
            } else {
                return invalidSys;
            }
        }

        int num1 = 0;
        int num2 = 0;

        // Конвертируем числа
        if (sysType == 0) {
            num1 = strToInt(numbers[0]);
            num2 = strToInt(numbers[1]);
        } else {
            num1 = strToInt(RomeNumber.valueOf(numbers[0]).getArabicNumber());
            num2 = strToInt(RomeNumber.valueOf(numbers[1]).getArabicNumber());
        }

        // Вычисляем результат выражения
        switch (operator) {
            case "+":
                return getOutput(sysType, num1 + num2);
            case "-":
                String out = getOutput(sysType, num1 - num2);
                if (out == "null") {
                    return negRomeVal;
                }
                return out;
            case "*":
                return getOutput(sysType, num1 * num2);
            case "/":
                return getOutput(sysType, num1 / num2);
            default:
                return noOperation;
        }
    }

    // Римская ли система счисления?
    public static boolean isRome(String num) {
        return Arrays.asList(romeNumbers).contains(num);
    }

    // Арабская ли система счисления?
    public static boolean isArabic(String num) {
        return Arrays.asList(arabicNumbers).contains(num);
    }

    // Конвертируем String -> Int
    public static int strToInt(String number) {
        return Integer.parseInt(number);
    }

    // Конвертируем Int -> String
    public static String intToStr(int number) {
        return String.valueOf(number);
    }

    // Метод для вывода результата
    public static String getOutput(int sysType, int out) {
        if (sysType == 0) {
            return intToStr(out);
        } else {
            return String.valueOf(RomeNumber.getRomeNumber(intToStr(out)));
        }
    }
}
