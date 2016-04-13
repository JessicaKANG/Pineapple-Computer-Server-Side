package com.Pineapple.iframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Pineapple.iframe.editstock.Addstockpanel;
import com.Pineapple.iframe.editstock.Fixstockpanel;

public class Editstock extends JInternalFrame{
	public Editstock() {
		setIconifiable(true);
		setClosable(true);
		setTitle("库存信息管理");
		JTabbedPane tabPane = new JTabbedPane();
		final Addstockpanel addPanel = new Addstockpanel();
		final Fixstockpanel fixPanel = new Fixstockpanel();
		tabPane.addTab("库存添加", null, addPanel, "库存添加");
		tabPane.addTab("修改与删除", null, fixPanel, "修改与删除");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//fixPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}

}
