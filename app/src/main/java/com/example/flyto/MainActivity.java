package com.example.flyto;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    boolean mapReady = false;

    LatLng bombayLatLong = new LatLng(19.1285902, 72.9173487);
    LatLng nagpurLatLong = new LatLng(21.176071, 79.045690);
    LatLng mechLatLng = new LatLng(19.133388, 72.916126);
    LatLng cseLatLng = new LatLng(19.130435, 72.915793);


    static final CameraPosition BOMBAY = CameraPosition.builder()
            .target(new LatLng(19.1285902, 72.9173487))
            .bearing(112)
            .tilt(65)
            .zoom(17)
            .build();
    static final CameraPosition NAGPUR = CameraPosition.builder()
            .target(new LatLng(21.176071, 79.045690))
            .bearing(112)
            .tilt(65)
            .zoom(17)
            .build();
    ;
    static final CameraPosition MECH = CameraPosition.builder()
            .target(new LatLng(19.133388, 72.916126))
            .bearing(112)
            .tilt(65)
            .zoom(17)
            .build();

    static final CameraPosition CSE = CameraPosition.builder()
            .target(new LatLng(19.130435, 72.915793))
            .bearing(112)
            .tilt(65)
            .zoom(17)
            .build();


    MarkerOptions bombay;
    MarkerOptions nagpur;
    MarkerOptions mech;
    MarkerOptions cse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bombay = new MarkerOptions()
                .position(bombayLatLong)
                .title(getResources().getString(R.string.bombay_title));

        nagpur = new MarkerOptions()
                .position(nagpurLatLong)
                .title(getResources().getString(R.string.nagpur_title));
        BitmapDrawable boyicon = (BitmapDrawable) getResources().getDrawable(R.drawable.boy);
        Bitmap boy = boyicon.getBitmap();
        Bitmap smallBoy = Bitmap.createScaledBitmap(boy, 96, 128, false);

        mech = new MarkerOptions()
                .position(mechLatLng)
                .title(getResources().getString(R.string.mech_title))
                .icon(BitmapDescriptorFactory.fromBitmap(smallBoy));

        BitmapDrawable girlicon = (BitmapDrawable) getResources().getDrawable(R.drawable.girl);
        Bitmap girl = girlicon.getBitmap();
        Bitmap smallGirl = Bitmap.createScaledBitmap(girl, 71, 160, false);


        cse = new MarkerOptions()
                .position(cseLatLng)
                .title(getResources().getString(R.string.cse_title))
                .icon(BitmapDescriptorFactory.fromBitmap(smallGirl));

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btn_bombay = findViewById(R.id.btn_bombay);
        btn_bombay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(BOMBAY);
            }
        });


        Button btn_nagpur = findViewById(R.id.btn_nagpur);
        btn_nagpur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(NAGPUR);
            }
        });


        Button btn_mech = findViewById(R.id.btn_mech);
        btn_mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(MECH);
            }
        });


        Button btn_cse = findViewById(R.id.btn_cse);
        btn_cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(CSE);
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        map = googleMap;

        map.addMarker(bombay);
        map.addMarker(nagpur);
        map.addMarker(mech);
        map.addMarker(cse);
        flyTo(BOMBAY);

    }

    private void flyTo(CameraPosition target) {
        map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
    }
}
