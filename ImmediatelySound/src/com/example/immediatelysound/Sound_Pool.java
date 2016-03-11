package com.example.immediatelysound;

import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sound_Pool extends Activity {
	SoundPool sp;
	HashMap<Integer,Integer> hm;
	int currStreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initSoundPool();
        
     //   Ev1=(EditText)findViewByld(R.id.editText1);
       
        Button b1=(Button) this.findViewByld(R.id.Button01);   // 获取播放按钮
        b1.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		playSound(1,0);
        		Toast.makeText(getBaseContext(), "播放即时音效", Toast.LENGTH_SHORT).show();        
        		}
        });
        
        Button b2=(Button)this.findViewById(R.id.Button02);
        b2.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		sp.stop(currStreamId);
        		Toast.makeText(getBaseContext(),"停止播放即时音效",Toast.LENGTH_SHORT).show();
        		
        	}});
	
			}

 public void playSound(int sound,int loop){
	 AudioManager am=(AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
	 float streamVolumeCurrent=am.getStreamVolume(AudioManager.STREAM_MUSIC);
	 float streamVolumeMax=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	 float volume=streamVolumeCurrent/streamVolumeMax;
	 currStreamId=sp.play(hm.get(sound), volume, volume, 1, loop, 1.0f);

 }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
  /*  
    public class Sound_Pool extends Activity{
    	Soundpool sp;
    	Hashmap<Integer,Integer> hm;
    	int currStreamId;
    	
    }
    */
    
    public void initSoundPool(){
    	sp=new SoundPool(4,AudioManager.STREAM_MUSIC,0);
    	hm=new HashMap<Integer,Integer>();
    	hm.put(1,sp.load(this,R.raw.musictest,1));
    	
    	
    }
    
}
