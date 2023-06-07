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
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class FacultySubjectsInfoActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String ID = "";
	private String ST_ID = "";
	private String SCHOOLYEAR = "";
	private String STUDENT = "";
	private String SEMESTER = "";
	private String LINK = "";
	private String share = "";
	private String MSG = "";
	private String MSG2 = "";
	private String SUBJECT = "";
	private double NO = 0;
	private String ATTENDANCECODE = "";
	private String Z1 = "";
	private double N1 = 0;
	private double L1 = 0;
	private double studentno = 0;
	
	private ArrayList<HashMap<String, Object>> enrolledlist = new ArrayList<>();
	private ArrayList<String> enrolledstr = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout header;
	private LinearLayout linear36;
	private LinearLayout linear38;
	private LinearLayout linear35;
	private LinearLayout linear8;
	private TextView textview2;
	private TextView subjectid;
	private TextView subjectname;
	private TextView textview14;
	private TextView course;
	private TextView textview16;
	private TextView sem;
	private ImageView imageview3;
	private TextView textview11;
	private LinearLayout linear39;
	private ImageView sharelink;
	private ImageView add;
	private TextView stdntn;
	private ListView students;
	
	private Intent PAGE = new Intent();
	private DatabaseReference enrolled_student_info = _firebase.getReference("enrolled_student_info");
	private ChildEventListener _enrolled_student_info_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.faculty_subjects_info);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		header = findViewById(R.id.header);
		linear36 = findViewById(R.id.linear36);
		linear38 = findViewById(R.id.linear38);
		linear35 = findViewById(R.id.linear35);
		linear8 = findViewById(R.id.linear8);
		textview2 = findViewById(R.id.textview2);
		subjectid = findViewById(R.id.subjectid);
		subjectname = findViewById(R.id.subjectname);
		textview14 = findViewById(R.id.textview14);
		course = findViewById(R.id.course);
		textview16 = findViewById(R.id.textview16);
		sem = findViewById(R.id.sem);
		imageview3 = findViewById(R.id.imageview3);
		textview11 = findViewById(R.id.textview11);
		linear39 = findViewById(R.id.linear39);
		sharelink = findViewById(R.id.sharelink);
		add = findViewById(R.id.add);
		stdntn = findViewById(R.id.stdntn);
		students = findViewById(R.id.students);
		
		sharelink.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_ShareToFriends();
			}
		});
		
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), AddStudentActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
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
						students.setAdapter(new StudentsAdapter(enrolledlist));
						((BaseAdapter)students.getAdapter()).notifyDataSetChanged();
						
						
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
						students.setAdapter(new StudentsAdapter(enrolledlist));
						((BaseAdapter)students.getAdapter()).notifyDataSetChanged();
						
						
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
						students.setAdapter(new StudentsAdapter(enrolledlist));
						((BaseAdapter)students.getAdapter()).notifyDataSetChanged();
						
						
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
	}
	
	private void initializeLogic() {
		_DESIGN();
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("SC", getIntent().getStringExtra("SC"));
		PAGE.putExtra("SYEAR", getIntent().getStringExtra("SYEAR"));
		PAGE.putExtra("UID", getIntent().getStringExtra("UID"));
		PAGE.putExtra("ST", getIntent().getStringExtra("ST"));
		PAGE.putExtra("SEM", getIntent().getStringExtra("SEM"));
		PAGE.putExtra("LINK", getIntent().getStringExtra("LINK"));
		PAGE.putExtra("SUBJ", getIntent().getStringExtra("SUBJ"));
		subjectid.setText(getIntent().getStringExtra("SC"));
		ID = getIntent().getStringExtra("SC");
		SCHOOLYEAR = getIntent().getStringExtra("SYEAR");
		STUDENT = getIntent().getStringExtra("ST");
		SEMESTER = getIntent().getStringExtra("SEM");
		LINK = getIntent().getStringExtra("LINK");
		SUBJECT = getIntent().getStringExtra("SUBJ");
		NO = 0;
		subjectname.setText(SUBJECT);
		sem.setText(SEMESTER);
		course.setText(STUDENT);
		_NOOFSTUDENT();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), FacultyprofileActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		header.setElevation(16);
		header.setBackground(a);
		
	}
	
	
	public void _ShareToFriends() {
		MSG = "[CLASSROOM INFO]\n".concat("CODE: ".concat(ID.concat("\nNAME:".concat(SUBJECT.concat("\nLINK: ".concat(LINK))))));
		MSG2 = "Hello, ".concat(STUDENT.concat(" student/s!\n\nUse the link to join classroom attendance! Thank you!\n\n".concat(MSG)));
		share = MSG2;
		Intent i = new Intent(android.content.Intent.ACTION_SEND);
		
		i.setType("text/plain");
		i.putExtra(android.content.Intent.EXTRA_TEXT, share);
		
		startActivity(Intent.createChooser(i, "share using"));
	}
	
	
	public void _NOOFSTUDENT() {
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
				N1 = 0;
				studentno = 0;
				L1 = enrolledlist.size();
				while(!(N1 == L1)) {
					if ((enrolledlist.get((int)N1).get("ST_SID").toString().equals(ID) && enrolledlist.get((int)N1).get("ST_SCHOOLYEAR").toString().equals(SCHOOLYEAR)) && enrolledlist.get((int)N1).get("ST_COURSE").toString().equals(STUDENT)) {
						studentno++;
						stdntn.setText(String.valueOf((long)(studentno)).concat(" STUDENT/S"));
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
	
	public class StudentsAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public StudentsAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.student, null);
			}
			
			final LinearLayout profiledisplay = _view.findViewById(R.id.profiledisplay);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final TextView name = _view.findViewById(R.id.name);
			final LinearLayout linear16 = _view.findViewById(R.id.linear16);
			final LinearLayout line = _view.findViewById(R.id.line);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final TextView id = _view.findViewById(R.id.id);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView sex = _view.findViewById(R.id.sex);
			
			if ((_data.get((int)_position).get("ST_SID").toString().equals(ID) && _data.get((int)_position).get("ST_SCHOOLYEAR").toString().equals(SCHOOLYEAR)) && _data.get((int)_position).get("ST_COURSE").toString().equals(STUDENT)) {
				profiledisplay.setVisibility(View.VISIBLE);
				android.graphics.drawable.GradientDrawable Z1 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
				
				Z1.setCornerRadius(20);
				profiledisplay.setElevation(16);
				profiledisplay.setBackground(Z1);
				name.setText(_data.get((int)_position).get("ST_NAME").toString());
				id.setText(_data.get((int)_position).get("ST_STUDENTID").toString());
				sex.setText(_data.get((int)_position).get("ST_SEX").toString());
			}
			else {
				profiledisplay.setVisibility(View.GONE);
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