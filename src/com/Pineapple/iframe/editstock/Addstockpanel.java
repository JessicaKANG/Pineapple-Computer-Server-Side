package com.Pineapple.iframe.editstock;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Pineapple.Dao.DBAddcomputer;
import com.Pineapple.Dao.DBAddstock;
import com.Pineapple.Dao.model.Computer;
import com.Pineapple.Dao.model.Stock;
import com.Pineapple.iframe.editcomputer.Addcomputerpanel;

public class Addstockpanel extends JPanel{
	private JTextField textFieldID;
	private JTextField textFieldAmount;
	private JTextField textFieldTypeID;
	private JComboBox comboboxType;

	private JButton resetButton;
	
	private Stock stock;
	private String idtype;
	private String type;
	private int number;
	
	
	public Addstockpanel(){
		
		super();//先构造一个panel
		
		//设置尺寸和布局，并将它显示出来
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		//所有的标签都设成final
		//final JLabel stockID = new JLabel();
		//stockID.setText("型号：");
		//setupComponent(stockID, 0, 0, 1, 0, false);
		//textFieldID = new JTextField();
		// 定位型号输入文本框
		//setupComponent(textFieldID, 1, 0, 5, 250, true);
		////////////////////////////////////////////////////////
		final JLabel stockAmount = new JLabel("库存量：");
		setupComponent(stockAmount, 0, 1, 1, 0, false);
		textFieldAmount = new JTextField();
		// 定位存量输入文本框
		setupComponent(textFieldAmount, 1, 1, 5, 100, true);
		////////////////////////////////////////////////////////
		
		textFieldTypeID = new JTextField();
		// 定位
		setupComponent(textFieldTypeID, 3, 0, 3, 100, true);
		final JLabel stockType = new JLabel();
		stockType.setText("型号:");
		setupComponent(stockType,0,0,1,0,false);
		comboboxType = new JComboBox();
		comboboxType.setPreferredSize(new Dimension(120, 21));
		comboboxType.setModel(new DefaultComboBoxModel(new String[]{"Component",
				"Computer"}));
		//initComboBox();// 初始化下拉选择框
		// 处理库存分类的下拉选择框的选择事件
				comboboxType.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//doKeHuSelectAction();
					}
				});
		// 定位电脑分类的下拉选择框
		setupComponent(comboboxType, 1, 0, 1, 0, true);
	/////////////////////////////////////////////////////////////////////////////
		//添加按钮
				final JButton addButton = new JButton();
				//添加按钮的事件监听
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						if (textFieldAmount.getText().equals("")
								|| textFieldTypeID.getText().equals("")) {
							JOptionPane.showMessageDialog(Addstockpanel.this,
									"请完成未填写的信息。", "库存添加", JOptionPane.ERROR_MESSAGE);
							return;
						}
						Stock stock = new Stock();
						try {
							idtype = textFieldTypeID.getText();
							type = comboboxType.getSelectedItem().toString();
							if(type.equals("Computer")){
								stock.setIdcpr(textFieldTypeID.getText());
								if (!DBAddstock.existsCMR(idtype)) {
								JOptionPane.showMessageDialog(Addstockpanel.this,
										"该商品不存在", "添加库存失败",
										JOptionPane.WARNING_MESSAGE);
								return;
									}
								if (DBAddstock.exists(idtype)) {
									JOptionPane.showMessageDialog(Addstockpanel.this,
											"该商品库存信息已存在", "添加库存失败",
											JOptionPane.WARNING_MESSAGE);
									return;
									}
							}
							else{
								stock.setIdcpt(textFieldTypeID.getText());
								if (!DBAddstock.existsCMT(idtype)) {
									JOptionPane.showMessageDialog(Addstockpanel.this,
											"该配件不存在", "添加库存失败",
											JOptionPane.WARNING_MESSAGE);
									return;
									}
								if (DBAddstock.exist(idtype)) {
									JOptionPane.showMessageDialog(Addstockpanel.this,
											"该配件库存信息已存在", "添加库存失败",
											JOptionPane.WARNING_MESSAGE);
									return;
									}
								
							}
								
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				//添加按钮的数据库操作
						
						
						stock.setNumber(Integer.parseInt(textFieldAmount.getText()));
						
						
						try {					
							if (DBAddstock.save(stock)) {
								JOptionPane.showMessageDialog(Addstockpanel.this,
										"恭喜，库存信息已成功添加", "库存信息添加成功",
										JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				addButton.setText("添加");
				setupComponent(addButton, 1, 7, 1, 1, false);
				
				resetButton = new JButton();
				resetButton.setText("重置");
				setupComponent(resetButton, 4, 7, 1, 1, false);
				// 重添按钮的事件监听类
						resetButton.addActionListener(new ActionListener() {
							public void actionPerformed(final ActionEvent e) {
								textFieldTypeID.setText("");
								textFieldAmount.setText("");
								
							}
						});
		
		
		
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
