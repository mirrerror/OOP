public class Animal {

    private int numberOfLegs;
    private String color;

    public Animal(int numberOfLegs, String color) {
        this.numberOfLegs = numberOfLegs;
        this.color = color;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void makeSound() {
        System.out.println("An animal is making a sound!");
    }
}
