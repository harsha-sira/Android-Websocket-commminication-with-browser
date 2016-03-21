package com.example.androidservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class MyService extends Service {

	String host = "localhost";
	int port = 8887;

	public MyService() {
	}


	Thread thread = new Thread(new Runnable(){
		@Override
		public void run() {
			try {
				WebSocketServer server = new SimpleServer(new InetSocketAddress(host, port));
				server.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});

	@Override
	public IBinder onBind(Intent intent) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// Perform your long running operations here.
		thread.start();
		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onDestroy() {
		thread.interrupt();
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

	}
}