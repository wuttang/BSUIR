public class Laptop extends Computer implements WithMonitor {
    private Monitor monitor;

    public Laptop(double frequency, int sizeOfRAM, int sizeOfMemory, int batteryCapacity, boolean coolingSystem, int numOfCores) {
        super(frequency, sizeOfRAM, sizeOfMemory, batteryCapacity, coolingSystem, numOfCores);
    }

    @Override
    public void withMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Monitor getMonitor() {
        return monitor;
    }
}