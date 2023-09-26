package com.example.shoappinglist.services.socket;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;

public class SocketClient extends Thread {

    public SocketClient(){
        this.serve();
    }
    private Socket clientSocket = null;
    protected BufferedReader in;
    protected PrintWriter out = null;


    private void serve() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket("10.0.2.2", 666);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

        });

    }
    public void send(String msg) {

        AsyncTask.execute(new Runnable() {
            @Override
                public void run(){
                    out.println(msg);
                try {
                    String response = in.readLine();
                    System.out.println(response);
                    if(response.equals("ADIOS")) clientSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
