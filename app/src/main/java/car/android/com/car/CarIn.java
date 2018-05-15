package car.android.com.car;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import car.android.com.tools.BmobTools;
import car.android.com.tools.TimeUtils;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CarIn extends AppCompatActivity implements View.OnClickListener {
    private TextView username;
    private TextView carid;
    private TextView timein;
    private TextView time;
    private TextView money;
    private Button out;

    //计时器
    private Handler mhandle = new Handler();
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {
            currentSecond = currentSecond + 1000;
            time.setText(TimeUtils.getFormatHMS(currentSecond));
            money.setText(TimeUtils.getMoney(currentSecond)+"元");
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
        money = findViewById(R.id.money);
        username.setText(BmobTools.user.getUsername());
        carid.setText(BmobTools.user.getCarId());
        //获取进入时间
        timein.setText(getTime());

        time = findViewById(R.id.car_time);
        mhandle.postDelayed(timeRunable,0);

        out = findViewById(R.id.out);
        out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.out:
                BmobTools.carStop.setCarid(BmobTools.user.getCarId());
                BmobTools.carStop.setTimein(timein.getText().toString());
                BmobTools.carStop.setTimeout(getTime());
                BmobTools.carStop.setTime(time.getText().toString());
                BmobTools.carStop.setMoney(money.getText().toString());
                BmobTools.carStop.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null)    {
                            goToOut();
                        }else{

                        }
                    }
                });
                break;
        }
    }
    private String getTime(){
        long time1=System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=new Date(time1);
        String t1=format.format(d1);
        return t1;
    }
    private void goToOut(){
        Toast.makeText(getBaseContext(),BmobTools.carStop.getMoney(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,CarOut.class);
        startActivity(intent);
    }
}
