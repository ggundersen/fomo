package fomo.core;

import org.mindrot.jbcrypt.BCrypt;

public class Password {

	private final static int WORKLOAD = 12;

	public static String hash(String password_plaintext) {
		String salt = BCrypt.gensalt(WORKLOAD);
		return BCrypt.hashpw(password_plaintext, salt);
	}

	public static boolean check(String password_plaintext, String stored_hash) {
		return BCrypt.checkpw(password_plaintext, stored_hash);
	}
}