public class DeliveryOrder extends Order {
    private String deliveryAddress;
    private String contactPhoneNumber;

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
}
