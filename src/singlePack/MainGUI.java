package singlePack;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JButton btnSelectTheFile;
	private JButton btnGenerate;
	private JButton btnVerify;

	private JLabel lblEnterTheReference;
	private JLabel lblAboutThisSoftware;
	private JLabel lblHelp;

	private JTextField textField1;

	private JScrollPane scrollPane;

	private JTextArea textArea;

	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception exc)
		{
		}
		// setDefaultLookAndFeelDecorated(true);

		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ButtonGroup radioButtonGroup = new ButtonGroup();

		textField1 = new JTextField();
		textField1.setBounds(14, 72, 350, 20);
		contentPane.add(textField1);
		textField1.setColumns(10);
		textField1.setEditable(false);
		textField1.setVisible(false);

		btnSelectTheFile = new JButton("Browse to select the file...");
		btnSelectTheFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int fileChooserState = fileChooser.showOpenDialog(MainGUI.this);
				if (fileChooserState == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = fileChooser.getSelectedFile();
					Path pathOfSelectedFile = selectedFile.toPath();
					textField1.setText(pathOfSelectedFile.toString());
					// textField1.setText(selectedFile.getPath());
					textField1.setVisible(true);
				}
				else if (fileChooserState == JFileChooser.ERROR_OPTION)
				{
					JOptionPane.showMessageDialog(null, "An error occurred when choosing file", "File selection error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSelectTheFile.setBounds(101, 42, 180, 27);
		contentPane.add(btnSelectTheFile);

		String[] comboItems = { "Select the algorithm", "MD2", "MD4", "MD5", "SHA1", "SHA256", "SHA384", "SHA512" };
		comboBox = new JComboBox(comboItems);
		comboBox.setBounds(123, 125, 142, 27);
		contentPane.add(comboBox);
		comboBox.setMaximumRowCount(5);

		lblEnterTheReference = new JLabel("If you want to verify, please enter the reference checksum:");
		lblEnterTheReference.setBounds(16, 194, 332, 14);
		contentPane.add(lblEnterTheReference);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 213, 354, 61);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		textArea = new JTextArea();
		String defaultTextInTextArea = "Use keyboard shortcuts to edit text here.\n(For example, press Ctrl+V to paste...)";
		textArea.setText(defaultTextInTextArea);
		textArea.setForeground(Color.GRAY);
		textArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0)
			{
				if (textArea.getText().equals(defaultTextInTextArea))
				{
					textArea.setText("");
					textArea.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0)
			{
				if (textArea.getText().equals(""))
				{
					textArea.setText(defaultTextInTextArea);
					textArea.setForeground(Color.GRAY);
				}
			}
		});
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);

		btnGenerate = new JButton("GENERATE");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{

				String filePathString = textField1.getText();
				String algorithmName = (String) comboBox.getSelectedItem();
				/*
				 * String algorithmName = null; Enumeration<AbstractButton>
				 * radioButtonList = radioButtonGroup.getElements(); while
				 * (radioButtonList.hasMoreElements()) { AbstractButton
				 * abstractButton = radioButtonList.nextElement(); if
				 * (abstractButton.isSelected()) { algorithmName =
				 * abstractButton.getText(); break; } }
				 */

				boolean fileAndAlgoInputsValidityStatus = InputValidator.validateFileAndAlgoInputs(filePathString, algorithmName, comboItems[0]);
				if (!fileAndAlgoInputsValidityStatus)
					return;

				setVisible(false);
				String generatedHash = HashGenerator.generateHash(filePathString, algorithmName);
				if (generatedHash == null)
				{
					setVisible(true);
					return;
				}

				new GenerationResultDialogue(filePathString, algorithmName, generatedHash);
				setVisible(true);
			}
		});
		btnGenerate.setBounds(37, 316, 148, 27);
		contentPane.add(btnGenerate);

		btnVerify = new JButton("GENERATE & VERIFY");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				String filePathString = textField1.getText();
				String algorithmName = (String) comboBox.getSelectedItem();
				String referenceHash = textArea.getText().replaceAll("\\s", "");

				boolean fileAndAlgoInputsValidityStatus = InputValidator.validateFileAndAlgoInputs(filePathString, algorithmName, comboItems[0]);
				if (!fileAndAlgoInputsValidityStatus)
					return;
				boolean referenceHashInputValidityStatus = InputValidator.validateReferenceHashInput(referenceHash, defaultTextInTextArea);
				if (!referenceHashInputValidityStatus)
					return;

				setVisible(false);
				String generatedHash = HashGenerator.generateHash(filePathString, algorithmName);
				if (generatedHash == null)
				{
					setVisible(true);
					return;
				}

				String result = HashComparator.compareHash(referenceHash, generatedHash);

				new VerificationResultDialogue(filePathString, algorithmName, referenceHash, generatedHash, result);
				setVisible(true);
			}
		});
		btnVerify.setBounds(195, 316, 148, 27);
		contentPane.add(btnVerify);

		lblAboutThisSoftware = new JLabel("<html><u>About this software</u></html>");
		lblAboutThisSoftware.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				new Info();
			}
		});
		lblAboutThisSoftware.setBounds(255, 393, 110, 14);
		contentPane.add(lblAboutThisSoftware);
		lblAboutThisSoftware.setFont(new Font("Georgia", Font.ITALIC, 12));
		lblAboutThisSoftware.setForeground(Color.BLUE);
		// lblAboutThisSoftware.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAboutThisSoftware.setCursor(new Cursor((Cursor.HAND_CURSOR)));

		lblHelp = new JLabel("<html><u>Help</u></html>");
		lblHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				try
				{
					// Desktop.getDesktop().open(new
					// File(getClass().getResource("/html_pages/Checksum_verifier_help.html").toURI()));
					// Desktop.getDesktop().browse(getClass().getResource("/html_pages/Checksum_verifier_help.html").toURI());
					Desktop.getDesktop().browse(new URI("https://google.com"));
					/*
					 * Path source = Paths.get(getClass().getResource(
					 * "/html_pages/Checksum_verifier_help.html").toURI()); Path
					 * target = Paths.get(System.getProperty("java.io.tmpdir"));
					 * Path temp = Files.copy(source,
					 * target.resolve(source.getFileName()),
					 * StandardCopyOption.REPLACE_EXISTING);
					 * temp.toFile().deleteOnExit();
					 * Desktop.getDesktop().browse(temp.toUri());
					 */
				}
				catch (IOException | URISyntaxException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cannot open help link", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblHelp.setBounds(13, 393, 46, 14);
		contentPane.add(lblHelp);
		lblHelp.setFont(new Font("Georgia", Font.ITALIC, 12));
		lblHelp.setForeground(Color.BLUE);
		lblHelp.setCursor(new Cursor((Cursor.HAND_CURSOR)));

		setTitle("ChecksumVerifier v1.0.12.9.2017");
		// setIconImage(new
		// ImageIcon("images/ChecksumVerifier_favicon.jpg").getImage());
		setIconImage(new ImageIcon(getClass().getResource("/images/Checksum_verifier_favicon.png")).getImage());
		setResizable(false);

	}
}