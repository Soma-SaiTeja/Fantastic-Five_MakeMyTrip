package com.makemytrip.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
	 public void writeToFile(String fileName, String key, String value) {

			File dir = new File(ConfigReader.getProperty("Json.path"));
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File out = new File(dir, fileName);
			String json = "{\n  \"" + key + "\": \"" + value + "\"\n}\n";

			try (FileWriter fw = new FileWriter(out, false)) {
				fw.write(json);
			} catch (IOException e) {
				throw new RuntimeException("Failed to write JSON file: " + out.getAbsolutePath(), e);
			}

		}
}
