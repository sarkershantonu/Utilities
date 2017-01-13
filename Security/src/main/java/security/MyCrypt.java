package security;

import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class MyCrypt {
	private static byte[] AESKey = "%ms9!sZpP#0_q3&m".getBytes();

	private static byte[][] encryptCipher = {{1,2,3},{4,5,6}};//sample cypher 
	private static byte[][] decryptCipher = {{1,2,3},{4,5,6}};//sample dcypher 
	private static int[] swap = { 7, 3, 4, 1, 0, 5, 2, 6 };

	public static String encode2(String password) throws Exception {
		return MadCrypt.encode(MadCrypt.encode(password));
	}
	private static String encode(String password) throws Exception {
		if (password == null || password.equals("")) {
			return "";
		}

		Cipher AESCipher = Cipher.getInstance("AES");
		AESCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(MadCrypt.AESKey, "AES"));
		return "~" + new String(Base64.getEncoder().encode(AESCipher.doFinal(password.getBytes())));
	}
	public static String decode2(String password) throws Exception {
		return MadCrypt.decode(MadCrypt.decode(password));
	}

	private static String decode(String password) throws Exception {
		if (password == null || password.equals("")) {
			return "";
		}

		if (password.charAt(0) == '~') {
			Cipher AESCipher = Cipher.getInstance("AES");
			AESCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(MadCrypt.AESKey, "AES"));
			return new String(AESCipher.doFinal(Base64.getDecoder().decode(password.substring(1))));
		}

		byte[] initialBuf = Base64.getDecoder().decode(password);

		int len = MadCrypt.removeAt(initialBuf, initialBuf.length / 2 - 1) - 33;

		MadCrypt.unswap(initialBuf);

		int end = MadCrypt.removeAt(initialBuf, len / 2 - 1) - 98;
		end = (end < 0) ? MadCrypt.encryptCipher.length - 1 : end;

		byte[] finalBuf = new byte[len - 1];

		for (int i = len - 2; i >= 0; i--) {
			if (i % 2 == 0) {
				if (Character.isLowerCase((char) initialBuf[i])) {
					initialBuf[i] = (byte) (Character.toUpperCase((char) initialBuf[i]));
				} else if (Character.isUpperCase((char) initialBuf[i])) {
					initialBuf[i] = (byte) (Character.toLowerCase((char) initialBuf[i]));
				}
			}
			finalBuf[i] = (byte) (MadCrypt.decryptCipher[end][initialBuf[i] - 33] + 33);
			if (--end < 0) {
				end = 25;
			}
		}
		return new String(finalBuf);
	}

	private static void insertAt(byte[] a, byte b, int pos) {
		if (pos < 0)
			pos = 0;
		else if (pos > a.length - 1)
			pos = a.length - 1;

		for (int i = a.length - 1; i > pos; --i) {
			a[i] = a[i - 1];
		}
		a[pos] = b;
	}

	private static byte removeAt(byte[] a, int pos) {
		if (pos < 0)
			pos = 0;
		else if (pos > a.length - 1)
			pos = a.length - 1;
		byte b = a[pos];
		for (int i = pos; i < a.length - 1; ++i) {
			a[i] = a[i + 1];
		}
		a[a.length - 1] = (byte) 0;
		return b;
	}

	private static byte[] padTo(byte[] in, int len) {
		Random r = new Random();
		byte a[] = new byte[len];
		int i;
		for (i = 0; i < in.length; ++i) {
			a[i] = in[i];
		}
		while (i < a.length) {
			a[i++] = (byte) (r.nextInt(93) + 33);
		}
		return a;
	}

	private static void swap(byte[] in) {
		int i = 0;
		while (i <= in.length - MadCrypt.swap.length) {
			for (int j = i; j < i + MadCrypt.swap.length; j++) {
				byte save = in[j];
				int o = i + MadCrypt.swap[j % MadCrypt.swap.length];
				in[j] = in[o];
				in[o] = save;
			}
			i += MadCrypt.swap.length / 2;
		}
	}

	private static void unswap(byte[] in) {
		int i = in.length - 1;
		while (i - MadCrypt.swap.length >= 0) {
			int stop = i - MadCrypt.swap.length;
			for (int j = i - 1; j >= stop; j--) {
				byte save = in[j];
				int o = stop + MadCrypt.swap[j % MadCrypt.swap.length];
				in[j] = in[o];
				in[o] = save;
			}
			i -= MadCrypt.swap.length / 2;
		}
	}


	public static void test(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java com.att.eai.util.MadCrypt (-e|-d|-e2|-d2) password");
			System.exit(1);
		}

		try {
			if (args[0].startsWith("-e2")) {
				System.out.println(MadCrypt.encode2(args[1]));
			} else if (args[0].startsWith("-d2")) {
				System.out.println(MadCrypt.decode2(args[1]));
			} else if (args[0].startsWith("-e")) {
				System.out.println(MadCrypt.encode(args[1]));
			} else if (args[0].startsWith("-d")) {
				System.out.println(MadCrypt.decode(args[1]));
			} else {
				System.err.println("usage: java com.att.eai.util.MadCrypt (-encode|-decode) password");
				System.exit(1);
			}
			System.exit(0);
		} catch (Exception e) {
			System.err.println("MadCrypt operation failed.");
			System.err.println(e.toString());
			System.exit(1);
		}
	}
}
