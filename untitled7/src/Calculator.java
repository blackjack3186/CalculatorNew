//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) throws Exception {

        System.out.println("Привет! Этот калькулятор считает только числа от 1 до 10, римскими или арабскими цифрами. Результат латинскими цифрами не может быть отрицательным.");

        Scanner s = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = s.nextLine();
        String result = calc(input);
    }

    public static String calc(String input) throws Exception {

        String operStr = " ";               // Тут прописаны операнды для действий с выражениями.
        char[] symbol = new char[10];
        char oper = '+';
        for (int i=1; i < input.length(); i++) {
            symbol[i] = input.charAt(i);
            if ( symbol[i] == '+'){
                oper = '+'; operStr = "\\+";
            }
            if ( symbol[i] == '-'){
                oper = '-'; operStr = "-";
            }
            if ( symbol[i] == '*'){
                oper = '*'; operStr = "\\*";
            }
            if ( symbol[i] == '/'){
                oper = '/'; operStr = "/";
            }
        }

        int num1 = 0;           //Инициализируем переменные.
        int num2 = 0;
        int result = 0;
        int resultArab = 0;

        String[] numbers = input.split(operStr);//Разделяем

        if (numbers.length > 2) {
            try {
                throw new Exception("=? \nОшибка. Вводить можно только два числа в выражении."); //Отбрасываем исключения
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try{
            num1 = romanNumeral(numbers[0]);
            num2 = romanNumeral(numbers[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("=? \nОшибка. Нужны два числа и значок операции между ними.");
        }
        if (num1 == 0 | num2 == 0) {
            try {
                num1 = Integer.parseInt(numbers[0]);
                num2 = Integer.parseInt(numbers[1]);
                if (num1 > 10 | num2 > 10 | num1 < 0 | num2 <0) {
                    throw new Exception("=? \nОшибка. Оба числа должны быть в интервале от 1 до 10.");
                }
                resultArab = calculate(num1, num2, oper);
                System.out.println("=" + resultArab);
            } catch (NumberFormatException e) {
                throw new Exception("=? \nОшибка. Нужно вводить или два арабских числа, или два римских.");
            } catch (ArithmeticException e) {
                throw new Exception("=? \nОшибка. Нельзя делить на 0!!!");
            }
        } else {
            try {
                result = calculate(num1, num2, oper);
                if (result == 0) {
                    System.out.println("=? \nОшибка. Разрешен только ответ больше нуля.");
                    System.exit(0);
                }
                String resultRom = romanSolution(result);
                System.out.println("=" + resultRom + " (" + result + ")");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new Exception("=? \nОшибка. Разрешен только ответ больше нуля.");
            }
        }
        return operStr;
    }

    public static int calculate(int x1, int x2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = x1 + x2;
                break;
            case '-':
                result = x1 - x2;
                break;
            case '*':
                result = x1 * x2;
                break;
            case '/':
                result = x1 / x2;
                break;
            default:
                break;
        }
        return result;
    }

    public static int romanNumeral(String roman) {  // Переводим римские цифры в арабские
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> {
                {
                    yield 0;
                }
            }
        };
    }

    public static String romanSolution(int arabNumeral) {           //здесь мы составляем словарь из массива римских цифр
        String[] romanAll = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
                "*****", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
                "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII",
                "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return romanAll[arabNumeral];
    }
}