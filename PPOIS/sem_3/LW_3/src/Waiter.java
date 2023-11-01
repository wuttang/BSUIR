public class Waiter extends Employee{
    private LocalOrder order;
    private Chef chef;
    public Waiter(String name, int employeeID) {
        super(name, employeeID);
    }

    public void takeOrder(Table table) {
        System.out.println(this.getName() + " is taking an order for table " + table.getTableNumber());
    }

    public void serveOrder(Table table) {
        System.out.println(this.getName() + " is serving the order for table " + table.getTableNumber());
        order.notifyChefToPrepareDishes();
        order.setChef(chef);
    }

    public void provideBill(Table table) {
        System.out.println(this.getName() + " is providing the bill for table " + table.getTableNumber());
    }

    public LocalOrder getOrder() {
        return order;
    }

    public Chef getChef() {
        return chef;
    }

    public void setOrder(LocalOrder order) {
        this.order = order;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}