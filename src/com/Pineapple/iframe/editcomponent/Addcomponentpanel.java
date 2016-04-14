package com.Pineapple.iframe.editcomponent;

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

import com.Pineapple.Dao.DBAddcomponent;
import com.Pineapple.Dao.DBAddcomputer;
import com.Pineapple.Dao.model.Component;
import com.Pineapple.Dao.model.Computer;
import com.Pineapple.iframe.editcomputer.Addcomputerpanel;

public class Addcomponentpanel extends JPanel{
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JComboBox comboboxType;

	private JButton resetButton;
	
	
	private Component component;
	private String id;
	private String name;
	private String type;
	private float price;
	
public Addcomponentpanel(){
		
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
		comboboxType.setModel(new DefaultComboBoxModel(new String[]{"Stock",
				"Memory", "Graphics","Color","Screen","Processor"}));
		// 定位配件分类的下拉选择框
		setupComponent(comboboxType, 1, 2, 1, 0, true);
	/////////////////////////////////////////////////////////////////////////////
		//添加按钮
				final JButton addButton = new JButton();
				//添加按钮的事件监听
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						if (textFieldID.getText().equals("")
								|| textFieldName.getText().equals("")
								|| textFieldPrice.getText().equals("")) {
							JOptionPane.showMessageDialog(Addcomponentpanel.this,
									"请完成未填写的信息。", "配件添加", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						try {
							id = textFieldID.getText();
							
							if (DBAddcomputer.exists(id)) {
								JOptionPane.showMessageDialog(Addcomponentpanel.this,
										"该型号已存在", "添加配件失败",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				//添加按钮的数据库操作
						Component component = new Component();
						component.setId(textFieldID.getText());
						component.setName(textFieldName.getText());
						component.setType(comboboxType.getSelectedItem().toString());
						component.setPrice(Float.parseFloat(textFieldPrice.getText()));
						
						try {					
							if (DBAddcomponent.save(component)) {
								JOptionPane.showMessageDialog(Addcomponentpanel.this,
										"恭喜，配件已成功添加入库", "配件添加成功",
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
						textFieldID.setText("");
						textFieldName.setText("");
						textFieldPrice.setText("");
						
						
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
