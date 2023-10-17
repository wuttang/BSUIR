public class Chef extends Employee{
    private Table currentTable;
    public Chef(String name, int employeeID) {
        super(name, employeeID);
    }

    public void prepareDish(String dishName) {
        System.out.println(this.getName() + " is preparing " + dishName);
        this.garnishDish(dishName);
    }

    public void garnishDish(String dishName) {
        System.out.println(this.getName() + " is garnishing " + dishName);
        this.sendDishToServer(dishName, currentTable);
    }

    public void sendDishToServer(String dishName, Table currentTable) {
        System.out.println(this.getName() + " is sending " + dishName + " to table " + currentTable.getTableNumber());
    }
}
