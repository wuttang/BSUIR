public class Table implements TableInterface{
    private final int tableNumber;
    private final int seatingCapacity;
    private String status;
    private Order currentOrder;

    public Table(int tableNumber, int seatingCapacity) {
        this.tableNumber = tableNumber;
        this.seatingCapacity = seatingCapacity;
        this.status = "Free";
        this.currentOrder = null;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getStatus() {
        return status;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void reserve() {
        status = "Reserved";
    }

    public void occupy() throws Exception {
        if (status.equals("Free")) {
            status = "Occupied";
            currentOrder = new LocalOrder(this.tableNumber);
        } else {
            throw new Exception("Table has been reserved.");
        }
    }

    public void vacate() {
        status = "Free";
        currentOrder = null;
    }

    @Override
    public String toString() {
        return "Table №" + tableNumber + " (" + seatingCapacity + " seats) - " + status;
    }
}

