package com.next.network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// server socket을 만들고 클라이언트 socket 에서 접근
public class ServerClient {

    public static void main(String[] args) {

        Socket socket = null;

        OutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;

        InputStream inputStream = null;
        DataInputStream dataInputStream = null;

        Scanner scanner = null;

        try {
            socket = new Socket("localhost", 9000);
            System.out.println("서버 연결");
            System.out.println("socket : " + socket);

            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);

            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            scanner = new Scanner(System.in);

            while (true) {
                System.out.println("메세지 입력:");
                String outMsg = scanner.nextLine();
                dataOutputStream.writeUTF(outMsg); // 서버 쪽으로 입력 받은 메시지를 날린다.
                dataOutputStream.flush(); // 정확히 전송되라고 한번더 써줌

                String inMsg = dataInputStream.readUTF();
                System.out.println("inMsg : " + inMsg);

                if (outMsg.equals("STOP")) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
