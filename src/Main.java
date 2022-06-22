import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
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

        // Обрабатываем ограничения
        if (expression.length < 3 | expression.length % 2 == 0) {
            return "throws Exception //т.к. строка не является математической операцией";
        } else if (expression.length > 3) {
            return "throws Exception //т.к. формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *)";
        } else {
            // Получаем числа и оператор
            numbers = new String[] {expression[0], expression[2]};
            operator = expression[1];
        }

//        Expression expression = new Expression(input);
        for (String val: expression) {
            System.out.println(val);
        }
        return " ";
    }
}