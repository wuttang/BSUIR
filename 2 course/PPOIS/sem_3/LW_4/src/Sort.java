import java.util.Comparator;
import java.util.List;

public class Sort {
    public static <T extends Comparable<T>> void quickSort(T[] array) throws Exception {
        if (array == null || array.length == 0) {
            throw new Exception("Element is null");
        }
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) throws Exception {
        if (array == null || low < 0 || high >= array.length || low > high) {
            return;
        } else {
            int partitionIndex = partition(array, low, high);
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) throws Exception {
        if (array == null || array.length == 0) {
            throw new Exception("Element is null");
        }
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                if (i != j) {
                    swap(array, i, j);
                }
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(T[] array, int i, int j) throws Exception {
        if (array == null || array.length == 0) {
            throw new Exception("Element is null");
        }
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static <T> void quickSort(T[] array, Comparator<T> comparator) throws Exception {
        if (array == null || array.length == 0) {
            throw new Exception("Element is null");
        }
        quickSort(array, 0, array.length - 1, comparator);
    }

    private static <T> void quickSort(T[] array, int low, int high, Comparator<T> comparator) throws Exception {
        if (array == null || low < 0 || high >= array.length || low > high || comparator == null) {
            return;
        }
        int partitionIndex = partition(array, low, high, comparator);
        quickSort(array, low, partitionIndex - 1, comparator);
        quickSort(array, partitionIndex + 1, high, comparator);
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) throws Exception {
        if (array == null || array.length == 0) {
            throw new Exception("Element is null");
        }
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    public static <T extends Comparable<T>> void quickSort(List<T> list) throws Exception {
        if (list == null || list.isEmpty()) {
            throw new Exception("Element is null");
        }
        quickSort(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list, int low, int high) throws Exception {
        if (list == null || list.isEmpty() || low < 0 || high >= list.size() || low > high) {
            return;
        }
        int partitionIndex = partition(list, low, high);
        quickSort(list, low, partitionIndex - 1);
        quickSort(list, partitionIndex + 1, high);

    }

    private static <T extends Comparable<T>> int partition(List<T> list, int low, int high) throws Exception {
        if (list == null || list.isEmpty()) {
            throw new Exception("Element is null");
        }
        T pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(List<T> list, int i, int j) throws Exception {
        if (list == null || list.isEmpty()) {
            throw new Exception("Element is null");
        }
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static <T> void quickSort(List<T> list, Comparator<T> comparator) throws Exception {
        if (list == null || list.isEmpty() || comparator == null) {
            throw new Exception("Element is null");
        }

        quickSort(list, 0, list.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> list, int low, int high, Comparator<T> comparator) throws Exception {
        if (list == null || list.isEmpty() || low < 0 || high >= list.size() || low > high || comparator == null) {
            return;
        }
        int partitionIndex = partition(list, low, high, comparator);
        quickSort(list, low, partitionIndex - 1, comparator);
        quickSort(list, partitionIndex + 1, high, comparator);

    }

    private static <T> int partition(List<T> list, int low, int high, Comparator<T> comparator) throws Exception {
        if (list == null || list.isEmpty()) {
            throw new Exception("Element is null");
        }
        T pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }
}
