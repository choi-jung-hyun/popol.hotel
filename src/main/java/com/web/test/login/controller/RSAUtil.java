package com.web.test.login.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
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
            logger.warn("RSAUtil 占쏙옙占쏙옙 占쏙옙占쏙옙.", e);
        }
    }

	/**
	 * 占쏙옙占싸울옙 키占쏙옙占쏙옙 占쏙옙占쏙옙 RSA 占쏙옙占쏙옙
	 * 
	 * @return vo.other.RSA
	 **/
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

	/**
	 * 占쏙옙占쏙옙키占쏙옙 占싱울옙占쏙옙 RSA 占쏙옙호화
	 * 
	 * @param privateKey    session占쏙옙 占쏙옙占쏙옙占� PrivateKey
	 * @param encryptedText 占쏙옙호화占쏙옙 占쏙옙占쌘울옙
	 **/
	public String getDecryptText(PrivateKey privateKey, String encryptedText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(hexToByteArray(encryptedText));
		return new String(decryptedBytes, "UTF-8");
	}

	// 16占쏙옙占쏙옙 占쏙옙占쌘울옙占쏙옙 byte 占썼열占쏙옙 占쏙옙환
	private byte[] hexToByteArray(String hex) {
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
}
