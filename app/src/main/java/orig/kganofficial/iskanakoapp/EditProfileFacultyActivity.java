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
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.budiyev.android.codescanner.*;
import com.bumptech.glide.Glide;
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
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.File;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class EditProfileFacultyActivity extends AppCompatActivity {
	
	public final int REQ_CD_IMAGE = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String URL = "";
	private String ID = "";
	private String GETID = "";
	private String NAME = "";
	private String SEX = "";
	private String COURSE = "";
	private String YEAR = "";
	private String SECTION = "";
	private String ADDRESS = "";
	private String path = "";
	private String path_name = "";
	private HashMap<String, Object> editprofile = new HashMap<>();
	private String EML = "";
	private String str = "";
	
	private ArrayList<HashMap<String, Object>> facultylist = new ArrayList<>();
	private ArrayList<String> fstr = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview3;
	private LinearLayout editdisplay;
	private LinearLayout linear12;
	private LinearLayout linear8;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private LinearLayout linear16;
	private LinearLayout linear15;
	private LinearLayout linear18;
	private LinearLayout linear23;
	private ImageView close;
	private ImageView edit;
	private CircleImageView circleimageview1;
	private TextView editdp;
	private TextView textview7;
	private ListView iddisplay;
	private TextView textview6;
	private EditText name;
	private TextView textview9;
	private EditText sex;
	
	private DatabaseReference faculty_user_info = _firebase.getReference("faculty_user_info");
	private ChildEventListener _faculty_user_info_child_listener;
	private Intent PAGE = new Intent();
	private Intent image = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference faculty_dp = _firebase_storage.getReference("faculty_dp");
	private OnCompleteListener<Uri> _faculty_dp_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _faculty_dp_download_success_listener;
	private OnSuccessListener _faculty_dp_delete_success_listener;
	private OnProgressListener _faculty_dp_upload_progress_listener;
	private OnProgressListener _faculty_dp_download_progress_listener;
	private OnFailureListener _faculty_dp_failure_listener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.edit_profile_faculty);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview3 = findViewById(R.id.textview3);
		editdisplay = findViewById(R.id.editdisplay);
		linear12 = findViewById(R.id.linear12);
		linear8 = findViewById(R.id.linear8);
		linear13 = findViewById(R.id.linear13);
		linear14 = findViewById(R.id.linear14);
		linear16 = findViewById(R.id.linear16);
		linear15 = findViewById(R.id.linear15);
		linear18 = findViewById(R.id.linear18);
		linear23 = findViewById(R.id.linear23);
		close = findViewById(R.id.close);
		edit = findViewById(R.id.edit);
		circleimageview1 = findViewById(R.id.circleimageview1);
		editdp = findViewById(R.id.editdp);
		textview7 = findViewById(R.id.textview7);
		iddisplay = findViewById(R.id.iddisplay);
		textview6 = findViewById(R.id.textview6);
		name = findViewById(R.id.name);
		textview9 = findViewById(R.id.textview9);
		sex = findViewById(R.id.sex);
		image.setType("image/*");
		image.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edit.setVisibility(View.VISIBLE);
				close.setVisibility(View.GONE);
				editdp.setVisibility(View.GONE);
				iddisplay.setEnabled(false);
			}
		});
		
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edit.setVisibility(View.GONE);
				close.setVisibility(View.VISIBLE);
				editdp.setVisibility(View.VISIBLE);
				iddisplay.setEnabled(false);
			}
		});
		
		circleimageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(image, REQ_CD_IMAGE);
			}
		});
		
		_faculty_user_info_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				faculty_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						facultylist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								facultylist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						fstr.add(_childKey);
						iddisplay.setAdapter(new IddisplayAdapter(facultylist));
						((BaseAdapter)iddisplay.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				faculty_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						facultylist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								facultylist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						iddisplay.setAdapter(new IddisplayAdapter(facultylist));
						((BaseAdapter)iddisplay.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				faculty_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						facultylist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								facultylist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						iddisplay.setAdapter(new IddisplayAdapter(facultylist));
						((BaseAdapter)iddisplay.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		faculty_user_info.addChildEventListener(_faculty_user_info_child_listener);
		
		_faculty_dp_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				if (URL.equals("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcuBdDEbOeZKJhNr3YyrdO-HeHYLhBPyqecQ&usqp=CAU")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Uploading your display picture...".concat("(".concat(String.valueOf((long)(_progressValue)).concat("%)"))));
				}
				else {
					_firebase_storage.getReferenceFromUrl(URL).delete().addOnSuccessListener(_faculty_dp_delete_success_listener).addOnFailureListener(_faculty_dp_failure_listener);
					SketchwareUtil.showMessage(getApplicationContext(), "Uploading your display picture...".concat("(".concat(String.valueOf((long)(_progressValue)).concat("%)"))));
				}
			}
		};
		
		_faculty_dp_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_faculty_dp_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				editprofile = new HashMap<>();
				editprofile.put("E_URL", _downloadUrl);
				faculty_user_info.child(ID).updateChildren(editprofile);
				editprofile.clear();
				SketchwareUtil.showMessage(getApplicationContext(), "Done");
			}
		};
		
		_faculty_dp_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_faculty_dp_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_faculty_dp_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
	}
	
	private void initializeLogic() {
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		EML = getIntent().getStringExtra("EML");
		_DISPLAY();
		_DESIGN();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMAGE:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				path = _filePath.get((int)(0));
				path_name = Uri.parse(Uri.parse(path).getLastPathSegment()).getLastPathSegment();
				faculty_dp.child(path_name).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_faculty_dp_failure_listener).addOnProgressListener(_faculty_dp_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return faculty_dp.child(path_name).getDownloadUrl();
					}}).addOnCompleteListener(_faculty_dp_upload_success_listener);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), FacultyprofileActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _GETDATA() {
		Glide.with(getApplicationContext()).load(Uri.parse(URL)).into(circleimageview1);
		ID = GETID;
		name.setText(NAME);
		sex.setText(SEX);
	}
	
	
	public void _DISPLAY() {
		edit.setVisibility(View.VISIBLE);
		close.setVisibility(View.GONE);
		editdp.setVisibility(View.GONE);
		iddisplay.setEnabled(false);
	}
	
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		editdisplay.setElevation(16);
		editdisplay.setBackground(a);
		editdisplay.setElevation(16);
		editdisplay.setBackground(a);
	}
	
	public class IddisplayAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public IddisplayAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.idshow, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView id = _view.findViewById(R.id.id);
			
			if (_data.get((int)_position).get("C_EMAIL").toString().equals(EML)) {
				linear1.setVisibility(View.VISIBLE);
				URL = _data.get((int)_position).get("E_URL").toString();
				GETID = _data.get((int)_position).get("C_ID").toString();
				id.setText(_data.get((int)_position).get("C_ID").toString());
				NAME = _data.get((int)_position).get("A_FULLNAME").toString();
				SEX = _data.get((int)_position).get("B_SEX").toString();
				_GETDATA();
			}
			else {
				linear1.setVisibility(View.GONE);
			}
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}