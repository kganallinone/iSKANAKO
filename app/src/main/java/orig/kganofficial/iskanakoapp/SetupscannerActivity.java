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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
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

public class SetupscannerActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String ROOM = "";
	private String RNDLTTRS = "";
	private String GENERATE = "";
	private double N = 0;
	private double L = 0;
	private String ID = "";
	private String GETSID = "";
	private String GETAID = "";
	private String DATE = "";
	private String START = "";
	private String END = "";
	private String EML = "";
	private String SID = "";
	private String STUDENT = "";
	private String SUBJCODE = "";
	private String SUBJ = "";
	private String AID = "";
	private String CHECK = "";
	private HashMap<String, Object> createattendance = new HashMap<>();
	private String ATTENDANCECODE = "";
	private HashMap<String, Object> editsubject = new HashMap<>();
	private String PROF = "";
	private String TI = "";
	private String TO = "";
	private String CALLTIME = "";
	private double MaxLength = 0;
	private double N1 = 0;
	private double L1 = 0;
	private String OPTIONAL_TO = "";
	private String OPTIONAL_TI = "";
	private double N3 = 0;
	private double L3 = 0;
	private String A_SUBJ = "";
	private String A_STUDENT = "";
	private HashMap<String, Object> attendancedatabase = new HashMap<>();
	private String ADB = "";
	private String STDNTID = "";
	private String S_STDNTNAME = "";
	
	private ArrayList<HashMap<String, Object>> subjlist = new ArrayList<>();
	private ArrayList<String> roomstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> aidlist = new ArrayList<>();
	private ArrayList<String> subjstr = new ArrayList<>();
	private ArrayList<String> timestr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> enrolledlist = new ArrayList<>();
	private ArrayList<String> to = new ArrayList<>();
	private ArrayList<String> ti = new ArrayList<>();
	
	private LinearLayout linear23;
	private LinearLayout setupscanpage;
	private TextView textview4;
	private TextView textview5;
	private TextView textview9;
	private LinearLayout linear13;
	private ScrollView vscroll1;
	private LinearLayout linear15;
	private TextView textview6;
	private ListView subject;
	private LinearLayout linear17;
	private LinearLayout linear24;
	private LinearLayout linear16;
	private Button create_bttn;
	private EditText room;
	private Spinner spinner1;
	
	private Intent PAGE = new Intent();
	private DatabaseReference subjects_info = _firebase.getReference("subjects_info");
	private ChildEventListener _subjects_info_child_listener;
	private Calendar date = Calendar.getInstance();
	private DatabaseReference attendance_info = _firebase.getReference("attendance_info");
	private ChildEventListener _attendance_info_child_listener;
	private DatabaseReference enrolled_student_info = _firebase.getReference("enrolled_student_info");
	private ChildEventListener _enrolled_student_info_child_listener;
	private DatabaseReference attendance_db = _firebase.getReference("attendance_db");
	private ChildEventListener _attendance_db_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.setupscanner);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear23 = findViewById(R.id.linear23);
		setupscanpage = findViewById(R.id.setupscanpage);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		textview9 = findViewById(R.id.textview9);
		linear13 = findViewById(R.id.linear13);
		vscroll1 = findViewById(R.id.vscroll1);
		linear15 = findViewById(R.id.linear15);
		textview6 = findViewById(R.id.textview6);
		subject = findViewById(R.id.subject);
		linear17 = findViewById(R.id.linear17);
		linear24 = findViewById(R.id.linear24);
		linear16 = findViewById(R.id.linear16);
		create_bttn = findViewById(R.id.create_bttn);
		room = findViewById(R.id.room);
		spinner1 = findViewById(R.id.spinner1);
		
		create_bttn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_CREATE();
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					room.setText("ICT LAB-1");
				}
				if (_position == 1) {
					room.setText("ICT LAB-2");
				}
				if (_position == 2) {
					room.setText("ICT LAB-3");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
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
						subject.setAdapter(new SubjectAdapter(subjlist));
						((BaseAdapter)subject.getAdapter()).notifyDataSetChanged();
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
						subject.setAdapter(new SubjectAdapter(subjlist));
						((BaseAdapter)subject.getAdapter()).notifyDataSetChanged();
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
						subject.setAdapter(new SubjectAdapter(subjlist));
						((BaseAdapter)subject.getAdapter()).notifyDataSetChanged();
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
		
		_attendance_info_child_listener = new ChildEventListener() {
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
		attendance_info.addChildEventListener(_attendance_info_child_listener);
		
		_enrolled_student_info_child_listener = new ChildEventListener() {
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
		enrolled_student_info.addChildEventListener(_enrolled_student_info_child_listener);
		
		_attendance_db_child_listener = new ChildEventListener() {
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
		attendance_db.addChildEventListener(_attendance_db_child_listener);
	}
	
	private void initializeLogic() {
		date = Calendar.getInstance();
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("PROF", getIntent().getStringExtra("PROF"));
		PAGE.putExtra("ID", getIntent().getStringExtra("ID"));
		EML = getIntent().getStringExtra("EML");
		ID = getIntent().getStringExtra("ID");
		PROF = getIntent().getStringExtra("PROF");
		_SPINROOM();
		_GENERATECODE();
		room.setEnabled(false);
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), ProfileFacultyActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _SPINROOM() {
		roomstr.add("ICT LAB-1");
		roomstr.add("ICT LAB-2");
		roomstr.add("ICT LAB-3");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, roomstr));
		((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _CREATE() {
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
				N = aidlist.size() - 1;
				L = aidlist.size();
				for(int _repeat16 = 0; _repeat16 < (int)(L); _repeat16++) {
					if (aidlist.get((int)N).get("A_UID").toString().equals(ID) && aidlist.get((int)N).get("A_CHECK").toString().equals("TRUE")) {
						GETSID = aidlist.get((int)N).get("A_SID").toString();
						GETAID = aidlist.get((int)N).get("A_ID").toString();
						DATE = aidlist.get((int)N).get("A_DATE").toString();
						START = new SimpleDateFormat("hh:mm a").format(date.getTime());
						END = "00:00";
						_EDITATTENDANCE();
					}
					else {
						
					}
					N--;
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	
	public void _EDITATTENDANCE() {
		if (true) {
			createattendance = new HashMap<>();
			createattendance.put("A_ROOM", room.getText().toString());
			createattendance.put("A_START", START);
			createattendance.put("A_END", END);
			createattendance.put("A_CALLTIME", START);
			attendance_info.child(GETAID).updateChildren(createattendance);
			createattendance.clear();
			if (true) {
				ATTENDANCECODE = GENERATE;
				PAGE.putExtra("AL", ATTENDANCECODE);
				PAGE.putExtra("SID", GETSID);
				PAGE.putExtra("AID", GETAID);
				PAGE.putExtra("STUDENT", A_STUDENT);
				PAGE.setClass(getApplicationContext(), QrcodescannerActivity.class);
				startActivity(PAGE);
				finish();
			}
		}
	}
	
	
	public void _GETDATA() {
		enrolled_student_info.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				enrolledlist = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						enrolledlist.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				N = enrolledlist.size() - 1;
				L = enrolledlist.size();
				for(int _repeat76 = 0; _repeat76 < (int)(L); _repeat76++) {
					if (SUBJCODE.equals(enrolledlist.get((int)N).get("ST_SID").toString()) && enrolledlist.get((int)N).get("ST_COURSE").toString().equals(STUDENT)) {
						STDNTID = enrolledlist.get((int)N).get("ST_STUDENTID").toString();
						S_STDNTNAME = enrolledlist.get((int)N).get("ST_NAME").toString();
						ADB = GENERATE.concat("-".concat(enrolledlist.get((int)N).get("ST_LN").toString().concat("-".concat(enrolledlist.get((int)N).get("ST_STUDENTID").toString().concat("-".concat(new SimpleDateFormat("MM-dd-YYYY").format(date.getTime())))))));
						if (true) {
							attendancedatabase = new HashMap<>();
							attendancedatabase.put("L_ID", ADB);
							attendancedatabase.put("L_AL", GENERATE);
							attendancedatabase.put("L_DATE", new SimpleDateFormat("MM-dd-YYYY").format(date.getTime()));
							attendancedatabase.put("L_NAME", S_STDNTNAME);
							attendancedatabase.put("L_STDNTID", STDNTID);
							attendancedatabase.put("L_STATUS", "N/A");
							attendancedatabase.put("L_NO", "");
							attendancedatabase.put("L_SUBJID", SID);
							attendancedatabase.put("L_TIME", "00:00");
							attendancedatabase.put("L_LOG", "N/A");
							attendancedatabase.put("L_SHEETNO", "0");
							attendancedatabase.put("L_PCUNIT", "");
							attendancedatabase.put("L_REMARKS", "");
							attendance_db.child(ADB).updateChildren(attendancedatabase);
							attendancedatabase.clear();
						}
					}
					else {
						
					}
					N--;
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	
	public void _GENERATECODE() {
		RNDLTTRS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		GENERATE = "";
		for (int i = 0; i < 6; i++) {
				GENERATE = GENERATE + RNDLTTRS.charAt(new java.util.Random().nextInt(RNDLTTRS.length()));
		}
	}
	
	public class SubjectAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public SubjectAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.checksubject, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final RadioButton getdisplay = _view.findViewById(R.id.getdisplay);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout info = _view.findViewById(R.id.info);
			final LinearLayout id = _view.findViewById(R.id.id);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final TextView time = _view.findViewById(R.id.time);
			final TextView subjectcode = _view.findViewById(R.id.subjectcode);
			final TextView subjectname = _view.findViewById(R.id.subjectname);
			final TextView student = _view.findViewById(R.id.student);
			
			if (_data.get((int)_position).get("R_UID").toString().equals(ID)) {
				SID = _data.get((int)_position).get("R_ID").toString();
				main.setVisibility(View.VISIBLE);
				if (subjstr.get((int)(_position)).equals(SID)) {
					subjectcode.setText(_data.get((int)_position).get("R_CODE").toString());
					subjectname.setText(_data.get((int)_position).get("R_SUBJECT").toString());
					time.setText(_data.get((int)_position).get("R_TIMEIN").toString().concat("-".concat(_data.get((int)_position).get("R_TIMEOUT").toString())));
					student.setText("(".concat(_data.get((int)_position).get("R_STUDENT").toString().concat(")")));
				}
				else {
					
				}
				if (_data.get((int)_position).get("R_CHECK").toString().equals("TRUE")) {
					getdisplay.setChecked(true);
				}
				else {
					getdisplay.setChecked(false);
				}
				getdisplay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						STUDENT = _data.get((int)_position).get("R_STUDENT").toString();
						SUBJCODE = _data.get((int)_position).get("R_CODE").toString();
						SUBJ = _data.get((int)_position).get("R_SUBJECT").toString();
						AID = GENERATE.concat("-".concat(SUBJCODE.concat("-".concat(STUDENT.concat("-".concat(new SimpleDateFormat("MM-dd-YYYY").format(date.getTime())))))));
						CHECK = _data.get((int)_position).get("R_CHECK").toString();
						TI = _data.get((int)_position).get("R_TIMEIN").toString();
						TO = _data.get((int)_position).get("R_TIMEOUT").toString();
						if (CHECK.equals("TRUE")) {
							editsubject = new HashMap<>();
							editsubject.put("R_CHECK", "FALSE");
							subjects_info.child(subjstr.get((int)(_position))).updateChildren(editsubject);
							editsubject.clear();
							if (true) {
								attendance_info.child(AID).removeValue();
							}
						}
						else {
							editsubject = new HashMap<>();
							editsubject.put("R_CHECK", "TRUE");
							subjects_info.child(subjstr.get((int)(_position))).updateChildren(editsubject);
							editsubject.clear();
							if (true) {
								createattendance = new HashMap<>();
								createattendance.put("A_ID", AID);
								createattendance.put("A_SID", _data.get((int)_position).get("R_ID").toString());
								createattendance.put("A_UID", ID);
								createattendance.put("A_AL", GENERATE);
								createattendance.put("A_CHECK", "TRUE");
								createattendance.put("A_STUDENT", STUDENT);
								createattendance.put("A_DATE", new SimpleDateFormat("MM-dd-YYYY").format(date.getTime()));
								createattendance.put("A_ROOM", "NONE");
								createattendance.put("A_SUBJ", SUBJ);
								createattendance.put("A_PROF", PROF);
								createattendance.put("A_START", "00:00");
								createattendance.put("A_END", "00:00");
								createattendance.put("A_TI", TI);
								createattendance.put("A_TO", TO);
								createattendance.put("A_CALLTIME", "00:00");
								createattendance.put("A_SHEETNO", "0");
								attendance_info.child(AID).updateChildren(createattendance);
								createattendance.clear();
								_GETDATA();
							}
						}
					}
				});
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