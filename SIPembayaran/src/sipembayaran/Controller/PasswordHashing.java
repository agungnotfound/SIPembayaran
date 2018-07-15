/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipembayaran.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author YAS
 */
public class PasswordHashing {

    Map<String, String> YA = new HashMap<>();
    private String salt = "ya-salt-text";

    public PasswordHashing() {
    }

    public PasswordHashing(String salt) {
        this.salt = salt;
    }

    public String getHashedPassword(String password) {
        if (password.isEmpty()) {
            return null;
        }
        return generateHash(salt + password);
    }

    private void signup(String username, String password) {
        String saltedPassword = salt + password;
        String hashedPassword = getHashedPassword(password);
        YA.put(username, hashedPassword);
    }

    private Boolean checking(String username, String password){
        String storedPasswordHash = YA.get(username);
        String hashedPassword = getHashedPassword(password);
        return hashedPassword.equals(storedPasswordHash);
    }
    
    private static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] hashedByte = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedByte.length; ++idx) {
                byte b = hashedByte[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }

        } catch (NoSuchAlgorithmException e) {

        }
        return hash.toString();
    }
    
    public static void main(String args[]) {
        PasswordHashing demo = new PasswordHashing();
        demo.signup("haikal", "hai123");

        System.out.println("hash : "+demo.getHashedPassword("hai12345sadfasAsdfasd"));
        // login harusnya sukses
        if (demo.checking("haikal", "hai123")) {
            System.out.println("user login successfull.");
        }

        // login harusnya gagal
        if (demo.checking("haikal", "wkwkwkwk")) {
            System.out.println("User login successfull.");
        } else {
            System.out.println("user login failed.");
        }
    }
    

}
