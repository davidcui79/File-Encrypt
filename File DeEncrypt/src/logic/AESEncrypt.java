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
import sun.misc.BASE64Encoder;
import static logic.AESDefs.*;


public class AESEncrypt {
	 

    private Cipher ecipher;
   
    AESEncrypt() {
       
    }
    
    public void initialize(String passPhrase) throws Exception {
    	 SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
         KeySpec spec = new PBEKeySpec(passPhrase.toCharArray(), SALT, ITERATION_COUNT, KEY_LENGTH);
         SecretKey tmp = factory.generateSecret(spec);
         SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
  
         ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
         ecipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(AES_IV.getBytes()));
    }
    
	public byte[] update(byte[] obj, int len){
		return ecipher.update(obj, 0, len);
	}
	
	public byte[] doFinal(){
		try {
			return ecipher.doFinal();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
 
    public String encrypt(String encrypt) throws Exception {
        byte[] bytes = encrypt.getBytes("UTF8");
        byte[] encrypted = encrypt(bytes);
        return new BASE64Encoder().encode(encrypted);
    }
 
    public byte[] encrypt(byte[] plain) throws Exception {
        return ecipher.doFinal(plain);
    }
 
    public static void main(String[] args) throws Exception {
 
        String message = "MESSAGE";
        String password = "PASSWORD";
 
        AESEncrypt encrypter = new AESEncrypt();
        AESDecrypt decrypter = new AESDecrypt();
        encrypter.initialize(password);
        decrypter.initialize(password);
        String encrypted = encrypter.encrypt(message);
        String decrypted = decrypter.decrypt(encrypted);
 
        System.out.println("Encrypt(\"" + message + "\", \"" + password + "\") = \"" + encrypted + "\"");
        System.out.println("Decrypt(\"" + encrypted + "\", \"" + password + "\") = \"" + decrypted + "\"");
    }
}
