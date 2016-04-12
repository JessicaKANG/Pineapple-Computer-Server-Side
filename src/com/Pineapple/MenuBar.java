package com.Pineapple;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;




public class MenuBar extends JMenuBar {
	/**
	 * （订单管理）菜单
	 */
	private JMenu order_Menu = null;

	/**
	 * （待处理订单）菜单项，位于（订单管理）菜单内
	 */
	private JMenuItem ordertocheckItem = null;

	/**
	 * （查看订单）菜单项，位于（订单管理）菜单内
	 */
	private JMenuItem checkorderItem = null;
//////////////////////////////////////////////////////////////////	
	/**
	 * （商品管理）菜单
	 */
	private JMenu computer_Menu = null;

	/**
	 * （商品资料管理）菜单项，位于（商品管理）菜单内
	 */
	private JMenuItem editcomputerItem = null;

	/**
	 * （查看商品）菜单项，位于（商品管理）菜单内
	 */
	private JMenuItem checkcomputerItem = null;
//////////////////////////////////////////////////////////////////
	
	/**
	 * （库存管理）菜单
	 */
	private JMenu stock_Menu = null;
	/**
	 * （库存调整）菜单项，位于（库存管理）菜单内
	 */
	private JMenuItem editstockItem = null;
	/**
	 * （查看库存）菜单项，位于（库存管理）菜单内
	 */
	private JMenuItem checkstockItem = null;
//////////////////////////////////////////////////////////////////
	/**
	 * 状态栏的内部窗体提示标签
	 */
	private JLabel stateLabel = null;
	/**
	 * 容纳内部窗体的桌面面板
	 */
	private JDesktopPane desktopPanel = null;
///////////////////////////////////////////////////////////////////

	/**
	 * 默认的构造方法
	 * 
	 */
	private MenuBar() {
	}
//初始化菜单栏界面的方法
	public MenuBar(JDesktopPane desktopPanel, JLabel label) {
		super();
		//iFrames = new HashMap<JMenuItem, JInternalFrame>(); 一个菜单项与内部窗口映射的哈希表
		this.desktopPanel = desktopPanel;
		//this.stateLabel = label;
		initialize();
	}

	/**
	 * 初始化菜单栏界面的方法
	 * 为菜单栏加入菜单
	 */
	private void initialize() {
		this.setSize(new Dimension(600, 24));
		add(getorder_Menu());
		add(getcomputer_Menu());
		add(getstock_Menu());
		
	}
//////////////////////////////////////////////////////////
	/**
	 * 初始化订单管理菜单的方法
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getorder_Menu() {
		if (order_Menu == null) {
			order_Menu = new JMenu();
			order_Menu.setText("订单管理");
			//jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//快捷键
			order_Menu.add(getordertocheckItem());
			order_Menu.add(getcheckorderItem());
		}
		return order_Menu;
	}

	/**
	 * 初始化（待处理订单）菜单项的方法 该方法定义菜单项打开待处理订单窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getordertocheckItem() {
		if (ordertocheckItem == null) {
			ordertocheckItem = new JMenuItem();
			ordertocheckItem.setText("待处理订单");
			ordertocheckItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/ordertocheck.png")));
			ordertocheckItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(ordertocheckItem, ordertocheck_IFrame.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return ordertocheckItem;
	}

	/**
	 * 初始化（查看订单）菜单项的方法，该方法定义菜单项打开（查看订单）窗体，并使窗体处于已选择状态。
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getcheckorderItem() {
		if (checkorderItem == null) {
			checkorderItem = new JMenuItem();
			checkorderItem.setText("查看订单");
			checkorderItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/checkorder.png")));
			checkorderItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							//createIFrame(checkorderItem, checkorder_IFrame.class);
						}
					});
		}
		return checkorderItem;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 初始化商品管理菜单的方法
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getcomputer_Menu() {
		if (computer_Menu == null) {
			computer_Menu = new JMenu();
			computer_Menu.setText("商品管理");
			//jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//快捷键
			computer_Menu.add(geteditcomputerItem());
			computer_Menu.add(getcheckcomputerItem());
		}
		return computer_Menu;
	}

	/**
	 * 初始化（商品资料管理）菜单项的方法 该方法定义菜单项打开商品资料管理窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem geteditcomputerItem() {
		if (editcomputerItem == null) {
			editcomputerItem = new JMenuItem();
			editcomputerItem.setText("商品资料管理");
			editcomputerItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/editcomputer.png")));
			editcomputerItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(editcomputerItem, editcomputer_IFrame.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return editcomputerItem;
	}

	/**
	 * 初始化（查看商品）菜单项的方法，该方法定义菜单项打开（查看商品）窗体，并使窗体处于已选择状态。
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getcheckcomputerItem() {
		if (checkcomputerItem == null) {
			checkcomputerItem = new JMenuItem();
			checkcomputerItem.setText("查看商品");
			checkcomputerItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/checkcomputer.png")));
			checkcomputerItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							//createIFrame(checkcomputerItem, checkcomputer_IFrame.class);
						}
					});
		}
		return checkcomputerItem;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 初始化库存管理菜单的方法
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getstock_Menu() {
		if (stock_Menu == null) {
			stock_Menu = new JMenu();
			stock_Menu.setText("库存管理");
			//jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//快捷键
			stock_Menu.add(geteditstockItem());
			stock_Menu.add(getcheckstockItem());
		}
		return stock_Menu;
	}

	/**
	 * 初始化（库存调整）菜单项的方法 该方法定义菜单项打开库存调整窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem geteditstockItem() {
		if (editstockItem == null) {
			editstockItem = new JMenuItem();
			editstockItem.setText("库存调整");
			editstockItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/editstock.png")));
			editstockItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(editstockItem, editstock_IFrame.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return editstockItem;
	}

	/**
	 * 初始化（库存盘点）菜单项的方法，该方法定义菜单项打开（库存盘点）窗体，并使窗体处于已选择状态。
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getcheckstockItem() {
		if (checkstockItem == null) {
			checkstockItem = new JMenuItem();
			checkstockItem.setText("库存盘点");
			checkstockItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/checkstock.png")));
			checkstockItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							//createIFrame(checkstockItem, checkstock_IFrame.class);
						}
					});
		}
		return checkstockItem;
	}
///////////////////////////////////////////////////////////////////////////////////////////////
	
	
}
