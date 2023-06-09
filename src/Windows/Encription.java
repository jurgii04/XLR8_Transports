package Windows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encription {
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

    /*public static void main(String[] args) {
        Encription encription = new Encription();
        //System.out.println(encription.encriptar("jefe123"));
        //System.out.println(encription.encriptar("bus123"));
        //System.out.println(encription.encriptar("reparto123"));
        System.out.println(encription.encriptar(""));
    }*/
}
