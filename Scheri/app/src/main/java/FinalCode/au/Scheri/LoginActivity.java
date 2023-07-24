package FinalCode.au.Scheri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import FinalCode.au.Scheri.R;


public class LoginActivity extends AppCompatActivity {

    Button button;
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

        button  = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { scheduleViewer();
            }
        });
    }
    public  void signUpPage(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void scheduleViewer(){
        Intent intent = new Intent(this, MainActivity.class);
    }
}

