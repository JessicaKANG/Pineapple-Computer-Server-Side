package com.Pineapple.iframe.editcomputer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fixcomputerpanel extends JPanel{
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
	private JComboBox comboboxSelect;
	
	private JButton modifyButton;
	private JButton deleteButton;
	
	public Fixcomputerpanel(){
		
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
		///////////////////////////////////////////////////////////////
		final JLabel configSelect = new JLabel();
		configSelect.setText("选择商品:");
		setupComponent(configSelect,0,7,1,0,false);
		comboboxSelect = new JComboBox();
		comboboxSelect.setPreferredSize(new Dimension(120, 21));
		//initComboBox();// 初始化下拉选择框
		// 处理电脑分类的下拉选择框的选择事件
				comboboxSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位
		setupComponent(comboboxSelect, 1, 7, 5, 0, true);
		
		modifyButton = new JButton();
		modifyButton.setText("修改");
		setupComponent(modifyButton, 1, 8, 1, 1, false);
		// 修改按钮的事件监听类
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
		deleteButton = new JButton();
		deleteButton.setText("删除");
		setupComponent(deleteButton, 4, 8, 1, 1, false);
		// 删除按钮的事件监听类
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
