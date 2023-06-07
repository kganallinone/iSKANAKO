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
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String TYPE = "";
	private double N = 0;
	private double L = 0;
	private double load_no = 0;
	private String GO = "";
	private String ID = "";
	private HashMap<String, Object> statuschange = new HashMap<>();
	private HashMap<String, Object> facultycreate = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> userlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout dsply;
	private LinearLayout linear6;
	private LinearLayout linear3;
	private EditText email;
	private EditText password;
	private LinearLayout linear10;
	private Button button1;
	private TextView or;
	private TextView offline;
	private LinearLayout linear7;
	private ImageView imageview2;
	private LinearLayout linear8;
	private TextView textview1;
	private TextView user_type;
	private TextView toast;
	
	private Intent PAGE = new Intent();
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
	
	private DatabaseReference student_user_info = _firebase.getReference("student_user_info");
	private ChildEventListener _student_user_info_child_listener;
	private DatabaseReference faculty_user_info = _firebase.getReference("faculty_user_info");
	private ChildEventListener _faculty_user_info_child_listener;
	private TimerTask load;
	private AlertDialog.Builder dialog;
	private DatabaseReference faculty_log = _firebase.getReference("faculty_log");
	private ChildEventListener _faculty_log_child_listener;
	private Calendar date = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		dsply = findViewById(R.id.dsply);
		linear6 = findViewById(R.id.linear6);
		linear3 = findViewById(R.id.linear3);
		email = findViewById(R.id.email);
		password = findViewById(R.id.password);
		linear10 = findViewById(R.id.linear10);
		button1 = findViewById(R.id.button1);
		or = findViewById(R.id.or);
		offline = findViewById(R.id.offline);
		linear7 = findViewById(R.id.linear7);
		imageview2 = findViewById(R.id.imageview2);
		linear8 = findViewById(R.id.linear8);
		textview1 = findViewById(R.id.textview1);
		user_type = findViewById(R.id.user_type);
		toast = findViewById(R.id.toast);
		users = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
					toast.setText("Please complete your details.");
				}
				else {
					if (!email.getText().toString().contains("iskolarngbayan.pup.edu.ph") || !email.getText().toString().contains("pup.edu.ph")) {
						dialog.setTitle("ATTENTION!");
						dialog.setIcon(R.drawable.iskanako_01);
						dialog.setMessage("After one year, you must change the email address you entered. You will be changing the email address provided by the PUP or the MS Team email address. \n\nSTUDENT EMAIL: anonymous@iskolarngbayan.pup.edu.ph\n\nFACULTY EMAIL: anonymous@pup.edu.ph\n");
						dialog.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								users.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(LoginActivity.this, _users_sign_in_listener);
							}
						});
						dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								toast.setText("Use the MS Tean email given by PUP Lopez.");
							}
						});
						dialog.create().show();
					}
					else {
						users.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(LoginActivity.this, _users_sign_in_listener);
					}
				}
			}
		});
		
		offline.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), OfflineActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
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
		
		_faculty_log_child_listener = new ChildEventListener() {
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
		faculty_log.addChildEventListener(_faculty_log_child_listener);
		
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
				if (_success) {
					_CONNECT();
				}
				else {
					dialog.setTitle("iSKAN AKO");
					dialog.setIcon(R.drawable.unex_black_logo);
					dialog.setMessage(_errorMessage);
					dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							PAGE.putExtra("PG", "1");
							PAGE.setClass(getApplicationContext(), RegisterActivity.class);
							startActivity(PAGE);
							finish();
						}
					});
					dialog.create().show();
				}
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
		date = Calendar.getInstance();
		_DESIGN();
		_USERTYPE();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.putExtra("USER", "NORMAL");
		PAGE.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _USERTYPE() {
		TYPE = getIntent().getStringExtra("TYPE");
		if (TYPE.equals("STUDENT")) {
			user_type.setText("Our Student!");
			or.setVisibility(View.VISIBLE);
			offline.setVisibility(View.VISIBLE);
		}
		else {
			if (TYPE.equals("FACULTY")) {
				user_type.setText("Our Faculty!");
				or.setVisibility(View.GONE);
				offline.setVisibility(View.GONE);
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "ERROR");
			}
		}
	}
	
	
	public void _CONNECT() {
		TYPE = getIntent().getStringExtra("TYPE");
		GO = "GO";
		_TOAST();
		if (TYPE.equals("STUDENT")) {
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
					N = userlist.size() - 1;
					L = userlist.size();
					for(int _repeat54 = 0; _repeat54 < (int)(L); _repeat54++) {
						if (userlist.get((int)N).get("C_EMAIL").toString().equals(email.getText().toString())) {
							if (true) {
								ID = userlist.get((int)N).get("C_ID").toString();
								statuschange = new HashMap<>();
								statuschange.put("E_STATUS", "ONLINE");
								student_user_info.child(ID).updateChildren(statuschange);
								statuschange.clear();
								if (true) {
									SketchwareUtil.showMessage(getApplicationContext(), "Welcome to the app our student!");
									load.cancel();
									PAGE.putExtra("EML", email.getText().toString());
									PAGE.setClass(getApplicationContext(), ProfileStudentActivity.class);
									startActivity(PAGE);
									finish();
								}
							}
						}
						N--;
					}
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}
		else {
			if (TYPE.equals("FACULTY")) {
				faculty_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
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
						N = userlist.size() - 1;
						L = userlist.size();
						for(int _repeat71 = 0; _repeat71 < (int)(L); _repeat71++) {
							if (userlist.get((int)N).get("C_EMAIL").toString().equals(email.getText().toString())) {
								if (true) {
									ID = userlist.get((int)N).get("C_ID").toString();
									statuschange = new HashMap<>();
									statuschange.put("E_STATUS", "ONLINE");
									faculty_user_info.child(ID).updateChildren(statuschange);
									statuschange.clear();
									if (true) {
										SketchwareUtil.showMessage(getApplicationContext(), "Welcome to the app our beloved teacher!");
										load.cancel();
										PAGE.putExtra("EML", email.getText().toString());
										PAGE.setClass(getApplicationContext(), ProfileFacultyActivity.class);
										startActivity(PAGE);
										finish();
									}
									if (true) {
										facultycreate = new HashMap<>();
										facultycreate.put("TIMEIN", new SimpleDateFormat("hh:mm a").format(date.getTime()));
										facultycreate.put("TIMEOUT", "N/A");
										facultycreate.put("DATE", new SimpleDateFormat("MM-dd-YYYY").format(date.getTime()));
										facultycreate.put("NAME", userlist.get((int)N).get("A_FULLNAME").toString());
										facultycreate.put("REMARKS", "N/A");
										faculty_log.child(ID.concat("-".concat(new SimpleDateFormat("MM-dd-YYYY").format(date.getTime()).concat(new SimpleDateFormat("hh:mm a").format(date.getTime()))))).updateChildren(facultycreate);
										facultycreate.clear();
									}
								}
							}
							N--;
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "ERROR");
			}
		}
	}
	
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		dsply.setElevation(16);
		dsply.setBackground(a);
		
	}
	
	
	public void _TOAST() {
		if (GO.equals("GO")) {
			load_no = 0;
			load = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (load_no == 200) {
								toast.setText("Can't find your account");
								load_no = 0;
								load.cancel();
								GO = "NO";
							}
							load_no++;
						}
					});
				}
			};
			_timer.scheduleAtFixedRate(load, (int)(1), (int)(20));
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