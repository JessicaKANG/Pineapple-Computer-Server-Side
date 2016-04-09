package com.Pineapple.login;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

//登陆面板类，面板中包含背景图片
public class LoginPanel extends JPanel {
	
	public int width, height;
	private Image img;
	
	//面板类的构造方法
	public LoginPanel() {
		super();//先集成JPanel类的构造方法
		URL url = getClass().getResource("/res/login.jpg");//导入背景图片的统一资源定位符（URL）
		img = new ImageIcon(url).getImage();//由URL获得Icon（图标），再由Icon获得图片
	}
	//这是个什么方法
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}