public class Main {
    public static void main(String[] args) {
        User user = new User("Ivan", "Ivanov");
        System.out.println(user.getInitials());
        System.out.println(MathUtils.sumMultiples(10));
        Diamond diamond3 = new Diamond(3);
        System.out.println("size = 3");
        diamond3.print();
        Diamond diamond5 = new Diamond(5);
        System.out.println("size = 5");
        diamond5.print();
        Member member = new Member("Jack", 34, 9);
        member.printCategory();
        Member member1 = new Member("Deshawn", 64, 3);
        member1.printCategory();
        Member member2 = new Member("Tim", 56, 9);
        member2.printCategory();
        System.out.println(MathUtils.getNextPerfectSquare(121));
        System.out.println(MathUtils.getNextPerfectSquare(64));
        System.out.println(MathUtils.isNarcissistic(1));
        System.out.println(MathUtils.isNarcissistic(5));
        System.out.println(MathUtils.isNarcissistic(7));
        System.out.println(MathUtils.isNarcissistic(11));
        System.out.println(MathUtils.isNarcissistic(153));
        System.out.println(MathUtils.isNarcissistic(519));
        System.out.println(MathUtils.isNarcissistic(370));
        System.out.println(MathUtils.isNarcissistic(371));
        System.out.println(MathUtils.isNarcissistic(1634));
        System.out.println(MathUtils.isNarcissistic(2009));
        System.out.println(MathUtils.isNarcissistic(23284));
        System.out.println(MathUtils.isNarcissistic(879));
        System.out.println(MathUtils.isNarcissistic(306461));
        System.out.println(MathUtils.isNarcissistic(44680));
    }
}