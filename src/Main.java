import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String[] arabNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[] romeNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static int[] arab = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] rome = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    static final String noMathExpr = "throws Exception //т.к. строка не является математической операцией";
    static final String invalidExpr = "throws Exception //т.к. формат математической операции не " +
            "удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
    static final String diffSystems = "throws Exception //т.к. используются одновременно разные системы счисления";
    static final String noOperation = "throws Exception //т.к. калькулятор не поддерживает такую операцию";
    static final String invalidSys = "throws Exception //т.к. введено неподходящее число";
    static final String negRomeVal = "throws Exception //т.к. римские числа могут быть только положительными";

    public static void main(String[] args) {
        // Создаём сканер и вводим данные
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Передаём данные в метод calc
        System.out.println(calc(input));
    }

    public static String calc(String input) {
        // Разделяем строку с математическим выражением
        String[] expression = input.split(" +");

        String[] numbers;
        String operator;

        // Проверка на валидность математической операции
        if (expression.length < 3 | expression.length % 2 == 0) {
            return exception(noMathExpr);
        // Проверка на формат математической операции
        } else if (expression.length > 3) {
            return exception(invalidExpr);
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
            if (isNumValid(arabNumbers, num)) {
                if (count == 0) {
                    sysType = 0;
                    count++;
                    continue;
                }
                if (sysType != 0) {
                    return exception(diffSystems);
                }
            } else if (isNumValid(romeNumbers, num)) {
                if (count == 0) {
                    sysType = 1;
                    count++;
                    continue;
                }
                if (sysType != 1) {
                    return exception(diffSystems);
                }
            } else {
                return exception(invalidSys);
            }
        }

        int num1;
        int num2;

        // Конвертируем числа
        if (sysType == 0) {
            num1 = strToInt(numbers[0]);
            num2 = strToInt(numbers[1]);
        } else {
            num1 = romeToArab(numbers[0]);
            num2 = romeToArab(numbers[1]);
        }

        // Вычисляем результат выражения
        return switch (operator) {
            case "+" -> getOutput(sysType, num1 + num2);
            case "-" -> getOutput(sysType, num1 - num2);
            case "*" -> getOutput(sysType, num1 * num2);
            case "/" -> getOutput(sysType, num1 / num2);
            default -> exception(noOperation);
        };
    }

    public static boolean isNumValid(String[] numbers, String num) {
        for (String n: numbers) {
            if (n.equals(num)) {
                return true;
            }
        }
        return false;
    }

    // Перевод числа из Арабской системы в Римскую
    public static String arabToRome(String num) {
        int n = strToInt(num);

        if (n < 1) {
            return exception(negRomeVal);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arab.length; i++) {
            while (n >= arab[i]){
                n -= arab[i];
                res.append(rome[i]);
            }
        }

        return res.toString();
    }

    // Перевод числа из Римской системы в Арабскую
    public static int romeToArab(String num) {
        int res = 0;
        int i = 0;

        while ((num.length() > 0) & (i < rome.length)) {
            if (num.startsWith(rome[i])) {
                res += arab[i];
                num = num.substring(rome[i].length());
            } else {
                i++;
            }
        }

        return res;
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
            return arabToRome(intToStr(out));
        }
    }
    
    public static String exception(String msg) {
        try {
            throw new IOException();
        } catch (IOException e) {
            return msg;
        }
    }
}
