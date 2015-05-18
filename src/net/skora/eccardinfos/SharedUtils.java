package net.skora.eccardinfos;

public class SharedUtils {
	
	final private static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
    static protected String formatBCDAmount(byte[] amount) {
		StringBuilder res = new StringBuilder(); 
		if (amount[0] != 0) res.append(Integer.toHexString(amount[0] >= 0 ? amount[0] : 256 + amount[0]));
		if (amount[1] == 0) {
			if (res.length() > 0) {
				res.append("00");
			} else {
				res.append("0");
			}
		} else {
			if (res.length() > 0 && amount[1] <= 9) {
				res.append("0");
			}
			res.append(Integer.toHexString(amount[1] >= 0 ? amount[1] : 256 + amount[1]));
		}
		res.append(",");
		String cents = Integer.toHexString(amount[2] >= 0 ? amount[2] : 256 + amount[2]);
		if (cents.length() == 1) res.append("0");
		res.append(cents);
		res.append("�");
		return res.toString();
    }
    
    static protected String parseLogState(byte logstate) {
		switch (logstate & 0x60 >> 5) {
		case 0: return new String("Laden");
		case 1: return new String("Entladen");
		case 2: return new String("Abbuchen");
		case 3: return new String("R�ckbuchen");
		}
		return new String("");
    }

	static protected String Byte2Hex(byte[] input) {
		return Byte2Hex(input, " ");
	}

	static protected String Byte2Hex(byte[] input, String space) {
		StringBuilder result = new StringBuilder();
		
		for (Byte inputbyte : input) {
			result.append(String.format("%02X" + space, inputbyte));
		}
		return result.toString();
	}

	protected static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = SharedUtils.hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = SharedUtils.hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
