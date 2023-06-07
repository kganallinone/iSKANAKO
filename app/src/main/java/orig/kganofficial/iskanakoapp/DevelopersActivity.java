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
import android.widget.ImageView;
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
import com.google.zxing.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class DevelopersActivity extends AppCompatActivity {
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private ImageView imageview1;
	private TextView textview9;
	private TextView textview10;
	private TextView textview6;
	private TextView textview5;
	private TextView textview7;
	private TextView textview8;
	private LinearLayout dev1;
	private LinearLayout dev2;
	private LinearLayout dev3;
	private LinearLayout dev4;
	private LinearLayout dev5;
	private TextView textview11;
	private CircleImageView circleimageview7;
	private LinearLayout linear4;
	private TextView textview3;
	private TextView textview4;
	private CircleImageView circleimageview8;
	private LinearLayout linear6;
	private TextView textview12;
	private TextView textview13;
	private CircleImageView circleimageview9;
	private LinearLayout linear8;
	private TextView textview14;
	private TextView textview15;
	private CircleImageView circleimageview10;
	private LinearLayout linear10;
	private TextView textview16;
	private TextView textview17;
	private CircleImageView circleimageview11;
	private LinearLayout linear12;
	private TextView textview18;
	private TextView textview19;
	
	private Intent PAGE = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.developers);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		textview9 = findViewById(R.id.textview9);
		textview10 = findViewById(R.id.textview10);
		textview6 = findViewById(R.id.textview6);
		textview5 = findViewById(R.id.textview5);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		dev1 = findViewById(R.id.dev1);
		dev2 = findViewById(R.id.dev2);
		dev3 = findViewById(R.id.dev3);
		dev4 = findViewById(R.id.dev4);
		dev5 = findViewById(R.id.dev5);
		textview11 = findViewById(R.id.textview11);
		circleimageview7 = findViewById(R.id.circleimageview7);
		linear4 = findViewById(R.id.linear4);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		circleimageview8 = findViewById(R.id.circleimageview8);
		linear6 = findViewById(R.id.linear6);
		textview12 = findViewById(R.id.textview12);
		textview13 = findViewById(R.id.textview13);
		circleimageview9 = findViewById(R.id.circleimageview9);
		linear8 = findViewById(R.id.linear8);
		textview14 = findViewById(R.id.textview14);
		textview15 = findViewById(R.id.textview15);
		circleimageview10 = findViewById(R.id.circleimageview10);
		linear10 = findViewById(R.id.linear10);
		textview16 = findViewById(R.id.textview16);
		textview17 = findViewById(R.id.textview17);
		circleimageview11 = findViewById(R.id.circleimageview11);
		linear12 = findViewById(R.id.linear12);
		textview18 = findViewById(R.id.textview18);
		textview19 = findViewById(R.id.textview19);
	}
	
	private void initializeLogic() {
		_DESIGN();
	}
	
	@Override
	public void onBackPressed() {
		PAGE.putExtra("USER", "NORMAL");
		PAGE.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(PAGE);
		finish();
	}
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		dev1.setElevation(16);
		dev1.setBackground(a);
		dev1.setElevation(16);
		dev1.setBackground(a);
		
		
		android.graphics.drawable.GradientDrawable b = new android.graphics.drawable.GradientDrawable();
		
		b.setColor(Color.parseColor("#FFFFFF"));
		b.setCornerRadius(10);
		
		dev2.setElevation(16);
		dev2.setBackground(b);
		dev2.setElevation(16);
		dev2.setBackground(b);
		
		
		android.graphics.drawable.GradientDrawable c = new android.graphics.drawable.GradientDrawable();
		
		c.setColor(Color.parseColor("#FFFFFF"));
		c.setCornerRadius(10);
		
		dev3.setElevation(16);
		dev3.setBackground(c);
		dev3.setElevation(16);
		dev3.setBackground(c);
		
		android.graphics.drawable.GradientDrawable d = new android.graphics.drawable.GradientDrawable();
		
		d.setColor(Color.parseColor("#FFFFFF"));
		d.setCornerRadius(10);
		
		dev4.setElevation(16);
		dev4.setBackground(d);
		dev4.setElevation(16);
		dev4.setBackground(d);
		
		android.graphics.drawable.GradientDrawable e = new android.graphics.drawable.GradientDrawable();
		
		e.setColor(Color.parseColor("#FFFFFF"));
		e.setCornerRadius(10);
		
		dev5.setElevation(16);
		dev5.setBackground(e);
		dev5.setElevation(16);
		dev5.setBackground(e);
		
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