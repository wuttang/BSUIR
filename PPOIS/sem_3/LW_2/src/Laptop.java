public class Laptop extends Computer implements WithMonitor {
    private Monitor monitor = null;

    public Laptop(double frequency, int sizeOfRAM, int sizeOfMemory, int batteryCapacity, boolean coolingSystem, int numOfCores) {
        super(frequency, sizeOfRAM, sizeOfMemory, batteryCapacity, coolingSystem, numOfCores);
    }

    @Override
    public void withMonitor(double sizeOfScreen, int resolutionX, int resolutionY, TypeOfScreen type, int frequency) {
        this.monitor = new Monitor(sizeOfScreen, resolutionX, resolutionY, type, frequency);
    }

    public Monitor getMonitor() {
        return monitor;
    }
}