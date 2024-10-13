public class Monitor {
    private final double sizeOfScreen;
    private final int resolutionX;
    private final int resolutionY;
    private final ScreenType type;
    private int brightness = 5;
    private final int frequency;
    private boolean isTurnedOn = false;

    public Monitor(double sizeOfScreen, int resolutionX, int resolutionY, ScreenType type, int frequency) {
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

    public ScreenType getType() {
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