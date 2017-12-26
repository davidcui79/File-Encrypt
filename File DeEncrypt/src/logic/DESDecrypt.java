package logic;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESDecrypt {

	/**
	 * @param args
	 * 
	 * 
	 */
	Cipher cipher = null;
	
	public DESDecrypt(){
		
	}
	
	public boolean initialize(String key){
		try{
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory sKeyFactory = null;
			sKeyFactory = SecretKeyFactory.getInstance("DES");
			cipher = Cipher.getInstance("DES");
			SecretKey desKey = sKeyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public byte[] update(byte[] obj, int len){
		return cipher.update(obj, 0, len);
	}
	
	public byte[] doFinal(){
		try {
			return cipher.doFinal();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
