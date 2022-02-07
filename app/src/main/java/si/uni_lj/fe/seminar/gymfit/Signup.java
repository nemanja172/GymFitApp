package si.uni_lj.fe.seminar.gymfit;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Signup extends AppCompatActivity {

    TextInputEditText textInputEditTextIme, textInputEditTextPriimek, textInputEditTextGeslo, textInputEditTextDatum, textInputEditTextSpol, textInputEditTextTel, textInputEditTextEmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textInputEditTextIme = findViewById(R.id.ime);
        textInputEditTextPriimek = findViewById(R.id.priimek);
        textInputEditTextGeslo = findViewById(R.id.geslo);
        textInputEditTextDatum = findViewById(R.id.datum);
        textInputEditTextSpol = findViewById(R.id.spol);
        textInputEditTextTel = findViewById(R.id.tel);
        textInputEditTextEmail = findViewById(R.id.email);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);


        textViewLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });
        buttonSignUp.setOnClickListener(v -> {

            String ime, priimek, geslo, datum, spol, tel, email;
            ime = String.valueOf(textInputEditTextIme.getText());
            priimek = String.valueOf(textInputEditTextPriimek.getText());
            geslo = String.valueOf(textInputEditTextGeslo.getText());
            datum = String.valueOf(textInputEditTextDatum.getText());
            spol = String.valueOf(textInputEditTextSpol.getText());
            tel = String.valueOf(textInputEditTextTel.getText());
            email = String.valueOf(textInputEditTextEmail.getText());

            if(!ime.equals("") && !priimek.equals("") && !geslo.equals("") && !datum.equals("") && !spol.equals("") && !tel.equals("") && !email.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[7];
                    field[0] = "ime";
                    field[1] = "priimek";
                    field[2] = "geslo";
                    field[3] = "Datum_rojstva";
                    field[4] = "Spol";
                    field[5] = "Tel_stevilka";
                    field[6] = "email";
                    //Creating array for data; id of android
                    String[] data = new String[7];
                    data[0] = ime;
                    data[1] = priimek;
                    data[2] = geslo;
                    data[3] = datum;
                    data[4] = spol;
                    data[5] = tel;
                    data[6] = email;

                    PutData putData = new PutData("http://192.168.64.104/gymfit/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Uspesna registracija")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
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



