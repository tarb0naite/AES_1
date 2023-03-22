import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.Base64;

public class CFBMode {
    private static final int IV_LENGTH = 16;
    public String encrypt(String plaintext, String encryptionKey)  {
        try {



            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[IV_LENGTH];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] ciphertextBytes = cipher.doFinal(plaintext.getBytes());
            String ciphertext = Base64.getEncoder().encodeToString(ciphertextBytes);
            String ivString = Base64.getEncoder().encodeToString(iv);


            byte[] outputBytes = cipher.doFinal(ciphertextBytes);
            String outputFile = "C:\\Users\\Karolina\\Desktop\\text\\outputCFB.txt";
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            outputFileStream.write(outputBytes);
            outputFileStream.close();



            return ivString + ":" + ciphertext;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String decrypt(String cipherText, String decryptionKey)  {
        try{
            String[] parts = cipherText.split(":");
            byte[] iv = Base64.getDecoder().decode(parts[0]);
            byte[] ciphertextBytes = Base64.getDecoder().decode(parts[1]);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec key = new SecretKeySpec(decryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
            String decryptedText = new String(decryptedBytes);
            return decryptedText;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    }

