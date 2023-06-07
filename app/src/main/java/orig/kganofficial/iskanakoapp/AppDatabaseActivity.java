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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

public class AppDatabaseActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double N1 = 0;
	private double N2 = 0;
	private double L1 = 0;
	private HashMap<String, Object> fix = new HashMap<>();
	private String D1 = "";
	
	private ArrayList<HashMap<String, Object>> adblist = new ArrayList<>();
	
	private LinearLayout linear5;
	private TextView textview3;
	private TextView textview9;
	private LinearLayout linear13;
	private ScrollView vscroll1;
	private LinearLayout linear16;
	private LinearLayout a1;
	private TextView textview14;
	private EditText edittext1;
	private EditText edittext2;
	private TextView process;
	private LinearLayout linear17;
	private Button button1;
	private Button button2;
	private Button button3;
	
	private DatabaseReference attendance_db = _firebase.getReference("attendance_db");
	private ChildEventListener _attendance_db_child_listener;
	private Intent PAGE = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.app_database);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear5 = findViewById(R.id.linear5);
		textview3 = findViewById(R.id.textview3);
		textview9 = findViewById(R.id.textview9);
		linear13 = findViewById(R.id.linear13);
		vscroll1 = findViewById(R.id.vscroll1);
		linear16 = findViewById(R.id.linear16);
		a1 = findViewById(R.id.a1);
		textview14 = findViewById(R.id.textview14);
		edittext1 = findViewById(R.id.edittext1);
		edittext2 = findViewById(R.id.edittext2);
		process = findViewById(R.id.process);
		linear17 = findViewById(R.id.linear17);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		button3 = findViewById(R.id.button3);
		
		a1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
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
							if (adblist.get((int)N1).containsKey(edittext1.getText().toString())) {
								attendance_db.child(adblist.get((int)N1).get(edittext1.getText().toString()).toString()).removeValue();
								process.setText("Deleting (".concat(String.valueOf((long)(N1 + 1)).concat("/".concat(String.valueOf((long)(adblist.size())).concat(")")))));
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
		});
		
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
		_DESIGN();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), DevTeamleaderActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable D1 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ 0xFFFFFFFF,0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF});
		
		D1.setCornerRadius(10);
		a1.setElevation(20);
		a1.setBackground(D1);
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