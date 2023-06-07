package orig.kganofficial.iskanakoapp;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import jxl.*;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.Cell;
import jxl.Sheet;
import jxl.write.*;
import jxl.write.WriteException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.RowsExceededException;

public class FacultyAttendancePageActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String EML = "";
	private String ID = "";
	private String PROF = "";
	private String ATTENDANCECODE = "";
	private String DATE = "";
	private String SUBJ = "";
	private String CALLTIME = "";
	private String START = "";
	private String TIMEIN = "";
	private String END = "";
	private String STDNTID = "";
	private String SID = "";
	private String S_SUBJ = "";
	private String S_STDNT = "";
	private double N = 0;
	private double L = 0;
	private String ADB = "";
	private HashMap<String, Object> attendancedatabase = new HashMap<>();
	private double N1 = 0;
	private double L1 = 0;
	private String S_STDNTNAME = "";
	private double N2 = 0;
	private double L2 = 0;
	private String S_SUBJID = "";
	private HashMap<String, Object> change = new HashMap<>();
	private String SAVED = "";
	private String STATUS = "";
	private String FIXTIME = "";
	private String FIXTIME2 = "";
	private String FIXTIME3 = "";
	private double ct = 0;
	private String TIMEENTER = "";
	private String FT = "";
	private String FT2 = "";
	private String FT3 = "";
	private double te = 0;
	private String ST = "";
	private String ATTENDANCEDATE = "";
	private String ATTENDANCETIME = "";
	private double position = 0;
	private String IFELSE = "";
	private double position2 = 0;
	private String IFELSE2 = "";
	private String ADB2 = "";
	private double n = 0;
	private String NAME = "";
	private double num = 0;
	private double i = 0;
	private String Z1 = "";
	private double n_checker = 0;
	private String ABD = "";
	private double N3 = 0;
	private double L3 = 0;
	private String fileName = "";
	private HashMap<String, Object> excel = new HashMap<>();
	private double N4 = 0;
	private double L4 = 0;
	private String EXNO = "";
	private String EXNAME = "";
	private String EXSTATUS = "";
	private String EXTIME = "";
	private String AT_STATUS = "";
	private String AT_TIME = "";
	private String AT_LOG = "";
	private String A = "";
	private String B = "";
	private String C = "";
	private double ST_NO = 0;
	private double GETNO = 0;
	private double row = 0;
	private double IntSTUDENT = 0;
	private String STDNT = "";
	private String STARTTIME = "";
	private double sheetno = 0;
	
	private ArrayList<HashMap<String, Object>> ailist = new ArrayList<>();
	private ArrayList<String> aistr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> adblist = new ArrayList<>();
	private ArrayList<String> adbstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> errolledlist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> subjlist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> maplist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear23;
	private LinearLayout linear25;
	private TextView textview14;
	private TextView prof;
	private TextView start_end;
	private TextView dtd;
	private TextView textview3;
	private ListView subjlistdisplay;
	private TextView textview10;
	private LinearLayout linear26;
	private ListView attrndancelistdisplay;
	private TextView textview17;
	private TextView textview18;
	private TextView textview19;
	private TextView textview20;
	private LinearLayout save;
	private ImageView imageview1;
	private TextView textview15;
	
	private Intent PAGE = new Intent();
	private DatabaseReference attendance_info = _firebase.getReference("attendance_info");
	private ChildEventListener _attendance_info_child_listener;
	private DatabaseReference attendance_db = _firebase.getReference("attendance_db");
	private ChildEventListener _attendance_db_child_listener;
	private DatabaseReference enrolled_student_info = _firebase.getReference("enrolled_student_info");
	private ChildEventListener _enrolled_student_info_child_listener;
	private Calendar date = Calendar.getInstance();
	private DatabaseReference subjects_info = _firebase.getReference("subjects_info");
	private ChildEventListener _subjects_info_child_listener;
	private SharedPreferences f;
	private AlertDialog.Builder dialog;
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.faculty_attendance_page);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear23 = findViewById(R.id.linear23);
		linear25 = findViewById(R.id.linear25);
		textview14 = findViewById(R.id.textview14);
		prof = findViewById(R.id.prof);
		start_end = findViewById(R.id.start_end);
		dtd = findViewById(R.id.dtd);
		textview3 = findViewById(R.id.textview3);
		subjlistdisplay = findViewById(R.id.subjlistdisplay);
		textview10 = findViewById(R.id.textview10);
		linear26 = findViewById(R.id.linear26);
		attrndancelistdisplay = findViewById(R.id.attrndancelistdisplay);
		textview17 = findViewById(R.id.textview17);
		textview18 = findViewById(R.id.textview18);
		textview19 = findViewById(R.id.textview19);
		textview20 = findViewById(R.id.textview20);
		save = findViewById(R.id.save);
		imageview1 = findViewById(R.id.imageview1);
		textview15 = findViewById(R.id.textview15);
		f = getSharedPreferences("f", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		d = new AlertDialog.Builder(this);
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_PRINTEXCEL();
			}
		});
		
		_attendance_info_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				attendance_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						ailist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								ailist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						aistr.add(_childKey);
						subjlistdisplay.setAdapter(new SubjlistdisplayAdapter(ailist));
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
						ailist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								ailist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						subjlistdisplay.setAdapter(new SubjlistdisplayAdapter(ailist));
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
						ailist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								ailist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						subjlistdisplay.setAdapter(new SubjlistdisplayAdapter(ailist));
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
						n = 1;
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
						n = 1;
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
		date = Calendar.getInstance();
		android.graphics.drawable.GradientDrawable Z1 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
		
		Z1.setCornerRadius(50);
		save.setElevation(16);
		save.setBackground(Z1);
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("PROF", getIntent().getStringExtra("PROF"));
		PAGE.putExtra("ID", getIntent().getStringExtra("ID"));
		PAGE.putExtra("AL", getIntent().getStringExtra("AL"));
		PAGE.putExtra("SUBJ", getIntent().getStringExtra("SUBJ"));
		PAGE.putExtra("USR", getIntent().getStringExtra("USR"));
		PAGE.putExtra("SHEETNO", getIntent().getStringExtra("SHEETNO"));
		n = 1;
		n_checker = 1;
		EML = getIntent().getStringExtra("EML");
		ID = getIntent().getStringExtra("ID");
		PROF = getIntent().getStringExtra("PROF");
		SUBJ = getIntent().getStringExtra("SUBJ");
		ATTENDANCECODE = getIntent().getStringExtra("AL");
		DATE = getIntent().getStringExtra("DATE");
		sheetno = Double.parseDouble(getIntent().getStringExtra("SHEETNO"));
		prof.setText("PROFESSOR: ".concat(PROF));
		dtd.setText("DATE: ".concat(DATE));
		_ERASENO();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("ID", getIntent().getStringExtra("ID"));
		PAGE.setClass(getApplicationContext(), FacultyCoursePageActivity.class);
		startActivity(PAGE);
		finish();
	}
	
	public void _GETCODE() {
		start_end.setText("START: ".concat(START.concat(" END: ".concat(END))));
		STDNT = S_STDNT;
		STARTTIME = START;
		_GETDATAATTENDANCE();
	}
	
	
	public void _GETDATAATTENDANCE() {
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
				N4 = adblist.size() - 1;
				L4 = adblist.size();
				for(int _repeat50 = 0; _repeat50 < (int)(L4); _repeat50++) {
					if (adblist.get((int)N4).get("L_AL").toString().equals(ATTENDANCECODE) && adblist.get((int)N4).get("L_STATUS").toString().equals("N/A")) {
						attendancedatabase = new HashMap<>();
						attendancedatabase.put("L_ID", adblist.get((int)N4).get("L_ID").toString());
						attendancedatabase.put("L_AL", adblist.get((int)N4).get("L_AL").toString());
						attendancedatabase.put("L_DATE", adblist.get((int)N4).get("L_DATE").toString());
						attendancedatabase.put("L_NAME", adblist.get((int)N4).get("L_NAME").toString());
						attendancedatabase.put("L_STDNTID", adblist.get((int)N4).get("L_STDNTID").toString());
						attendancedatabase.put("L_STATUS", "A");
						attendancedatabase.put("L_NO", "");
						attendancedatabase.put("L_TIME", "N/A");
						attendancedatabase.put("L_LOG", "SYSTEM");
						attendance_db.child(adblist.get((int)N4).get("L_ID").toString()).updateChildren(attendancedatabase);
						attendancedatabase.clear();
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
	
	
	public void _PRINTEXCEL() {
		if (true) {
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
					fileName = ATTENDANCECODE.concat("-".concat(SUBJ.concat(" -".concat(DATE.concat(".xls")))));
					//Save to File Path
					File sdCard = Environment.getExternalStorageDirectory();
					File directory = new File(sdCard.getAbsolutePath() + "/iSKAN AKO/EXCEL/BY FILE");
					
					//file path
					    File file = new File(directory, fileName);
					
					
					WorkbookSettings wbSettings = new WorkbookSettings();
					wbSettings.setLocale(new Locale("en", "EN"));
					WritableWorkbook workbook;
					try{
						workbook = Workbook.createWorkbook(file, wbSettings);
						       
						 //Excel sheet name. 0 represents first sheet
						        WritableSheet sheet = workbook.createSheet(DATE, 0);
						try{
							sheet.addCell(new Label(0, 0, "DATE:"));
							sheet.addCell(new Label(1, 0, DATE));
							sheet.addCell(new Label(3, 0, "START TIME:"));
							sheet.addCell(new Label(4, 0, STARTTIME));
							sheet.addCell(new Label(0, 1, "SUBJECT:"));
							sheet.addCell(new Label(1, 1, SUBJ));
							sheet.addCell(new Label(3, 1, "STUDENT:"));
							sheet.addCell(new Label(4, 1, STDNT));
							sheet.addCell(new Label(0, 3, "NO"));
							sheet.addCell(new Label(1, 3, "NAME"));
							sheet.addCell(new Label(2, 3, "STATUS"));
							sheet.addCell(new Label(3, 3, "TIME"));
							for(int i= 0; i < (int)(adblist.size()); i++) {
								if (adblist.get((int)i).get("L_AL").toString().equals(ATTENDANCECODE)) {
									sheet.addCell(new Label(0, 3 + Integer.parseInt(adblist.get((int)i).get("L_NO").toString()), adblist.get((int)i).get("L_NO").toString()));
									sheet.addCell(new Label(1, 3 + Integer.parseInt(adblist.get((int)i).get("L_NO").toString()), adblist.get((int)i).get("L_NAME").toString()));
									sheet.addCell(new Label(2, 3 + Integer.parseInt(adblist.get((int)i).get("L_NO").toString()), adblist.get((int)i).get("L_STATUS").toString()));
									sheet.addCell(new Label(3, 3 + Integer.parseInt(adblist.get((int)i).get("L_NO").toString()), adblist.get((int)i).get("L_TIME").toString()));
								}
								else {
									
								}
							}
						}catch(RowsExceededException e){
							d.setTitle("ERROR MESSAGE");
							d.setIcon(R.drawable.iskanako_01);
							d.setMessage(e.getMessage());
							dialog.setCancelable(false);
							d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							d.create().show();
						}catch (WriteException e) {
							SketchwareUtil.showMessage(getApplicationContext(), e.getMessage());
						}
						try{
							workbook.write();
							workbook.close();
							SketchwareUtil.showMessage(getApplicationContext(), fileName.concat(" is successfully saved!"));
						}catch(WriteException e){
							d.setTitle("ERROR MESSAGE");
							d.setIcon(R.drawable.iskanako_01);
							d.setMessage(e.getMessage());
							d.setCancelable(false);
							d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							d.create().show();
						}
					}catch(IOException e){
						d.setTitle("ERROR MESSAGE");
						d.setIcon(R.drawable.iskanako_01);
						d.setMessage(e.getMessage());
						d.setCancelable(false);
						d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						d.create().show();
					}
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
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
	
	
	public void _ADBGET() {
		GETNO = ST_NO;
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
			
			if (_data.get((int)_position).get("A_AL").toString().equals(ATTENDANCECODE) && _data.get((int)_position).get("A_SUBJ").toString().equals(SUBJ)) {
				main.setVisibility(View.VISIBLE);
				subject.setText(_data.get((int)_position).get("A_SUBJ").toString());
				studenttype.setText(_data.get((int)_position).get("A_STUDENT").toString());
				time.setText(_data.get((int)_position).get("A_TI").toString().concat("-".concat(_data.get((int)_position).get("A_TO").toString())));
				START = _data.get((int)_position).get("A_START").toString();
				END = _data.get((int)_position).get("A_END").toString();
				S_SUBJ = _data.get((int)_position).get("A_ID").toString();
				S_STDNT = _data.get((int)_position).get("A_STUDENT").toString();
				ATTENDANCEDATE = _data.get((int)_position).get("A_DATE").toString();
				_GETCODE();
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
				change = new HashMap<>();
				change.put("L_SHEETNO", String.valueOf((long)(sheetno)));
				attendance_db.child(_data.get((int)_position).get("L_ID").toString()).updateChildren(change);
				change.clear();
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