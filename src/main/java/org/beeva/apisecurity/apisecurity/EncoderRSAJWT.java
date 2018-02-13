package org.beeva.apisecurity.apisecurity;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class EncoderRSAJWT {

	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;

	EncoderRSAJWT(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public String getJWT() {
		if (publicKey == null | privateKey == null)
			return null;
		String token = "";
		try {
			Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
			token = JWT.create().withIssuer("auth0").sign(algorithm);
		} catch (JWTCreationException e) {
		}
		return token;
	}
}
