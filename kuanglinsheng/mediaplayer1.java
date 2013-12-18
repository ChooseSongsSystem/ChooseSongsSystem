
import java.awt.Component;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.swing.JFrame;


class mediaplayer1 extends JFrame implements ControllerListener{
	String mediaFile;
	Component comp1,comp2;
	Player player;
	mediaplayer1(String str){
		
		super("多媒体播放器");
		String name=str+".mpg";
		this.setVisible(true);
		this.setBounds(300,100,400,350);
		mediaFile="file:D:\\workspace\\ShuJuKU\\"+name;
		this.validate();
	/*	this.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
		play();
	}
	
	
	public void close(){
		player.stop();
		this.dispose();
	}
	void play() {
		// TODO 自动生成的方法存根
		String str=new String(mediaFile);
		try{
			MediaLocator mrl=new MediaLocator(str);
			player=Manager.createPlayer(mrl);
			player.addControllerListener(this);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(player!=null){
			player.prefetch();
		}
	}
	@Override
	public void controllerUpdate(ControllerEvent event) {
		// TODO 自动生成的方法存根
		if(event instanceof RealizeCompleteEvent){
			if((comp1=player.getVisualComponent())!=null){
				add("Center",comp1);
			}
			if((comp2=player.getControlPanelComponent())!=null){
				add("South",comp2);
			}
			validate();
		}
		else if(event instanceof PrefetchCompleteEvent){
			player.start();
		}
		
	}
	public static void main(String args[]){
		new mediaplayer1("海阔天空");
	}
}
