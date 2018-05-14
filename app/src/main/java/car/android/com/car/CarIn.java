package car.android.com.car;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class CarIn extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_come_in);
    }
}
