package com.adama.sorho.redditClone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

@Configuration
public class JwtConfig {
	@Value("${app.keystore.path}")
	private String keyStorePath;

	@Value("${app.keystore.password}")
	private String keyStorePassword;

	@Value("${app.keystore.alias}")
	private String keyAlias;

	@Bean
	public PrivateKey jwtSigningKey() throws Exception {
		KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(getClass().getResourceAsStream(keyStorePath), keyStorePassword.toCharArray());

		return (PrivateKey) keyStore.getKey(keyAlias, keyStorePassword.toCharArray());
	}

	@Bean
	public PublicKey jwtVerificationKey() throws Exception {
		KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(getClass().getResourceAsStream(keyStorePath), keyStorePassword.toCharArray());
		Certificate certificate = keyStore.getCertificate(keyAlias);

		return certificate.getPublicKey();
	}
}
