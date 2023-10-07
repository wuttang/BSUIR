public class ScreenSecurity {
    private double size;
    private TypeOfScreenSecurity type;
    private double thickness;

    public ScreenSecurity(double size, TypeOfScreenSecurity type, double thickness) {
        this.size = size;
        this.type = type;
        this.thickness = thickness;
    }

    public double getSize() {
        return size;
    }

    public TypeOfScreenSecurity getType() {
        return type;
    }

    public double getThickness() {
        return thickness;
    }
}