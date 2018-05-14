package car.android.com.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import car.android.com.tools.BmobTools;
import car.android.com.utils.User;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button register;
    private Button login;
    private EditText editName;
    private EditText editPW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //初始化控件，绑定监听器
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        editName = findViewById(R.id.username1);
        editPW = findViewById(R.id.pw);
        editPW.setText("");
        //初始化bmob数据库
        BmobTools.onCreate(this, "09da0e466a5420e12f258ef48d1ded9b");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                final Intent intent = new Intent(this, Register.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.login:
                //从数据库获取信息，如果用户名和密码正确，则登录成功
                BmobQuery<User> query = new BmobQuery<User>("User");
                query.addWhereEqualTo("username", editName.getText().toString());
                query.addWhereEqualTo("pwd", editPW.getText().toString());
                query.findObjects(new FindListener<User>() {

                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e == null) {
                            if (list.size() > 0) {
                                Toast.makeText(getBaseContext(),"登录成功",Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(getBaseContext(),CarIn.class);
                                BmobTools.user = list.get(0);
                                startActivity(intent1);
                            }else{
                                Toast.makeText(getBaseContext(),"用户名或密码错误，请重新登录",Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getBaseContext(),"网络错误",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String username = data.getStringExtra("username").toString();
        if(username != "") {
            editName.setText(username);
        }
    }
}
