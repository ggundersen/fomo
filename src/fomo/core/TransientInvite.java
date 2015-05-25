package fomo.core;

import java.util.Timer;
import java.util.TimerTask;

public class TransientInvite {
	
	private static final int INVITE_LIFETIME_IN_SECONDS = 60; 

	public TransientInvite() {
		Timer timer = new Timer();
	    timer.schedule(new DestroyInvite(), INVITE_LIFETIME_IN_SECONDS * 1000);
	}
	
	class DestroyInvite extends TimerTask {
		public void run() {
			System.out.println("Timer done");
		}
	}
}
