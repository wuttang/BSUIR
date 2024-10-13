import java.util.*;

/**
 * Данный класс предназачен для работы с <b><i>кантровыми множествами</i></b>.
 * @author <b>Arkhip Volozhinets</b>
 */
public class CantorSet {
    private final Set<Object> elements;

    public CantorSet() {
        this.elements = new LinkedHashSet<>();
    }

    public CantorSet(String input) throws Exception {
        this();
        parseFromString(input);
    }

    /**
     * Функция, предназначенная для добавления элемента в множество
     * @param element элемент, который нужно добавить
     */
    public void addElement(Object element) {
        elements.add(element);
    }

    /**
     * Функция, предназначенная для удаления элемента из множества
     * @param element элемент, который нужно удалить
     */
    public void removeElement(Object element) {
        elements.remove(element);
    }

    /**
     * Функция, предназначенная для получения элементов множества
     * @return элементы множества
     */
    public Set<Object> getElements() {
        return elements;
    }

    /**
     * Функция проверяет является ли множество пустым
     * @return true - множество пустое, false - множество не пустое
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Функция проверяет наличие элемента в множестве
     * @param element элемент, который будет проверяться на наличие в множестве
     * @return true - элемент находится в множестве, falsr - элемента нет в множестве
     */
    public boolean isInSet(Object element) {
        return elements.contains(element);
    }


    /**
     * Функция считает мощность множества
     * @return целое число, которое является мощностью
     */
    public int sizeOfSet() {
        return elements.size();
    }

    /**
     * Функция предназначена для объединения двух множест
     *
     * @param set1 первое мноожество
     * @param set2 второе множество
     * @return объединение двух множеств
     */
    public static CantorSet unionOfSets(CantorSet set1, CantorSet set2) {
        CantorSet set3 = new CantorSet();
        set3.elements.addAll(set1.elements);
        set3.elements.addAll(set2.elements);
        return set3;
    }

    /**
     * Фцнкция пердназначена для пересечения двух множеств
     * @param set1 первое множество
     * @param set2 второе множество
     * @return пересечение двух множеств
     */
    public static CantorSet intersectionOfSets(CantorSet set1, CantorSet set2) {
        CantorSet set3 = new CantorSet();
        set3.elements.addAll(set1.elements);
        set3.elements.retainAll(set2.elements);
        return set3;
    }

    /**
     * Фцнкция пердназначена для разности двух множеств
     * @param set1 первое множество
     * @param set2 второе множество
     * @return разность двух множеств
     */
    public static CantorSet differenceOfSets(CantorSet set1, CantorSet set2) {
        CantorSet set3 = new CantorSet();
        set3.elements.addAll(set1.elements);
        set3.elements.removeAll(set2.elements);
        return set3;
    }

    /**
     * Функция предназначена для вычисления булеана множества
     * @param inputSet множество над которым нужно провести операцию
     * @return булеан множества
     */
    public static Set<CantorSet> booleanSet(CantorSet inputSet) {
        Set<CantorSet> powerSet = new HashSet<>();
        List<Object> elementsList = new ArrayList<>(inputSet.getElements());

        int numSubsets = 1 << elementsList.size();

        for (int i = 0; i < numSubsets; i++) {
            CantorSet subset = new CantorSet();
            for (int j = 0; j < elementsList.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    subset.addElement(elementsList.get(j));
                }
            }
            powerSet.add(subset);
        }

        return powerSet;
    }

    /**
     * Функция проверяет правильность ввода фигурных скобок
     * @param input строка, которую подает пользователь
     * @return true - количество скобок корректно, false - количество скобок некорректно
     */
    public static boolean correctInput(String input) {
        int bracketsCount = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '{') bracketsCount++;
            if (input.charAt(i) == '}') bracketsCount--;

            if (bracketsCount < 0) return false;
        }

        return bracketsCount == 0;
    }

    /**
     * Функция работает с поданной строкой и формирует из нее множество
     * @param input строка, поданная пользователем
     * @throws Exception если количество фигцрных скобок несовпадает
     */
    public void parseFromString(String input) throws Exception {
        if (!correctInput(input)) throw new Exception("Write correct Set");

        input = input.replaceAll("\\s{2,}", " ");
        input = input.substring(1, input.length() - 1);

        for (int i = 0; i < input.length(); i++) {
            StringBuilder temp = new StringBuilder();
            if (input.charAt(i) == '{') {
                while (input.charAt(i) != '}') {
                    if (input.charAt(i + 1) == '{') {
                        while (input.charAt(i) != '}') {
                            temp.append(input.charAt(i));
                            i++;
                        }
                    }
                    temp.append(input.charAt(i));
                    i++;
                }
                temp.append('}');
                String str_temp = temp.toString();
                this.addElement(str_temp);
            } else {
                if (i + 1 != input.length()) {
                    while (input.charAt(i + 1) != ',') {
                        temp.append(input.charAt(i));
                        i++;
                    }
                }
                temp.append(input.charAt(i));
                String str_temp = temp.toString();
                this.addElement(str_temp);

            }
            i = skipComma(input, i);
        }
    }

    /**
     * Функция для работы с запятыми внутри поданной строки.
     * @param input элемент, который обрабатывается
     * @param i индекс цикла
     * @return измененный индекс
     */
    private int skipComma(String input, int i) {
        if (i != input.length() - 1) {
            if (input.charAt(i + 1) == ',' && input.charAt(i + 2) != ' ') i++;
            else if (input.charAt(i + 1) == ',' && input.charAt(i + 2) == ' ') i += 2;
        }
        return i;
    }

    /**
     * Функция для перевода множества в строку для последующего вывода
     * @return строку
     */
    @Override
    public String toString() {
        return elements.toString();
    }

    /**
     * Функция для сравнения множеств
     * @param o сравниваемый объект
     * @return true - сравниваемые множества равны, false - сравниваемые множества не равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CantorSet cantorSet = (CantorSet) o;
        return Objects.equals(elements, cantorSet.elements);
    }

    /**
     * Функция для перевода объекта в хэш
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}