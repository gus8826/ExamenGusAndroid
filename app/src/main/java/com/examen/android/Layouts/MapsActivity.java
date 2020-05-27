package com.examen.android.Layouts;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.examen.android.Models.ItemMaps;
import com.examen.android.R;
import com.examen.android.WebServicies.GetRequestAllUserInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    int flag = 0;
    private GoogleMap mMap;
    private Float lat;
    private Float log;
    private ArrayList<ItemMaps> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        data = new ArrayList<>();
        final EditText editTextLog = findViewById(R.id.editTextLog);
        final EditText editTextLat = findViewById(R.id.editTextLat);
        Button button = findViewById(R.id.button);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextLog.getText().toString().isEmpty()) {
                    editTextLog.setError(getString(R.string.text_5));
                    editTextLog.requestFocus();
                } else if (validateNullEdtTxt(editTextLog.getText().toString())) {
                    editTextLog.setError(getString(R.string.text_6));
                    editTextLog.requestFocus();
                } else {
                    editTextLog.setVisibility(View.GONE);
                    editTextLat.setVisibility(View.VISIBLE);
                    if (flag == 1) {
                        if (editTextLat.getText().toString().isEmpty()) {
                            editTextLat.setError(getString(R.string.text_5));
                            editTextLat.requestFocus();
                        } else if (validateNullEdtTxt(editTextLat.getText().toString())) {
                            editTextLat.setError(getString(R.string.text_6));
                            editTextLat.requestFocus();
                        } else  {
                            lat = Float.parseFloat(editTextLat.getText().toString());
                            log = Float.parseFloat(editTextLog.getText().toString());
                            data.add(new ItemMaps(lat, log));
                            puntyro(mMap);
                            editTextLog.setText("");
                            editTextLat.setText("");
                            editTextLog.setVisibility(View.VISIBLE);
                            editTextLat.setVisibility(View.GONE);
                        }
                    } else {
                        flag = 1;
                    }

                }
            }
        });

    }

    public static boolean validateNullEdtTxt(String string) {
        return string != null && string.length() > 10;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(19.2631885, -99.0018432);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void puntyro(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney;
        for (int i = 0; i < data.size(); i++) {
            sydney = new LatLng(data.get(i).getText_1(), data.get(i).getText_2());
            mMap.addMarker(new MarkerOptions().position(sydney).title("nueva posición" + i));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            if (i >= 2) {
                Intent intent = new Intent(MapsActivity.this, SegundoActivity.class);
                startActivity(intent);
            }
        }
    }

}
