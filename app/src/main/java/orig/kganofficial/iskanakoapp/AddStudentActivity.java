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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class AddStudentActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String ID = "";
	private String SCHOOLYEAR = "";
	private String STUDENT = "";
	private HashMap<String, Object> createjoinsubj = new HashMap<>();
	private String UID = "";
	private String STR = "";
	private double N2 = 0;
	private double L2 = 0;
	private String NAME = "";
	private String SID = "";
	
	private ArrayList<String> sexstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> faculty_list = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private ImageView imageview4;
	private TextView textview7;
	private EditText id;
	private EditText name;
	private EditText ln;
	private LinearLayout linear25;
	private Button create_bttn;
	private EditText sex;
	private Spinner sexspin;
	
	private Intent PAGE = new Intent();
	private DatabaseReference enrolled_student_info = _firebase.getReference("enrolled_student_info");
	private ChildEventListener _enrolled_student_info_child_listener;
	private DatabaseReference faculty_user_info = _firebase.getReference("faculty_user_info");
	private ChildEventListener _faculty_user_info_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.add_student);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		imageview4 = findViewById(R.id.imageview4);
		textview7 = findViewById(R.id.textview7);
		id = findViewById(R.id.id);
		name = findViewById(R.id.name);
		ln = findViewById(R.id.ln);
		linear25 = findViewById(R.id.linear25);
		create_bttn = findViewById(R.id.create_bttn);
		sex = findViewById(R.id.sex);
		sexspin = findViewById(R.id.sexspin);
		
		create_bttn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_GETINFO();
				if ((name.getText().toString().equals("") || ln.getText().toString().equals("")) || (id.getText().toString().equals("") || sex.getText().toString().equals(""))) {
					SketchwareUtil.showMessage(getApplicationContext(), "Complete your details.");
				}
				else {
					createjoinsubj = new HashMap<>();
					createjoinsubj.put("ST_STUDENTID", id.getText().toString());
					createjoinsubj.put("ST_NAME", name.getText().toString().toUpperCase());
					createjoinsubj.put("ST_SEX", sex.getText().toString());
					createjoinsubj.put("ST_LN", ln.getText().toString().toUpperCase());
					createjoinsubj.put("ST_COURSE", STUDENT);
					createjoinsubj.put("ST_SID", ID);
					createjoinsubj.put("ST_SCHOOLYEAR", SCHOOLYEAR);
					createjoinsubj.put("ST_UID", UID);
					createjoinsubj.put("ST_SCHEDULE", "N/A");
					createjoinsubj.put("ST_PROF", NAME);
					createjoinsubj.put("ST_SEM", "N/A");
					createjoinsubj.put("ST_SUBJNAME", "N/A");
					createjoinsubj.put("ST_STUDENTEML", "N/A");
					STR = ID.toUpperCase().concat("-".concat(ln.getText().toString().toUpperCase().concat("-".concat(id.getText().toString().concat("-PROF-".concat(UID.concat("-".concat(SCHOOLYEAR))))))));
					createjoinsubj.put("ST_ID", STR);
					enrolled_student_info.child(STR).updateChildren(createjoinsubj);
					createjoinsubj.clear();
					SketchwareUtil.showMessage(getApplicationContext(), "Success! Your student are joined to your classroom. Please check.");
					if (true) {
						name.setText("");
						id.setText("");
						PAGE.setClass(getApplicationContext(), FacultySubjectsInfoActivity.class);
						startActivity(PAGE);
						finish();
					}
				}
			}
		});
		
		sexspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					sex.setText("MALE");
				}
				if (_position == 1) {
					sex.setText("FEMALE");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
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
	}
	
	private void initializeLogic() {
		PAGE.putExtra("EML", getIntent().getStringExtra("EML"));
		PAGE.putExtra("SC", getIntent().getStringExtra("SC"));
		PAGE.putExtra("SYEAR", getIntent().getStringExtra("SYEAR"));
		PAGE.putExtra("UID", getIntent().getStringExtra("UID"));
		PAGE.putExtra("ST", getIntent().getStringExtra("ST"));
		PAGE.putExtra("SEM", getIntent().getStringExtra("SEM"));
		PAGE.putExtra("LINK", getIntent().getStringExtra("LINK"));
		PAGE.putExtra("SUBJ", getIntent().getStringExtra("SUBJ"));
		ID = getIntent().getStringExtra("SC");
		SCHOOLYEAR = getIntent().getStringExtra("SYEAR");
		STUDENT = getIntent().getStringExtra("ST");
		UID = getIntent().getStringExtra("UID");
		_SPINROOM();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), FacultySubjectsInfoActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _SPINROOM() {
		sexstr.add("MALE");
		sexstr.add("FEMALE");
		sexspin.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, sexstr));
		((ArrayAdapter)sexspin.getAdapter()).notifyDataSetChanged();
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
						if (UID.contains(faculty_list.get((int)N2).get("C_ID").toString())) {
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