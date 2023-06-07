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
import com.bumptech.glide.Glide;
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

public class StudentattendanceinfoActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String SID = "";
	private String FID = "";
	private String AL = "";
	private String DATE = "";
	private String EML = "";
	private String FULLNAME = "";
	private String ID = "";
	private String SEX = "";
	private String URL = "";
	private String COURSE = "";
	private String YEAR = "";
	private String SECTION = "";
	private double N2 = 0;
	private double L2 = 0;
	private String NAME = "";
	private String STATUS = "";
	private String SUBJ = "";
	private String PROF = "";
	private String SCHED = "";
	private String ROOM = "";
	private String SEM = "";
	private String STID = "";
	private String Z1 = "";
	
	private ArrayList<HashMap<String, Object>> userlist = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> faculty_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> enrolled_info = new ArrayList<>();
	private ArrayList<String> adb = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> adb_list = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview16;
	private LinearLayout linear3;
	private LinearLayout profiledisplay;
	private ListView listview1;
	private LinearLayout linear12;
	private LinearLayout linear6;
	private TextView textview5;
	private TextView name;
	private TextView course_year_sec;
	private LinearLayout linear16;
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
	
	private DatabaseReference student_user_info = _firebase.getReference("student_user_info");
	private ChildEventListener _student_user_info_child_listener;
	private DatabaseReference attendance_db = _firebase.getReference("attendance_db");
	private ChildEventListener _attendance_db_child_listener;
	private Intent PAGE = new Intent();
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.studentattendanceinfo);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview16 = findViewById(R.id.textview16);
		linear3 = findViewById(R.id.linear3);
		profiledisplay = findViewById(R.id.profiledisplay);
		listview1 = findViewById(R.id.listview1);
		linear12 = findViewById(R.id.linear12);
		linear6 = findViewById(R.id.linear6);
		textview5 = findViewById(R.id.textview5);
		name = findViewById(R.id.name);
		course_year_sec = findViewById(R.id.course_year_sec);
		linear16 = findViewById(R.id.linear16);
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
		dialog = new AlertDialog.Builder(this);
		
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
		
		_attendance_db_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				attendance_db.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						adb_list = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adb_list.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						adb.add(_childKey);
						listview1.setAdapter(new Listview1Adapter(adb_list));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
						adb_list = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adb_list.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(adb_list));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
						adb_list = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adb_list.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(adb_list));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
	}
	
	private void initializeLogic() {
		PAGE.putExtra("STID", getIntent().getStringExtra("STID"));
		PAGE.putExtra("ID", getIntent().getStringExtra("ID"));
		PAGE.putExtra("AL", getIntent().getStringExtra("AL"));
		PAGE.putExtra("DATE", getIntent().getStringExtra("DATE"));
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("SUBJ", getIntent().getStringExtra("SUBJ"));
		PAGE.putExtra("USR", getIntent().getStringExtra("USR"));
		PAGE.putExtra("SCHED", getIntent().getStringExtra("SCHED"));
		PAGE.putExtra("PROF", getIntent().getStringExtra("PROF"));
		PAGE.putExtra("SEM", getIntent().getStringExtra("SEM"));
		FID = getIntent().getStringExtra("ID");
		AL = getIntent().getStringExtra("AL");
		DATE = getIntent().getStringExtra("DATE");
		SEM = getIntent().getStringExtra("SEM");
		SUBJ = getIntent().getStringExtra("SUBJ");
		STID = getIntent().getStringExtra("STID");
		SCHED = getIntent().getStringExtra("SCHED");
		PROF = getIntent().getStringExtra("PROF");
		ROOM = getIntent().getStringExtra("SEM");
		EML = getIntent().getStringExtra("EML");
		_DESIGN();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), FacultyCoursePageActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _GETDATAS() {
		if (true) {
			name.setText(FULLNAME);
			id.setText(ID);
			sex.setText(SEX);
			Glide.with(getApplicationContext()).load(Uri.parse(URL)).into(circleimageview2);
			if (COURSE.equals("NONE")) {
				course_year_sec.setText("[No course/year/section yet, edit your profile now]");
			}
			else {
				course_year_sec.setText(COURSE.concat(" ".concat(YEAR.concat("-".concat(SECTION)))));
			}
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
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.studentattendance, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView status = _view.findViewById(R.id.status);
			final TextView time = _view.findViewById(R.id.time);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final TextView date = _view.findViewById(R.id.date);
			final TextView textview7 = _view.findViewById(R.id.textview7);
			final TextView subject = _view.findViewById(R.id.subject);
			final TextView textview9 = _view.findViewById(R.id.textview9);
			final TextView prof = _view.findViewById(R.id.prof);
			final TextView textview11 = _view.findViewById(R.id.textview11);
			final TextView sched = _view.findViewById(R.id.sched);
			final TextView textview14 = _view.findViewById(R.id.textview14);
			final TextView room = _view.findViewById(R.id.room);
			
			if (_data.get((int)_position).get("L_AL").toString().equals(AL) && _data.get((int)_position).get("L_STDNTID").toString().equals(STID)) {
				main.setVisibility(View.VISIBLE);
				STATUS = _data.get((int)_position).get("L_STATUS").toString();
				subject.setText(SUBJ);
				prof.setText(PROF);
				sched.setText(SCHED);
				date.setText(_data.get((int)_position).get("L_DATE").toString());
				time.setText(_data.get((int)_position).get("L_TIME").toString());
				room.setText(SEM);
				if (true) {
					if (STATUS.equals("P")) {
						
						status.setTextColor(0xFF2196F3);
						status.setText("PRESENT");
					} 
					else if (STATUS.equals("L")){
						
						status.setText("LATE");
						status.setTextColor(0xFFFF5722);
						
					}
					else if (STATUS.equals("A")){
						
						status.setText("ABSENT");
						status.setTextColor(0xFFF44336);
						
					}
					else {
						status.setText("EXCUSE");
						status.setTextColor(0xFF4CAF50);
					}
				}
			}
			else {
				main.setVisibility(View.GONE);
				adb.clear();
			}
			
			return _view;
		}
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
				_GETDATAS();
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