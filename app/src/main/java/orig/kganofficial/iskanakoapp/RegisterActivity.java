package orig.kganofficial.iskanakoapp;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.budiyev.android.codescanner.*;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import me.dm7.barcodescanner.core.*;
import org.json.*;

public class RegisterActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String PG = "";
	private double N = 0;
	private double L = 0;
	private HashMap<String, Object> createaccount = new HashMap<>();
	private String USER = "";
	private String SEX = "";
	private String CHECK = "";
	private double MaxLength = 0;
	private double MaxLength2 = 0;
	private String STUDENTID = "";
	private double MaxLength3 = 0;
	private double MaxLength4 = 0;
	private String TERM = "";
	private boolean pas2 = false;
	private double total = 0;
	private String text = "";
	private double len = 0;
	private double y = 0;
	private boolean truee = false;
	private double pw_no = 0;
	private String VERIFICATION = "";
	private String STR = "";
	private String StrongPW = "";
	
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> userlist = new ArrayList<>();
	private ArrayList<String> roomstr = new ArrayList<>();
	private ArrayList<String> sexstr = new ArrayList<>();
	
	private LinearLayout registerpage;
	private LinearLayout linear29;
	private ScrollView vscroll1;
	private LinearLayout linear58;
	private LinearLayout linear59;
	private TextView textview1;
	private LinearLayout linear30;
	private LinearLayout pg1;
	private TextView reg_txt;
	private LinearLayout pg2;
	private LinearLayout pg3;
	private LinearLayout pg4;
	private LinearLayout pg5;
	private LinearLayout pg6;
	private LinearLayout pg7;
	private LinearLayout pg8;
	private TextView nones;
	private LinearLayout linear34;
	private LinearLayout linear33;
	private LinearLayout linear32;
	private TextView textview9;
	private TextView textview10;
	private Button faculty;
	private Button student;
	private LinearLayout linear61;
	private LinearLayout linear62;
	private LinearLayout linear63;
	private TextView textview21;
	private TextView textview22;
	private TextView txt_zv;
	private TextView textview26;
	private LinearLayout linear77;
	private LinearLayout eml_box;
	private EditText edittext_s;
	private LinearLayout pw_box;
	private LinearLayout cpw_box;
	private LinearLayout linear12;
	private LinearLayout linear71;
	private LinearLayout linear52;
	private RadioButton msteam;
	private RadioButton other;
	private ImageView imageview10;
	private EditText email;
	private ImageView imageview5;
	private EditText pw;
	private ImageView imageview6;
	private ImageView imageview8;
	private EditText cpw;
	private ImageView imageview9;
	private LinearLayout ps1;
	private LinearLayout ps2;
	private LinearLayout ps3;
	private LinearLayout ps4;
	private TextView pg1_error;
	private Button pg5_back;
	private Button pg5_save;
	private LinearLayout linear66;
	private LinearLayout linear67;
	private LinearLayout linear68;
	private TextView textview24;
	private TextView textview25;
	private TextView email_verify_txt;
	private TextView textview3;
	private TextView verify_txt;
	private LinearLayout linear69;
	private Button button4;
	private Button button5;
	private LinearLayout linear36;
	private LinearLayout linear35;
	private LinearLayout linear37;
	private TextView textview11;
	private TextView textview12;
	private LinearLayout fn_box;
	private LinearLayout mi_box;
	private LinearLayout ln_box;
	private LinearLayout sf_box;
	private LinearLayout linear73;
	private LinearLayout linear38;
	private ImageView imageview11;
	private EditText fn;
	private ImageView imageview13;
	private EditText mi;
	private ImageView imageview14;
	private EditText ln;
	private ImageView imageview15;
	private EditText sf;
	private TextView pg4_error;
	private Button pg2_back;
	private Button pg2_next;
	private LinearLayout linear39;
	private LinearLayout linear40;
	private LinearLayout linear41;
	private TextView textview13;
	private TextView textview14;
	private LinearLayout linear43;
	private LinearLayout linear42;
	private EditText sex;
	private Spinner spinner1;
	private Button pg3_back;
	private Button pg3_next;
	private LinearLayout linear44;
	private LinearLayout linear45;
	private LinearLayout linear46;
	private TextView textview15;
	private TextView textview16;
	private EditText employeeno;
	private EditText id;
	private LinearLayout stdntid;
	private LinearLayout numberdsply;
	private LinearLayout linear76;
	private LinearLayout linear47;
	private EditText year;
	private EditText edittext1;
	private EditText studentno;
	private EditText edittext3;
	private EditText lastofid;
	private EditText number;
	private TextView pg6_error;
	private Button pg4_back;
	private Button pg4_next;
	private LinearLayout linear49;
	private LinearLayout linear50;
	private LinearLayout linear51;
	private TextView textview17;
	private TextView textview18;
	private WebView termandcon;
	private CheckBox checkbox1;
	private LinearLayout linear70;
	private Button button6;
	private Button button7;
	private LinearLayout linear54;
	private LinearLayout linear55;
	private LinearLayout linear56;
	private TextView textview19;
	private TextView textview20;
	private ImageView imageview1;
	private CheckBox captha;
	private LinearLayout linear57;
	private Button pg6_back;
	private Button register;
	
	private DatabaseReference student_user_info = _firebase.getReference("student_user_info");
	private ChildEventListener _student_user_info_child_listener;
	private FirebaseAuth users;
	private OnCompleteListener<AuthResult> _users_create_user_listener;
	private OnCompleteListener<AuthResult> _users_sign_in_listener;
	private OnCompleteListener<Void> _users_reset_password_listener;
	private OnCompleteListener<Void> users_updateEmailListener;
	private OnCompleteListener<Void> users_updatePasswordListener;
	private OnCompleteListener<Void> users_emailVerificationSentListener;
	private OnCompleteListener<Void> users_deleteUserListener;
	private OnCompleteListener<Void> users_updateProfileListener;
	private OnCompleteListener<AuthResult> users_phoneAuthListener;
	private OnCompleteListener<AuthResult> users_googleSignInListener;
	
	private Intent PAGE = new Intent();
	private DatabaseReference faculty_user_info = _firebase.getReference("faculty_user_info");
	private ChildEventListener _faculty_user_info_child_listener;
	private AlertDialog.Builder reg_dialog;
	private Calendar date = Calendar.getInstance();
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.register);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		registerpage = findViewById(R.id.registerpage);
		linear29 = findViewById(R.id.linear29);
		vscroll1 = findViewById(R.id.vscroll1);
		linear58 = findViewById(R.id.linear58);
		linear59 = findViewById(R.id.linear59);
		textview1 = findViewById(R.id.textview1);
		linear30 = findViewById(R.id.linear30);
		pg1 = findViewById(R.id.pg1);
		reg_txt = findViewById(R.id.reg_txt);
		pg2 = findViewById(R.id.pg2);
		pg3 = findViewById(R.id.pg3);
		pg4 = findViewById(R.id.pg4);
		pg5 = findViewById(R.id.pg5);
		pg6 = findViewById(R.id.pg6);
		pg7 = findViewById(R.id.pg7);
		pg8 = findViewById(R.id.pg8);
		nones = findViewById(R.id.nones);
		linear34 = findViewById(R.id.linear34);
		linear33 = findViewById(R.id.linear33);
		linear32 = findViewById(R.id.linear32);
		textview9 = findViewById(R.id.textview9);
		textview10 = findViewById(R.id.textview10);
		faculty = findViewById(R.id.faculty);
		student = findViewById(R.id.student);
		linear61 = findViewById(R.id.linear61);
		linear62 = findViewById(R.id.linear62);
		linear63 = findViewById(R.id.linear63);
		textview21 = findViewById(R.id.textview21);
		textview22 = findViewById(R.id.textview22);
		txt_zv = findViewById(R.id.txt_zv);
		textview26 = findViewById(R.id.textview26);
		linear77 = findViewById(R.id.linear77);
		eml_box = findViewById(R.id.eml_box);
		edittext_s = findViewById(R.id.edittext_s);
		pw_box = findViewById(R.id.pw_box);
		cpw_box = findViewById(R.id.cpw_box);
		linear12 = findViewById(R.id.linear12);
		linear71 = findViewById(R.id.linear71);
		linear52 = findViewById(R.id.linear52);
		msteam = findViewById(R.id.msteam);
		other = findViewById(R.id.other);
		imageview10 = findViewById(R.id.imageview10);
		email = findViewById(R.id.email);
		imageview5 = findViewById(R.id.imageview5);
		pw = findViewById(R.id.pw);
		imageview6 = findViewById(R.id.imageview6);
		imageview8 = findViewById(R.id.imageview8);
		cpw = findViewById(R.id.cpw);
		imageview9 = findViewById(R.id.imageview9);
		ps1 = findViewById(R.id.ps1);
		ps2 = findViewById(R.id.ps2);
		ps3 = findViewById(R.id.ps3);
		ps4 = findViewById(R.id.ps4);
		pg1_error = findViewById(R.id.pg1_error);
		pg5_back = findViewById(R.id.pg5_back);
		pg5_save = findViewById(R.id.pg5_save);
		linear66 = findViewById(R.id.linear66);
		linear67 = findViewById(R.id.linear67);
		linear68 = findViewById(R.id.linear68);
		textview24 = findViewById(R.id.textview24);
		textview25 = findViewById(R.id.textview25);
		email_verify_txt = findViewById(R.id.email_verify_txt);
		textview3 = findViewById(R.id.textview3);
		verify_txt = findViewById(R.id.verify_txt);
		linear69 = findViewById(R.id.linear69);
		button4 = findViewById(R.id.button4);
		button5 = findViewById(R.id.button5);
		linear36 = findViewById(R.id.linear36);
		linear35 = findViewById(R.id.linear35);
		linear37 = findViewById(R.id.linear37);
		textview11 = findViewById(R.id.textview11);
		textview12 = findViewById(R.id.textview12);
		fn_box = findViewById(R.id.fn_box);
		mi_box = findViewById(R.id.mi_box);
		ln_box = findViewById(R.id.ln_box);
		sf_box = findViewById(R.id.sf_box);
		linear73 = findViewById(R.id.linear73);
		linear38 = findViewById(R.id.linear38);
		imageview11 = findViewById(R.id.imageview11);
		fn = findViewById(R.id.fn);
		imageview13 = findViewById(R.id.imageview13);
		mi = findViewById(R.id.mi);
		imageview14 = findViewById(R.id.imageview14);
		ln = findViewById(R.id.ln);
		imageview15 = findViewById(R.id.imageview15);
		sf = findViewById(R.id.sf);
		pg4_error = findViewById(R.id.pg4_error);
		pg2_back = findViewById(R.id.pg2_back);
		pg2_next = findViewById(R.id.pg2_next);
		linear39 = findViewById(R.id.linear39);
		linear40 = findViewById(R.id.linear40);
		linear41 = findViewById(R.id.linear41);
		textview13 = findViewById(R.id.textview13);
		textview14 = findViewById(R.id.textview14);
		linear43 = findViewById(R.id.linear43);
		linear42 = findViewById(R.id.linear42);
		sex = findViewById(R.id.sex);
		spinner1 = findViewById(R.id.spinner1);
		pg3_back = findViewById(R.id.pg3_back);
		pg3_next = findViewById(R.id.pg3_next);
		linear44 = findViewById(R.id.linear44);
		linear45 = findViewById(R.id.linear45);
		linear46 = findViewById(R.id.linear46);
		textview15 = findViewById(R.id.textview15);
		textview16 = findViewById(R.id.textview16);
		employeeno = findViewById(R.id.employeeno);
		id = findViewById(R.id.id);
		stdntid = findViewById(R.id.stdntid);
		numberdsply = findViewById(R.id.numberdsply);
		linear76 = findViewById(R.id.linear76);
		linear47 = findViewById(R.id.linear47);
		year = findViewById(R.id.year);
		edittext1 = findViewById(R.id.edittext1);
		studentno = findViewById(R.id.studentno);
		edittext3 = findViewById(R.id.edittext3);
		lastofid = findViewById(R.id.lastofid);
		number = findViewById(R.id.number);
		pg6_error = findViewById(R.id.pg6_error);
		pg4_back = findViewById(R.id.pg4_back);
		pg4_next = findViewById(R.id.pg4_next);
		linear49 = findViewById(R.id.linear49);
		linear50 = findViewById(R.id.linear50);
		linear51 = findViewById(R.id.linear51);
		textview17 = findViewById(R.id.textview17);
		textview18 = findViewById(R.id.textview18);
		termandcon = findViewById(R.id.termandcon);
		termandcon.getSettings().setJavaScriptEnabled(true);
		termandcon.getSettings().setSupportZoom(true);
		checkbox1 = findViewById(R.id.checkbox1);
		linear70 = findViewById(R.id.linear70);
		button6 = findViewById(R.id.button6);
		button7 = findViewById(R.id.button7);
		linear54 = findViewById(R.id.linear54);
		linear55 = findViewById(R.id.linear55);
		linear56 = findViewById(R.id.linear56);
		textview19 = findViewById(R.id.textview19);
		textview20 = findViewById(R.id.textview20);
		imageview1 = findViewById(R.id.imageview1);
		captha = findViewById(R.id.captha);
		linear57 = findViewById(R.id.linear57);
		pg6_back = findViewById(R.id.pg6_back);
		register = findViewById(R.id.register);
		users = FirebaseAuth.getInstance();
		reg_dialog = new AlertDialog.Builder(this);
		dialog = new AlertDialog.Builder(this);
		
		faculty.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				USER = "FACULTY";
				StrongPW = "";
				PG = "2";
				_PAGING();
				employeeno.setVisibility(View.VISIBLE);
				id.setVisibility(View.GONE);
				stdntid.setVisibility(View.GONE);
				reg_txt.setText("FACULTY REGISTRATION");
				reg_txt.setVisibility(View.VISIBLE);
			}
		});
		
		student.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				USER = "STUDENT";
				PG = "2";
				StrongPW = "";
				_PAGING();
				stdntid.setVisibility(View.GONE);
				id.setVisibility(View.VISIBLE);
				employeeno.setVisibility(View.GONE);
				reg_txt.setText("STUDENT REGISTRATION");
				reg_txt.setVisibility(View.VISIBLE);
			}
		});
		
		pw_box.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		msteam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					
					msteam.setChecked(true);
					other.setChecked(false);
				} 
				else {
					msteam.setChecked(false);
					other.setChecked(true);
				}
			}
		});
		
		other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					
					other.setChecked(true);
					msteam.setChecked(false);
				} 
				else {
					other.setChecked(false);
					msteam.setChecked(true);
				}
			}
		});
		
		email.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		pw.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				_GradientDrawable(ps1, 6, 0, 0, "#ff17ff8f", "#ff17ff8f", false, false, 45);
				if (_charSeq.length() > 7) {
					edittext_s.setText("0");
					_calculatePassword();
					if (total > 0) {
						_pakpassword();
					}
					else {
						edittext_s.setText("1");
						_calculatePassword();
						if (total > 0) {
							_pakpassword();
						}
						else {
							edittext_s.setText("2");
							_calculatePassword();
							if (total > 0) {
								_pakpassword();
							}
							else {
								edittext_s.setText("3");
								_calculatePassword();
								if (total > 0) {
									_pakpassword();
								}
								else {
									edittext_s.setText("4");
									_calculatePassword();
									if (total > 0) {
										_pakpassword();
									}
									else {
										edittext_s.setText("5");
										_calculatePassword();
										if (total > 0) {
											_pakpassword();
										}
										else {
											edittext_s.setText("6");
											_calculatePassword();
											if (total > 0) {
												_pakpassword();
											}
											else {
												edittext_s.setText("7");
												_calculatePassword();
												if (total > 0) {
													_pakpassword();
												}
												else {
													edittext_s.setText("8");
													_calculatePassword();
													if (total > 0) {
														_pakpassword();
													}
													else {
														edittext_s.setText("9");
														_calculatePassword();
														if (total > 0) {
															_pakpassword();
														}
														else {
															_GradientDrawable(ps2, 6, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				else {
					_GradientDrawable(ps1, 4, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
					_GradientDrawable(ps2, 4, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
					_GradientDrawable(ps3, 4, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
					_GradientDrawable(ps4, 4, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_HIDEPW();
			}
		});
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_HIDEPW();
			}
		});
		
		pg5_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PG = "1";
				_BACK();
			}
		});
		
		pg5_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (msteam.isChecked()) {
					
					if (!(email.getText().toString().contains("iskolarngbayan.pup.edu.ph") || email.getText().toString().contains("pup.edu.ph"))) {
						reg_dialog.setTitle("iSKAN AKO");
						reg_dialog.setIcon(R.drawable.unex_black_logo);
						reg_dialog.setMessage("Use your MS Team Iskolar email.\n\n[Example]\n\nFACULTY EMAIL:\nanonymous@pup.edu.ph\n\nSTUDENT EMAIL: anonymous@iskolarngbayan.pup.edu.ph");
						reg_dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								email.setText("");
								SketchwareUtil.hideKeyboard(getApplicationContext());
							}
						});
						reg_dialog.create().show();
					}
					else {
						if (email.getText().toString().equals("") || (pw.getText().toString().equals("") || cpw.getText().toString().equals(""))) {
							pg1_error.setVisibility(View.VISIBLE);
							pg1_error.setText("Enter your email or password first.");
						}
						else {
							if (StrongPW.equals("STRONG")) {
								if (!pw.getText().toString().equals(cpw.getText().toString())) {
									pg1_error.setVisibility(View.VISIBLE);
									pg1_error.setText("Your password comfirmation is incorrect.");
								}
								else {
									reg_dialog.setTitle("iSKAN AKO");
									reg_dialog.setIcon(R.drawable.unex_black_logo);
									reg_dialog.setMessage("Verify your account in just 24 hours or else it will be deleted.");
									reg_dialog.setCancelable(false);
									reg_dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											if (true) {
												SketchwareUtil.hideKeyboard(getApplicationContext());
												users.signInWithEmailAndPassword(email.getText().toString(), cpw.getText().toString()).addOnCompleteListener(RegisterActivity.this, _users_sign_in_listener);
											}
										}
									});
									reg_dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											
										}
									});
									reg_dialog.create().show();
								}
							}
							else {
								
							}
						}
					}
				} 
				if (other.isChecked()) {
					
					if (email.getText().toString().equals("") || (pw.getText().toString().equals("") || cpw.getText().toString().equals(""))) {
						pg1_error.setVisibility(View.VISIBLE);
						pg1_error.setText("Enter your email or password first.");
					}
					else {
						reg_dialog.setTitle("iSKAN AKO");
						reg_dialog.setIcon(R.drawable.unex_black_logo);
						reg_dialog.setMessage("After one year, you must change the email address you entered. You will be changing the email address provided by the PUP or the MS Team email address. \n\nExample:\n\nSTUDENT EMAIL: \nanonymous@iskolarngbayan.pup.edu.ph\n\nFACULTY EMAIL: \nanonymous@pup.edu.ph");
						reg_dialog.setCancelable(false);
						reg_dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								if (StrongPW.equals("STRONG")) {
									if (!pw.getText().toString().equals(cpw.getText().toString())) {
										pg1_error.setVisibility(View.VISIBLE);
										pg1_error.setText("Your password comfirmation is incorrect.");
									}
									else {
										reg_dialog.setTitle("iSKAN AKO");
										reg_dialog.setIcon(R.drawable.unex_black_logo);
										reg_dialog.setMessage("Verify your account in just 24 hours or else it will be deleted.");
										reg_dialog.setCancelable(false);
										reg_dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface _dialog, int _which) {
												if (true) {
													SketchwareUtil.hideKeyboard(getApplicationContext());
													users.signInWithEmailAndPassword(email.getText().toString(), cpw.getText().toString()).addOnCompleteListener(RegisterActivity.this, _users_sign_in_listener);
												}
											}
										});
										reg_dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface _dialog, int _which) {
												
											}
										});
										reg_dialog.create().show();
									}
								}
								else {
									
								}
							}
						});
						reg_dialog.create().show();
					}
				} 
			}
		});
		
		email_verify_txt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				VERIFICATION = "FIND";
				users.signInWithEmailAndPassword(email.getText().toString(), cpw.getText().toString()).addOnCompleteListener(RegisterActivity.this, _users_sign_in_listener);
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (users.getCurrentUser().isEmailVerified()){
					verify_txt.setText("VERIFIED");
				}
				else {
					verify_txt.setText("NOT VERIFIED");
				}
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PG = "2";
				_BACK();
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (verify_txt.getText().toString().equals("VERIFIED")) {
					PG = "4";
					_PAGING();
				}
				else {
					reg_dialog.setTitle("iSKAN AKO");
					reg_dialog.setIcon(R.drawable.unex_black_logo);
					reg_dialog.setMessage("You're account is not verified yet, check your email to verify your account. Thank you.");
					reg_dialog.setCancelable(false);
					reg_dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							if (true) {
								users.signInWithEmailAndPassword(email.getText().toString(), cpw.getText().toString()).addOnCompleteListener(RegisterActivity.this, _users_sign_in_listener);
							}
						}
					});
					reg_dialog.create().show();
				}
			}
		});
		
		fn.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					pg4_error.setText("");
				}
				else {
					
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		mi.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					pg4_error.setText("");
				}
				else {
					
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		ln.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					pg4_error.setText("");
				}
				else {
					
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		sf.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					pg4_error.setText("");
				}
				else {
					
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		pg2_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PG = "3";
				_BACK();
			}
		});
		
		pg2_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (fn.getText().toString().equals("") || ln.getText().toString().equals("")) {
					pg4_error.setText("Complete your name.");
				}
				else {
					PG = "5";
					_PAGING();
				}
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
		
		pg3_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PG = "4";
				_BACK();
			}
		});
		
		pg3_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (sex.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Complete your data.");
				}
				else {
					if (!(sex.getText().toString().equals("MALE") || sex.getText().toString().equals("FEMALE"))) {
						reg_dialog.setTitle("iSKAN AKO");
						reg_dialog.setIcon(R.drawable.unex_black_logo);
						reg_dialog.setMessage("Choose your sex first.");
						reg_dialog.setCancelable(false);
						reg_dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						reg_dialog.create().show();
					}
					else {
						PG = "6";
						_PAGING();
					}
				}
			}
		});
		
		employeeno.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
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
						N = userlist.size() - 1;
						L = userlist.size();
						for(int _repeat19 = 0; _repeat19 < (int)(L); _repeat19++) {
							if (userlist.get((int)N).get("C_ID").toString().equals(employeeno.getText().toString())) {
								employeeno.setText("");
								pg6_error.setText("This ID is used by other users. User you own ID.");
							}
							N--;
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
				if (_charSeq.length() > 5) {
					((EditText)employeeno).setError("Employee Number has 5 characters only.");
					pg6_error.setText("");
				}
				else {
					pg6_error.setText("");
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		//OnTouch
		id.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 stdntid.setVisibility(View.VISIBLE);
					id.setVisibility(View.GONE);
					year.requestFocus();
					year.setSelection(year.getText().length());
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		id.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 1) {
					year.setText(_charSeq);
					stdntid.setVisibility(View.VISIBLE);
					id.setVisibility(View.GONE);
					pg6_error.setText("");
					year.requestFocus();
					year.setSelection(year.getText().length());
				}
				else {
					
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		year.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		year.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 0) {
					stdntid.setVisibility(View.GONE);
					id.setVisibility(View.VISIBLE);
					id.requestFocus();
					id.setSelection(id.getText().length());
				}
				else {
					if (_charSeq.length() == 4) {
						studentno.requestFocus();
						studentno.setSelection(studentno.getText().length());
					}
					else {
						if ((Double.parseDouble(_charSeq) > 2015) && ((Double.parseDouble(new SimpleDateFormat("YYYY").format(date.getTime())) == Double.parseDouble(_charSeq)) || (Double.parseDouble(_charSeq) < Double.parseDouble(new SimpleDateFormat("YYYY").format(date.getTime()))))) {
							pg6_error.setText("");
						}
						else {
							pg6_error.setText("Use right year!");
						}
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		studentno.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 5) {
					lastofid.requestFocus();
					lastofid.setSelection(lastofid.getText().length());
				}
				else {
					
				}
				if (_charSeq.length() == 0) {
					year.requestFocus();
					year.setSelection(year.getText().length());
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		lastofid.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				STUDENTID = year.getText().toString().concat("-".concat(studentno.getText().toString().concat("-LQ-".concat(lastofid.getText().toString()))));
				student_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
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
						N = userlist.size() - 1;
						L = userlist.size();
						for(int _repeat22 = 0; _repeat22 < (int)(L); _repeat22++) {
							if (userlist.get((int)N).get("C_ID").toString().equals(STUDENTID)) {
								SketchwareUtil.showMessage(getApplicationContext(), "This ID is used by other users. User you own ID.");
								year.setText("");
								studentno.setText("");
								lastofid.setText("");
							}
							N--;
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
				if (_charSeq.length() == 0) {
					studentno.requestFocus();
					studentno.setSelection(studentno.getText().length());
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		pg4_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PG = "5";
				_BACK();
			}
		});
		
		pg4_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.hideKeyboard(getApplicationContext());
				if (USER.equals("STUDENT")) {
					if (STUDENTID.length() == 15) {
						if (STUDENTID.equals("") || number.getText().toString().equals("")) {
							pg6_error.setText("Complete your id ang contact info.");
						}
						else {
							if ((Double.parseDouble(year.getText().toString()) > 2015) && ((Double.parseDouble(new SimpleDateFormat("YYYY").format(date.getTime())) == Double.parseDouble(year.getText().toString())) || (Double.parseDouble(year.getText().toString()) < Double.parseDouble(new SimpleDateFormat("YYYY").format(date.getTime()))))) {
								PG = "7";
								_PAGING();
							}
							else {
								pg6_error.setText("We accept 2015-Current year only.");
							}
						}
					}
					else {
						pg6_error.setText("Enter your real student ID.");
					}
				}
				else {
					if (employeeno.getText().toString().length() == 5) {
						if (employeeno.getText().toString().equals("") || number.getText().toString().equals("")) {
							pg6_error.setText("Complete your id ang contact info.");
						}
						else {
							PG = "7";
							_PAGING();
						}
					}
					else {
						pg6_error.setText("Enter your real Employee Number.");
					}
				}
			}
		});
		
		termandcon.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					TERM = "TRUE";
					checkbox1.setChecked(true);
				}
				else {
					TERM = "FALSE";
					checkbox1.setChecked(false);
				}
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PG = "6";
				_BACK();
			}
		});
		
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!checkbox1.isChecked()) {
					SketchwareUtil.showMessage(getApplicationContext(), "You hasn't agree to our Terms and Conditions.");
				}
				else {
					PG = "8";
					_PAGING();
				}
			}
		});
		
		captha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					CHECK = "TRUE";
					captha.setChecked(true);
				}
				else {
					CHECK = "FALSE";
					captha.setChecked(false);
				}
			}
		});
		
		pg6_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PG = "7";
				_BACK();
			}
		});
		
		register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (USER.equals("STUDENT")) {
					if (!captha.isChecked()) {
						SketchwareUtil.showMessage(getApplicationContext(), "Confirm your captha.");
					}
					else {
						createaccount = new HashMap<>();
						createaccount.put("U_TYPE", USER);
						createaccount.put("A_MI", mi.getText().toString().toUpperCase());
						createaccount.put("A_FN", fn.getText().toString().toUpperCase());
						createaccount.put("A_LN", ln.getText().toString().toUpperCase());
						createaccount.put("A_SF", sf.getText().toString().toUpperCase());
						createaccount.put("B_SEX", sex.getText().toString());
						createaccount.put("C_ID", STUDENTID);
						createaccount.put("C_NO", number.getText().toString());
						createaccount.put("C_EMAIL", email.getText().toString());
						createaccount.put("C_ADDRESS", "NONE");
						createaccount.put("D_YEAR", "NONE");
						createaccount.put("D_SECTION", "NONE");
						createaccount.put("D_COURSE", "NONE");
						createaccount.put("E_URL", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcuBdDEbOeZKJhNr3YyrdO-HeHYLhBPyqecQ&usqp=CAU");
						createaccount.put("E_STATUS", "OFFLINE");
						createaccount.put("A_FULLNAME", ln.getText().toString().concat(" ".concat(fn.getText().toString().concat(" ".concat(mi.getText().toString().concat(" ".concat(sf.getText().toString())))))).toUpperCase());
						student_user_info.child(STUDENTID).updateChildren(createaccount);
						createaccount.clear();
						_RESETDATAINPUT();
						if (true) {
							reg_dialog.setTitle("iSKAN AKO");
							reg_dialog.setIcon(R.drawable.unex_black_logo);
							reg_dialog.setMessage("Success! You have account now.");
							reg_dialog.setCancelable(false);
							reg_dialog.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									PAGE.putExtra("TYPE", "STUDENT");
									PAGE.setClass(getApplicationContext(), LoginActivity.class);
									startActivity(PAGE);
									finish();
								}
							});
							reg_dialog.setNegativeButton("HOME", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									PAGE.setClass(getApplicationContext(), HomeActivity.class);
									startActivity(PAGE);
									finish();
								}
							});
							reg_dialog.create().show();
						}
					}
				}
				else {
					if (!captha.isChecked()) {
						SketchwareUtil.showMessage(getApplicationContext(), "Confirm your captha.");
					}
					else {
						createaccount = new HashMap<>();
						createaccount.put("U_TYPE", USER);
						createaccount.put("A_MI", mi.getText().toString().toUpperCase());
						createaccount.put("A_FN", fn.getText().toString().toUpperCase());
						createaccount.put("A_LN", ln.getText().toString().toUpperCase());
						createaccount.put("A_SF", sf.getText().toString().toUpperCase());
						createaccount.put("B_SEX", sex.getText().toString());
						createaccount.put("C_ID", employeeno.getText().toString());
						createaccount.put("C_NO", number.getText().toString());
						createaccount.put("C_EMAIL", email.getText().toString());
						createaccount.put("C_ADDRESS", "NONE");
						createaccount.put("E_URL", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcuBdDEbOeZKJhNr3YyrdO-HeHYLhBPyqecQ&usqp=CAU");
						createaccount.put("E_STATUS", "OFFLINE");
						createaccount.put("A_FULLNAME", ln.getText().toString().concat(" ".concat(fn.getText().toString().concat(" ".concat(mi.getText().toString().concat(" ".concat(sf.getText().toString())))))).toUpperCase());
						faculty_user_info.child(employeeno.getText().toString()).updateChildren(createaccount);
						createaccount.clear();
						_RESETDATAINPUT();
						if (true) {
							reg_dialog.setTitle("iSKAN AKO");
							reg_dialog.setIcon(R.drawable.unex_black_logo);
							reg_dialog.setMessage("Success! You have account now.");
							reg_dialog.setCancelable(false);
							reg_dialog.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									PAGE.putExtra("TYPE", "FACULTY");
									PAGE.setClass(getApplicationContext(), LoginActivity.class);
									startActivity(PAGE);
									finish();
								}
							});
							reg_dialog.setNegativeButton("HOME", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									PAGE.setClass(getApplicationContext(), HomeActivity.class);
									startActivity(PAGE);
									finish();
								}
							});
							reg_dialog.create().show();
						}
					}
				}
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
		
		users_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		users_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		users_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_users_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					users.signInWithEmailAndPassword(email.getText().toString(), cpw.getText().toString()).addOnCompleteListener(RegisterActivity.this, _users_sign_in_listener);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_users_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
						createaccount.clear();
						if (users.getCurrentUser().isEmailVerified()){
							if (VERIFICATION.equals("FIND")) {
								email_verify_txt.setText(email.getText().toString());
								verify_txt.setTextColor(0xFF8BC34A);
								verify_txt.setText("VERIFIED");
								PG = "3";
								_PAGING();
							}
							else {
								email_verify_txt.setText(email.getText().toString());
								PG = "4";
								_PAGING();
							}
						}
						else {
							if (VERIFICATION.equals("FIND")) {
								PG = "3";
								email_verify_txt.setText(email.getText().toString());
								verify_txt.setTextColor(0xFFF44336);
								verify_txt.setText("NOT VERIFIED");
								_PAGING();
							}
							else {
								users.getCurrentUser().sendEmailVerification() .addOnCompleteListener(new OnCompleteListener<Void>() { 
									@Override
									public void onComplete(Task<Void> task) { if (task.isSuccessful()){ showMessage("Verification link has been sent to your email!"); }else {
											
											showMessage("Verification link could not be sent to your email!");
										}}});
								email_verify_txt.setText(email.getText().toString());
								verify_txt.setTextColor(0xFFF44336);
								verify_txt.setText("NOT VERIFIED");
								PG = "3";
								_PAGING();
							}
						}
					}
				}
				else {
					users.createUserWithEmailAndPassword(email.getText().toString(), cpw.getText().toString()).addOnCompleteListener(RegisterActivity.this, _users_create_user_listener);
				}
			}
		};
		
		_users_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		date = Calendar.getInstance();
		PG = getIntent().getStringExtra("PG");
		sex.setEnabled(false);
		_DESIGN();
		_PAGING();
		_SPINROOM();
		_STIDENTIDEDITEXT();
		_onCreated();
		termandcon.loadUrl("file:///android_asset/HTML/termsandcondition.html");
	}
	
	@Override
	public void onBackPressed() {
		_BACK();
	}
	public void _FINDSTUDENTID() {
		if (USER.equals("STUDENT")) {
			student_user_info.addListenerForSingleValueEvent(new ValueEventListener() {
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
					N = userlist.size() - 1;
					L = userlist.size();
					for(int _repeat19 = 0; _repeat19 < (int)(L); _repeat19++) {
						if (userlist.get((int)N).get("C_ID").toString().equals(year.getText().toString())) {
							SketchwareUtil.showMessage(getApplicationContext(), "This ID is used by other users. User you own ID.");
						}
						N--;
					}
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}
		else {
			
		}
	}
	
	
	public void _RESETDATAINPUT() {
		fn.setText("");
		mi.setText("");
		ln.setText("");
		sf.setText("");
		mi.setText("");
		year.setText("");
		email.setText("");
		pw.setText("");
		cpw.setText("");
		number.setText("");
		USER = "";
		CHECK = "FALSE";
		TERM = "FALSE";
		captha.setChecked(false);
		checkbox1.setChecked(false);
		studentno.setText("");
		lastofid.setText("");
	}
	
	
	public void _PAGING() {
		if (PG.equals("1")) {
			pg1.setVisibility(View.VISIBLE);
			reg_txt.setVisibility(View.GONE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
		}
		if (PG.equals("2")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.VISIBLE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "1";
		}
		if (PG.equals("3")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.VISIBLE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "2";
		}
		if (PG.equals("4")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.VISIBLE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "3";
		}
		if (PG.equals("5")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.VISIBLE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "4";
		}
		if (PG.equals("6")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.VISIBLE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "5";
		}
		if (PG.equals("7")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.VISIBLE);
			pg8.setVisibility(View.GONE);
			PG = "6";
		}
		if (PG.equals("8")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.VISIBLE);
			PG = "7";
		}
	}
	
	
	public void _BACK() {
		if (PG.equals("1")) {
			if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
				if (users.getCurrentUser().isEmailVerified()){
					_HOME();
					FirebaseAuth.getInstance().signOut();
				}
				else {
					deleteAccount();
					_HOME();
				}
			}
			else {
				_HOME();
			}
		}
		if (PG.equals("2")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.GONE);
			pg2.setVisibility(View.VISIBLE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "1";
		}
		if (PG.equals("3")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.VISIBLE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "2";
		}
		if (PG.equals("4")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.VISIBLE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "3";
		}
		if (PG.equals("5")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.VISIBLE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "4";
		}
		if (PG.equals("6")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.VISIBLE);
			pg7.setVisibility(View.GONE);
			pg8.setVisibility(View.GONE);
			PG = "5";
		}
		if (PG.equals("7")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.VISIBLE);
			pg8.setVisibility(View.GONE);
			PG = "6";
		}
		if (PG.equals("8")) {
			pg1.setVisibility(View.GONE);
			reg_txt.setVisibility(View.VISIBLE);
			pg2.setVisibility(View.GONE);
			pg3.setVisibility(View.GONE);
			pg4.setVisibility(View.GONE);
			pg5.setVisibility(View.GONE);
			pg6.setVisibility(View.GONE);
			pg7.setVisibility(View.VISIBLE);
			pg8.setVisibility(View.GONE);
			PG = "7";
		}
	}
	
	
	public void _DESIGN() {
		android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
		
		a.setColor(Color.parseColor("#FFFFFF"));
		a.setCornerRadius(10);
		
		pg1.setElevation(16);
		pg1.setBackground(a);
		
		android.graphics.drawable.GradientDrawable b = new android.graphics.drawable.GradientDrawable();
		
		b.setColor(Color.parseColor("#FFFFFF"));
		b.setCornerRadius(10);
		
		pg2.setElevation(16);
		pg2.setBackground(b);
		
		
		
		android.graphics.drawable.GradientDrawable c = new android.graphics.drawable.GradientDrawable();
		
		c.setColor(Color.parseColor("#FFFFFF"));
		c.setCornerRadius(10);
		
		pg3.setElevation(16);
		pg3.setBackground(c);
		
		
		android.graphics.drawable.GradientDrawable f = new android.graphics.drawable.GradientDrawable();
		
		f.setColor(Color.parseColor("#FFFFFF"));
		f.setCornerRadius(10);
		
		pg4.setElevation(16);
		pg4.setBackground(f);
		
		android.graphics.drawable.GradientDrawable d = new android.graphics.drawable.GradientDrawable();
		
		d.setColor(Color.parseColor("#FFFFFF"));
		d.setCornerRadius(10);
		
		pg5.setElevation(16);
		pg5.setBackground(d);
		
		
		
		android.graphics.drawable.GradientDrawable e = new android.graphics.drawable.GradientDrawable();
		
		e.setColor(Color.parseColor("#FFFFFF"));
		e.setCornerRadius(10);
		
		pg6.setElevation(16);
		pg6.setBackground(e);
		
		android.graphics.drawable.GradientDrawable h = new android.graphics.drawable.GradientDrawable();
		
		h.setColor(Color.parseColor("#FFFFFF"));
		h.setCornerRadius(10);
		
		pg7.setElevation(16);
		pg7.setBackground(h);
		
		android.graphics.drawable.GradientDrawable g = new android.graphics.drawable.GradientDrawable();
		
		g.setColor(Color.parseColor("#FFFFFF"));
		g.setCornerRadius(10);
		
		pg8.setElevation(16);
		pg8.setBackground(g);
		
		captha.setChecked(false);
		checkbox1.setChecked(false);
		CHECK = "FALSE";
		TERM = "FALSE";
	}
	
	
	public void _SPINROOM() {
		sexstr.add("MALE");
		sexstr.add("FEMALE");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, sexstr));
		((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _STIDENTIDEDITEXT() {
		MaxLength = 4;
		MaxLength2 = 5;
		MaxLength3 = 11;
		MaxLength4 = 1;
		year.setFilters (new InputFilter[] { new InputFilter.LengthFilter((int) MaxLength)} );
		
		studentno.setFilters (new InputFilter[] { new InputFilter.LengthFilter((int) MaxLength2)} );
		
		employeeno.setFilters (new InputFilter[] { new InputFilter.LengthFilter((int) MaxLength2)} );
		
		number.setFilters (new InputFilter[] { new InputFilter.LengthFilter((int) MaxLength3)} );
		
		id.setFilters (new InputFilter[] { new InputFilter.LengthFilter((int) MaxLength4)} );
		
		lastofid.setFilters (new InputFilter[] { new InputFilter.LengthFilter((int) MaxLength4)} );
		
	}
	
	
	public void _extra2() {
	}
	
	private void deleteAccount() {
		
		final com.google.firebase.auth.FirebaseUser CurrentUser = users.getCurrentUser();
		
		CurrentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() { 
			
			@Override public void onComplete(Task<Void> task) { 
				
				if (task.isSuccessful()) {
					
					SketchwareUtil.showMessage(getApplicationContext(), "HAVE A NICE DAY!");
					
				}
				else {
					
					SketchwareUtil.showMessage(getApplicationContext(), "GOD BLESS!");
					
				}}});
	}
	
	
	public void _HOME() {
		PAGE.putExtra("USER", "NORMAL");
		PAGE.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(PAGE);
		finish();
	}
	
	
	public void _pakpassword() {
		pas2 = true;
		_GradientDrawable(ps2, 6, 0, 0, "#ff17ff8f", "#ff17ff8f", false, false, 45);
		if (true) {
			if (pw.getText().toString().contains("-")) {
				
				edittext_s.setText("-");
			} 
			if (pw.getText().toString().contains("_")) {
				
				edittext_s.setText("_");
			} 
			if (pw.getText().toString().contains("$")) {
				
				edittext_s.setText("$");
			} 
			if (pw.getText().toString().contains("&")) {
				
				edittext_s.setText("&");
			} 
			if (pw.getText().toString().contains("?")) {
				
				edittext_s.setText("?");
			} 
			if (pw.getText().toString().contains("!")) {
				
				edittext_s.setText("!");
			} 
			if (pw.getText().toString().contains("?")) {
				
				edittext_s.setText("?");
			} 
			if (pw.getText().toString().contains("*")) {
				
				edittext_s.setText("*");
			} 
			_calculatePassword();
		}
		if (total > 0) {
			_GradientDrawable(ps3, 6, 0, 0, "#ff17ff8f", "#ff17ff8f", false, false, 45);
			_hvezda();
		}
		else {
			edittext_s.setText(txt_zv.getText().toString());
			_calculatePassword();
			if (total > 0) {
				_GradientDrawable(ps3, 6, 0, 0, "#ff17ff8f", "#ff17ff8f", false, false, 45);
				_hvezda();
			}
			else {
				_GradientDrawable(ps3, 6, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
				_GradientDrawable(ps4, 6, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
			}
		}
	}
	
	
	public void _calculatePassword() {
		text = pw.getText().toString();
		Spannable spannable1 = new SpannableString(text);
		android.text.style.ForegroundColorSpan fgSpan = new android.text.style.ForegroundColorSpan(Color.parseColor("#ffffff"));
		android.text.style.BackgroundColorSpan bgSpan = new android.text.style.BackgroundColorSpan(Color.parseColor("#4c91df"));
		len = edittext_s.getText().toString().length();
		y = 0;
		total = 0;
		if (text.contains(edittext_s.getText().toString()) && (len > 0)) {
			for(int _repeat25 = 0; _repeat25 < (int)(((text.length() - len) + 1)); _repeat25++) {
				if (text.substring((int)(y), (int)(y + len)).equals(edittext_s.getText().toString())) {
					y++;
					total++;
				}
				else {
					y++;
				}
			}
			int x = 0;
			for(int _repeat44 = 0; _repeat44 < (int)(total); _repeat44++) {
				int n = text.indexOf(edittext_s.getText().toString(), x);
				x = n+1;
				spannable1.setSpan(android.text.style.CharacterStyle.wrap(fgSpan), n, n + edittext_s.getText().length(), 0);
				spannable1.setSpan(android.text.style.CharacterStyle.wrap(bgSpan), n, n + edittext_s.getText().length(), 0);
			}
		}
		cpw.setText(spannable1);
	}
	
	
	public void _hvezda() {
		if (true) {
			if (pw.getText().toString().contains("-")) {
				
				edittext_s.setText("-");
			} 
			if (pw.getText().toString().contains("_")) {
				
				edittext_s.setText("_");
			} 
			if (pw.getText().toString().contains("$")) {
				
				edittext_s.setText("$");
			} 
			if (pw.getText().toString().contains("&")) {
				
				edittext_s.setText("&");
			} 
			if (pw.getText().toString().contains("?")) {
				
				edittext_s.setText("?");
			} 
			if (pw.getText().toString().contains("!")) {
				
				edittext_s.setText("!");
			} 
			if (pw.getText().toString().contains("?")) {
				
				edittext_s.setText("?");
			} 
			if (pw.getText().toString().contains("*")) {
				
				edittext_s.setText("*");
			} 
			if (pw.getText().toString().contains("#")) {
				
				edittext_s.setText("#");
			} 
			_calculatePassword();
		}
		if (total > 0) {
			_GradientDrawable(ps4, 4, 0, 0, "#ff17ff8f", "#ff17ff8f", false, false, 45);
			pg1_error.setTextColor(0xFF757575);
			pg1_error.setText("Your password is very strong, ideal for use.");
			StrongPW = "STRONG";
		}
		else {
			_GradientDrawable(ps4, 4, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
			pg1_error.setTextColor(0xFF757575);
			pg1_error.setText("Use 8 or more characters with a mix of letters, numbers & symbols.");
			StrongPW = "";
		}
	}
	
	
	public void _GradientDrawable(final View _view, final double _radius, final double _stroke, final double _shadow, final String _color, final String _borderColor, final boolean _ripple, final boolean _clickAnim, final double _animDuration) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			_view.setBackground(gd);
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
		}
		if (_clickAnim) {
			_view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()){
						case MotionEvent.ACTION_DOWN:{
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues(0.9f);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues(0.9f);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							break;
						}
						case MotionEvent.ACTION_UP:{
							
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues((float)1);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues((float)1);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							
							break;
						}
					}
					return false;
				}
			});
		}
	}
	
	
	public void _onCreated() {
		_GradientDrawable(ps1, 6, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
		_GradientDrawable(ps2, 6, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
		_GradientDrawable(ps3, 6, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
		_GradientDrawable(ps4, 6, 0, 0, "#EEEEEE", "#ff17ff8f", false, false, 45);
		_GradientDrawable(eml_box, 22, 0, 0, "#F3F6F8", "#f4fafc", false, false, 0);
		_GradientDrawable(cpw_box, 22, 0, 0, "#F3F6F8", "#f4fafc", false, false, 0);
		_GradientDrawable(pw_box, 22, 0, 0, "#F3F6F8", "#f4fafc", false, false, 0);
		_GradientDrawable(fn_box, 22, 0, 0, "#F3F6F8", "#f4fafc", false, false, 0);
		_GradientDrawable(mi_box, 22, 0, 0, "#F3F6F8", "#f4fafc", false, false, 0);
		_GradientDrawable(ln_box, 22, 0, 0, "#F3F6F8", "#f4fafc", false, false, 0);
		_GradientDrawable(sf_box, 22, 0, 0, "#F3F6F8", "#f4fafc", false, false, 0);
		email.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		pw.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		cpw.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		fn.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		mi.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		ln.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		sf.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		pw.setFilters(new InputFilter[] { new InputFilter.LengthFilter((int) 32) });
		truee = true;
	}
	
	
	public void _HIDEPW() {
		if (truee) {
			truee = false;
			imageview6.setImageResource(R.drawable.ic_visibility_grey);
			imageview9.setImageResource(R.drawable.ic_visibility_grey);
			pw.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
			cpw.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
		}
		else {
			truee = true;
			pw.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
			cpw.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
			imageview6.setImageResource(R.drawable.ic_visibility_off_grey);
			imageview9.setImageResource(R.drawable.ic_visibility_off_grey);
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