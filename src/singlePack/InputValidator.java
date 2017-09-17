package singlePack;

import javax.swing.JOptionPane;

class InputValidator {

	static boolean validateFileAndAlgoInputs(String filePathString, String algorithmName, String firstItemInCombo)
	{
		if (filePathString.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please select a file.", "Incomplete input", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (algorithmName.equals(firstItemInCombo))
		{
			JOptionPane.showMessageDialog(null, "Please select an algorithm.", "Incomplete input", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		return true;
	}

	static boolean validateReferenceHashInput(String referenceHash, String defaultTextInTextArea)
	{
		if (referenceHash.equals(defaultTextInTextArea.replaceAll("\\s", "")))
		{
			JOptionPane.showMessageDialog(null, "Please provide the reference checksum.", "Incomplete input", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!(referenceHash.matches("[a-zA-Z0-9]+")))
		{
			JOptionPane.showMessageDialog(null, "The reference checksum you provided is not valid. Please provide a valid checksum.", "Invalid reference checksum", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		return true;
	}
}
