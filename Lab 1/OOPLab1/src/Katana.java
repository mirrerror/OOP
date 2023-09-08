public class Katana extends Melee {
    public Katana(int damage, String material, String manufacturer) {
        super(damage, material, manufacturer);
    }

    @Override
    void attack() {
        System.out.println("anime is bad");
    }
}
