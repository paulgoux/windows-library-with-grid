package windowsGui;

import processing.core.PApplet;

class Point{
	  BMScontrols Bms;
	  PApplet applet;
	  float x,y;
	  int id;
	  
	  public Point(float X, float Y,int Id,BMScontrols bms){
		Bms = bms;
	    this.applet = bms.applet;
	    x = X;
	    y = Y;
	    id = Id;
	  };
	  
	  public void draw(){
	    
		applet.strokeWeight(8);
		applet.stroke(0);
		applet.point(x,y);
	    //float t1 = atan2(y-H/2,x-W/2);
	    //float t1 = atan2(y-Boundary.midpoint.y,x-midpoint.x);
	    
	  };
};
