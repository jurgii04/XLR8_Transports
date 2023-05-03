package Windows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encription {
    public String encriptar(String password) {
        StringBuilder stringBuilder;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] result = messageDigest.digest();
            stringBuilder = new StringBuilder();
            for (byte i : result) {
                stringBuilder.append(String.format("%02x", i));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return String.valueOf(stringBuilder);
    }

    public static void main(String[] args) {
        encription encription = new encription();
        System.out.println(encription.encriptar("pepe"));
    }
}
