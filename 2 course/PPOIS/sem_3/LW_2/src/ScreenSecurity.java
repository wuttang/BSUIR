public class ScreenSecurity {
    private final double size;
    private final ScreenSecurityType type;
    private final double thickness;

    public ScreenSecurity(double size, ScreenSecurityType type, double thickness) {
        this.size = size;
        this.type = type;
        this.thickness = thickness;
    }

    public double getSize() {
        return size;
    }

    public ScreenSecurityType getType() {
        return type;
    }

    public double getThickness() {
        return thickness;
    }
}