
public class BitParser {
	
	public static String trim(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '0' || c == '1') {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static byte[] decode(String bits) {
		byte[] ret = new byte[bits.length() / 8];
		
		for (int i = 0; i < ret.length; i++) {
			int charByte = 0;
			for (int j = 0; j < 8; j++) {
				char c = bits.charAt(i*8+j);
				switch (c) {
					case '1':
						charByte = charByte * 2 + 1;
						break;
					case '0':
						charByte = charByte * 2;
						break;
					default:
						throw new IllegalArgumentException("unexpected character: " + c);
				}
			}
			
			ret[i] = (byte)(0xFF & charByte);
		}
		
		return ret;
	}
	
}
