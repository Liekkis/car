package car.android.com.car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import car.android.com.tools.BmobTools;

/**
 * Created by yangli on 2018/5/15.
 */

public class CarOut extends AppCompatActivity {
    private TextView username;
    private TextView carid;
    private TextView timein;
    private TextView timeout;
    private TextView time;
    private TextView money;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_come_out);
        initView();
    }

    private void initView() {
        username = findViewById(R.id.user_out);
        username.setText(BmobTools.user.getUsername());
        carid = findViewById(R.id.car_out);
        carid.setText(BmobTools.carStop.getCarid());
        timein = findViewById(R.id.car_out_in_time);
        timein.setText(BmobTools.carStop.getTimein());
        timeout =findViewById(R.id.car_out_out_time);
        timeout.setText(BmobTools.carStop.getTimeout());
        time = findViewById(R.id.car_time_out);
        time.setText(BmobTools.carStop.getTime());
        money = findViewById(R.id.money_out);
        money.setText(BmobTools.carStop.getMoney());

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
