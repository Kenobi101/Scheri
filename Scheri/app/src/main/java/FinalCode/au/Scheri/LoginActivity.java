package FinalCode.au.Scheri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import FinalCode.au.Scheri.R;


public class LoginActivity extends AppCompatActivity {

    Button button;
    Button loginbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.signUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpPage();
            }
        });

        loginbutton = (Button) findViewById(R.id.login);
        loginbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {

                    EditText userIdEditText = (EditText) findViewById(R.id.userId);
                    String userId = userIdEditText.getText().toString();

                    EditText passwordEditText = (EditText) findViewById(R.id.password);
                    String password = passwordEditText.getText().toString();

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());

                    UserDao userDao = userDatabase.userDao();
                    List<UserEntity> userEntityList = userDao.getusersbyuseridandpassword(userId, password);

                    if (userEntityList.size() > 0) {

                        scheduleViewer();
                    } else {

                        userIdEditText.setText("");
                        passwordEditText.setText("");
                    }

                }catch (Exception e){
                    System.out.println("loginactivity login: " + e.getMessage());
                }
            }
        });
    }
    public  void signUpPage(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void scheduleViewer(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

