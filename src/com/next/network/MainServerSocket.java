package com.next.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServerSocket {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = null;

        OutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;

        InputStream inputStream = null;
        DataInputStream dataInputStream = null;

        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("클라이언트 맞을 준비 완료!");

            socket = serverSocket.accept(); // accept() 가 소켓을 받아오는 역할
            System.out.println("클라이언트 연결!!");
            System.out.println("socket : " + socket);

            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);


            while (true) {
                String clientMsg = dataInputStream.readUTF(); // client에서 전송한 메세지를 읽음
                System.out.println("clientMsg : " + clientMsg);

                dataOutputStream.writeUTF("메세지 전송 완료-server에서 수신함");
                dataOutputStream.flush();

                if (clientMsg.equals("STOP")) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
