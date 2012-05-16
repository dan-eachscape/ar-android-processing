package com.osc.kinect.processing;

import processing.core.*;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.graphics.Bitmap;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

import oscP5.OscMessage;
import oscP5.OscP5;



public class sketch_may16b extends PApplet {

	private static final String IP_EMULATOR = "192.168.1.30";

	private OscP5 oscP5;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			initOSC(IP_EMULATOR);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onPause(){
		super.onPause();
		oscP5.dispose();
	}
	
	public void onResume(){
		super.onResume();
		try {
			initOSC(IP_EMULATOR);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("deprecation")
	public void initOSC(String ipAddress) throws SocketException{
			oscP5 = new OscP5(this, ipAddress, 12345);
			Log.i("REMOVE BEFORE COMMIT","blah");
	}
	
	public String getLocalIpAddress() throws SocketException {

		for (Enumeration<NetworkInterface> en = NetworkInterface
				.getNetworkInterfaces(); en.hasMoreElements();) {
			NetworkInterface intf = en.nextElement();
			for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr
					.hasMoreElements();) {
				InetAddress inetAddress = enumIpAddr.nextElement();
				if (!inetAddress.isLoopbackAddress()) {
					return inetAddress.getHostAddress().toString();
				}
			}
		}

		return IP_EMULATOR;
	}

	public int sketchWidth() {
		return screenWidth;
	}

	public int sketchHeight() {
		return screenHeight;
	}

	public String sketchRenderer() {
		return P3D;
	}

	public void setup() {
		orientation(LANDSCAPE);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int densityDpi = dm.densityDpi;
		

	}

	public void draw() {
		
		//int colorInt = frameCount % 100;
		background(0);
		
		
	}

	public void oscEvent(OscMessage theOscMessage) {
			
		if (theOscMessage.checkAddrPattern("/test") == true) {
			int x = theOscMessage.get(0).intValue();
			int y = theOscMessage.get(1).intValue();
			//float z = theOscMessage.get(2).floatValue();
			//fill(255);
			translate(x, y);
			sphere(2);
			
		}
	
	}
}
