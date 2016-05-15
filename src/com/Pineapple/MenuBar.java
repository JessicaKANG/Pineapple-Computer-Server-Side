package com.Pineapple;
import com.Pineapple.iframe.*;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
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
	 * （配件管理）菜单
	 */
	private JMenu component_Menu = null;

	/**
	 * （配件资料管理）菜单项，位于（配件管理）菜单内
	 */
	private JMenuItem editcomponentItem = null;

	/**
	 * （查看配件）菜单项，位于（配件管理）菜单内
	 */
	private JMenuItem checkcomponentItem = null;
//////////////////////////////////////////////////////////////////
	/**
	 * 状态栏的内部窗体提示标签
	 */
	private JLabel stateLabel = null;
	/**
	 * 容纳内部窗体的桌面面板
	 */
	private JDesktopPane desktopPanel = null;
	/**
	 * 内部窗体的集合（菜单项，内部窗体）映射哈希表
	 */
	private Map<JMenuItem, JInternalFrame> iFrames = null;
	/**
	 * 内部窗体的位置坐标
	 */
	private int nextFrameX, nextFrameY;

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
		iFrames = new HashMap<JMenuItem, JInternalFrame>(); //一个菜单项与内部窗口映射的哈希表
		this.desktopPanel = desktopPanel;
		this.stateLabel = label;
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
		add(getcomponent_Menu());
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
					"/res/icon/ordertocheck.jpg")));
			ordertocheckItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(ordertocheckItem, CheckActiveOrder.class);//鼠标监听，打开内部窗口
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
					"/res/icon/checkorder.jpg")));
			checkorderItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							createIFrame(checkorderItem, CheckDeadOrder.class);
						}
					});
		}
		return checkorderItem;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 初始化配件管理菜单的方法
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getcomponent_Menu() {
		if (component_Menu == null) {
			component_Menu = new JMenu();
			component_Menu.setText("配件管理");
			//jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//快捷键
			component_Menu.add(geteditcomponentItem());
			component_Menu.add(getcheckcomponentItem());
		}
		return component_Menu;
	}

	/**
	 * 初始化（配件资料管理）菜单项的方法 该方法定义菜单项打开配件资料管理窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem geteditcomponentItem() {
		if (editcomponentItem == null) {
			editcomponentItem = new JMenuItem();
			editcomponentItem.setText("配件资料管理");
			editcomponentItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/editcomponent.jpg")));
			editcomponentItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(editcomponentItem, Editcomponent.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return editcomponentItem;
	}

	/**
	 * 初始化（查看配件）菜单项的方法，该方法定义菜单项打开（查看配件）窗体，并使窗体处于已选择状态。
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getcheckcomponentItem() {
		if (checkcomponentItem == null) {
			checkcomponentItem = new JMenuItem();
			checkcomponentItem.setText("查看配件");
			checkcomponentItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/checkcomponent.jpg")));
			checkcomponentItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							createIFrame(checkcomponentItem, Checkcomponent.class);
						}
					});
		}
		return checkcomponentItem;
	}
	//////////////////////////////////////////////////////////////////////////////////////////
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
					"/res/icon/editcomputer.jpg")));
			editcomputerItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(editcomputerItem, Editcomputer.class);//鼠标监听，打开内部窗口
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
					"/res/icon/checkcomputer.jpg")));
			checkcomputerItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							createIFrame(checkcomputerItem, Checkcomputer.class);
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
					"/res/icon/editstock.jpg")));
			editstockItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(editstockItem, Editstock.class);//鼠标监听，打开内部窗口
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
					"/res/icon/checkstock.jpg")));
			checkstockItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							createIFrame(checkstockItem, Checkstock.class);
						}
					});
		}
		return checkstockItem;
	}

///////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 创建内部窗体的方法，该方法使用反射技术获取内部窗体的构造方法，从而创建内部窗体。
	 * 
	 * @param item：激活该内部窗体的菜单项
	 * @param clazz：内部窗体的Class对象
	 */
	private JInternalFrame createIFrame(JMenuItem item, Class clazz) {
		Constructor constructor = clazz.getConstructors()[0];//获取内部窗体的构造器
		JInternalFrame iFrame = iFrames.get(item);//以菜单项为key，查找对应的内部窗体
		try {
			//如果不存在当前窗体，或者窗体处于关闭状态，就执行以下操作
			if (iFrame == null || iFrame.isClosed()) {
				iFrame = (JInternalFrame) constructor
						.newInstance(new Object[] {});//使用内部窗体构造器，构造一个新窗体
				iFrames.put(item, iFrame);//把新窗体至于内部窗体哈希表中
				iFrame.setFrameIcon(item.getIcon());//内部窗体图标取自菜单项图标
				iFrame.setLocation(nextFrameX, nextFrameY);//设置内部窗体位置
				////////////////////////////////////////////////////////////////////////////////
				int frameH = iFrame.getPreferredSize().height;//获取内部窗体高度
				int panelH = iFrame.getContentPane().getPreferredSize().height;//获取桌面高度
				//设置下一个内部窗口打开位置，避免窗口重叠看不到的现象
				int fSpacing = frameH - panelH;
				nextFrameX += fSpacing;
				nextFrameY += fSpacing;
				if (nextFrameX + iFrame.getWidth() > desktopPanel.getWidth())
					nextFrameX = 0;
				if (nextFrameY + iFrame.getHeight() > desktopPanel.getHeight())
					nextFrameY = 0;
				/////////////////////////////////////////////////////////////////////////////////
				desktopPanel.add(iFrame);//把内部窗口放到桌面上
				iFrame.setResizable(false);//内部窗体大小不可改变
				iFrame.setMaximizable(false);//内部窗体不能最大化
				iFrame.setVisible(true);//使内部窗体可见
			}
			//如果已存在当前窗体，或者执行完创建操作后，就执行以下操作
			iFrame.setSelected(true);//使当前窗体处于被选中状态
			stateLabel.setText(iFrame.getTitle());//把状态栏中的标签设置成当前窗体名字
			//为当前内部窗体添加监听
			iFrame.addInternalFrameListener(new InternalFrameAdapter() {
				/**
				 * 内部窗体的激活方法，获取内部窗口，并将状态栏标签设置为内部窗口名称
				 */
				public void internalFrameActivated(InternalFrameEvent e) {
					super.internalFrameActivated(e);
					JInternalFrame frame = e.getInternalFrame();
					stateLabel.setText(frame.getTitle());
				}
				/**
				 * 内部窗体的最小化方法，状态栏标签更改为“未选择窗口”
				 */
				public void internalFrameDeactivated(InternalFrameEvent e) {
					stateLabel.setText("No window choosed");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iFrame;
	}

	
}
