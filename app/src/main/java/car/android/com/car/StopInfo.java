package car.android.com.car;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import car.android.com.tools.BmobTools;
import car.android.com.utils.CarStop;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class StopInfo extends AppCompatActivity {
    private ListView listView;
    private TextView textcar;
    List<CarStop> mlist = null;
    private ArrayList<HashMap<String, Object>> listItem;
    SimpleAdapter simpleAdapter;
    private Handler mhandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    init();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_info);
        listView = findViewById(R.id.list);
        textcar = findViewById(R.id.text_name);
        textcar.setText(BmobTools.user.getCarId());
        getStopList();
    }

    private void init(){
        listItem = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<mlist.size();i++)
        {
            CarStop carStop = mlist.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("time","停车时间："+carStop.getTimein()+"--"+carStop.getTimeout());
            map.put("money", "金额："+carStop.getMoney());
            listItem.add(map);
        }
        Log.d("yangli","listItem:"+listItem.size());
         simpleAdapter = new SimpleAdapter(this,
                listItem,R.layout.list_item,new String[] {"time","money"},
                new int[] {R.id.time_text,R.id.money_text});
        listView.setAdapter(simpleAdapter);
    }

    private void getStopList(){
        //查询停车信息
        BmobQuery<CarStop> query = new BmobQuery<>();
        query.addWhereEqualTo("carid", BmobTools.user.getCarId());
        query.findObjects(new FindListener<CarStop>() {

            @Override
            public void done(List<CarStop> list, BmobException e) {
                if (e == null) {
                    Log.d("yangli","list:"+list.size());
                    mlist = list;
                    Message message = new Message();
                    message.what = 1;
                    mhandle.sendMessage(message);
                } else {
                    mlist = null;
                }
            }
        });
    }
}
