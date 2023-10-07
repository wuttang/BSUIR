public class MobileDevice extends Computer implements WearableCase, WearableScreenSecurity, WithMonitor {
    private Case cover = null;
    private ScreenSecurity screenSecurity = null;
    private Monitor monitor = null;
    private boolean caseIsOn = false;
    private boolean screenSecurityIsOn = false;

    public MobileDevice(double frequency, int sizeOfRAM, int sizeOfMemory, int batteryCapacity, boolean coolingSystem, int numOfCores) {
        super(frequency, sizeOfRAM, sizeOfMemory, batteryCapacity, coolingSystem, numOfCores);
    }

    @Override
    public void wearCase(String color, double size, MaterialOfCase material) {
        this.cover = new Case(color, size, material);
        caseIsOn = true;
    }

    @Override
    public void wearScreenSecurity(double size, TypeOfScreenSecurity type, double thickness) {
        this.screenSecurity = new ScreenSecurity(size, type, thickness);
        screenSecurityIsOn = true;
    }

    @Override
    public void withMonitor(double sizeOfScreen, int resolutionX, int resolutionY, TypeOfScreen type, int frequency) {
        this.monitor = new Monitor(sizeOfScreen, resolutionX, resolutionY, type, frequency);
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
        return caseIsOn;
    }

    public boolean isScreenSecurityIsOn() {
        return screenSecurityIsOn;
    }
}
