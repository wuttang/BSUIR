public class DeliveryOrder extends Order {
    private final String deliveryAddress;
    private String contactPhoneNumber;
    private static final double DELIVERY_PRICE = 5.83;

    public DeliveryOrder(String deliveryAddress, String contactPhoneNumber) {
        super();
        this.deliveryAddress = deliveryAddress;
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem orderItem : super.items) {
            total += orderItem.getMenuItem().getPrice() * orderItem.getQuantity();
        }
        if (total <= 50) {
            total += DELIVERY_PRICE;
        }
        return total;
    }
}
