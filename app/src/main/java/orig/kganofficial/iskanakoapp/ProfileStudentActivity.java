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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class ProfileStudentActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String sresult = "";
	CodeScanner mCodeScanner;
	private String EML = "";
	private String COURSE = "";
	private String YEAR = "";
	private String SECTION = "";
	private String BDAY = "";
	private String SEX = "";
	private String ID = "";
	private String NAME = "";
	private String FN = "";
	private String MI = "";
	private String SF = "";
	private String LN = "";
	private String URL = "";
	private String PG = "";
	private HashMap<String, Object> statuschange = new HashMap<>();
	private double ARRAY = 0;
	private double position = 0;
	private String NAME2 = "";
	private String FULLNAME = "";
	private String DSPLY = "";
	private double page = 0;
	private String d1 = "";
	private HashMap<String, Object> createjoinsubj = new HashMap<>();
	private double N = 0;
	private double L = 0;
	private double N2 = 0;
	private double L2 = 0;
	private String STR = "";
	private String STDNTID = "";
	private String TES = "";
	private String EMAIL = "";
	private String LASTNAME = "";
	private String GETLASTNAME = "";
	
	private ArrayList<HashMap<String, Object>> userlist = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> enrolledlist = new ArrayList<>();
	private ArrayList<String> enrolledstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> subject_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> student_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> faculty_list = new ArrayList<>();
	
	private LinearLayout profilepage;
	private LinearLayout profiledisplay;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private LinearLayout swipearea;
	private LinearLayout linear12;
	private LinearLayout linear6;
	private TextView textview5;
	private TextView name;
	private TextView course_year_sec;
	private LinearLayout linear16;
	private ImageView edit;
	private CircleImageView circleimageview2;
	private LinearLayout line;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private ListView status;
	private TextView textview3;
	private TextView id;
	private TextView textview1;
	private TextView sex;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private ImageView qrcodebttn;
	private TextView textview8;
	private ImageView subjects;
	private ImageView add;
	private ListView listview2;
	private LinearLayout joinpage;
	private ImageView imageview2;
	private LinearLayout linear20;
	private LinearLayout pw_box;
	private Button join_bttn;
	private TextView textview7;
	private TextView textview2;
	private ImageView imageview5;
	private EditText subjid;
	private ImageView imageview6;
	
	private Intent PAGE = new Intent();
	private DatabaseReference student_user_info = _firebase.getReference("student_user_info");
	private ChildEventListener _student_user_info_child_listener;
	private AlertDialog.Builder exit;
	private DatabaseReference student_join = _firebase.getReference("student_join");
	private ChildEventListener _student_join_child_listener;
	private SharedPreferences FILEDATA;
	private DatabaseReference subjects_info = _firebase.getReference("subjects_info");
	private ChildEventListener _subjects_info_child_listener;
	private DatabaseReference faculty_user_info = _firebase.getReference("faculty_user_info");
	private ChildEventListener _faculty_user_info_child_listener;
	private DatabaseReference enrolled_student_info = _firebase.getReference("enrolled_student_info");
	private ChildEventListener _enrolled_student_info_child_listener;
	private DatabaseReference enrolled_student = _firebase.getReference("enrolled_student");
	private ChildEventListener _enrolled_student_child_listener;
	private FirebaseAuth users;
	private OnCompleteListener<AuthResult> _users_create_user_listener;
	private OnCompleteListener<AuthResult> _users_sign_in_listener;
	private OnCompleteListener<Void> _users_reset_password_listener;
	private OnCompleteListener<Void> users_updateEmailListener;
	private OnCompleteListener<Void> users_updatePasswordListener;
	private OnCompleteListener<Void> users_emailVerificationSentListener;
	private OnCompleteListener<Void> users_deleteUserListener;
	private OnCompleteListener<Void> users_updateProfileListener;
	private OnCompleteListener<AuthResult> users_phoneAuthListener;
	private OnCompleteListener<AuthResult> users_googleSignInListener;
	
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile_student);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		profilepage = findViewById(R.id.profilepage);
		profiledisplay = findViewById(R.id.profiledisplay);
		linear13 = findViewById(R.id.linear13);
		linear14 = findViewById(R.id.linear14);
		swipearea = findViewById(R.id.swipearea);
		linear12 = findViewById(R.id.linear12);
		linear6 = findViewById(R.id.linear6);
		textview5 = findViewById(R.id.textview5);
		name = findViewById(R.id.name);
		course_year_sec = findViewById(R.id.course_year_sec);
		linear16 = findViewById(R.id.linear16);
		edit = findViewById(R.id.edit);
		circleimageview2 = findViewById(R.id.circleimageview2);
		line = findViewById(R.id.line);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		status = findViewById(R.id.status);
		textview3 = findViewById(R.id.textview3);
		id = findViewById(R.id.id);
		textview1 = findViewById(R.id.textview1);
		sex = findViewById(R.id.sex);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		qrcodebttn = findViewById(R.id.qrcodebttn);
		textview8 = findViewById(R.id.textview8);
		subjects = findViewById(R.id.subjects);
		add = findViewById(R.id.add);
		listview2 = findViewById(R.id.listview2);
		joinpage = findViewById(R.id.joinpage);
		imageview2 = findViewById(R.id.imageview2);
		linear20 = findViewById(R.id.linear20);
		pw_box = findViewById(R.id.pw_box);
		join_bttn = findViewById(R.id.join_bttn);
		textview7 = findViewById(R.id.textview7);
		textview2 = findViewById(R.id.textview2);
		imageview5 = findViewById(R.id.imageview5);
		subjid = findViewById(R.id.subjid);
		imageview6 = findViewById(R.id.imageview6);
		exit = new AlertDialog.Builder(this);
		FILEDATA = getSharedPreferences("FILEDATA", Activity.MODE_PRIVATE);
		users = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), EditProfileActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		linear18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), QrcodegeneratoronlineActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		subjects.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				page = 1;
				_DISPLAY();
			}
		});
		
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				page = 3;
				_DISPLAY();
			}
		});
		
		listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
				PAGE.putExtra("USR", "STUDENT");
				PAGE.putExtra("ID", enrolledlist.get((int)_position).get("ST_UID").toString());
				PAGE.putExtra("SUBJ", enrolledlist.get((int)_position).get("ST_SUBJNAME").toString());
				PAGE.putExtra("SCHED", enrolledlist.get((int)_position).get("ST_SCHEDULE").toString());
				PAGE.putExtra("PROF", enrolledlist.get((int)_position).get("ST_PROF").toString());
				PAGE.putExtra("STID", enrolledlist.get((int)_position).get("ST_STUDENTID").toString());
				PAGE.putExtra("SEM", enrolledlist.get((int)_position).get("ST_SEM").toString());
				PAGE.setClass(getApplicationContext(), FacultyCoursePageActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		imageview2.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		join_bttn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_GETINFO();
				if (subjid.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Enter subject link first.");
				}
				else {
					subjects_info.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							subject_list = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									subject_list.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							N = subject_list.size() - 1;
							L = subject_list.size();
							for(int _repeat20 = 0; _repeat20 < (int)(L); _repeat20++) {
								if (subject_list.get((int)N).get("R_LINK").toString().equals(subjid.getText().toString())) {
									createjoinsubj = new HashMap<>();
									createjoinsubj.put("ST_STUDENTID", ID);
									createjoinsubj.put("ST_NAME", FULLNAME);
									createjoinsubj.put("ST_SEX", SEX);
									createjoinsubj.put("ST_LN", GETLASTNAME);
									createjoinsubj.put("ST_COURSE", subject_list.get((int)N).get("R_STUDENT").toString());
									createjoinsubj.put("ST_SID", subject_list.get((int)N).get("R_CODE").toString());
									createjoinsubj.put("ST_SCHOOLYEAR", subject_list.get((int)N).get("R_SY").toString());
									createjoinsubj.put("ST_UID", subject_list.get((int)N).get("R_UID").toString());
									createjoinsubj.put("ST_SCHEDULE", subject_list.get((int)N).get("R_TIMEIN").toString().concat("-".concat(subject_list.get((int)N).get("R_TIMEOUT").toString())));
									createjoinsubj.put("ST_PROF", NAME);
									createjoinsubj.put("ST_SEM", subject_list.get((int)N).get("R_SEM").toString());
									createjoinsubj.put("ST_SUBJNAME", subject_list.get((int)N).get("R_SUBJECT").toString());
									createjoinsubj.put("ST_STUDENTEML", EML);
									STR = subject_list.get((int)N).get("R_CODE").toString().toUpperCase().concat("-".concat(GETLASTNAME.concat("-".concat(id.getText().toString().concat("-PROF-".concat(subject_list.get((int)N).get("R_UID").toString().concat("-".concat(subject_list.get((int)N).get("R_SY").toString()))))))));
									createjoinsubj.put("ST_ID", STR);
									enrolled_student_info.child(STR).updateChildren(createjoinsubj);
									createjoinsubj.clear();
									SketchwareUtil.showMessage(getApplicationContext(), "Success! You are now joined to your classroom.");
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
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("iSKAN AKO");
				dialog.setIcon(R.drawable.iskanako_01);
				dialog.setMessage("This page is undermaintenance.");
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
		
		subjid.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				ClipData clipData = clipboard.getPrimaryClip();
				if (clipData != null){
					subjid.setText(clipData.getItemAt(0).getText().toString());
					SketchwareUtil.showMessage(getApplicationContext(), "Successfully Paste!");
				}
				else{
					SketchwareUtil.showMessage(getApplicationContext(), "Empty Clipboard!");
				}
			}
		});
		
		_student_user_info_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				student_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						userlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								userlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						str.add(_childKey);
						status.setAdapter(new StatusAdapter(userlist));
						((BaseAdapter)status.getAdapter()).notifyDataSetChanged();
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
				student_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						userlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								userlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						status.setAdapter(new StatusAdapter(userlist));
						((BaseAdapter)status.getAdapter()).notifyDataSetChanged();
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
				student_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						userlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								userlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						status.setAdapter(new StatusAdapter(userlist));
						((BaseAdapter)status.getAdapter()).notifyDataSetChanged();
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
		student_user_info.addChildEventListener(_student_user_info_child_listener);
		
		_student_join_child_listener = new ChildEventListener() {
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
		student_join.addChildEventListener(_student_join_child_listener);
		
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
		
		_faculty_user_info_child_listener = new ChildEventListener() {
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
		faculty_user_info.addChildEventListener(_faculty_user_info_child_listener);
		
		_enrolled_student_info_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
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
						enrolledstr.add(_childKey);
						listview2.setAdapter(new Listview2Adapter(enrolledlist));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
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
						listview2.setAdapter(new Listview2Adapter(enrolledlist));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
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
						listview2.setAdapter(new Listview2Adapter(enrolledlist));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
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
		enrolled_student_info.addChildEventListener(_enrolled_student_info_child_listener);
		
		_enrolled_student_child_listener = new ChildEventListener() {
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
		enrolled_student.addChildEventListener(_enrolled_student_child_listener);
		
		users_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		users_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_users_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_users_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_users_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		EML = getIntent().getStringExtra("EML");
		_DESIGN();
		page = 1;
		_DISPLAY();
	}
	
	@Override
	public void onBackPressed() {
		exit.setTitle("iSKAN AKO");
		exit.setIcon(R.drawable.unex_black_logo);
		exit.setMessage("You want to exit?");
		exit.setPositiveButton("LOG-OUT", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				statuschange = new HashMap<>();
				statuschange.put("E_STATUS", "OFFLINE");
				student_user_info.child(ID).updateChildren(statuschange);
				statuschange.clear();
				if (true) {
					FirebaseAuth.getInstance().signOut();
					PAGE.putExtra("USER", "NORMAL");
					PAGE.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(PAGE);
					finish();
				}
			}
		});
		exit.create().show();
	}
	public void _GETDATA() {
		if (true) {
			STDNTID = ID;
			EMAIL = EML;
			name.setText(FULLNAME);
			id.setText(ID);
			sex.setText(SEX);
			Glide.with(getApplicationContext()).load(Uri.parse(URL)).into(circleimageview2);
			GETLASTNAME = LASTNAME;
			if (COURSE.equals("NONE")) {
				course_year_sec.setText("[No course/year/section yet, edit your profile now]");
			}
			else {
				course_year_sec.setText(COURSE.concat(" ".concat(YEAR.concat("-".concat(SECTION)))));
			}
		}
	}
	
	
	public void _DISPLAY() {
		if (page == 1) {
			
			listview2.setVisibility(View.VISIBLE);
			joinpage.setVisibility(View.GONE);
		} 
		if (page == 2) {
			
			listview2.setVisibility(View.GONE);
			joinpage.setVisibility(View.GONE);
		} 
		if (page == 3) {
			
			listview2.setVisibility(View.GONE);
			joinpage.setVisibility(View.VISIBLE);
		} 
	}
	
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		profiledisplay.setElevation(16);
		profiledisplay.setBackground(a);
		profiledisplay.setElevation(16);
		profiledisplay.setBackground(a);
	}
	
	
	public void _BACK() {
		
	}
	
	
	public void _GETINFO() {
		if (true) {
			faculty_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					faculty_list = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							faculty_list.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					N2 = faculty_list.size() - 1;
					L2 = faculty_list.size();
					for(int _repeat20 = 0; _repeat20 < (int)(L2); _repeat20++) {
						if (subjid.getText().toString().contains(faculty_list.get((int)N2).get("C_ID").toString())) {
							NAME = faculty_list.get((int)N2).get("A_FULLNAME").toString();
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
	
	
	public void _TEST() {
		
	}
	
	public class StatusAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public StatusAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.status, null);
			}
			
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView status = _view.findViewById(R.id.status);
			
			if (_data.get((int)_position).get("C_EMAIL").toString().equals(EML)) {
				status.setText(_data.get((int)_position).get("E_STATUS").toString());
				FULLNAME = _data.get((int)_position).get("A_FULLNAME").toString();
				ID = _data.get((int)_position).get("C_ID").toString();
				SEX = _data.get((int)_position).get("B_SEX").toString();
				URL = _data.get((int)_position).get("E_URL").toString();
				COURSE = _data.get((int)_position).get("D_COURSE").toString();
				YEAR = _data.get((int)_position).get("D_YEAR").toString();
				SECTION = _data.get((int)_position).get("D_SECTION").toString();
				EML = _data.get((int)_position).get("C_EMAIL").toString();
				LASTNAME = _data.get((int)_position).get("A_LN").toString();
				_GETDATA();
			}
			else {
				linear9.setVisibility(View.GONE);
				str.clear();
			}
			
			return _view;
		}
	}
	
	public class Listview2Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.studentsubj, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView subject = _view.findViewById(R.id.subject);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView semester = _view.findViewById(R.id.semester);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView profname = _view.findViewById(R.id.profname);
			final TextView textview6 = _view.findViewById(R.id.textview6);
			final TextView scgedule = _view.findViewById(R.id.scgedule);
			
			if (!EMAIL.equals(_data.get((int)_position).get("ST_STUDENTEML").toString())) {
				main.setVisibility(View.GONE);
			}
			else {
				android.graphics.drawable.GradientDrawable d1 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
				
				d1.setCornerRadius(10);
				main.setElevation(50);
				main.setBackground(d1);
				subject.setText(_data.get((int)_position).get("ST_SUBJNAME").toString());
				profname.setText(_data.get((int)_position).get("ST_PROF").toString());
				semester.setText(_data.get((int)_position).get("ST_SEM").toString());
				scgedule.setText(_data.get((int)_position).get("ST_SCHEDULE").toString());
				main.setVisibility(View.VISIBLE);
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