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

public class AppAnnounceActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double N = 0;
	private double L = 0;
	private HashMap<String, Object> create = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> versionlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout up;
	private LinearLayout linear3;
	private LinearLayout line;
	private ScrollView vscroll1;
	private Button update;
	private Button button1;
	private TextView textview3;
	private LinearLayout linear9;
	private EditText edittext1;
	
	private DatabaseReference appinfo_iskanako = _firebase.getReference("appinfo_iskanako");
	private ChildEventListener _appinfo_iskanako_child_listener;
	private Intent PAGE = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.app_announce);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		up = findViewById(R.id.up);
		linear3 = findViewById(R.id.linear3);
		line = findViewById(R.id.line);
		vscroll1 = findViewById(R.id.vscroll1);
		update = findViewById(R.id.update);
		button1 = findViewById(R.id.button1);
		textview3 = findViewById(R.id.textview3);
		linear9 = findViewById(R.id.linear9);
		edittext1 = findViewById(R.id.edittext1);
		
		update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Complete your info.");
				}
				else {
					create = new HashMap<>();
					create.put("ANNOUNCE", edittext1.getText().toString());
					appinfo_iskanako.child("APK-INFO").updateChildren(create);
					create.clear();
					SketchwareUtil.showMessage(getApplicationContext(), "Uploaded");
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
		_DESIGN();
		_GETVERSION();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), DevTeamleaderActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _GETVERSION() {
		appinfo_iskanako.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				versionlist = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						versionlist.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				edittext1.setText(versionlist.get((int)0).get("ANNOUNCE").toString());
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		up.setElevation(16);
		up.setBackground(a);
		
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