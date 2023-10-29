import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение ( Пример: 3 + 3 или VI + III))");

        String input = scanner.nextLine();
        String[] elements = input.split(" ");

        if (elements.length != 3) {
            throw new IllegalArgumentException("Не верный формат ввода. Используйте: a + b, a - b, a * b, a / b");
        }

        try {
            int a = convertToNumber(elements[0]);
            int b = convertToNumber(elements[2]);

            int result = calculate(a, b, elements[1]);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int convertToNumber(String input) {
        if (input.matches("[0-9]+")) {
            return Integer.parseInt(input);
        } else {
            return RomanToArabic(input);
        }
    }

    public static int RomanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        int i = 0;
        while (i < romanNumeral.length()) {
            char currentSymbol = romanNumeral.charAt(i);
            int currentValue = getValue(currentSymbol);

            if (i + 1 < romanNumeral.length()) {
                char nextSymbol = romanNumeral.charAt(i + 1);
                int nextValue = getValue(nextSymbol);

                if (nextValue > currentValue) {
                    result += nextValue - currentValue;
                    i += 2;
                } else {
                    result += currentValue;
                    i++;
                }
            } else {
                result += currentValue;
                i++;
            }
        }

        return result;
    }

    public static int getValue(char symbol) {
        switch (symbol) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return  1000;
            default:
                throw new IllegalArgumentException("Неверный символ римского числа: " + symbol);
        }
    }

    public static int calculate(int a, int b, String operator) {
        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }
        return result;
    }
}