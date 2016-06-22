package com.ljheee.socket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.ljh.password.SimpleEncrypy;
/**
 * �ͻ�����
 * @author ljheee
 *
 */
public class Client {
	
	private JTextArea jta = null;
	private JTextField jtf = null;
	private JPanel jPanel = null;
	BufferedReader in = null;
	PrintWriter out = null;
	String localHostName = null;
	String localIP = null;
	SimpleDateFormat sdf = null;
	
	JFrame jf = null;
	Socket s = null;//�ͻ���socket
	
	public Client() {
		
		sdf = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
		
		this.initGUI();
		this.initNet();
		
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			localHostName = inetAddress.getHostName(); //��ȡ��Client������������Ϊ���ǳơ�
			localIP = inetAddress.getHostAddress();
			new ReceiveTCP().start();
//			System.out.println(localHostName+"    "+localIP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//����ͻ���UI����
	public void initGUI(){
		
		jf = new JFrame("�ͻ���Client");
		jf.setSize(400,400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		
		jta = new JTextArea();
		jta.setEditable(false);
		
		jf.add(new JScrollPane(jta));
		
		jPanel = new JPanel();
		jtf = new JTextField(26);
		jf.add(jPanel,BorderLayout.SOUTH);
		jPanel.add(new JLabel("��Ҫ���ԣ�"));
		jPanel.add(jtf);
		
		jtf.addActionListener(new ActionListener() {//
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = jtf.getText().trim();
				String data  = SimpleEncrypy.encrypt(str);//����ǰ--���ܴ���
				jtf.setText("");
				out.println(data);
				out.flush();
			}
		});
		
		//�ͻ����˳�
//		jf.addWindowListener(new WindowAdapter() {
//
//			@Override
//			public void windowClosed(WindowEvent e) {
//				try {
//					s.close();	//��ǰ�ͻ��˹ر�ʱ----�رյ�ǰ�û�socket���Ҵӷ������б������
//					Server.sockets.remove(s);
//				//	System.exit(0);
//				} catch (IOException e1) {
//				}
//			}
//			
//		});
		
		jf.setVisible(true);
	}
	
	
	public void initNet(){
		
		try {
			s = new Socket("127.0.0.1",8888);//�½��ͻ���socket������ָ��IP�ģ�ָ���˿�
		
			out = new PrintWriter(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "����Serveʧ��..");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receive(){
		while(true){
			String str = null;
			String data = null;
			try {
				str = in.readLine();
				if(str==null)  return;
				data = SimpleEncrypy.deEncrypt(str);
				jta.append(localHostName+"��    "+sdf.format(new Date())+"\n"+"       "+data+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
		}
		
	}
	class ReceiveTCP extends Thread{
		@Override
		public void run() {
			receive();
		}
	}
	public static void main(String[] args) {
		new Client();
	}

}
