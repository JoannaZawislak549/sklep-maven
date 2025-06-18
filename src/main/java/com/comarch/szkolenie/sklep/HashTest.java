package com.comarch.szkolenie.sklep;


import org.apache.commons.codec.digest.DigestUtils;

public class HashTest {
    public static void main(String[] args) {
        String password = "admindwzl{MVT+-b9%aYLAnbeRz!&=KCePCJk5K8cpo5!Mzl9dnC%z'";
        String hash = DigestUtils.md5Hex(password);
        System.out.println(hash);
    }
}
