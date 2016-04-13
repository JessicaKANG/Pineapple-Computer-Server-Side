package com.Pineapple.iframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Pineapple.iframe.editcomputer.Addcomputerpanel;
import com.Pineapple.iframe.editcomputer.Fixcomputerpanel;



public class Editcomputer extends JInternalFrame{
	public Editcomputer() {
		setIconifiable(true);
		setClosable(true);
		setTitle("商品信息管理");
		JTabbedPane tabPane = new JTabbedPane();
		final Addcomputerpanel addPanel = new Addcomputerpanel();
		final Fixcomputerpanel fixPanel = new Fixcomputerpanel();
		tabPane.addTab("商品添加", null, addPanel, "商品添加");
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
