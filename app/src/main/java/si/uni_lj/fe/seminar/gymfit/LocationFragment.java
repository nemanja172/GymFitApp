package si.uni_lj.fe.seminar.gymfit;

import android.os.Bundle;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.Objects;


public class LocationFragment extends Fragment {

    ListView listView;
    ArrayAdapter<String> adapter;

    public LocationFragment() {

    }
    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
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
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        new Connection().execute();

        return view;
    }
    class Connection extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            String result="";
            String host = "http://192.168.64.104/gymfit/fitnesi1.php";
            try{
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(host));
                HttpResponse response = client.execute(request);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer stringBuffer = new StringBuffer("");

                String line = "";
                while ((line = reader.readLine()) !=null ){
                    stringBuffer.append(line);
                    break;
                }
                reader.close();
                result = stringBuffer.toString();
            }
            catch (Exception e){
                return new String("Greska: " + e.getMessage());
            }
            return result;

        }
        @Override
        protected void onPostExecute(String result){
            try {
                JSONObject jsonResult = new JSONObject(result);
                int success = jsonResult.getInt("success");
                if (success == 1) {
                    JSONArray fitnesi = jsonResult.getJSONArray("fitnesi");
                    for(int i=0; i < fitnesi.length(); i ++){
                        JSONObject fitnes = fitnesi.getJSONObject(i);
                        int ID_fitnesa = fitnes.getInt("ID_fitnesa");
                        String ime = fitnes.getString("ime");
                        String lokacija = fitnes.getString("lokacija");
                        String naslov = fitnes.getString("naslov");
                        String tip = fitnes.getString("tip");
                        String line = ID_fitnesa + " - " + ime + " - " + lokacija + " - " + naslov + " - " + tip;
                        adapter.add(line);
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Nema fitnesa", Toast.LENGTH_SHORT).show();
                }
            }
            catch (JSONException e){
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}