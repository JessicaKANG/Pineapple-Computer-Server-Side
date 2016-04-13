package com.Pineapple.iframe.editcomponent;

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

public class Fixcomponentpanel extends JPanel{
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JComboBox comboboxType;
	
	private JButton modifyButton;
	private JButton deleteButton;
	private JComboBox comboboxSelect;


	
	public Fixcomponentpanel(){
			
			super();//先构造一个panel
			
			//设置尺寸和布局，并将它显示出来
			setBounds(10, 10, 460, 300);
			setLayout(new GridBagLayout());
			setVisible(true);
			
			//所有的标签都设成final
			final JLabel componentID = new JLabel();
			componentID.setText("型号：");
			setupComponent(componentID, 0, 0, 1, 0, false);
			textFieldID = new JTextField();
			// 定位型号输入文本框
			setupComponent(textFieldID, 1, 0, 5, 200, true);
			////////////////////////////////////////////////////////
			final JLabel componentName = new JLabel("名称：");
			setupComponent(componentName, 0, 1, 1, 0, false);
			textFieldName = new JTextField();
			// 定位名称输入文本框
			setupComponent(textFieldName, 1, 1, 5, 100, true);
			////////////////////////////////////////////////////////
			final JLabel componentPrice = new JLabel();
			componentPrice.setText("价格：");
			setupComponent(componentPrice, 3, 2, 1, 0, false);
			textFieldPrice = new JTextField();
			// 定位价格输入文本框
			setupComponent(textFieldPrice, 4, 2, 1, 100, true);
			final JLabel componentType = new JLabel();
			componentType.setText("分类:");
			setupComponent(componentType,0,2,1,0,false);
			comboboxType = new JComboBox();
			comboboxType.setPreferredSize(new Dimension(120, 21));
			//initComboBox();// 初始化下拉选择框
			// 处理配件分类的下拉选择框的选择事件
					comboboxType.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//doKeHuSelectAction();
						}
					});
			// 定位电脑分类的下拉选择框
			setupComponent(comboboxType, 1, 2, 1, 0, true);
			
			
			final JLabel componentSelect = new JLabel();
			componentSelect.setText("选择配件:");
			setupComponent(componentSelect,0,3,1,0,false);
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
			setupComponent(comboboxSelect, 1, 3, 5, 0, true);
			
			
			
			modifyButton = new JButton();
			modifyButton.setText("修改");
			setupComponent(modifyButton, 1, 4, 1, 1, false);
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
			setupComponent(deleteButton, 4, 4, 1, 1, false);
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
