package com.core.encryption;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

/*
Cifrado de clave privada o criptografia simétrica

1.Alice y Bob se reunen para acordar una clave secreta
2.Alice puede ahora utilizar la clave (simetrica,secreta,privada) para cifrar mensajes con un algoritmo de cifrado (Recomendado:AES)
3.el texto cifrado se envia a Bob. de manera que los atacantes no pueden extraer el texto original desde el texto cifrado.
4.bob descifra el texto cifrado con el algoritmo de cifrado con la misma clave secreta.
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
