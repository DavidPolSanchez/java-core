package com.core.encryption;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

/*

 */
public class SymmetricMain {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String plainText = "Hola mundo!";

        // 1. Generador de claves
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);

        // 2. Crear clave privada
        SecretKey key = keyGen.generateKey();

        // 3. Crear Cipher (encriptador/cifrador)
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // 4. Configuración Cipher con Vector de inicialización (IV) / nonce
        byte[] iv = new byte[cipher.getBlockSize()];
        SecureRandom random = SecureRandom.getInstanceStrong();
        random.nextBytes(iv);

        int bits = cipher.getBlockSize() * 8;
        GCMParameterSpec gcmParam = new GCMParameterSpec(bits, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmParam); // MODO CIFRADO

        // 5. Realizar cifrado
        byte[] cipherBytes = cipher.doFinal(plainText.getBytes());
        String cipherText = HexFormat.of().formatHex(cipherBytes);
        System.out.println(cipherText);

        // 6. Descrifrar
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParam); // MODO DESCIFRADO
        byte[] plainBytes = cipher.doFinal(cipherBytes);
        String plainText2 = new String(plainBytes);
        System.out.println(plainText2);

    }

}
