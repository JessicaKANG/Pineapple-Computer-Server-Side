package com.Pineapple.iframe.editcomputer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class Addcomputerpanel extends JPanel {
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JComboBox comboboxType;
	private JComboBox comboboxColor;
	private JComboBox comboboxSize;
	private JComboBox comboboxStock;
	private JComboBox comboboxMemory;
	private JComboBox comboboxGraphics;
	private JComboBox comboboxProcessor;
	private JButton resetButton;
	
	public Addcomputerpanel(){
		
		super();//先构造一个panel
		
		//设置尺寸和布局，并将它显示出来
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		//所有的标签都设成final
		final JLabel computerID = new JLabel();
		computerID.setText("型号：");
		setupComponent(computerID, 0, 0, 1, 0, false);
		textFieldID = new JTextField();
		// 定位型号输入文本框
		setupComponent(textFieldID, 1, 0, 5, 200, true);
		////////////////////////////////////////////////////////
		final JLabel computerName = new JLabel("名称：");
		setupComponent(computerName, 0, 1, 1, 0, false);
		textFieldName = new JTextField();
		// 定位名称输入文本框
		setupComponent(textFieldName, 1, 1, 5, 100, true);
		////////////////////////////////////////////////////////
		final JLabel computerPrice = new JLabel();
		computerPrice.setText("价格：");
		setupComponent(computerPrice, 0, 2, 1, 0, false);
		textFieldPrice = new JTextField();
		// 定位价格输入文本框
		setupComponent(textFieldPrice, 1, 2, 2, 100, true);
		////////////////////////////////////////////////////////
		final JLabel computerType = new JLabel();
		computerType.setText("分类:");
		setupComponent(computerType,3,2,1,0,false);
		comboboxType = new JComboBox();
		comboboxType.setPreferredSize(new Dimension(120, 21));
		//initComboBox();// 初始化下拉选择框
		// 处理电脑分类的下拉选择框的选择事件
				comboboxType.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位电脑分类的下拉选择框
		setupComponent(comboboxType, 4, 2, 1, 0, true);
		//////////////////////////////////////////////////////////
		final JLabel computerConfig = new JLabel();
		computerConfig.setText("配置信息:");
		setupComponent(computerConfig,0,3,1,0,false);
		//////////////////////////////////////////////////////////
		final JLabel configColor = new JLabel();
		configColor.setText("颜色:");
		setupComponent(configColor,0,4,1,0,false);
		comboboxColor = new JComboBox();
		comboboxColor.setPreferredSize(new Dimension(120, 21));
		//initComboBox();// 初始化下拉选择框
		// 处理电脑分类的下拉选择框的选择事件
				comboboxColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位
		setupComponent(comboboxColor, 1, 4, 1, 0, true);
		//*********************************************
		final JLabel configSize = new JLabel();
		configSize.setText("尺寸:");
		setupComponent(configSize,3,4,1,0,false);
		comboboxSize = new JComboBox();
		comboboxSize.setPreferredSize(new Dimension(120, 21));
		//initComboBox();// 初始化下拉选择框
		// 处理电脑分类的下拉选择框的选择事件
				comboboxSize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位
		setupComponent(comboboxSize, 4, 4, 1, 0, true);

		//////////////////////////////////////////////////////////
		final JLabel configStock = new JLabel();
		configStock.setText("硬盘:");
		setupComponent(configStock,0,5,1,0,false);
		comboboxStock = new JComboBox();
		comboboxStock.setPreferredSize(new Dimension(120, 21));
		//initComboBox();// 初始化下拉选择框
		// 处理电脑分类的下拉选择框的选择事件
				comboboxStock.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位
		setupComponent(comboboxStock, 1, 5, 1, 0, true);
		//*********************************************
		final JLabel configMemory = new JLabel();
		configMemory.setText("内存:");
		setupComponent(configMemory,3,5,1,0,false);
		comboboxMemory = new JComboBox();
		comboboxMemory.setPreferredSize(new Dimension(120, 21));
		//initComboBox();// 初始化下拉选择框
		// 处理电脑分类的下拉选择框的选择事件
				comboboxMemory.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位
		setupComponent(comboboxMemory, 4, 5, 1, 0, true);
		//////////////////////////////////////////////////////////
		final JLabel configGraphics = new JLabel();
		configGraphics.setText("显卡:");
		setupComponent(configGraphics,0,6,1,0,false);
		comboboxGraphics = new JComboBox();
		comboboxGraphics.setPreferredSize(new Dimension(120, 21));
		//initComboBox();// 初始化下拉选择框
		// 处理电脑分类的下拉选择框的选择事件
				comboboxGraphics.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位
		setupComponent(comboboxGraphics, 1, 6, 1, 0, true);
		//*********************************************
		final JLabel configProcessor = new JLabel();
		configProcessor.setText("处理器:");
		setupComponent(configProcessor,3,6,1,0,false);
		comboboxProcessor = new JComboBox();
		comboboxProcessor.setPreferredSize(new Dimension(120, 21));
		//initComboBox();// 初始化下拉选择框
		// 处理电脑分类的下拉选择框的选择事件
				comboboxProcessor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位
		setupComponent(comboboxProcessor, 4, 6, 1, 0, true);
/////////////////////////////////////////////////////////////////////////////////////////////
		//添加按钮
		final JButton addButton = new JButton();
		//添加按钮的事件监听
		/*addButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (baoZhuang.getText().equals("")
						|| chanDi.getText().equals("")
						|| danWei.getText().equals("")
						|| guiGe.getText().equals("")
						|| jianCheng.getText().equals("")
						|| piHao.getText().equals("")
						|| wenHao.getText().equals("")
						|| quanCheng.getText().equals("")) {
					JOptionPane.showMessageDialog(ShangPinTianJiaPanel.this,
							"请完成未填写的信息。", "商品添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ResultSet haveUser = Dao
						.query("select * from tb_spinfo where spname='"
								+ quanCheng.getText().trim() + "'");
				try {
					if (haveUser.next()) {
						System.out.println("error");
						JOptionPane.showMessageDialog(
								ShangPinTianJiaPanel.this, "商品信息添加失败，存在同名商品",
								"客户添加信息", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (Exception er) {
					er.printStackTrace();
				}
		//添加按钮的数据库操作
				ResultSet set = Dao.query("select max(id) from tb_spinfo");
				String id = null;
				try {
					if (set != null && set.next()) {
						String sid = set.getString(1);
						if (sid == null)
							id = "sp1001";
						else {
							String str = sid.substring(2);
							id = "sp" + (Integer.parseInt(str) + 1);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				TbSpinfo spInfo = new TbSpinfo();
				spInfo.setId(id);
				spInfo.setBz(baoZhuang.getText().trim());
				spInfo.setCd(chanDi.getText().trim());
				spInfo.setDw(danWei.getText().trim());
				spInfo.setGg(guiGe.getText().trim());
				spInfo.setGysname(gysQuanCheng.getSelectedItem().toString()
						.trim());
				spInfo.setJc(jianCheng.getText().trim());
				spInfo.setMemo(beiZhu.getText().trim());
				spInfo.setPh(piHao.getText().trim());
				spInfo.setPzwh(wenHao.getText().trim());
				spInfo.setSpname(quanCheng.getText().trim());
				Dao.addSp(spInfo);
				JOptionPane.showMessageDialog(ShangPinTianJiaPanel.this,
						"商品信息已经成功添加", "商品添加", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();
			}
		});*/
		addButton.setText("添加");
		setupComponent(addButton, 1, 7, 1, 1, false);
		
		resetButton = new JButton();
		resetButton.setText("重置");
		setupComponent(resetButton, 4, 7, 1, 1, false);
		// 重添按钮的事件监听类
			/*	resetButton.addActionListener(new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						baoZhuang.setText("");
						chanDi.setText("");
						danWei.setText("");
						guiGe.setText("");
						jianCheng.setText("");
						beiZhu.setText("");
						piHao.setText("");
						wenHao.setText("");
						quanCheng.setText("");
					}
				});*/
				
		
		
	}
	/**
	 * 设置组件位置并添加到容器中的方法
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param ipadx
	 * @param fill
	 */
	private void setupComponent(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);//设置最小间距
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;//设置宽度
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;//横向加长文本框以满足输入需求
		add(component, gridBagConstrains);
		
		
	}
	

}
