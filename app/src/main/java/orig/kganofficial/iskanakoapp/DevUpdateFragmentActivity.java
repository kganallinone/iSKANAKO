package orig.kganofficial.iskanakoapp;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.budiyev.android.codescanner.*;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.*;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class DevUpdateFragmentActivity extends Fragment {
	
	public final int REQ_CD_APP = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private double N = 0;
	private double L = 0;
	private String path_name = "";
	private HashMap<String, Object> create = new HashMap<>();
	
	private ArrayList<String> versionstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> versionlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout up;
	private LinearLayout linear3;
	private LinearLayout line;
	private LinearLayout linear4;
	private LinearLayout linear8;
	private EditText version;
	private ScrollView vscroll1;
	private Button update;
	private TextView textview3;
	private ImageView uploadfilepick;
	private TextView percent;
	private TextView link;
	private LinearLayout linear9;
	private EditText edittext1;
	
	private StorageReference APPLICATION = _firebase_storage.getReference("APPLICATION/Apk");
	private OnCompleteListener<Uri> _APPLICATION_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _APPLICATION_download_success_listener;
	private OnSuccessListener _APPLICATION_delete_success_listener;
	private OnProgressListener _APPLICATION_upload_progress_listener;
	private OnProgressListener _APPLICATION_download_progress_listener;
	private OnFailureListener _APPLICATION_failure_listener;
	
	private DatabaseReference appinfo_iskanako = _firebase.getReference("appinfo_iskanako");
	private ChildEventListener _appinfo_iskanako_child_listener;
	private Intent app = new Intent(Intent.ACTION_GET_CONTENT);
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.dev_update_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		up = _view.findViewById(R.id.up);
		linear3 = _view.findViewById(R.id.linear3);
		line = _view.findViewById(R.id.line);
		linear4 = _view.findViewById(R.id.linear4);
		linear8 = _view.findViewById(R.id.linear8);
		version = _view.findViewById(R.id.version);
		vscroll1 = _view.findViewById(R.id.vscroll1);
		update = _view.findViewById(R.id.update);
		textview3 = _view.findViewById(R.id.textview3);
		uploadfilepick = _view.findViewById(R.id.uploadfilepick);
		percent = _view.findViewById(R.id.percent);
		link = _view.findViewById(R.id.link);
		linear9 = _view.findViewById(R.id.linear9);
		edittext1 = _view.findViewById(R.id.edittext1);
		app.setType("*/*");
		app.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().equals("") || version.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Complete your info.");
				}
				else {
					create = new HashMap<>();
					create.put("UPDATEINFO", edittext1.getText().toString());
					create.put("VERSION", version.getText().toString());
					appinfo_iskanako.child("APK-INFO").updateChildren(create);
					create.clear();
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Uploaded");
				}
			}
		});
		
		uploadfilepick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(app, REQ_CD_APP);
			}
		});
		
		_APPLICATION_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				if (_progressValue == 100) {
					percent.setText("UPLOADING SUCCESS");
				}
				else {
					percent.setText("UPLOADING ".concat("(".concat(String.valueOf((long)(_progressValue)).concat("%)"))));
				}
			}
		};
		
		_APPLICATION_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_APPLICATION_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				link.setText(_downloadUrl);
				create = new HashMap<>();
				create.put("UPDATELINK", _downloadUrl);
				appinfo_iskanako.child("APK-INFO").updateChildren(create);
				create.clear();
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Done");
			}
		};
		
		_APPLICATION_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_APPLICATION_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_APPLICATION_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_appinfo_iskanako_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		appinfo_iskanako.addChildEventListener(_appinfo_iskanako_child_listener);
	}
	
	private void initializeLogic() {
		_GETVERSION();
		_DESIGN();
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_APP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), _data.getData()));
					}
				}
				link.setText(_filePath.get((int)(0)));
				path_name = Uri.parse(Uri.parse(link.getText().toString()).getLastPathSegment()).getLastPathSegment();
				APPLICATION.child(path_name).putFile(Uri.fromFile(new File(link.getText().toString()))).addOnFailureListener(_APPLICATION_failure_listener).addOnProgressListener(_APPLICATION_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return APPLICATION.child(path_name).getDownloadUrl();
					}}).addOnCompleteListener(_APPLICATION_upload_success_listener);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _GETVERSION() {
		appinfo_iskanako.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				versionlist = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						versionlist.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				N = versionlist.size() - 1;
				L = versionlist.size();
				for(int _repeat21 = 0; _repeat21 < (int)(L); _repeat21++) {
					version.setText(versionlist.get((int)N).get("VERSION").toString());
					edittext1.setText(versionlist.get((int)N).get("UPDATEINFO").toString());
					N--;
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		up.setElevation(16);
		up.setBackground(a);
		
	}
	
}