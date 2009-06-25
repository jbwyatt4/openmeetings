package de.medint.rtpsharer.test;

import de.medint.rtpsharer.main.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Streamer streamer = new Streamer("", 0, 0);
		streamer.start(10, 1024, 768, 1);
		

	}

}
