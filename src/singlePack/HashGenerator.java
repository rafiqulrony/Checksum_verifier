package singlePack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

class HashGenerator {

	static String generateHash(String filePathString, String algorithmName)
	{
		/*
		 * An immediate double quote(") after the backslash(\) is considered as
		 * part of string value.
		 */
		String command = "certutil -hashfile \"" + filePathString + "\" " + algorithmName;
		String generatedHash = null;
		try
		{
			// Process process = Runtime.getRuntime().exec("cmd /c " + command);
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			/*
			 * getInputStream() returns the input stream connected to the normal
			 * output of the subprocess. The stream obtains data piped from the
			 * standard output of the process.
			 */
			for (int i = 0; i <= 1; i++)
				generatedHash = bufferedReader.readLine();
		}
		catch (IOException | InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return generatedHash;
		}

		return generatedHash.replaceAll("\\s", "");
	}

}
