package pe.cotic.restCotic.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class Seguridad {
	
	private static final byte[] SALT_BYTES = { -87, -101, -56, 50, 86, 53, -29, 3 };
	private static final int ITERATION_COUNT = 19;

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
	
	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}
	
	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	
}
