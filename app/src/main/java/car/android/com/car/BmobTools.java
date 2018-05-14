package car.android.com.car;

import android.content.Context;

import cn.bmob.v3.Bmob;

public class BmobTools {
   public static void onCreate(Context context,String applicationid){
       Bmob.initialize(context,applicationid);
   }

}
