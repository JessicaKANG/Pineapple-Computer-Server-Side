package com.Pineapple.login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.Pineapple.*;

//登录对话框类
public class LoginDialog extends JFrame {
	
	private String USERNAME = "Admin";
	private String PASSWORD = "1234";
	
	private static final long serialVersionUID = 1L;
	private LoginPanel loginPanel = null;
	private JLabel jLabel = null;
	private JTextField userField = null;
	private JLabel jLabel1 = null;
	private JPasswordField passwordField = null;
	private JButton loginButton = null;
	private JButton exitButton = null;
	private static String userStr;
	private MainFrame mainFrame;//主框架类

	
	//构造方法
	public LoginDialog() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//设置界面效果
			mainFrame = new MainFrame();//构造主框架
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 初始化loginPanel登录面板的方法, 向空面板添加6个组件
	 * 
	 * @return com.Pineapple.login.LoginPanel，返回登录面板
	 */
	private LoginPanel getLoginPanel() {
		if (loginPanel == null) {
			
			//label的初始化在此完成，因为不经常改动
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(86, 71, 80, 18));
			jLabel1.setText("Password：");//密码
			jLabel = new JLabel();
			jLabel.setText("Username：");//用户名
			jLabel.setBounds(new Rectangle(85, 41, 80, 18));
			
			loginPanel = new LoginPanel();//此时panel上只包含背景图片
			loginPanel.setLayout(null);//设置panel的布局方式
			loginPanel.setBackground(new Color(0xD8DDC7));
			
			//导入6个组件到面板，两个lable直接导入，其余用get方法导入
			loginPanel.add(jLabel, null);                
			loginPanel.add(getUserField(), null);
			loginPanel.add(jLabel1, null);
			loginPanel.add(getPasswordField(), null);
			loginPanel.add(getLoginButton(), null);
			loginPanel.add(getExitButton(), null);
		}
		return loginPanel;
	}
	
	/**
	 * This method initializes userField，初始化用户名输入区域
	 * 
	 * @return javax.swing.JTextField，返回用户输入区
	 */
	//如果输入区是空的，就重新构造输入区并设置大小
	private JTextField getUserField() {
		if (userField == null) {
			userField = new JTextField();
			userField.setBounds(new Rectangle(150, 39, 120, 22));
		}
		return userField;
	}
	
	/**
	 * This method initializes passwordField，初始化密码输入区
	 * 
	 * @return javax.swing.JPasswordField，返回密码输入区
	 */
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(new Rectangle(150, 69, 120, 22));
			//键盘监听密码输入区，当键入回车时执行等同于单击login按钮的动作
			passwordField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n')
						loginButton.doClick();
				}
			});
		}
		return passwordField;
	}
	
	/**
	 * This method initializes loginButton，初始化登录键
	 * 
	 * @return javax.swing.JButton，返回登录键
	 */
	private JButton getLoginButton() {
		if (loginButton == null) {
			loginButton = new JButton();
			loginButton.setBounds(new Rectangle(109, 114, 48, 20));
			loginButton.setIcon(new ImageIcon(getClass().getResource(
					"/res/loginButton.jpg")));//设置登录按钮图标
			//为登录按钮增加事件监听，监听方法是：提取用户名输入字符串；提取用户密码字符串；向数据库对比用户名和密码
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						userStr = userField.getText();
						String passStr = new String(passwordField
								.getPassword());
						/*
						//验证用户名
						if (!userStr.equals(USERNAME)){
							JOptionPane.showMessageDialog(LoginDialog.this,
									"Username Error", "Login failed",
									JOptionPane.ERROR_MESSAGE);
							return;
								
						}
						//验证密码
						if (!passStr.equals(PASSWORD)){
							JOptionPane.showMessageDialog(LoginDialog.this,
									"Password Error", "Login failed",
									JOptionPane.ERROR_MESSAGE);
							return;
								
						}*/
					
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					//以下操作和登录按钮有什么关系？
					mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);//主界面的小红叉执行退出操作
					mainFrame.setVisible(true);//显示主框架
					MainFrame.getCzyStateLabel().setText(userStr);
					setVisible(false);//关闭主框架
				}
			});
			
		}
		return loginButton;
	}
	
	/**
	 * This method initializes exitButton，初始化退出按钮
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setBounds(new Rectangle(181, 114, 48, 20));
			exitButton.setIcon(new ImageIcon(getClass().getResource(
					"/res/exitButton.jpg")));
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitButton;
	}
	
	/**
	 * 界面初始化方法,对loginframe进行一系列设置
	 * 
	 * @return void
	 */
	private void initialize() {
		Dimension size = getToolkit().getScreenSize();//提取屏幕尺寸
		setLocation((size.width - 296) / 2, (size.height - 188) / 2);//设定窗口位置为屏幕中央
		setSize(296, 188);//设置窗口大小
		this.setTitle("Login");//this指代本类,因为本类继承的JFrame
		setContentPane(getLoginPanel());//把loginpanel放到loginfram上
	}
	//获取用户名字符串的方法
	public String getUserStr() {
		return userStr;
	}
	
}
