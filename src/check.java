import java.util.Scanner;
public class check {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(time);
        long time2 = time + (28800);
        int FunnyNumber = 0;
        Scanner Xbox = new Scanner(System.in);
        while (FunnyNumber != 1) {
            System.out.println("Yeah that fits.");
            Xbox.next();
            System.out.println(timecheck(time2));
            if(timecheck(time2).equals("Time's up!")) {
                FunnyNumber = 1;
            }
        }
    }

    public static String timecheck(long fuck) {
        if (System.currentTimeMillis() <= fuck) {
            long Remainingtime = fuck - System.currentTimeMillis();
            long RemainingSeconds = Remainingtime / 1000;
            return "Current time remaining: " + RemainingSeconds + " seconds.";
        } else {
            return "Time's up!";
        }
    }
}
