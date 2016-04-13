package com.Pineapple.iframe.editcomputer;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class Test extends JPanel {
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldPrice;

	/**
	 * Create the panel.
	 */
	public Test() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblComputerid = new JLabel("ID:");
		GridBagConstraints gbc_lblComputerid = new GridBagConstraints();
		gbc_lblComputerid.anchor = GridBagConstraints.EAST;
		gbc_lblComputerid.insets = new Insets(0, 0, 5, 5);
		gbc_lblComputerid.gridx = 0;
		gbc_lblComputerid.gridy = 0;
		add(lblComputerid, gbc_lblComputerid);
		
		textFieldID = new JTextField();
		GridBagConstraints gbc_textFieldID = new GridBagConstraints();
		gbc_textFieldID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldID.gridx = 1;
		gbc_textFieldID.gridy = 0;
		add(textFieldID, gbc_textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);
		
		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 1;
		add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 2;
		add(lblType, gbc_lblType);
		
		JComboBox comboBoxType = new JComboBox();
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxType.gridx = 1;
		gbc_comboBoxType.gridy = 2;
		add(comboBoxType, gbc_comboBoxType);
		
		JLabel lblPrice = new JLabel("Price:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 3;
		add(lblPrice, gbc_lblPrice);
		
		JLabel label = new JLabel("$");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 3;
		add(label, gbc_label);
		
		textFieldPrice = new JTextField();
		GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
		gbc_textFieldPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrice.gridx = 1;
		gbc_textFieldPrice.gridy = 4;
		add(textFieldPrice, gbc_textFieldPrice);
		textFieldPrice.setColumns(5);

	}

}
