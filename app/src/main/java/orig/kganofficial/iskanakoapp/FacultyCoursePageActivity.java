package orig.kganofficial.iskanakoapp;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

public class FacultyCoursePageActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String ID = "";
	private String SUBJ = "";
	private String DATETODAY = "";
	private double N = 0;
	private double L = 0;
	private String USR = "";
	private String SID = "";
	private String fileName = "";
	private String GENERATE = "";
	private double N1 = 0;
	private double L1 = 0;
	private double i = 0;
	private String DATE = "";
	private String STARTTIME = "";
	private String STDNT = "";
	private String ATTENDANCECODE = "";
	private String GETSID = "";
	private double sheetno = 0;
	private String AC = "";
	private HashMap<String, Object> adddata = new HashMap<>();
	private double N2 = 0;
	private double L2 = 0;
	private String date1 = "";
	private String date2 = "";
	private String D1 = "";
	private double N3 = 0;
	private double L3 = 0;
	private String ROWNO = "";
	private double DN = 0;
	private String D2 = "";
	private String D3 = "";
	
	private ArrayList<HashMap<String, Object>> ailist = new ArrayList<>();
	private ArrayList<String> aistr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> dblist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> adblist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout header;
	private LinearLayout save;
	private LinearLayout linear18;
	private LinearLayout linear8;
	private ListView todaylist;
	private ListView historylist;
	private ImageView imageview3;
	private LinearLayout linear35;
	private TextView title;
	private TextView subtext;
	private ImageView imageview1;
	private TextView textview15;
	private TextView today;
	private TextView textview13;
	private TextView history;
	
	private Intent PAGE = new Intent();
	private DatabaseReference attendance_info = _firebase.getReference("attendance_info");
	private ChildEventListener _attendance_info_child_listener;
	private Calendar date = Calendar.getInstance();
	private DatabaseReference attendance_db = _firebase.getReference("attendance_db");
	private ChildEventListener _attendance_db_child_listener;
	private AlertDialog.Builder d;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.faculty_course_page);
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
		header = findViewById(R.id.header);
		save = findViewById(R.id.save);
		linear18 = findViewById(R.id.linear18);
		linear8 = findViewById(R.id.linear8);
		todaylist = findViewById(R.id.todaylist);
		historylist = findViewById(R.id.historylist);
		imageview3 = findViewById(R.id.imageview3);
		linear35 = findViewById(R.id.linear35);
		title = findViewById(R.id.title);
		subtext = findViewById(R.id.subtext);
		imageview1 = findViewById(R.id.imageview1);
		textview15 = findViewById(R.id.textview15);
		today = findViewById(R.id.today);
		textview13 = findViewById(R.id.textview13);
		history = findViewById(R.id.history);
		d = new AlertDialog.Builder(this);
		dialog = new AlertDialog.Builder(this);
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("iSKAN AKO");
				dialog.setIcon(R.drawable.iskanako_01);
				dialog.setMessage("This feature is undermaintenance, you can't print all attendance of this subject this time.");
				dialog.setCancelable(false);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		todaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (USR.equals("FACULTY")) {
					
					PAGE.putExtra("PROF", ailist.get((int)_position).get("A_PROF").toString());
					PAGE.putExtra("AL", ailist.get((int)_position).get("A_AL").toString());
					PAGE.putExtra("DATE", ailist.get((int)_position).get("A_DATE").toString());
					PAGE.putExtra("SID", ailist.get((int)_position).get("A_SID").toString());
					PAGE.putExtra("SHEETNO", ailist.get((int)_position).get("A_SHEETNO").toString());
					PAGE.setClass(getApplicationContext(), FacultyAttendancePageActivity.class);
					startActivity(PAGE);
					finish();
				} 
				else if (USR.equals("STUDENT")){
					
					PAGE.putExtra("AL", ailist.get((int)_position).get("A_AL").toString());
					PAGE.putExtra("DATE", ailist.get((int)_position).get("A_DATE").toString());
					PAGE.setClass(getApplicationContext(), StudentattendanceinfoActivity.class);
					startActivity(PAGE);
					finish();
					
				}
				else {
					 
				}
			}
		});
		
		todaylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
		
		historylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (USR.equals("FACULTY")) {
					
					PAGE.putExtra("PROF", ailist.get((int)_position).get("A_PROF").toString());
					PAGE.putExtra("AL", ailist.get((int)_position).get("A_AL").toString());
					PAGE.putExtra("DATE", ailist.get((int)_position).get("A_DATE").toString());
					PAGE.putExtra("SID", ailist.get((int)_position).get("A_SID").toString());
					PAGE.putExtra("SHEETNO", ailist.get((int)_position).get("A_SHEETNO").toString());
					PAGE.setClass(getApplicationContext(), FacultyAttendancePageActivity.class);
					startActivity(PAGE);
					finish();
				} 
				else if (USR.equals("STUDENT")){
					
					PAGE.putExtra("AL", ailist.get((int)_position).get("A_AL").toString());
					PAGE.putExtra("DATE", ailist.get((int)_position).get("A_DATE").toString());
					PAGE.setClass(getApplicationContext(), StudentattendanceinfoActivity.class);
					startActivity(PAGE);
					finish();
					
				}
				else {
					 
				}
			}
		});
		
		today.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				todaylist.setVisibility(View.VISIBLE);
				historylist.setVisibility(View.GONE);
			}
		});
		
		history.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				todaylist.setVisibility(View.GONE);
				historylist.setVisibility(View.VISIBLE);
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
						todaylist.setAdapter(new TodaylistAdapter(ailist));
						((BaseAdapter)todaylist.getAdapter()).notifyDataSetChanged();
						historylist.setAdapter(new HistorylistAdapter(ailist));
						((BaseAdapter)historylist.getAdapter()).notifyDataSetChanged();
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
						todaylist.setAdapter(new TodaylistAdapter(ailist));
						((BaseAdapter)todaylist.getAdapter()).notifyDataSetChanged();
						historylist.setAdapter(new HistorylistAdapter(ailist));
						((BaseAdapter)historylist.getAdapter()).notifyDataSetChanged();
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
						todaylist.setAdapter(new TodaylistAdapter(ailist));
						((BaseAdapter)todaylist.getAdapter()).notifyDataSetChanged();
						historylist.setAdapter(new HistorylistAdapter(ailist));
						((BaseAdapter)historylist.getAdapter()).notifyDataSetChanged();
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
		AC = "";
		sheetno = 1;
		_DESIGN();
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("USR", getIntent().getStringExtra("USR"));
		historylist.setVisibility(View.GONE);
		todaylist.setVisibility(View.VISIBLE);
		USR = getIntent().getStringExtra("USR");
		if (USR.equals("FACULTY")) {
			
			PAGE.putExtra("SUBJ", getIntent().getStringExtra("SUBJ"));
			PAGE.putExtra("ID", getIntent().getStringExtra("ID"));
			PAGE.putExtra("SID", getIntent().getStringExtra("SID"));
			PAGE.putExtra("STDNT", getIntent().getStringExtra("STDNT"));
			STDNT = getIntent().getStringExtra("STDNT");
			SID = getIntent().getStringExtra("SID");
			ID = getIntent().getStringExtra("ID");
			SUBJ = getIntent().getStringExtra("SUBJ");
			DATETODAY = new SimpleDateFormat("MM-dd-YYYY").format(date.getTime());
			_GETSHEETNO();
		} 
		else {
			PAGE.putExtra("SCHED", getIntent().getStringExtra("SCHED"));
			PAGE.putExtra("PROF", getIntent().getStringExtra("PROF"));
			PAGE.putExtra("ID", getIntent().getStringExtra("ID"));
			PAGE.putExtra("STID", getIntent().getStringExtra("STID"));
			PAGE.putExtra("SEM", getIntent().getStringExtra("SEM"));
			PAGE.putExtra("SUBJ", getIntent().getStringExtra("SUBJ"));
			title.setText("STUDENT INFORMATION");
			subtext.setText("Your Attendance List");
			save.setVisibility(View.GONE);
			ID = getIntent().getStringExtra("ID");
			SUBJ = getIntent().getStringExtra("SUBJ");
			DATETODAY = new SimpleDateFormat("MM-dd-YYYY").format(date.getTime());
		}
	}
	
	@Override
	public void onBackPressed() {
		if (getIntent().getStringExtra("USR").equals("FACULTY")) {
			
			PAGE.setClass(getApplicationContext(), AttendancehistoryActivity.class);
			startActivity(PAGE);
			finish();
		} 
		else if (getIntent().getStringExtra("USR").equals("STUDENT")){
			
			PAGE.setClass(getApplicationContext(), ProfileStudentActivity.class);
			startActivity(PAGE);
			finish();
			
		}
		else {
			 
		}
	}
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		header.setElevation(16);
		header.setBackground(a);
		
		android.graphics.drawable.GradientDrawable D2 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
		
		D2.setCornerRadius(50);
		save.setElevation(10);
		save.setBackground(D2);
	}
	
	
	public void _PRINTEXCEL() {
		String RNDLTTRS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		GENERATE = "";
		for (int i = 0; i < 8; i++) {
					GENERATE = GENERATE + RNDLTTRS.charAt(new java.util.Random().nextInt(RNDLTTRS.length()));
		}
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
					fileName = SUBJ.concat("-".concat(STDNT.concat(" -".concat(new SimpleDateFormat("MM-DD-YY-hh-mm-a").format(date.getTime()).concat(".xls")))));
					date1 = "";
					date2 = "";
					N1 = 0;
					L1 = adblist.size();
					//Save to File Path
					File sdCard = Environment.getExternalStorageDirectory();
					File directory = new File(sdCard.getAbsolutePath() + "/iSKAN AKO/EXCEL/BY SUBJECTS");
					
					//file path
					    File file = new File(directory, fileName);
					
					
					WorkbookSettings wbSettings = new WorkbookSettings();
					wbSettings.setLocale(new Locale("en", "EN"));
					WritableWorkbook workbook;
					try{
						workbook = Workbook.createWorkbook(file, wbSettings);
						       
						 //Excel sheet name. 0 represents first sheet
						        WritableSheet sheet = workbook.createSheet(SUBJ.concat("-".concat(STDNT)), 0);
						while(!(N1 == L1)) {
							if (SID.equals(adblist.get((int)N1).get("L_SUBJID").toString())) {
								
								if (true) {
									try{
										sheet.addCell(new Label(0, 0, "NO"));
										sheet.addCell(new Label(1, 0, "NAME"));
										sheet.addCell(new Label(0, 0 + Integer.parseInt(adblist.get((int)N1).get("L_NO").toString()), adblist.get((int)N1).get("L_NO").toString()));
										sheet.addCell(new Label(1, 0 + Integer.parseInt(adblist.get((int)N1).get("L_NO").toString()), adblist.get((int)N1).get("L_NAME").toString()));
										sheet.addCell(new Label(1 + Integer.parseInt(adblist.get((int)N1).get("L_SHEETNO").toString()), 0, adblist.get((int)N1).get("L_DATE").toString()));
										sheet.addCell(new Label(1 + Integer.parseInt(adblist.get((int)N1).get("L_SHEETNO").toString()), 0 + Integer.parseInt(adblist.get((int)N1).get("L_NO").toString()), adblist.get((int)N1).get("L_STATUS").toString()));
									}catch(RowsExceededException e){
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
									}catch (WriteException e) {
										SketchwareUtil.showMessage(getApplicationContext(), e.getMessage());
									}
								}
							} 
							N1++;
						}
						try{
							workbook.write();
							workbook.close();
							SketchwareUtil.showMessage(getApplicationContext(), fileName.concat(" is saving..."));
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
	
	
	public void _GETSHEETNO() {
		if (true) {
			
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
					N2 = 0;
					L2 = ailist.size();
					while(!(N2 == (L2 - 1))) {
						if (ailist.get((int)N2).get("A_SUBJ").toString().equals(SUBJ) && ailist.get((int)N2).get("A_UID").toString().equals(ID)) {
							if (Double.parseDouble(ailist.get((int)N2).get("A_SHEETNO").toString()) > sheetno) {
								
								adddata = new HashMap<>();
								adddata.put("A_SHEETNO", String.valueOf((long)(sheetno)));
								attendance_info.child(ailist.get((int)N2).get("A_ID").toString()).updateChildren(adddata);
								adddata.clear();
								sheetno = Double.parseDouble(ailist.get((int)N2).get("A_SHEETNO").toString());
							} 
							if (!(sheetno == 0) && ailist.get((int)N2).get("A_SHEETNO").toString().equals("0")) {
								
								adddata = new HashMap<>();
								adddata.put("A_SHEETNO", String.valueOf((long)(sheetno + 1)));
								attendance_info.child(ailist.get((int)N2).get("A_ID").toString()).updateChildren(adddata);
								adddata.clear();
							} 
						}
						else {
							
						}
						N2++;
					}
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		} 
	}
	
	
	public void _AUTOSET() {
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
				N3 = 0;
				L3 = adblist.size();
				while(!(N3 == L3)) {
					if (adblist.get((int)N3).get("L_AL").toString().equals(AC)) {
						adddata = new HashMap<>();
						adddata.put("L_SHEETNO", ROWNO);
						attendance_db.child(adblist.get((int)N3).get("L_ID").toString()).updateChildren(adddata);
						adddata.clear();
					}
					else {
						
					}
					N3++;
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	public class TodaylistAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public TodaylistAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.openscan, null);
			}
			
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final LinearLayout linear13 = _view.findViewById(R.id.linear13);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView date = _view.findViewById(R.id.date);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView start = _view.findViewById(R.id.start);
			final ImageView openscan = _view.findViewById(R.id.openscan);
			
			if (_data.get((int)_position).get("A_DATE").toString().equals(DATETODAY)) {
				if (_data.get((int)_position).get("A_SUBJ").toString().equals(SUBJ) && _data.get((int)_position).get("A_UID").toString().equals(ID)) {
					if (USR.equals("FACULTY")) {
						openscan.setVisibility(View.VISIBLE);
					}
					else {
						openscan.setVisibility(View.GONE);
					}
					linear9.setVisibility(View.VISIBLE);
					android.graphics.drawable.GradientDrawable D1 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
					
					D1.setCornerRadius(30);
					linear9.setElevation(16);
					linear9.setBackground(D1);
					date.setText(_data.get((int)_position).get("A_DATE").toString());
					start.setText(_data.get((int)_position).get("A_START").toString());
					AC = _data.get((int)_position).get("A_AL").toString();
					ROWNO = _data.get((int)_position).get("A_SHEETNO").toString();
					openscan.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							d.setTitle("HELLO!");
							d.setIcon(R.drawable.unex_black_logo);
							d.setMessage("Do you want to open this attendance again?");
							d.setPositiveButton("YES", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									PAGE.putExtra("AL", _data.get((int)_position).get("A_AL").toString());
									PAGE.putExtra("SID", _data.get((int)_position).get("A_SID").toString());
									PAGE.putExtra("AID", _data.get((int)_position).get("A_ID").toString());
									PAGE.putExtra("STUDENT", _data.get((int)_position).get("A_STUDENT").toString());
									PAGE.putExtra("PROF", _data.get((int)_position).get("A_PROF").toString());
									PAGE.setClass(getApplicationContext(), QrcodescannerActivity.class);
									startActivity(PAGE);
									finish();
								}
							});
							d.setNegativeButton("NO", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							d.setCancelable(false);
							d.create().show();
						}
					});
				}
				else {
					linear9.setVisibility(View.GONE);
				}
			}
			else {
				linear9.setVisibility(View.GONE);
			}
			
			return _view;
		}
	}
	
	public class HistorylistAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public HistorylistAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.date, null);
			}
			
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView date = _view.findViewById(R.id.date);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView start = _view.findViewById(R.id.start);
			
			if (!_data.get((int)_position).get("A_DATE").toString().equals(DATETODAY)) {
				if (_data.get((int)_position).get("A_SUBJ").toString().equals(SUBJ) && _data.get((int)_position).get("A_UID").toString().equals(ID)) {
					linear9.setVisibility(View.VISIBLE);
					android.graphics.drawable.GradientDrawable D3 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
					
					D3.setCornerRadius(30);
					linear9.setElevation(16);
					linear9.setBackground(D3);
					date.setText(_data.get((int)_position).get("A_DATE").toString());
					start.setText(_data.get((int)_position).get("A_START").toString());
				}
				else {
					linear9.setVisibility(View.GONE);
				}
			}
			else {
				linear9.setVisibility(View.GONE);
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