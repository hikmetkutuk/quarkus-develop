package com.develop.security;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@ApplicationScoped
public class PBKDF2Encoder {
    @ConfigProperty(name = "jwt.password.secret")
    String secret;
    @ConfigProperty(name = "jwt.password.iteration")
    Integer iteration;
    @ConfigProperty(name = "jwt.password.keylength")
    Integer keylength;

    /**
     * Encodes the given character sequence using PBKDF2 with HmacSHA512 algorithm.
     *
     * @param  cs  the character sequence to be encoded
     * @return     the encoded string
     */
    public String encode(CharSequence cs) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keylength))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }
}
