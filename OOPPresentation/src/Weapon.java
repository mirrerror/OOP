public abstract class Weapon {

    private int magazineCapacity;

    public Weapon(int magazineCapacity) {
        this.magazineCapacity = magazineCapacity;
    }

    public abstract void shoot();
    public abstract String getCaliber();
    public int getMagazineCapacity() {
        return magazineCapacity;
    }

    public void setMagazineCapacity(int magazineCapacity) {
        this.magazineCapacity = magazineCapacity;
    }

}
