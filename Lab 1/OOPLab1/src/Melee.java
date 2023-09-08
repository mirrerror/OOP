public abstract class Melee {

    private int damage;
    private String material;
    private String manufacturer;

    public Melee(int damage, String material, String manufacturer) {
        this.damage = damage;
        this.material = material;
        this.manufacturer = manufacturer;
    }

    abstract void attack();

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
