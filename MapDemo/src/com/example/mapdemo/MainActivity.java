package com.example.mapdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.mapdemo.MainActivity;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends Activity {
	 private MapView mMapView = null;
	 private BaiduMap mBaiduMap;
	 @Override  
	 protected void onCreate(Bundle savedInstanceState) {  
		 super.onCreate(savedInstanceState);   
	     //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
		 //注意该方法要再setContentView方法之前实现  
	     SDKInitializer.initialize(getApplicationContext());  
	     setContentView(R.layout.activity_main);  
	     //获取地图控件引用  
	     mMapView = (MapView) findViewById(R.id.bmapView);  
	     mBaiduMap = mMapView.getMap();
	     //setMapType(BaiduMap.MAP_TYPE_NORMAL);
	     //mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
	 }  
	 @Override  
	 protected void onDestroy() {  
	     super.onDestroy();  
	     //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
	     mMapView.onDestroy();  
	 }  
	 @Override  
	 protected void onResume() {  
		 super.onResume();  
		 //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
		 mMapView.onResume();  
	 }  
	 @Override  
	 protected void onPause() {  
		 super.onPause();  
		 //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
		 mMapView.onPause();  
	 }	  
}
