public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Person person = new Person(24, "Ivan Ivanovich Ivanov");
        System.out.println("A new person has been created: " + person.getFullName() + ". His age is: " + person.getAge() + ".");
        Animal dog = new Dog("gray");
        Animal cat = new Cat("white");
        dog.makeSound();
        cat.makeSound();
        Weapon ak47 = new AK47(30);
        System.out.println("AK-47's magazine capacity is: " + ak47.getMagazineCapacity());
        ak47.shoot();
    }
}
