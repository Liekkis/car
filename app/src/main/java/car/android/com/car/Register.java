package car.android.com.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import car.android.com.utils.User;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends Activity implements View.OnClickListener {
    private EditText username;
    private EditText pwd;
    private EditText question;
    private EditText answer;
    private EditText carId;
    private Button cancel;
    private Button reg;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(getBaseContext(),"注册成功",Toast.LENGTH_LONG).show();
                    regeistSuccess();
                    break;
                case 0:
                    Toast.makeText(getBaseContext(),"注册失败",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_car);
        initView();
    }

    private void initView() {
        //初始化EditText
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        carId = findViewById(R.id.carid);
        //初始化两个button，注册上监听器
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        reg = findViewById(R.id.register1);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                cancel();
                break;
            case R.id.register1:
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User user = new User();
                        user.setUsername(username.getText().toString());
                        user.setPwd(pwd.getText().toString());
                        user.setQuestion(question.getText().toString());
                        user.setAnswer(answer.getText().toString());
                        user.setCarId(carId.getText().toString());
                        user.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Message msg = new Message();
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                } else {
                                    Log.d("yangli","信息："+e.getMessage());
                                    Message msg = new Message();
                                    msg.what = 0;
                                    handler.sendMessage(msg);
                                }
                            }
                        });
                    }
                });
                thread.start();

        }
    }

    private void regeistSuccess() {
        Intent intent = new Intent();
        intent.putExtra("username", username.getText().toString());
        this.setResult(1, intent);
        finish();
    }

    private void cancel() {
        Intent intent = new Intent();
        intent.putExtra("username", "");
        this.setResult(1, intent);
        finish();
    }
}
