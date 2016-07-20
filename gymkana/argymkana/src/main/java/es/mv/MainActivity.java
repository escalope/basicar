
package es.mv;

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


	private TextView text;    


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text= (TextView) findViewById(R.id.text);
		Button scan1= (Button)findViewById(R.id.btnScan1);         
		scan1.setOnClickListener(new View.OnClickListener() {            
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub  
				Intent intent = new Intent("es.mv.CAPTURE");
				intent.putExtra("CAPTURE_MODE", "CUBE");
				startActivityForResult(intent, 0);
			}
		});

		Button scan2= (Button)findViewById(R.id.btnScan2);         
		scan2.setOnClickListener(new View.OnClickListener() {            
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub  
				Intent intent = new Intent("es.mv.CAPTUREWALKING");
				intent.putExtra("CAPTURE_MODE", "UFO");
				startActivityForResult(intent, 0);
			}
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {                 
				String contents = intent.getStringExtra("MESSAGE");                
				text.setText(contents+":"+intent.getStringExtra("CAPTUREDID"));                
				View vg = this.findViewById(android.R.id.content);
				vg.invalidate();              
				// Handle successful capture

			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel              
				String contents = intent.getStringExtra("MESSAGE");                
				text.setText(contents);
				View vg = this.findViewById(android.R.id.content);
				vg.invalidate();
			}
		}
	}


}
