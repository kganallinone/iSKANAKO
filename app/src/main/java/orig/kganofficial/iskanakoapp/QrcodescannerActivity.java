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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;
import com.google.zxing.Result;

public class QrcodescannerActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String PG = "";
	CodeScanner mCodeScanner;
	private String T1 = "";
	private String T2 = "";
	private String ID = "";
	private String EML = "";
	private String SUBJCODE = "";
	private String SC = "";
	private HashMap<String, Object> createattendance = new HashMap<>();
	private HashMap<String, Object> editsubject = new HashMap<>();
	private String SID = "";
	private String AID = "";
	private String STUDENT = "";
	private double N = 0;
	private double L = 0;
	private double position = 0;
	private String CHECK = "";
	private String GETSID = "";
	private String GETAID = "";
	private String DATE = "";
	private double ck = 0;
	private String ATTENDANCECODE = "";
	private HashMap<String, Object> attendancedatabase = new HashMap<>();
	private double N1 = 0;
	private double L1 = 0;
	private String DETECT = "";
	private String Z_ID = "";
	private String Z_FULLNAME = "";
	private double N2 = 0;
	private double L2 = 0;
	private String SUBJ = "";
	private String ADB = "";
	private String RNDLTTRS = "";
	private String GENERATE = "";
	private String ROOM = "";
	private String START = "";
	private String END = "";
	private String PROF = "";
	private double N4 = 0;
	private double L4 = 0;
	private double N5 = 0;
	private double L5 = 0;
	private double N6 = 0;
	private double L6 = 0;
	private String SUBJECT = "";
	private String STUDENTS = "";
	private double N3 = 0;
	private double L3 = 0;
	private String A_SUBJ = "";
	private String A_STUDENT = "";
	
	private ArrayList<String> subjstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> subjlist = new ArrayList<>();
	private ArrayList<String> aidstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> aidlist = new ArrayList<>();
	private ArrayList<String> studentstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> studentlist = new ArrayList<>();
	private ArrayList<String> adbstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> adblist = new ArrayList<>();
	private ArrayList<String> sem_str = new ArrayList<>();
	private ArrayList<String> yearstr = new ArrayList<>();
	private ArrayList<String> roomstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> roomlist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> enrolledlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout scannerpage;
	private LinearLayout linear8;
	private LinearLayout scanlayer;
	private LinearLayout linear11;
	private ImageView list_bttn;
	private CodeScannerView scannerview;
	private LinearLayout linear9;
	private TextView detecttext;
	private TextView welcome;
	private TextView name;
	private TextView note;
	
	private Intent PAGE = new Intent();
	private DatabaseReference subjects_info = _firebase.getReference("subjects_info");
	private ChildEventListener _subjects_info_child_listener;
	private DatabaseReference attendance_info = _firebase.getReference("attendance_info");
	private ChildEventListener _attendance_info_child_listener;
	private TimerTask time;
	private Calendar date = Calendar.getInstance();
	private DatabaseReference student_user_info = _firebase.getReference("student_user_info");
	private ChildEventListener _student_user_info_child_listener;
	private DatabaseReference attendance_db = _firebase.getReference("attendance_db");
	private ChildEventListener _attendance_db_child_listener;
	private AlertDialog.Builder dialog;
	private DatabaseReference enrolled_student_info = _firebase.getReference("enrolled_student_info");
	private ChildEventListener _enrolled_student_info_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.qrcodescanner);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		scannerpage = findViewById(R.id.scannerpage);
		linear8 = findViewById(R.id.linear8);
		scanlayer = findViewById(R.id.scanlayer);
		linear11 = findViewById(R.id.linear11);
		list_bttn = findViewById(R.id.list_bttn);
		scannerview = findViewById(R.id.scannerview);
		linear9 = findViewById(R.id.linear9);
		detecttext = findViewById(R.id.detecttext);
		welcome = findViewById(R.id.welcome);
		name = findViewById(R.id.name);
		note = findViewById(R.id.note);
		dialog = new AlertDialog.Builder(this);
		
		list_bttn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), ScannerattendanceActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
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
		
		_student_user_info_child_listener = new ChildEventListener() {
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
		student_user_info.addChildEventListener(_student_user_info_child_listener);
		
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
	}
	
	private void initializeLogic() {
		date = Calendar.getInstance();
		CodeScannerView scannerView = findViewById(R.id.scannerview);
		 mCodeScanner = new CodeScanner(this, scannerView);
		//Block From Sketchqube 
		//Block From Sketchqube
		mCodeScanner.setDecodeCallback(new DecodeCallback() {
			    @Override public void onDecoded(@NonNull final Result result) { runOnUiThread(new Runnable() {
					         @Override
					          public void run() { 
						          
						       detecttext.setText(result.getText());
						DETECT = result.getText();
						_ENROLLEDDATA();
						mCodeScanner.startPreview();
						          
						           } }
				           
				           ); }
			           
			            }
		           
		           );
		//Block From Sketchqube 
		mCodeScanner.startPreview();
		_DESIGN();
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("PROF", getIntent().getStringExtra("PROF"));
		PAGE.putExtra("ID", getIntent().getStringExtra("ID"));
		PAGE.putExtra("SID", getIntent().getStringExtra("SID"));
		PAGE.putExtra("AL", getIntent().getStringExtra("AL"));
		PAGE.putExtra("AID", getIntent().getStringExtra("AID"));
		EML = getIntent().getStringExtra("EML");
		ID = getIntent().getStringExtra("ID");
		PROF = getIntent().getStringExtra("PROF");
		SID = getIntent().getStringExtra("SID");
		GETAID = getIntent().getStringExtra("AID");
		ATTENDANCECODE = getIntent().getStringExtra("AL");
	}
	
	@Override
	public void onBackPressed() {
		dialog.setTitle("iSKAN AKO");
		dialog.setIcon(R.drawable.unex_black_logo);
		dialog.setMessage("Do you want to end attendance?");
		dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				if (true) {
					_BACK();
					if (true) {
						PAGE.setClass(getApplicationContext(), ProfileFacultyActivity.class);
						startActivity(PAGE);
						finish();
					}
				}
			}
		});
		dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		dialog.create().show();
	}
	
	public void _GETDATA() {
		date = Calendar.getInstance();
		_ENROLLEDDATA();
	}
	
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		scanlayer.setElevation(16);
		scanlayer.setBackground(a);
		scanlayer.setElevation(16);
		scanlayer.setBackground(a);
	}
	
	
	public void _BACK() {
		if (true) {
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
					for(int _repeat16 = 0; _repeat16 < (int)(L); _repeat16++) {
						if (subjlist.get((int)N).get("R_UID").toString().equals(ID)) {
							GETSID = subjlist.get((int)N).get("R_ID").toString();
							if (subjlist.get((int)N).get("R_CHECK").toString().equals("TRUE")) {
								editsubject = new HashMap<>();
								editsubject.put("R_CHECK", "FALSE");
								subjects_info.child(GETSID).updateChildren(editsubject);
								editsubject.clear();
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
		if (true) {
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
					N2 = aidlist.size() - 1;
					L2 = aidlist.size();
					for(int _repeat93 = 0; _repeat93 < (int)(L2); _repeat93++) {
						if (aidlist.get((int)N2).get("A_UID").toString().equals(ID)) {
							GETAID = aidlist.get((int)N2).get("A_ID").toString();
							createattendance = new HashMap<>();
							createattendance.put("A_END", new SimpleDateFormat("hh:mm a").format(date.getTime()));
							createattendance.put("A_CHECK", "FALSE");
							attendance_info.child(GETAID).updateChildren(createattendance);
							createattendance.clear();
						}
						else {
							
						}
						N2--;
					}
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}
	}
	
	
	public void _ENROLLEDDATA() {
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
				N4 = enrolledlist.size() - 1;
				L4 = enrolledlist.size();
				for(int _repeat132 = 0; _repeat132 < (int)(L4); _repeat132++) {
					if (enrolledlist.get((int)N4).get("ST_STUDENTID").toString().equals(DETECT)) {
						ADB = ATTENDANCECODE.concat("-".concat(enrolledlist.get((int)N4).get("ST_LN").toString().concat("-".concat(enrolledlist.get((int)N4).get("ST_STUDENTID").toString().concat("-".concat(new SimpleDateFormat("MM-dd-YYYY").format(date.getTime())))))));
						attendancedatabase = new HashMap<>();
						attendancedatabase.put("L_ID", ADB);
						attendancedatabase.put("L_STDNTID", enrolledlist.get((int)N4).get("ST_STUDENTID").toString());
						attendancedatabase.put("L_AL", ATTENDANCECODE);
						attendancedatabase.put("L_NAME", enrolledlist.get((int)N4).get("ST_NAME").toString());
						attendancedatabase.put("L_DATE", new SimpleDateFormat("MM-dd-YYYY").format(date.getTime()));
						attendancedatabase.put("L_NO", "");
						attendancedatabase.put("L_SUBJID", SID);
						attendancedatabase.put("L_STATUS", "P");
						attendancedatabase.put("L_SHEETNO", "");
						attendancedatabase.put("L_PCUNIT", "");
						attendancedatabase.put("L_REMARKS", "");
						attendancedatabase.put("L_TIME", new SimpleDateFormat("hh:mm a").format(date.getTime()));
						attendancedatabase.put("L_LOG", "SCAN");
						attendance_db.child(ADB).updateChildren(attendancedatabase);
						attendancedatabase.clear();
						welcome.setText("WELCOME!");
						name.setText(enrolledlist.get((int)N4).get("ST_NAME").toString());
					}
					else {
						
					}
					N4--;
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
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