package com.example.shoappinglist.services.socket;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class SocketClient extends Thread {

    public SocketClient(){
        this.serve();
    }


    private void serve() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Socket clientSocket = null;
                try {
                    clientSocket = new Socket("10.0.2.2", 666);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                PrintWriter out = null;
                try {
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                out.println("ANDROID CONNECTION");
                out.println("HOLA DESDE ANDROID VÍA PUERTO 666");
                out.println("ADIOS 666");
                out.println("se fini");

                try {
                    clientSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
}
