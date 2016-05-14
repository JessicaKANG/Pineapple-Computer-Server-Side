package com.Pineapple.iframe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

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
import javax.swing.table.TableColumn;

import com.Pineapple.Dao.DBAddorder;
import com.Pineapple.Dao.DBCheckcomponent;
import com.Pineapple.Dao.model.Component;
import com.Pineapple.Dao.model.Order;

public class CheckDeadOrder extends JInternalFrame{
	private JTable table;
	private JTextField conditionContent;
	//private JComboBox conditionOperation;
	private JComboBox conditionName;
	public CheckDeadOrder() {
		super();//先构造一个内部窗口
		setIconifiable(true);//开启内部窗口最小化功能
		setClosable(true);//开启内部窗口关闭功能
		setTitle("查看订单");//设置窗口标题
		getContentPane().setLayout(new GridBagLayout());//窗口内部布局设置
		setBounds(100, 100, 600, 375);//窗口大小设置

		table = new JTable();//新建一个表
		table.setEnabled(false);//设置表格使能关闭，即不与用户交互
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//表格自动调整尺寸方式
		final DefaultTableModel dftm = new DefaultTableModel()//重写一个表格格式
				{
					public Class<?> getColumnClass(int column)
					{
						switch(column)
						{
						case 0:
							return String.class;
						case 1:
							return String.class;
						case 3:
							return String.class;
						case 2:
							return Timestamp.class;
						case 4:
							return String.class;
						case 5:
							return String.class;
							default:
								return String.class;
						}
					}
					boolean[] editables = {false,false,false, false,false,false};
					   public boolean isCellEditable(int row, int col)
					   {
					      return editables[col];
					   }
					
			
				};
		table.setModel(dftm);
		String[] tableHeads = new String[]{"订单状态", "单号", "日期", "总价", "支付方式","邮寄地址"};//添加表头
		dftm.setColumnIdentifiers(tableHeads);//把表头设置为每栏的标示
		for(int i=0;i<6;i++){
			TableColumn column = null;	
		    column = table.getColumnModel().getColumn(i);
		    if(i==0)column.setPreferredWidth(50);//状态栏小一点
		    else if(i==1)column.setPreferredWidth(150);//单号栏
		    else if(i==2)column.setPreferredWidth(100);//日期
		    else if(i==5)column.setPreferredWidth(150);//地址
		    else if(i==3)column.setPreferredWidth(70);//总价
		    else if(i==4)column.setPreferredWidth(100);//总价
		    
		}
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
		queryButton.setText("查询订单");
////////////////////////////////////////////////////////////////////////////////////////////
		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				List<Order> list = DBAddorder.getOrderList();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("全部订单");
		showAllButton.doClick();
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
	private void updateTable(List<Order> list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();//判断表有多少行
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);//把表中第i行现有内容去掉
		Iterator iterator = list.iterator();//创建一个迭代器，用于遍历链表
		int i=0;//代表行号
		while (iterator.hasNext()) {
			Order order = (Order) iterator.next();//获取链表中的元素
			dftm.addRow(new Object[0]);
			dftm.setValueAt(order.getID(), i, 1);
			dftm.setValueAt(order.getPrice(), i, 3);
			dftm.setValueAt(order.getDatetime(), i, 2);
			dftm.setValueAt(order.getPayment(), i, 4);
			dftm.setValueAt(order.getDelivery(), i, 5);
			dftm.setValueAt(order.getState(), i, 0);
			i++;	
		}
	}

}
