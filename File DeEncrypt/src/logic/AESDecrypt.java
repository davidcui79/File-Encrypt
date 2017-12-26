package logic;

import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import static logic.AESDefs.*;

public class AESDecrypt {

    private Cipher dcipher;
   
    AESDecrypt() {
       
    }
    
    public void initialize(String passPhrase) throws Exception {
    	 SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
         KeySpec spec = new PBEKeySpec(passPhrase.toCharArray(), SALT, ITERATION_COUNT, KEY_LENGTH);
         SecretKey tmp = factory.generateSecret(spec);
         SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

         Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
         c.init(Cipher.ENCRYPT_MODE, secret);
         
         dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
         dcipher.init(Cipher.DECRYPT_MODE, secret,new IvParameterSpec(AES_IV.getBytes()));
    }
    
	public byte[] update(byte[] obj, int len){
		return dcipher.update(obj, 0, len);
	}
	
	public byte[] doFinal(){
		try {
			return dcipher.doFinal();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
 
 
    public String decrypt(String encrypt) throws Exception {
        byte[] bytes = new BASE64Decoder().decodeBuffer(encrypt);
        byte[] decrypted = decrypt(bytes);
        return new String(decrypted, "UTF8");
    }
 
    public byte[] decrypt(byte[] encrypt) throws Exception {
        return dcipher.doFinal(encrypt);
    }

}