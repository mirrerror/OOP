public class ButterflyKnife extends Melee {
    public ButterflyKnife(int damage, String material, String manufacturer) {
        super(damage, material, manufacturer);
    }

    @Override
    void attack() {
        System.out.println("butterfly tricks look cool");
    }
}
