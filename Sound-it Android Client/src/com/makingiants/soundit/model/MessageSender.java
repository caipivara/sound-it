package com.makingiants.soundit.model;

import java.net.SocketException;

import android.util.Log;

import com.makingiants.soundit.model.udp.Client;

public class MessageSender {
	
	// ---------------------------------------------
	// Attributes
	// ---------------------------------------------
	
	private static MessageSender instance;
	private Client client;
	
	// ---------------------------------------------
	// Constructors And Singleton get
	// ---------------------------------------------
	
	private MessageSender() {
		try {
			client = Client.getInstance();
		} catch (final SocketException e) {
			Log.e("RemoteControl", "MessageSender 1", e);
		}
	}
	
	public static MessageSender getInstance() {
		if (instance == null) {
			instance = new MessageSender();
		}
		
		return instance;
	}
	
	// ---------------------------------------------
	// Message methods
	// ---------------------------------------------
	
	public void sendMidiValueMessage(final int accX, final int accY, final int accZ, final int channel, final boolean left_pressed) {
		//TODO: use left_pressed in last parameter
		client.send(String.format("v %d %d %d %d", accX, accY, accZ, channel));
	}
	
	public void sendMidiInstrumentMessage(final int instrument, final int channel) {
		client.send("i " + instrument + " " + channel);
	}
}
