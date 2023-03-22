import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Test extends JFrame implements ActionListener {
    private JPanel panel;
    private JComboBox<String> modeDropdown;
    private JLabel modeLabel;
    private JTextField plaintextField;
    private JLabel plaintextLabel;

    private JTextArea keytextField;
    private JLabel keytextLabel;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextArea ciphertextArea;
    private JLabel ciphertextLabel;

    private JTextArea decryptedtextArea;
    private JLabel decryptedtextLabel;

    public Test() {
        super("AES Encryption");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        modeLabel = new JLabel("Encryption mode:");
        panel.add(modeLabel);
        String[] modes = {"ECB", "CBC", "OFB", "CFB", "CTR"};
        modeDropdown = new JComboBox<>(modes);
        panel.add(modeDropdown);

        plaintextLabel = new JLabel("Plaintext:");
        panel.add(plaintextLabel);
        plaintextField = new JTextField(20);
        panel.add(plaintextField);

        keytextLabel = new JLabel("Key: (Just 16 charts)");
        panel.add(keytextLabel);
        keytextField = new JTextArea(2,20);
        panel.add(keytextField);

        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);
        panel.add(encryptButton);

        ciphertextLabel = new JLabel("Ciphertext:");
        panel.add(ciphertextLabel);
        ciphertextArea = new JTextArea(5, 20);
        ciphertextArea.setEditable(true);
        panel.add(ciphertextArea);

        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this);
        panel.add(decryptButton);

        decryptedtextLabel = new JLabel("Decrypted:");
        panel.add(decryptedtextLabel);
        decryptedtextArea = new JTextArea(5, 20);
       decryptedtextArea.setEditable(false);
        panel.add(decryptedtextArea);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String mode = (String) modeDropdown.getSelectedItem();
        String plaintext = plaintextField.getText();
        String ciphertext = "";
        String encryptionKey = keytextField.getText();
        String cipherkey = "";

        if (encryptButton.getText().equals("Encrypt")) {
            if (mode.equals("ECB")) {
                ECBMode ecb = new ECBMode();
                ciphertext = ecb.encrypt(plaintext,encryptionKey);
            } else if (mode.equals("CBC")) {
                CBCMode cbc = new CBCMode();
                ciphertext = cbc.encrypt(plaintext,encryptionKey);
            } else if (mode.equals("OFB")) {
                OFBMode ofb = new OFBMode();
                ciphertext = ofb.encrypt(plaintext,encryptionKey);
            } else if (mode.equals("CFB")) {
                CFBMode cfb = new CFBMode();
                ciphertext = cfb.encrypt(plaintext,encryptionKey );
            } else if (mode.equals("CTR")) {
                 CTRMode ctr = new CTRMode();
                ciphertext = ctr.encrypt(plaintext, encryptionKey);
            }
            ciphertextArea.setText(ciphertext);
            encryptButton.setText("Decrypt");
            decryptedtextArea.setText("");

        } else if (encryptButton.getText().equals("Decrypt")) {
            String cipherText = ciphertextArea.getText();
            String c = " ";
            String decryptionKey = keytextField.getText();
            String cipherkeyD = "";
            if (mode.equals("ECB")) {
                ECBMode ecbMode = new ECBMode();
                c = ecbMode.decrypt(cipherText, decryptionKey);
                decryptedtextArea.setText(c);
            }else if(mode.equals("CBC")){
                CBCMode cbcMode = new CBCMode();
                c = cbcMode.decrypt(cipherText, decryptionKey);
                decryptedtextArea.setText(c);
            }else if(mode.equals("CFB")){
                CFBMode cfbMode = new CFBMode();
                c = cfbMode.decrypt(cipherText, decryptionKey);
                decryptedtextArea.setText(c);
            }else if(mode.equals("OFB")){
                OFBMode ofbMode = new OFBMode();
                c = ofbMode.decrypt(cipherText, decryptionKey);
                decryptedtextArea.setText(c);
            }else if(mode.equals("CTR")){
               CTRMode ctrMode = new CTRMode();
                c = ctrMode.decrypt(cipherText, decryptionKey);
                decryptedtextArea.setText(c);
            }
        }
    }


            public static void main(String[] args) {
        Test gui = new Test();

    }
}