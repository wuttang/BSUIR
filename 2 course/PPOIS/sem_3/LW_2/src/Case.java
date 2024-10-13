public class Case {
    private final String color;
    private final double size;
    private final CaseMaterial material;

    public Case(String color, double size, CaseMaterial material) {
        this.color = color;
        this.size = size;
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public double getSize() {
        return size;
    }

    public CaseMaterial getMaterial() {
        return material;
    }
}