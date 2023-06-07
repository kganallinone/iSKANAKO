package orig.kganofficial.iskanakoapp;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.budiyev.android.codescanner.*;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class AttendancehistoryActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double N = 0;
	private double L = 0;
	private String SEMESTER = "";
	private String ID = "";
	private String SID = "";
	private HashMap<String, Object> addid = new HashMap<>();
	private String Z1 = "";
	
	private ArrayList<HashMap<String, Object>> subjlist = new ArrayList<>();
	private ArrayList<String> subjstr = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout header;
	private LinearLayout linear18;
	private LinearLayout linear8;
	private ListView SUBJECT;
	private ImageView imageview3;
	private LinearLayout linear35;
	private TextView textview11;
	private TextView textview12;
	private TextView firstsem;
	private TextView textview13;
	private TextView secondsem;
	private LinearLayout linear36;
	private ImageView imageview1;
	
	private Intent PAGE = new Intent();
	private DatabaseReference subjects_info = _firebase.getReference("subjects_info");
	private ChildEventListener _subjects_info_child_listener;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.attendancehistory);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		header = findViewById(R.id.header);
		linear18 = findViewById(R.id.linear18);
		linear8 = findViewById(R.id.linear8);
		SUBJECT = findViewById(R.id.SUBJECT);
		imageview3 = findViewById(R.id.imageview3);
		linear35 = findViewById(R.id.linear35);
		textview11 = findViewById(R.id.textview11);
		textview12 = findViewById(R.id.textview12);
		firstsem = findViewById(R.id.firstsem);
		textview13 = findViewById(R.id.textview13);
		secondsem = findViewById(R.id.secondsem);
		linear36 = findViewById(R.id.linear36);
		imageview1 = findViewById(R.id.imageview1);
		dialog = new AlertDialog.Builder(this);
		
		SUBJECT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
				PAGE.putExtra("USR", "FACULTY");
				PAGE.putExtra("ID", ID);
				PAGE.putExtra("SUBJ", subjlist.get((int)_position).get("R_SUBJECT").toString());
				PAGE.putExtra("SID", subjlist.get((int)_position).get("R_ID").toString());
				PAGE.putExtra("STDNT", subjlist.get((int)_position).get("R_STUDENT").toString());
				PAGE.setClass(getApplicationContext(), FacultyCoursePageActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		textview11.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		firstsem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SEMESTER = "[1st Semester]";
				subjects_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						subjlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								subjlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						N = subjlist.size() - 1;
						L = subjlist.size();
						for(int _repeat17 = 0; _repeat17 < (int)(L); _repeat17++) {
							if (subjlist.get((int)N).get("R_SEM").toString().equals(SEMESTER)) {
								if (subjlist.get((int)N).get("R_UID").toString().equals(ID)) {
									
								}
								else {
									subjlist.remove((int)(N));
								}
							}
							else {
								subjlist.remove((int)(N));
							}
							N--;
						}
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		
		secondsem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SEMESTER = "[2nd Semester]";
				subjects_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						subjlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								subjlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						N = subjlist.size() - 1;
						L = subjlist.size();
						for(int _repeat17 = 0; _repeat17 < (int)(L); _repeat17++) {
							if (subjlist.get((int)N).get("R_SEM").toString().equals(SEMESTER)) {
								if (subjlist.get((int)N).get("R_UID").toString().equals(ID)) {
									
								}
								else {
									subjlist.remove((int)(N));
								}
							}
							else {
								subjlist.remove((int)(N));
							}
							N--;
						}
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("iSKAN AKO");
				dialog.setIcon(R.drawable.iskanako_01);
				dialog.setMessage("Archive is not available.");
				dialog.setCancelable(false);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				dialog.create().show();
			}
		});
		
		_subjects_info_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				subjects_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						subjlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								subjlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						subjstr.add(_childKey);
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
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
				subjects_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						subjlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								subjlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
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
				subjects_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						subjlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								subjlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
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
		subjects_info.addChildEventListener(_subjects_info_child_listener);
	}
	
	private void initializeLogic() {
		_DESIGN();
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		ID = getIntent().getStringExtra("ID");
		SEMESTER = "[1st Semester]";
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), ProfileFacultyActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		header.setElevation(16);
		header.setBackground(a);
		
	}
	
	public class SUBJECTAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public SUBJECTAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.subjects, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView subject = _view.findViewById(R.id.subject);
			final TextView subjcode = _view.findViewById(R.id.subjcode);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView textview6 = _view.findViewById(R.id.textview6);
			final TextView date = _view.findViewById(R.id.date);
			final TextView scgedule = _view.findViewById(R.id.scgedule);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView student = _view.findViewById(R.id.student);
			
			if (_data.get((int)_position).get("R_SEM").toString().equals(SEMESTER)) {
				if (_data.get((int)_position).get("R_UID").toString().equals(ID)) {
					main.setVisibility(View.VISIBLE);
					android.graphics.drawable.GradientDrawable Z1 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
					
					Z1.setCornerRadius(30);
					main.setElevation(16);
					main.setBackground(Z1);
					subject.setText(_data.get((int)_position).get("R_SUBJECT").toString());
					subjcode.setText(_data.get((int)_position).get("R_CODE").toString());
					student.setText(_data.get((int)_position).get("R_STUDENT").toString());
					scgedule.setText(_data.get((int)_position).get("R_TIMEIN").toString().concat("–".concat(_data.get((int)_position).get("R_TIMEOUT").toString())));
					date.setText("(".concat(_data.get((int)_position).get("R_DATE").toString().concat(")")));
				}
				else {
					main.setVisibility(View.GONE);
				}
			}
			else {
				main.setVisibility(View.GONE);
			}
			if (_data.get((int)_position).get("R_ID").toString().equals("NONE")) {
				SID = subjstr.get((int)(_position));
				addid = new HashMap<>();
				addid.put("R_ID", SID);
				subjects_info.child(SID).updateChildren(addid);
				addid.clear();
			}
			else {
				
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