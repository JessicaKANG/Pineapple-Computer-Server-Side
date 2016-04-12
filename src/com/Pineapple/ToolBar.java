package com.Pineapple;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

import com.Pineapple.MenuBar;

public class ToolBar extends JToolBar {
	private MenuBar menuBar;
	/**
	 * 默认的构造方法
	 */
	private ToolBar() {
	}

	public ToolBar(MenuBar frameMenuBar) {
		super();
		this.menuBar = frameMenuBar;
		initialize();
	}

	/**
	 * 界面初始化方法
	 * 设置大小，加入各个按钮
	 */
	private void initialize() {
		setSize(new Dimension(600, 24));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		add(createToolButton(menuBar.getordertocheckItem()));
		add(createToolButton(menuBar.getcheckorderItem()));
		add(createToolButton(menuBar.geteditcomputerItem()));
		add(createToolButton(menuBar.getcheckcomputerItem()));
		add(createToolButton(menuBar.geteditstockItem()));
		add(createToolButton(menuBar.getcheckstockItem()));
		
	}

	/**
	 * 创建工具栏按钮的方法
	 * 名称、图标、监听全都来自菜单项
	 * @return javax.swing.JButton
	 */
	private JButton createToolButton(final JMenuItem item) {
		JButton button = new JButton();
		button.setText(item.getText());
		button.setToolTipText(item.getText());
		button.setIcon(item.getIcon());
		button.setFocusable(false);
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				item.doClick();
			}
		});
		return button;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}
}