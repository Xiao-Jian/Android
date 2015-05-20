package com.example.location;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
 
/**
 * 
 * @author ZDY
 *
 */
public class MainActivity extends Activity implements LocationListener {
 
        private TextView myLocationText;
        private LocationManager locationManager;
        private String provider;
        private Location location;
        private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
 
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
 
                myLocationText = (TextView) findViewById(R.id.myLocationText);
 
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // String provider = LocationManager.GPS_PROVIDER;
 
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                criteria.setAltitudeRequired(true);
                criteria.setBearingRequired(true);
                criteria.setCostAllowed(true);
                criteria.setPowerRequirement(Criteria.POWER_LOW);
 
                provider = locationManager.getBestProvider(criteria, true);
                location = locationManager.getLastKnownLocation(provider);
                updateWithNewLocation(location);
                locationManager.requestLocationUpdates(provider, 2000, 10, this);
        }
 
        private void updateWithNewLocation(Location location) {
                String latLongString;
                if (location != null) {
                        double lat = location.getLatitude();
                        double lng = location.getLongitude();
                        float spe = location.getSpeed();// �ٶ�
                        float acc = location.getAccuracy();// ����
                        double alt = location.getAltitude();// ����
                        float bea = location.getBearing();// ���
                        long tim = location.getTime();// ����UTCʱ��1970��1��1����
                        latLongString = "γ��:" + lat + "\n����:" + lng + "\n���ȣ�" + acc
                                        + "\n�ٶȣ�" + spe + "\n���Σ�" + alt + "\n��У�" + bea + "\nʱ�䣺"
                                        + sdf.format(tim);
                } else {
                        latLongString = "�޷���ȡλ����Ϣ";
                }
                myLocationText.setText("����ǰ��λ����:\n" + latLongString);
        }
 
        @Override
        public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                updateWithNewLocation(location);
        }
 
        @Override
        public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub
                updateWithNewLocation(null);
        }
 
        @Override
        public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub
 
        }
 
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub
 
        }
}
