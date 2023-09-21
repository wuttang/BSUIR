import java.util.*;

public class CantorSet {
    private final Set<Object> elements;

    public CantorSet() {
        this.elements = new LinkedHashSet<>();
    }

    public CantorSet(String input) throws Exception {
        this();
        parseFromString(input);
    }

    public void addElement(Object element) {
        elements.add(element);
    }

    public void removeElement(Object element) {
        elements.remove(element);
    }

    public Set<Object> getElements() {
        return elements;
    }

    public void sizeOfSet() {
        if (elements.isEmpty()) {
            System.out.println("This Set is Empty!");
        } else {
            System.out.println("Size of this Set is " + elements.size());
        }
    }

    public void isInSet(Object element) {
        if (elements.contains(element)) {
            System.out.println("Element " + element + " is in Set");
        } else {
            System.out.println("Element " + element + " isn't in this Set");
        }
    }

    public static CantorSet unionOfSets(CantorSet set1, CantorSet set2) {
        CantorSet set3 = new CantorSet();
        set3.elements.addAll(set1.elements);
        set3.elements.addAll(set2.elements);
        System.out.println("Result set of union: " + set3.elements);
        return set3;
    }

    public static CantorSet intersectionOfSets(CantorSet set1, CantorSet set2) {
        CantorSet set3 = new CantorSet();
        set3.elements.addAll(set1.elements);
        set3.elements.retainAll(set2.elements);
        return set3;
    }

    public static CantorSet differenceOfSets(CantorSet set1, CantorSet set2) {
        CantorSet set3 = new CantorSet();
        set3.elements.addAll(set1.elements);
        set3.elements.removeAll(set2.elements);
        return set3;
    }

    public static Set<CantorSet> powerSet(CantorSet inputSet) {
        Set<CantorSet> powerSet = new HashSet<>();
        List<Object> elementsList = new ArrayList<>(inputSet.getElements());

        // Calculate the number of subsets (2^n, where n is the number of elements in the input set)
        int numSubsets = 1 << elementsList.size();

        // Generate all possible subsets
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

    public static boolean correctInput(String input) {
        int bracketsCount = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '{') bracketsCount++;
            if (input.charAt(i) == '}') bracketsCount--;

            if (bracketsCount < 0) return false;
        }

        return bracketsCount == 0;
    }

    public void parseFromString(String input) throws Exception {
        if (!correctInput(input)) throw new Exception("Write correct Set");

        input = input.substring(1, input.length() - 1);

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '{') {
                StringBuilder temp = new StringBuilder();
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
                //  this.addElement(input.charAt(i));
                StringBuilder temp = new StringBuilder();
                while (input.charAt(i+1) != ',') {
                    temp.append(input.charAt(i));
                    i++;
                }
                temp.append(input.charAt(i));
                String str_temp = temp.toString();
                this.addElement(str_temp);
            }
            i = skipComma(input, i);
        }
        System.out.println("Your Set: " + elements);
    }

    private int skipComma(String input, int i) {
        if (i != input.length() - 1) {
            if (input.charAt(i + 1) == ',' && input.charAt(i + 2) != ' ') i++;
            else if (input.charAt(i + 1) == ',' && input.charAt(i + 2) == ' ') i += 2;
        }
        return i;
    }

    @Override
    public String toString() {
        return "Your Set: " + elements;
    }

    public static void main(String[] args) throws Exception {
        CantorSet set5 = new CantorSet("{anton, b, c, {d, e}, {d, e}, h, {}, {f, {g}}}");
        set5.isInSet("{d, e}");

//        CantorSet set1 = new CantorSet("{a, b, {b, c}}");
//        CantorSet set2 = new CantorSet("{{b, c}, d, e, f}");
//        CantorSet set3 = new CantorSet("{}");
//        set3 = differenceOfSets(set1, set2);
//        System.out.println(set3);

//        CantorSet set = new CantorSet("{a, b, c}");
//        Set<CantorSet> powerSet = CantorSet.powerSet(set);
//
//        System.out.println("Power Set of " + set + ":");
//        for (CantorSet subset : powerSet) {
//            System.out.println(subset);
//        }
    }
}
