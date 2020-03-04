package com.web.test.login.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


@Controller
public class RSAUtil {
	private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);

	private static KeyPairGenerator generator;
	private static KeyFactory keyFactory;
	private static KeyPair keyPair;
	private static Cipher cipher;

	public RSAUtil() {
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            keyFactory = KeyFactory.getInstance("RSA");
            cipher = Cipher.getInstance("RSA");
        } catch (Exception e) {
            logger.warn("RSAUtil 생성 실패.", e);
        }
    }

	  /** 새로운 키값을 가진 RSA 생성
     *  @return vo.other.RSA **/


	public static Map<String, Object>  createRSA() {
		 Map<String, Object> map = null;
		try {
			keyPair = generator.generateKeyPair();

			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			   map = new HashMap<String, Object>();
		        map.put("pubKeyModule", publicKeyModulus);
		        map.put("pubKeyExponent", publicKeyExponent);
		        map.put("privateKey", privateKey);
		} catch (Exception e) {
			logger.warn("RSAUtil.createRSA()", e);
		}
		return map;
	}

	/** 개인키를 이용한 RSA 복호화
     *  @param privateKey session에 저장된 PrivateKey
     *  @param encryptedText 암호화된 문자열 **/
	public static String getDecryptText(PrivateKey privateKey, String encryptedText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(hexToByteArray(encryptedText));
		return new String(decryptedBytes, "UTF-8");
	}

	// 16진수 문자열을 byte 배열로 변환
	private static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}

		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}
	
	public static String sha256_enc(String pwd) {
	    StringBuffer hexString = new StringBuffer();
	 
	    try {
	 
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
	 
	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	 
	            if (hex.length() == 1) {
	                hexString.append('0');
	            }
	 
	            hexString.append(hex);
	        }
	 
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	 
	    return hexString.toString();
	}

}
