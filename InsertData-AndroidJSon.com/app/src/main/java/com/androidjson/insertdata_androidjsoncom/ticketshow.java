package com.androidjson.insertdata_androidjsoncom;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.google.android.gms.location.FusedLocationProviderClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

public class ticketshow extends AppCompatActivity implements OnMapReadyCallback {
    ImageView imview;

    String ticketid = journeyplan.ticketdata;


    String datefromjourney = journeyplan.date1;
    String fromaddfromjourney = journeyplan.fromadd;
    String toaddfromjourney = journeyplan.toadd;

    public static DateFormat df1 = new SimpleDateFormat("dd/MM/yy HH:mm");
    public static String date1 = df1.format(Calendar.getInstance().getTime());

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketshow);
        imview = (ImageView) findViewById(R.id.imageView);
/////////////////////////////////////////////

       // MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
       // mapFragment.getMapAsync(this);


        /////////////////////////



        ////////////////////////
///////////////////////////////////////////////
        String ticketqr = ticketid;
        int height = 500;
        int width = 500;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(ticketqr, BarcodeFormat.QR_CODE, width, height);

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imview.setImageBitmap(bitmap);
            Canvas canvas = new Canvas(bitmap);

            Paint paint = new Paint();
            paint.setColor(Color.RED); // Text Color
            paint.setTextSize(20); // Text Size
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)); // Text Overlapping Pattern
            // some more settings...

            canvas.drawBitmap(bitmap, 0, 0, paint);
            canvas.drawText(fromaddfromjourney + " to " + toaddfromjourney +" on " + date1, 25, 480, paint);

            saveImageBitmap(bitmap, datefromjourney);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        //////maps part

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();

        ////////


    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(ticketshow.this);
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }

    public boolean isStoragePermissionGranted() {
        String TAG = "Storage Permission";
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    public void saveImageBitmap(Bitmap image_bitmap, String image_name) {
//        String root = Environment.getExternalStorageDirectory().toString();
        String root = "/storage/emulated/0/Pictures";
        Toast.makeText(ticketshow.this, "Saved QR in /storage/emulated/0/Pictures/Smart_Bus_QRs", Toast.LENGTH_SHORT).show();
        if (isStoragePermissionGranted()) { // check or ask permission
            File myDir = new File(root, "/Smart_Bus_QRs");
            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            String fname = image_name + ".jpg";
            File file = new File(myDir, fname);
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile(); // if file already exists will do nothing
                FileOutputStream out = new FileOutputStream(file);
                image_bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            MediaScannerConnection.scanFile(this, new String[]{file.toString()}, new String[]{file.getName()}, null);
        }
    }


    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        System.out.println("The lat long are:"+p1);

        return p1;
    }



    @Override
    public void onMapReady(final GoogleMap googleMap) {

       // googleMap.addMarker(new MarkerOptions().position(new LatLng(10, 10)).title("Marker1"));
       // googleMap.addMarker(new MarkerOptions().position(new LatLng(20, 20)).title("Marker2"));

        System.out.println(currentLocation.getLatitude()+"--"+currentLocation.getLongitude());
        LatLng latLng1 = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng1).title("I am here!").icon(BitmapDescriptorFactory.fromResource(R.drawable.moon));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng1));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 10));
        googleMap.addMarker(markerOptions);

        LatLng source = getLocationFromAddress(ticketshow.this,"Gajuwaka bus depot");
        LatLng destination = getLocationFromAddress(ticketshow.this,"NAD");
        System.out.println(source);
       //LatLng source = new LatLng(17.69, 83.22);
      // LatLng destination = new LatLng(17.74, 83.22);

       //googleMap.addMarker(new MarkerOptions().position(source).title("Marker1").icon(BitmapDescriptorFactory.fromResource(R.drawable.lettera)));
      // googleMap.addMarker(new MarkerOptions().position(destination).title("Marker2").icon(BitmapDescriptorFactory.fromResource(R.drawable.letterb)));



        new GetPathFromLocation(source, destination, new DirectionPointListener() {
            @Override
            public void onPath(PolylineOptions polyLine) {
             //   googleMap.addPolyline(polyLine);
            }
        }).execute();

    }




}








