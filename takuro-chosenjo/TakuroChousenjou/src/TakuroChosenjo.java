
public class TakuroChosenjo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String kadai = "010010000110010101101100011011000110111100101100010101110110111101110010011011000110010000100001";
		
		byte[] parsed = BitParser.decode(kadai);
		String answer = new String(parsed);
		System.out.println(answer);

	}

}
