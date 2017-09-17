package singlePack;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Info extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblVersion;
	private JLabel lblDeveloper;
	private JLabel lblWebsite;
	private JLabel lblThanks;

	Info()
	{
		setBounds(100, 100, 380, 300);
		getContentPane().setLayout(null);

		lblVersion = new JLabel("ChecksumVerifier (version 1.0.12.9.2017)");
		lblVersion.setBounds(20, 23, 243, 14);
		getContentPane().add(lblVersion);

		String developerString = "<html>DEVELOPER:<br>Md. Rafiqul Islam<br>rafiqul.rony123@gmail.com</html>";
		lblDeveloper = new JLabel(developerString);
		lblDeveloper.setBounds(20, 55, 160, 44);
		getContentPane().add(lblDeveloper);

		lblThanks = new JLabel("THANKS for using this software.");
		lblThanks.setBounds(116, 183, 186, 14);
		getContentPane().add(lblThanks);

		lblWebsite = new JLabel("github.com/rafiqulrony");
		lblWebsite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					Desktop.getDesktop().browse(new URI("https://github.com/rafiqulrony"));
				}
				catch (IOException | URISyntaxException e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Cannot open link", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblWebsite.setBounds(20, 102, 187, 14);
		getContentPane().add(lblWebsite);
		lblWebsite.setCursor(new Cursor((Cursor.HAND_CURSOR)));
		lblWebsite.setForeground(Color.BLUE);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		btnOk.setBounds(146, 226, 89, 23);
		getContentPane().add(btnOk);

		setTitle("About this software");
		setIconImage(new ImageIcon(getClass().getResource("/images/Checksum_verifier_favicon.png")).getImage());
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
