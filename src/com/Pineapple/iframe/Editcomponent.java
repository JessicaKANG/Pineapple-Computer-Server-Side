package com.Pineapple.iframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Pineapple.iframe.editcomponent.Addcomponentpanel;
import com.Pineapple.iframe.editcomponent.Fixcomponentpanel;

public class Editcomponent extends JInternalFrame{
	public Editcomponent() {
		setIconifiable(true);
		setClosable(true);
		setTitle("配件信息管理");
		JTabbedPane tabPane = new JTabbedPane();
		final Addcomponentpanel addPanel = new Addcomponentpanel();
		final Fixcomponentpanel fixPanel = new Fixcomponentpanel();
		tabPane.addTab("配件添加", null, addPanel, "配件添加");
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
