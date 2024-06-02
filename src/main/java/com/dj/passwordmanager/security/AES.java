package com.dj.passwordmanager.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import org.springframework.context.annotation.Bean;

public class AES {
	private static final int KEY_LENGTH = 256;
	private static final int ITERATION_COUNT = 65536;
	private static final String salt = "gscbsqwdnjjiinjjcsa";

	@Bean
	public static String encrypt(String strToEncrypt, String secretKey) throws Exception {
		SecureRandom secureRandom = new SecureRandom();
		byte[] iv = new byte[16];
		secureRandom.nextBytes(iv);
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);
		byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
		byte[] encryptedData = new byte[iv.length + cipherText.length];
		System.arraycopy(iv, 0, encryptedData, 0, iv.length);
		System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);
		return Base64.getEncoder().encodeToString(encryptedData);
	}

	public static String decrypt(String strToDecrypt, String secretKey) throws Exception {
		byte[] encryptedData = Base64.getDecoder().decode(strToDecrypt);
		byte[] iv = new byte[16];
		System.arraycopy(encryptedData, 0, iv, 0, iv.length);
		IvParameterSpec ivspec = new IvParameterSpec(iv);

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);

		byte[] cipherText = new byte[encryptedData.length - 16];
		System.arraycopy(encryptedData, 16, cipherText, 0, cipherText.length);

		byte[] decryptedText = cipher.doFinal(cipherText);
		return new String(decryptedText, "UTF-8");
	}

}