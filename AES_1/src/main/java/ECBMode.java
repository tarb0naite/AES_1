import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;

public class ECBMode {
    public String encrypt(String plaintext, String encryptionKey)  {
        try {

            SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertextBytes = cipher.doFinal(plaintext.getBytes());
            String ciphertext = Base64.getEncoder().encodeToString(ciphertextBytes);


            byte[] outputBytes = cipher.doFinal(ciphertextBytes);
            String outputFile = "C:\\Users\\Karolina\\Desktop\\text\\outputECB.txt";
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            outputFileStream.write(outputBytes);
            outputFileStream.close();




            return ciphertext;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String cipherText, String decryptionKey)  {
        try {
            SecretKeySpec key = new SecretKeySpec(decryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] ciphertextBytes = Base64.getDecoder().decode(cipherText);
            byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
            String decryptedText = new String(decryptedBytes);
            return decryptedText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
