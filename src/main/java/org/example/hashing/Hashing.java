package org.example.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    //https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
    /**
     * This method is inspired by the cryptocurrency hashing algorithm ( it is a simplified version of it)
     * This method encodes the password of a user to obtain a more secure form of it that can be stored
     * This method is also used at the login stage for comparing the given password with the one in store
     * @param passwordToHash - A string that represents the password of a user
     * @return  generatedPassword - A string that represents the encoding of the password after hash
     * @throws NoSuchAlgorithmException - exception occurs if there is no instance of the given algorithm name
     */
    public static String hashPassword(String passwordToHash) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        try{
             MessageDigest md = MessageDigest.getInstance("MD5");
             md.update(passwordToHash.getBytes());
             byte[] bytes = md.digest();

             StringBuilder sb = new StringBuilder();
             for(int i=0; i<bytes.length;i++){
                 sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
             }
             generatedPassword=sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
