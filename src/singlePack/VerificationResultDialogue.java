package singlePack;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Paths;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class VerificationResultDialogue extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblTestedFile;
	private JLabel lblLocation;
	private JLabel lblGeneratedHash;
	private JLabel lblReferenceHash;
	private JLabel lblResult;
	private JLabel lblResultSentence;

	private JScrollPane scrollPaneTestedFile;
	private JScrollPane scrollPaneLocation;
	private JScrollPane scrollPaneGeneratedHash;
	private JScrollPane scrollPaneReferenceHash;

	private JTextArea textAreaFileName;
	private JTextArea textAreaLocation;
	private JTextArea textAreaGeneratedHash;
	private JTextArea textAreaReferenceHash;

	private JButton btnOk;
	private JButton btnCopyToClipboard2;
	private JButton btnCopyToClipboard1;

	VerificationResultDialogue(String filePathString, String algorithmName, String referenceHash, String generatedHash,
			String result)
	{
		setBounds(100, 100, 605, 432);
		getContentPane().setLayout(null);

		lblTestedFile = new JLabel("Tested file:");
		lblTestedFile.setBounds(15, 29, 110, 14);
		getContentPane().add(lblTestedFile);

		lblLocation = new JLabel("Location:");
		lblLocation.setBounds(15, 90, 110, 14);
		getContentPane().add(lblLocation);

		lblGeneratedHash = new JLabel("Generated " + algorithmName + ":");
		lblGeneratedHash.setBounds(15, 153, 110, 14);
		getContentPane().add(lblGeneratedHash);

		lblReferenceHash = new JLabel("Reference " + algorithmName + ":");
		lblReferenceHash.setBounds(15, 217, 110, 14);
		getContentPane().add(lblReferenceHash);

		lblResult = new JLabel("Result:");
		lblResult.setBounds(13, 281, 110, 30);
		getContentPane().add(lblResult);
		lblResult.setFont(new Font("Bookman Old Style", Font.BOLD, 20));

		scrollPaneReferenceHash = new JScrollPane();
		scrollPaneReferenceHash.setBounds(142, 211, 300, 40);
		getContentPane().add(scrollPaneReferenceHash);

		textAreaReferenceHash = new JTextArea(referenceHash);
		scrollPaneReferenceHash.setViewportView(textAreaReferenceHash);
		textAreaReferenceHash.setEditable(false);

		scrollPaneGeneratedHash = new JScrollPane();
		scrollPaneGeneratedHash.setBounds(142, 147, 300, 40);
		getContentPane().add(scrollPaneGeneratedHash);

		textAreaGeneratedHash = new JTextArea(generatedHash);
		scrollPaneGeneratedHash.setViewportView(textAreaGeneratedHash);
		textAreaGeneratedHash.setEditable(false);

		scrollPaneLocation = new JScrollPane();
		scrollPaneLocation.setBounds(142, 84, 441, 40);
		getContentPane().add(scrollPaneLocation);

		textAreaLocation = new JTextArea(Paths.get(filePathString).getParent().toString());
		scrollPaneLocation.setViewportView(textAreaLocation);
		textAreaLocation.setEditable(false);

		scrollPaneTestedFile = new JScrollPane();
		scrollPaneTestedFile.setBounds(142, 23, 441, 40);
		getContentPane().add(scrollPaneTestedFile);

		textAreaFileName = new JTextArea(Paths.get(filePathString).getFileName().toString());
		scrollPaneTestedFile.setViewportView(textAreaFileName);
		textAreaFileName.setEditable(false);

		lblResultSentence = new JLabel(algorithmName + " " + result + ".");
		lblResultSentence.setBounds(147, 281, 281, 30);
		getContentPane().add(lblResultSentence);
		lblResultSentence.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		if (result.equalsIgnoreCase("not matched"))
			lblResultSentence.setForeground(Color.RED);
		else
			lblResultSentence.setForeground(Color.GREEN);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnOk.setBounds(263, 350, 89, 23);
		getContentPane().add(btnOk);

		btnCopyToClipboard2 = new JButton("Copy to clipboard");
		btnCopyToClipboard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				StringSelection stringSelection = new StringSelection(textAreaReferenceHash.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		btnCopyToClipboard2.setBounds(451, 215, 132, 23);
		getContentPane().add(btnCopyToClipboard2);
		btnCopyToClipboard2.setToolTipText("Click to copy the reference " + algorithmName);

		btnCopyToClipboard1 = new JButton("Copy to clipboard");
		btnCopyToClipboard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				StringSelection stringSelection = new StringSelection(textAreaGeneratedHash.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		btnCopyToClipboard1.setBounds(451, 151, 132, 23);
		getContentPane().add(btnCopyToClipboard1);
		btnCopyToClipboard1.setToolTipText("Click to copy the generated " + algorithmName);

		setTitle(algorithmName + " verification result");
		setIconImage(new ImageIcon(getClass().getResource("/images/Checksum_verifier_favicon.png")).getImage());
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		requestFocus();
	}

}