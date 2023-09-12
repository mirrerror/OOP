public class Member {

    public enum MemberCategory {
        OPEN, SENIOR;

        public static MemberCategory getCategory(int age, int handicap) {
            return (age > 55 && handicap > 7) ? SENIOR : OPEN;
        }
    }

    private String name;
    private int age, handicap;

    public Member(String name, int age, int handicap) {
        this.name = name;
        this.age = age;
        this.handicap = handicap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    public void printCategory() {
        System.out.println(MemberCategory.getCategory(age, handicap));
    }
}
