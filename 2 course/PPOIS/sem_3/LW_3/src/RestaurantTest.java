import org.junit.Test;
import org.junit.Assert;

public class RestaurantTest {

    @Test
    public void menuItemTest() throws Exception {
        Menu menu = new Menu();
        MenuItem item = new MenuItem("Bolognese", "Dish with pasta and tomatos", 3.14, "Main dish");
        menu.addItem(item);
        Assert.assertEquals(1, menu.getItems().size());
        menu.removeItem(item);
        Assert.assertEquals(0, menu.getItems().size());
    }

    @Test
    public void orderTest() throws Exception {
        MenuItem menuItem = new MenuItem("Bolognese", "Dish with pasta and tomatos", 3.14, "Main dish");
        OrderItem orderItem = new OrderItem(menuItem, 2);
        LocalOrder order = new LocalOrder(3);
        Chef chef = new Chef("Leopold", 10347);
        Table table = new Table(3, 5);
        order.addItem(orderItem);
        Assert.assertEquals(1, order.getItems().size());
        order.printReceipt();
        order.setChef(chef);
        chef.setCurrentTable(table);
        order.notifyChefToPrepareDishes();
        order.removeItem(orderItem);
        Assert.assertEquals(0, order.getItems().size());
    }

    @Test
    public void tableTest() throws Exception{
        Table table = new Table(3, 5);
        table.reserve();
        table.vacate();
        table.occupy();
    }

    @Test
    public void employeeTest() throws Exception {
        Bartender bartender = new Bartender("Gerald", 72190);
        bartender.prepareDrink("Bloody Mary");
        Table table = new Table(3, 5);
        Chef chef = new Chef("Leopold", 10347);
        LocalOrder order = new LocalOrder(3);
        Waiter waiter = new Waiter("Gerrardo", 318942);
        waiter.setOrder(order);
        waiter.setChef(chef);
        waiter.takeOrder(table);
        waiter.serveOrder(table);
        waiter.provideBill(table);
    }
    @Test
    public void deliveryOrderTest() throws Exception {
        DeliveryOrder order = new DeliveryOrder("Gikalo street, 9", "+375298835423");
        order.calculateTotal();
        order.setContactPhoneNumber("+375296666666");
    }
}
