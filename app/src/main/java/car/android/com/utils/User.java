package car.android.com.utils;

import cn.bmob.v3.BmobObject;

public class User extends BmobObject{
    private String username;
    private String pwd;
    private String question;
    private String answer;
    private String carId;

    public void setAccountBalance(String accountBalance) {
        AccountBalance = accountBalance;
    }

    public String getAccountBalance() {

        return AccountBalance;
    }

    private String AccountBalance;
    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCarId() {
        return carId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
