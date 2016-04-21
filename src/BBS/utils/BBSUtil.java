package BBS.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class BBSUtil {
	public static String encrypt(String target){
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(target.getBytes());
			return Base64.encodeBase64String(md.digest());
		} catch (NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
	}

	//改行コードを<br>に変換
	public static String lineSeparatorEncoder(String traget){
		String decode;
		decode = traget.replaceAll(System.lineSeparator(), "<br>");
		return decode;
	}
}
