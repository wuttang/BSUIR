public class Chef extends Employee{
    private Table currentTable;
    public Chef(String name, int employeeID) {
        super(name, employeeID);
    }

    public void prepareDish(String dishName) {
        System.out.println(this.getName() + " is preparing " + dishName);
        this.garnishDish(dishName);
    }

    private void garnishDish(String dishName) {
        System.out.println(this.getName() + " is garnishing " + dishName);
        this.sendDishToServer(dishName, currentTable);
    }

    private void sendDishToServer(String dishName, Table currentTable) {
        System.out.println(this.getName() + " is sending " + dishName + " to table " + currentTable.getTableNumber());
    }

    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }

    public Table getCurrentTable() {
        return currentTable;
    }
}
