public class LocalOrder extends Order {
    private int tableNumber;

    public LocalOrder(int tableNumber) {
        super();
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }
}
