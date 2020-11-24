package com.bankwithmint.inventorymanagement.utility;

import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
import org.jasypt.util.numeric.BasicDecimalNumberEncryptor;
import org.jasypt.util.numeric.BasicIntegerNumberEncryptor;

import java.io.DataOutputStream;
import java.math.BigDecimal;

public class EncryptionUtil {
    static BasicDecimalNumberEncryptor encryptor = new BasicDecimalNumberEncryptor();
    static {
        encryptor.setPassword("ibankwithmint");
    }

    public static BigDecimal encryptBigDecimal(BigDecimal amount){

        return encryptor.encrypt(amount);
    }

    public static BigDecimal decryptBigDecimal(BigDecimal encryptedAmount){
        return encryptor.decrypt(encryptedAmount);}
}
