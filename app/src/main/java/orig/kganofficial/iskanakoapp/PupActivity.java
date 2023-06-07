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

public class PupActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private ImageView imageview1;
	private LinearLayout web;
	private LinearLayout as;
	private LinearLayout sis;
	private LinearLayout mabinibox;
	private ImageView imageview2;
	private LinearLayout linear7;
	private TextView textview3;
	private ImageView imageview5;
	private LinearLayout linear13;
	private TextView textview6;
	private ImageView imageview3;
	private LinearLayout linear9;
	private TextView textview4;
	private ImageView imageview4;
	private LinearLayout linear11;
	private TextView textview5;
	
	private Intent PAGE = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.pup);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		web = findViewById(R.id.web);
		as = findViewById(R.id.as);
		sis = findViewById(R.id.sis);
		mabinibox = findViewById(R.id.mabinibox);
		imageview2 = findViewById(R.id.imageview2);
		linear7 = findViewById(R.id.linear7);
		textview3 = findViewById(R.id.textview3);
		imageview5 = findViewById(R.id.imageview5);
		linear13 = findViewById(R.id.linear13);
		textview6 = findViewById(R.id.textview6);
		imageview3 = findViewById(R.id.imageview3);
		linear9 = findViewById(R.id.linear9);
		textview4 = findViewById(R.id.textview4);
		imageview4 = findViewById(R.id.imageview4);
		linear11 = findViewById(R.id.linear11);
		textview5 = findViewById(R.id.textview5);
		
		web.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setAction(Intent.ACTION_VIEW);
				PAGE.setData(Uri.parse("https://www.pup.edu.ph/"));
				startActivity(PAGE);
				finish();
			}
		});
		
		as.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setAction(Intent.ACTION_VIEW);
				PAGE.setData(Uri.parse("https://apps.pup.edu.ph/appointment/"));
				startActivity(PAGE);
				finish();
			}
		});
		
		sis.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setAction(Intent.ACTION_VIEW);
				PAGE.setData(Uri.parse("https://sis2.pup.edu.ph/"));
				startActivity(PAGE);
				finish();
			}
		});
		
		mabinibox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setAction(Intent.ACTION_VIEW);
				PAGE.setData(Uri.parse("https://mabinibox.vercel.app/"));
				startActivity(PAGE);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
	}
	
	@Override
	public void onBackPressed() {
		PAGE.putExtra("USER", "NORMAL");
		PAGE.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(PAGE);
		finish();
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