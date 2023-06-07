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
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class CreatesubjectActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> createroom = new HashMap<>();
	private String SCHOOLYEAR = "";
	private String SEMESTER = "";
	private String ID = "";
	private double MaxLength = 0;
	private String START = "";
	private String END = "";
	private String DATETEXT = "";
	private String DATESPLIT = "";
	private String MONDAY = "";
	private String TUESDAY = "";
	private String LENGHTDATE = "";
	private double lenght = 0;
	private String FILTERLINK1 = "";
	private String FILTERLINK2 = "";
	
	private ArrayList<String> sem_str = new ArrayList<>();
	private ArrayList<String> yearstr = new ArrayList<>();
	private ArrayList<String> timestr = new ArrayList<>();
	private ArrayList<String> timestr2 = new ArrayList<>();
	private ArrayList<String> progstr = new ArrayList<>();
	private ArrayList<String> datestr = new ArrayList<>();
	private ArrayList<String> labstr = new ArrayList<>();
	
	private ScrollView vscroll2;
	private LinearLayout linear1;
	private LinearLayout linear3;
	private TextView textview7;
	private EditText subjcode;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private LinearLayout linear29;
	private LinearLayout linear27;
	private LinearLayout linear26;
	private EditText starttime;
	private LinearLayout s_display;
	private EditText endtime;
	private LinearLayout e_display;
	private LinearLayout linear16;
	private LinearLayout linear25;
	private Button create_bttn;
	private EditText subject;
	private Spinner spinner6;
	private EditText studenttype;
	private Spinner course;
	private EditText laboratory;
	private Spinner spinner7;
	private TextView textview3;
	private LinearLayout linear28;
	private TextView textview10;
	private EditText date;
	private Spinner datespin;
	private EditText s_hour;
	private TextView textview2;
	private EditText s_min;
	private EditText s_t;
	private Spinner spinner2;
	private EditText e_hour;
	private TextView textview9;
	private EditText e_min;
	private EditText e_t;
	private Spinner spinner5;
	private EditText SCHLYR;
	private Spinner sy;
	private EditText SMSTR;
	private Spinner sem;
	
	private Intent PAGE = new Intent();
	private DatabaseReference subjects_info = _firebase.getReference("subjects_info");
	private ChildEventListener _subjects_info_child_listener;
	private DatabaseReference subject_db = _firebase.getReference("subject_db");
	private ChildEventListener _subject_db_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.createsubject);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll2 = findViewById(R.id.vscroll2);
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		textview7 = findViewById(R.id.textview7);
		subjcode = findViewById(R.id.subjcode);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		linear29 = findViewById(R.id.linear29);
		linear27 = findViewById(R.id.linear27);
		linear26 = findViewById(R.id.linear26);
		starttime = findViewById(R.id.starttime);
		s_display = findViewById(R.id.s_display);
		endtime = findViewById(R.id.endtime);
		e_display = findViewById(R.id.e_display);
		linear16 = findViewById(R.id.linear16);
		linear25 = findViewById(R.id.linear25);
		create_bttn = findViewById(R.id.create_bttn);
		subject = findViewById(R.id.subject);
		spinner6 = findViewById(R.id.spinner6);
		studenttype = findViewById(R.id.studenttype);
		course = findViewById(R.id.course);
		laboratory = findViewById(R.id.laboratory);
		spinner7 = findViewById(R.id.spinner7);
		textview3 = findViewById(R.id.textview3);
		linear28 = findViewById(R.id.linear28);
		textview10 = findViewById(R.id.textview10);
		date = findViewById(R.id.date);
		datespin = findViewById(R.id.datespin);
		s_hour = findViewById(R.id.s_hour);
		textview2 = findViewById(R.id.textview2);
		s_min = findViewById(R.id.s_min);
		s_t = findViewById(R.id.s_t);
		spinner2 = findViewById(R.id.spinner2);
		e_hour = findViewById(R.id.e_hour);
		textview9 = findViewById(R.id.textview9);
		e_min = findViewById(R.id.e_min);
		e_t = findViewById(R.id.e_t);
		spinner5 = findViewById(R.id.spinner5);
		SCHLYR = findViewById(R.id.SCHLYR);
		sy = findViewById(R.id.sy);
		SMSTR = findViewById(R.id.SMSTR);
		sem = findViewById(R.id.sem);
		
		//OnTouch
		starttime.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 starttime.setVisibility(View.GONE);
					s_display.setVisibility(View.VISIBLE);
					s_hour.requestFocus();
					s_hour.setSelection(s_hour.getText().length());
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		endtime.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 endtime.setVisibility(View.GONE);
					e_display.setVisibility(View.VISIBLE);
					e_hour.requestFocus();
					e_hour.setSelection(e_hour.getText().length());
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		create_bttn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				START = s_hour.getText().toString().concat(":".concat(s_min.getText().toString().concat(" ".concat(s_t.getText().toString()))));
				END = e_hour.getText().toString().concat(":".concat(e_min.getText().toString().concat(" ".concat(e_t.getText().toString()))));
				if ((subject.getText().toString().equals("") || (studenttype.getText().toString().equals("") || START.equals(""))) || ((END.equals("") || subjcode.getText().toString().equals("")) || date.getText().toString().equals(""))) {
					SketchwareUtil.showMessage(getApplicationContext(), "Complete your room details.");
				}
				else {
					FILTERLINK1 = "https://sites.google.com/view/iskanako/".concat(ID.concat("/".concat(subjcode.getText().toString().toUpperCase().concat("/".concat(studenttype.getText().toString().toUpperCase().concat("/".concat(SCHOOLYEAR)))))));
					FILTERLINK2 = FILTERLINK1.replace(" ", "");
					createroom = new HashMap<>();
					createroom.put("R_UID", ID);
					createroom.put("R_SUBJECT", subject.getText().toString().toUpperCase());
					createroom.put("R_STUDENT", studenttype.getText().toString().toUpperCase());
					createroom.put("R_TIMEIN", START);
					createroom.put("R_TIMEOUT", END);
					createroom.put("R_ROOM", laboratory.getText().toString());
					createroom.put("R_ID", "NONE");
					createroom.put("R_DATE", date.getText().toString());
					createroom.put("R_SY", SCHOOLYEAR);
					createroom.put("R_SEM", SEMESTER);
					createroom.put("R_CHECK", "FALSE");
					createroom.put("R_CODE", subjcode.getText().toString().toUpperCase());
					createroom.put("R_LINK", FILTERLINK2);
					subjects_info.push().updateChildren(createroom);
					createroom.clear();
					if (true) {
						_CLEARDATA();
						SketchwareUtil.showMessage(getApplicationContext(), "Room succesfully created.");
						PAGE.setClass(getApplicationContext(), FacultyprofileActivity.class);
						startActivity(PAGE);
						finish();
					}
				}
			}
		});
		
		course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					studenttype.setText("BSIT-1");
				}
				if (_position == 1) {
					studenttype.setText("BSIT-2");
				}
				if (_position == 2) {
					studenttype.setText("BSIT-3");
				}
				if (_position == 3) {
					studenttype.setText("BSIT-4");
				}
				if (_position == 4) {
					studenttype.setText("DICT-1");
				}
				if (_position == 5) {
					studenttype.setText("DICT-2");
				}
				if (_position == 6) {
					studenttype.setText("DICT-3");
				}
				if (_position == 7) {
					studenttype.setText("DIT-1");
				}
				if (_position == 8) {
					studenttype.setText("DIT-2");
				}
				if (_position == 9) {
					studenttype.setText("DIT-3");
				}
				if (_position == 10) {
					studenttype.setText("DpET-1");
				}
				if (_position == 11) {
					studenttype.setText("DCET-1");
				}
				if (_position == 12) {
					studenttype.setText("DCET-2");
				}
				if (_position == 13) {
					studenttype.setText("DCET-3");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					laboratory.setText("ICT LAB-1");
				}
				if (_position == 1) {
					laboratory.setText("ICT LAB-2");
				}
				if (_position == 2) {
					laboratory.setText("ICT LAB-3");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		textview10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				date.setText("");
			}
		});
		
		datespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					if (date.getText().toString().equals("")) {
						date.setText("MONDAY");
					}
					else {
						if (date.getText().toString().contains("MONDAY")) {
							date.setText(date.getText().toString());
						}
						else {
							date.setText("MONDAY".concat("/".concat(date.getText().toString())));
						}
					}
				}
				if (_position == 1) {
					if (date.getText().toString().equals("")) {
						date.setText("TUESDAY");
					}
					else {
						if (date.getText().toString().contains("TUESDAY")) {
							date.setText(date.getText().toString());
						}
						else {
							date.setText(date.getText().toString().concat("/".concat("TUESDAY")));
						}
					}
				}
				if (_position == 2) {
					if (date.getText().toString().equals("")) {
						date.setText("WEDNESDAY");
					}
					else {
						if (date.getText().toString().contains("WEDNESDAY")) {
							date.setText(date.getText().toString());
						}
						else {
							date.setText(date.getText().toString().concat("/".concat("WEDNESDAY")));
						}
					}
				}
				if (_position == 3) {
					if (date.getText().toString().equals("")) {
						date.setText("THURSDAY");
					}
					else {
						if (date.getText().toString().contains("THURSDAY")) {
							date.setText(date.getText().toString());
						}
						else {
							date.setText(date.getText().toString().concat("/".concat("THURSDAY")));
						}
					}
				}
				if (_position == 4) {
					if (date.getText().toString().equals("")) {
						date.setText("FRIDAY");
					}
					else {
						if (date.getText().toString().contains("THURSDAY")) {
							date.setText(date.getText().toString());
						}
						else {
							date.setText(date.getText().toString().concat("/".concat("FRIDAY")));
						}
					}
				}
				if (_position == 5) {
					if (date.getText().toString().equals("")) {
						date.setText("SATURDAY");
					}
					else {
						if (date.getText().toString().contains("SATURDAY")) {
							date.setText(date.getText().toString());
						}
						else {
							date.setText(date.getText().toString().concat("/".concat("SATURDAY")));
						}
					}
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		s_hour.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 0) {
					s_display.setVisibility(View.GONE);
					starttime.setVisibility(View.VISIBLE);
					starttime.requestFocus();
					starttime.setSelection(starttime.getText().length());
				}
				else {
					if (_charSeq.length() == 2) {
						s_min.requestFocus();
						s_min.setSelection(s_min.getText().length());
					}
					else {
						
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		s_min.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 0) {
					s_hour.requestFocus();
					s_hour.setSelection(s_hour.getText().length());
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					s_t.setText("AM");
				}
				if (_position == 1) {
					s_t.setText("PM");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		e_hour.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 0) {
					e_display.setVisibility(View.GONE);
					endtime.setVisibility(View.VISIBLE);
					endtime.requestFocus();
					endtime.setSelection(endtime.getText().length());
				}
				else {
					if (_charSeq.length() == 2) {
						e_min.requestFocus();
						e_min.setSelection(e_min.getText().length());
					}
					else {
						
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		e_min.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 0) {
					e_hour.requestFocus();
					e_hour.setSelection(e_hour.getText().length());
				}
				else {
					
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					e_t.setText("AM");
				}
				if (_position == 1) {
					e_t.setText("PM");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		sy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					SCHOOLYEAR = "2022-2023";
					SCHLYR.setText(SCHOOLYEAR);
				}
				if (_position == 1) {
					SCHOOLYEAR = "2023-2024";
					SCHLYR.setText(SCHOOLYEAR);
				}
				if (_position == 2) {
					SCHOOLYEAR = "2024-2025";
					SCHLYR.setText(SCHOOLYEAR);
				}
				if (_position == 3) {
					SCHOOLYEAR = "2025-2026";
					SCHLYR.setText(SCHOOLYEAR);
				}
				if (_position == 4) {
					SCHOOLYEAR = "2026-2027";
					SCHLYR.setText(SCHOOLYEAR);
				}
				if (_position == 5) {
					SCHOOLYEAR = "2027-2028";
					SCHLYR.setText(SCHOOLYEAR);
				}
				if (_position == 6) {
					SCHOOLYEAR = "2028-2029";
					SCHLYR.setText(SCHOOLYEAR);
				}
				if (_position == 7) {
					SCHOOLYEAR = "2029-2030";
					SCHLYR.setText(SCHOOLYEAR);
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					SEMESTER = "[1st Semester]";
					SMSTR.setText(SEMESTER);
				}
				if (_position == 1) {
					SEMESTER = "[2nd Semester]";
					SMSTR.setText(SEMESTER);
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
		
		_subject_db_child_listener = new ChildEventListener() {
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
		subject_db.addChildEventListener(_subject_db_child_listener);
	}
	
	private void initializeLogic() {
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		ID = getIntent().getStringExtra("ID");
		_SPIN();
		_DISPLAY();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), FacultyprofileActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _SPIN() {
		sem_str.add("1st Semester");
		sem_str.add("2nd Semester");
		sem.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, sem_str));
		((ArrayAdapter)sem.getAdapter()).notifyDataSetChanged();
		yearstr.add("2022-2023");
		yearstr.add("2023-2024");
		yearstr.add("2024-2025");
		yearstr.add("2025-2026");
		yearstr.add("2026-2027");
		yearstr.add("2027-2028");
		yearstr.add("2028-2029");
		yearstr.add("2029-2030");
		sy.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, yearstr));
		((ArrayAdapter)sy.getAdapter()).notifyDataSetChanged();
		timestr.add("AM");
		timestr.add("PM");
		spinner2.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, timestr));
		((ArrayAdapter)spinner2.getAdapter()).notifyDataSetChanged();
		timestr2.add("AM");
		timestr2.add("PM");
		spinner5.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, timestr2));
		((ArrayAdapter)spinner5.getAdapter()).notifyDataSetChanged();
		progstr.add("BSIT-1");
		progstr.add("BSIT-2");
		progstr.add("BSIT-3");
		progstr.add("BSIT-4");
		progstr.add("DICT-1");
		progstr.add("DICT-2");
		progstr.add("DICT-3");
		progstr.add("DIT-1");
		progstr.add("DIT-2");
		progstr.add("DIT-3");
		progstr.add("DpET-1");
		progstr.add("DCET-1");
		progstr.add("DCET-2");
		progstr.add("DCET-3");
		course.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, progstr));
		((ArrayAdapter)course.getAdapter()).notifyDataSetChanged();
		datestr.add("MONDAY");
		datestr.add("TUESDAY");
		datestr.add("WEDNESDAY");
		datestr.add("THURSDAY");
		datestr.add("FRIDAY");
		datestr.add("SATURDAY");
		datespin.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, datestr));
		((ArrayAdapter)datespin.getAdapter()).notifyDataSetChanged();
		labstr.add("LAB-1");
		labstr.add("LAB-2");
		labstr.add("LAB-3");
		spinner7.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, labstr));
		((ArrayAdapter)spinner7.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _DISPLAY() {
		s_display.setVisibility(View.GONE);
		e_display.setVisibility(View.GONE);
		s_hour.setFilters (new InputFilter[] { new InputFilter.LengthFilter(2)} );
		
		s_min.setFilters (new InputFilter[] { new InputFilter.LengthFilter(2)} );
		
		e_hour.setFilters (new InputFilter[] { new InputFilter.LengthFilter(2)} );
		
		e_min.setFilters (new InputFilter[] { new InputFilter.LengthFilter(2)} );
		
		subjcode.setFilters (new InputFilter[] { new InputFilter.LengthFilter(12)} );
		
		subject.setFilters (new InputFilter[] { new InputFilter.LengthFilter(50)} );
		
	}
	
	
	public void _CLEARDATA() {
		START = "";
		END = "";
		SCHOOLYEAR = "";
		SEMESTER = "";
		subjcode.setText("");
		subject.setText("");
		studenttype.setText("");
		SCHLYR.setText("");
		SMSTR.setText("");
		starttime.setText("");
		s_hour.setText("");
		s_min.setText("");
		endtime.setText("");
		e_hour.setText("");
		e_min.setText("");
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