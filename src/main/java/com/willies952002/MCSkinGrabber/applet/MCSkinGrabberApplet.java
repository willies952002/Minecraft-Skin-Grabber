package com.willies952002.MCSkinGrabber.applet;

import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MCSkinGrabberApplet extends JApplet {

	private static final long serialVersionUID = 1296760920539720631L;
	private JTextField textField;

	/**
	 * Create the applet.
	 */
	public MCSkinGrabberApplet() {
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		getContentPane().add(lblUsername, "2, 2, right, default");
		
		textField = new JTextField();
		getContentPane().add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, "2, 4, 3, 1, fill, default");
		
		JButton btnNewButton = new JButton("New button");
		getContentPane().add(btnNewButton, "2, 6, 3, 1");
		
	}
}
