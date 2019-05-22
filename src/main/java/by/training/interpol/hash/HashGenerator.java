package by.training.interpol.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private static final String ALGORITHM = "MD5";

    public static String encodePassword(String password) throws EncodePasswordException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();

            BigInteger bigInt = new BigInteger(1, digest);
            StringBuilder md5HexPassword = new StringBuilder(bigInt.toString(16));

            while( md5HexPassword.length() < 32 ){
                md5HexPassword.insert(0, "0");
            }

            return md5HexPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new EncodePasswordException("Can't encode password exception", e);
        }
    }
}
