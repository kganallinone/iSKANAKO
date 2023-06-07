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
import com.google.firebase.FirebaseApp;
import com.google.zxing.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class DevLoginActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private LinearLayout uu;
	private ImageView imageview1;
	private EditText devcode;
	private Button student;
	private TextView textview3;
	
	private Intent PAGE = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dev_login);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		uu = findViewById(R.id.uu);
		imageview1 = findViewById(R.id.imageview1);
		devcode = findViewById(R.id.devcode);
		student = findViewById(R.id.student);
		textview3 = findViewById(R.id.textview3);
		
		student.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (devcode.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Hello our developer/s, your code is empty.");
				}
				else {
					if (devcode.getText().toString().equals("4B47414E")) {
						PAGE.setClass(getApplicationContext(), DevTeamleaderActivity.class);
						startActivity(PAGE);
						finish();
					}
					else {
						if (devcode.getText().toString().equals("MARTIN-DEV-01-2022-UU")) {
							PAGE.setClass(getApplicationContext(), DevMemberActivity.class);
							startActivity(PAGE);
							finish();
						}
						else {
							if (devcode.getText().toString().equals("MIGUEL-DEV-02-2022-UU")) {
								PAGE.setClass(getApplicationContext(), DevMemberActivity.class);
								startActivity(PAGE);
								finish();
							}
							else {
								if (devcode.getText().toString().equals("JAYSON-DEV-03-2022-UU")) {
									PAGE.setClass(getApplicationContext(), DevMemberActivity.class);
									startActivity(PAGE);
									finish();
								}
								else {
									if (devcode.getText().toString().equals("KENDRICK-DEV-04-2022-UU")) {
										PAGE.setClass(getApplicationContext(), DevMemberActivity.class);
										startActivity(PAGE);
										finish();
									}
									else {
										if (devcode.getText().toString().equals("MAAMANDIE-DEV")) {
											PAGE.setClass(getApplicationContext(), DevMemberActivity.class);
											startActivity(PAGE);
											finish();
										}
										else {
											if (devcode.getText().toString().equals("ARGEL-DEV-04-2023-UU")) {
												PAGE.setClass(getApplicationContext(), DevMemberActivity.class);
												startActivity(PAGE);
												finish();
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Exit this page if you are not developer.");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), TestActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		_DESIGN();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.setClass(getApplicationContext(), MainActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		uu.setElevation(16);
		uu.setBackground(a);
		
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