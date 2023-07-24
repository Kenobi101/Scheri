package FinalCode.au.Scheri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.lang.*;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import FinalCode.au.Scheri.R;

public class SignupActivity extends AppCompatActivity {
    Button button;
    Button buttonRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInPage();
            }
        });

        buttonRegister = (Button) findViewById(R.id.register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    EditText userIdEditText = (EditText) findViewById(R.id.userId);
                    String userId = userIdEditText.getText().toString();

                    EditText nameEditText = (EditText) findViewById(R.id.name);
                    String name = nameEditText.getText().toString();

                    EditText passwordEditText = (EditText) findViewById(R.id.password);
                    String password = passwordEditText.getText().toString();

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUserId(userId);

                    userEntity.setName(name);
                    userEntity.setPassword(password);
                    userEntity.setUserId(userId);

                    UserDao userDao = userDatabase.userDao();
                    userDao.registerUser(userEntity);

                    System.out.println("Sign Up Succesfull");

                    logInPage();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        });


    }

    public  void logInPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
