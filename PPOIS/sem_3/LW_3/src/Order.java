import java.util.ArrayList;

public abstract class Order {
    private ArrayList<OrderItem> items = new ArrayList<>();
    private Chef chef;

    public void addItem(MenuItem menuItem, int quantity) {
        OrderItem orderItem = new OrderItem(menuItem, quantity);
        items.add(orderItem);
    }

    public void removeItem(OrderItem orderItem) {
        items.remove(orderItem);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem orderItem : items) {
            total += orderItem.getMenuItem().getPrice() * orderItem.getQuantity();
        }
        return total;
    }

    public void printReceipt() {
        System.out.println("Order receipt:");
        for (OrderItem orderItem : items) {
            System.out.println(orderItem.getMenuItem().getName() + " x" + orderItem.getQuantity() + " - $" + orderItem.getMenuItem().getPrice() * orderItem.getQuantity());
        }
        System.out.println("Total: $" + calculateTotal());
    }

    public void notifyChefToPrepareDishes() {
        for (OrderItem orderItem : items) {
            String dishName = orderItem.getMenuItem().getName();
            chef.prepareDish(dishName);
        }
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}