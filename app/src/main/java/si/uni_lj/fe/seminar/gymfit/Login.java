package si.uni_lj.fe.seminar.gymfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {


    TextInputEditText textInputEditTextEmail, textInputEditTextGeslo;
    Button buttonLogin;
    TextView textViewSignup;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextGeslo = findViewById(R.id.geslo);
        buttonLogin = findViewById(R.id.btnLogin);
        textViewSignup = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        textViewSignup.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Signup.class);
            startActivity(intent);
            finish();
        });
        buttonLogin.setOnClickListener(v -> {

            String email, geslo;
            email = String.valueOf(textInputEditTextEmail.getText());
            geslo = String.valueOf(textInputEditTextGeslo.getText());

            if(!email.equals("") && !geslo.equals("") ) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "geslo";
                    //Creating array for data; id of android
                    String[] data = new String[2];
                    data[0] = email;
                    data[1] = geslo;
                    PutData putData = new PutData("http://192.168.64.104/gymfit/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Uspesna prijava")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            else{
                Toast.makeText(getApplicationContext(), "Vsa polja so obvezna", Toast.LENGTH_SHORT).show();
            }

        });
    }
}