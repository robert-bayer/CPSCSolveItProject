import java.util.Scanner;
public class clockcheck {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(time);
        long time2 = time + (7200000);
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

    public static String timecheck(long clocktime) {
        if (System.currentTimeMillis() <= clocktime) {
            long Remainingtime = clocktime - System.currentTimeMillis();
            long RemainingSeconds = Remainingtime / 1000;
            long RemainingMinutes = RemainingSeconds / 60;
            long RemainingHours = RemainingMinutes / 60;
            long RemainingMinutesDisplay = RemainingMinutes - (RemainingHours*60);
            long RemainingSecondsDisplay = RemainingSeconds - (RemainingMinutes*60);
            return "Current time remaining: " +RemainingHours+ " hour(s), "+ RemainingMinutesDisplay+ " minute(s), and "+ RemainingSecondsDisplay + " second(s).";
        } else {
            return "Time's up!";
        }
    }
}



