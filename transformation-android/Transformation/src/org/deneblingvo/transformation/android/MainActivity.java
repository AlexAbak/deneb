package org.deneblingvo.transformation.android;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import java.util.*;
import android.view.View.*;
import android.widget.AdapterView.*;
import android.content.*;
import android.view.ContextMenu.*;

public class MainActivity extends Activity
{
	
	private static final int PICKFILE_RESULT_CODE = 1;
	
	private SettingsDb settings;
	
	private Vector<SettingsFile> files;
	
	private void reloadFiles() {
		this.files = this.settings.selectFiles();
		String[] names = new String[this.files.size()];
		for (int i = 0; i < this.files.size(); i++) {
			names[i] = this.files.get(i).getPath();
		}
		
		ListView l = (ListView) this.findViewById(R.id.lvMain);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
		l.setAdapter(adapter);
	}
	
	private void initPickButton () {
		OnClickListener addListener = new OnClickListener() {
			@Override
			public void onClick (View v) {
				Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
				pick.setType("file/*");
				startActivityForResult(pick, PICKFILE_RESULT_CODE);
			}
		};
		Button pickFile = (Button) this.findViewById(R.id.btFileName);
		pickFile.setOnClickListener(addListener);
	}
	
	@Override 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
			case PICKFILE_RESULT_CODE: if(resultCode == RESULT_OK){
				String FilePath = data.getData().getPath();
				EditText t = (EditText) findViewById(R.id.etFileName);	
				t.setText(FilePath); 
			}
			break;
		}
	}
	
	private void initAddButton () {
		OnClickListener addListener = new OnClickListener() {
			@Override
			public void onClick (View v) {
				EditText t = (EditText) findViewById(R.id.etFileName);
				settings.addFile(t.getText().toString());
				t.setText("");
				reloadFiles();
			}
		};
		Button addFile = (Button) this.findViewById(R.id.btAddFile);
		addFile.setOnClickListener(addListener);
	}
	
	private void initListViewActions() {
		OnItemLongClickListener longClick = new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
				settings.delFile(files.get(position).getId());
				reloadFiles();
				return true;
			}
		};
		OnCreateContextMenuListener contextMenu = new OnCreateContextMenuListener() {
			public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo info) {
				getMenuInflater().inflate(R.menu.file_context_menu, menu);
			}
			public void onContextItemSelected(MenuItem item) {
				switch(item.getItemId()) {
					case R.id.file_del:
						ListView l = (ListView) findViewById(R.id.lvMain);
						settings.delFile(files.get(l.getSelectedItemPosition()).getId());
						reloadFiles();	
					break;
				}
			}
		};
		ListView l = (ListView) this.findViewById(R.id.lvMain);
		l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		//l.setOnItemLongClickListener(longClick);
		l.setOnCreateContextMenuListener(contextMenu);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		this.settings = new SettingsDb(this);
		this.initPickButton();
		this.initAddButton();
		this.initListViewActions();
		this.reloadFiles();
    }

}
