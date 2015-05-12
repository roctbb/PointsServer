/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.messageboard.entities;

/**
 *
 * @author roctbb
 */
public class User {
    String name;
    String hash;

    public String getName() {
        return name;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
    int r,g,b;
    public String MD5(String md5) {
            try {
                 java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                 byte[] array = md.digest(md5.getBytes());
                 StringBuffer sb = new StringBuffer();
                     for (int i = 0; i < array.length; ++i) {
                           sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                         }
                    return sb.toString();
                } catch (java.security.NoSuchAlgorithmException e) {
             }
             return null;
    }
    public User(String namer) {
        hash = MD5(namer);
        name = namer;
        r = (int)(Math.random()*255);
        g = (int)(Math.random()*255);
        b = (int)(Math.random()*255);
    }
    public String getHash()
    {
        return hash;
    }
    
    
}
