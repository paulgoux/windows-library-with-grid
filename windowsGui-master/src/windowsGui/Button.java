package windowsGui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Button{
	  BMScontrols Bms;
	  PApplet applet;
	  PGraphics canvas;
	  public float x,y,bx = x,by = y,w,h,size,textsize = 12,xoff,yoff,bsize,tsize = 12,tyoff,txoff,tmax = 2;
	  public int id,toggle,toggle2,type;
	  public float scalew,scaleh,r1,r2,r3,r4,rx,ry,transparency;
	  
	  public String label,blabel;
	  public PImage img;
	  public boolean drag,resize, radio,update,border = true,vertical,horizontal,gif,Img,value,textright,textbtm,textleft,
			  textup,texttoggle,animate = true,toggleb,mdown,m1down,sdown,visible = true,plain = true,labelVisible = true
	          ,up,right,down,togglebar,togglebox,mdown2,textcheck,parentCanvas,subLeft,click,mclick,m2down,toggle_;
	  public boolean localTheme;
	  int fcol,bcol,hcol,col,tcol,col1 = fcol,toggleCol;
	  public Menu parent;
	  public Menu submenu;
	  public Window subwindow;
	  public ArrayList<Button> buttons = new ArrayList<Button>();
	  public HashMap<String,Boolean> values = new HashMap<String,Boolean>();
	  public String Text = "" ;
	  public TextBox textbox;
	  public PVector mouse;
	  public tab parentTab;
	  Dropdown dMenu;
	  public tooltip info;
	  
	  public Button(float xx, float yy, float ww, float hh, String Label){
	    
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = hh;
	    label = Label;
	    blabel = label;
	    size = 1;
	    textsize = hh/2+hh/5;
	    bsize = tsize;
	    
	  };
	  
	  
	  
	  public Button(float xx, float yy, float ww, float hh, String Label,BMScontrols bms){
		    Bms = bms;
		    applet = bms.applet;
		    x = xx;
		    y = yy;
		    bx = x;
		    by = y;
		    w = ww;
		    h = hh;
		    label = Label;
		    blabel = label;
		    size = 1;
		    textsize = hh/2+hh/5;
		    bsize = tsize;
		    
	  };
	  
	  public Button(float xx, float yy, float ww, float hh){
	    
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = hh;
	    size = 1;
	    textsize = hh/2+hh/5;
	    bsize = tsize;
	    
	  };
	  
	  public Button(float xx, float yy, float ww, float hh,int Cols){
	    
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = hh;
	    size = 1;
	    textsize = hh/2+hh/5;
	    bsize = tsize;
	    value = true;
	    textbox = new TextBox(x,y,ww+1,hh,Cols);
	    
	  };
	  
	  public Button(float xx, float yy, float ww, float hh,int Cols, String Label){
	    
	    x = xx;
	    y = yy;
	    bx = x;
	    by = y;
	    w = ww;
	    h = hh;
	    size = 1;
	    textsize = hh/2+hh/5;
	    bsize = tsize;
	    label = Label;
	    blabel = label;
	    value = true;
	    textbox = new TextBox(x,y,ww+1,hh,Cols,Label);
	    
	  };
	  
	  public Button(){
	    
	  };
	  
	  public void save(){
	    
	  };
	  
	  public void load(){
	    
	  };
	  
	  public void draw2(){
	    applet.fill(Bms.bcol);
	    if(localTheme)
	    applet.fill(bcol);
	    applet.rect(x,y,w,h);
	  };
	  
	  public void btn(){
	    if(!radio&&!togglebar){
	      applet.stroke(255);
	      applet.strokeWeight(size);
	      if(!border)applet.noStroke();
	      if(parent!=null&&type!=0){
	        x = parent.x;
	        //y = parent.y;
	      }
	      applet.fill(255);
	      applet.rect(x,y,w-2,h-2,r1,r2,r3,r4);
	      applet.fill(col);
	      if(localTheme)applet.fill(fcol);
	      applet.rect(x ,y,w,h,r1,r2,r3,r4);
	      
	      applet.textSize(textsize);
	      
	      if(label!=null){
	        
	      if(scaleh>0){
	    	applet.pushMatrix();
	    	applet.translate(0,y*scaleh+h/2);
	    	applet.scale(1,scaleh);
	      }
	      if(scalew>0){
	    	applet.pushMatrix();
	    	applet.translate(x*scalew,0);
	    	applet.scale(scalew,1);
	      }
	        applet.fill(Bms.tcol);
	        if(localTheme)applet.fill(tcol);
	        applet.textSize(bsize);
	        // if(!textbtm&&!textright&&!textup)applet.text(label,x+5+txoff,y+13+tyoff+5);
	        if(textbtm)applet.text(label,x+txoff,y+h+tyoff+tsize);
	        applet.textSize(12);
	        if(scaleh>0){
	          applet.scale(1,1/scaleh);
	          applet.popMatrix();
	        }
	        if(scalew>0){
	          applet.scale(-scalew,1/scalew);
	          applet.popMatrix();
	        }
	        
	      }
	      if(value){
	        
	        if(textup)textbox.y     = y - h;
	        if(textbtm)textbox.y    = y + h;
	        if(textleft) textbox.x  = x - w;
	        if(textright) textbox.x = x + w;
	        Text = textbox.textBox;
	        
	        if(pos()||textbox.pos()||textbox.toggle==1)texttoggle=true;
	        else texttoggle = false;
	        if(texttoggle)textbox.draw();
	        if(textbox.pos()&&applet.mousePressed&&parent!=null){ parent.draw();}
	        else parent.toggle=0;
	      }
	      if(img!=null){
	        applet.image(img,x,y+1,w,h);
	      }}
	  };
	  
	  public void initColors() {
		  
//		  fcol = applet.color(0, 255, 73);
//		  bcol = applet.color(0,100);
//		  hcol = applet.color(255,50);
//		  col = fcol;
//		  tcol = applet.color(255);
//		  col1 = fcol;
//		  toggleCol = applet.color(50,0);
		  
		  col = applet.color(0, 255, 73);
		  bcol = applet.color(255);
		  tcol = applet.color(255); 
		  fcol = applet.color(0, 255, 73);
		  hcol = applet.color(0, 255, 73,100);
		  toggleCol = applet.color(55, 84, 63);
	  };
	  
	  public void drawPlain() {
		  if(plain){
		      applet.fill(0);
		      applet.stroke(Bms.col);
		      if(!border)applet.noStroke();
		      
		      if(w>h){
		      applet.fill(255);
		      applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      applet.fill(col);
		      if(localTheme)applet.fill(col);
		      applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      if(label!=null){
		    	  
		        applet.fill(Bms.tcol);
		        if(localTheme)applet.fill(tcol);
		        applet.textSize(tsize);
		        
		        if(up)applet.text(label,x+txoff,y-3+tyoff);
		        if(right)applet.text(label,x+w+2,y+tyoff);
		        if(down)applet.text(label,x,y+tsize*2+2+tyoff);
		        else applet.text(label,x +5 + txoff,y+tsize + tyoff + 4);
		        applet.textSize(12);
		      }}
		      else{
		        applet.fill(255);
		        applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		        applet.fill(col);
		        if(localTheme)applet.fill(col);
		        applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		        
		        if(label!=null){
		          applet.fill(Bms.tcol);
		          if(localTheme)applet.fill(tcol);
		          applet.textSize(tsize);
		          if(up)applet.text(label,x+txoff,y-3+tyoff);
		          if(right)applet.text(label,x+w+2+txoff,y+tyoff);
		          if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
		          else applet.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
		          applet.textSize(12);
		      }}}
	  };
	  
	  public void drawPlain(PGraphics canvas) {
		  if(plain){
		      canvas.fill(0);
		      canvas.stroke(Bms.col);
		      if(!border)canvas.noStroke();
//		      canvas.ellipse(mouse.x,mouse.y,20,20);
		      if(w>h){
		      canvas.fill(255);
		      canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      canvas.fill(col);
		      if(localTheme)canvas.fill(fcol);
		      canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      if(label!=null){
		    	  
		        canvas.fill(Bms.tcol);
		        if(localTheme)canvas.fill(tcol);
		        canvas.textSize(tsize);
		        
		        if(up)canvas.text(label,x+txoff,y-3+tyoff);
		        if(right)canvas.text(label,x+w+2,y+tyoff);
		        if(down)canvas.text(label,x,y+tsize*2+2+tyoff);
		        else canvas.text(label,x +5 + txoff,y+tsize + tyoff + 4);
		        canvas.textSize(12);
		      }}
		      else{
		        canvas.fill(255);
		        canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		        canvas.fill(col);
		        if(localTheme)canvas.fill(fcol);
		        canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		        
		        if(label!=null){
		          canvas.textSize(tsize);
		          if(up)canvas.text(label,x+txoff,y-3+tyoff);
		          if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
		          if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
		          else canvas.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
		          canvas.textSize(12);
		      }}}
	  };
	  
	  public void radioLogic() {
		  
	  }
	  
	  public void drawRadio(){
		  if(radio){
		      applet.stroke(0);
		      if(!border)applet.noStroke();
		      
		      applet.fill(Bms.col);
		      if(localTheme)applet.fill(fcol);
		      
		      if(w>h){
		      applet.fill(255);
		      applet.rect(x + rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
		      applet.fill(col);
		      if(localTheme)applet.fill(col);
		      if(pos())applet.fill(Bms.hcol);
		      applet.rect(x +rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
		      
		      if(label!=null){
		        applet.textSize(tsize);
		        applet.fill(Bms.tcol);
		        if(localTheme)applet.fill(tcol);
		        if(up)applet.text(label,x + txoff,y-3+ tyoff);
		        if(right)applet.text(label,x+w+2+ txoff,y+ tyoff);
		        if(down)applet.text(label,x+ txoff,y+tsize*2+2+ tyoff);
		        else applet.text(label,x +5 + txoff,y + tsize + tyoff);
		        applet.textSize(12);
		      }
		      
		      applet.fill(0);
		      if(toggle==1)applet.fill(255);
		      applet.ellipseMode(PConstants.CENTER);
		      applet.ellipse(x+rx+w/2+xoff ,y+h/2+yoff,h-5,h-5);
		    }
		    else if(w==h){
		      applet.fill(255);
		      applet.rect(x + rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
		      applet.fill(col);
		      if(localTheme)applet.fill(fcol);
		      
		      applet.rect(x + rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
		      if(label!=null){
		        applet.textSize(tsize);
		        if(up)applet.text(label,x + txoff,y-3 + tyoff);
		        if(right)applet.text(label,x+w+2 + txoff,y+tyoff);
		        if(down)applet.text(label,x + txoff,y+tsize*2+2+tyoff);
		        else applet.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
		        applet.textSize(12);
		      }
		      applet.fill(255);
		      applet.ellipse(x+w/2 + rx+xoff,y+h/2+yoff,w-8,h-8);
		    }}
	  };
	  
	  //revise code
	  public void drawTogglebar() {
		  if(togglebar){
		      applet.stroke(Bms.col);
		      if(!border)applet.noStroke();
		      
		      if(w>h){
		      applet.fill(255);
		      applet.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      
		      applet.fill(255);
//		      if(pos())applet.fill(Bms.hcol);
		      applet.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      
		      if(label!=null){
		        applet.fill(Bms.tcol);
		        if(localTheme)applet.fill(tcol);
		        applet.textSize(tsize);
		        if(up)applet.text(label,x + txoff,y-3 + tyoff);
		        if(right)applet.text(label,x+w+2 + txoff,y + tyoff);
		        if(down)applet.text(label,x + txoff,y+tsize*2+2 + tyoff);
		        else applet.text(label,x + 5 + txoff,y+tsize + tyoff + 4);
		        applet.textSize(12);
		      }
		      applet.fill(col);
		      if(localTheme)applet.fill(fcol);
		      
		      if(toggle==0){
		        applet.rect(x + rx+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
		        applet.fill(Bms.tcol);
		        if(localTheme)
		        applet.fill(tcol);
		        applet.text("OFF",x+rx+w+10+txoff,y+tsize+tyoff+4);
		      }else{
		        applet.fill(Bms.fcol);
		        if(localTheme)applet.fill(fcol);
		        applet.rect(x+rx+w/2+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
		        applet.fill(Bms.tcol);
		        if(localTheme)applet.fill(tcol);
		        applet.text("ON",x+rx+w+10+txoff,y+tsize+tyoff+4);
		      }
		    }
		    else{
		      applet.fill(255);
		      applet.rect(x + rx+xoff,y,w,h,r1,r2,r3,r4);
		      applet.fill(col);
		      if(localTheme)
		      applet.fill(fcol);
		      applet.rect(x+rx+xoff,y,w,h,r1,r2,r3,r4);
		      
		      if(label!=null){
		        applet.fill(Bms.tcol);
		        if(localTheme)
		        applet.fill(tcol);
		        applet.textSize(tsize);
		        if(up)applet.text(label,x+txoff,y-3+tyoff);
		        if(right)applet.text(label,x+w+2+txoff,y+tyoff);
		        if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
		        else applet.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
		        applet.textSize(12);
		      }
		      applet.fill(255);
		      if(localTheme)applet.fill(fcol);
		      if(toggle==0){
		        applet.rect(x+ rx+h/2+xoff,y+yoff,w,h/2,r1,r2,r3,r4);
		        applet.fill(Bms.tcol);
		        if(localTheme)
		        applet.fill(tcol);
		        applet.text("OFF",x+ rx+txoff,y+h+2+tyoff+4);
		      }
		      else{ 
		        applet.rect(x + rx+w/2+xoff,y+yoff,w,h/2,r1,r2,r3,r4);
		        applet.fill(Bms.tcol);
		        if(localTheme)
		        applet.fill(tcol);
		        applet.text("ON",x+rx+txoff,y+h+2+tyoff+4);
		      }
		    }}
	  };
	  
	  public void drawTogglebox() {
		   if(togglebox){
		      applet.fill(0);
		      applet.stroke(Bms.col);
		      if(!border)applet.noStroke();
		      
		      if(w>h){
		      applet.fill(255);
		      applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      applet.fill(col);
		      if(localTheme)applet.fill(fcol);
		      applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      if(label!=null){
		    	  
		        applet.fill(Bms.tcol);
		        if(localTheme)applet.fill(tcol);
		        applet.textSize(tsize);
		        
		        if(up)applet.text(label,x+txoff,y-3+tyoff);
		        if(right)applet.text(label,x+w+2+txoff,y+tyoff);
		        if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
		        else applet.text(label,x +5 + txoff,y+tsize + tyoff + 4);
		        applet.textSize(12);
		      }}
		      else{
		        applet.fill(255);
		        applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		        applet.fill(col);
		        if(localTheme)applet.fill(fcol);
		        applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		        
		        if(label!=null){
		          applet.textSize(tsize);
		          if(up)applet.text(label,x+txoff,y-3+tyoff);
		          if(right)applet.text(label,x+w+2+txoff,y+tyoff);
		          if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
		          else applet.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
		          applet.textSize(12);
		      }}}
	  };
	  
	  public void drawRadio(PGraphics canvas){
		  if(radio){
		      canvas.stroke(0);
		      if(!border)canvas.noStroke();
		      
		      canvas.fill(Bms.col);
		      if(localTheme)canvas.fill(fcol);
		      
		      if(w>h){
		      canvas.fill(255);
		      canvas.rect(x + rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
		      canvas.fill(col);
		      if(localTheme)canvas.fill(col);
		      if(pos(mouse))applet.fill(Bms.hcol);
		      canvas.rect(x +rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
		      
		      if(label!=null){
		        canvas.textSize(tsize);
		        canvas.fill(Bms.tcol);
		        if(localTheme)canvas.fill(tcol);
		        if(up)canvas.text(label,x + txoff,y-3+ tyoff);
		        if(right)canvas.text(label,x+w+2+ txoff,y+ tyoff);
		        if(down)canvas.text(label,x+ txoff,y+tsize*2+2+ tyoff);
		        else canvas.text(label,x +5 + txoff,y + tsize + tyoff);
		        canvas.textSize(12);
		      }
		      
		      canvas.fill(0);
		      if(toggle==1)canvas.fill(255);
		      canvas.ellipseMode(PConstants.CENTER);
		      canvas.ellipse(x+rx+w/2+xoff ,y+h/2+yoff,h-5,h-5);
		    }
		    else if(w==h){
		      canvas.fill(255);
		      canvas.rect(x + rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
		      canvas.fill(col);
		      if(localTheme)canvas.fill(fcol);
		      
		      canvas.rect(x + rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
		      if(label!=null){
		        canvas.textSize(tsize);
		        if(up)canvas.text(label,x + txoff,y-3 + tyoff);
		        if(right)canvas.text(label,x+w+2 + txoff,y+tyoff);
		        if(down)canvas.text(label,x + txoff,y+tsize*2+2+tyoff);
		        else canvas.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
		        canvas.textSize(12);
		      }
		      canvas.fill(255);
		      canvas.ellipse(x+w/2 + rx+xoff,y+h/2+yoff,w-8,h-8);
		    }}
	  };
	  
	  //revise code
	  public void drawTogglebar(PGraphics canvas) {
		  if(togglebar){
		      canvas.stroke(Bms.col);
		      if(!border)canvas.noStroke();
		      
		      if(w>h){
		      canvas.fill(255);
		      canvas.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      
		      canvas.fill(255);
//		      if(pos(mouse))canvas.fill(Bms.hcol);
		      canvas.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      
		      if(label!=null){
		        canvas.fill(Bms.tcol);
		        if(localTheme)canvas.fill(tcol);
		        canvas.textSize(tsize);
		        if(up)canvas.text(label,x+txoff,y-3+tyoff);
		        if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
		        if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
		        else canvas.text(label,x + 5 + txoff,y+tsize + tyoff + 4);
		        canvas.textSize(12);
		      }
		      canvas.fill(col);
		      if(localTheme)canvas.fill(fcol);
		      
		      if(toggle==0){
		        canvas.rect(x + rx+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
		        canvas.fill(Bms.tcol);
		        if(localTheme)
		        canvas.fill(tcol);
		        canvas.text("OFF",x+rx+w+10+txoff,y+tsize+tyoff+4);
		      }else{
		        canvas.fill(Bms.fcol);
		        if(localTheme)canvas.fill(fcol);
		        canvas.rect(x+rx+w/2+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
		        canvas.fill(Bms.tcol);
		        if(localTheme)canvas.fill(tcol);
		        canvas.text("ON",x+rx+w+10+txoff,y+tsize+tyoff+4);
		      }
		    }
		    else{
		      canvas.fill(255);
		      canvas.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      canvas.fill(col);
		      if(localTheme)
		      canvas.fill(fcol);
		      canvas.rect(x+rx+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      
		      if(label!=null){
		        canvas.fill(Bms.tcol);
		        if(localTheme)
		        canvas.fill(tcol);
		        canvas.textSize(tsize);
		        if(up)canvas.text(label,x +txoff,y-3+tyoff);
		        if(right)canvas.text(label,x+w+2 +txoff,y+tyoff);
		        if(down)canvas.text(label,x +txoff,y+tsize*2+2+tyoff);
		        else canvas.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
		        canvas.textSize(12);
		      }
		      canvas.fill(255);
		      if(localTheme)canvas.fill(fcol);
		      if(toggle==0){
		        canvas.rect(x+ rx+h/2+xoff,y+yoff,w,h/2,r1,r2,r3,r4);
		        canvas.fill(Bms.tcol);
		        if(localTheme)
		        canvas.fill(tcol);
		        canvas.text("OFF",x+ rx+txoff,y+h+2+tyoff+4);
		      }
		      else{ 
		        canvas.rect(x + rx+w/2+xoff,y+yoff,w,h/2,r1,r2,r3,r4);
		        canvas.fill(Bms.tcol);
		        if(localTheme)
		        canvas.fill(tcol);
		        canvas.text("ON",x+rx+txoff,y+h+2+tyoff+4);
		      }
		    }}
	  };
	  
	  public void drawTogglebox(PGraphics canvas) {
		   if(togglebox){
			  
		      canvas.fill(0);
		      canvas.stroke(Bms.col);
		      if(!border)canvas.noStroke();
		      
		      if(w>h){
		      canvas.fill(255);
		      canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      canvas.fill(col);
		      if(localTheme)canvas.fill(fcol);
		      canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		      if(label!=null){
		    	  
		        canvas.fill(Bms.tcol);
		        if(localTheme)canvas.fill(tcol);
		        canvas.textSize(tsize);
		        
		        if(up)canvas.text(label,x+txoff,y-3+ tyoff);
		        if(right)canvas.text(label,x+w+2+ txoff,y+tyoff);
		        if(down)canvas.text(label,x+ txoff,y+tsize*2+2+tyoff);
		        else canvas.text(label,x +5 + txoff,y+tsize + tyoff + 4);
		        canvas.textSize(12);
		      }}
		      else{
		        canvas.fill(255);
		        canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		        canvas.fill(col);
		        if(localTheme)canvas.fill(fcol);
		        canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
		        
		        if(label!=null){
		          canvas.textSize(tsize);
		          if(up)canvas.text(label,x+txoff,y-3+tyoff);
		          if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
		          if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
		          else canvas.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
		          canvas.textSize(12);
		      }}}
	  };
	  
	  public void draw(){
	    logic();
	    applet.textSize(12);
	    highlight();
//	    btn();
	    if(plain)drawPlain();
	    else if(radio)drawRadio();
	    else if(togglebar)drawTogglebar();
	    else if(togglebox)drawTogglebox();
	    
	    if(subpos())toggle2 = 1;
	    else toggle2 = 0;
	    
	    applet.strokeWeight(1);
	    if(info!=null)info.draw();
	  };
	  
	  public void logic(){
	    if(animate){
	      if(pos()||toggle==1){
	        if(bsize<tsize+tmax) bsize += 0.5;
	      }else if(bsize>tsize)bsize -= 0.5;
	      }
	      if(radio||togglebar){
	      }
	      
	  };
	  
	  public void logic(PVector m){
	    if(animate){
	      if(pos(m)||toggle==1){
	        if(bsize<tsize+tmax) bsize += 0.5;
	        }else if(bsize>tsize)bsize -= 0.5;
	      
	      }
	      if(radio||togglebar){
	        // if(pos2(m)||toggle==1)bcol = hcol;
	        // else bcol = applet.color(0,100);
	      }
	      
	  };
	  
	  public void draw(PGraphics canvas) {
		    logic(mouse);
		    highlight(mouse);
		    canvas.textSize(12);
//		    canvas.fill(0);
//			canvas.ellipse(mouse.x,mouse.y,20,20);
		    if(plain)drawPlain(canvas);
		    else if(radio)drawRadio(canvas);
		    else if(togglebar)drawTogglebar(canvas);
		    else if(togglebox)drawTogglebox(canvas);
		    
		    if(subpos(mouse))toggle2 = 1;
		    else toggle2 = 0;
		    
		    canvas.strokeWeight(1);
		    if(info!=null)info.draw();
	  };
	  
	  public void radio(){
	    
	    radio = true;
	  };
	  
	  
	  
	  public void labelcheck(float a){
	    
	    if(applet.textWidth(label)>a){
	    for(int i=10;i<label.length();i++){
	      float lw = applet.textWidth(label.substring(0,i)+ " ...");
	      if(lw>a){
	        label = label.substring(0,i-1) + "...";
	        textcheck = true;
	        break;
	      }
	    }}
	    else textcheck = true;
	    
	  };
	  
	  boolean pos(){
	    if(radio||togglebar){
	      return x +rx < applet.mouseX && applet.mouseX < x + w+rx && y < applet.mouseY && applet.mouseY < y + h+2;
	    }else{
	      return x  < applet.mouseX && applet.mouseX < x + w && y < applet.mouseY && applet.mouseY < y + h+2;
	    }
	    
	  };
	  
	  boolean radioPos(){
	    //return false;
	    return x +applet.textWidth(label)+20 < applet.mouseX && applet.mouseX < x + w+applet.textWidth(label)+20 && y < applet.mouseY && applet.mouseY < y + h+2;
	  };
	  
	  boolean pos(PVector m){
	    if(radio||togglebar){
	      
	      return rx+x  < m.x && m.x < x+rx + w && y < m.y && m.y < y + h+2;
	      
	    }else{
	      return x  < m.x && m.x < x + w && y < m.y && m.y < y + h+2;
	    }
	    
	  };
	  
	  boolean pos(PGraphics m){
	    
	    return x  < mouse.x && mouse.x < x + w && y < mouse.y && mouse.y < y + h+2;
	  };
	  
	  boolean pos2(){
	    
	    return x < applet.mouseX && applet.mouseX < x + w + applet.textWidth(label)+5 && y < applet.mouseY && applet.mouseY < y + h+2;
	  };
	  
	  boolean pos2(PVector m){
	    
	    return x < m.x && m.x < x + w + applet.textWidth(label)+5 && y < m.y && m.y < y + h+2;
	  };
	  
	  boolean pos3(){
	    
	    return x + applet.textWidth(label)+5 < applet.mouseX && applet.mouseX < x + w + applet.textWidth(label)+5 && y < applet.mouseY && applet.mouseY < y + h+2;
	  };
	  
	  boolean pos3(PVector m){
	    
	    return x + applet.textWidth(label)+5 < m.x && m.x < x + w + applet.textWidth(label)+5 && y < m.y && m.y < y + h+2;
	  };
	  
	  public void getValue(){
	    //if(key='ENTER')
	  };
	  
	  public void setName(String a){
	    label = a;
	  };
	  
	  String getName(){
	    String a = "";
	    if (label!=null) a = label;
	    
	    return a;
	  };
	  
	  public void self_toggle(){
	    
	      if(parent==null){
	        if(pos()&&applet.mousePressed&&!mdown){
	        mdown = true;
	        toggle++;
	      if(toggle==2){
	       toggle=0; 
	      }}}else{
	      if(pos()&&parent.toggle==1&&applet.mousePressed&&!mdown){
	        mdown = true;
	        toggle++;
	      }
	      if(toggle==2){
	       toggle=0; 
	      }}
	    
	    if(!applet.mousePressed)mdown = false;
	  };
	  
	  public void self_toggle(PVector m){
		  
	      if(parent==null){
	        if(pos(m)&&applet.mousePressed&&!mdown){
	        m1down = true;
	        toggle++;
	      if(toggle==2){
	        toggle=0; 
	      }}}else{
	      if(pos(m)&&parent.toggle==1&&applet.mousePressed&&!mdown){
	        mdown = true;
	        toggle++;
	      }
	      if(toggle==2){
	        toggle=0; 
	      }}
		  
//		    if(pos(m)&&parent.toggle==1&&applet.mousePressed&&toggle==0&&!m1down){
//		      toggle=1;
//		      m1down = true;
//		    }
//		    
//		    if(pos(m)&&parent.toggle==1&&applet.mousePressed&&toggle>0&&!m1down){
//		      toggle=0;
//		      m1down = true;
//		    }
	    
	    if(!applet.mousePressed)mdown = false;
	  };

	  boolean hold(){
	    if(mdown)return true;
	    else return false;
	  };
	  
	  public void self_click(){
	    if(!pos()&&parent!=null&&parent.toggle==1&&applet.mousePressed){mdown = true;}
	    //else if(!pos()&&parent==null&&toggle==1&&applet.mousePressed){mdown = false;}
	    if(pos()&&parent!=null&&parent.toggle==1&&!mdown){
	      mdown = true;
	    }
	    else if(pos()&&parent==null&&!mdown){
	      mdown = true;
	    }
	    if(mdown)toggle = 1;
	    else toggle = 0;
	    if(mdown && !applet.mousePressed){mdown = false;}
	  };
	  
	  public void self_click2(){
	     if(pos()&&parent==null&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle++;
	      col = hcol;
	      mdown = true;
	    }
	    
	    if(pos()&&parent==null&&applet.mousePressed&&toggle>0&&!mdown){
	      toggle=0;
	      col = fcol;
	      mdown = true;
	    }
	    
	    if(!applet.mousePressed)mdown = false;
	  };
	  
	  public void self_click2(PVector m){
	     if(pos(m)&&parent==null&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle++;
	      col = hcol;
	      mdown = true;
	    }
	    
	    if(pos(m)&&parent==null&&applet.mousePressed&&toggle>0&&!mdown){
	      toggle=0;
	      col = fcol;
	      mdown = true;
	    }
	    
	    if(!applet.mousePressed)mdown = false;
	  };
	  
	  public void self_click3(){
	     if(pos()&&parent==null&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle++;
	      col = hcol;
	      mdown = true;
	    }
	    
	    if(!mdown){
	      toggle=0;
	      col = fcol;
	      mdown = true;
	    }
	    
	    if(!applet.mousePressed)mdown = false;
	  };
	  
	  public void self_click4(){
	    boolean k = false;
	     if(pos()&&parent==null&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle++;
	      col = hcol;
	      mdown = true;
	    }
	    
	    if(mdown)k = true;
	    
	    if(k){
	      toggle=0;
	      col = fcol;
	      mdown = false;
	    }
	    
	    if(!applet.mousePressed)mdown = false;
	  };

	  boolean click(){
	      boolean k = false;
	      // if(pos())k = true;
	      // else k = false;
	      if (pos()&&applet.mousePressed&&!click){
	        click = true;
	        k = false;
	      }else if(click&&!applet.mousePressed){
	        k = true;
	        click = false;
	      }
	      
	      return k;
	  };

	  boolean click(PVector m){
	      boolean k = false;
	      // if(pos())k = true;
	      // else k = false;
	      if (pos(m)&&applet.mousePressed&&!click){
	        click = true;
	        k = false;
	      }else if(click&&!applet.mousePressed){
	        k = true;
	        click = false;
	      }
	      
	      return k;
	  };
	  
	  boolean mouseDown(){
	    boolean k = false;
	    if(pos()&&applet.mousePressed)k = true;
	    if(!applet.mousePressed)k = false;
	    return k;
	  };
	  
	  public void click(Object a,String b){
	    
	    if(parent!=null){
	    Field field = null;
	    
	    try{
	         field = a.getClass().getField(b); 
	        
	         if(click()&&parent.toggle==1){
	           field.set(a, true); 
	           toggle = 1;
	         }else {
	           field.set(a, false);
	           toggle = 0;
	         }
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }}
	    else{
	    
	    Field field = null;
	    
	    try{
	         field = a.getClass().getField(b); 
	        
	         if(click()){
	           field.set(a, true); 
	         }else {
	           field.set(a, false);
	         }
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }
	    }
	  };

	  public void toggleField(){

	  };
	  
	  public void latch(Object a,String b){
	    PVector n = new PVector(applet.mouseX,applet.mouseY);
	    if(mouse!=null)n = mouse;
	    boolean mousedown = false;
	    if(parent!=null){
	    if(pos(n)&&parent.toggle==1&&applet.mousePressed&&toggle==0)toggle++;
	    if(!applet.mousePressed&&toggle>0){mdown = true;toggle = 0;}
	    
	    Field field = null;
	    
	    try{
	         field = a.getClass().getField(b); 
	        
	         if(mdown)field.set(a, true); 
	         //else field.set(a, false); 
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }if(!applet.mousePressed)mdown = false;}
	    else{
	      if(pos(n)&&applet.mousePressed&&toggle==0)toggle++;
	      if(!applet.mousePressed&&toggle>0){mdown = true;toggle = 0;}
	      
	    
	    Field field = null;
	    
	    try{
	         field = a.getClass().getField(b); 
	        
	         if(mdown){
	           field.set(a, true); 
	         }
	         //else field.set(a, false); 
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }
	  if(!applet.mousePressed)mdown = false;
	}
	  };
	  
	  public void reverseclick(Object a,String b){
	    if(parent!=null){
	    //if(!pos()&&parent.toggle==1&&applet.mousePressed)mdown = true;
	    if(pos()&&parent.toggle==1&&applet.mousePressed&&!mdown)mdown = true;
	    
	    if(mdown)toggle = 1;
	    else toggle = 0;
	    if(mdown && !applet.mousePressed){mdown = false;}
	    
	    Field field = null;
	    
	    try{
	         field = a.getClass().getField(b); 
	        
	         if(mdown)field.set(a, true); 
	         else field.set(a, false);
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }}
	    else{
	      if(pos()&&applet.mousePressed&&!mdown)mdown = true;
	    
	    if(mdown){
	      toggle = 1;
	    }
	    else toggle = 0;
	    if(mdown && !applet.mousePressed){mdown = false;}
	    
	    Field field = null;
	    
	    try{
	         field = a.getClass().getField(b); 
	        
	         if(!mdown)field.set(a, true); 
	         else field.set(a, false);
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }
	    }
	  };
	  
	   public void self_Toggle(){
	   if(parent!=null){
	    if(pos() && parent.toggle==1){
	      toggle ++;
	    
	    if(toggle==2){
	      toggle = 0;
	    }}}else {
	      if(pos()){
	      toggle ++;
	    
	    if(toggle==2){
	      toggle = 0;
	    }}
	    }
	    
	  };

	  public void toggle() {
	    
	    if (pos()&&applet.mousePressed&&!mdown)mdown = true;
	    if(mdown&&!toggle_&&!m2down){
	      toggle_ = true;
	      m2down = true;
	    }
	    if(mdown&&toggle_&&!m2down){
	      toggle_ = false;
	      m2down = true;
	    }
	    
	    if(!applet.mousePressed){
	      mdown = false;
	      m2down = false;
	    }
	    
	  };
	  
	  public void toggle( String b){
	    if(parent!=null){
	    if(pos() && parent.toggle==1){
	      toggle ++;
	      
	      if(toggle==2){
	        toggle=0;
	    }}
	    if(pos() && parent.toggle==1){
	        
	    
	         //if(toggle==1)field.set(a, true); 
	         //else if(toggle==0)field.set(a, false);
	        }}
	    
	    else{
	      
	    }
	  };
	  
	  public void toggle(Object a, String b){
	    if(parent!=null){
	    if(pos() && parent.toggle==1&&applet.mousePressed&&!mdown){
	      mdown = true;
	      toggle ++;
	      
	      if(toggle==2)toggle=0;
	    }
	    if(pos() && parent.toggle==1&&mdown&&!applet.mousePressed){
	        mdown = false;
	        Field field = null;
	    
		    try{
		      
		         field = a.getClass().getField(b); 
		        
		         if(toggle==1)field.set(a, true); 
		         else if(toggle==0)field.set(a, false);
		         
		    }catch (NullPointerException e) {
		    }catch (NoSuchFieldException e) {
		    }catch (IllegalAccessException e) {
	    }}}else{
	      
	      if(pos()&&applet.mousePressed&&!mdown){
	        mdown = true;
	        toggle ++;
	        if(toggle==2)toggle=0;
	    }
	    if(pos()&&toggle==1&&mdown&&!applet.mousePressed){
	        mdown = false;
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	        
	         if(toggle==1)field.set(a, true); 
	         else if(toggle==0)field.set(a, false);
	         
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }}}
	    if(!applet.mousePressed){
	      mdown = false;
	    }
	  };

	  public void toggle(Object a, String b,PGraphics c){
	    
//	    if(applet.mousePressed){
//	    if(parent!=null){
//	    if(pos(mouse) && parent.toggle==1){
//	      toggle ++;
//	      if(toggle==2)toggle=0;
//	    }
//	    if(pos(mouse) && parent.toggle==1){
	  if(parent!=null){
		    if(pos() && parent.toggle==1&&applet.mousePressed&&!mdown){
		      mdown = true;
		      toggle ++;
		      
		      if(toggle==2)toggle=0;
		    }
		    if(pos() && parent.toggle==1&&mdown&&!applet.mousePressed){
		        mdown = false;
		    Field field = null;
		    
		    try{
		      
		         field = a.getClass().getField(b); 
		        
		         if(toggle==1&&mdown)field.set(a, true); 
		         else if(toggle==0)field.set(a, false);
		         
		    }catch (NullPointerException e) {
		      PApplet.println("np np");
		    }catch (NoSuchFieldException e) {
		      PApplet.println("np nsf");
		    }catch (IllegalAccessException e) {
		      PApplet.println("np ia");
	    }}}else{
	      
			if(pos(mouse)&&applet.mousePressed&&!mdown){
				toggle ++;
				if(toggle==2)toggle=0;
				mdown = true;
			}
		    if(mdown){
		      
		    Field field = null;
		    
			    try{
			      
			        field = a.getClass().getField(b); 
			        
			        if(toggle==1&&mdown)field.set(a, true); 
			        else if(toggle==0&&mdown)field.set(a, false);
			        if(field.getBoolean(a)==true)c.fill(255,0,0);
			    }catch (NullPointerException e) {
			      PApplet.println("p np");
			    }catch (NoSuchFieldException e) {
			      PApplet.println("p nsf");
			    }catch (IllegalAccessException e) {
			      PApplet.println("p ia");
	    }}}
	    if(!applet.mousePressed)mdown = false;
	  };
	  
	  public void toggle(PApplet a, String b){
	    if(parent!=null){
	    if(pos() && parent.toggle==1){
	      toggle ++;
	      
	      if(toggle==2){
	        toggle=0;
	    }}
	    if(pos() && parent.toggle==1){
	        
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	        
	         if(toggle==1)field.set(a, true); 
	         else if(toggle==0)field.set(a, false);
	         
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }}}
	    
	    else{
	      
	    }
	  };
	  
	  
	  public void toggle2(Object a, String b){
	    
	    
	    if(parent!=null){
	    if(pos()&&parent.toggle==1&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle=1;
	      mdown = true;
	    }
	    
	    if(pos()&&parent.toggle==1&&applet.mousePressed&&toggle>0&&!mdown){
	      toggle=0;
	      mdown = true;
	    }
	    if(!applet.mousePressed)mdown = false;
	    
	    if(pos() && parent.toggle==1){
	       //if(applet.mousePressed) applet.println("button",a,b);
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	        
	         if(toggle==1)field.set(a, true); 
	         else field.set(a, false);
	         
	    }catch (NullPointerException e) {
	      if(applet.mousePressed)PApplet.println("button1, np");
	    }catch (NoSuchFieldException e) {
	      if(applet.mousePressed)PApplet.println("button1, nsf",a);
	    }catch (IllegalAccessException e) {
	      if(applet.mousePressed)PApplet.println("button1, ia");
	    }}}
	    else {
	      
	    if(pos()&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle++;
	      mdown = true;
	    }
	    
	    else if(pos()&&applet.mousePressed&&toggle>0&&!mdown){
	      toggle=0;
	      mdown = true;
	    }
	    if(!applet.mousePressed)mdown = false;
	    
	    if(mdown){
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	        
	         if(toggle==1){
	           field.set(a, true); 
	         }else 
	           field.set(a, false);
	         
	    }catch (NullPointerException e) {
	      if(applet.mousePressed)PApplet.println("button, np");
	    }catch (NoSuchFieldException e) {
	      if(applet.mousePressed)PApplet.println("button, nsf");
	    }catch (IllegalAccessException e) {
	      if(applet.mousePressed)PApplet.println("button, ia");
	    }}if(!applet.mousePressed)toggle = 0;}
	  };
	  
	  public void toggle2(Object a, String b,PVector m){
		    
		    
		    if(parent!=null){
		    if(pos(m)&&parent.toggle==1&&applet.mousePressed&&toggle==0&&!mdown){
		      toggle=1;
		      mdown = true;
		    }
		    
		    if(pos(m)&&parent.toggle==1&&applet.mousePressed&&toggle>0&&!mdown){
		      toggle=0;
		      mdown = true;
		    }
		    if(!applet.mousePressed)mdown = false;
		    
		    if(pos(m) && parent.toggle==1){
		       //if(applet.mousePressed) applet.println("button",a,b);
		    Field field = null;
		    
		    try{
		      
		         field = a.getClass().getField(b); 
		        
		         if(toggle==1)field.set(a, true); 
		         else field.set(a, false);
		         
		    }catch (NullPointerException e) {
		      if(applet.mousePressed)PApplet.println("button1, np");
		    }catch (NoSuchFieldException e) {
		      if(applet.mousePressed)PApplet.println("button1, nsf",a);
		    }catch (IllegalAccessException e) {
		      if(applet.mousePressed)PApplet.println("button1, ia");
		    }}}
		    else {
		      
		    if(pos(m)&&applet.mousePressed&&toggle==0&&!mdown){
		      toggle++;
		      mdown = true;
		    }
		    
		    else if(pos(m)&&applet.mousePressed&&toggle>0&&!mdown){
		      toggle=0;
		      mdown = true;
		    }
		    if(!applet.mousePressed)mdown = false;
		    
		    if(mdown){
		    Field field = null;
		    
		    try{
		      
		         field = a.getClass().getField(b); 
		        
		         if(toggle==1){
		           field.set(a, true); 
		         }else 
		           field.set(a, false);
		         
		    }catch (NullPointerException e) {
		      if(applet.mousePressed)PApplet.println("button, np");
		    }catch (NoSuchFieldException e) {
		      if(applet.mousePressed)PApplet.println("button, nsf");
		    }catch (IllegalAccessException e) {
		      if(applet.mousePressed)PApplet.println("button, ia");
		    }}if(!applet.mousePressed)toggle = 0;}
		  };
	  
	  public void toggle4(Object a, String b){
	    
	    
	    if(parent!=null){
	    if(pos()&&parent.toggle==1&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle=1;
	      mdown = true;
	    }
	    
	    if(pos()&&parent.toggle==1&&applet.mousePressed&&toggle>0&&!mdown){
	      toggle=0;
	      mdown = true;
	    }
	    if(!applet.mousePressed)mdown = false;
	    
	    if(pos() && parent.toggle==1){
	        
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	        
	         if(toggle==1){
	           field.set(a, true); 
	         }else {
	           field.set(a, false);
	         }
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }}}
	    else {
	      
	    if(pos()&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle++;
	      mdown = true;
	    }
	    
	    else if(pos()&&applet.mousePressed&&toggle>0&&!mdown){
	      toggle=0;
	      mdown = true;
	    }
	    if(!applet.mousePressed)mdown = false;
	    
	    if(mdown){
	        
	    Field field = null;
	    
	    //try{
	      
	    //     field = a.getClass().getField(b); 
	        
	    //     if(toggle==1&&field){
	    //     }else if(toggle==1&&!field){
	    //       field.set(a, true);
	    //     }else if(toggle==0&&!field){
	    //     }else if(toggle==0&&field){
	    //       field.set(a, false);
	    //     }
	    //}catch (NullPointerException e) {
	    //}catch (NoSuchFieldException e) {
	    //}catch (IllegalAccessException e) {
	    //}
	  }
	  }
	  };
	  
	  public void toggled(Object a,String b){
	    //if(dclick)
	    if(pos()&&applet.mousePressed&&toggle==0&&!mdown){
	      toggle++;
	      mdown = true;
	    }
	    
	    else if(pos()&&applet.mousePressed&&toggle>0&&!mdown){
	      toggle=0;
	      mdown = true;
	    }
	    if(!applet.mousePressed)mdown = false;
	    
	    if(mdown){
	        
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	        
	         if(toggle==1){
	           field.set(a, true); 
	         }else {
	           field.set(a, false);
	         }
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }}
	  
	  };
	  
	   public void sptoggle(Object a, String b,String [] c){
	     
	     
	    if(pos() && parent.toggle==1){
	      toggle ++;
	      if(toggle==2){
	        toggle=0;
	    }}
	    if(pos() && parent.toggle==1){
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	         
	         if(toggle==1){
	           
	           field.set(a, true); 
	         }else {
	           field.set(a, false);
	           //applet.println("button ",next,a," ");
	         }}catch (NoSuchFieldException e) {
	           PApplet.println("nsf");
	          }catch (NullPointerException e) {
	            PApplet.println("npe");
	          }catch (IllegalAccessException e) {
	            PApplet.println("iae");
	          }
	          
	        for(int i=0;i<c.length;i++){
	           String next = c[i];
	           if(next!=b){
	             //applet.println("button ",next,b," ");
	             try{
	             field = a.getClass().getField(next); 
	             field.set(a, false); 
	             parent.items.get(i).toggle=0;
	             //applet.print(field.get(a)," ");
	             }catch (NullPointerException e) {
	               PApplet.println("nsf");
	             }catch (NoSuchFieldException e) {
	               PApplet.println("npe");
	             }catch (IllegalAccessException e) {
	               PApplet.println("iae");
	             }
	           }}}
	  };

	  public void sptoggle2(Object a, String b,String [] c){
	     
	     
	    if(pos() && parent.toggle==1){
	      toggle ++;
	      if(toggle==2){
	        toggle=0;
	    }}
	    if(pos() && parent.toggle==1){
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	         
	         if(toggle==1){
	           
	           field.set(a, true); 
	         }else {
	           field.set(a, false);
	           //applet.println("button ",next,a," ");
	         }}catch (NoSuchFieldException e) {
	           PApplet.println("nsf");
	          }catch (NullPointerException e) {
	            PApplet.println("npe");
	          }catch (IllegalAccessException e) {
	            PApplet.println("iae");
	          }
	          
	        for(int i=0;i<c.length;i++){
	           String next = c[i];
	           if(next!=b){
	             //applet.println("button ",next,b," ");
	             try{
	             field = a.getClass().getField(next); 
	             field.set(a, false); 
	             PApplet.print(field.get(a)," ");
	             }catch (NullPointerException e) {
	               PApplet.println("nsf");
	             }catch (NoSuchFieldException e) {
	               PApplet.println("npe");
	             }catch (IllegalAccessException e) {
	               PApplet.println("iae");
	             }
	           }}}
	  };
	  
	  public void toggle3(Object a, String b){
	    
	    if(applet.mousePressed&&pos()){
	      toggle++;
	    }
	    
	    if(toggle>2)toggle=0;
	    
	    Field field = null;
	    
	    try{
	      
	         field = a.getClass().getField(b); 
	        
	         if(toggle==1){
	           field.set(a, true); 
	         }else {
	           field.set(a, false);
	         }
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }catch (IllegalAccessException e) {
	    }
	  };
	  
	  public void highlight2(){
	    col = Bms.col;
	  }
	  
	  public void highlight3(){
	     if(pos()) {
	    	 col = Bms.hcol;
	     }
	     else {
	    	 col = Bms.fcol;
	     }
	    
	  };
	  
	  public void highlight3(PVector m){
	     if(pos(m)) {
	    	 col = Bms.hcol;
	     }
	     else {
	    	 col = Bms.fcol;
	     }
	    
	  };
	  
	  public void highlight(){
	    
	      if(toggle==1){
	        col = Bms.toggleCol;
	        if(localTheme)col = toggleCol;
	        if(pos()){
	          
	        col = Bms.hcol;
	        if(localTheme)col = hcol;
	      }
	      }else{
	      
		      if(pos()){
		        col = Bms.hcol;
		        if(localTheme)col = hcol;
		      }else{
		        col = Bms.fcol;
		        if(localTheme)col = fcol;
		      }
	      
	      }
	  };
	  
	  public void highlight(PVector m){
		  
		  if(toggle==1){
	        col = Bms.toggleCol;
	        if(localTheme)col = toggleCol;
	        if(pos(mouse)){
	          
	        col = Bms.hcol;
	        if(localTheme)col = hcol;
	      }
	      }else{
	      
//		      if(pos(mouse)){
//		        col = Bms.hcol;
//		        if(localTheme)col = hcol;
//		      }else{
//		        col = Bms.fcol;
//		        if(localTheme)col = fcol;
//		      }
				col = Bms.fcol;
				if(localTheme)col = fcol;
				if(pos(mouse)){
				  
					col = Bms.hcol;
					if(localTheme)col = hcol;
				}
	      }
	  };
	  
	  public void setRadio() {
		  plain = false;
		  togglebox = false;
		  togglebar = false;
		  radio = true;
	  };
	  
	  public void setToggleBox() {
		  plain = false;
		  togglebox = true;
		  togglebar = false;
		  radio = false;
	  };
	  
	  public void setTogglebar() {
		  plain = false;
		  togglebox = false;
		  togglebar = true;
		  radio = false;
	  };
	  
	  public void setPlain() {
		  plain = plain;
		  togglebox = false;
		  togglebar = false;
		  radio = false;
	  };
	  
	  public void setLabelOff() {
		  labelVisible = false;
	  };
	  
	  boolean subpos(){
	     float X = 0;
	     float Y = 0;
	      if(parent!=null){
	        X = parent.x;
	        Y = parent.y;
	      }
	    
	      return applet.mouseX> x + w-20+xoff&& applet.mouseX < x + w&& applet.mouseY > y&& applet.mouseY < y + h;
	    
	  };
	  
	  boolean subpos(PVector m){
	     float X = 0;
	     float Y = 0;
	      if(parent!=null){
	        X = parent.x;
	        Y = parent.y;
	      }
	    
	      return m.x> x + w-20+xoff&& m.x < x + w&& m.y > y&& m.y < y + h;
	    
	  };
	  
	  boolean subposLeft(){
	     float X = 0;
	     float Y = 0;
	      if(parent!=null){
	        X = parent.x;
	        Y = parent.y;
	      }
	    
	      return applet.mouseX> x&& applet.mouseX < x + 20&& applet.mouseY > y&& applet.mouseY < y + h;
	    
	  };
	  
	  boolean subposLeft(PVector m){
	    
	      return m.x> x&& m.x < x + 20&& m.y > y&& m.y < y + h;
	    
	  };
	  
	  public void setPos(float a,float b) {
		  x = a;
		  bx = a;
		  y = b;
		  by = b;
	  };
};

