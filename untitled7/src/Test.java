public class Test {
    int counter;
    public static void main(String[] args) {
        countSymbols("коворода", 'о');
    }

        private static int countSymbols(String str, char ch) {
            int counter = 0;
            for (int i = 0; i < str.length(); i++)
            {
                if (str.charAt(i) == ch) {
                    counter++;
                }
            }
            System.out.println("Количество символов " + "\"" + ch + "\": " + counter);
            return counter;
        }


}
