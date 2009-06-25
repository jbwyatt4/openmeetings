package org.openmeetings.app.rtp;

public class RTPStreamingHandler {

		public static int getNextFreeRTPPort(){
			//TODO : Define Range of valid ports and check, if another Session already uses it
			return 22224;
		}
}
