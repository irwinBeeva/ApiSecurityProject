package org.beeva.apisecurity.apisecurity;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class DecoderRSAJWT {

	private RSAPublicKey publickey;
	private RSAPrivateKey privateKey;
	
	DecoderRSAJWT(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
		this.publickey = publicKey;
		this.privateKey = privateKey;
	}
	
	public DecodedJWT decodeJWT(String token) {
		DecodedJWT jwt = null;
		try {
			Algorithm algorithm = Algorithm.RSA256(publickey, privateKey);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer("auth0")
					.build();
			jwt = verifier.verify(token);	
		} catch (JWTVerificationException e) {}
		return jwt;
	}
}
