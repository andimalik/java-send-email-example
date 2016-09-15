package com.andimalik.learn;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptDecrypt {

	public static void main(String[] args) {
		String encryptionPassword = "bingo";
		String data = "trololo";
		// Bpyx8vGaV7v6XMp0dNymqQ==
		System.out.println(data);

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(encryptionPassword);
		String encryptedData = encryptor.encrypt(data);

		System.out.println(encryptedData);

		String decryptedData = encryptor.decrypt(encryptedData);

		System.out.println(decryptedData);
	}

}
