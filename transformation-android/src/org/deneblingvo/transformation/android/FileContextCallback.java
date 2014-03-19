package org.deneblingvo.transformation.android;

import android.view.*;
import android.webkit.*;
import android.content.*;
import android.net.*;
import java.io.*;
import org.deneblingvo.transformator.*;

public class FileContextCallback implements ActionMode.Callback {

	private MainActivity activity;
	
	private int position;
	
	public FileContextCallback(MainActivity activity, int position) {
		this.activity = activity;
		this.position = position;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		mode.getMenuInflater().inflate(R.menu.file_context_menu, menu);
		return true;
	}

	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return false;
	}
	
	private void delFile() {
		this.activity.settings.delFile(activity.files.get(this.position).getId());
		this.activity.reloadFiles();
	}

	private void openFile() {
		String url = this.activity.files.get(this.position).getPath();
		String extension = MimeTypeMap.getFileExtensionFromUrl(url);
		String mType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
		Intent intent = new Intent(Intent.ACTION_EDIT); 
		try {
			intent.setDataAndType(Uri.parse("file://"+url), mType);
			this.activity.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			this.activity.tvHeader.setText(e.getLocalizedMessage());
		}
	}
	
	private void runFile() {
		Transformator transformator = new Transformator();
		String url = this.activity.files.get(this.position).getPath();
		File file = new File(url);
		try {
			InputStream source = new FileInputStream(file);
			transformator.transformate(false, source);
			source.close();
		} catch (Exception e) {
			this.activity.tvHeader.setText(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		switch (item.getItemId()) {
			case R.id.file_del: {
				this.delFile();
				mode.finish();
				break;
			}
			case R.id.file_open: {
				this.openFile();
				mode.finish();
				break;
			}
			case R.id.file_run: {
				this.runFile();
				mode.finish();
				break;
			}
		}
		return false;
	}

	public void onDestroyActionMode(ActionMode mode) {
		this.activity.action = null;
	}

}
