package com.pmq.read_file.xml;

import java.io.InputStream;
import java.io.Reader;

public class Resources {
	public static InputStream getResourceAsStream(String resource) {
		return Resources.class.getClassLoader().getResourceAsStream(resource);
	}
	public static Reader getResourceAsReader(String resource) {
		return null;
	}
}
