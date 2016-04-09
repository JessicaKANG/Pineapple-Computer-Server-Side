package com.Pineapple.login;

import static org.junit.Assert.*;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SplashScreen;
import javax.swing.*;
import org.junit.Test;

import com.Pineapple.login.LoginDialog;

public class TestLogin {

	private static final int EXIT_ON_CLOSE = 1;

	@Test
	public void test1() {	
		LoginPanel p = new LoginPanel();
	}
	
	@Test
	public void test2(){
		
		SplashScreen splashScreen = SplashScreen.getSplashScreen();//？？获取闪屏对象
		JFrame login = new LoginDialog();
		//如果不闪屏的话执行以下操作
		if (splashScreen != null) {
			try {
				login.setDefaultCloseOperation(EXIT_ON_CLOSE);
				Thread.sleep(3000);//？线程睡眠
			} catch (InterruptedException e) {
			}
		}
		login.setVisible(true);//使login界面可见
	}
	
	
	
	

}

