package com.Pineapple.iframe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.Pineapple.Dao.DBCheckcomponent;
import com.Pineapple.Dao.DBCheckcomputer;
import com.Pineapple.Dao.model.Component;
import com.Pineapple.Dao.model.Computer;

public class Checkcomponent extends JInternalFrame{
	private JTable table;
	private JTextField conditionContent;
	//private JComboBox conditionOperation;
	private JComboBox conditionName;
	public Checkcomponent() {
		super();//先构造一个内部窗口
		setIconifiable(true);//开启内部窗口最小化功能
		setClosable(true);//开启内部窗口关闭功能
		setTitle("配件信息查询");//设置窗口标题
		getContentPane().setLayout(new GridBagLayout());//窗口内部布局设置
		setBounds(100, 100, 600, 375);//窗口大小设置

		table = new JTable();//新建一个表
		table.setEnabled(false);//设置表格使能关闭，即不与用户交互
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//表格自动调整尺寸方式
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();//表格模型强制转换为向量模型
		String[] tableHeads = new String[]{"型号", "名称", "类型", "价格"};//添加表头
		dftm.setColumnIdentifiers(tableHeads);//把表头设置为每栏的标示
		
		//把表格放置到有滚动条的面板上,控制表格位置
		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);
//查询器//////////////////////////////////////////////////////////////////////////
		setupComponet(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"型号",
				"名称", "类型"}));
		setupComponet(conditionName, 1, 0, 1, 30, true);

	/*	conditionOperation = new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"等于",
				"包含"}));
		setupComponet(conditionOperation, 2, 0, 1, 30, true);*/

		conditionContent = new JTextField();
		setupComponet(conditionContent, 2, 0, 2, 140, true);

		final JButton queryButton = new JButton();
		//queryButton.addActionListener(new QueryAction(dftm));
		setupComponet(queryButton, 4, 0, 1, 1, false);
		queryButton.setText("查询");
////////////////////////////////////////////////////////////////////////////////////////////
		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				List<Component> list = DBCheckcomponent.select();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("显示全部配件");
	}
	
	/**
	 * 添加组件方法
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param ipadx
	 * @param fill
	 */
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
	
	/**
	 * 把数据库查询结果表投放到TABLE模型中
	 * @param list
	 * @param dftm
	 */
	private void updateTable(List<Component> list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();//判断表有多少行
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);//把表中第i行现有内容去掉
		Iterator iterator = list.iterator();//创建一个迭代器，用于遍历链表
		while (iterator.hasNext()) {
			Component component = (Component) iterator.next();//获取链表中的元素
			Vector rowData = new Vector();
			rowData.add(component.getId());
			rowData.add(component.getName());
			rowData.add(component.getType());
			rowData.add(component.getPrice());
			dftm.addRow(rowData);
		}
	}
	

}
