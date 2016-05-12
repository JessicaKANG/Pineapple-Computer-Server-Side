package com.Pineapple.iframe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.Pineapple.MainFrame;
import com.Pineapple.Dao.DBAddorder;
import com.Pineapple.Dao.model.Computer;
import com.Pineapple.Dao.model.Order;

public class CheckActiveOrder extends JInternalFrame{
	private JTable table;
	public CheckActiveOrder() {
		super();//先构造一个内部窗口
		setIconifiable(true);//开启内部窗口最小化功能
		setClosable(true);//开启内部窗口关闭功能
		setTitle("待处理订单");//设置窗口标题
		getContentPane().setLayout(new GridBagLayout());//窗口内部布局设置
		setBounds(100, 100, 600, 375);//窗口大小设置

		table = new JTable();//新建一个表
		table.setEnabled(true);//设置表格使能关闭，即不与用户交互
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//表格自动调整尺寸方式
		final DefaultTableModel dftm = new DefaultTableModel()//重写一个表格格式
				{
					public Class<?> getColumnClass(int column)
					{
						switch(column)
						{
						case 0:
							return Boolean.class;
						case 1:
							return String.class;
						case 2:
							return String.class;
						case 3:
							return Timestamp.class;
						case 4:
							return String.class;
						case 5:
							return String.class;
						case 6:
							return String.class;
							default:
								return String.class;
						}
					}
					boolean[] editables = {true,false,false,false, false,false,false};
					   public boolean isCellEditable(int row, int col)
					   {
					      return editables[col];
					   }
					
			
				};
		table.setModel(dftm);
		String[] tableHeads = new String[]{"选择","订单状态", "单号", "日期", "总价", "支付方式","邮寄地址"};//添加表头
		dftm.setColumnIdentifiers(tableHeads);//把表头设置为每栏的标示
		for(int i=0;i<7;i++){
			TableColumn column = null;	//把电脑名一栏画大一点	
		    column = table.getColumnModel().getColumn(i);
		    if(i==0)column.setPreferredWidth(30);
		    else if(i==1)column.setPreferredWidth(50);
		    else if(i==2)column.setPreferredWidth(150);
		    else if(i==5)column.setPreferredWidth(100);
		    else if(i==6)column.setPreferredWidth(150);
		    else if(i==3)column.setPreferredWidth(100);
		    
		}
		
		//把表格放置到有滚动条的面板上,控制表格位置
		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 9;
		gridBagConstraints_6.gridy = 0;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);
		//////////////////////////////////////////////////////////////////////////////////
		//添加table内容
		List<Order> orderList= DBAddorder.getActiveOrderList();
		updateTable(orderList,dftm);
		
////////////////////////////////////////////////////////////////////////////////////////////
		setupComponet(new JLabel("                    "
				+ "                                   "
				+ "                                   "
				+ "                       "), 2, 1, 2, 1, false);
		final JButton shipButton = new JButton();
		shipButton.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e){
				//遍历选中的订单
				  for(int j=0;j<table.getRowCount();j++){
					 Boolean checked = Boolean.valueOf(table.getValueAt(j,0).toString()) ;//检查该行是否被选中
					 if(checked){//如果被选中，则执行：则操作数据库改变订单状态；
						String id_order = table.getValueAt(j, 2).toString();
						DBAddorder.ChangeOrderState(id_order);							
					 }
				  }
				  List<Order> orderList= DBAddorder.getActiveOrderList();
					updateTable(orderList,dftm);
			}
			
		});
		setupComponet(shipButton, 7, 1, 1, 1, false);
		shipButton.setText("确认发货");
		
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
		gridBagConstrains.insets = new Insets(0, 1, 0, 1);
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
			dftm.setValueAt(false,i,0);
			dftm.setValueAt(order.getID(), i, 2);
			dftm.setValueAt(order.getPrice(), i, 4);
			dftm.setValueAt(order.getDatetime(), i, 3);
			dftm.setValueAt(order.getPayment(), i, 5);
			dftm.setValueAt(order.getDelivery(), i, 6);
			dftm.setValueAt(order.getState(), i, 1);
			i++;	
		}
	}
	
	

}
