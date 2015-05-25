package fomo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

	public static String[] getLines(String filename) throws IOException {
		String[] result;
		BufferedReader br = new BufferedReader(new FileReader(filename));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			result = sb.toString().split(System.lineSeparator());
		} finally {
			br.close();
		}
		return result;
	}
}
