package by.training.interpol.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private static final String ALGORITHM = "MD5";
    private static final String DEFAULT_CHARACTER_TO_FILL = "0";
    private static final int PASSWORD_RADIX = 16;
    private static final int PASSWORD_LENGTH = 32;
    private static final int OFFSET_FOR_INSERTING = 0;
    private static final int PASSWORD_SIGNUM = 1;

    public static String encodePassword(String password) throws EncodePasswordException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();

            BigInteger bigInt = new BigInteger(PASSWORD_SIGNUM, digest);
            StringBuilder md5HexPassword = new StringBuilder(bigInt.toString(PASSWORD_RADIX));

            while( md5HexPassword.length() < PASSWORD_LENGTH ){
                md5HexPassword.insert(OFFSET_FOR_INSERTING, DEFAULT_CHARACTER_TO_FILL);
            }

            return md5HexPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new EncodePasswordException("Can't encode password exception", e);
        }
    }
}
