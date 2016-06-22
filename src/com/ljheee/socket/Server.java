package com.ljheee.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

class ChatThread extends Thread{   //内部类，线程类
	Socket s = null;
	List<Socket> sockets = null;//所有客户端Socket的集合
	PrintWriter pw = null;
	public ChatThread(Socket s , List<Socket> sockets) {
		this.s=s;
		this.sockets = sockets;
	}
	
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true){
				String str = br.readLine();
				for (int i = 0; i < sockets.size(); i++) {
					Socket si = sockets.get(i);
					pw=new PrintWriter(si.getOutputStream());
					pw.println(str);
					pw.flush();
				}
			}
		} catch (IOException e) {
			//Client的socket关闭，读取流pw=new PrintWriter失败，抛异常
//			JOptionPane.showMessageDialog(null, "有一个客户端退出");
			e.printStackTrace();//若一个client关闭，此处会抛异常
			
			sockets.remove(s);
		}finally{	//此处不能将流关闭，因为如果其中有一个客户端socket关闭，抛异常，finally中关闭后，，其他多个客户端不能使用了
//			try {
//				br.close();
//				pw.close();
//			} catch (IOException e) {
//			}
		}
	}
	
}


public class Server {  //服务器类
	
	static List<Socket> sockets = null;
	
	public Server() {
		try {
			ServerSocket ss = new ServerSocket(8888);//服务器Socket，指定监听端口8888
			JOptionPane.showMessageDialog(null, "服务器已启动..等待客户端连接...");
			System.out.println("服务器已启动..等待客户端连接........");
			sockets = new ArrayList<Socket>();//所有客户端Socket的集合
		
		while(true){
			Socket s = ss.accept();//为每一个接收到的客户端，创建套接字
			sockets.add(s);//并添加到列表集合中
//			JOptionPane.showMessageDialog(null, "有一个客户端加入...");
			Thread thread = new ChatThread(s, sockets);
			thread.start();
			
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
		
	}

	

}
