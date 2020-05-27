package com.examen.android.Layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.examen.android.Models.ItemData;
import com.examen.android.R;
import com.examen.android.WebServicies.GetRequestAllUserInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ExecutionException;

public class SegundoActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMapSegundo;
    private ItemData itemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        itemData = new ItemData();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMapSegundo);
        mapFragment.getMapAsync(this);

        Button buttonNextTercer = findViewById(R.id.buttonNextTercer);
        buttonNextTercer.setOnClickListener(this);
        try {
            itemData = new GetRequestAllUserInfo(this).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        puntyro(mMapSegundo);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMapSegundo = googleMap;
        LatLng sydney = new LatLng(19.2631885, -99.0018432);
        mMapSegundo.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMapSegundo.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void puntyro(GoogleMap googleMap) {
        mMapSegundo = googleMap;
        LatLng sydney = new LatLng(Float.parseFloat(itemData.getItemUbicaciones().getLat()), Float.parseFloat(itemData.getItemUbicaciones().getLat()));
        mMapSegundo.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMapSegundo.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNextTercer) {
            Intent intent = new Intent(SegundoActivity.this, TercerActivity.class);
            startActivity(intent);
        }
    }
}
