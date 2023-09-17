public class Dog extends Animal {
    public Dog(String color) {
        super(4, color);
    }

    @Override
    public void makeSound() {
        System.out.println("Dog: Woof!");
    }
}
