package pe.cotic.restCotic.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class Pruebas {

	private static final byte[] SALT_BYTES = { -87, -101, -56, 50, 86, 53, -29,
			3 };
	private static final int ITERATION_COUNT = 19;

	public static void main(String[] args) {
		String clave = "VIRGO198425";
		try {
			String claveEncriptada = fn_sEncrypting("PASSWORD", clave);
			System.out.println("Clave : " + clave + " - Encriptada : " + claveEncriptada);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String fn_sEncrypting(String sKeyPhrase, String sStringValue)
			throws NoSuchAlgorithmException {
		Cipher oECipher = null;
		Cipher oDCipher = null;
		try {
			KeySpec oKeySpec = new PBEKeySpec(sKeyPhrase.toCharArray(),
					SALT_BYTES, 19);
			SecretKey oKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(oKeySpec);
			oECipher = Cipher.getInstance(oKey.getAlgorithm());
			oDCipher = Cipher.getInstance(oKey.getAlgorithm());
			AlgorithmParameterSpec oaramSpec = new PBEParameterSpec(SALT_BYTES,
					19);
			oECipher.init(1, oKey, oaramSpec);
			oDCipher.init(2, oKey, oaramSpec);
			byte[] oUTF8 = sStringValue.getBytes("UTF8");
			byte[] oEnc = oECipher.doFinal(oUTF8);
			//return new BASE64Encoder().encode(oEnc);
			return new BASE64Encoder().encode(oEnc);
			
		} catch (Exception oE1) {
			oE1.printStackTrace();
		}
		return null;
	}

}
