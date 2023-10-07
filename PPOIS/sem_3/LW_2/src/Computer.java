public class Computer {
    private double frequency;
    private int sizeOfRAM;
    private int sizeOfMemory;
    private int batteryCapacity;
    private boolean coolingSystem;
    private int numOfCores;
    private boolean isTurnedOn = false;
    private String password = null;
    private boolean passwordSetted = false;
    private boolean isUnlocked = true;

    public Computer(double frequency, int sizeOfRAM, int sizeOfMemory, int batteryCapacity, boolean coolingSystem, int numOfCores) {
        this.frequency = frequency;
        this.sizeOfRAM = sizeOfRAM;
        this.sizeOfMemory = sizeOfMemory;
        this.batteryCapacity = batteryCapacity;
        this.coolingSystem = coolingSystem;
        this.numOfCores = numOfCores;
    }

    public void turnOn() {
        isTurnedOn = true;
        System.out.println("Computer is turned on.");
    }

    public void turnOff() {
        isTurnedOn = false;
        System.out.println("Computer is turned off.");
    }

    public void restart() {
        turnOff();
        turnOn();
    }

    public void setPassword(String password) {
        this.password = password;
        passwordSetted = true;
        isUnlocked = false;
        System.out.println("Password has been set.");
    }

    public void changePassword(String newPassword) {
        if (passwordSetted) {
            this.password = newPassword;
            System.out.println("Passwords has been changed.");
        } else {
            this.setPassword(newPassword);
        }
    }

    public void unlockComputer(String enteredPassword) throws Exception {
        if (passwordSetted) {
            if (enteredPassword.equals(password)) {
                isUnlocked = true;
                System.out.println("Computer is unlocked.");
            } else throw new Exception("Incorrect password!");
        }
    }

    public double getFrequency() {
        return frequency;
    }

    public int getSizeOfRAM() {
        return sizeOfRAM;
    }

    public int getSizeOfMemory() {
        return sizeOfMemory;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public boolean isCoolingSystem() {
        return coolingSystem;
    }

    public int getNumOfCores() {
        return numOfCores;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPasswordSetted() {
        return passwordSetted;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }
}