public class MathUtils {

    public static int getNextPerfectSquare(int number) {
        int candidate = (int) Math.pow(Math.ceil(Math.sqrt(number)), 2);
        if(candidate == number) return (int) Math.pow(Math.ceil(Math.sqrt(number+1)), 2);
        else return candidate;
    }

    public static String isNarcissistic(int number) {
        String[] numArr = String.valueOf(number).split("");
        int sum = 0;
        for(String entry : numArr) sum += Math.pow(Integer.parseInt(entry), numArr.length);
        if(sum == number) return number + " - narcissistic";
        else return number + " - not narcissistic";
    }

    public static int sumMultiples(int number) {
        int sum = 0;
        for(int i = 1; i < number; i++) if(i % 3 == 0 || i % 5 == 0) sum += i;
        return sum;
    }

}
