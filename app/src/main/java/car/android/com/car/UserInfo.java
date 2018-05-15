package car.android.com.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import car.android.com.tools.BmobTools;
import car.android.com.utils.CarStop;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by yangli on 2018/5/15.
 */

public class UserInfo extends Activity implements View.OnClickListener {
    private TextView name;
    private TextView carid;
    private TextView stopcount;
    private Button startcar;
    private Button carstop;
    private int count;
    private Handler mhandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    stopcount.setText(Integer.toString(count));
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCount();
    }

    private void initView() {
        //设置显示信息
        name = findViewById(R.id.user_info_name);
        carid = findViewById(R.id.user_info_car_id);
        stopcount = findViewById(R.id.user_info_stop_car_count);
        name.setText(BmobTools.user.getUsername());
        carid.setText(BmobTools.user.getCarId());
        //为两个button绑定监听器
        startcar = findViewById(R.id.start_car);
        carstop = findViewById(R.id.car_stop);
        startcar.setOnClickListener(this);
        carstop.setOnClickListener(this);
        getCount();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_car:
                Intent intent = new Intent(this,CarIn.class);
                startActivity(intent);
                break;
            case R.id.car_stop:
                Intent mintent = new Intent(this,StopInfo.class);
                startActivity(mintent);
                break;
        }
    }
    private int getCount(){
        //查询停车次数并显示
        BmobQuery<CarStop> query = new BmobQuery<>();
        query.addWhereEqualTo("carid",BmobTools.user.getCarId());
        query.findObjects(new FindListener<CarStop>() {

            @Override
            public void done(List<CarStop> list, BmobException e) {
                if (e == null) {
                    count = list.size();
                    Message message = new Message();
                    message.what = 1;
                    mhandle.sendMessage(message);
                } else {
                    count = 0;
                }
            }
        });
        return count;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
