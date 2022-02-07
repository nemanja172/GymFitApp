package si.uni_lj.fe.seminar.gymfit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;


public class ProfileFragment extends Fragment {

    TextInputEditText ime, priimek, geslo, datum, spol, tel, email;
    Button buttonUpdate;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ime = view.findViewById(R.id.ime);
        priimek = view.findViewById(R.id.priimek);
        geslo = view.findViewById(R.id.geslo);
        datum = view.findViewById(R.id.datum);
        spol = view.findViewById(R.id.spol);
        tel = view.findViewById(R.id.tel);
        email = view.findViewById(R.id.email);

        buttonUpdate = view.findViewById(R.id.buttonUpdate);





        return view;
    }
}