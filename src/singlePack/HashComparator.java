package singlePack;

class HashComparator {

	static String compareHash(String referenceHash, String generatedHash)
	{
		String result;
		if (generatedHash.equalsIgnoreCase(referenceHash))
			result = "matched";
		else
			result = "not matched";

		return result;
	}
}