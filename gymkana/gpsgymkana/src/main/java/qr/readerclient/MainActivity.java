
package qr.readerclient;
 
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.*;
import android.widget.*;


import android.widget.Button;
// code based on the original from http://karanbalkar.com/2013/12/tutorial-65-implement-barcode-scanner-using-zxing-in-android/ 
public class MainActivity extends Activity {
 
    private Button scan;
    private TextView text;    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        scan= (Button)findViewById(R.id.btnScan);
	text= (TextView) findViewById(R.id.text);
         
        scan.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub  
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
    }
 
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
           if (requestCode == 0) {
              if (resultCode == RESULT_OK) {
                  
                 String contents = intent.getStringExtra("SCAN_RESULT");
                 String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
		 Log.i("App","Scan successful:"+format+" obtained "+contents);
		 text.setText(contents);
		View vg = this.findViewById(android.R.id.content);
		vg.invalidate();
              
                 // Handle successful scan
                                           
              } else if (resultCode == RESULT_CANCELED) {
                 // Handle cancel
                 Log.i("App","Scan unsuccessful");
              }
         }
      }
     
   
}
