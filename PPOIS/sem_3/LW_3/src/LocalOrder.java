public class LocalOrder extends Order {
    private final int tableNumber;
    private Order order;

    public LocalOrder(int tableNumber) {
        super();
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem orderItem : super.items) {
            total += orderItem.getMenuItem().getPrice() * orderItem.getQuantity();
        }
        return total;
    }
}