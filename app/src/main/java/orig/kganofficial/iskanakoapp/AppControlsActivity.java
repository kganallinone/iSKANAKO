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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
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

public class AppControlsActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> open = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> appinfo = new ArrayList<>();
	
	private LinearLayout linear5;
	private TextView textview3;
	private TextView textview9;
	private LinearLayout linear13;
	private LinearLayout dsply1;
	private LinearLayout dsply2;
	private LinearLayout dsply;
	private LinearLayout linear15;
	private Switch switch2;
	private TextView textview10;
	private Switch switch3;
	private TextView textview11;
	private Switch switch4;
	private TextView textview12;
	private Switch switch5;
	private TextView textview13;
	
	private DatabaseReference appinfo_iskanako = _firebase.getReference("appinfo_iskanako");
	private ChildEventListener _appinfo_iskanako_child_listener;
	private Intent PAGE = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.app_controls);
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
		dsply1 = findViewById(R.id.dsply1);
		dsply2 = findViewById(R.id.dsply2);
		dsply = findViewById(R.id.dsply);
		linear15 = findViewById(R.id.linear15);
		switch2 = findViewById(R.id.switch2);
		textview10 = findViewById(R.id.textview10);
		switch3 = findViewById(R.id.switch3);
		textview11 = findViewById(R.id.textview11);
		switch4 = findViewById(R.id.switch4);
		textview12 = findViewById(R.id.textview12);
		switch5 = findViewById(R.id.switch5);
		textview13 = findViewById(R.id.textview13);
		
		switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					
					switch2.setText("ON");
					open = new HashMap<>();
					open.put("Z_UNDERMAINTENANCE", "ON");
					appinfo_iskanako.child("APK-INFO").updateChildren(open);
					open.clear();
				} 
				else {
					switch2.setText("OFF");
					open = new HashMap<>();
					open.put("Z_UNDERMAINTENANCE", "OFF");
					appinfo_iskanako.child("APK-INFO").updateChildren(open);
					open.clear();
				}
			}
		});
		
		switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					
					switch3.setText("ON");
					open = new HashMap<>();
					open.put("TEST", "ON");
					appinfo_iskanako.child("APK-INFO").updateChildren(open);
					open.clear();
				} 
				else {
					switch3.setText("OFF");
					open = new HashMap<>();
					open.put("TEST", "OFF");
					appinfo_iskanako.child("APK-INFO").updateChildren(open);
					open.clear();
				}
			}
		});
		
		switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					
					switch4.setText("ON");
					open = new HashMap<>();
					open.put("Z_SHOWUPDATEDIALOG", "ON");
					appinfo_iskanako.child("APK-INFO").updateChildren(open);
					open.clear();
				} 
				else {
					switch4.setText("OFF");
					open = new HashMap<>();
					open.put("Z_SHOWUPDATEDIALOG", "OFF");
					appinfo_iskanako.child("APK-INFO").updateChildren(open);
					open.clear();
				}
			}
		});
		
		switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					
					switch5.setText("ON");
					open = new HashMap<>();
					open.put("Z_ANNOUNCEMENT", "ON");
					appinfo_iskanako.child("APK-INFO").updateChildren(open);
					open.clear();
				} 
				else {
					switch5.setText("OFF");
					open = new HashMap<>();
					open.put("Z_ANNOUNCEMENT", "OFF");
					appinfo_iskanako.child("APK-INFO").updateChildren(open);
					open.clear();
				}
			}
		});
		
		_appinfo_iskanako_child_listener = new ChildEventListener() {
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
		appinfo_iskanako.addChildEventListener(_appinfo_iskanako_child_listener);
	}
	
	private void initializeLogic() {
		_GETINFO();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), DevTeamleaderActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _GETINFO() {
		appinfo_iskanako.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				appinfo = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						appinfo.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (appinfo.get((int)0).get("TEST").toString().equals("ON")) {
					switch3.setChecked(true);
				}
				else {
					switch3.setChecked(false);
				}
				if (appinfo.get((int)0).get("Z_ANNOUNCEMENT").toString().equals("ON")) {
					switch5.setChecked(true);
				}
				else {
					switch5.setChecked(false);
				}
				if (appinfo.get((int)0).get("Z_SHOWUPDATEDIALOG").toString().equals("ON")) {
					switch4.setChecked(true);
				}
				else {
					switch4.setChecked(false);
				}
				if (appinfo.get((int)0).get("Z_UNDERMAINTENANCE").toString().equals("ON")) {
					switch2.setChecked(true);
				}
				else {
					switch2.setChecked(false);
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
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