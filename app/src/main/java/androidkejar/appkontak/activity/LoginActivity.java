package androidkejar.appkontak.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidkejar.appkontak.MainActivity;
import androidkejar.appkontak.R;

public class LoginActivity extends AppCompatActivity {
    private EditText editText1 ,editText2; //Deklarasi object dari class EdiText

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText1 = (EditText)findViewById(R.id.username);
        editText2 = (EditText) findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.login_button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText1.getText().toString().equals("admin")&&editText2.getText()
                        .toString().equals("admin")){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"Login Sukses",Toast.LENGTH_LONG).show();
                }else if(editText1.getText().toString().equals("")&&editText2.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this,"Username dan Password harus diisi",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(LoginActivity.this,"Login Gagal",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    }








