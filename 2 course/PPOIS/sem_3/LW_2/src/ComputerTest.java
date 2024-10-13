import org.junit.Assert;
import org.junit.Test;

public class ComputerTest {

    @Test
    public void turnOnTest() throws Exception {
        MobileDevice phone = new MobileDevice(2.1, 8, 128, 4000, false, 8);
        phone.turnOn();
        Assert.assertTrue(phone.isTurnedOn());
    }

    @Test
    public void turnOffTest() throws Exception {
        MobileDevice phone = new MobileDevice(2.1, 8, 128, 4000, false, 8);
        phone.turnOn();
        phone.turnOff();
        Assert.assertFalse(phone.isTurnedOn());
    }

    @Test
    public void restartTest() throws Exception {
        Laptop laptop = new Laptop(2.1, 8, 128, 4000, false, 8);
        laptop.restart();
        Assert.assertTrue(laptop.isTurnedOn());
    }

    @Test
    public void setPasswordTest() throws Exception {
        Laptop laptop = new Laptop(2.1, 8, 128, 4000, false, 8);
        String password = "qwerty";
        laptop.changePassword(password);
        Assert.assertEquals("qwerty", laptop.getPassword());
    }

    @Test
    public void changePasswordTest() throws Exception {
        Laptop laptop = new Laptop(2.1, 8, 128, 4000, false, 8);
        String password = "qwerty";
        laptop.setPassword(password);
        String newPassword = "ytrewq";
        laptop.changePassword(newPassword);
        Assert.assertEquals("ytrewq", laptop.getPassword());
    }

    @Test
    public void unlockComputerTest() throws Exception {
        Laptop laptop = new Laptop(2.1, 8, 128, 4000, false, 8);
        String password = "qwerty";
        laptop.setPassword(password);
        laptop.unlockComputer(password);
        Assert.assertTrue(laptop.isUnlocked());
    }

    @Test
    public void monitorTest() throws Exception {
        Laptop laptop = new Laptop(2.1, 8, 128, 4000, false, 8);
        Monitor monitor = new Monitor(13.1, 1920, 1080, ScreenType.AMOLED, 144);
        laptop.withMonitor(monitor);
        Assert.assertNotEquals(null, laptop.getMonitor());
    }

    @Test
    public void mobileMonitorTest() throws Exception {
        MobileDevice tablet = new MobileDevice(2.1, 8, 128, 4000, false, 8);
        Monitor monitor = new Monitor(13.1, 1920, 1080, ScreenType.AMOLED, 144);
        tablet.withMonitor(monitor);
        Assert.assertNotEquals(null, tablet.getMonitor());
    }

    @Test
    public void makeCallTest() {
        MobileDevice tablet = new MobileDevice(2.1, 8, 128, 4000, false, 8);
        tablet.makeCall("80297777777");
        tablet.sendTextMessage("80297777777", "Hello World!");
    }

    @Test
    public void adjustBrightnessTest() throws Exception {
        Monitor monitor = new Monitor(13.2, 1920, 1080, ScreenType.OLED, 60);
        monitor.adjustBrightness(9);
        Assert.assertEquals(9, monitor.getBrightness());
    }

    @Test
    public void monitorTurnOnTest() {
        Monitor monitor = new Monitor(13.2, 1920, 1080, ScreenType.OLED, 60);
        monitor.turnOn();
        Assert.assertTrue(monitor.isTurnedOn());
    }

    @Test
    public void monitorTurnOffTest() {
        Monitor monitor = new Monitor(13.2, 1920, 1080, ScreenType.OLED, 60);
        monitor.turnOn();
        monitor.turnOff();
        Assert.assertFalse(monitor.isTurnedOn());
    }

    @Test
    public void CaseTest() {
        Case cover = new Case("White", 6.1, CaseMaterial.LEATHER);
        MobileDevice phone = new MobileDevice(2.2, 8, 128, 4000, false, 0);
        phone.wearCase(cover);
    }

    @Test
    public void ScreenSecurityTest() {
        ScreenSecurity screenSecurity = new ScreenSecurity(6.1, ScreenSecurityType.GLASS, 1.5);
        MobileDevice phone = new MobileDevice(2.2, 8, 128, 4000, false, 0);
        phone.wearScreenSecurity(screenSecurity);
    }
}