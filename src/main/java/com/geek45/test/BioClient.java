package com.geek45.test;

import com.alibaba.fastjson.JSONObject;
import com.geek45.taobaosdk.Test;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BioClient {
    public static void main(String[] args) {
        try {
            Test test = new Test();
            Test.main(null);
//
//            Socket socket = new Socket("127.0.0.1", 10085);
//            System.err.println(LocalDateTime.now() + "连接成功");
//            socket.setKeepAlive(true);
////            socket.getOutputStream().write("我是客户端1".getBytes());
////            socket.close();
////            byte[] bytes = new byte[1024];
////            System.err.println("开始读取消息");
////            socket.getInputStream().read(bytes);
////            System.err.println("消息读取成功");
////            System.err.println(new String(bytes));
////            BioTest.receiveFile(socket);
//            new Thread(() -> {
//                BioTest.receiveForClient(socket);
//            }).start();
//            Scanner sc = new Scanner(System.in);
//            JSONObject object = new JSONObject();
//            object.put("user", "老钱");
//            while (true) {
//                System.err.println("请输入");
//                String msg = sc.nextLine();
//                object.put("msg", msg);
//                BioTest.sender(socket, object.toJSONString());
//            }
//            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//{'user':'qian','msg':'hello'}
