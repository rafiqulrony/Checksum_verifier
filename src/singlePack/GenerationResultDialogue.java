package singlePack;

import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Paths;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class GenerationResultDialogue extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblTestedFile;
	private JLabel lblLocation;
	private JLabel lblGeneratedHash;

	private JScrollPane scrollPaneTestedFile;
	private JScrollPane scrollPaneLocation;
	private JScrollPane scrollPaneGeneratedHash;

	private JTextArea textAreaFileName;
	private JTextArea textAreaLocation;
	private JTextArea textAreaGeneratedHash;

	private JButton btnOk;
	private JButton btnCopyToClipboard;

	GenerationResultDialogue(String filePathString, String algorithmName, String generatedHash)
	{
		setBounds(100, 100, 569, 314);
		getContentPane().setLayout(null);

		lblTestedFile = new JLabel("Tested file:");
		lblTestedFile.setBounds(15, 28, 110, 14);
		getContentPane().add(lblTestedFile);

		lblLocation = new JLabel("Location:");
		lblLocation.setBounds(15, 89, 110, 14);
		getContentPane().add(lblLocation);

		lblGeneratedHash = new JLabel("Generated " + algorithmName + ":");
		lblGeneratedHash.setBounds(15, 152, 110, 14);
		getContentPane().add(lblGeneratedHash);

		scrollPaneGeneratedHash = new JScrollPane();
		scrollPaneGeneratedHash.setBounds(142, 147, 266, 40);
		getContentPane().add(scrollPaneGeneratedHash);

		textAreaGeneratedHash = new JTextArea(generatedHash);
		scrollPaneGeneratedHash.setViewportView(textAreaGeneratedHash);
		textAreaGeneratedHash.setEditable(false);

		scrollPaneLocation = new JScrollPane();
		scrollPaneLocation.setBounds(142, 84, 406, 40);
		getContentPane().add(scrollPaneLocation);

		textAreaLocation = new JTextArea(Paths.get(filePathString).getParent().toString());
		scrollPaneLocation.setViewportView(textAreaLocation);
		textAreaLocation.setEditable(false);

		scrollPaneTestedFile = new JScrollPane();
		scrollPaneTestedFile.setBounds(142, 23, 406, 40);
		getContentPane().add(scrollPaneTestedFile);

		textAreaFileName = new JTextArea(Paths.get(filePathString).getFileName().toString());
		scrollPaneTestedFile.setViewportView(textAreaFileName);
		textAreaFileName.setEditable(false);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnOk.setBounds(240, 241, 89, 23);
		getContentPane().add(btnOk);

		btnCopyToClipboard = new JButton("Copy to clipboard");
		btnCopyToClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				StringSelection stringSelection = new StringSelection(textAreaGeneratedHash.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		btnCopyToClipboard.setBounds(417, 150, 132, 23);
		getContentPane().add(btnCopyToClipboard);
		btnCopyToClipboard.setToolTipText("Click to copy the generated " + algorithmName);

		setTitle(algorithmName + " generation completed");
		setIconImage(new ImageIcon(getClass().getResource("/images/Checksum_verifier_favicon.png")).getImage());
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		requestFocus();
	}
}