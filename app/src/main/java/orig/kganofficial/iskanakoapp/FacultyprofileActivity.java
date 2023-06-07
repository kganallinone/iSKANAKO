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

public class FacultyprofileActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String FULLNAME = "";
	private String ID = "";
	private String SEX = "";
	private String BDAY = "";
	private String URL = "";
	private double position = 0;
	private double ARRAY = 0;
	private HashMap<String, Object> statuschange = new HashMap<>();
	private String EML = "";
	private HashMap<String, Object> createroom = new HashMap<>();
	private String ROOMID = "";
	private String SEMESTER = "";
	private String PG = "";
	private String SCHOOLYEAR = "";
	private HashMap<String, Object> addid = new HashMap<>();
	private String SID = "";
	private String GET_FULLN = "";
	private double N = 0;
	private double L = 0;
	private String R_CODE = "";
	private String Z1 = "";
	
	private ArrayList<HashMap<String, Object>> userlist = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<String> subjstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> subjlist = new ArrayList<>();
	private ArrayList<String> sem_str = new ArrayList<>();
	private ArrayList<String> yearstr = new ArrayList<>();
	
	private LinearLayout profilepage;
	private LinearLayout profile;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private ListView SUBJECT;
	private LinearLayout linear12;
	private LinearLayout linear6;
	private TextView textview5;
	private TextView name;
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
	private ImageView addsubject;
	private TextView textview8;
	private TextView firstsem;
	private TextView textview12;
	private TextView secondsem;
	
	private Intent PAGE = new Intent();
	private DatabaseReference faculty_user_info = _firebase.getReference("faculty_user_info");
	private ChildEventListener _faculty_user_info_child_listener;
	private DatabaseReference subjects_info = _firebase.getReference("subjects_info");
	private ChildEventListener _subjects_info_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.facultyprofile);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		profilepage = findViewById(R.id.profilepage);
		profile = findViewById(R.id.profile);
		linear13 = findViewById(R.id.linear13);
		linear14 = findViewById(R.id.linear14);
		SUBJECT = findViewById(R.id.SUBJECT);
		linear12 = findViewById(R.id.linear12);
		linear6 = findViewById(R.id.linear6);
		textview5 = findViewById(R.id.textview5);
		name = findViewById(R.id.name);
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
		addsubject = findViewById(R.id.addsubject);
		textview8 = findViewById(R.id.textview8);
		firstsem = findViewById(R.id.firstsem);
		textview12 = findViewById(R.id.textview12);
		secondsem = findViewById(R.id.secondsem);
		
		SUBJECT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				PAGE.putExtra("SC", subjlist.get((int)_position).get("R_CODE").toString());
				PAGE.putExtra("SYEAR", subjlist.get((int)_position).get("R_SY").toString());
				PAGE.putExtra("ST", subjlist.get((int)_position).get("R_STUDENT").toString());
				PAGE.putExtra("SEM", subjlist.get((int)_position).get("R_SEM").toString());
				PAGE.putExtra("LINK", subjlist.get((int)_position).get("R_LINK").toString());
				PAGE.putExtra("SUBJ", subjlist.get((int)_position).get("R_SUBJECT").toString());
				PAGE.putExtra("UID", subjlist.get((int)_position).get("R_UID").toString());
				PAGE.setClass(getApplicationContext(), FacultySubjectsInfoActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), EditProfileFacultyActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		addsubject.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.putExtra("ID", ID);
				PAGE.setClass(getApplicationContext(), CreatesubjectActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		firstsem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SEMESTER = "[1st Semester]";
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
						for(int _repeat73 = 0; _repeat73 < (int)(L); _repeat73++) {
							if (subjlist.get((int)N).get("R_SEM").toString().equals(SEMESTER)) {
								if (subjlist.get((int)N).get("R_UID").toString().equals(ID)) {
									
								}
								else {
									subjlist.remove((int)(N));
								}
							}
							else {
								subjlist.remove((int)(N));
							}
							N--;
						}
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		
		secondsem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SEMESTER = "[2nd Semester]";
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
						for(int _repeat104 = 0; _repeat104 < (int)(L); _repeat104++) {
							if (subjlist.get((int)N).get("R_SEM").toString().equals(SEMESTER)) {
								if (subjlist.get((int)N).get("R_UID").toString().equals(ID)) {
									
								}
								else {
									subjlist.remove((int)(N));
								}
							}
							else {
								subjlist.remove((int)(N));
							}
							N--;
						}
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
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
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
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
						SUBJECT.setAdapter(new SUBJECTAdapter(subjlist));
						((BaseAdapter)SUBJECT.getAdapter()).notifyDataSetChanged();
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
	}
	
	private void initializeLogic() {
		SEMESTER = "[1st Semester]";
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		EML = getIntent().getStringExtra("EML");
		_DESIGN();
		_SPIN();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), ProfileFacultyActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		profile.setElevation(16);
		profile.setBackground(a);
		profile.setElevation(16);
		profile.setBackground(a);
	}
	
	
	public void _GETDATA() {
		if (true) {
			name.setText(FULLNAME);
			id.setText(ID);
			sex.setText(SEX);
			Glide.with(getApplicationContext()).load(Uri.parse(URL)).into(circleimageview2);
			position = ARRAY;
			GET_FULLN = FULLNAME;
		}
	}
	
	
	public void _SPIN() {
		sem_str.add("1st Semester");
		sem_str.add("2nd Semester");
		
		
		yearstr.add("2022-2023");
		yearstr.add("2023-2024");
		yearstr.add("2024-2025");
		yearstr.add("2025-2026");
		yearstr.add("2026-2027");
		yearstr.add("2027-2028");
		yearstr.add("2028-2029");
		yearstr.add("2029-2030");
		
		
	}
	
	public class SUBJECTAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public SUBJECTAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.subjects, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView subject = _view.findViewById(R.id.subject);
			final TextView subjcode = _view.findViewById(R.id.subjcode);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView textview6 = _view.findViewById(R.id.textview6);
			final TextView date = _view.findViewById(R.id.date);
			final TextView scgedule = _view.findViewById(R.id.scgedule);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView student = _view.findViewById(R.id.student);
			
			if (!_data.get((int)_position).containsKey("R_DATE")) {
				SID = subjstr.get((int)(_position));
				addid = new HashMap<>();
				addid.put("R_DATE", "N/A");
				subjects_info.child(SID).updateChildren(addid);
				addid.clear();
			}
			else {
				if (_data.get((int)_position).get("R_SEM").toString().equals(SEMESTER)) {
					if (_data.get((int)_position).get("R_UID").toString().equals(ID)) {
						main.setVisibility(View.VISIBLE);
						android.graphics.drawable.GradientDrawable Z1 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
						
						Z1.setCornerRadius(30);
						main.setElevation(16);
						main.setBackground(Z1);
						subject.setText(_data.get((int)_position).get("R_SUBJECT").toString());
						subjcode.setText(_data.get((int)_position).get("R_CODE").toString());
						student.setText(_data.get((int)_position).get("R_STUDENT").toString());
						scgedule.setText(_data.get((int)_position).get("R_TIMEIN").toString().concat("–".concat(_data.get((int)_position).get("R_TIMEOUT").toString())));
						date.setText("(".concat(_data.get((int)_position).get("R_DATE").toString().concat(")")));
					}
					else {
						main.setVisibility(View.GONE);
					}
				}
				else {
					main.setVisibility(View.GONE);
				}
				if (_data.get((int)_position).get("R_ID").toString().equals("NONE")) {
					SID = subjstr.get((int)(_position));
					addid = new HashMap<>();
					addid.put("R_ID", SID);
					subjects_info.child(SID).updateChildren(addid);
					addid.clear();
				}
				else {
					
				}
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