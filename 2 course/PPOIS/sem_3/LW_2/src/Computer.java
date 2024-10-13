public class Computer {
    private final double frequency;
    private final int sizeOfRAM;
    private final int sizeOfMemory;
    private final int batteryCapacity;
    private final boolean coolingSystem;
    private final int numOfCores;
    private boolean isTurnedOn;
    private String password;
    private boolean passwordSetted;
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
        if (password == null || password.isEmpty()){
            return;
        }
        this.password = password;
        passwordSetted = true;
        isUnlocked = false;
        System.out.println("Password has been set.");
    }

    public void changePassword(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()){
            return;
        }
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