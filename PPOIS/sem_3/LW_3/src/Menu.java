import java.util.ArrayList;

public class Menu {
    private final ArrayList<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public void printMenu() {
        for (MenuItem item : items) {
            System.out.println(item);
        }
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }
}
