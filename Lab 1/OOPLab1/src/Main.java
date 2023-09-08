public class Main {
    public static void main(String[] args) {
        Melee katana = new Katana(999, "steel", "tokyoghoulzxc");
        Melee butterflyKnife = new ButterflyKnife(356, "steel", "us army");
        Melee karambit = new Karambit(123, "plastic", "cs:go fan");

        System.out.println("Katana has " + katana.getDamage() + " damage");
        System.out.println("Butterfly knife has " + butterflyKnife.getDamage() + " damage");
        System.out.println("Karambit has " + karambit.getDamage() + " damage");
    }
}