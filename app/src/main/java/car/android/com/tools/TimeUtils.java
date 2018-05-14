package car.android.com.tools;

public class TimeUtils {
    public static String getFormatHMS(long time){
        time=time/1000;//总秒数
        int s= (int) (time%60);//秒
        int m= (int) (time/60);//分
        int h=(int) (time/3600);//秒
        return String.format("%02d:%02d:%02d",h,m,s);
    }
    public static String getMoney(long time){
        time = time/1000;
        long money = time * 1/60;
        return String.valueOf(money);
    }
}
