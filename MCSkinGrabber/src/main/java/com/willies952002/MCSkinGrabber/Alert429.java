package com.willies952002.MCSkinGrabber;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Alert429 extends JDialog {

	private static final long serialVersionUID = -6264207855588099877L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Alert429() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblHttpError = new JLabel("HTTP ERROR 429");
			lblHttpError.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblHttpError, "2, 2");
		}
		{
			JLabel label = new JLabel("Too Many Requsts Have Been Made To The Mojang Public API");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(label, "2, 4");
		}
		{
			JLabel lblPleaseTryAgain = new JLabel("Please Try Again At A Later Time");
			lblPleaseTryAgain.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblPleaseTryAgain, "2, 6");
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonPane.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.GROWING_BUTTON_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,},
					new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC,
						RowSpec.decode("23px"),
						FormFactory.RELATED_GAP_ROWSPEC,}));
			}
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton, "2, 2, fill, fill");
			getRootPane().setDefaultButton(okButton);
		}
	}

}
