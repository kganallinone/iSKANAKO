package orig.kganofficial.iskanakoapp;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
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
import java.io.File;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class HomeActivity extends AppCompatActivity {
	
	public final int REQ_CD_FILE = 101;
	public final int REQ_CD_CAM = 102;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private String VERSION = "";
	private String share = "";
	private double change = 0;
	private String PLAYSTORE = "";
	private String DOWNLOAD = "";
	private String UPDATEINFO = "";
	private String ANNOUNCE = "";
	
	private ArrayList<String> versionstr = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> versionlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout tab;
	private LinearLayout linear5;
	private ImageView menubar;
	private LinearLayout linear7;
	private LinearLayout logindisplay;
	private LinearLayout linear8;
	private Button admin;
	private Button faculty;
	private Button student;
	private TextView textview5;
	private LinearLayout linear10;
	private LinearLayout linear12;
	private CircleImageView circleimageview3;
	private LinearLayout line;
	private LinearLayout linear6;
	private TextView textview1;
	private TextView textview2;
	private TextView textview6;
	private TextView textview8;
	private TextView textview7;
	private ListView versiondisplay;
	private LinearLayout _drawer_linear1;
	private LinearLayout _drawer_linear3;
	private LinearLayout _drawer_linear6;
	private LinearLayout _drawer_linear8;
	private LinearLayout _drawer_linear7;
	private LinearLayout _drawer_line;
	private LinearLayout _drawer_linear11;
	private LinearLayout _drawer_linear13;
	private LinearLayout _drawer_linear9;
	private LinearLayout _drawer_linear10;
	private LinearLayout _drawer_linear14;
	private LinearLayout _drawer_linear15;
	private LinearLayout _drawer_linear16;
	private ImageView _drawer_imageview2;
	private TextView _drawer_dev;
	private ImageView _drawer_imageview4;
	private TextView _drawer_about;
	private ImageView _drawer_imageview3;
	private TextView _drawer_qar;
	private ImageView _drawer_imageview7;
	private TextView _drawer_pup;
	private ImageView _drawer_imageview5;
	private TextView _drawer_suggest;
	private ImageView _drawer_imageview6;
	private TextView _drawer_contact;
	private ImageView _drawer_imageview8;
	private TextView _drawer_share;
	private ImageView _drawer_devpage;
	
	private Intent PAGE = new Intent();
	private Intent file = new Intent(Intent.ACTION_GET_CONTENT);
	private DatabaseReference appinfo_iskanako = _firebase.getReference("appinfo_iskanako");
	private ChildEventListener _appinfo_iskanako_child_listener;
	private AlertDialog.Builder dialog;
	private Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_cam;
	private AlertDialog.Builder exit;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
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
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		linear1 = findViewById(R.id.linear1);
		tab = findViewById(R.id.tab);
		linear5 = findViewById(R.id.linear5);
		menubar = findViewById(R.id.menubar);
		linear7 = findViewById(R.id.linear7);
		logindisplay = findViewById(R.id.logindisplay);
		linear8 = findViewById(R.id.linear8);
		admin = findViewById(R.id.admin);
		faculty = findViewById(R.id.faculty);
		student = findViewById(R.id.student);
		textview5 = findViewById(R.id.textview5);
		linear10 = findViewById(R.id.linear10);
		linear12 = findViewById(R.id.linear12);
		circleimageview3 = findViewById(R.id.circleimageview3);
		line = findViewById(R.id.line);
		linear6 = findViewById(R.id.linear6);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		textview6 = findViewById(R.id.textview6);
		textview8 = findViewById(R.id.textview8);
		textview7 = findViewById(R.id.textview7);
		versiondisplay = findViewById(R.id.versiondisplay);
		_drawer_linear1 = _nav_view.findViewById(R.id.linear1);
		_drawer_linear3 = _nav_view.findViewById(R.id.linear3);
		_drawer_linear6 = _nav_view.findViewById(R.id.linear6);
		_drawer_linear8 = _nav_view.findViewById(R.id.linear8);
		_drawer_linear7 = _nav_view.findViewById(R.id.linear7);
		_drawer_line = _nav_view.findViewById(R.id.line);
		_drawer_linear11 = _nav_view.findViewById(R.id.linear11);
		_drawer_linear13 = _nav_view.findViewById(R.id.linear13);
		_drawer_linear9 = _nav_view.findViewById(R.id.linear9);
		_drawer_linear10 = _nav_view.findViewById(R.id.linear10);
		_drawer_linear14 = _nav_view.findViewById(R.id.linear14);
		_drawer_linear15 = _nav_view.findViewById(R.id.linear15);
		_drawer_linear16 = _nav_view.findViewById(R.id.linear16);
		_drawer_imageview2 = _nav_view.findViewById(R.id.imageview2);
		_drawer_dev = _nav_view.findViewById(R.id.dev);
		_drawer_imageview4 = _nav_view.findViewById(R.id.imageview4);
		_drawer_about = _nav_view.findViewById(R.id.about);
		_drawer_imageview3 = _nav_view.findViewById(R.id.imageview3);
		_drawer_qar = _nav_view.findViewById(R.id.qar);
		_drawer_imageview7 = _nav_view.findViewById(R.id.imageview7);
		_drawer_pup = _nav_view.findViewById(R.id.pup);
		_drawer_imageview5 = _nav_view.findViewById(R.id.imageview5);
		_drawer_suggest = _nav_view.findViewById(R.id.suggest);
		_drawer_imageview6 = _nav_view.findViewById(R.id.imageview6);
		_drawer_contact = _nav_view.findViewById(R.id.contact);
		_drawer_imageview8 = _nav_view.findViewById(R.id.imageview8);
		_drawer_share = _nav_view.findViewById(R.id.share);
		_drawer_devpage = _nav_view.findViewById(R.id.devpage);
		file.setType("*/*");
		file.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		dialog = new AlertDialog.Builder(this);
		_file_cam = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_cam;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_cam = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_cam);
		} else {
			_uri_cam = Uri.fromFile(_file_cam);
		}
		cam.putExtra(MediaStore.EXTRA_OUTPUT, _uri_cam);
		cam.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		exit = new AlertDialog.Builder(this);
		
		menubar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.openDrawer(GravityCompat.START);
			}
		});
		
		admin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), AcadheadloginActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		faculty.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.putExtra("TYPE", "FACULTY");
				PAGE.setClass(getApplicationContext(), LoginActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		student.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.putExtra("TYPE", "STUDENT");
				PAGE.setClass(getApplicationContext(), LoginActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		//OnTouch
		textview5.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		circleimageview3.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				if (change == 0) {
					circleimageview3.setImageResource(R.drawable.pup_logo);
					faculty.setVisibility(View.GONE);
					student.setVisibility(View.GONE);
					admin.setVisibility(View.VISIBLE);
					change = 1;
				}
				else {
					circleimageview3.setImageResource(R.drawable.iskanako_01);
					faculty.setVisibility(View.VISIBLE);
					student.setVisibility(View.VISIBLE);
					admin.setVisibility(View.GONE);
					change = 0;
				}
				return true;
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.putExtra("PG", "1");
				PAGE.setClass(getApplicationContext(), RegisterActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		textview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), ForgotActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		_appinfo_iskanako_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
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
						versionstr.add(_childKey);
						versiondisplay.setAdapter(new VersiondisplayAdapter(versionlist));
						((BaseAdapter)versiondisplay.getAdapter()).notifyDataSetChanged();
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
						versiondisplay.setAdapter(new VersiondisplayAdapter(versionlist));
						((BaseAdapter)versiondisplay.getAdapter()).notifyDataSetChanged();
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
						versiondisplay.setAdapter(new VersiondisplayAdapter(versionlist));
						((BaseAdapter)versiondisplay.getAdapter()).notifyDataSetChanged();
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
		appinfo_iskanako.addChildEventListener(_appinfo_iskanako_child_listener);
		
		_drawer_linear1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_dev.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), DevelopersActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		_drawer_about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), AboutappActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		_drawer_qar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("iSKAN AKO");
				dialog.setIcon(R.drawable.iskanako_01);
				dialog.setMessage("This area is not available");
				dialog.setCancelable(false);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		_drawer_pup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PAGE.setClass(getApplicationContext(), PupActivity.class);
				startActivity(PAGE);
				finish();
			}
		});
		
		_drawer_suggest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("iSKAN AKO");
				dialog.setIcon(R.drawable.iskanako_01);
				dialog.setMessage("Do you notice anything we need to change or improve or maybe add to the application? Suggest Now!");
				dialog.setCancelable(false);
				dialog.setPositiveButton("SUGGEST", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						PAGE.setAction(Intent.ACTION_VIEW);
						PAGE.setData(Uri.parse("mailto:".concat("systemiscanit@gmail.com")));
						PAGE.putExtra("android.intent.extra.SUBJECT", "iSKAN AKO: Suggestion");
						PAGE.putExtra("android.intent.extra.TEXT", "[ENTER YOUR SUGGESTION HERE]");
						startActivity(PAGE);
					}
				});
				dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		_drawer_contact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("iSKAN AKO");
				dialog.setIcon(R.drawable.iskanako_01);
				dialog.setMessage("You can contact us using our email.\n\n[ Email ] systemiscanit@gmail.com\n");
				dialog.setCancelable(false);
				dialog.setPositiveButton("SEND EMAIL", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						PAGE.setAction(Intent.ACTION_VIEW);
						PAGE.setData(Uri.parse("mailto:".concat("systemiscanit@gmail.com")));
						PAGE.putExtra("android.intent.extra.SUBJECT", "iSKAN AKO: User Support");
						PAGE.putExtra("android.intent.extra.TEXT", "[ENTER YOUR QUESTION HERE OR WHAT CAN WE HELP]");
						startActivity(PAGE);
					}
				});
				dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		_drawer_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_ShareToFriends();
			}
		});
		
		_drawer_devpage.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				PAGE.setClass(getApplicationContext(), DevLoginActivity.class);
				startActivity(PAGE);
				finish();
				return true;
			}
		});
	}
	
	private void initializeLogic() {
		PAGE.putExtra("USER", getIntent().getStringExtra("USER"));
		_DETECTEXTERNAL();
		_DISPLAY();
	}
	
	@Override
	public void onBackPressed() {
		exit.setTitle("iSKAN AKO");
		exit.setIcon(R.drawable.unex_black_logo);
		exit.setMessage("You want to exit to this application?");
		exit.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finish();
			}
		});
		exit.create().show();
	}
	public void _DISPLAY() {
		if (getIntent().getStringExtra("USER").equals("NORMAL")) {
			change = 0;
			faculty.setVisibility(View.VISIBLE);
			student.setVisibility(View.VISIBLE);
			admin.setVisibility(View.GONE);
			circleimageview3.setImageResource(R.drawable.iskanako_01);
		}
		else {
			change = 1;
			faculty.setVisibility(View.GONE);
			student.setVisibility(View.GONE);
			admin.setVisibility(View.VISIBLE);
			circleimageview3.setImageResource(R.drawable.pup_logo);
		}
		try {
			getSupportActionBar().hide();
		} catch (Exception e) {}
		LinearLayout _nav_view = (LinearLayout)findViewById(R.id._nav_view);
		
		_nav_view.setBackgroundColor(Color.parseColor("#FFFFFF"));
		
		androidx.drawerlayout.widget.DrawerLayout.LayoutParams params = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams)_nav_view.getLayoutParams ();
		
		params.width = (int)getDip((int)250);
		
		params.height = androidx.drawerlayout.widget.DrawerLayout.LayoutParams.MATCH_PARENT;
		
		_nav_view.setLayoutParams(params);
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		logindisplay.setElevation(10);
		logindisplay.setBackground(a);
		versiondisplay.setEnabled(false);
	}
	
	
	public void _ShareToFriends() {
		share = "Hello PUP Lopez Students! I recommend you to download this application!\n\nApplication Name: iSKAN AKO\nDeveloper: Unexpected Upgrade Team (PUP Lopez BSIT Students)\nDescription: This is to make it easier for faculty and students to obtain attendance. It also cuts down on time spent on maual attendance.\n\n\nClick the link to download. Thank you!\nhttps://www.mediafire.com/file/kpgdoo7vszpu15v/Iskan_Ako_1.0.1_beta_test_1.apk/file";
		Intent i = new Intent(android.content.Intent.ACTION_SEND);
		
		i.setType("text/plain");
		i.putExtra(android.content.Intent.EXTRA_TEXT, share);
		
		startActivity(Intent.createChooser(i, "share using"));
	}
	
	
	public void _DETECTEXTERNAL() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			
			FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()));
			FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/iSKAN AKO"));
			FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/iSKAN AKO/EXCEL"));
			FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/iSKAN AKO/EXCEL/BY FILE"));
			FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/iSKAN AKO/EXCEL/BY SUBJECTS"));
			FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/iSKAN AKO/IMAGE"));
		} 
		else {
			SketchwareUtil.showMessage(getApplicationContext(), "ERROR");
		}
	}
	
	public class VersiondisplayAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public VersiondisplayAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.version, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final TextView version = _view.findViewById(R.id.version);
			
			VERSION = "2023-BETA-TEST-4";
			if (_data.get((int)0).get("VERSION").toString().equals(VERSION)) {
				version.setText(_data.get((int)0).get("VERSION").toString());
			}
			else {
				version.setText(_data.get((int)0).get("VERSION").toString());
				PLAYSTORE = _data.get((int)0).get("PLAYSTORE").toString();
				DOWNLOAD = _data.get((int)0).get("UPDATELINK").toString();
				UPDATEINFO = _data.get((int)0).get("UPDATEINFO").toString();
				final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
				View inflate = getLayoutInflater().inflate(R.layout.updatenoexit,null); 
				dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				dialog.setView(inflate);
				LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
				TextView nf = (TextView) inflate.findViewById(R.id.nf);
				ImageView gplay = (ImageView) inflate.findViewById(R.id.gplay);
				ImageView download = (ImageView) inflate.findViewById(R.id.download);
				nf.setText(UPDATEINFO);
				gplay.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						PAGE.setAction(Intent.ACTION_VIEW);
						PAGE.setData(Uri.parse(PLAYSTORE));
						startActivity(PAGE);
						finishAffinity();
						
					}
				});
				download.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						PAGE.setAction(Intent.ACTION_VIEW);
						PAGE.setData(Uri.parse(DOWNLOAD));
						startActivity(PAGE);
						finishAffinity();
						
					}
				});
				dialog.setCancelable(false);
				dialog.show();
			}
			if (_data.get((int)0).get("Z_SHOWUPDATEDIALOG").toString().equals("ON")) {
				PLAYSTORE = _data.get((int)0).get("PLAYSTORE").toString();
				DOWNLOAD = _data.get((int)0).get("UPDATELINK").toString();
				UPDATEINFO = _data.get((int)0).get("UPDATEINFO").toString();
				final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
				View inflate = getLayoutInflater().inflate(R.layout.updatedialog,null); 
				dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				dialog.setView(inflate);
				LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
				ImageView close = (ImageView) inflate.findViewById(R.id.close);
				TextView nf = (TextView) inflate.findViewById(R.id.nf);
				ImageView gplay = (ImageView) inflate.findViewById(R.id.gplay);
				ImageView download = (ImageView) inflate.findViewById(R.id.download);
				nf.setText(UPDATEINFO);
				gplay.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						PAGE.setAction(Intent.ACTION_VIEW);
						PAGE.setData(Uri.parse(PLAYSTORE));
						startActivity(PAGE);
						
					}
				});
				download.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						PAGE.setAction(Intent.ACTION_VIEW);
						PAGE.setData(Uri.parse(DOWNLOAD));
						startActivity(PAGE);
						
					}
				});
				close.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						 
						
					}
				});
				dialog.setCancelable(false);
				dialog.show();
			}
			else {
				
			}
			if (_data.get((int)0).get("Z_UNDERMAINTENANCE").toString().equals("ON")) {
				dialog.setTitle("iSKAN AKO [ANNOUNCEMENT]");
				dialog.setIcon(R.drawable.iskanako_01);
				dialog.setMessage("Sorry for the inconvenience, but this application is currently unavailable or under maintenance. Return in a few hours to update or use this application once more. Thank you very much!");
				dialog.setCancelable(false);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				dialog.create().show();
			}
			else {
				
			}
			if (_data.get((int)0).get("TEST").toString().equals("ON")) {
				PLAYSTORE = _data.get((int)0).get("PLAYSTORE").toString();
				DOWNLOAD = _data.get((int)0).get("UPDATELINK").toString();
				UPDATEINFO = _data.get((int)0).get("UPDATEINFO").toString();
				final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
				View inflate = getLayoutInflater().inflate(R.layout.updatedialog,null); 
				dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				dialog.setView(inflate);
				LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
				ImageView close = (ImageView) inflate.findViewById(R.id.close);
				TextView nf = (TextView) inflate.findViewById(R.id.nf);
				ImageView gplay = (ImageView) inflate.findViewById(R.id.gplay);
				ImageView download = (ImageView) inflate.findViewById(R.id.download);
				nf.setText(UPDATEINFO);
				gplay.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						PAGE.setAction(Intent.ACTION_VIEW);
						PAGE.setData(Uri.parse(PLAYSTORE));
						startActivity(PAGE);
						
					}
				});
				download.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						PAGE.setAction(Intent.ACTION_VIEW);
						PAGE.setData(Uri.parse(DOWNLOAD));
						startActivity(PAGE);
						
					}
				});
				close.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						 
						
					}
				});
				dialog.setCancelable(false);
				dialog.show();
			}
			else {
				
			}
			if (_data.get((int)0).get("Z_ANNOUNCEMENT").toString().equals("ON")) {
				ANNOUNCE = _data.get((int)0).get("ANNOUNCE").toString();
				final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
				View inflate = getLayoutInflater().inflate(R.layout.announce,null); 
				dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				dialog.setView(inflate);
				LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
				ImageView close = (ImageView) inflate.findViewById(R.id.close);
				TextView nf = (TextView) inflate.findViewById(R.id.nf);
				nf.setText(ANNOUNCE);
				close.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog.dismiss();
						
						 
						
					}
				});
				dialog.setCancelable(false);
				dialog.show();
			}
			else {
				
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