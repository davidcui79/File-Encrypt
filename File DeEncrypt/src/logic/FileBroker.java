package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileBroker {
	File srcFile = null;
	File tgtFile = null;

	public FileBroker(File src, File tgt) {
		srcFile = src;
		tgtFile = tgt;
	}

	public boolean open(File file) {
		return false;
	}

	public void encrypt(String pwd) {
		try {
//			AESEncrypt encrypt = new AESEncrypt();
			DESEncrypt encrypt = new DESEncrypt();
			encrypt.initialize(pwd);
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(tgtFile);
			byte[] buffer = new byte[1024];
			int n = 0;
			n = fis.read(buffer);
			while(n > -1){
				//System.out.println(new String(buffer, 0, n));
				byte[] encrypted = encrypt.update(buffer, n);
				fos.write(encrypted);
				n = fis.read(buffer);
			}
			
			byte[] encrypted = encrypt.doFinal();
			fos.write(encrypted);

			fis.close();		
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void decrypt(String pwd){
		try {
//			AESDecrypt decrypt = new AESDecrypt();
			DESDecrypt decrypt = new DESDecrypt();
			decrypt.initialize(pwd);
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(tgtFile);	
			byte[] buffer = new byte[1024];
			int n = 0;
			n = fis.read(buffer);
			while(n > -1){
				//System.out.println(new String(buffer, 0, n));
				byte[] decrypted = decrypt.update(buffer, n);
				//System.out.println(new String(decrypted));
				fos.write(decrypted);
				n = fis.read(buffer);
			}
			byte[] decrypted = decrypt.doFinal();
			fos.write(decrypted);

			fis.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
