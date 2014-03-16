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
	
	public TextView tvHeader;
	private ListView lvFiles;
	private EditText etFileName;
	private Button btPickFile;
	private Button btAddFile;
	
	private static final int PICKFILE_RESULT_CODE = 1;
	
	public SettingsDb settings;
	
	public Vector<SettingsFile> files;
	
	public void reloadFiles() {
		this.files = this.settings.selectFiles();
		String[] names = new String[this.files.size()];
		for (int i = 0; i < this.files.size(); i++) {
			names[i] = this.files.get(i).getPath();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
		this.lvFiles.setAdapter(adapter);
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
		this.btPickFile.setOnClickListener(addListener);
	}
	
	@Override 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
			case PICKFILE_RESULT_CODE: if(resultCode == RESULT_OK){
				String FilePath = data.getData().getPath();	
				this.etFileName.setText(FilePath); 
			}
			break;
		}
	}
	
	private void initAddButton () {
		OnClickListener addListener = new OnClickListener() {
			@Override
			public void onClick (View v) {
				settings.addFile(etFileName.getText().toString());
				etFileName.setText("");
				reloadFiles();
			}
		};
		btAddFile.setOnClickListener(addListener);
	}
	
	public ActionMode action;
	private int actionPosition;

	private void initListViewActions() {
		OnItemClickListener itemClick = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id)
			{
				if (action == null) {
					action = startActionMode(new FileContextCallback(MainActivity.this, position));
					actionPosition = position;
				} else {
					if (actionPosition == position) {
						action.finish();
					} else {
						action.finish();
						action = startActionMode(new FileContextCallback(MainActivity.this, position));
						actionPosition = position;
					}
				}
			}
		};
		this.lvFiles.setOnItemClickListener(itemClick);
	}

    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		this.tvHeader = (TextView) this.findViewById(R.id.tvHeader);
		this.lvFiles = (ListView) this.findViewById(R.id.lvFiles);
		this.etFileName = (EditText) this.findViewById(R.id.etFileName);
		this.btPickFile = (Button) this.findViewById(R.id.btPickFile);
		this.btAddFile = (Button) this.findViewById(R.id.btAddFile);
		this.settings = new SettingsDb(this);
		this.initPickButton();
		this.initAddButton();
		this.initListViewActions();
		this.reloadFiles();
    }

}
