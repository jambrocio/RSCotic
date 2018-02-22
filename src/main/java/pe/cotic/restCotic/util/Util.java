package pe.cotic.restCotic.util;

import java.io.IOException;

import pe.cotic.restCotic.pruebas.BASE64Encoder;

public class Util {

	public static byte[] Base64ToBytes(String imageString) throws IOException {
		
		BASE64Encoder decode = new BASE64Encoder();
		byte[] decodedBytes = decode.decode(imageString);
		return decodedBytes;		
		
	}
	
}
