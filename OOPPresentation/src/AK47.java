public class AK47 extends Weapon {
    public AK47(int magazineCapacity) {
        super(magazineCapacity);
    }

    @Override
    public void shoot() {
        System.out.println("You better run, AK-47 is shooting...");
    }

    @Override
    public String getCaliber() {
        return "7,62x39mm";
    }
}
