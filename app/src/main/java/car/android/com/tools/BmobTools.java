package car.android.com.tools;

import android.content.Context;

import car.android.com.utils.CarStop;
import car.android.com.utils.User;
import cn.bmob.v3.Bmob;

public class BmobTools {
    public static User user = null;
    public static CarStop carStop = new CarStop();
    public static void onCreate(Context context, String applicationid) {
        Bmob.initialize(context, applicationid);
    }

}
