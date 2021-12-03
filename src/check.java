public class check {
    public static void main(String[]args){
        long time = System.currentTimeMillis();
        System.out.println(time);
        long time2 = time + (28800);
        while (System.currentTimeMillis()!= time2){
            System.out.println(timecheck(time2));
        }
    }
    public static String timecheck(long fuck){
        long Remainingtime= fuck - System.currentTimeMillis();
        return "Current time remaining: "+Remainingtime;
    }
}
