public class Waiter extends Employee{
    public Waiter(String name, int employeeID) {
        super(name, employeeID);
    }

    public void takeOrder(Table table) {
        System.out.println(this.getName() + " is taking an order for table " + table.getTableNumber());
    }

    public void serveOrder(Table table) {
        System.out.println(this.getName() + " is serving the order for table " + table.getTableNumber());
    }

    public void provideBill(Table table) {
        System.out.println(this.getName() + " is providing the bill for table " + table.getTableNumber());
    }
}