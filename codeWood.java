import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class codeWood{
    public static void main(String [] args) throws InterruptedException {
        int second = 10;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND , calendar.get(Calendar.SECOND) + second);
        System.out.println(calendar.getTime());
        System.out.println(Calendar.getInstance().getTime());
    }

}