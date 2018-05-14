package car.android.com.car;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import car.android.com.tools.BmobTools;
import car.android.com.tools.TimeUtils;

public class CarIn extends Activity{
    private TextView username;
    private TextView carid;
    private TextView timein;
    private TextView time;
    //计时器
    private Handler mhandle = new Handler();
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {

            currentSecond = currentSecond + 1000;
            time.setText(TimeUtils.getFormatHMS(currentSecond));
            if (!isPause) {
                //递归调用本runable对象，实现每隔一秒一次执行任务
                mhandle.postDelayed(this, 1000);
            }
        }
    };
    private boolean isPause = false;//是否暂停
    private long currentSecond = 0;//当前毫秒数
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_come_in);
        initView();
    }

    private void initView() {
        username = findViewById(R.id.user_in);
        carid = findViewById(R.id.car_in);
        timein = findViewById(R.id.car_in_time);

        username.setText("用户名："+BmobTools.user.getUsername());
        carid.setText("车辆牌照："+BmobTools.user.getCarId());
        //获取进入时间
        long time1=System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=new Date(time1);
        String t1=format.format(d1);
        timein.setText("进入时间："+t1);

        time = findViewById(R.id.car_time);
        mhandle.postDelayed(timeRunable,0);
    }
}
