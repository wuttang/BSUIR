public class Bartender extends Employee {
    public Bartender(String name, int employeeID) {
        super(name, employeeID);
    }

    public void prepareDrink(String drinkName) {
        System.out.println(this.getName() + " is preparing a " + drinkName);
    }
}