package com.example.lixueqing.fingerprint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.security.KeyStore;

import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME="qbb";
    private Cipher cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public static void main(String[] args) {
        System.out.println("xixixi");
    }

}
