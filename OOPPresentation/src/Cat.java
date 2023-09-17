public class Cat extends Animal {
    public Cat(String color) {
        super(4, color);
    }

    @Override
    public void makeSound() {
        System.out.println("Cat: Meow!");
    }
}
