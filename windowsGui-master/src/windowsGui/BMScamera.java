package windowsGui;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.opengl.PShader;

public class BMScamera{
	
	  public BMScontrols Bms;
	  public tab settings;
	  public PApplet applet;
	  public PGraphics canvas;
	  public PShader shader;
	  public boolean flash;
	  float mult = 0.0f,counter = 0.0f;
	  
	  public BMScamera(BMScontrols bms){
			Bms = bms;
			applet = bms.applet;
		    canvas = bms.applet.createGraphics((int) (applet.width), (int)(applet.height));
		    settings = new tab(0,200,200,400,"Settings",bms);
		    //settings.toggle = true;
		    settings.visible = true;
		    settings.draggable = true;
		    settings.scrollable = true;
		    settings.vscroll = true;
		    
		    String []s = {"Save","Load","Flash"};
		    Menu menu = new Menu(0,40,60,s,bms);
		    menu.setToggleBar();
		    settings.add(menu);
		    
		    String []s2 = {"Counter","Mult"};
		    sliderBox sl1 = new sliderBox(20,120,90,90,10,s2,bms);
		    sl1.menu.draggable = false;
		    sl1.tooltip = null;
		    sl1.setFloat(0,0,7);
		    sl1.setFloat(1,1,20);
		    sl1.setPieSquare();
		    settings.add(sl1);
		    settings.setRadius(10);
		    settings.setAlignment("center");
		    //Bms.add(settings);
		    Bms.dock.add(settings);
		  };
	  
	  public BMScamera(BMScontrols bms,int w,int h){
			Bms = bms;
			applet = bms.applet;
		    canvas = bms.applet.createGraphics((int) (w), (int)(h),PConstants.P2D);
		    settings = new tab(0,200,200,400,"Settings",bms);
		    settings.visible = true;
		    settings.draggable = true;
		    settings.scrollable = true;
		    settings.vscroll = true;

		    String []s = {"Save","Load","Flash"};
		    Menu menu = new Menu(0,40,60,s,bms);
		    menu.setToggleBar();
		    settings.add(menu);
		    
		    String []s2 = {"Counter","Mult"};
		    sliderBox sl1 = new sliderBox(20,120,90,90,10,s2,bms);
		    sl1.menu.draggable = false;
		    sl1.tooltip = null;
		    sl1.setFloat(0,0,7);
		    sl1.setFloat(1,1,20);
		    sl1.setPieSquare();
		    settings.add(sl1);
		    settings.setRadius(10);
		    settings.setAlignment("center");
		    bms.camera = this;
		    //Bms.add(settings);
		    Bms.dock.add(settings);
		  };
	  
	  public BMScamera(int w,int h,BMScontrols bms){
			Bms = bms;
			applet = bms.applet;
		    canvas = bms.applet.createGraphics((int) (w), (int)(h));
		    bms.camera = this;
		    settings = new tab(0,200,200,400,"Settings",bms);
		    settings.toggle = true;
		    settings.visible = true;
		    settings.draggable = true;
		    settings.scrollable = true;
		    settings.vscroll = true;

		    String []s = {"Save","Load","Flash"};
		    Menu menu = new Menu(0,20,60,20,5,s,bms);
		    menu.setToggleBar();
		    settings.add(menu);
		    
		    String []s2 = {"Counter","Mult"};
		    sliderBox sl1 = new sliderBox(20,120,90,90,10,s2,bms);
		    sl1.menu.draggable = false;
		    sl1.tooltip = null;
		    sl1.setFloat(0,0,7);
		    sl1.setFloat(1,1,20);
		    settings.add(sl1);
		    settings.setRadius(10);
		    settings.setAlignment("center");
		    //Bms.add(settings);
		    Bms.dock.add(settings);
		  };
		  
	  BMScamera(PApplet applet,int w,int h,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
	    settings = new tab(0,200,200,400,"Settings",bms);
	    settings.toggle = true;
	    settings.visible = true;
	    settings.draggable = true;
	    settings.scrollable = true;
	    settings.vscroll = true;
	    
	    String []s = {"Save","Load","Flash"};
	    Menu menu = new Menu(0,40,60,s,bms);
	    menu.setToggleBar();
	    settings.add(menu);
	    
	    String []s2 = {"Counter","Mult"};
	    sliderBox sl1 = new sliderBox(20,120,90,90,10,s2,bms);
	    sl1.menu.draggable = false;
	    sl1.tooltip = null;
	    sl1.setPieSquare();
	    sl1.setFloat(0,0,7);
	    sl1.setFloat(1,1,20);
	    sl1.setPieSquare();
	    settings.add(sl1);
	    settings.setRadius(10);
	    settings.setAlignment("center");
	    bms.camera = this;
	    //Bms.add(settings);
	    Bms.dock.add(settings);
	  };

	  public void displayCam(){
	      canvas.beginDraw();
//	      if (applet.frameCount>10&&!cam.isStarted()){cam.start();}
	      if(shader!=null) {
		      mult = settings.getValue(0,1);
		      counter = PApplet.floor(settings.getValue(0,0));
		      shader.set("mult",mult);
		      shader.set("type",counter);
	      }
	      canvas.shader(shader);
	      canvas.fill(0);
	      canvas.rect(0,0,applet.width,applet.height);
	      canvas.imageMode(PConstants.CENTER);
//	      canvas.image(cam, applet.width/2, applet.height/2+20);
	      canvas.imageMode(PConstants.CORNER);
	      canvas.fill(0);
	      canvas.rect(0,0,applet.width,20);
	      canvas.endDraw();
	      applet.image(canvas,0,0);
	      settings.displayTab();
	  };

	  public void camLogic(){
	      if(settings.getToggle(0,2))flash = false;
	      else flash = true;

//	      if (!flash) {
//	          if (cam.isFlashEnabled())cam.disableFlash();
//          }else cam.enableFlash();
	      
	    settings.sliderv.set(0,100);
	  };
	  
	  public void setBms(BMScontrols bms) {
		  
		  Bms = bms;
		  applet = bms.applet;
		  settings.Bms = bms; 
		  settings.sliderv.setBms(bms);
	  };
	  
	  public void setShader() {
		  
		  shader = applet.loadShader("edges.glsl");
	  };
	  
	  public void read() {
//		  cam.read();
		  
	  };
	  
	  public void setShader(PShader s) {
		  shader = s;
	  };
};
