package car.android.com.utils;

import cn.bmob.v3.BmobObject;

/**
 * Created by yangli on 2018/5/14.
 */

public class CarStop extends BmobObject{
    private String carid;
    private String timein;
    private String timeout;
    private String time;
    private String money;
    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public void setTimein(String timein) {
        this.timein = timein;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCarid() {

        return carid;
    }

    public String getTimein() {
        return timein;
    }

    public String getTimeout() {
        return timeout;
    }

    public String getMoney() {
        return money;
    }

}
