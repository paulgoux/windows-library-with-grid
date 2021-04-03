package windowsGui;

import java.lang.reflect.Field;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Slider{
	  BMScontrols Bms;
	  PApplet applet;
	  public int id = -1,type,functionId;
	  public float x,y,w,h,bx,by,bw,bh,valuex,valuey,btnw,btnh, value = 0,toffx,toffy,spacing = 20,tsize = 12,ssize,
			  temp,startvalue,endvalue,start,end,r1,r2,r3,r4,radius,pieVal,transparency;
	  public String label,parentVar,parentBool,itemLabel;
	  boolean drag,resize,border,fill = true ,toggle,visible = true,horizontal = true,vertical,matrix,
			  classic,pie,radio,square,bar,mdown,mup,Label,right,up,down,left,tvisible = true,update = true,
	          tdown,parentCanvas,mdown1;
	  public boolean localTheme;
	  public int col, col2, col3;
	  public int barcol,col4,tcol,slidercol,hovercol,toggleCol ,sliderbgcol;
	  public String control = "";
	  public Object Link,parentObject;
	  public Menu parent;
	  public tab tooltip,parentTab;
	  public PVector mouse;
	  public TextBox Start = null,End = null,Current = null;
	  public HashMap<String,Boolean> values = new HashMap<String,Boolean>();
	  
	  public Slider(float xx,float yy, float ww, float hh){
	    
	    x = xx;
	    y = yy;
	    w = ww;
	    h = hh;
	    bx = x;
	    by = y;
	    bw = w;
	    bh = h;
	    btnh = h+2;
	    btnw = h;
	    valuex = w/2;
	    valuey = y;
	    classic = true;
	    bar = true;
	    
	    //createOptionsMenu();
	    
	  };
	  
	  public Slider(float xx,float yy, float ww, float hh,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    
		    x = xx;
		    y = yy;
		    w = ww;
		    h = hh;
		    bx = x;
		    by = y;
		    bw = w;
		    bh = h;
		    btnh = h+2;
		    btnw = h;
		    valuex = w/2;
		    valuey = y;
		    //value = 0;
		    //valuex = 0;
		    classic = true;
		    bar = true;
		    
		    //createOptionsMenu();
	    
	  };
		  
	  public Slider(float xx,float yy, float ww, float hh,BMScontrols bms,boolean b1) {
		    Bms = bms;
		    applet = bms.applet;
		    
		    x = xx;
		    y = yy;
		    w = ww;
		    h = hh;
		    bx = x;
		    by = y;
		    bw = w;
		    bh = h;
		    btnh = h+2;
		    btnw = h;
		    valuex = w/2;
		    valuey = y;
		    //value = 0;
		    //valuex = 0;
		    classic = true;
		    bar = true;
		    
		    createOptionsMenu();
			    
	  };
	  
	  public Slider(float xx,float yy, float ww, float hh,String Label){
	    
	    x = xx;
	    y = yy;
	    w = ww;
	    h = hh;
	    bx = x;
	    by = y;
	    bw = w;
	    bh = h;
	    btnh = h+2;
	    btnw = h;
	    label = Label;
	    valuex = w/2;
	    valuey = y;
	    value = 0;
	    classic = true;
	    bar = true;
	    //createOptionsMenu();
	    
	  };
	  
	  public Slider(float xx,float yy, float ww, float hh,String Label,BMScontrols bms) {
		    Bms = bms;
		    applet = bms.applet;
		    
		    x = xx;
		    y = yy;
		    w = ww;
		    h = hh;
		    bx = x;
		    by = y;
		    bw = w;
		    bh = h;
		    btnh = h+2;
		    btnw = h;
		    label = Label;
		    valuex = w/2;
		    valuey = y;
		    value = 0;
		    classic = true;
		    bar = true;
		    createOptionsMenu();
		    initColors();
		  };
	  
	  public void createOptionsMenu(){
	    tooltip = new tab(x,w,50,120,Bms);
	    String[] s1 = {"Reset"};
	    float X = x+w+applet.textWidth("0.000")+20;
	    Menu options = new Menu(0,0,80,s1,0,Bms);
	    tooltip.w = options.w;
	    tooltip.add(options);
	    TextBox t1 = new TextBox(0,0+30,68,20,20,"Start",Bms);
	    tooltip.add(t1);
	    t1.w = options.w;
	    t1 = new TextBox(0,0+60,68,20,20,"End",Bms);
	    t1.w = options.w;
	    tooltip.add(t1);
	    t1 = new TextBox(0,0+90,68,20,20,"Value",Bms);
	    t1.w = options.w;
	    tooltip.textboxes.add(t1);
	    Start = tooltip.textboxes.get(0);
	    End = tooltip.textboxes.get(1);
	    tooltip.h = 90+20;
	    
	  };
	  
	  public void updateOptionsMenu(){
	    //if(!tooltip.drag&&!dragged){
	    tooltip.x = x+w+applet.textWidth("0.000")+20;
	    Menu options = tooltip.menus.get(0);
	    
	    
	    options.x = 0;
	    options.y = 0;
	    for(int i=0;i<tooltip.menus.get(0).items.size();i++){
	      Button b = tooltip.menus.get(0).items.get(i);
	      b.x = 0;
	      b.y = 0 + 20 * i;
	    }
	    TextBox t1 = tooltip.textboxes.get(0);
	    t1.y = 0+30;
	    t1.x = 0;
	    t1 = tooltip.textboxes.get(1);
	    t1.y = 0+60;
	    t1.x = 0;
	    t1 = tooltip.textboxes.get(2);
	    t1.y = 0+90;
	    t1.x = 0;
	    Start = tooltip.textboxes.get(0);
	    End = tooltip.textboxes.get(1);
	    
	    if(tooltip.y+tooltip.h>applet.height)tooltip.draggable = true;
	    else tooltip.draggable = false;
	    
	  };
	  
	  public void updateOptionsMenu(PGraphics canvas){
	    //if(!tooltip.drag&&!dragged){
	    tooltip.x = x+w+applet.textWidth("0.000")+20;
	    tooltip.y = y;
	    Menu options = tooltip.menus.get(0);
	    
	    options.x = 0;
	    options.y = 0;
	    for(int i=0;i<tooltip.menus.get(0).items.size();i++){
	      Button b = tooltip.menus.get(0).items.get(i);
	      b.x = 0;
	      b.y = 0 + 20 * i;
	    }
	    TextBox t1 = tooltip.textboxes.get(0);
	    t1.y = 0+30;
	    t1.x = 0;
	    t1 = tooltip.textboxes.get(1);
	    t1.y = 0+60;
	    t1.x = 0;
	    t1 = tooltip.textboxes.get(2);
	    t1.y = 0+90;
	    t1.x = 0;
	    Start = tooltip.textboxes.get(0);
	    End = tooltip.textboxes.get(1);
	    
	    if(tooltip.y+tooltip.h>applet.height)tooltip.draggable = true;
	    else tooltip.draggable = false;
	    
	  };
	  
	  public void initColors() {
		  
		  barcol = applet.color(0, 255, 73);
		  col4 = applet.color(0,50);
		  tcol = applet.color(255);
		  slidercol = applet.color(255);
		  hovercol = Bms.hcol;
		  toggleCol = applet.color(50,0);
		  sliderbgcol = applet.color(255);
	  };
	  
	  public void draw(){
	    functions();
	    applet.fill(255);
	     
	    if(tooltip!=null){
	      if(tooltipPos()){
	        applet.noStroke();
	        //if(stroke)applet.stroke(0);
	        applet.fill(Bms.col);
	        if(localTheme)applet.fill(barcol);
	        String k = "0.0000";
	        applet.rect(x+w+applet.textWidth(k),y,20,h);
	      }
	      if(tooltipPos()&&!mdown&&applet.mousePressed){
	        
	        tooltip.x = x+w+applet.textWidth("0.000")+20;
	        tooltip.y = y;
	        tooltip.trace();
	        
	        //important
	        tooltip.toggle = true;
	        tooltip.visible = true;
	        mdown = true;
	        tdown = true;
	      }
	       if(!tooltip.draggable){
	       if((!tooltip.posTab()&&!mdown&&tdown&&applet.mousePressed)){
	        
	         tooltip.toggle = false;
	         tooltip.visible = false;
	         mdown = true;
	         tdown = false;
	         //cursor(ARROW);
	       }}else 
	      if((!tooltip.posTabd()&&!mdown&&tdown&&applet.mousePressed)&&!tooltip.posTab()){
	        
	        tooltip.toggle = false;
	        tooltip.visible = false;
	        mdown = true;
	        tdown = false;
	        //cursor(ARROW);
	      }
	      if(!applet.mousePressed)mdown = false;
	      if(tdown){
	        updateOptionsMenu();
	        tooltip.displayTab(); 
	        
	        if(tooltip.menus.get(0).items.get(0).pos(new PVector(applet.mouseX-tooltip.x,applet.mouseY-tooltip.y))&&applet.mousePressed){
	          tooltip.menus.get(0).items.get(0).toggle=1;
	          resetControl();
	          valuex = w/2;
	          value = 0;
	          //cursor(ARROW);
	          tdown = false;
	      }
	      //if(!tdown)cursor(ARROW);
	      TextBox t = tooltip.textboxes.get(2);
	      
	      if(t.textBox!=""&&Float.parseFloat(t.textBox)>-99999999&&applet.keyPressed&&applet.key==PConstants.ENTER){
	        if(parent!=null){

	            valuex = t.value;
	            switch(functionId){
	              
	              case(0):set(startvalue,endvalue,parentObject, parentVar);
	              break;
	              case(1):set(startvalue,endvalue,parentObject, parentVar,parentBool);
	              break;
	              case(2):setint((int)startvalue,(int)endvalue,parentObject, parentVar);
	              break;
	              case(3):setint((int)startvalue,(int)endvalue);
	              break;
	            }
	          }
	        setControl(t.value);
	      }}

	    if(!applet.mousePressed){
	      mdown = false;
	      
	      for(int i=0;i<tooltip.menus.get(0).items.size();i++){
	        Button b = tooltip.menus.get(0).items.get(i);
	        
	        b.toggle=0;
	      }}}
	  };

	  public void draw(PGraphics canvas){
	    functions(canvas);
	    //if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
	    //classicBar(canvas);
	    //canvas.fill(0,255,0);
	    //if (parentTab!=null)mouse = new PVector(applet.mouseX-parentTab.x,applet.mouseY-parentTab.y);
	    //else applet.println("no tab");
	    //canvas.ellipse(m.x,m.y,20,20);
	    if(tooltip!=null){
	      if(tooltipPos(mouse)){
	        canvas.noStroke();
	        //if(stroke)applet.stroke(0);
	        canvas.fill(Bms.col);
	        if(localTheme)canvas.fill(barcol);
	        String k = "0.0000";
	        PVector m = tooltip.getMouse(mouse);
	        //canvas.rect(x+w+applet.textWidth(k),y,20,h);
	      }
	      if(tooltip!=null&&tooltipPos(mouse)&&!mdown&&!tdown&&applet.mousePressed){
	        
	        tooltip.x = x+w+applet.textWidth("0.000")+20;
	        tooltip.y = y;
	        //important
	        tooltip.toggle=true;
	        tooltip.visible = true;
	        mdown = true;
	        tdown = true;
	      }
	      if(tooltip!=null&&!tooltip.draggable){

	      if((!tooltip.posTab(mouse)&&!mdown&&tdown&&applet.mousePressed)||pos(mouse)&&applet.mousePressed){
	        
	        tooltip.toggle=false;
	        tooltip.visible = false;
	        mdown = true;
	        tdown = false;
	        //cursor(ARROW);
	      }}else if((tooltip!=null&&!tooltip.posTabd(mouse)&&!mdown&&tdown&&applet.mousePressed)||pos(mouse)&&applet.mousePressed){
	        
	        tooltip.toggle=false;
	        tooltip.visible = false;
	        mdown = true;
	        tdown = false;
	        //cursor(ARROW);
	      }
	      
	      if(tdown&&!mdown){
	        updateOptionsMenu(canvas);
	        tooltip.mouse2 = mouse;
	        if(parentTab!=null)tooltip.parentTab = parentTab;
	        tooltip.displayTab(canvas); 
	        applet.fill(255);
	        
	        if(tooltip.menus.get(0).items.get(0).pos(new PVector(mouse.x-tooltip.x,mouse.y-tooltip.y))&&applet.mousePressed){
	          tooltip.menus.get(0).items.get(0).toggle=1;
	          resetControl();
	          valuex = w/2;
	          value = 0;
	          
	          
	          //cursor(ARROW);
	      }
	      //else if(!tdown)cursor(ARROW);
	      TextBox t = tooltip.textboxes.get(2);
	      
	      if(t.textBox!=""&&Float.parseFloat(t.textBox)>-99999999&&applet.keyPressed&&applet.key==PConstants.ENTER){
	        //update = true;
	        if(parent!=null){

	            // for(int i=0;i<5;i++;){
	            //   if(functionId==i){
	            //     set()
	            //   }
	            // }
	            valuex = t.value;
	            switch(functionId){
	              case(0):set(startvalue,endvalue,parentObject, parentVar);
	              //applet.println(startvalue,endvalue,parentObject, parentVar);
	              break;
	              case(1):set(startvalue,endvalue,parentObject, parentVar,parentBool);
	              break;
	              case(2):setint((int)startvalue,(int)endvalue,parentObject, parentVar);
	              break;
	              case(3):setint((int)startvalue,(int)endvalue);
	              break;
	            }
	          }
	      }
	      //applet.println(valuex);
	        //setControl(t.value);
	        //update = false;
	      }
	      if(!applet.mousePressed){
	        mdown = false;
	        if(tooltip!=null)
	        for(int i=0;i<tooltip.menus.get(0).items.size();i++){
	          Button b = tooltip.menus.get(0).items.get(i);
	          
	          b.toggle=0;
	    }}}
	  };
	  
	  public void set(String a){
	    if(a=="vertical"||a=="Vertical"||a=="VERTICAL"){}
	  };
	  
	  
	  public void resetControl(){
	    Field field = null;

	    try{
	         field = Link.getClass().getField("sUpdate"); 
	         //field.set(Link, "sUpdate");
	         field.set(Link, true); 
	         //applet.println(field.get(Link),"update",control,Link);
	    }catch (NullPointerException e) {
	      PApplet.println("null slider");
	    }catch (NoSuchFieldException e) {
	      PApplet.println("no field: update");
	    }catch (IllegalAccessException e) {
	    } 
	            
	    try{
	         field = Link.getClass().getField(control); 
	         field.set(Link, 0); 
	         //applet.println(valuex);
	    }catch (NullPointerException e) {
	      PApplet.println("null p slider");
	    }catch (NoSuchFieldException e) {
	      PApplet.println("no field");
	    }catch (IllegalAccessException e) {
	    } 
	  };
	  
	  public void setControl(float a){
	    value = a;
	    valuex = PApplet.map( value,startvalue,endvalue, 0+1, w-1);
	            Field field = null;
	    try{
	         field = Link.getClass().getField("sUpdate"); 
	         //field.set(Link, "sUpdate");
	         field.set(Link, true); 
	         //applet.println(field.get(Link),"update",control,Link);
	    }catch (NullPointerException e) {
	      PApplet.println("s control; null");
	    }catch (NoSuchFieldException e) {
	      PApplet.println("s control; no field: update");
	    }catch (IllegalAccessException e) {
	    } 
	    try{
	         field = Link.getClass().getField(control); 
	         //field.set(Link, "update");
	         field.set(Link, a); 
	         //applet.println(field.get(Link),valuex,control,Link);
	    }catch (NullPointerException e) {
	      PApplet.println("s control; null");
	    }catch (NoSuchFieldException e) {
	      PApplet.println("s control; no field");
	    }catch (IllegalAccessException e) {
	    } 
	  };
	  
	  public void setStart(float a){
	            Field field = null;
	    try{
	         field = Link.getClass().getField(control); 
	         field.set(Link, a); 
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    } 
	  };
	  
	  public void setEnd(float a){
	            Field field = null;
	    try{
	         field = Link.getClass().getField(control); 
	         field.set(Link, a); 
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    } 
	  };
	  
	  public void functions(){
	    applet.stroke(col);
	    if(!border){
	     applet.noStroke(); 
	    }
	    if(classic){
	      if(square)classicSquare();
	      else if(radio)classicRadio();
	      else if(bar)classicBar();
	    }else if(matrix){
	      if(square)Matrix();
	      else if(radio){}
	      else if(bar){}
	    }else if(pie){
	      if(square)pieSquare();
	      else if(radio)pieRadio();
	      else if(bar)pieBar();
	    }
	  };
	  
	  public void functions(PGraphics canvas){
	    if(visible){
	    canvas.stroke(col);
	    if(!border){
	     canvas.noStroke(); 
	    }
	    if(classic){
	      if(square)classicSquare(canvas);
	      else if(radio)classicRadio(canvas);
	      else if(bar)classicBar(canvas);
	    }else if(matrix){
	      if(square)Matrix();
	      else if(radio){}
	      else if(bar){}
	    }else if(pie){
	      if(square)pieSquare(canvas);
	      else if(radio)pieRadio();
	      else if(bar)pieBar();
	    }}
	  };
	  
	 public void classicSquare(){
	    //----------slider bg-----------
	    applet.fill(slidercol);
	    if(vertical)applet.rect(x,y,w,h,r1,r2,r3,r4);
	    else applet.rect(x,y,w,h,r1,r2,r3,r4);
	    
	    if(pos())applet.fill(Bms.hcol);if(!fill)applet.noFill();
	    
	    if(vertical)applet.rect(x,y,w,h,r1,r2,r3,r4);
	    else applet.rect(x,y,w,h,r1,r2,r3,r4);
	    
	    applet.textSize(tsize);
	    applet.strokeWeight(ssize);
	    applet.stroke(0);
	    if(parent==null){
	    if(btnpos()||pos()||mdown)applet.fill(col4);btnh = h+10;
	    }
	    
	      if(label!=null){
	        applet.fill(tcol);
	        if(vertical)applet.text(label,x,y-tsize);
	        else applet.text(label,x-applet.textWidth(label),y+h);
	      }
	      if(!vertical)btnh = h;
	      applet.noStroke();
	      
	      applet.fill(barcol);
	      
	      if(vertical)applet.rect(x,y+valuex,btnw-2,btnw-2,r1,r2,r3,r4);
	      else  applet.rect(x + valuex,y,btnw,btnh,r1,r2,r3,r4);
	      applet.fill(255);
	      
	      //slider value-------------------
	      applet.rectMode(PConstants.CORNER);
	      applet.fill(Bms.col);
	      if(vertical)applet.rect(x,y+valuex,btnw-2,btnw-2,r1,r2,r3,r4);
	      else applet.rect(x+valuex,y,btnw,btnh,r1,r2,r3,r4);
	      
	      
	      
	      applet.fill(tcol);
	      if(vertical){
	        applet.pushMatrix();
	        applet.translate(x+w+toffx,y+h+toffy);
	        applet.rotate(PConstants.PI/2);
	        
	        applet.translate(-(x+w+toffx),-(y+h+toffy-w));
	        applet.text(value,x+w+toffx,y+h+toffy);
	        applet.popMatrix();
	      }else applet.text(value,x+w+toffx,y+h+toffy);
	      
	  };
	  
	  public void classicSquare(PGraphics canvas){
	    //----------slider bg-----------
	    canvas.fill(slidercol);
	    if(vertical)canvas.rect(x,y,w,h,r1,r2,r3,r4);
	    else canvas.rect(x,y,w,h,r1,r2,r3,r4);
	    
	    if(pos(mouse))canvas.fill(Bms.hcol);if(!fill)canvas.noFill();
	    
	    if(vertical)canvas.rect(x,y,w,h,r1,r2,r3,r4);
	    else canvas.rect(x,y,w,h,r1,r2,r3,r4);
	    
	    canvas.textSize(tsize);
	    canvas.strokeWeight(ssize);
	    canvas.stroke(0);
	    if(parent==null){
		    if(btnpos(mouse)||pos(mouse)||mdown) {
		    	canvas.fill(col4);
		    	btnh = h+10;
		    }
	    }
	    
	      if(label!=null){
	        canvas.fill(tcol);
	        if(vertical)canvas.text(label,x,y-tsize);
	        else canvas.text(label,x-applet.textWidth(label),y+h);
	      }
	      if(!vertical)btnh = h;
	      canvas.noStroke();
	      
	      canvas.fill(barcol);
	      //canvas.fill(Bms.col);
	      if(pos(mouse))canvas.fill(Bms.hcol);
	      if(vertical)canvas.rect(x,y+valuex,btnw-2,btnw-2,r1,r2,r3,r4);
	      else  canvas.rect(x + valuex,y,btnw,btnh,r1,r2,r3,r4);
	      canvas.fill(255);
	      
	      //slider value-------------------
	      canvas.rectMode(PConstants.CORNER);
	      canvas.fill(Bms.fcol);
	      if(vertical)canvas.rect(x,y+valuex,btnw-2,btnw-2,r1,r2,r3,r4);
	      else canvas.rect(x+valuex,y,btnw,btnh,r1,r2,r3,r4);
	      
	      canvas.fill(tcol);
	      if(vertical){
	        canvas.pushMatrix();
	        canvas.translate(x+w+toffx,y+h+toffy);
	        canvas.rotate(PConstants.PI/2);
	        
	        canvas.translate(-(x+w+toffx),-(y+h+toffy-w));
	        canvas.text(value,x+w+toffx,y+h+toffy);
	        canvas.popMatrix();
	      }else canvas.text(value,x+w+toffx,y+h+toffy);
	      
	  };
	  
	  public void classicRadio(){
	    //----------slider bg-----------
	    applet.fill(slidercol);
	    if(vertical)applet.rect(x+w/2-2,y+valuex,4,h-valuex,r1,r2,r3,r4);
	    else applet.rect(x,y+h/2-2,w,4,r1,r2,r3,r4);
	    
	    if(pos())applet.fill(hovercol);if(!fill)applet.noFill();
	    
	    if(vertical)applet.rect(x+w/2-2,y+valuex,4,h-valuex,r1,r2,r3,r4);
	    else applet.rect(x,y+h/2-2,w,4,r1,r2,r3,r4);
	    
	    applet.textSize(tsize);
	    applet.strokeWeight(ssize);
	    applet.stroke(0);
	    if(parent==null){
	    if(btnpos()||pos()||mdown)applet.fill(col4);btnh = h+10;
	    }
	    
	      if(label!=null){
	        applet.fill(tcol);
	        if(vertical)applet.text(label,x,y-tsize);
	        else applet.text(label,x-applet.textWidth(label),y+h);
	      }
	      if(!vertical)btnh = h;
	      applet.noStroke();
	      applet.fill(255);
	      
	      //slider value-------------------
	      applet.ellipseMode(PConstants.CORNER);
	      //applet.text(valuex , 500,500 + 10*id);
	      if(vertical)applet.ellipse(x,y+valuex,btnw-2,btnw-2);
	      else applet.ellipse(x+valuex,y,btnw-2,btnh-2);
	      
	      applet.fill(barcol);
	      
	      if(vertical)applet.ellipse(x,y+valuex,btnw-2,btnw-2);
	      else  applet.ellipse(x + valuex,y,btnw,btnh);
	      
	      applet.fill(tcol);
	      if(vertical){
	        applet.pushMatrix();
	        applet.translate(x+w+toffx,y+h+toffy);
	        applet.rotate(PConstants.PI/2);
	        
	        applet.translate(-(x+w+toffx),-(y+h+toffy-w));
	        applet.text(value,x+w+toffx,y+h+toffy);
	        applet.popMatrix();
	      }else applet.text(value,x+w+toffx,y+h+toffy);
	      
	  };
	  
	  public void classicRadio(PGraphics canvas){
	    //----------slider bg-----------
	    canvas.fill(slidercol);
	    if(vertical)canvas.rect(x+w/2-2,y+valuex,4,h-valuex,r1,r2,r3,r4);
	    else canvas.rect(x,y+h/2-2,w,4,r1,r2,r3,r4);
	    
	    if(pos(mouse))canvas.fill(hovercol);if(!fill)canvas.noFill();
	    
	    if(vertical)canvas.rect(x+w/2-2,y+valuex,4,h-valuex,r1,r2,r3,r4);
	    else canvas.rect(x,y+h/2-2,w,4,r1,r2,r3,r4);
	    
	    canvas.textSize(tsize);
	    canvas.strokeWeight(ssize);
	    canvas.stroke(0);
	    if(parent==null){
	    if(btnpos(mouse)||pos(mouse)||mdown)canvas.fill(col4);btnh = h+10;
	    }
	    
	      if(label!=null){
	        canvas.fill(tcol);
	        if(vertical)canvas.text(label,x,y-tsize);
	        else canvas.text(label,x-applet.textWidth(label),y+h-5);
	      }
	      if(!vertical)btnh = h;
	      canvas.noStroke();
	      canvas.fill(255);
	      
	      //slider value-------------------
	      canvas.ellipseMode(PConstants.CORNER);
	      //applet.text(valuex , 500,500 + 10*id);
	      if(vertical)canvas.ellipse(x,y+valuex,btnw-2,btnw-2);
	      else canvas.ellipse(x+valuex,y,btnw,btnh);
	      
	      canvas.fill(barcol);
	      
	      if(vertical)canvas.ellipse(x,y+valuex,btnw-2,btnw-2);
	      else  canvas.ellipse(x + valuex,y,btnw,btnh);
	      
	      canvas.fill(tcol);
	      if(vertical){
	        canvas.pushMatrix();
	        canvas.translate(x+w+toffx,y+h+toffy);
	        canvas.rotate(PConstants.PI/2);
	        
	        canvas.translate(-(x+w+toffx),-(y+h+toffy-w));
	        canvas.text(value,x+w+toffx,y+h+toffy);
	        canvas.popMatrix();
	      }else canvas.text(value,x+w+toffx,y+h+toffy-5);
	      
	  };
	  
	  public void classicBar(){
	    //----------slider bg-----------
	    applet.fill(slidercol);
	    if(vertical)applet.rect(x,y+valuex,w,h-valuex,r1,r2,r3,r4);
	    else applet.rect(x+valuex,y,w-valuex,h,r1,r2,r3,r4);
	    
	    if(pos())applet.fill(hovercol);if(!fill)applet.noFill();
	    
	    if(vertical)applet.rect(x,y+valuex,w,h-valuex,r1,r2,r3,r4);
	    else applet.rect(x + valuex,y,w-valuex,h,r1,r2,r3,r4);
	    
	    applet.textSize(tsize);
	    applet.strokeWeight(ssize);
	    applet.stroke(0);
	    if(parent==null){
	    if(btnpos()||pos()||mdown)applet.fill(col4);btnh = h+10;
	    }
	    
	      if(label!=null){
	        applet.fill(Bms.tcol);
	        if(localTheme)applet.fill(tcol);
	        if(vertical)applet.text(label,x,y-tsize);
	        else applet.text(label,x-applet.textWidth(label),y+h-5);
	      }
	      if(!vertical)btnh = h;
	      applet.noStroke();
	      applet.fill(255);
	      
	      //slider value-------------------
	      if(vertical)applet.rect(x,y,w,valuex,r1,r2,r3,r4);
	      else applet.rect(x,y,valuex,btnh,r1,r2,r3,r4);
	      applet.fill(barcol);
	      if(vertical)applet.rect(x,y,w,valuex,r1,r2,r3,r4);
	      else applet.rect(x,y,valuex,btnh,r1,r2,r3,r4);
	      applet.fill(Bms.tcol);
	      if(localTheme)applet.fill(tcol);
	      if(vertical){
	        applet.pushMatrix();
	        applet.translate(x+w+toffx,y+h+toffy);
	        applet.rotate(PConstants.PI/2);
	        
	        applet.translate(-(x+w+toffx),-(y+h+toffy-w));
	        if(tvisible)applet.text(value,x+w+toffx,y+h+toffy);
	        applet.popMatrix();
	      }else if(tvisible)applet.text(value,x+w+toffx,y+h+toffy-5);
	      
	   
	  };
	  
	  public void classicBar(PGraphics canvas){
	    canvas.fill(255);
	    if(mouse==null){
	      if(parentTab!=null)PApplet.println("cb","parent tab");
	      else PApplet.println("cb","no parent tab");
	      mouse = new PVector(applet.mouseX,applet.mouseY);
	    }

	    //----------slider bg-----------
	    canvas.fill(slidercol);
	    if(vertical)canvas.rect(x,y+valuex,w,h-valuex,r1,r2,r3,r4);
	    else canvas.rect(x,y,w,h,r1,r2,r3,r4);
	    
	    if(pos(mouse))canvas.fill(hovercol);if(!fill)canvas.noFill();
	    
	    if(vertical)canvas.rect(x,y+valuex,w,h-valuex,0,r2,r3,0);
	    else canvas.rect(x ,y,w,h,r1,r2,r3,r4);
	    
	    canvas.textSize(tsize);
	    canvas.strokeWeight(ssize);
	    canvas.stroke(0);
	    if(parent==null){
	    if(btnpos(mouse)||pos(mouse)||mdown)canvas.fill(col4);btnh = h+10;
	    }
	    
	      if(label!=null){
	        canvas.fill(tcol);
	        if(vertical)canvas.text(label,x,y-tsize);
	        else canvas.text(label,x-canvas.textWidth(label),y+h-5);
	      }
	      if(!vertical)btnh = h;
	      canvas.noStroke();
	      canvas.fill(255);
	      //slider value-------------------
	      //if(vertical)canvas.rect(x,y,w,valuex,r1,0,r3,r4);
	      //else canvas.rect(x,y,valuex,btnh,r1,0,r3,r4);
	      canvas.fill(barcol);
	      if(vertical)canvas.rect(x,y,w,valuex,r1,r2,r3,r4);
	      else canvas.rect(x,y,valuex,btnh,r1,r2,r3,r4);
	      canvas.fill(tcol);
	      if(vertical){
	        canvas.pushMatrix();
	        canvas.translate(x+w+toffx,y+h+toffy);
	        canvas.rotate(PConstants.PI/2);
	        
	        canvas.translate(-(x+w+toffx),-(y+h+toffy-w));
	        if(tvisible)canvas.text(value,x+w+toffx,y+h+toffy);
	        canvas.popMatrix();
	      }else if(tvisible)canvas.text(value,x+w+toffx,y+h+toffy-5);
	      
	   
	  };
	  
	  public void Matrix(){
	    if(!fill){
	      applet.noFill();
	    }
	    applet.fill(col2);
	    applet.rect(x,y,w,h);
	    
	  };
	  
	  public void pieSquare(){
	    float val = 0;
	    float v1 = 10;
	    boolean k =PApplet.dist(applet.mouseX,applet.mouseY,x,y)<radius;
	    if(applet.mousePressed&&k&&!mdown1&&Bms.sliderObject ==null){
	      mdown1 = true;
	      Bms.sliderObject = this;
	    }
	    //if(mdown)val = applet.map(applet.mouseX,0,width,0,2*applet.PI);
	    if(mdown1)pieVal = PApplet.abs(2*PConstants.PI-(PApplet.atan2(x-applet.mouseX,y-applet.mouseY)+PConstants.PI/2));
	    if(pieVal>2*PConstants.PI)pieVal -=PConstants.PI*2;
	    
	    applet.fill(255);
	    if(k)
	    applet.fill(Bms.fcol);
	    applet.ellipseMode(PConstants.CENTER);
	    applet.ellipse( x,y,radius*2-v1,radius*2-v1);
	    applet.fill(255);
	    applet.ellipse( x,y,radius*2-v1,radius*2-v1);
	    applet.fill(Bms.fcol);
	    if(k)
	    applet.fill(Bms.hcol);
	    applet.arc(x,y, radius*2, radius*2, 0, pieVal, PConstants.PIE);
	    applet.fill(255);
	    applet.ellipse( x,y,radius*2-25,radius*2-25);
	    applet.fill(Bms.hcol);
	    if(k)
	    applet.fill(Bms.fcol);
	    applet.ellipse( x,y,radius*2-25,radius*2-25);
	    applet.fill(Bms.tcol);
	    		
	    if(mdown1) {
	    	value = PApplet.map(pieVal,0,2*PConstants.PI,startvalue,endvalue);
	    }
	    applet.text(value,x-40,y+h-40);
	    if(!applet.mousePressed){
	      mdown1 = false;
	      if(Bms.sliderObject == this)Bms.sliderObject = null;
	    }
	  };
	  
	  public void pieSquare(PGraphics canvas){
	    float v1 = 10;
//	    pieLogic(mouse);
	    canvas.fill(0);
	    boolean k = parentTab.posTab()&&parentTab.visible;
	    boolean k1 = PApplet.dist(mouse.x,mouse.y,x,y)<radius;
	    if(k&&applet.mousePressed&&k1&&!mdown1&&Bms.sliderObject ==null){
	      mdown1 = true;
	      Bms.sliderObject = this;
	    }
	    if(mdown1)pieVal = PApplet.abs(2*PConstants.PI-(PApplet.atan2(x-mouse.x,y-mouse.y)+PConstants.PI/2));
	    if(pieVal>2*PConstants.PI)pieVal -=PConstants.PI*2;
	    canvas.text(label,x - applet.textWidth(label)/2, y - radius);
	    canvas.ellipseMode(PConstants.CENTER);
	    canvas.fill(255);
	    if(k&&(k1||mdown1))
	    canvas.fill(Bms.fcol);
	    canvas.ellipse( x,y,radius*2-v1,radius*2-v1);
	    canvas.fill(255);
	    canvas.ellipse( x,y,radius*2-v1,radius*2-v1);
	    canvas.fill(Bms.fcol);
	    if(k&&(k1||mdown1))
	    canvas.fill(Bms.hcol);
	    canvas.arc(x,y, radius*2, radius*2, 0, pieVal, PConstants.PIE);
	    canvas.fill(255);
	    canvas.ellipse( x,y,radius*2-25,radius*2-25);
	    canvas.fill(Bms.hcol);
	    if(k&&(k1||mdown1))
	    canvas.fill(Bms.fcol);
	    canvas.ellipse( x,y,radius*2-25,radius*2-25);
	    canvas.stroke(255);
	    canvas.fill(Bms.tcol);
	    canvas.fill(0);
	    if(mdown1) {
	    	value = PApplet.map(pieVal,0,2*PConstants.PI,startvalue,endvalue);
	    }
	    canvas.text(value,x-20,y+h-h/2+10);
	    if(mdown1&&!applet.mousePressed){
	      mdown1 = false;
	      if(Bms.sliderObject == this)Bms.sliderObject = null;
	    }
	  };
	  
	  public void pieLogic(){
	    
	  };
	  
	  public void pieLogic(PVector mouse){
	    if(PApplet.dist(mouse.x,mouse.y,x,y)<radius&&applet.mousePressed&&!mdown1&&Bms.sliderObject ==null){
	      mdown1 = true;
	      Bms.sliderObject = this;
	      
	    }
	    if(PApplet.dist(mouse.x,mouse.y,x,y)>radius&&applet.mousePressed&&!mdown1&&Bms.sliderObject ==this){
	      Bms.sliderObject = null;
	      
	    }
	    if(mdown1&&Bms.sliderObject==this){
	      pieVal = PApplet.abs(2*PConstants.PI-(PApplet.atan2(x-mouse.x,y-mouse.y)+PConstants.PI/2));
	      if(pieVal>2*PConstants.PI)pieVal -=PConstants.PI*2;
	      value = PApplet.map(pieVal,0,2*PConstants.PI,startvalue,endvalue);
	    }
	  };
	  
	  public void pieRadio(){
	    if(!fill){
	      applet.noFill();
	    }
	    applet.fill(col2);
	    applet.ellipseMode(PConstants.CENTER);
	    applet.ellipse(x,y,w,h);
	    applet.arc(x,y,w,h,valuex,valuey);
	    applet.arc(x+10,y+10,w,h,valuex,valuey);
	    applet.fill(col3);
	    applet.ellipseMode(PConstants.CENTER);
	    if(!horizontal){
	      applet.ellipse(x+valuex,y,btnw,btnh);
	    }else{
	      applet.ellipse(x,y + valuey,10,btnh);
	    }
	  };
	  public void pieBar(){
	    if(!fill){
	      applet.noFill();
	    }
	    applet.fill(col2);
	    applet.ellipseMode(PConstants.CENTER);
	    applet.ellipse(x,y,w,h);
	    applet.arc(x,y,w,h,valuex,valuey);
	    applet.arc(x+10,y+10,w,h,valuex,valuey);
	    //line();
	  };
	  
	  public void mouseFunctions(){
	    if(pos()&&applet.mousePressed&&Bms.sliderObject==null){
	      Bms.sliderObject = this;
	      mup = false;
	      mdown = true;
	      toggle = true;
	    }
	    
	    if(mdown&&!tdown&&!parentCanvas&&Bms.sliderObject==this){
	      if(bar){
	        if(vertical){
	        if(applet.mouseY>y&&applet.mouseY<y + h-1)valuex = applet.mouseY-y;
	        if(applet.mouseY>y+h-1)valuex = h-1;
	        }else{
	          if(applet.mouseX>x&&applet.mouseX<x + w-1)valuex = applet.mouseX-x;
	          if(applet.mouseX>x+w-1)valuex = w-1;
	        }}
	      else if(radio||square){
	        if(vertical){
	        	if(applet.mouseY>y&&applet.mouseY<y + h-1)valuex = applet.mouseY-y-1;
	          if(applet.mouseY>y+h-btnw)valuex = h-btnw;
	        }else{
	        	if(applet.mouseX>x&&applet.mouseX<x + w-1)valuex = applet.mouseX-x;
	          if(applet.mouseX>x+w-btnw)valuex = w-btnw;
	        }}
	      }
	    if(mdown&&!applet.mousePressed&&!tdown&&Bms.sliderObject==null){
	      Bms.sliderObject = this;
	      mdown = false;
	      toggle = false;
	    }
	    if(mdown&&!applet.mousePressed&&Bms.sliderObject==this){
	      Bms.sliderObject = null;
	      mdown = false;
	    }
	  };
	  
	  public void mouseFunctions(PVector m){
	    // if(mouse==null){
	      //  if(parentTab!=null)applet.println("mf","parent tab");
	      //  else applet.println("mf","no parent tab");
	    //   mouse = new PVector(applet.mouseX,applet.mouseY);
	    // }
	    // applet.fill(255,0,0);
	    // applet.ellipse(m.x,m.y,20,20);
	    if(!pos(mouse)&&applet.mousePressed&&!mdown&&Bms.sliderObject==this){
	      //Bms.sliderObject = null;
	      //mdown = true;
	      //applet.println(parentTab.x,parentTab.y,applet.mouseX,mouse.x,applet.mouseY,mouse.y);
	    }
	    if(pos(mouse)&&applet.mousePressed&&!tdown&&Bms.sliderObject==null){
	      Bms.sliderObject = this;
	      mdown = true;
	      //applet.println(parentTab.x,parentTab.y,applet.mouseX,mouse.x,applet.mouseY,mouse.y);
	    }
	    
	    if(mdown){
	      if(bar){
	        if(vertical){
	          if(m.y>y&&m.y<y + h-1)valuex = m.y-y;
	          if(m.y>y+h-1)valuex = h-1;
	        }else{
	          if(m.x>x&&m.x<x + w-1)valuex = m.x-x;
	          if(m.x>x+w-1)valuex = w-1;
	        }}
	      else if(radio||square){
	        if(vertical){
	          if(m.y>y-1&&m.y<y + h-1&&m.x>x && m.x < x + w)valuex = m.y-y-1;
	          if(m.y>y+h-btnw)valuex = h-btnw;
	          
	        }else{
	          if(m.x>x-1&&m.x<x + w-btnw && m.y>y && m.y < y + h)valuex = m.x-x;
	          if(m.x>x+w-btnw)valuex = w-btnw;
	        }}}
	    if(mdown&&!applet.mousePressed){
	      if(Bms.sliderObject==this)Bms.sliderObject = null;
	      mdown = false;
	    }
	  };
	  
	  boolean pos(){
	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	  }
	  
	  boolean pos(PVector m){
	    return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+h;
	    // else 
	    //return applet.mouseX-parentTab.x>x&&applet.mouseX-parentTab.x<x+w&&applet.mouseY-parentTab.y>y&&applet.mouseY-parentTab.y<y+h;
	    //return false;
	  }
	  
	  boolean btnpos(){
	    
	    if(!horizontal){
	    return applet.mouseX>x + valuex &&applet.mouseX<x+valuex+btnw&&applet.mouseY>y&&applet.mouseY<y+h;
	    
	    }
	    else{
	      return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y+ valuey&&applet.mouseY<y + valuey +btnh;
	    }
	  };
	  
	  boolean btnpos(PVector m){
	    
	    if(!horizontal){
	    return m.x>x + valuex &&m.x<x+valuex+btnw&&m.y>y&&m.y<y+h;
	    
	    }
	    else{
	      return m!=null&&m.x>x&&m.x<x+w&&m.y>y+ valuey&&m.y<y + valuey;
	    }
	  };
	  
	  boolean tooltipPos(){
	    return applet.mouseX>x+w+applet.textWidth("0.0000")&&applet.mouseX<x+w+applet.textWidth("0.0000")+20&&applet.mouseY>y&&applet.mouseY<y+h;
	  };
	  
	  boolean tooltipMenuPos(){
	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	  };
	  
	  boolean tooltipPos(PVector m){
		if(pie)return m!=null&&m.x>x+w&&m.x<x+w+applet.textWidth("0.0000")+20&&m.y>y&&m.y<y+h;
		else return m!=null&&m.x>x+w&&m.x<x+w+applet.textWidth("0.0000")+20&&m.y>y&&m.y<y+h;
	  };
	  
	  boolean tooltipMenuPos(PVector m){
	    return m.x>tooltip.x&&m.x<tooltip.x+tooltip.w&&m.y>tooltip.y&&m.y<tooltip.y+tooltip.h;
	  };
	  
	  public void click(){
	    
	  };
	  
	  public void set(float start, float end,Object a,String b) {
	    startvalue = start;
	    endvalue = end;
	    if(Link==null)Link = a;
	    if(control=="")control = b;
	    float v = 0;
	    if(!tdown||update){
	    if(mdown||update){
	      v = PApplet.map( valuex, 0+1, w-1,start,end);
	      value = v;
	    }
	            Field field = null;
	    try{
	         field = a.getClass().getField(b); 
	         if(mdown||update){
	           field.set(a, v); 
	           update = false;
	         }
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }} 
	  };
	  
	  public void setPie(float start, float end,Object a,String b) {
	    startvalue = start;
	    endvalue = end;
	    if(Link==null)Link = a;
	    if(control=="")control = b;
	    float v = 0;
	    if(!tdown||update){
	    if(mdown1||update){
//	      v = PApplet.map( pieVal, 0, 2*applet.PI,start,end);
//	      value = v;
	    }
	            Field field = null;
	    try{
	         field = a.getClass().getField(b); 
	         if(mdown1||update){
	           field.set(a, value); 
	           update = false;
	         }
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }} 
	  };
	  
	  public void setPieInt(int start, int end,Object a,String b) {
		    startvalue = start;
		    endvalue = end;
		    if(Link==null)Link = a;
		    if(control=="")control = b;
		    float v = 0;
		    if(!tdown||update){
		    if(mdown1||update){
		      v = PApplet.map( pieVal, 0, w,start,end);
//		      value = v;
		    }
		            Field field = null;
		    try{
		         field = a.getClass().getField(b); 
		         if(mdown1||update){
		           field.set(a, value); 
		           update = false;
		         }
		    }catch (NullPointerException e) {
		    }catch (NoSuchFieldException e) {
		    }catch (IllegalAccessException e) {
		    }} 
		  };
	  
	  public void set(float start, float end,Object a,String b,String c) {
	    startvalue = start;
	    endvalue = end;
	    if(Link==null)Link = a;
	    if(control=="")control = b;
	    float v = 0;
	    if(!tdown||update){
	    if(mdown||update){
	      v = PApplet.map( valuex, 0+1, w-1,start,end);
	      value = v;
	    }
	            Field field1 = null;
	    try{
	         field1 = a.getClass().getField(c); 
	         if(mdown||update)field1.set(a, true); 
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }
	            Field field = null;
	    try{
	         field = a.getClass().getField(b); 
	         if(mdown||update){
	           field.set(a, v); 
	           update = false;
	         }
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }} 
	  };
	  
	  public void set(float start, float end) {
	    
	    float v = 0;
	    startvalue = start;
	    endvalue = end;
	    if(!vertical){
	    if((mdown||update)){
	      v = PApplet.map( valuex, 1, w-1,start,end);
	      value = v;
	      update = false;
	    }}else{
	      if(mdown||update){
	      v = PApplet.map( valuex, 1, h-1,start,end);
	      value = v;
	      update = false;
	    }}
	    //if(mdown&&!applet.mousePressed){
	    //  if(Bms.sliderObject==this)Bms.sliderObject = null;
	    //  mdown = false;
	    //}
	  };
	  
	  public void setint(int start, int end,Object a,String b) {
	    if(Link==null)Link = a;
	    if(control=="")control = b;
	    int v = 0;
	    if(mdown||update){
	      //valuex = 
	      v = PApplet.floor(PApplet.map( valuex, 1, w-1,start,end));
	      value = v;
	    }
	            Field field = null;
	    try{
	         field = a.getClass().getField(b); 
	         if(mdown||update){
	           field.set(a, (v)); 
	           update = false;
	         }
	         }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }      //noLoop();
	  };
	  
	  public void setint(int start, int end) {
	    int v = 0;
	    if(mdown||update){
	      v = PApplet.floor(PApplet.map( valuex, 1, w-1,start,end));
	      value = v;
	      update = false;
	    }
	  };
	  
	  public void setBms(BMScontrols bms) {
		  Bms = bms;
		  applet = bms.applet;
		  initColors();
	  };
	  
	  public void setPos(float a,float b) {
		  x = a;
		  bx = a;
		  y = b;
		  by = b;
	  };
};
