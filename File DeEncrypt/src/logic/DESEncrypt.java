package logic;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESEncrypt {

	/**
	 * @param args
	 */
	Cipher cipher = null;
	
	public DESEncrypt(){
		
	}
	
	public boolean initialize(String key){
		try{
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory sKeyFactory = null;
			sKeyFactory = SecretKeyFactory.getInstance("DES");
			cipher = Cipher.getInstance("DES");
			SecretKey desKey = sKeyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
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
	
    protected String bytesToString(byte[] encryptByte){
    	String result = "";
    	for (Byte bytes : encryptByte){
    		result += (char) bytes.intValue();
    	}
    	return result;
    }
    
    /*
     * TEST ONLY FUNCTION
     * User of this class should invoke update() and doFinal()
     */
	private byte[] encrypt(byte[] obj, String key){
		try{
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory sKeyFactory = null;
			Cipher cipher = null;
			sKeyFactory = SecretKeyFactory.getInstance("DES");
			cipher = Cipher.getInstance("DES");
			SecretKey desKey = sKeyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			return(cipher.doFinal(obj));

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * TEST ONLY FUNCTION
	 */
	private byte[] decrypt(byte[] obj, String key){
		try{
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory sKeyFactory = null;
			Cipher cipher = null;
			sKeyFactory = SecretKeyFactory.getInstance("DES");
			cipher = Cipher.getInstance("DES");
			SecretKey desKey = sKeyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			return (cipher.doFinal(obj));
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String encryptText = "DES encrypt text";
		DESEncrypt encrypt = new DESEncrypt();
		//byte[] e = encrypt.encrypt(encryptText.getBytes(),"Ericsson123");
		encrypt.initialize("Ericsson123");
		encrypt.update(encryptText.getBytes(), encryptText.getBytes().length);
		byte[] e = encrypt.doFinal();
		byte[] de = encrypt.decrypt(e, "Ericsson123");
		
		System.out.println(encrypt.bytesToString(e));
		System.out.println(encrypt.bytesToString(de));
	}

}
