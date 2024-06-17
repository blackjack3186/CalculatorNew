import java.util.Scanner;

public class StringCalculator {
    static String result; // результат выражения.
    public static void main(String[] args) throws Exception {
        System.out.println("Привет! Этот калькулятор может складывать и вычитать две строки, либо делить и умножать строку на число.");
        System.out.println("В обратном порядке это сделать не получится. Строка не должна превышать 10 символов. Число должно быть меньше десяти.");
        System.out.println("Число также может являться строкой, если оно заключено в кавычки. Например, \"10\" + \"10\" = \"1010\"");

        System.out.println("Введите ваше выражение: ");
        Scanner scanner = new Scanner(System.in);
        String expressInput = scanner.nextLine();
        char operation = operation(expressInput);
// Блок проверок на ошибки.
        if (operation == ' ') throw new Exception("Ошибка! Отсутствует знак операции «+, -, /, *». Либо вы ничего не ввели.");
        String[] massiv = expressInput.split("\\" + operation + " ");
        if (massiv.length > 2) {throw new Exception("Ошибка! Введено больше двух строк.");}
        if (massiv.length < 2) {throw new Exception("Ошибка! Вы ввели только одну строку, либо только знак операции.");}
        if (massiv[0].length() > 13 || massiv[1].length() > 12) throw new Exception("Введено больше 10 символов");
        //Тут возник небольшой затуп.
// На данном этапе кавычки это тоже символы. Потому 12, а не 10.
// И ещё лишний пробел в первом элементе массива, из-за него выскакивает ошибка при вводе «"Example!!!" / 3».
// Наверное он появился из-за регулярного выражения в строке 16. В любом случае, я его обрезал в строке 37.
        if (!massiv[0].contains("\"")) throw new Exception("Ошибка! Операции типа «число на строку» или «число на число» недопустимы. Строка должна быть в кавычках.");
        if (operation == '*' || operation == '/') {
            if (massiv[1].contains("\"")) throw new Exception("Ошибка! Умножать или делить строку можно только на число.");}
        if (operation == '+' || operation == '-'){
            if (!massiv[1].contains("\"")) throw new Exception("Ошибка! Складывать или вычитать строку можно только со второй строкой");}
        int number = 0;
        if (!massiv[1].contains("\"")){ number = Integer.parseInt(massiv[1]);} // Если вторая строка это цифра без кавычек, то переводим в число.
        if (number > 10){throw new Exception("Ошибка! Вы ввели число больше 10.");
        }

        for (int i = 0; i < massiv.length; i++) { //Кавычки убираем
            massiv[i] = massiv[i].replace("\"", "");
        }
        //Для большего удобства перевожу массив в строки. Так и не понял, откуда берется пробел в первом элементе массива.
        // Я отрезал его методом trim. На всякий случай, второй элемент тоже затримил.
        String stroka1 = massiv[0].trim();
        String stroka2 = massiv[1].trim();
        calculation(operation, stroka1, stroka2, number);
        limitCalc();
    }
    static void calculation(char operation, String stroka1, String stroka2, int number) throws IllegalArgumentException {
        switch (operation) {
            case '+' -> result = stroka1 + stroka2;
            case '*' -> result = stroka1.repeat(number);
            case '-' -> {
                if (stroka1.contains(stroka2)) {
                    int resultA = stroka1.length() - stroka2.length();
                    result = stroka1.substring(0, resultA);
                } else {
                    result = stroka1;
                }
            }
            case '/' -> {
                if(number == 0){throw new IllegalArgumentException("Ошибка. Делить на ноль нельзя!");}
                if (stroka1.length() < number) {
                    throw new IllegalArgumentException("Ошибка. Делимое меньше, чем делитель");
                }
                int resultB = stroka1.length() / number;
                result = stroka1.substring(0, resultB);
            }
        }
    }
    static char operation(String text) { //Этот метод определяет и возвращает знак операции.
        String[] a = new String[] {"+", "-", "*", "/"};
        char action = ' ';
        for (int i = 0; i < a.length; i++) {
            if (text.contains(a[i])) {
                action = a[i].charAt(0);
                break;
            }
        }
        return action;
    }

    private static void limitCalc() {
        //Этот метод считает количество символов в строке результата и выводит в консоль.
        // Если число превысит 40, то строка обрежется и будет добавлено троеточие.
        //Точно не знаю, обязательны ли кавычки в выводе, но я их добавил, так как в примерах они есть.
        int limit = 40;
        if(result.length() < 40) {
            System.out.println("\"" + result + "\"");
        } else{
            String subStr = result.codePointCount(0, result.length()) > limit ?
                    result.substring(0, result.offsetByCodePoints(0, limit)) : result;
            System.out.println( "\"" + subStr + "..." + "\"");
        }
    }
}
