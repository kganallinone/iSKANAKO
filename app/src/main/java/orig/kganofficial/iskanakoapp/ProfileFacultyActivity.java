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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class ProfileFacultyActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String ID = "";
	private HashMap<String, Object> statuschange = new HashMap<>();
	private String FULLNAME = "";
	private String BDAY = "";
	private String SEX = "";
	private String URL = "";
	private double position = 0;
	private double ARRAY = 0;
	private String COURSE = "";
	private String YEAR = "";
	private String SECTION = "";
	private String FN = "";
	private String EML = "";
	private String GETFULLN = "";
	private HashMap<String, Object> facultycreate = new HashMap<>();
	private double N = 0;
	private double L = 0;
	private String LOG = "";
	
	private ArrayList<HashMap<String, Object>> userlist = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> userlistfaculty = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> f_log = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private ImageView imageview1;
	private LinearLayout linear4;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout p;
	private CircleImageView circleimageview2;
	private LinearLayout linear12;
	private LinearLayout get_qrcode;
	private LinearLayout get_attendance;
	private LinearLayout get_attendancedata;
	private LinearLayout linear14;
	private TextView textview6;
	private TextView name;
	private LinearLayout linear13;
	private ListView status;
	private ImageView imageview2;
	private LinearLayout linear7;
	private TextView textview3;
	private ImageView imageview3;
	private LinearLayout linear9;
	private TextView textview4;
	private ImageView imageview4;
	private LinearLayout linear11;
	private TextView textview5;
	private ImageView imageview5;
	private LinearLayout linear15;
	private TextView textview7;
	
	private Intent PAGE = new Intent();
	private DatabaseReference faculty_user_info = _firebase.getReference("faculty_user_info");
	private ChildEventListener _faculty_user_info_child_listener;
	private AlertDialog.Builder dialog;
	private AlertDialog.Builder exit;
	private Calendar date = Calendar.getInstance();
	private DatabaseReference faculty_log = _firebase.getReference("faculty_log");
	private ChildEventListener _faculty_log_child_listener;
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
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile_faculty);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		imageview1 = findViewById(R.id.imageview1);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		p = findViewById(R.id.p);
		circleimageview2 = findViewById(R.id.circleimageview2);
		linear12 = findViewById(R.id.linear12);
		get_qrcode = findViewById(R.id.get_qrcode);
		get_attendance = findViewById(R.id.get_attendance);
		get_attendancedata = findViewById(R.id.get_attendancedata);
		linear14 = findViewById(R.id.linear14);
		textview6 = findViewById(R.id.textview6);
		name = findViewById(R.id.name);
		linear13 = findViewById(R.id.linear13);
		status = findViewById(R.id.status);
		imageview2 = findViewById(R.id.imageview2);
		linear7 = findViewById(R.id.linear7);
		textview3 = findViewById(R.id.textview3);
		imageview3 = findViewById(R.id.imageview3);
		linear9 = findViewById(R.id.linear9);
		textview4 = findViewById(R.id.textview4);
		imageview4 = findViewById(R.id.imageview4);
		linear11 = findViewById(R.id.linear11);
		textview5 = findViewById(R.id.textview5);
		imageview5 = findViewById(R.id.imageview5);
		linear15 = findViewById(R.id.linear15);
		textview7 = findViewById(R.id.textview7);
		dialog = new AlertDialog.Builder(this);
		exit = new AlertDialog.Builder(this);
		users = FirebaseAuth.getInstance();
		
		get_qrcode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.putExtra("PG", "1");
				PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
				PAGE.setClass(getApplicationContext(), FacultyprofileActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		get_attendance.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), SetupscannerActivity.class);
				PAGE.putExtra("PROF", GETFULLN);
				PAGE.putExtra("ID", ID);
				startActivity(PAGE);
				finish();
			}
		});
		
		get_attendancedata.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
				PAGE.putExtra("ID", ID);
				PAGE.setClass(getApplicationContext(), AttendancehistoryActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		linear14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				exit.setTitle("iSKAN AKO");
				exit.setIcon(R.drawable.unex_black_logo);
				exit.setMessage("This page is undermaintenance.");
				exit.setPositiveButton("BACK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				exit.setCancelable(false);
				exit.create().show();
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
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		EML = getIntent().getStringExtra("EML");
		_DESIGN();
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
				faculty_user_info.child(ID).updateChildren(statuschange);
				statuschange.clear();
				if (true) {
					FirebaseAuth.getInstance().signOut();
					PAGE.putExtra("USER", "NORMAL");
					PAGE.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(PAGE);
					finish();
				}
				if (true) {
					facultycreate = new HashMap<>();
					facultycreate.put("TIMEOUT", new SimpleDateFormat("hh:mm a").format(date.getTime()));
					facultycreate.put("REMARKS", "N/A");
					faculty_log.child(ID).updateChildren(facultycreate);
					facultycreate.clear();
				}
			}
		});
		exit.create().show();
	}
	public void _GETDATA() {
		if (true) {
			if (SEX.equals("MALE")) {
				name.setText("Sir ".concat(FN));
			}
			else {
				name.setText("Ma'am ".concat(FN));
			}
			Glide.with(getApplicationContext()).load(Uri.parse(URL)).into(circleimageview2);
			position = ARRAY;
			GETFULLN = FULLNAME;
		}
	}
	
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		p.setElevation(16);
		p.setBackground(a);
		
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
				_view = _inflater.inflate(R.layout.status2, null);
			}
			
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView status = _view.findViewById(R.id.status);
			
			if (_data.get((int)_position).get("C_EMAIL").toString().equals(EML)) {
				status.setText(_data.get((int)_position).get("E_STATUS").toString());
				FULLNAME = _data.get((int)_position).get("A_FULLNAME").toString();
				FN = _data.get((int)_position).get("A_FN").toString();
				ID = _data.get((int)_position).get("C_ID").toString();
				SEX = _data.get((int)_position).get("B_SEX").toString();
				URL = _data.get((int)_position).get("E_URL").toString();
				ARRAY = _position;
				_GETDATA();
			}
			else {
				linear9.setVisibility(View.GONE);
				str.clear();
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