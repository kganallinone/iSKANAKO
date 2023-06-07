package orig.kganofficial.iskanakoapp;

import android.animation.*;
import android.app.*;
import android.content.*;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class ScannerattendanceActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String EML = "";
	private String ID = "";
	private String PROF = "";
	private String ATTENDANCECODE = "";
	private String START = "";
	private String END = "";
	private String TIMEIN = "";
	private String FIXTIME = "";
	private String FIXTIME2 = "";
	private String FIXTIME3 = "";
	private String CALLTIME = "";
	private double N = 0;
	private double L = 0;
	private double ct = 0;
	private String TIMEENTER = "";
	private String FT = "";
	private String FT2 = "";
	private String FT3 = "";
	private double te = 0;
	private String STATUS = "";
	private String SAVED = "";
	private HashMap<String, Object> change = new HashMap<>();
	private double n = 0;
	private double N1 = 0;
	private double L1 = 0;
	
	private ArrayList<String> adbstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> adblist = new ArrayList<>();
	private ArrayList<String> aidstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> aidlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear23;
	private TextView textview14;
	private TextView prof;
	private TextView start_end;
	private TextView dtd;
	private TextView textview3;
	private ListView subjlistdisplay;
	private TextView textview10;
	private LinearLayout linear26;
	private ListView attrndancelistdisplay;
	private TextView textview19;
	private TextView textview20;
	private TextView textview21;
	private TextView textview22;
	
	private Intent PAGE = new Intent();
	private DatabaseReference attendance_db = _firebase.getReference("attendance_db");
	private ChildEventListener _attendance_db_child_listener;
	private DatabaseReference attendance_info = _firebase.getReference("attendance_info");
	private ChildEventListener _attendance_info_child_listener;
	private DatabaseReference subjects_info = _firebase.getReference("subjects_info");
	private ChildEventListener _subjects_info_child_listener;
	private Calendar date = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.scannerattendance);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear23 = findViewById(R.id.linear23);
		textview14 = findViewById(R.id.textview14);
		prof = findViewById(R.id.prof);
		start_end = findViewById(R.id.start_end);
		dtd = findViewById(R.id.dtd);
		textview3 = findViewById(R.id.textview3);
		subjlistdisplay = findViewById(R.id.subjlistdisplay);
		textview10 = findViewById(R.id.textview10);
		linear26 = findViewById(R.id.linear26);
		attrndancelistdisplay = findViewById(R.id.attrndancelistdisplay);
		textview19 = findViewById(R.id.textview19);
		textview20 = findViewById(R.id.textview20);
		textview21 = findViewById(R.id.textview21);
		textview22 = findViewById(R.id.textview22);
		
		_attendance_db_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				attendance_db.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						adblist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adblist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						adbstr.add(_childKey);
						attrndancelistdisplay.setAdapter(new AttrndancelistdisplayAdapter(adblist));
						((BaseAdapter)attrndancelistdisplay.getAdapter()).notifyDataSetChanged();
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
				attendance_db.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						adblist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adblist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						attrndancelistdisplay.setAdapter(new AttrndancelistdisplayAdapter(adblist));
						((BaseAdapter)attrndancelistdisplay.getAdapter()).notifyDataSetChanged();
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
				attendance_db.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						adblist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adblist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						attrndancelistdisplay.setAdapter(new AttrndancelistdisplayAdapter(adblist));
						((BaseAdapter)attrndancelistdisplay.getAdapter()).notifyDataSetChanged();
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
		attendance_db.addChildEventListener(_attendance_db_child_listener);
		
		_attendance_info_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				attendance_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						aidlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								aidlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						aidstr.add(_childKey);
						subjlistdisplay.setAdapter(new SubjlistdisplayAdapter(aidlist));
						((BaseAdapter)subjlistdisplay.getAdapter()).notifyDataSetChanged();
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
				attendance_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						aidlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								aidlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						subjlistdisplay.setAdapter(new SubjlistdisplayAdapter(aidlist));
						((BaseAdapter)subjlistdisplay.getAdapter()).notifyDataSetChanged();
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
				attendance_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						aidlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								aidlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						subjlistdisplay.setAdapter(new SubjlistdisplayAdapter(aidlist));
						((BaseAdapter)subjlistdisplay.getAdapter()).notifyDataSetChanged();
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
		attendance_info.addChildEventListener(_attendance_info_child_listener);
		
		_subjects_info_child_listener = new ChildEventListener() {
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
		subjects_info.addChildEventListener(_subjects_info_child_listener);
	}
	
	private void initializeLogic() {
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("PROF", getIntent().getStringExtra("PROF"));
		PAGE.putExtra("ID", getIntent().getStringExtra("ID"));
		PAGE.putExtra("SID", getIntent().getStringExtra("SID"));
		PAGE.putExtra("AL", getIntent().getStringExtra("AL"));
		PAGE.putExtra("AID", getIntent().getStringExtra("AID"));
		EML = getIntent().getStringExtra("EML");
		ID = getIntent().getStringExtra("ID");
		PROF = getIntent().getStringExtra("PROF");
		ATTENDANCECODE = getIntent().getStringExtra("AL");
		prof.setText("PROFESSOR: ".concat(PROF));
		dtd.setText("DATE TODAY: ".concat(new SimpleDateFormat("MM-dd-YYYY").format(date.getTime())));
		n = 1;
		_ERASENO();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), QrcodescannerActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _GETDATA() {
		start_end.setText("START: ".concat(START.concat("     END: ".concat(END))));
	}
	
	
	public void _DETECTSTATUS() {
		if (true) {
			change = new HashMap<>();
			change.put("L_ID", SAVED);
			change.put("L_STATUS", STATUS);
			attendance_db.child(SAVED).updateChildren(change);
			change.clear();
		}
	}
	
	
	public void _ERASENO() {
		attendance_db.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				adblist = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						adblist.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				N1 = 0;
				L1 = adblist.size();
				while(!(N1 == L1)) {
					if (adblist.get((int)N1).get("L_AL").toString().equals(ATTENDANCECODE)) {
						if (n > adblist.size()) {
							
						}
						else {
							change = new HashMap<>();
							change.put("L_NO", String.valueOf((long)(n)));
							attendance_db.child(adblist.get((int)N1).get("L_ID").toString()).updateChildren(change);
							change.clear();
							n++;
						}
					}
					else {
						
					}
					N1++;
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	public class SubjlistdisplayAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public SubjlistdisplayAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.subject3, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView subject = _view.findViewById(R.id.subject);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView semester = _view.findViewById(R.id.semester);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView studenttype = _view.findViewById(R.id.studenttype);
			final TextView textview5 = _view.findViewById(R.id.textview5);
			final TextView time = _view.findViewById(R.id.time);
			
			if (_data.get((int)_position).get("A_AL").toString().equals(ATTENDANCECODE)) {
				main.setVisibility(View.VISIBLE);
				subject.setText(_data.get((int)_position).get("A_SUBJ").toString());
				studenttype.setText(_data.get((int)_position).get("A_STUDENT").toString());
				time.setText(_data.get((int)_position).get("A_TI").toString().concat("-".concat(_data.get((int)_position).get("A_TO").toString())));
				START = _data.get((int)_position).get("A_START").toString();
				END = _data.get((int)_position).get("A_END").toString();
				TIMEIN = _data.get((int)_position).get("A_CALLTIME").toString();
				_GETDATA();
			}
			else {
				main.setVisibility(View.GONE);
			}
			
			return _view;
		}
	}
	
	public class AttrndancelistdisplayAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public AttrndancelistdisplayAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.attendancegrid, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView no = _view.findViewById(R.id.no);
			final TextView name = _view.findViewById(R.id.name);
			final TextView status = _view.findViewById(R.id.status);
			final TextView time = _view.findViewById(R.id.time);
			
			if (_data.get((int)_position).get("L_AL").toString().equals(ATTENDANCECODE)) {
				main.setVisibility(View.VISIBLE);
				name.setText(_data.get((int)_position).get("L_NAME").toString());
				status.setText(_data.get((int)_position).get("L_STATUS").toString());
				time.setText(_data.get((int)_position).get("L_TIME").toString());
				no.setText(_data.get((int)_position).get("L_NO").toString());
			}
			else {
				main.setVisibility(View.GONE);
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