import org.apache.commons.codec.binary.Base64;

public class TripleB64 {

	public TripleB64() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		String text = "Boku No Pico";
		byte[] data = text.getBytes("UTF-8");
		byte[] b64_1 = Base64.encodeBase64(data);
		byte[] b64_2 = Base64.encodeBase64(b64_1);
		byte[] b64_3 = Base64.encodeBase64(b64_2);
		System.out.println(text);
		System.out.println(new String(data));
		System.out.println(new String(b64_1));
		System.out.println(new String(b64_2));
		System.out.println(new String(b64_3));
	}

}
