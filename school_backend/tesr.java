import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class tesr {
    public static void main(String[]args){

        try{
        String secretkey="";

        KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey key = keygen.generateKey();

        // Convert to Base64 string
         secretkey = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("Base64 Secret Key: " + secretkey);
        }
        catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }




    }
    
}
