package windowsGui;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class TextArea{
	  BMScontrols Bms;
	  PApplet applet;
	  public int id,toggle,cols,rows,size,t,timer = 101,blkrate = 30,t1 = blkrate,t2 = blkrate,start,end,hcount,index,lindex,
			  vindex = -1,hindex = -1,windex,vpos = 0,hpos,sltcounter,vcount = 0,delay=10, maxCount = 51;
	  public float x,y,w,h,bx,by,bh,bw,textsize = 12,twidth = 0,posx,posy,tposx,tposy,strposx,strposy,tbwidth,strwidth,
			  strheight,cursorx, cursory,crwidth,totalwidth,r1,r2,r3,r4;
	  public float inputDelay = 9,delay2 = 5;
	  public String label,text,label_backup,cboard = "",value;
	  public boolean drag,resize,hover,border,background,hidden,fill = true,init,ready,label_bool,clear,copied,tbox = true,
			  tsize = false,parentCanvas,setDelay,firstPress,getChar,selectAll,ctrl,
	          clipboardToggle,copyClipboard;
	  //Menu toolBox;
	  //Button child;
	  //Slider
	  public ArrayList<Letter> textbox = new ArrayList<Letter>();
	  ArrayList<Letter> tm0 = new ArrayList<Letter>();
	  ArrayList<Letter> tm1 = new ArrayList<Letter>();
	  public String textBox = "";
	  String temp = "";
	  String stringImport = "";
	  String [] backup;
	  ArrayList<Integer> counted = new ArrayList<Integer>();
	  ArrayList<Integer> lines = new ArrayList<Integer>();
	  ArrayList<Integer> dragh = new ArrayList<Integer>();
	  //ArrayList<Float> tsize = new ArrayList<Float>();
	  Letter b = null;
	  public int col,col2;
	  PVector mouse;
	  //Window parent;
	  
	  public TextArea(float X,float Y,float WW,float HH,int Cols,int Rows,BMScontrols bms) {
		  	
	    Bms = bms;
	    applet = bms.applet;
	    
	    x = X;
	    y = Y;
	    w = WW;
	    h = HH;
	    bx = x;
	    by = y;
	    bw = w;
	    bh = h;
	    cols = Cols;
	    rows = Rows;
	    totalwidth = w * rows;
	    size = textbox.size();
	    lines.add(0);
	    textsize = 12;
	    //toolBox = new Menu(x+1,y-20,200-1,20);
	    fill = true;
	  };
	  
	  public TextArea(float X,float Y,float WW,float HH,int Cols,int Rows,String Label,BMScontrols bms) {
		  	
	    Bms = bms;
	    applet = bms.applet;
	    
	    x = X;
	    y = Y;
	    w = WW;
	    h = HH;
	    bx = x;
	    by = y;
	    bw = w;
	    bh = h;
	    cols = Cols;
	    rows = Rows;
	    totalwidth = w * rows;
	    textsize = 12;
	    size = textbox.size();
	    label = Label;
	    label_backup = Label;
	    lines.add(0);
	    label_bool = true;
	    //toolBox = new Menu(x+1,y-20,200-1,20);
	  };
	  
	  TextArea(float X,float Y,float WW,float HH,int Cols,int Rows,String []Lines,BMScontrols bms) {
		  	
	    Bms = bms;
	    applet = bms.applet;
	    
	    x = X;
	    y = Y;
	    w = WW;
	    h = HH;
	    bx = x;
	    by = y;
	    bw = w;
	    bh = h;
	    cols = Cols;
	    rows = Rows;
	    
	    //String [] text = null;
	    //if(Bmscontrols.Lines!=null)text = null;
	    if(Lines!=null){
	      //for(int i=0;i<Lines.length;i++){
	        
	      //  //String[] space = match(Lines[i],"  ");
	      //  //if(space!=null){
	      //  //  //Lines.splice(i+1,"")
	      //  //  for(int j=0;j<cols;j++){
	      //  //    Lines[i]+= " ";
	      //  //  }}
	      //  //Lines[i] = Lines[i].replace("//+", "\n  ");
	      //}
	    
	      for(int i=0;i<Lines.length;i++){
	      textBox += Lines[i];
	      for(int j=0;j<Lines[i].length();j++){
	      //label_backup += Lines[i];
	      //textbox.add(new Letter(str(Lines[i].charAt(j)),x,y));
	      //lines.add(i);
	      }}
	    backup = Lines;
	    textsize = 12;
	    if(Lines.length> rows){
	      rows = PApplet.floor(Lines.length);
	      h = textsize * rows;
	      }
	    totalwidth = w * rows;
	    size = textbox.size();
	    tbox = false;
	    //toolBox = new Menu(x+1,y-20,200-1,20);
	    testcode(textBox);
	    }
	  };
	  
	  public void initColors() {
		  
		  col = applet.color(0, 255, 73);
		  col2 = applet.color(0, 255, 73,100);
	  };
	  
	  public void testcode(String a){
	      
	      //for(
	    };
	    
	  public void save(){
	    
	  };
	  
	  public void load(){
	    
	  };
	  
	  public void init(){
	    
	  };
	  
	  public void getKey2(){
	   
	    if(toggle==1){
	      if(!applet.keyPressed){
	        timer=101;
	        firstPress = false;
	      }
	      if(applet.keyPressed&&applet.key != PConstants.CODED){
	        if((timer%delay==0&&!firstPress)||(firstPress&&timer%delay2==0)||timer==maxCount-1){
	          getChar = true;
	        }else getChar = true;
	        if(timer<0)firstPress=true;
	      }
	    }
	  };
	  
	  public void draw(){
	    
	    twidth = 0;
	    box();
	    getCursor();
	    mpos();
	    nav();
	    conditionals();
	    highlight();
	    //selectall();
	    toolbox();
	    error();
	    //debug();
	    if(!tsize)getKey();;
	    
	    //----------------------------------------------------- amend letters
	    float c1 = 0;
	    int cindex = -1;
	    
	    for(int i=0;i<textBox.length();i++){
	      
	      //text(textbox.get(i).l,x+ 10 *i,y);
	       Letter a = null;
	       String b = PApplet.str(textBox.charAt(i));
	       
	      if(textbox.size()==textBox.length()){a = textbox.get(i);}
	        else{a = new Letter(b,x,y,Bms);textbox.add(a);}
	      
	      if(a!=null){
	        if(c1 + a.w > w)c1=0;
	        if(c1==0){cindex++;
	        a.id = i;
	        a.vpos = cindex;
	        boolean k = lines.contains(i);
	        if(!k)lines.add(i);
	      }
	      }
	      
	      if(a==null&&b!=null){
	        a = new Letter(b,x,y,Bms);
	        if(a.id!=i)a.id = i;
	        a.y = y + cindex*textsize;
	        a.x = x+c1;
	        a.vpos = cindex;
	        textbox.add(a);
	      }
	      
	      if(a.l!=b){ 
	      textbox.get(i).l = b;
	      a.w = applet.textWidth(b);
	      if(a.id!=i)a.id = i;
	      a.y = y + cindex*textsize;
	      a.x = x+c1;
	      a.vpos = cindex;
	      }
	      applet.fill(a.col2);
	      applet.text(a.l,a.x,a.y+textsize);
	      c1 += applet.textWidth(a.l);
	      
	      if(selectAll&&toggle==1&&dragh.size()==textBox.length()){
	        
	        if(dragh.get(i)!= i) dragh.set(i,i);
	      }}
	    
	    //-------------------------------------------------
	    
	        for(int j=0;j<dragh.size();j++){
	            Letter b = null;
	            if(j<dragh.size()-1){
	              if(dragh.get(j)<textbox.size()){
	              b = textbox.get(dragh.get(j));
	              applet.fill(0,0,255,125);
	              applet.noStroke();
	              applet.rect(b.x,b.y,b.w,b.h);
	          }}}
	        if(!applet.mousePressed){
	          //dragh = new ArrayList<Integer>();
	        }
	        applet.strokeWeight(1);
	  };
	  
	  public void draw(PGraphics canvas){
	    
	    twidth = 0;
	    box(canvas);getCursor(canvas);mpos(mouse);nav();conditionals(canvas);highlight(canvas);selectall();toolbox(canvas);error(canvas);
	    //debug();
	    if(!tsize)getKey();;
	    
	    //----------------------------------------------------- amend letters
	    float c1 = 0;
	    int cindex = -1;
	    
	    for(int i=0;i<textBox.length();i++){
	      //text(textbox.get(i).l,x+ 10 *i,y);
	       Letter a = null;
	       String b = PApplet.str(textBox.charAt(i));
	       
	      if(textbox.size()==textBox.length()){a = textbox.get(i);}
	        else{a = new Letter(b,x,y,Bms);textbox.add(a);}
	      
	      if(a!=null){
	        if(c1 + a.w > w)c1=0;
	        if(c1==0){cindex++;
	        a.id = i;
	        a.vpos = cindex;
	        boolean k = lines.contains(i);
	        if(!k)lines.add(i);
	      }
	      }
	      
	      if(a==null&&b!=null){
	        a = new Letter(b,x,y,Bms);
	        if(a.id!=i)a.id = i;
	        a.y = y + cindex*textsize;
	        a.x = x+c1;
	        a.vpos = cindex;
	        textbox.add(a);
	      }
	      
	      if(a.l!=b){ 
	      textbox.get(i).l = b;
	      a.w = applet.textWidth(b);
	      if(a.id!=i)a.id = i;
	      a.y = y + cindex*textsize;
	      a.x = x+c1;
	      a.vpos = cindex;
	      }
	      canvas.fill(a.col2);
	      canvas.text(a.l,a.x,a.y+textsize);
	      c1 += applet.textWidth(a.l);
	      
	      if(selectAll&&toggle==1&&dragh.size()==textBox.length()){
	        
	        if(dragh.get(i)!= i) dragh.set(i,i);
	      }}
	    
	    //-------------------------------------------------
	    
	        for(int j=0;j<dragh.size();j++){
	            Letter b = null;
	            if(j<dragh.size()-1){
	              if(dragh.get(j)<textbox.size()){
	              b = textbox.get(dragh.get(j));
	              canvas.fill(0,0,255,125);
	              canvas.noStroke();
	              canvas.rect(b.x,b.y,b.w,b.h);
	          }}}
	        if(!applet.mousePressed){
	          //dragh = new ArrayList<Integer>();
	        }
	        canvas.strokeWeight(1);
	  };
	  
	  public void selectall(){
	    
	    if(toggle==1&&ctrl&&selectAll){
	      applet.fill(0);
	      applet.text("Select all", 100,200);
	    }
	  };
	  
	  String getValue(){
	    return textBox;
	  };
	  
	  ArrayList getObject(){
	    return textbox;
	  };
	  
	  public void regex(){
	    
	  };
	  
	  public void toolbox(){
	    //tbox = true;
	    if(tbox){
	      applet.stroke(col);
	      applet.fill(col2);
	      //toolBox.draw();
	      // btn quote
	      // btn Bold
	      // btn Italic
	      // btn Underlined
	      // btn Link
	      // btn Quotes
	      // btn Html tag
	      // btn Picture
	      // btn Bulletpoints
	      // btn Numbered
	      // btn Smiley
	      // btn Calendar
	      // btn Options
	      
	    }
	    
	  };
	  
	  public void toolbox(PGraphics canvas){
	    //tbox = true;
	    if(tbox){
	      canvas.stroke(col);
	      canvas.fill(col2);
	      //toolBox.draw();
	      // btn quote
	      // btn Bold
	      // btn Italic
	      // btn Underlined
	      // btn Link
	      // btn Quotes
	      // btn Html tag
	      // btn Picture
	      // btn Bulletpoints
	      // btn Numbered
	      // btn Smiley
	      // btn Calendar
	      // btn Options
	      
	    }
	    
	  };
	  
	  public void highlight(){
	    float c = 0;
	        int d = 0;
	      
	      if(textbox.size()>0){
	        
	        for(int i=0;i<textbox.size();i++){
	          Letter a = textbox.get(i);
	          applet.noStroke();
	          if(a.pos()){a.col = applet.color(255,255,0);applet.fill(a.col);applet.rect(a.x,a.y,a.w,a.h);}
	          else a.col  = applet.color(0);
	          if(applet.mousePressed&&a.pos()){
	            vindex = a.vpos;
	            hindex = a.id;
	            cursorx = a.x-x+a.w;
	            cursory = a.y;
	            boolean n = dragh.contains(i);
	            if(!n) dragh.add(i);
	            //text(a.id,a.x + x,a.y);
	          }}}
	  };
	  
	  public void highlight(PGraphics canvas){
	    float c = 0;
	        int d = 0;
	      
	      if(textbox.size()>0){
	        
	        for(int i=0;i<textbox.size();i++){
	          Letter a = textbox.get(i);
	          canvas.noStroke();
	          if(a.pos(mouse)){a.col = applet.color(255,255,0);canvas.fill(a.col);canvas.rect(a.x,a.y,a.w,a.h);}
	          else a.col  = applet.color(0);
	          if(applet.mousePressed&&a.pos(mouse)){
	            vindex = a.vpos;
	            hindex = a.id;
	            cursorx = a.x-x+a.w;
	            cursory = a.y;
	            boolean n = dragh.contains(i);
	            if(!n) dragh.add(i);
	            //text(a.id,a.x + x,a.y);
	          }}}
	  };
	  
	  public void getKey(){
	   
	    if(toggle==1){
	    String clipboard = null;
	    Letter current = null;
	    
	    String a = getChar();
	    String tm = "";
	    String tm2 = "";
	    tm0 = new ArrayList<Letter>();
	    tm1 = new ArrayList<Letter>();
	    float current_width = applet.textWidth(textBox);
	    
	    if(textbox.size()>0&&hindex!=-1)current = textbox.get(hindex);
	    if(textbox.size()>0&&hindex==-1)current = textbox.get(textbox.size()-1);
	    
	    if(!clipboardToggle)cboard = "";
	    
	    if(cboard != Bms.clipBoard&&copyClipboard){ 
	      clipboard = Bms.clipBoard;
	      cboard = Bms.clipBoard;
	    }
	    else clipboard = null;
	    float delay = delay2;
	    if(!copyClipboard&&!clipboardToggle){
	      
	        timer --;
	        Letter l = null;
	        if(setDelay)delay = inputDelay;
	        if(a!=null) l = new Letter(a,x+strwidth,y + vcount * textsize,Bms);
	        if(a!=null&&getChar&&applet.key!=PConstants.BACKSPACE&&(current_width+ l.w<totalwidth)){
	          
	          l.id = hindex +1;
	          
	          if(hindex==-1){
	            if(textbox.size()==0){
	              l.y = y;
	              l.x = x;
	              textbox.add(l);
	              textBox += a;
	            }
	              else if(l.vpos < rows||l.x+l.w<x+w){
	              l.y = y + vcount * textsize;
	              cursory = l.y;
	              textbox.add(l);
	              textBox += a;
	              
	          }}else{
	            
	            Letter end = textbox.get(textbox.size()-1);
	            Letter n = new Letter(a,cursorx,cursory,Bms);
	            if((hindex!=textbox.size()-1||hindex!=-1)&&getChar){
	              
	            Letter b = textbox.get(hindex);
	            l.id = b.id ;
	            
	            n.x = cursorx ;
	            if(hindex-1> 0)l.y = y + textbox.get(hindex-1).vpos * textsize;
	                else l.y = y; cursory = y;
	                
	            if(hindex<textBox.length())tm = textBox.substring ( 0, hindex + 1);
	            tm += a;
	            if(hindex<textBox.length())tm2  = textBox.substring ( hindex + 1, textBox.length()  );
	            textBox = tm + tm2;
	            hindex ++;
	            if(hindex < textbox.size()-1)cursorx = textbox.get(hindex+1).x;
	          }}
	          
	          if(timer<=0){
	            timer = maxCount;
	          }}
	        else if(applet.keyPressed && applet.key==PConstants.BACKSPACE&&getChar){delete();}
	        //setDelay = true;
	    }
	    else if(copyClipboard&&clipboardToggle&&!tsize){
	      
	            if(hindex>-1){
	            if(hindex<textBox.length())tm = textBox.substring ( 0, hindex + 1  );
	            if(hindex<textBox.length())tm2  = textBox.substring ( hindex + 1, textBox.length()  );
	            tm += cboard;
	            tm += tm2;
	            
	            textBox = tm;
	            
	         }else{
	             
	              for (int i=0;i<cboard.length();i++){
	                String b = PApplet.str(cboard.charAt(i));
	                textBox += b;
	              }}
	              clipboardToggle = false;
	              copyClipboard = false;
	              }}
	  };
	  
	  
	  //((((
	  
	  public void error(){
	    
	    if(Bms.clipBoard.length()>0&&applet.textWidth(Bms.clipBoard)+applet.textWidth(textBox)*textsize/12>w*(rows-1)&&copyClipboard&&clipboardToggle){tsize = true;}
	    
	    if(toggle==1||pos()){
	          if(tsize){
	              String message = "ClipBoard too large";
	              float l = applet.textWidth(message);
	              applet.stroke(0);
	              applet.fill(255);
	              applet.rect(applet.width/2-l/2+200,applet.height/2-textsize,l*2,textsize*2,r1,r2,r3,r4);
	              applet.fill(0);
	              applet.text(message,applet.width/2+200,applet.height/2);
	          
	          }
	          
	          if(tsize&&applet.mousePressed)tsize = false;
	    }
	  };

	public void error(PGraphics canvas){

	  if(Bms.clipBoard.length()>0&&applet.textWidth(Bms.clipBoard)+applet.textWidth(textBox)*textsize/12>w*(rows-1)&&copyClipboard&&clipboardToggle){tsize = true;}
	    
	  if(mouse!=null&&(toggle==1||pos(mouse))){
	          if(tsize){
	              String message = "ClipBoard too large";
	              float l = applet.textWidth(message);
	              canvas.stroke(0);
	              canvas.fill(255);
	              canvas.rect(applet.width/2-l/2+200,applet.height/2-textsize,l*2,textsize*2,r1,r2,r3,r4);
	              canvas.fill(0);
	              canvas.text(message,applet.width/2+200,applet.height/2);
	          
	          }
	          
	          if(tsize&&applet.mousePressed)tsize = false;
	    }
	};

	  public void getCursor(){
	    
	    getClick();
	    
	    if(hindex==-1){
	      if(textbox.size()==0){
	        cursory = y;
	        cursorx = 1;
	        vindex = 0;
	      }else{
	      if(textbox.size()>0&&hindex<textbox.size()){
	      cursory = y + textbox.get(textbox.size()-1).vpos * textsize;
	      cursorx = textbox.get(textbox.size()-1).x + textbox.get(textbox.size()-1).w*(textsize/12)-x;
	      }
	    }}
	    if(hindex>-1){
	      if(textbox.size()>hindex+1){
	      cursorx = textbox.get(hindex).x + textbox.get(hindex).w*(textsize/12) - x;
	      cursory = y + textbox.get(hindex).vpos * textsize;
	    }}
	    if(pos()||toggle==1){
	      //cursor(POINT);
	        if(t1>0){
	          t1 --;
	          if(strheight<y+h){
        	  applet.stroke(0);
        	  applet.strokeWeight(1);
        	  applet.line(x+cursorx,cursory ,x+cursorx,cursory+textsize);
	          }
	        if(t1<=0)t2 = blkrate;
	        }
	        if(t2>0&&t1<=0){
	          t2--;if(t2<=0)t1 = blkrate;
	          }}
	          //else {
	          //  cursor(ARROW);
	          //}
	  };
	  
	  public void getCursor(PGraphics canvas){
	    
	    getClick();
	    
	    if(hindex==-1){
	      if(textbox.size()==0){
	        cursory = y;
	        cursorx = 1;
	        vindex = 0;
	      }else{
	      if(textbox.size()>0&&hindex<textbox.size()){
	      cursory = y + textbox.get(textbox.size()-1).vpos * textsize;
	      cursorx = textbox.get(textbox.size()-1).x + textbox.get(textbox.size()-1).w*(textsize/12)-x;
	      }
	    }}
	    if(hindex>-1){
	      if(textbox.size()>hindex+1){
	      cursorx = textbox.get(hindex).x + textbox.get(hindex).w*(textsize/12) - x;
	      cursory = y + textbox.get(hindex).vpos * textsize;
	    }}
	    if(pos(mouse)||toggle==1){
	      //cursor(POINT);
	        if(t1>0){
	          t1 --;
	          if(strheight<y+h){
	          canvas.stroke(0);
	          canvas.strokeWeight(1);
	          canvas.line(x+cursorx,cursory ,x+cursorx,cursory+textsize);
	          }
	        if(t1<=0)t2 = blkrate;
	        }
	        if(t2>0&&t1<=0){
	          t2--;if(t2<=0)t1 = blkrate;
	          }}
	          else {
	            //cursor(ARROW);
	          }
	  };
	  
	  public void delete(){
	    Letter current = null;
	    
	    if(textbox.size()>0&&hindex!=-1)current = textbox.get(hindex);
	    if(textbox.size()>0&&hindex==-1)current = textbox.get(textbox.size()-1);
	    
	    String tm1 = "",tm2 = "";
	    String tma;
	    
	    if(applet.keyPressed && applet.key == PConstants.BACKSPACE){
	      if(hindex==-1&&textBox.length()>0){
	        if(current!=null&&current.id>0){
	          //text
	          if(lines.get(lines.size()-1) > current.id-1) lines.remove(lines.size()-1);
	          if(lines.size()-1>0&&current.vpos<lines.size()-1){
	              vcount --;
	              lines.remove(lines.size()-1);
	          }
	          hindex = current.id;
	          textbox.remove(textbox.size()-1);
	          textBox = textBox.substring ( 0, textBox.length()-1  );
	        }else{
	          textbox = new ArrayList<Letter>();
	          textBox = "";
	        }
	      }
	      else{
	        if(current!=null&&current.id>-1){
	          if(current!=null&&lines.get(lines.size()-1) > textbox.get(textbox.size()-1).id-1) lines.remove(lines.size()-1);
	          if(lines.size()>0&&textbox.get(textbox.size()-1).vpos<lines.size()-1){
	              vcount --;
	              lines.remove(lines.size()-1);
	          }
	          hindex = current.id-1;
	          
	          textbox.remove(textbox.size()-1);
	          if(current.id >0){
	            tm1 = textBox.substring ( 0, hindex+1 );
	            tm2 = textBox.substring ( hindex +2, textBox.length()  );
	          }else if(current.id==0){
	            tm1 = "";
	            tm2 = textBox.substring ( 0, textBox.length()  );
	          }
	          textBox = tm1 + tm2;
	          
	        }}}
	    
	  };
	  
	  public void paste(){
	    
	    for(int i=0;i<cboard.length();i++){
	      String a = PApplet.str(cboard.charAt(i));
	      getKey();
	    }
	    
	  };
	  
	  public void box(){
	    if(!hidden){
	      //------------------------------- border
	      if(border)applet.stroke(col2);
	      else applet.noStroke();
	      //-------------------------------label
	      if(label!=null){
	    	applet.fill(col2);
	    	applet.text(label,x,y+textsize);
	      }
	      //---------------------------------fill
	      if(fill)if(pos())applet.fill(col);else applet.fill(0);
	        else applet.noFill();applet.fill(col);
	      
	        applet.rect(x,y,w,h,r1,r2,r3,r4);
	      }
	  };

	  public void box(PGraphics canvas){
	    if(!hidden){
	      //------------------------------- border
	      if(border)canvas.stroke(col2);
	      else canvas.noStroke();
	      //-------------------------------label
	      if(label!=null){
	        canvas.fill(col2);
	        canvas.text(label,x,y+textsize);
	      }
	      //---------------------------------fill
	      if(fill)if(pos())canvas.fill(col);else canvas.fill(0);
	        else canvas.noFill();canvas.fill(col);
	      
	      canvas.rect(x,y,w,h,r1,r2,r3,r4);
	      }
	  };
	  
	  Letter sort(ArrayList<Letter>a){
	    Letter k = a.get(0);
	    
	    for(int i=0;i<a.size();i++){
	      
	      if(k.id>a.get(i).id){
	        k = a.get(i);
	      }}
	    return k;
	  };
	  
	  public void conditionals(){
	    
	    if(vpos<=0) vpos = 0;
	    
	    if(selectAll&&toggle==1){
	        if(dragh.size()!=textBox.length())dragh.add(0);
	      }
	     
	    if(applet.keyPressed){
	      timer --;
	    }else {
	      timer = maxCount;
	      firstPress = false;
	    }
	    if(lines.size()>rows)lines.remove(lines.size()-1);
	    if(lines.size()<=1)vcount = 0;
	    if(toggle==1)clear = true;
	    if(applet.mousePressed&&!pos()){
	      //vindex = -1;
	      hindex = -1;
	    }
	    if(applet.mousePressed&&pos()){toggle=1;}
	    
	    if(toggle==1){
	    if(applet.keyPressed){
	      //applet.println(timer);
	        if((timer==maxCount-2||(timer%delay==0&&!firstPress))||(firstPress&&timer%delay2==0)){
	          getChar = true;
	        }else getChar = false;
	        if(timer<0)firstPress=true;
	      }else getChar = false;
	    }
	    //if(applet.keyPressed&&applet.keyCode==16)firstPress = false;
	  };
	  
	  public void conditionals(PGraphics canvas){
	    
	    if(vpos<=0) vpos = 0;
	    
	    if(selectAll&&toggle==1){
	        if(dragh.size()!=textBox.length())dragh.add(0);
	      }
	    if(applet.keyPressed){
	      timer --;
	    }else {
	      timer = maxCount;
	      firstPress = false;
	    }
	    if(lines.size()>rows)lines.remove(lines.size()-1);
	    if(lines.size()<=1)vcount = 0;
	    if(toggle==1)clear = true;
	    if(applet.mousePressed&&!pos(mouse)){
	        //vindex = -1;
	        hindex = -1;
	      }
	      if(applet.mousePressed&&pos(mouse)){toggle=1;}
	      if(toggle==1){
	    if(applet.keyPressed){
	      //println(timer);
	        if((timer==maxCount-2||(timer%delay==0&&!firstPress))||(firstPress&&timer%delay2==0)){
	          getChar = true;
	        }else getChar = false;
	        if(timer<0)firstPress=true;
	      }else getChar = false;
	    }
	  };
	  
	  public void constants(){
	    strheight = (applet.floor(applet.textWidth(textBox)/(w)));
	    strwidth = ((applet.textWidth(textBox))%(w));
	  };
	  
	  boolean exclusion(){
	    boolean k = false;
	    
	    if(applet.keyCode==37||applet.keyCode==38||applet.keyCode==39||applet.keyCode==40||applet.keyCode==33||applet.keyCode==34||applet.keyCode==17||applet.keyCode==18||applet.keyCode==9||
	       applet.keyCode==20||applet.keyCode==16||applet.keyCode==255||applet.keyCode==91||applet.keyCode==36||applet.keyCode==35||applet.keyCode==45||applet.keyCode==46||applet.keyCode==8){
	      k = true;
	    }
	    return k;
	  };
	  
	  public void select_All(){
	    if(selectAll){
	      
	    }
	  };
	  
	  public void debug(){
		applet.fill(255);
		applet.text(textbox.size(),140,80);
		applet.text(backup.length,140,90);
		applet.text(totalwidth,140,100);
	    //text(currentwidth,140,110);
	    
		applet.text("lines " + lines.size(),140,150);
	    if(hindex>-1){applet.text(hindex,140,120);}
	    else {applet.text(hindex,140,120);}
	    if(vcount>-1)applet.text(vcount,140,130);
	    //if(clipBoard!=""){text(clipBoard,140,100);}
	    for(int i=0;i<lines.size();i++){
	    	applet.text(lines.get(i),x+x,y+textsize+10*i);
	    }
	  };

	  String getChar(){
	    String a = null;
	    
	    if(applet.keyPressed){
	      a  = PApplet.str(applet.key);
	    }
	    if(applet.keyCode==9) a = null;
	    
	    return a;
	    
	  }
	  char getchar(){
	    char a = 'k';
	    
	    if(applet.keyPressed){
	      a  = (applet.key);
	    }
	    if(applet.keyCode == 8){
	      
	    }
	    pos();
	    return a;
	    
	  };
	  
	  boolean pos(){
	    
	    float X = applet.mouseX;
	    float Y = applet.mouseY;
	    
	    return X > x && X < x + w && Y > y && Y < y + h; 
	  };
	  
	  boolean pos(PVector m){
	    
	    float X = m.x;
	    float Y = m.y;
	    
	    return X > x && X < x + w && Y > y && Y < y + h-20; 
	  };
	  
	  public void calc_lwidth(){
	    for (int i=0;i<strheight+1;i++){
	      for(int j=0;j<w;j++){
	        
	        
	    }}
	  };
	  
	  public void insert(){
	    if(hindex>-1){
	      
	    }else{
	      
	    }
	  };
	  
	  public void mpos(){
	    float X = applet.mouseX;
	    float Y = applet.mouseY;
	    float ww = w/cols;
	    float hh = h/rows;
	    
	    if(pos())posx = PApplet.floor(X/ww)*ww-10;posy = PApplet.floor(Y/hh)*hh;
	  };
	  
	  public void mpos(PVector m){
	    float X = m.x;
	    float Y = m.y;
	    float ww = w/cols;
	    float hh = h/rows;
	    
	    if(pos(m))posx = PApplet.floor(X/ww)*ww-10;posy = PApplet.floor(Y/hh)*hh;
	  };
	  
	  public void getClick(){
	  if(pos() && applet.mousePressed)toggle=1;
	  if(!pos()&& applet.mousePressed)toggle=0;
	  //if(toggle==2)toggle=0;
	  };
	  
	  public void getClick(PVector m){
	  if(pos(m) && applet.mousePressed)toggle=1;
	  if(!pos(m)&& applet.mousePressed)toggle=0;
	  //if(toggle==2)toggle=0;
	  };
	  
	  public void nav(){
	    if(hindex>=textbox.size()-1){hindex  =-1;}
	    if(textbox.size()>0){
	      if(hindex>=1){
	    if(applet.keyPressed && applet.keyCode == 37 &&getChar){hindex --;}}
	    if(hindex<=textbox.size()-1&&hindex>-1){
	    if(applet.keyPressed && applet.keyCode == 39 &&getChar){ hindex ++;}}
	    //if(applet.keyPressed && key == UP && strheight >0) hindex += vpos *2;
	    //if(applet.keyPressed && key == DOWN && strheight )
	    
	    if(hindex==-1){
	      if(applet.keyPressed && applet.keyCode == 37 &&getChar){hindex = textbox.size()-2;}
	      //if(applet.keyPressed && applet.keyCode == 39 &&getChar){ hindex = 0;}
	    }
	    
	    
	    }
	  };
	  
	  public void setRadius(float a) {
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
	  };
	  
	  public void setRadius(float a,float b,float c,float d) {
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;
	  };
	  
	  public void setPos(float a,float b) {
		  x = a;
		  bx = a;
		  y = b;
		  by = b;
	  };
};

	
