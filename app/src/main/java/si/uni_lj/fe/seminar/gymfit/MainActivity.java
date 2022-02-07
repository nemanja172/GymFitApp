package si.uni_lj.fe.seminar.gymfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    TextView textViewLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    break;

                case R.id.nav_profile:
                    fragment = new ProfileFragment();
                    break;

                case R.id.nav_reservation:
                    fragment = new ReservationFragment();
                    break;

                case R.id.nav_location:
                    fragment = new LocationFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

            textViewLogout = (TextView) findViewById(R.id.logout);
            textViewLogout.setOnClickListener(v -> new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Odjava")
                    .setMessage("Se želite odjaviti?")
                    .setPositiveButton("Da", (dialog, which) -> {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("Ne", (dialog, which) -> {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    })
                    .show());

            return true;
        });
    }

}