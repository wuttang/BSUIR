public class MobileDevice extends Computer implements WithMonitor {
    private Case cover;
    private ScreenSecurity screenSecurity;
    private Monitor monitor;

    public MobileDevice(double frequency, int sizeOfRAM, int sizeOfMemory, int batteryCapacity, boolean coolingSystem, int numOfCores) {
        super(frequency, sizeOfRAM, sizeOfMemory, batteryCapacity, coolingSystem, numOfCores);
    }

    public void wearCase(Case cover) {
        this.cover = cover;
    }

    public void wearScreenSecurity(ScreenSecurity screenSecurity) {
        this.screenSecurity = screenSecurity;
    }

    @Override
    public void withMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public void makeCall(String phoneNumber) {
        System.out.println("Calling " + phoneNumber);
    }

    public void sendTextMessage(String phoneNumber, String message) {
        System.out.println("Sending message to " + phoneNumber + ": " + message);
    }

    public Case getCover() {
        return cover;
    }

    public ScreenSecurity getScreenSecurity() {
        return screenSecurity;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public boolean isCaseIsOn() {
        return cover != null;
    }

    public boolean isScreenSecurityIsOn() {
        return screenSecurity != null;
    }
}
