public class Monitor {
    private double sizeOfScreen;
    private int resolutionX;
    private int resolutionY;
    private TypeOfScreen type;
    private int brightness = 5;
    private int frequency;
    private boolean isTurnedOn = false;

    public Monitor(double sizeOfScreen, int resolutionX, int resolutionY, TypeOfScreen type, int frequency) {
        this.sizeOfScreen = sizeOfScreen;
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
        this.type = type;
        this.frequency = frequency;
    }

    public void turnOn() {
        isTurnedOn = true;
        System.out.println("Monitor is turned on.");
    }

    public void turnOff() {
        isTurnedOn = false;
        System.out.println("Monitor is turned off.");
    }

    public void adjustBrightness(int brightness) throws Exception {
        if (brightness < 0 || brightness > 10) throw new Exception("Incorrect brightness");
        this.brightness = brightness;
    }

    public double getSizeOfScreen() {
        return sizeOfScreen;
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public TypeOfScreen getType() {
        return type;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }
}