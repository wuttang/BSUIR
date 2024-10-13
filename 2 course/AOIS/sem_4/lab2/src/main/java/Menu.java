import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите логическое выражение(любые названия для переменных, знаки операций: &,|,~,→,!): ");
        LogicalExpression expression = new LogicalExpression(scanner.next());

        System.out.println("1. Таблица истинности.");
        System.out.println("2. СКНФ и СДНФ.");
        System.out.println("3. СКНФ и СДНФ в числовой форме.");
        System.out.println("4. Индексную форма функции");
        System.out.print("Сделайте выбор: ");
        int choose = scanner.nextInt();

        switch (choose) {
            case 1: {
                expression.printTruthTable();
                break;
            }

            case 2: {
                LogicalExpression pdnf = expression.generatePDNF();
                LogicalExpression pcnf = expression.generatePCNF();
                System.out.println("СДНФ: " + pdnf.getExpression());
                System.out.println("СКНФ: " + pcnf.getExpression());
                break;
            }

            case 3: {
                String numericFormPDNF = expression.toNumericFormPDNF();
                String numericFormPCNF = expression.toNumericFormPCNF();
                System.out.println("СДНФ в числовой форме: " + numericFormPDNF);
                System.out.println("СКНФ в числовой форме: " + numericFormPCNF);
                break;
            }

            case 4: {
                long indexForm = expression.toIndexForm();
                System.out.println("Функция в индесной форме: " + indexForm);
                break;
            }
        }
    }
}
