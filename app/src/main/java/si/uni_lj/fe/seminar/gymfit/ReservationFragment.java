package si.uni_lj.fe.seminar.gymfit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class ReservationFragment extends Fragment {

    TextInputEditText textInputEditTextID_uporabnika, textInputEditTextID_fitnesa, textInputEditTextdatum;
    Button buttonRezervacija;
    ProgressBar progressBar;

    public ReservationFragment() {
        // Required empty public constructor
    }

    public static ReservationFragment newInstance(String param1, String param2) {
        ReservationFragment fragment = new ReservationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        textInputEditTextID_uporabnika = view.findViewById(R.id.ID_uporabnika);
        textInputEditTextID_fitnesa = view.findViewById(R.id.ID_fitnesa);
        textInputEditTextdatum = view.findViewById(R.id.datum);
        buttonRezervacija = view.findViewById(R.id.buttonRezervacija);
        progressBar = view.findViewById(R.id.progress);

        buttonRezervacija.setOnClickListener(v -> {

            String ID_uporabnika, ID_fitnesa, datum;
            ID_uporabnika = String.valueOf(textInputEditTextID_uporabnika.getText());
            ID_fitnesa = String.valueOf(textInputEditTextID_fitnesa.getText());
            datum = String.valueOf(textInputEditTextdatum.getText());

            if(!ID_uporabnika.equals("") && !ID_fitnesa.equals("") && !datum.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[3];
                    field[0] = "ID_uporabnika";
                    field[1] = "ID_fitnesa";
                    field[2] = "datum";
                    //Creating array for data; id of android
                    String[] data = new String[3];
                    data[0] = ID_uporabnika;
                    data[1] = ID_fitnesa;
                    data[2] = datum;

                    PutData putData = new PutData("http://192.168.64.104/gymfit/reservation.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Uspesna rezervacija")) {
                                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity().getApplicationContext(), Login.class);
                                startActivity(intent);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            else{
                Toast.makeText(getActivity().getApplicationContext(), "Vsa polja so obvezna", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}