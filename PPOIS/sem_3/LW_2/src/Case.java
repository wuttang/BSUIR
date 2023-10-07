public class Case {
    private String color;
    private double size;
    private MaterialOfCase material;

    public Case(String color, double size, MaterialOfCase material) {
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

    public MaterialOfCase getMaterial() {
        return material;
    }
}