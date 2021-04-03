package windowsGui;

import processing.core.PApplet;

class tooltip{
	  BMScontrols Bms;
	  PApplet applet;
	  public float x,y,w,h,transparency;
	//int 
	  public String label;
	  public boolean toggle;
	  tooltip(){
	        
	  };

	  tooltip(float x,float y,float w,float h,String label){
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	    this.label = label;

	  };

	  tooltip(float x,float y,float w,float h){
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;

	  };

	  public void draw(){

	    if(pos()){
	        applet.fill(219, 212, 11,100);
	        applet.rect(x,y,w,h);
	        applet.fill(0);
	        applet.text(label,x+10,y+10);

	    }
	  };

	  boolean pos(){
	    return applet.mouseX > x && applet.mouseX < x + w && applet.mouseY > y && applet.mouseY < y + h;

	  };

	};
