package usp.neura;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class QRPrompt extends Activity {
    private static final int CAMERA_REQUEST = 1888; 
    private ImageView imageView;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.imageView = (ImageView)this.findViewById(R.id.imageView_imagem);
        Button photoButton = (Button) this.findViewById(R.id.button_tirarFoto);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                //startActivityForResult(cameraIntent, CAMERA_REQUEST);
                
            	
            	//  imageView.setImageDrawable(LoadImageFromWebOperations(QRurl("Rodolfo Lindo")));
            	Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(QRurl("Rodolfo Lindo")));
            	startActivity(browserIntent);
            
            }
        });
        
        Button sendQRButton = (Button)this.findViewById(R.id.button_enviar);
        sendQRButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		
        	}
        });    
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
            imageView.setImageBitmap(photo);
        }  
    }
    
    String QRurl(String msg) {
    	Display display = getWindowManager().getDefaultDisplay();
    	Point size = new Point();
    	display.getSize(size);
    	int width = size.x;
    	
    	if (width > 400)
    		width = 400;
    	
    	return "http://chart.apis.google.com/chart?cht=qr&chs=" + width + "x" + width + "&chl=" + msg + "&chld=H|0";
    }
}