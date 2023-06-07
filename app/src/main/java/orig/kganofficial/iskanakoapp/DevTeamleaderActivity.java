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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.budiyev.android.codescanner.*;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.AppBarLayout;
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

public class DevTeamleaderActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private double N = 0;
	private double L = 0;
	private String STUDENTTOTAL = "";
	private String FACULTYTOTAL = "";
	private String USERTOTAL = "";
	
	private ArrayList<HashMap<String, Object>> studentlist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> facultylist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout tab;
	private LinearLayout linear2;
	private ImageView menubar;
	private ScrollView vscroll1;
	private LinearLayout linear3;
	private TextView textview3;
	private LinearLayout usersdsply;
	private LinearLayout fandsdsply;
	private ImageView imageview1;
	private TextView textview4;
	private LinearLayout linear5;
	private TextView usrtotal;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private TextView textview8;
	private TextView faculty;
	private TextView textview9;
	private TextView student;
	private LinearLayout _drawer_linear1;
	private LinearLayout _drawer_linear2;
	private LinearLayout _drawer_linear5;
	private LinearLayout _drawer_linear6;
	private LinearLayout _drawer_linear8;
	private LinearLayout _drawer_linear9;
	private LinearLayout _drawer_linear7;
	private LinearLayout _drawer_linear10;
	private LinearLayout _drawer_linear11;
	private CircleImageView _drawer_circleimageview2;
	private ImageView _drawer_imageview1;
	private TextView _drawer_update;
	private ImageView _drawer_imageview2;
	private TextView _drawer_addsample;
	private ImageView _drawer_imageview4;
	private TextView _drawer_textview1;
	private ImageView _drawer_imageview5;
	private TextView _drawer_textview2;
	private ImageView _drawer_imageview3;
	private TextView _drawer_systemcon;
	private ImageView _drawer_imageview6;
	private TextView _drawer_sendannouncement;
	private ImageView _drawer_imageview7;
	private TextView _drawer_databasefixer;
	
	private Intent PAGE = new Intent();
	private DatabaseReference student_user_info = _firebase.getReference("student_user_info");
	private ChildEventListener _student_user_info_child_listener;
	private DatabaseReference faculty_user_info = _firebase.getReference("faculty_user_info");
	private ChildEventListener _faculty_user_info_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dev_teamleader);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(DevTeamleaderActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		linear1 = findViewById(R.id.linear1);
		tab = findViewById(R.id.tab);
		linear2 = findViewById(R.id.linear2);
		menubar = findViewById(R.id.menubar);
		vscroll1 = findViewById(R.id.vscroll1);
		linear3 = findViewById(R.id.linear3);
		textview3 = findViewById(R.id.textview3);
		usersdsply = findViewById(R.id.usersdsply);
		fandsdsply = findViewById(R.id.fandsdsply);
		imageview1 = findViewById(R.id.imageview1);
		textview4 = findViewById(R.id.textview4);
		linear5 = findViewById(R.id.linear5);
		usrtotal = findViewById(R.id.usrtotal);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		textview8 = findViewById(R.id.textview8);
		faculty = findViewById(R.id.faculty);
		textview9 = findViewById(R.id.textview9);
		student = findViewById(R.id.student);
		_drawer_linear1 = _nav_view.findViewById(R.id.linear1);
		_drawer_linear2 = _nav_view.findViewById(R.id.linear2);
		_drawer_linear5 = _nav_view.findViewById(R.id.linear5);
		_drawer_linear6 = _nav_view.findViewById(R.id.linear6);
		_drawer_linear8 = _nav_view.findViewById(R.id.linear8);
		_drawer_linear9 = _nav_view.findViewById(R.id.linear9);
		_drawer_linear7 = _nav_view.findViewById(R.id.linear7);
		_drawer_linear10 = _nav_view.findViewById(R.id.linear10);
		_drawer_linear11 = _nav_view.findViewById(R.id.linear11);
		_drawer_circleimageview2 = _nav_view.findViewById(R.id.circleimageview2);
		_drawer_imageview1 = _nav_view.findViewById(R.id.imageview1);
		_drawer_update = _nav_view.findViewById(R.id.update);
		_drawer_imageview2 = _nav_view.findViewById(R.id.imageview2);
		_drawer_addsample = _nav_view.findViewById(R.id.addsample);
		_drawer_imageview4 = _nav_view.findViewById(R.id.imageview4);
		_drawer_textview1 = _nav_view.findViewById(R.id.textview1);
		_drawer_imageview5 = _nav_view.findViewById(R.id.imageview5);
		_drawer_textview2 = _nav_view.findViewById(R.id.textview2);
		_drawer_imageview3 = _nav_view.findViewById(R.id.imageview3);
		_drawer_systemcon = _nav_view.findViewById(R.id.systemcon);
		_drawer_imageview6 = _nav_view.findViewById(R.id.imageview6);
		_drawer_sendannouncement = _nav_view.findViewById(R.id.sendannouncement);
		_drawer_imageview7 = _nav_view.findViewById(R.id.imageview7);
		_drawer_databasefixer = _nav_view.findViewById(R.id.databasefixer);
		
		menubar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.openDrawer(GravityCompat.START);
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
		
		_drawer_linear1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), AppUpdateActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		_drawer_addsample.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_drawer_systemcon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), AppControlsActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		_drawer_sendannouncement.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), AppAnnounceActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		_drawer_databasefixer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), AppDatabaseActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		_DISPLAY();
		_DESIGN();
		_GETALLUSER();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), DevLoginActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _DISPLAY() {
		try {
			getSupportActionBar().hide();
		} catch (Exception e) {}
		_DrawerWidth(250);
	}
	
	
	public void _DrawerWidth(final double _num) {
		
		LinearLayout _nav_view = (LinearLayout)findViewById(R.id._nav_view);
		
		_nav_view.setBackgroundColor(Color.parseColor("#FFFFFF"));
		
		androidx.drawerlayout.widget.DrawerLayout.LayoutParams params = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams)_nav_view.getLayoutParams ();
		
		params.width = (int)getDip((int)_num);
		
		params.height = androidx.drawerlayout.widget.DrawerLayout.LayoutParams.MATCH_PARENT;
		
		_nav_view.setLayoutParams(params);
	}
	
	
	public void _GETALLUSER() {
		student_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				studentlist = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						studentlist.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				STUDENTTOTAL = String.valueOf((long)(studentlist.size()));
				student.setText(STUDENTTOTAL);
				faculty_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						facultylist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								facultylist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						FACULTYTOTAL = String.valueOf((long)(facultylist.size()));
						faculty.setText(FACULTYTOTAL);
						USERTOTAL = String.valueOf((long)(Double.parseDouble(STUDENTTOTAL) + Double.parseDouble(FACULTYTOTAL)));
						usrtotal.setText(USERTOTAL);
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
		
		usersdsply.setElevation(16);
		usersdsply.setBackground(a);
		
		android.graphics.drawable.GradientDrawable b = new android.graphics.drawable.GradientDrawable();
		
		b.setColor(Color.parseColor("#FFFFFF"));
		b.setCornerRadius(10);
		
		fandsdsply.setElevation(16);
		fandsdsply.setBackground(b);
		
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