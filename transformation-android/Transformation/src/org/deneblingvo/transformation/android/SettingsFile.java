package org.deneblingvo.transformation.android;

public class SettingsFile {
	
	private int id;
	
	private String path;
	
	public int getId () {
		return this.id;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public SettingsFile (int id, String path) {
		super();
		this.id = id;
		this.path = path;
	}
	
}
