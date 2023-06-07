package orig.kganofficial.iskanakoapp;


    import android.content.ActivityNotFoundException; 
    import android.content.Context; 
    import android.content.Intent; 
    import android.net.Uri;
     import android.os.Build;
     import androidx.core.content.FileProvider; 
     import android.util.Log; import java.io.File; 
     
     public class ApkInstaller{ 
     public static void installApplication(Context context, String path) { 
     Intent intent = new Intent(Intent.ACTION_VIEW); 
     intent.setDataAndType(uriFromFile(context, new File(path)), "application/vnd.android.package-archive");
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); 
       try { context.startActivity(intent); } 
       catch (ActivityNotFoundException e)
        { e.printStackTrace(); Log.e("TAG", "Error in opening the file!"); } } 
        private static Uri uriFromFile(Context context, File file)
         { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
          { return FileProvider.getUriForFile(context,context.getApplicationContext().getPackageName() + ".provider", file); } 
          else { return Uri.fromFile(file); } } }
        
