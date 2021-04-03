package windowsGui;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Menu {
	  PApplet applet;
	  BMScontrols Bms;
	  public int id, item, t, toggle, toggle2, type = 0, index =-1, subindex = -1, t2, sindex=-1, counter, slcount,
			  nindex = -1,rows,cols;
	  public float x, y, bx, by, w, h, xoff, yoff, window = 0, htotal, Yoff, xpos, ypos, tsize = 12, bsize,
			  spacing, twidth,r1,r2,r3,r4,transparency,ghSpacing,gvSpacing;
	  public PVector mouse,mousePos;
	  public String label, blabel,itemLabel;
	  float dx,dy;
	  public boolean drag = false, resize = false, slide = false, border = false, menu, menuhover = false, 
			  highlightable = true, animate, vertical, horizontal = false, dmenu,toggleBox,radio,toggleBar,classicBar,
			  classicSquare,classicRadio,pieSquare,plain,debug;
	  public boolean mdown, mup, smdown, smup, listbox, open_menu, sltoggle, free,parentRight,draggable,parentCanvas,
	  		  m2down,m3down,getIndex,m4down,m5down,m6down,subMenuPos;
	  public boolean visible = true,show = true,localTheme,grid;
	  public ArrayList<Button> items = new ArrayList<Button>();
	  public ArrayList<Slider> sliders = new ArrayList<Slider>();
	  public ArrayList<Dropdown> dMenus = new ArrayList<Dropdown>();
	  ArrayList<Boolean> child = new ArrayList<Boolean>();
	  public ArrayList<String> labels = new ArrayList<String>();
	  HashMap<String, Boolean> values ;
	  Window mWindow = null;
	  public Button parent;
	  tab tab;
	  PGraphics localCanvas;
	  public tab parentTab;
	  //Menu dmenu;
	  Dropdown dMenu;

	  public Menu link;
	  //color col = applet.color(0,150), bcol, tcol = applet.color(255), fcol = applet.color(255, 80), hcol = applet.color(255, 50),toggleCol = applet.color(50,0);
	  public int col, bcol, tcol, fcol, hcol,toggleCol;

	  public Menu() {
		  
	  };

	  public Menu(float xx, float yy, float ww, float hh, String Label) {
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = hh;
	    label = Label;
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	  };
	  
	  public Menu(float xx, float yy, float ww, float hh, String Label,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    x = xx;
		    y = yy;
		    bx = x;
		    by = y;
		    w = ww;
		    h = hh;
		    label = Label;
		    xoff = 0;
		    yoff = 0;
		    toggle =0;
		    bcol = col;
		    bsize = tsize;
		  };

	  public Menu(float xx, float yy, float ww, float hh) {
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = hh;
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	  };
	  
	  public Menu(float xx, float yy, float ww, float hh,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    x = xx;
		    y = yy;
		    bx = x;
		    by = y;
		    w = ww;
		    h = hh;
		    xoff = 0;
		    yoff = 0;
		    toggle =0;
		    bcol = col;
		    bsize = tsize;
		  };

	  

	  public Menu(float xx, float yy, float ww, float hh, String Label, String [] list) {

	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = hh;
	    xoff = 0;
	    yoff = 0;
	    label = Label;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	    slide = true;
	    float bwidth = 0;

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+hh>bwidth)bwidth = applet.textWidth(l)+20;
	      Button a = new Button( x, y + hh *(i+1), bwidth, h, l);
	      a.border = false;
	      a.parent = this;
	      items.add(a);
	    }
	    twidth = bwidth*items.size();
	  };
	  
	  public Menu(float xx, float yy, float ww, float hh, String Label, String [] list,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    x = xx;
		    y = yy;
		    bx = x;
		    by = y;
		    w = ww;
		    h = hh;
		    xoff = 0;
		    yoff = 0;
		    label = Label;
		    toggle =0;
		    bcol = col;
		    bsize = tsize;
		    slide = true;
		    float bwidth = 0;

		    for (int i =0; i<list.length; i++) {
		      String l = list[i];

		      if (applet.textWidth(l)+hh>bwidth)bwidth = applet.textWidth(l)+20;
		      Button a = new Button( x, y + hh *(i+1), bwidth, h, l,bms);
		      a.border = false;
		      a.parent = this;
		      items.add(a);
		    }
		    twidth = bwidth*items.size();
		  };

	  public Menu(float xx, float yy, float ww, float hh, String Label, String [] list, int n) {
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = hh;
	    xoff = 0;
	    yoff = 0;
	    label = Label;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	    slide = true;
	    float bwidth = 0;
	    spacing = 0;
	    //println(y)
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	    }
	    
	    if(w>bwidth)bwidth = w;

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      Button a = new Button( x, y + hh *(i+1), bwidth, hh, l);
	      a.border = false;
	      a.parent = this;
	      items.add(a);
	    }
	    //h = hh * items.size();
	    twidth = bwidth*items.size();
	  };
	  
	  public Menu(float xx, float yy, float ww, float hh, String Label, String [] list, int n,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    x = xx;
		    y = yy;
		    bx = x;
		    by = y;
		    w = ww;
		    h = hh;
		    xoff = 0;
		    yoff = 0;
		    label = Label;
		    toggle =0;
		    bcol = col;
		    bsize = tsize;
		    slide = true;
		    float bwidth = 0;
		    spacing = 0;
		    //println(y)
		    for (int i =0; i<list.length; i++) {
		      String l = list[i];

		      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		    }
		    
		    if(w>bwidth)bwidth = w;

		    for (int i =0; i<list.length; i++) {
		      String l = list[i];

		      Button a = new Button( x, y + hh *(i+1), bwidth, hh, l,bms);
		      a.border = false;
		      a.parent = this;
		      items.add(a);
		    }
		    //h = hh * items.size();
		    twidth = bwidth*items.size();
	  };

	  public Menu(float xx, float yy, float ww, String [] list) {
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;

	    h = list.length*20;
	    xoff = 0;
	    yoff = 0;
	    toggle = 0;
	    bcol = col;
	    bsize = tsize;
	    //slide = true;

	    float bwidth = 0;

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	    }

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];
	      Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l);
	      a.border = false;
	      a.parent = this;
	      items.add(a);
	    }

	    twidth = bwidth*items.size();
	    w = bwidth;
	  };
	  
	  public Menu(float xx, float yy, float ww,float hh,float ss, String [] list,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    x = xx;
		    y = yy;
		    bx = x;
		    by = y;
		    w = ww;
		    spacing = ss;
		    h = list.length*(hh+ss);
		    xoff = 0;
		    yoff = 0;
		    toggle = 1;
		    bcol = col;
		    bsize = tsize;
		    //slide = true;

		    float bwidth = 0;

		    for (int i =0; i<list.length; i++) {
		      String l = list[i];

		      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		    }

		    for (int i =0; i<list.length; i++) {
		      String l = list[i];
		      Button a = new Button( x, y + (hh+ss) *(i+1), bwidth, hh, l,bms);
		      a.border = false;
		      a.parent = this;
		      items.add(a);
		    }

		    twidth = bwidth*items.size();
		    w = bwidth;
	  };

	  public Menu(float xx, float yy, float ww, String [] list,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    x = xx;
		    y = yy;
		    bx = x;
		    by = y;
		    w = ww;

		    h = list.length*20;
		    xoff = 0;
		    yoff = 0;
		    toggle =0;
		    bcol = col;
		    bsize = tsize;
		    //slide = true;

		    float bwidth = 0;

		    for (int i =0; i<list.length; i++) {
		      String l = list[i];

		      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		    }

		    for (int i =0; i<list.length; i++) {
		      String l = list[i];
		      Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l,bms);
		      a.parent = this;
		      a.border = false;
		      items.add(a);
		    }

		    twidth = bwidth*items.size();
		    w = bwidth;
	  };

	  public Menu(float xx, float yy, float ww, ArrayList<String> list) {
	    x = xx;
	    y = yy;
	    w = ww;

	    h = list.size()*20;
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	    //slide = true;

	    float bwidth = 0;

	    for (int i =0; i<list.size(); i++) {
	      String l = list.get(i);

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	    }

	    for (int i =0; i<list.size(); i++) {
	      String l = list.get(i);

	      Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l);
	      a.border = false;
	      a.parent = this;
	      items.add(a);
	    }

	    twidth = bwidth*items.size();
	    w = bwidth;
	  };
	  
	  public Menu(float xx, float yy, float ww, ArrayList<String> list,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    x = xx;
		    y = yy;
		    w = ww;

		    h = list.size()*20;
		    xoff = 0;
		    yoff = 0;
		    toggle =0;
		    bcol = col;
		    bsize = tsize;
		    //slide = true;

		    float bwidth = 0;

		    for (int i =0; i<list.size(); i++) {
		      String l = list.get(i);

		      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		    }

		    for (int i =0; i<list.size(); i++) {
		      String l = list.get(i);

		      Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l,bms);
		      a.border = false;
		      a.parent = this;
		      items.add(a);
		    }

		    twidth = bwidth*items.size();
		    w = bwidth;
		  };
	  
	  public Menu(float xx, float yy, float ww, String []list,boolean b1) {
	    x = xx;
	    y = yy;
	    w = ww;
	    spacing = 5;
	    
	    h = list.length*20;
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	    //slide = true;

	    float bwidth = 0;
	    float maxW = 0;
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	      //if(
	    }
	    
	    if(b1)
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      Button a = new Button( x+bwidth, y + (30)*(i+1), ww, 20, l);
	      a.rx = bwidth;
	      a.border = false;
	      a.parent = this;
	      items.add(a);
	    }
	    else 
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      Button a = new Button( x+bwidth, y + (30)*(i+1), ww, 20, l);
	      a.rx = bwidth;
	      a.border = false;
	      a.parent = this;
	      a.togglebar = true;
	      items.add(a);
	      //println("button",a);
	    }
	    
	    //twidth = bwidth*items.size();
	    w = bwidth+w;
	    twidth = w;
	  };
	  
	  public Menu(float xx, float yy, float ww,float H, String []list,boolean b1) {
	    x = xx;
	    y = yy;
	    w = ww;
	    spacing = 5;
	    
	    h = list.length*H;
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	    //slide = true;

	    float bwidth = 0;
	    float maxW = 0;
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	      //if(
	    }
	    
	    if(b1==true)
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      Button a = new Button( x+bwidth, y + (H+5)*(i+1), ww, H, l);
	      a.rx = bwidth;
	      a.border = false;
	      a.parent = this;
	      a.radio = true;
	      items.add(a);
	    }
	    else 
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      Button a = new Button( x+bwidth, y + (H+5)*(i+1), ww, H, l);
	      a.rx = bwidth;
	      a.border = false;
	      a.parent = this;
	      a.togglebar = true;
	      items.add(a);
	      //println("button",a);
	    }
	    
	    //twidth = bwidth*items.size();
	    w = bwidth+w;
	    twidth = w;
	  };
	  
	  public Menu(float xx, float yy, float ww,float H,float s, String []list,boolean b1) {
	    x = xx;
	    y = yy;
	    w = ww;
	    spacing = s;
	    
	    h = list.length*(H+s);
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	    //slide = true;

	    float bwidth = 0;
	    float maxW = 0;
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	      //if(
	    }
	    
	    if(b1==true)
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      Button a = new Button( x+bwidth, y + (H+s)*(i+1), ww, H, l);
	      a.rx = bwidth;
	      a.border = false;
	      a.parent = this;
	      a.radio = true;
	      items.add(a);
	    }
	    else 
	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      Button a = new Button( x+bwidth, y + (H+s)*(i+1), ww, H, l);
	      a.rx = bwidth;
	      a.border = false;
	      a.parent = this;
	      a.togglebar = true;
	      items.add(a);
	    }
	    w = bwidth+w;
	    twidth = w;
	  };

	  public Menu(float xx, float yy, float ww, String [] list, float k) {

	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = list.length*(20+k) - k;
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	    spacing = k;

	    float bwidth = 0;

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	    }

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];
	      Button a = new Button( x, y + (20+k) *(i+1), bwidth, 20, l);
	      a.border = false;
	      a.parent = this;
	      a.togglebox = true;
	      items.add(a);
	    }

	    twidth = bwidth*items.size();
	    w = bwidth;
	  };
	  
	  public Menu(float xx, float yy, float ww, String [] list, float k,BMScontrols bms) {
		  	Bms = bms;
		  	applet = bms.applet;
		    x = xx;
		    y = yy;
		    bx = x;
		    by = y;
		    w = ww;
		    h = list.length*(20+k) - k;
		    xoff = 0;
		    yoff = 0;
		    toggle =0;
		    bcol = col;
		    bsize = tsize;
		    spacing = k;

		    float bwidth = 0;

		    for (int i =0; i<list.length; i++) {
		      String l = list[i];

		      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		    }

		    for (int i =0; i<list.length; i++) {
		      String l = list[i];
		      Button a = new Button( x, y + (20+k) *(i+1), bwidth, 20, l,bms);
		      a.border = false;
		      a.parent = this;

		      items.add(a);
		    }

		    twidth = bwidth*items.size();
		    w = bwidth;
		  };
	  
	  public Menu(float xx, float yy, float ww, String [] list, float k,float h) {

	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = list.length*(h+k) - k;
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;
	    spacing = k;

	    float bwidth = 0;

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	    }

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];
	      Button a = new Button( x, y + (h+k) *(i+1+k), bwidth, 20, l);
	      a.border = false;
	      a.parent = this;
	      a.togglebox = true;
	      items.add(a);
	    }

	    twidth = bwidth*items.size();
	    w = bwidth;
	  };

	  public Menu(float xx, float yy, String [] list) {
	    
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    h = list.length*(20);
	    xoff = 0;
	    yoff = 0;
	    toggle =0;
	    bcol = col;
	    bsize = tsize;

	    float bwidth = 0;

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];

	      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
	    }

	    for (int i =0; i<list.length; i++) {
	      String l = list[i];
	      Button a = new Button( x, y + (20) *(i), bwidth, 20, l);
	      a.border = false;
	      a.parent = this;
	      a.togglebox = true;
	      items.add(a);
	    }

	    twidth = bwidth*items.size();
	    w = bwidth;
	  };

	  public Menu(String k, float xx, float yy, float ww, String dir) {
	    if (k=="dir"|| k=="DIR"||k=="Dir") {
	      x = xx;
	      y = yy;
	      bx = x;
	      by = y;
	      w = ww;

	      xoff = 0;
	      yoff = 0;
	      toggle =0;
	      bcol = col;
	      bsize = tsize;

	      String []list = fileUtils.listFileNames(dir);

	      for (int i =0; i<list.length; i++) {

	        String l = list[i];
	        for (int j=0; j<list[i].length(); j++) {
	          Button a = new Button( x, y + 20 *(i), w, 20, l);
	          a.border = false;
	          a.togglebox = true;
	          a.parent = this;
	          items.add(a);
	        }
	      }
	    }

	    h = items.size()*20;
	    Slider s = new Slider(x, y, 20, 20);
	    s.vertical = true;
	    sliders.add(s);
	  };

	  

	public void save(){
	    
	  };
	  
	  public void load(){
	    
	  };
	  
	  public void setParentCanvas(PGraphics canvas){
	    parentCanvas = true;
	    localCanvas = canvas;
	    
	  };

	  public void draw() {
		if(debug)applet.println("menu draw start");
	    logic();
	    if(debug)applet.println("menu draw logic");
	    if(show)trace();
	    if(debug)applet.println("menu draw trace");
	    if (draggable) {
	      if(!parentCanvas){
	        applet.stroke(0);
	        applet.fill(col);
	        if(localTheme)
	        applet.fill(fcol);
	        applet.rect(x,y-5, w, 5,r1,r2,r3,r4);
	      }else{
	        localCanvas.stroke(0);
	        localCanvas.fill(col);
	        if(localTheme)
	        localCanvas.fill(fcol);
	        localCanvas.rect(x,y-5, w, 5,r1,r2,r3,r4);
	      }
	    }
	    if(debug)applet.println("menu draw end");
	  };
	  
	  public void draw2() {
	    if(applet.mousePressed)click();
	    logic();
	    if(show)trace();
	    if (draggable) {
	      if(!parentCanvas){
	        applet.stroke(0);
	        applet.fill(0,266,0,150);
	        applet.rect(x,y-5, w, 5);
	      }else{
	        localCanvas.stroke(0);
	        localCanvas.fill(0,150);
	        localCanvas.rect(x,y-5, w, 5);
	      }
	    }
	  };
	  
	  public void draw(PGraphics canvas) {
	    if(mouse!=null)logic(mouse);
	    if(show)trace(canvas);
	    if(!border)canvas.noStroke();
//	      canvas.ellipse(mouse.x,mouse.y,20,20);
	    
	  };
	  
	  public void simpleDraw(PGraphics canvas) {
	    if(mouse!=null)logic(mouse);
	    if(show)trace(canvas);
	    if(!border)canvas.noStroke();
	    
	  };

	  public void drawWindow() {
	  }

	  public void trace() {
	    t2 = 0;
	    slcount = 0;
	    getState();
	    drawButtons();
//	    drawDmenu();
	    drawsliders();
	  };

	  public void trace(PGraphics canvas) {
	    t2 = 0;
	    slcount = 0;

	    getState(canvas);
	    drawButtons(canvas);
	    //drawDmenu();
	    drawsliders();
	  };

	  public void getState() {

	    applet.stroke(0);
	    if(highlightable)highlight();
	    if (!border)applet.noStroke();

	    applet.fill(255);
	      if (visible&&label!=null)applet.rect(x, y, w, h,r1,r2,r3,r4);
	      applet.fill(col);
	      if(localTheme)
	    	  applet.fill(fcol);
	      
	      if (visible&&label!=null)applet.rect(x, y, w, h,r1,r2,r3,r4);
	      applet.fill(0);

	    if(label!=null) {
	      applet.fill(Bms.tcol);
	      if(localTheme)
    	  applet.fill(tcol);
	      applet.textSize(bsize);
	      applet.text(label, x+xoff, y+yoff + 18);
	      applet.textSize(12);
	      //fill(col);
	    } else {
	      toggle = 1;
	    }

	    
	  };
	  
	  public void getState(PGraphics canvas) {
	    //if(highlightable)highlight(canvas);
	    while (items.size()!=child.size()) {
	      child.add(false);
	    }
	    canvas.stroke(0);
	    if (!border)canvas.noStroke();

	    canvas.fill(col);
	    if(localTheme)
	    canvas.fill(fcol);
	    if (visible&&label!=null)canvas.rect(x, y, w, h,r1,r2,r3,r4);
	    canvas.fill(col);
	    if(localTheme)
	    canvas.fill(fcol);
	    if (visible&&label!=null)canvas.rect(x, y, w, h,r1,r2,r3,r4);
	    canvas.fill(0);

	    if(label!=null) {
	      canvas.fill(Bms.tcol);
	      if(localTheme)
	      canvas.fill(tcol);
	      canvas.textSize(bsize);
	      canvas.text(label, x+xoff, y+yoff + 18);
	      canvas.textSize(12);
	      //fill(col);
	    } else {
	      toggle = 1;
	    }
	  };

	  public void drawButtons() {
	    
	    float speed = h/5;
	    if (items.size()>0) {
	      // if(pos()&&applet.mousePressed){
	      //   println("hello");
	      //   toggle = 1;
	      // }
	      for (int i=0; i<items.size(); i++) {

	        Button a = items.get(i);
	        a.id = i;
	        if(parentTab!=null)a.parentTab = parentTab;
	        if (a.parent!=null&&label==null&&!horizontal){
	          a.y = a.parent.y + i * (a.h + spacing);
	          a.x = a.parent.x;
	        }
	        
	        if (a.parent!=null&&label!=null&&!horizontal&&!free){
	          a.y = a.parent.y + a.h + i * (a.h + spacing);
	          a.x = a.parent.x;
	        }
	        
	        if (a.parent!=null&&label==null&&horizontal) {
	          a.x = a.parent.x + (a.parent.w + spacing) + i * a.w;
	          a.y = y+a.h;
	        }
	        if(a.submenu!=null){
	          if(!a.submenu.parentRight){
	            a.submenu.x = a.x + a.w;
	            a.submenu.y = a.y;
	          }else{
	            a.submenu.x = a.x - a.submenu.w;
	            a.submenu.y = a.y;
	          }
	        }

	        if(a.dMenu!=null){
	          a.dMenu.x = a.x + a.w;
	          a.dMenu.y = a.y;
	        }

	        if (toggle==1) {
	          if (a.pos(mousePos)&&i>=0) {
	            t2 ++;
	            index = i;
	          }
	          if (a.subpos(mousePos)&&i>=0) {
	            t2 ++;
	            subindex = a.id;
	          }}

	        if (!slide) {
	          if (toggle==1)window = y+h*(items.size()+1);
	          else window = 0;
	        } else {
	          if (toggle==1) {
	            window += speed;

	            if (!horizontal) {
	              if (window>y + h*items.size()) window = y+h*items.size();
	            } else {
	              if (window>x + a.w*items.size())window = x+a.w*items.size();
	            }} else {
	            window -= speed;
	            if (window<0)window = 0;
	          }}

	        if (!horizontal) {
	          if (y + window >= a.y) {
	            if (a.visible){
	            if(!parentCanvas) {
	            	a.draw();
	            }
	            else {
	              a.mouse = mouse;
	              a.draw(localCanvas);
	            }}
	          }
	        } else {
	          if (x + window >= a.x) {
	            if (a.visible){
	            if(!parentCanvas)a.draw();
	            else {
	              a.mouse = mouse;
	              a.draw(localCanvas);
	            }
	            }
	          }}}}
	    
	  };

	  public void drawButtons(PGraphics canvas) {
	    
	    float speed = 2;
	    if (items.size()>0) {
	      for (int i=0; i<items.size(); i++) {

	        Button a = items.get(i);
	        a.id = i;

	        a.mouse = mouse;
	        
	        
	        if (a.parent!=null&&label==null&&!horizontal){
	          a.y = a.parent.y + i * (a.h + spacing);
	          a.x = a.parent.x;
	        }
	        
	        if (a.parent!=null&&label!=null&&!horizontal&&!free){
	          a.y = a.parent.y + 20 + i * (a.h + spacing);
	          a.x = a.parent.x;
	        }
	        
	        if (a.parent!=null&&label==null&&horizontal) {
	          a.x = a.parent.x + (a.parent.w + spacing) + i * a.w;
	          a.y = y;
	        }
	        if(a.submenu!=null){
	          if(!a.submenu.parentRight){
	            a.submenu.x = a.x + a.w;
	            a.submenu.y = a.y;
	          }else{
	            a.submenu.x = a.x - a.submenu.w;
	            a.submenu.y = a.y;
	          }
	        }

	        if(a.dMenu!=null){
	          a.dMenu.x = a.x + a.w;
	          a.dMenu.y = a.y;
	        }

	        if (toggle==1) {
	          if (a.pos(mouse)&&i>=0) {
	            t2 ++;
	            index = i;
	          }
	          if (a.subpos(mouse)&&i>=0) {
	            t2 ++;
	            subindex = a.id;
	          }}

	        if (!slide) {
	          if (toggle==1)window = y+20*(items.size()+1);
	          else window = 0;
	        } else {
	          if (toggle==1) {
	            window += speed;

	            if (!horizontal) {
	              if (window>y + 20*items.size()) window = y+20*items.size();
	            } else {
	              if (window>x + a.w*items.size())window = x+a.w*items.size();
	            }} else {
	            window -= speed;
	            if (window<0)window = 0;
	          }}

	        if (!horizontal) {
	          if (y + window >= a.y) {
	            if (a.visible)a.draw(canvas);
//	            a.highlight(mouse);
	            
	          }
	        } else {
	          if (x + window >= a.x) {
	            if (a.visible)a.draw(canvas);
//	            a.highlight(mouse);
	            
	          }}
//	        a.highlight(mouse);
	      }}
	    
	  };

	  public void drawDmenu() {
	    float speed = 2;
	    if (dMenus.size()>0) {
	      
	      }
	  };
	  
	  public void add(Slider s,float spacing){
	    s.Bms = Bms;
	    s.applet = this.applet;
	    sliders.add(s);
	    
	    if(items.size()==0)h = (s.h+spacing)*sliders.size();
	    else h = (items.get(0).h+spacing)*items.size()+(s.h+spacing)*sliders.size();
	  };
	  
	  public void add(Slider s){
	    s.Bms = Bms;
	    s.applet = this.applet;
	    sliders.add(s);
	    
	    if(items.size()==0)h = (s.h+spacing)*sliders.size();
	    else h = (items.get(0).h+spacing)*items.size()+(s.h+spacing)*sliders.size();
	  };
	  
	  public void add(Button b){
	    b.Bms = Bms;
	    b.applet = this.applet;
	    b.parent = this;
	    items.add(b);
	    
	    if(sliders.size()==0)h = (b.h+spacing)*items.size();
	    else h = (sliders.get(0).h+spacing)*sliders.size()+(b.h+spacing)*items.size();
	  };
	  
	  public void add(String s){
		if(items.size()>0) {
			Button b = new Button(x,y+items.size()*items.get(0).h+spacing,items.get(0).w,items.get(0).h,s,Bms);
		    b.Bms = Bms;
		    b.applet = this.applet;
		    b.parent = this;
		    items.add(b);
		    
		    if(sliders.size()==0)h = (b.h+spacing)*items.size();
		    else h = (sliders.get(0).h+spacing)*sliders.size()+(b.h+spacing)*items.size();
		    h += items.get(0).h;
		}
	  };
	  
	  public void add(String s,float h1){
		if(items.size()>0) {
			Button b = new Button(x,y+items.size()*items.get(0).h+spacing,items.get(0).w,h1,s,Bms);
		    b.Bms = Bms;
		    b.applet = this.applet;
		    b.parent = this;
		    items.add(b);
		    
		    h+= h1;
		}
	  };
	  
	  public void drawsliders() {
	    float speed = 2;
	    
	    if (sliders.size()>0&&show) {

	      if (pos4()) {
	        //toggle=1;
	        //menuhover = true;
	      } else menuhover = false;
	      
	      for (int i=0; i<sliders.size(); i++) {

	        Slider a = sliders.get(i);
	        if(parentCanvas)a.mouse = mouse;
	        if(parentTab!=null)a.parentTab = parentTab;
	        //else a.mouse = new PVector(applet.mouseX,applet.mouseY);
	        if (a.toggle||a.mdown) slcount ++;
	        a.parent = this;
	        if (a.id==-1)a.id = i;
	        if (horizontal) { 
	          if(grid) {
	        	  
	          }else {
		          a.horizontal = true; 
		          a.y = y; 
		          a.x = x + a.w + spacing * i;
	          }
	        }else{
	        	if(grid) {
	        		
	        	  int r = i/cols;
	        	  int c = i%cols;
//	        	  if(applet.mousePressed)applet.println("kek",c,r);
	        	  if(a.pie) {
	        		  a.y = a.h/2+y + (a.h + gvSpacing+20) * r; 
	  		          a.x = x+a.w/2 + (a.w + ghSpacing+20)* c;
	  		          
	        	  }else {
	        		  a.x = x + c * (a.w + ghSpacing);
		        	  a.y = y + r * (a.h + gvSpacing); 
	        	  }
	        	}else {
	        		if(a.pie) {
	        			a.y = a.h/2+y + (a.h + spacing+20) * i; 
	  		            a.x = x+a.w/2;
		        	}else {
		        		a.y = y + (a.h + spacing) * i; 
		        		a.x = x;
		        	  }
	        	}
	        }
	        if(!parentCanvas){
	        if ((a.pos()||a.btnpos())&&!smdown) {
	          smdown = true;
	          sindex = i;
	          a.mdown = true;
	        }}else if ((a.pos(mouse)||a.btnpos(mouse))&&!smdown) {
	          smdown = true;
	          sindex = i;
	          a.mdown = true;
	        }
	        if(sindex!=i)a.mdown = false;
	        if (!applet.mousePressed&&smdown) { 
	          smdown = false;
	          a.mdown = false;
	        }
	        if (!slide) {
	          if (toggle==1) {
	            if (vertical)window = y + (spacing +a.h)*(sliders.size());
	            else window = x + (spacing +a.w)*(sliders.size());
	          }} else {
	          if (toggle==1) {
	            window += speed;
	            if (vertical) {
	              if (window>y + a.h * sliders.size()) {
	                window = y + a.h * sliders.size();
	              }} else {
	              if (window>x + a.w * sliders.size()) {
	                window = x + a.w * sliders.size();
	              }}} else {
	            window -= speed;
	            if (window<=0) window = 0;
	            
	          }}
	          if(applet.mousePressed){
	            //if(vertical)println("vert");
	            //else println("nvert");
	          }
	        if (vertical) {
	          if (y + window >= a.y){
	            
	            if (a.visible){
	            if(!parentCanvas)a.draw();
	            else a.draw(localCanvas);
	            }
	          }} else {
	          if (x + window >= a.x){

	            if (a.visible){
	            if(!parentCanvas)a.draw();
	            else a.draw(localCanvas);
	            }}}}
	    }
	    if (slcount>0) sltoggle = true;
	    else sltoggle = false;
	    
	  };

	  public void borders(Boolean k) {
	    for (int i=0; i<items.size(); i++) {
	      Button b = items.get(i);

	      b.border = k;
	    }
	  };

	  public void update() {
	    counter = 0;
	    for (int i=0; i<items.size(); i++) {

	      Button a = items.get(i);
	      if (a.pos()) counter++;
	    }
	  };
	  
	  public void draw4(){
	    
		applet.fill(255);
	    PApplet.println("hishe",applet.mouseX,applet.mouseY);
	    for (int i=0; i<sliders.size(); i++) {

	        Slider a = sliders.get(i);
	        if(parentCanvas)a.mouse = mouse;
	        a.draw();
	    }
	  };
	  
	  public void draw5(){
	    applet.fill(0);
	    applet.rect(x,y,20,20);
	  };

	  public void setBorders(Boolean a) {
	    for (int i=0; i<items.size(); i++) {
	      Button b = items.get(i);

	      b.border = a;
	    }
	  };

	  public void logic() {

	    mousePos = new PVector(applet.mouseX,applet.mouseY);
	    if(applet.mousePressed&&pos()&&!dpos()&&!drag)m5down = true;
	    if(mouse!=null) mousePos = mouse;
	    //if(index>-1&&subindex==-1&&!pos()&&applet.mousePressed)index = -1;
	    if (mWindow!=null)y = sliders.get(0).valuey;
	    if (highlightable) {
	      if(!localTheme){
	        if (pos(mousePos)) col = Bms.hcol;
	        else col = Bms.fcol;
	      }else{
	        if (pos(mousePos)) col = hcol;
	        else col = fcol;
	      }
	    }
	    if(!localTheme){
	      if (toggle==1)col = Bms.toggleCol;
	      else col = Bms.fcol;
	    }else{
	      if (toggle==1)col = toggleCol;
	      else col = fcol;
	    }
	    
	    if ((pos()||toggle==1)) open_menu = true;
	    else open_menu = false;
	    if (draggable&&dpos()&&applet.mousePressed&&!drag&&!mdown&&!m5down) {
	      mdown = true;
	      drag = true;
	      dx = applet.mouseX - x;
	      dy = applet.mouseY - y;
	    }
	    
	    if(drag){
	      x = applet.mouseX - dx;
	      y = applet.mouseY - dy;
	      
	      for (int i=0;i<items.size();i++){
	        Button b = items.get(i);
	        b.x = x;
	      }}
	    
	    float X = (applet.mouseX - applet.pmouseX);
	    float Y = (applet.mouseY - applet.pmouseY);

	    if (!applet.mousePressed) {
	      mdown = false;
	      mup = true;
	      drag = false;
	      m5down = false;

	    }

	    if (t2>0)menuhover = true;
	    else menuhover = false;
	    
	    if (!pos()) {
	      if(toggle==1)
	        if (applet.mousePressed&&!menu&&!menuhover&&sliders.size()==0&&index>0&&!m3down) {
	          m3down = true;
	        }
	        
	        if(m3down&&!applet.mousePressed){
	          m3down = false;
	          getIndex = true;
	        }
	    
	    }

	    if(index>-1&&subindex>-1&&items.get(subindex).submenu!=null&&items.get(subindex).submenu.parentRight){
	          //if( index>-1&&subindex>-1&&items.get(subindex).pos()&&!items.get(subindex).subpos())subindex = -1;
	        }else if(index>-1&&subindex>-1&&items.get(subindex).submenu!=null){
	          if( index>-1&&subindex>-1&&items.get(subindex).pos()&&!items.get(subindex).subpos())subindex = -1;
	        }
	    if (subindex!=index&&subindex!=-1&&items.size()>0&&sliders.size()==0)index = -1;
	    

	    if (items.size()>0&&index>=0&&toggle==1) {
	      Button a = items.get(index);
	      a.highlight2();
	    }

	    if (type==2) {
	      toggle=1;
	      if (sliders.size()>0&&sindex>=0&&smdown) {

	        Slider b = sliders.get(sindex);
	        
	          b.mouseFunctions();
	          if (b.btnpos()||b.pos()||b.mdown)b.btnh = b.h+5;
	          if (!b.mdown) b.btnh = b.h;
	          //if (b.btnpos()||b.pos()||b.mdown)b.mouseFunctions();
	        }
	        
	      if (sliders.size()>0&&sindex>=0&&!smdown) {
	        Slider b = sliders.get(sindex); 
	        b.btnh = b.h+2;
	      }}

	    if (items.size()>0&&subindex>=0&&subindex==index&&toggle==1&&sliders.size()==0) {
	      Button a = items.get(subindex);

	      if (a.submenu!=null&&a.id==subindex) {

	        if (a.submenu.pos2()||a.submenu.menu)menu = true;
	        else menu = false; 

	        a.submenu.draw();
	      }
	      if (a.dMenu!=null&&a.id==subindex) {

	        if (a.dMenu.menu)menu = true;
	        else menu = false; 

	        
	        if(a.pos()&&!a.subpos()){}
	        else a.dMenu.displayDropdown();
	        
	      }
	    }
	    if (!animate) {
	      if (pos()||toggle==1) {
	        if (bsize<tsize+2) bsize += 0.5;
	      } else if (bsize>tsize&&bsize>2)bsize -= 0.5;
	    }
	    if(!applet.mousePressed){
	     m5down = false; 
	    }
	  };
	  
	  public void logic(PVector m) {

	    mousePos = new PVector(applet.mouseX,applet.mouseY);
	    if(mouse!=null) mousePos = mouse;

	    if (mWindow!=null)y = sliders.get(0).valuey;
	    if (highlightable) {
	      if (pos(m))col = hcol;
	       else col = fcol;
	      }

	    if (toggle==1)col = hcol;
	    else col = fcol;

	    if ((pos(m)||toggle==1)) open_menu = true;
	    else open_menu = false;
	    if (draggable&&dpos(m)&&applet.mousePressed&&!drag&&!mdown) {
	      mdown = true;
	      drag = true;
	      dx = m.x - x;
	      dy = m.y - y;
	      //mup = false;
	    }
	    
	    if(drag){
	      x = m.x - dx;
	      y = m.y - dy;
	      
	      for (int i=0;i<items.size();i++){
	        Button b = items.get(i);
	        b.x = x;
	      }}
	      
	    if (!applet.mousePressed) {
	      mdown = false;
	      mup = true;
	      drag = false;
	    }

	    if (t2>0)menuhover = true;
	    else menuhover = false;
	    
	    if (!pos(mouse)) {
	      if (applet.mousePressed&&!menu&&!menuhover&&sliders.size()==0) {
	        toggle=0;
	        subindex = -1;
	        index = -1;
	      }}
	    if (subindex!=index&&subindex!=-1&&items.size()>0&&sliders.size()==0)index = -1;
	    

	    if (items.size()>0&&index>=0&&toggle==1) {
	      Button a = items.get(index);
	      a.highlight2();
	    }

	    if (type==2) {
	      toggle=1;
	      if (sliders.size()>0&&sindex>=0&&smdown) {

	        Slider b = sliders.get(sindex);
	        b.mouseFunctions(m);
	        if (b.btnpos(m)||b.pos(m)||b.mdown)b.btnh = b.h+5;
	        if (!b.mdown) b.btnh = b.h;
	        if (b.btnpos(m)||b.pos(m)||b.mdown)b.mouseFunctions(m);
	      }
	      if (sliders.size()>0&&sindex>=0&&!smdown) {
	        Slider b = sliders.get(sindex); 
	        b.btnh = b.h+2;
	      }
	    }

	    if (items.size()>0&&subindex>=0&&subindex==index&&toggle==1&&sliders.size()==0) {
	      Button a = items.get(subindex);

	      if (a.submenu!=null&&a.id==subindex) {

	        if (a.submenu.pos2(m))menu = true;
	        else menu = false; 

	        a.submenu.draw();
	      }
	    }
	    if (!animate) {
	      if (pos(m)||toggle==1) {
	        if (bsize<tsize+2)bsize += 0.5;
	      } else if (bsize>tsize&&bsize>0)bsize -= 0.5;
	    }
	  };

	  public void setLink(Menu a) {

	    link = a;
	  };
	  
	  public void setPos(float a,float b) {
		  x = a;
		  bx = a;
		  y = b;
		  by = b;
	  };

	  boolean pos() {

	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h+1;
	  };
	  
	  boolean pos(PVector m) {

	    return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+h+1;
	  };
	  
	  boolean pos2() {

	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+20*(items.size());
	  };
	  boolean pos2(PVector m) {

	    return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+20*(items.size());
	  };
	  boolean pos3() {

	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+20*(items.size());
	  };
	  boolean pos4() {

	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+20*(sliders.size());
	  };

	  public void drag() {
	  };

	  boolean dpos() {
	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y-10&&applet.mouseY<y;
	  };
	  
	  boolean dpos(PVector m) {
	    return m.x>x&&m.x<x+w&&m.y>y-10&&m.y<y;
	  };

	  boolean outpos() {
	    float X = applet.mouseX;
	    float Y = applet.mouseY;
	    return (X < x || X > x + w+10) || (Y < y || Y > y + h * (items.size()+1));
	  };

	  boolean click(int a){
	    boolean k = false;
	    if(items.size()>a&&items.get(a).click()) k = true;
	    return k;
	  };

	  public void click(int i, Object a, String b) {
	    Button k = items.get(i);
	    //k.click(a, b);
	    if(k.click()){
	      if (link==null)k.toggle(a, b);
	    else if (link.toggle==1)k.toggle(a, b);
	    }

	  };
	  
	  public void click(int i, PApplet a, String b) {
	    Button k = items.get(i);
	    k.click(a, b);
	    k.toggle=0;
	    if(!applet.mousePressed)k.mdown=false;
	  };

	  boolean pos(int i){
	    return items.get(i).pos(mousePos);

	  };

	  // boolean click(int a, Object b, String c){
	  //   boolean k = false;
	  //   if(click(a)) k = true;

	  //   Field field = null;
	    
	  //   try{
	  //        field = b.getClass().getField(c); 
	        
	  //        if((click(a))){
	  //          field.set(c, true); 
	  //        }else {
	  //          field.set(c, false);
	  //        }
	  //   }catch (NullPointerException e) {
	  //   }catch (NoSuchFieldException e) {
	  //   }catch (IllegalAccessException e) {
	  //   }
	    
	  //   return k;
	  // };
	  
	  boolean itemSelected(){
	    boolean k = false;
	    //if(applet.mousePressed)
	    for(int i=0;i<items.size();i++){
	      Button b = items.get(i);
	      
	      if(b.pos()){
	        k = true;
	        index = i;
	        if(applet.mousePressed){
	          m4down = true;
	          //println("m4down");
	        }
	        //println("button",i);
	        //break;
	      }
	    }
	    //if(index>-1&&items.get(index).submenu!=null){
	    //  for(int i=0;i<items.get(index).submenu.items.size();i++){
	    //    Button b = items.get(index).submenu.items.get(i);
	        
	    //    if(b.pos()){
	    //      k = true;
	    //      //index = i;
	    //      //b.draw2();
	    //      if(applet.mousePressed){
	    //        m4down = true;
	    //        //println("m4down");
	    //      }
	    //    }
	    //  }
	    //}
	    //if(k){
	    // fill(0);
	    // rect(0,0,200,200);
	    //}
	    if(!applet.mousePressed)m4down = false;
	    return k;
	  };

	  public void click() {
	    if (parent==null) {
	      boolean k = itemSelected();
	      if(k&&applet.mousePressed)m4down = true;
	      if(index>-1&&items.get(index).submenu!=null&&items.get(index).submenu.pos())subMenuPos = true;
	      else subMenuPos = false;
	      
	      if (pos() && applet.mousePressed&&Bms.menuObject==null&&!m3down) {
	        toggle++;
	        Bms.menuObject = this;
	        m3down = true;
	        //println("l1");
	      }
	      if (!m3down&&pos() && applet.mousePressed&&Bms.menuObject==this&&toggle==1) {
	        m3down = true;
	        toggle = 0;
	        Bms.menuObject.toggle = 0;
	        Bms.menuObject.index = -1;
	        Bms.menuObject=null;
	        //println("l2");
	      }
	      if(applet.mousePressed&&k){
	        m3down = true;
	      }
	      
	      if (!pos() && applet.mousePressed&&!k&&!m4down&&!subMenuPos&&!m3down) {
	        m3down = true;
	        toggle = 0;
	        if(Bms.menuObject==this){
	        Bms.menuObject.toggle = 0;
	        Bms.menuObject.index = -1;
	        Bms.menuObject=null;
	      }
	        //println("l3");
	      }
	      //if(!m3down&&index==-1&&applet.mousePressed&&Bms.menuObject==this){
	      //    m3down = true;
	      //    toggle = 0;
	      //    Bms.menuObject.toggle = 0;
	      //    //Bms.menuObject.index = -1;
	      //    Bms.menuObject=null;
	      //    println("l4");
	      //  }
	    }
	    
	    if(!applet.mousePressed){
	      m3down = false;
	      m4down = false;
	    }
	  };

	  public void click(boolean m){
	    
	    if(pos()&&applet.mousePressed&&!m2down){
	      toggle ++;
	      m2down = true;
	    }
	    
	    if(toggle>1)toggle=0;
	    if(!applet.mousePressed)m2down = false;
	  };
	  
	  // public void selfClick(int i) {
	  //   Button k = items.get(i);
	    
	  //   return k.click(mouse);
	    
	  // };

	  boolean selfClick(int i) {
	    Button k = items.get(i);
	    
	    return k.click(mouse);
	    
	  };

	  public void selfClick(int i, Object o, String b) {
	    //Button k = items.get(i);
	    //if (link==null)k.selfClick(o, b);
	    //else if (link.toggle==1)k.toggle(o, b);
	    
	  };
	  
	  public void toggle(int i, String b) {
	    Button k = items.get(i);
	    k.toggle();
	    //k.toggle(o, b);
	    ;
	  };

	  public void toggle(int i, PApplet o, String b) {
	    Button k = items.get(i);
	    if (link==null)k.toggle(o, b);
	    else if (link.toggle==1)k.toggle(o, b);
	    ;
	  };
	  
	  public void toggle(int i, Object o, String b) {
		  
	    Button k = items.get(i);
	    if(show){
	    if (link==null)k.toggle(o, b);
	    else if (link.toggle==1)k.toggle(o, b);
	    }
	    //k.toggle(o, b);
	    ;
	  };

	  public void toggle_(int i, Object o, String b) {
	    Button k = items.get(i);
	    if(show){
	    if (link==null&&applet.mousePressed&&!mdown)k.toggle(o, b);
	    else if (link!=null&&link.toggle==1&&applet.mousePressed&&!mdown)k.toggle(o, b);
	    }
	    //k.toggle(o, b);
	    ;
	  };
	  
	  
	  public void toggle2(int i, Object o, String b) {
	    Button k = items.get(i);
	    if (link==null)k.toggle2(o, b);
	    else if (link.toggle==1)k.toggle2(o, b);
	    ;
	  };

	  public void toggle(Menu m, int i, Object o, String b) {
	    Button k = items.get(i);
	    //if(parent!=null&&parent.toggle==1)
	    if (m.toggle==1)k.toggle(o, b);
	  };
	  
	  public void self_toggle(int i) {
	    Button k = items.get(i);
	    if (k.parent.toggle==1)k.self_toggle();
	    //else if(
	  };
	  
	  public void self_toggle(int i,PVector m) {
	    Button k = items.get(i);
	    if (k.parent.toggle==1)k.self_toggle(m);
	    //else if(
	  };

	  public void sptoggle(int n, Object a, String b, String [] c) {

	    Button k = items.get(n);

	    k.sptoggle(a, b, c);

	  };

	  public void sptoggle(int n, Object a, String b, String [] c,Menu m) {

	    Button k = items.get(n);

	    k.sptoggle(a, b, c);
	  };

	  public void sptoggle( Object a, String [] c) {


	    for(int i=0;i<c.length;i++){
	      Bms.shapes.sptoggle(i,a,c[i],c);
	      if(applet.mousePressed)
	      if(!items.get(i).pos()){
	        items.get(i).toggle=0;
	      }
	      PApplet.println("item toggle = ",items.get(i).toggle);
	    }
	  };

	  public void sptoggle2( Object a, String [] c,Menu m) {


	    for(int i=0;i<c.length;i++){
	      Bms.shapes.sptoggle(i,a,c[i],c,m);
	    }
	  };
	  
	  public void toggleAll() {
		  if(parentTab==null) {
			 for(int i=0;i<items.size();i++){
			  	Button k = items.get(i);
			  	if (toggle==1)k.self_toggle();
			 }
		  }else {
			  for(int i=0;i<items.size();i++){
			  	 Button k = items.get(i);
			  	 if (toggle==1)k.self_toggle(mouse);
			  } 
		  }
		 
	  };
	  
	  public void toggleAll(PVector m) {
		 for(int i=0;i<items.size();i++){
		  	Button k = items.get(i);
		  	if (toggle==1)k.self_toggle(m);
		 }
	  };
	  
	  public void toggleAll(PGraphics canvas) {
		 for(int i=0;i<items.size();i++){
		  	Button k = items.get(i);
		  	if (toggle==1)k.self_toggle(mouse);
		 }
	  };

	  public void slide(float start, float end) {
	    sliders.get(0).set(start, end);
	  };

	  public void set(int a, int b) {
	    items.get(a).toggle=b;
	  };
	  
	  

	  public void set_parent(Button b) {

	    parent = b;

	    //for(int i=0;i<m.items.size();i++){
	    //  Button k = m.items.get(i);

	    //}
	  };

	  public void setLink(int k) {
	    Menu m = items.get(k).submenu;

	    m.link = this;
	  };

	  String[] getItems(){
	    String[] s = new String[items.size()];
	    for(int i=0;i<items.size();i++){
	      s[i] = items.get(i).label;
	    }
	    return s;
	  };
	  
	  public void setPlain() {
		  for(int i=0;i<items.size();i++){
		      Button b = items.get(i);
		      b.plain = true;
		      b.radio = false;
		      b.togglebox = false;
		      b.togglebar = false;
		    }
	  };
	  
	  public void setRadio() {
		  float bwidth = 0;
		  radio = true;
		  for (int i =0; i<items.size(); i++) {
			  Button b = items.get(i);
		      String l = b.label;

		      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		      //if(
		  }
		  for(int i=0;i<items.size();i++){
		      Button b = items.get(i);
		      b.plain = false;
		      b.radio = true;
		      b.togglebox = false;
		      b.togglebar = false;
		      b.rx = bwidth;
		   }
		  
		  
//		    bwidth = 0;
//		    for (int i =0; i<sliders.size(); i++) {
//			  Slider b = sliders.get(i);
//		      String l = b.label;
//
//		      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
//		      //if(
//		    }
		    for(int i=0;i<sliders.size();i++){
		      Slider b = sliders.get(i);
		      
		      //b.rx = bwidth;
		      b.classic = true;
		      b.matrix = false;
		      b.pie = false;
		      b.radio = true;
		      b.bar = false;
		      b.square = false;
		      
		    }
		  
	  };
	  
	  public void setClassicBar() {
		  classicBar = true;
		  for(int i=0;i<items.size();i++){
		      Button b = items.get(i);
		      b.plain = false;
		      b.radio = false;
		      b.togglebox = false;
		      b.togglebar = true;
		    }
		    
		    for(int i=0;i<sliders.size();i++){
		      Slider b = sliders.get(i);
		      
		      b.classic = true;
		      b.matrix = false;
		      b.pie = false;
		      b.radio = false;
		      b.bar = true;
		      b.square = false;
		      
		    }
		  
	  };
	  
	  public void setClassicBox() {
		  toggleBox = true;
		  for(int i=0;i<items.size();i++){
		      Button b = items.get(i);
		      b.plain = false;
		      b.radio = false;
		      b.togglebox = true;
		      b.togglebar = false;
		    }
	  };
	  
	  public void setClassicSquare() {
		  classicSquare = true;
		  for(int i=0;i<items.size();i++){
			  
		      Button b = items.get(i);
		      b.plain = false;
		      b.radio = false;
		      b.togglebox = false;
		      b.togglebar = false;
		    }
		    
		    for(int i=0;i<sliders.size();i++){
		      Slider b = sliders.get(i);
		      
		      b.classic = true;
		      b.matrix = false;
		      b.pie = false;
		      b.radio = false;
		      b.bar = false;
		      b.square = true;
		      
		    }
		  
	  };
	  
	  public void setClassicRadio() {
		  classicRadio = true;
		  for(int i=0;i<items.size();i++){
		      Button b = items.get(i);
		      b.plain = false;
		      b.radio = true;
		      b.togglebox = false;
		      b.togglebar = false;
		    }
		    
		    for(int i=0;i<sliders.size();i++){
		      Slider b = sliders.get(i);
		      
		      b.classic = true;
		      b.matrix = false;
		      b.pie = false;
		      b.radio = true;
		      b.bar = false;
		      b.square = false;
		      
		    }
		  
	  };
	  
	  public void setPieSquare() {
		    pieSquare = true;
		    for(int i=0;i<sliders.size();i++){
		      Slider b = sliders.get(i);
		      
		      b.classic = false;
		      b.matrix = false;
		      b.pie = true;
		      b.radio = false;
		      b.bar = false;
		      b.square = true;
		      //b.valuex = 2*PConstants.PI;
		      b.valuex = applet.map(b.value,0,255,0,2*PConstants.PI);
		      b.radius = w/2;
		      b.y = y + b.h + (b.h/2)*i;
		    }
		    
		    if(sliders.size()>0)h = (sliders.get(0).h+sliders.get(0).spacing)*sliders.size();
		  
	  };
	  
	  
	  public void setRadius(float a){
	    r1 = a;
	    r2 = a;
	    r3 = a;
	    r4 = a;
	    for(int i=0;i<items.size();i++){
	      Button b = items.get(i);
	      b.r1 = a;
	      b.r2 = a;
	      b.r3 = a;
	      b.r4 = a;
	      //println(b.r1);
	      if(b.submenu!=null){
	        for(int j=0;j<b.submenu.items.size();j++){
	          Button b1 = b.submenu.items.get(j);
	          b1.r1 = a;
	          b1.r2 = a;
	          b1.r3 = a;
	          b1.r4 = a;
	        }
	      }
	    }
	    
	    for(int i=0;i<sliders.size();i++){
	      Slider b = sliders.get(i);
	      b.r1 = a;
	      b.r2 = a;
	      b.r3 = a;
	      b.r4 = a;
	      if(b.tooltip!=null)b.tooltip.setRadius(a);
	    }
	  };
	  
	  public void setRadius(float a,float b,float c,float d){
	    r1 = a;
	    r2 = b;
	    r3 = c;
	    r4 = d;
	    
	    for(int i=0;i<items.size();i++){
	      Button b1 = items.get(i);
	      b1.r1 = a;
	      b1.r2 = b;
	      b1.r3 = c;
	      b1.r4 = d;
	      if(b1.submenu!=null){
	        for(int j=0;j<b1.submenu.items.size();j++){
	          Button b2 = b1.submenu.items.get(j);
	          b2.r1 = a;
	          b2.r2 = b;
	          b2.r3 = c;
	          b2.r4 = d;
	        }
	      }
	    }
	    
	    for(int i=0;i<sliders.size();i++){
	      Slider b1 = sliders.get(i);
	      b1.r1 = a;
	      b1.r2 = b;
	      b1.r3 = c;
	      b1.r4 = d;
	      if(b1.tooltip!=null)b1.tooltip.setRadius(a);
	      
	    }
	  };
	  
	  public void setButtonRadius(float a){
	    
	    for(int i=0;i<items.size();i++){
	      Button b1 = items.get(i);
	      b1.r1 = a;
	      b1.r2 = a;
	      b1.r3 = a;
	      b1.r4 = a;
	      if(b1.submenu!=null){
	        for(int j=0;j<b1.submenu.items.size();j++){
	          Button b2 = b1.submenu.items.get(j);
	          b2.r1 = a;
	          b2.r2 = a;
	          b2.r3 = a;
	          b2.r4 = a;
	        }
	      }
	    }
	  };
	  
	  public void setSliderRadius(float a){
	    
	    for(int i=0;i<sliders.size();i++){
	      Slider b1 = sliders.get(i);
	      b1.r1 = a;
	      b1.r2 = a;
	      b1.r3 = a;
	      b1.r4 = a;
	    }
	  };
	  
	  public void setButtonRadius(float a,float b,float c,float d){
	    
	    for(int i=0;i<items.size();i++){
	      Button b1 = items.get(i);
	      b1.r1 = a;
	      b1.r2 = b;
	      b1.r3 = c;
	      b1.r4 = d;
	      if(b1.submenu!=null){
	        for(int j=0;j<b1.submenu.items.size();j++){
	          Button b2 = b1.submenu.items.get(j);
	          b2.r1 = a;
	          b2.r2 = b;
	          b2.r3 = c;
	          b2.r4 = d;
	        }
	      }
	    }
	  };
	  
	  public void setSliderRadius(float a,float b,float c,float d){
	    
	    for(int i=0;i<sliders.size();i++){
	      Slider b1 = sliders.get(i);
	      b1.r1 = a;
	      b1.r2 = b;
	      b1.r3 = c;
	      b1.r4 = d;
	    }
	  };
	  
	  public void setAlignment(String s){
	    
	    if(s=="CENTER"||s=="center"||s=="Center"){
	      
	      xoff = (w-applet.textWidth(label))/2;
	      yoff = h/3;
	      
	      for(int i=0;i<items.size();i++){
	        Button b = items.get(i);
	        b.tyoff = b.h/3;
	        b.txoff = (b.w-applet.textWidth(b.label))/2-((b.w-applet.textWidth(b.label))/2)/2;
	        if(b.submenu!=null){
	          for(int j=0;j<b.submenu.items.size();j++){
	            Button b2 = b.submenu.items.get(j);
	            if(b2.h>20)b2.tyoff = b2.h/3;
	            b2.txoff = (b2.w-applet.textWidth(b2.label))/2-((b2.w-applet.textWidth(b2.label))/2)/2;
	            //b2.txoff = b2.w-applet.textWidth(b2.label)/2;
	          }
	        }
	      }
	      
	      for(int i=0;i<sliders.size();i++){
	        Slider b = sliders.get(i);
	      }
	    }
	    
	    if(s=="RIGHT"||s=="right"||s=="Right"){
	      
	      xoff = (w-applet.textWidth(label))-((w-applet.textWidth(label)))/4;
	      yoff = h/3;
	      
	      for(int i=0;i<items.size();i++){
	        Button b = items.get(i);
	        b.tyoff = b.h/3;
	        b.txoff = (b.w-applet.textWidth(b.label))-((b.w-applet.textWidth(b.label)))/4;
	        if(b.submenu!=null){
	          for(int j=0;j<b.submenu.items.size();j++){
	            Button b2 = b.submenu.items.get(j);
	            b2.tyoff = b2.h/3;
	            b2.txoff = (b2.w-applet.textWidth(b2.label))-((b2.w-applet.textWidth(b2.label)))/4;
	          }
	        }
	      }
	      
	      for(int i=0;i<sliders.size();i++){
	        Slider b = sliders.get(i);
	      }
	    }
	    
	    if(s=="LEFT"||s=="left"||s=="Left"){
	      
	      xoff = 5;
	      yoff = h/3;
	      
	      for(int i=0;i<items.size();i++){
	        Button b = items.get(i);
	        b.tyoff = b.h/3;
	        b.txoff = 0;
	        if(b.submenu!=null){
	          for(int j=0;j<b.submenu.items.size();j++){
	            Button b2 = b.submenu.items.get(j);
	            b2.tyoff = b2.h/3;
	            b2.txoff = 0;
	          }
	        }
	      }
	      
	      for(int i=0;i<sliders.size();i++){
	        Slider b = sliders.get(i);
	      }
	    }
	  };
	  
	  public void highlight(){
	    
	    if(!pos()){
	      col = Bms.fcol;
	      if(localTheme)col = fcol;
	    }
	    else{
	      col = Bms.hcol;
	      if(localTheme)col = fcol;
	    }
	    if(toggle==1){
	      col = Bms.toggleCol;
	      if(localTheme)col = toggleCol;
	    }
	    if(toggle==1&&pos()){
	      col = applet.color(Bms.fcol,100);
	      if(localTheme)col = applet.color(fcol,100);
	    }
	  };
	  
	  public void highlight(PGraphics canvas){
	    //if(toggle==1||pos(mouse)){
	    //  col = Bms.hcol;
	    //  if(localTheme)col = hcol;
	    //}
	    //else if(!pos(mouse)){
	    //  col = Bms.fcol;
	    //  if(localTheme)col = fcol;
	    //}
	    if(!pos(mouse)){
	      col = Bms.fcol;
	      if(localTheme)col = fcol;
	    }
	    else{
	      col = Bms.hcol;
	      if(localTheme)col = fcol;
	    }
	    if(toggle==1){
	      col = Bms.toggleCol;
	      if(localTheme)col = toggleCol;
	    }
	    if(toggle==1&&pos(mouse)){
	      col = applet.color(Bms.fcol,100);
	      if(localTheme)col = applet.color(fcol,100);
	    }
	  };
	  
	  public void setBms(BMScontrols bms) {
		  Bms = bms;
		  applet = bms.applet;
		  for(int i=0;i<items.size();i++) {
			  Button b = items.get(i);
			  b.Bms = Bms;
			  b.applet = applet;
			  b.initColors();
		  }
		  for(int i=0;i<sliders.size();i++) {
			  Slider b = sliders.get(i);
			  b.Bms = Bms;
			  b.applet = applet;
			  b.initColors();
		  }
	  };
	  
	  public void setTab(tab t) {
		  parentTab = t;
		  applet = t.applet;
		  for(int i=0;i<items.size();i++) {
			  Button b = items.get(i);
			  b.parentTab = t;
			  b.applet = applet;
			  b.initColors();
		  }
		  for(int i=0;i<sliders.size();i++) {
			  Slider b = sliders.get(i);
			  b.parentTab = t;
			  b.applet = applet;
			  b.initColors();
		  }
	  };
	  
	  public void setToggleBar() {
		  toggleBar = true;
		  float bwidth = 0;
		  for (int i =0; i<items.size(); i++) {
		      Button l = items.get(i);

		      if (applet.textWidth(l.label)+20>bwidth)bwidth = applet.textWidth(l.label)+20;
		  }
		  
		  for (int i =0; i<items.size(); i++) {
			  
		      Button a = items.get(i);
		      
		      a.rx = bwidth;
		      a.border = false;
		      a.parent = this;
		      a.togglebar = true;
		      a.radio = false;
		      a.plain = false;
		      a.togglebox = false;
		    }
		    
		    w = bwidth+w;
		    twidth = w;
	  };
	  
	  public void setToggleBox() {
		  toggleBox = true;
		  float bwidth = 0;
		  for (int i =0; i<items.size(); i++) {
		      Button l = items.get(i);

		      if (applet.textWidth(l.label)+20>bwidth)bwidth = applet.textWidth(l.label)+20;
		  }
		  
		  for (int i =0; i<items.size(); i++) {
			  
		      Button a = items.get(i);
		      
		      a.rx = bwidth;
		      a.border = false;
		      a.parent = this;
		      a.togglebar = false;
		      a.radio = false;
		      a.plain = false;
		      a.togglebox = true;
		    }
		    
		    w = bwidth+w;
		    twidth = w;
	  };
	  
	  public void setBorder() {
		  border = true;
	  };
	  
	  public void setAllBorder(boolean k) {
		  border = true;
		  localTheme = true;
		  if(k) {
			  for(int i=0;i<items.size();i++) {
				  Button b = items.get(i);
				  b.border = true;
				  b.localTheme = true;
			  }
			  for(int i=0;i<sliders.size();i++) {
				  Slider b = sliders.get(i);
				  b.border = true;
				  b.localTheme = true;
			  }
		  }else {
			  for(int i=0;i<items.size();i++) {
				  Button b = items.get(i);
				  b.border = false;
				  b.localTheme = true;
			  }
			  for(int i=0;i<sliders.size();i++) {
				  Slider b = sliders.get(i);
				  b.border = false;
				  b.localTheme = true;
			  }
		  }
	  };
	  
	  public void setTransparency(float a) {
		  transparency = a;
		  localTheme = true;
	  };
	  
	  public void setAllTransparency(float a) {
		  transparency = a;
		  localTheme = true;
		  for(int i=0;i<items.size();i++) {
			  Button b = items.get(i);
			  b.transparency = a;
			  b.localTheme = true;
		  }
		  for(int i=0;i<sliders.size();i++) {
			  Slider b = sliders.get(i);
			  b.transparency = a;
			  b.localTheme = true;
		  }
	  };
	  
	  public void setMouse() {
		  if(mouse!=null) {
			  for(int i=0;i<items.size();i++) {
				  Button b = items.get(i);
				  b.mouse = mouse;
			  }
			  for(int i=0;i<sliders.size();i++) {
				  Slider s = sliders.get(i);
				  s.mouse = mouse;
			  }
		  }
		  
	  };
	  
	  public void initColors() {
		  col = Bms.fcol;
		  bcol = Bms.bcol;
		  fcol = Bms.fcol;
		  hcol = Bms.hcol;
		  toggleCol = Bms.toggleCol;
			  
	  };
	  
	  public void setTextColor(int c) {
		  initColors();
		  tcol = c;
		  localTheme = true;
	  };
	  
	  public void setAllTextColor(int c) {
		  tcol = c;
		  localTheme = true;
		  
		  for(int i=0;i<items.size();i++) {
			  Button b = items.get(i);
			  b.initColors();
			  b.localTheme = true;
			  b.tcol = c;
		  }
		  for(int i=0;i<sliders.size();i++) {
			  Slider s = sliders.get(i);
			  s.initColors();
			  s.localTheme = true;
			  s.tcol = c;
		  }
	  };
	  
	  public void setGrid(int i,int j) {
		  cols = i;
		  rows = j;
		  ghSpacing = 0;
		  gvSpacing = 0;
		  grid = true;
	  };
	  
	  public void setGrid(int i,int j,float h,float v) {
		  cols = i;
		  rows = j;
		  ghSpacing = h;
		  gvSpacing = v;
		  grid = true;
	  };
	  
};
