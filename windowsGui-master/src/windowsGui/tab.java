package windowsGui;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;

public class tab extends tabBoundary {
	  BMScontrols Bms;
	  PApplet applet;
	  public PGraphics canvas,canvas2;
	  float x, y, w, h,bx,by,bh,bw,r1,r2,r3,r4,transparency;
	  int tabindex = -1, current,id,Width,Height;
	  public int state;
	  public String label,itemLabel;
	  public boolean border,open,parentCanvas,overflow,docking,docked,dmdown;
	  public boolean localTheme,resizable,vscroll,hscroll;
	  public PVector mouse,mouse2;
	  public Dock parentDock; 
	  
	  public ArrayList<Grid> grids = new ArrayList<Grid>();
	  
	  public ArrayList<Window> windows = new ArrayList<Window>();
	  public ArrayList<Button> buttons = new ArrayList<Button>();
	  public ArrayList<PImage> images = new ArrayList<PImage>();
	  public ArrayList<PImage> temp_images = new ArrayList<PImage>();
	  public ArrayList<Menu> menus = new ArrayList<Menu>();
	  public ArrayList<Slider> sliders = new ArrayList<Slider>();
	  public ArrayList<sliderBox> sliderBoxes = new ArrayList<sliderBox>();
	  public ArrayList<Dropdown> dmenus = new ArrayList<Dropdown>();
	  public ArrayList<toggleMenu> toggleMenus = new ArrayList<toggleMenu>();
	  public ArrayList<Table_> tables = new ArrayList<Table_>();
	  public ArrayList<String> links = new ArrayList<String>();
	  
	  public ArrayList<TextArea> textareas = new ArrayList<TextArea>();
	  public ArrayList<textBlock> textBlocks = new ArrayList<textBlock>();
	  public ArrayList<TextBox> textboxes = new ArrayList<TextBox>();
	  public ArrayList<String[]> temp_text = new ArrayList<String[]>();
	  public ArrayList<processing.data.Table> temp_tables = new ArrayList<processing.data.Table>();
	  public String folder = "",file = "";
	  HashMap<String, Boolean> keys = new HashMap<String, Boolean>();

	  ArrayList<Boolean> bools = new ArrayList<Boolean>();
	  PVector window;
	  public Slider sliderv;
	  public Slider sliderh;
	  public tab navigator;
	  public tab child, parent,parentTab;
	  public tab current_tab, next_tab, previous_tab;
	  public int titleColor,textColor,tabcol;


	  public ArrayList<tab> tabs = new ArrayList<tab>();
	  public ArrayList<tab> states = new ArrayList<tab>();
	  public ArrayList<Integer> transitions = new ArrayList<Integer>();
	  public boolean visible = true, scrollable,draggable,drag;
	  boolean sliderset, displayChild, init, setTab,slidersUpdated;
	  public Button title;

	  public tab( float x, float y, float w, float h,int k,BMScontrols bms) {
	    
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	    Bms = bms;
	    applet = bms.applet;
	    canvas = createCanvas(bms);
	    //tabs.add(this);
	    states.add(this);
	    createConstruct();
	    tabcol = applet.color(0,255,175);
	  };
	  
	  public tab( float x, float y, float w, float h,BMScontrols bms) {
	    
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	    Bms = bms;
	    applet = bms.applet;
	    //tabs.add(this);
	    states.add(this);
	    canvas = createCanvas(bms);
	    tabcol = applet.color(0,255,175);
	    createConstruct();
	  };
	  
	  public tab( float x, float y, float w, float h,Slider s,BMScontrols bms) {
	    
	    
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	    Bms = bms;
	    applet = bms.applet;
	    canvas = createCanvas(bms);
	    states.add(this);
	    tabcol = applet.color(0,255,175);
	  };
	  
	  public tab( float x, float y, float w, float h,Boundary b,BMScontrols bms) {
	    
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	    Bms = bms;
	    applet = bms.applet;
	    canvas = createCanvas(bms);
	    states.add(this);
	    createConstruct2();
	    tabcol = applet.color(0,255,175);
	  };
	  
	  public tab( float x, float y, float w, float h, String label,int k,BMScontrols bms) {
	    //super();
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	    Bms = bms;
	    applet = bms.applet;
	    canvas = createCanvas(bms);
	    this.label = label;
	    this.itemLabel = label;
	    title = new Button(0, 0, w, 20, label,bms);
	    title.border = false;
	    tabcol = applet.color(0,255,175);
	    createConstruct();
	  };

	  public tab( float x, float y, float w, float h, String label,BMScontrols bms) {
	    
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	    Bms = bms;
	    applet = bms.applet;
	    canvas = createCanvas(bms);
	    this.label = label;
	    this.itemLabel = label;
	    title = new Button(0, 0, w, 20, label,bms);
	    title.border = false;
	    tabcol = applet.color(0,255,175);
	    createConstruct();
	  };
	  
	    public tab( float x, float y, float w, float h, String label,boolean n,BMScontrols bms) {
	    
	    this.x = x;
	    this.y = y;
	    this.w = w;
	    this.h = h;
	    Bms = bms;
	    applet = bms.applet;
	    canvas = createCanvas(bms);
	    this.label = label;
	    this.itemLabel = label;
	    title = new Button(0, 0, w, 20, label,bms);
	    title.border = false;
	    tabcol = applet.color(0,255,175);
	    createConstruct();
	  };

	  tab() {
	  };
	  
	  public void createConstruct(){
	    bx = x;
	    by = y;
	    Window w1 = new Window(83, 80, 200, 200, "C:\\Users\\paul goux\\",Bms);
	    
	    windows.add(w1);

	    sliderv = new Slider(w-10, 20, 10, h-20,Bms);
	    sliderv.classic = true;
	    sliderv.bar = true;
	    sliderv.vertical = true;
	    sliderv.tooltip = null;
	    sliderv.parentCanvas = true;
	    sliderv.parentTab = this;
	    sliderv.initColors();
//	    
	    sliderh = new Slider(0, h-10, w-10, 10,Bms);
	    sliderh.tooltip = null;
	    sliderh.classic = true;
	    sliderh.bar = true;
	    sliderh.parentCanvas = true;
	    sliderh.parentTab = this;
	    sliderh.initColors();
	    Boundaries.add(new tabBoundary(0, 0, w, h, 4,Bms));
	    states.add(this);
	  };
	  
	  public void createConstruct2(){

	    sliderv = new Slider(w-10, 0, 10, h-10);
	    sliderv.classic = true;
	    sliderv.bar = true;
	    sliderv.vertical = true;
	    sliderv.tvisible = false;
	    sliderv.Bms = Bms;
	    sliderv.applet = applet;
	    sliderv.initColors();
	    sliderh = new Slider(0, h-10, w-10, 10);
	    sliderh.tvisible = false;
	    sliderh.classic = true;
	    sliderh.bar = true;
	    sliderh.Bms = Bms;
	    sliderh.applet = applet;
	    sliderh.initColors();
	    //Boundaries.add(new Boundary(0, 0, w, h, 4));
	  };
	  
	  public void setvScroll() {
		  if(title!=null)sliderv = new Slider(w-10, title.h, 10, h-10,Bms);
	      else sliderv = new Slider(w-10, 0, 10, h-10,Bms);
		  sliderv.classic = true;
		  sliderv.bar = true;
          sliderv.vertical = true;
	      sliderv.tvisible = false;
	      sliderv.initColors();
	      scrollable = true;
	      vscroll = true;
	      //hscrol
		  
	  };
	  
	  public void sethScroll() {
		  
		  sliderh = new Slider(0, h-10, w-10, 10,Bms);
	      sliderh.tvisible = false;
	      sliderh.classic = true;
	      sliderh.bar = true;
	      sliderh.initColors();
	      scrollable = true;
	      hscroll = true;
	  };
	  
	  public void setScroll() {
		  
		  sliderh = new Slider(0, h-10, w-10, 10,Bms);
	      sliderh.tvisible = false;
	      sliderh.classic = true;
	      sliderh.bar = true;
	      sliderh.initColors();
	      scrollable = true;
	      hscroll = true;
	      
	      if(title!=null)sliderv = new Slider(w-10, title.h, 10, h-10,Bms);
	      else sliderv = new Slider(w-10, 0, 10, h-10,Bms);
		  sliderv.classic = true;
		  sliderv.bar = true;
          sliderv.vertical = true;
	      sliderv.tvisible = false;
	      sliderv.initColors();
	      scrollable = true;
	      vscroll = true;
	  };
	  
	PGraphics createCanvas(BMScontrols bms) {
      Bms = bms;
	  PGraphics pg = bms.applet.createGraphics((int) (w), (int)(h));
	  //pg.setLocation(x, y);
	  return pg;
	}

	PGraphics createCanvas2(BMScontrols bms) {
	  Bms = bms;
	  PGraphics pg = bms.applet.createGraphics((int) (w), (int)(h));
	  return pg;
	}

	PGraphics createCanvas(float w,float h,BMScontrols bms) {
	  Bms = bms;
	  PGraphics pg = bms.applet.createGraphics((int) (w), (int)(h));
	  return pg;
	}

	public void setCanvas(tab t){
	    if (!sliderh.mdown)sliderv.mouseFunctions(t.mouse);
	    // fill(0);
	    // ellipse(t.mouse.x,t.mouse.y,20,20);
	    sliderv.mouse = mouse;
	    sliderv.draw(t.canvas);
	    if (!sliderv.mdown)sliderh.mouseFunctions(t.mouse);
	    sliderh.mouse = mouse;
	    sliderh.draw(t.canvas);

	};
	  
	  public void save(){
	    
	  };
	  
	  public void load(){
	    
	  };
	  

	  public void displayTab(PGraphics scene) {
	    
	    tab t = states.get(state);
	    
	    if (toggle&&canvas!=null) {
	      t.mouse = mouse2;
	      mouse = mouse2;
	      canvas.beginDraw();
	      canvas.background(50);
	      logic();
	      t.drawBorder();
	      t.boundingBox();
	      
	      t.drawButtons();
	      t.drawMenus();
	      t.drawTextboxes();
	      t.drawTextareas();
	      t.drawTextBlocks();
	      t.drawSliderBoxes();
	    if(t.title!=null&&t.visible){
	      t.title.toggle=1;
	      t.drawTitle();
	      t.drawBorder();
	      t.drawDmenu();
	    }
	      //t.drawImages();
	    if(t.scrollable)t.drawSlider();
	      //t.drawWindows();
	      canvas.endDraw();
	      scene.image(canvas,x,y);
	    }
	  };
	  
	  public void displayTab() {
	    applet.fill(255);
	    tab t = states.get(state);
	    //applet.println(t.menus.size());
	    if (toggle&&visible&&canvas!=null) {
	        t.mouse = getMouse();
	        mouse = getMouse();
	      canvas.beginDraw();
	      //canvas.background(50);
	      logic();
	      t.drawBorder();
	      t.boundingBox();
	      t.drawButtons();
	      t.drawMenus();
	      t.drawTextboxes();
	      t.drawTextareas();
	      t.drawSliderBoxes();
	      t.drawToggleMenus();
	      t.drawTextBlocks();
	      if(t.title!=null&&t.visible){
	        if(!toggle)t.title.toggle=1;
	        t.drawTitle();
	        t.drawBorder();
	        t.drawDmenu();
	      }
	      //t.drawWindows();
	      if(t.scrollable)t.drawSlider();
	      // if(docked){
	      //   canvas.fill(0);
	      //   canvas.rect(0,0,w,h);
	      // }
	      canvas.endDraw();
	      applet.image(canvas,x,y);
	    }
	  };

	  
	  public void displayTabs() {
	    
	    tab t = states.get(state);
	    //applet.println(t.menus.size());
	    if (toggle&&t!=null&&canvas!=null) {
	      t.mouse = getMouse();
	      mouse = getMouse();
	      canvas.beginDraw();
	      canvas.background(50);
	      //for(int i=0;i<states.size();i++){
	      //tab t1 = states.get(i);
	      
	      //if(i!=state&&toggle){
	      //  t1.toggle = false;
	      //  t1.visible = false;
	      //  if(t1.title!=null)t1.title.toggle=1;
	      //}else t1.toggle=true;}
	      t.logic();
	      t.boundingBox();
	      t.drawButtons();
	      t.drawMenus();
	      t.drawTextboxes();
	      t.drawTextareas();
	      t.drawTable_s();
	      //t.displayInnerTabs();
	      t.drawText();
	      t.drawTitle();
	      t.drawBorder();
	      t.drawDmenu();
	      t.drawSliderBoxes();
	      t.drawTextBlocks();
	      //t.drawWindows();
	    if (t!=null&&t.scrollable&&t.toggle)t.drawSlider();
	      
	      canvas.endDraw();
	      applet.image(canvas,x,y);
	    }
	  };
	  
	  public void displayTabs(PGraphics scene) {
	    
	    tab t = states.get(state);
	    
	    if (toggle&&t!=null&&canvas!=null) {
	      t.mouse = mouse2;
	      mouse = mouse2;
	      
	      canvas.beginDraw();
	      
	      for(int i=0;i<states.size();i++){
	      tab t1 = states.get(i);
	      
	      if(i!=state&&toggle){
	        // t1.toggle = false;
	        // t1.visible = false;
	        // if(t1.title!=null)t1.title.toggle=1;
	      }else t1.toggle=true;}
	      t.logic();
	      t.boundingBox();
	      t.drawButtons();
	      t.drawMenus();
	      t.drawTextboxes();
	      t.drawTextareas();
	      t.drawTable_s();
	      //t.displayInnerTabs();
	      t.drawText();
	      t.drawTitle();
	      t.drawBorder();
	      t.drawDmenu();
	      t.drawSliderBoxes();
	      t.drawTextBlocks();
	    if (t!=null&&t.scrollable&&t.toggle)t.drawSlider();
	    
	      canvas.endDraw();
	      scene.image(canvas,x,y);
	      
	    }
	  };
	  
	  public void addState(int k){
	    if(title==null){
	    for(int i=0;i<k;i++){
	      states.add(new tab(x,y,w,h,Bms));
	    }}else{
	      for(int i=0;i<k;i++){
	      states.add(new tab(x,y,w,h,"hello"+i,Bms));
	    }}
	  };

	  public void addState(int k,String[] names){
	    if(title==null){
	    for(int i=0;i<k;i++){
	      states.add(new tab(x,y,w,h,Bms));
	    }}else{
	      for(int i=0;i<k;i++){
	      states.add(new tab(x,y,w,h,names[i],Bms));
	    }}
	  };
	  
	  public void setTitle(int i,String s){
	    tab k = states.get(i);
	    
	    if(k.title!=null)k.title.label = s;
	  };
	  
	  public Button add(int i,Button b){
	    //clear();
	    b.Bms = Bms;
	    b.applet = applet;
	    tab k = states.get(i);
	    b.parentCanvas = true;
	    k.buttons.add(b);
	    return b;
	  };
	  
	  public Menu add(int i,Menu m){
	    //clear();
	    m.Bms = Bms;
	    m.applet = applet;
	    tab k = states.get(i);
	    m.parentCanvas = true;
	    k.menus.add(m);
	    return m;
	  };
	  
	  public Dropdown add(int i,Dropdown d){
	    //clear();
	    d.Bms = Bms;
	    d.applet = applet;
	    tab k = states.get(i);
	    d.parentCanvas = true;
	    k.dmenus.add(d);
	    return d;
	  };
	  
	  public TextBox add(int i,TextBox t){
	    //clear();
	    t.Bms = Bms;
	    t.applet = applet;
	    tab k = states.get(i);
	    t.parentCanvas = true;
	    k.textboxes.add(t);
	    return t;
	  };
	  
	  public TextArea add(int i,TextArea t){
	    //clear();
	    t.Bms = Bms;
	    t.applet = applet;
	    tab k = states.get(i);
	    t.parentCanvas = true;
	    k.textareas.add(t);
	    return t;
	  };

	  public textBlock add(int i,textBlock t){
	    //clear();
	    t.Bms = Bms;
	    t.applet = applet;
	    tab k = states.get(i);
	    t.parentCanvas = true;
	    k.textBlocks.add(t);
	    return t;
	  };
	  
	  public String add(int i,String s){
	    //clear();
	    // tab k = states.get(i);
	    // k.textblock.add(s);
	    return s;
	  };
	  
	  public Table_ add(int i,Table_ t){
	    //clear();
	    t.Bms = Bms;
	    t.applet = applet;
	    tab k = states.get(i);
	    t.parentCanvas = true;
	    k.tables.add(t);
	    return t;
	  };
	  
	  public Button add(Button b){
	    //clear();
	    b.Bms = Bms;
	    b.applet = applet;
	    tab k = states.get(0);
	    b.parentCanvas = true;
	    k.buttons.add(b);
	    return b;
	  };
	  
	  public Menu add(Menu m){
	    //clear();
	    m.setBms(Bms);
	    tab k = states.get(0);
//	    m.x = x + m.bx;
	    m.parentCanvas = true;
	    m.localCanvas = canvas;
	    k.menus.add(m);
	    return m;
	  };
	  
	  public Dropdown add(Dropdown d){
	    //clear();
	    d.Bms = Bms;
	    d.applet = applet;
	    tab k = states.get(0);
	    d.parentCanvas = true;
	    k.dmenus.add(d);
	    return d;
	  };
	  
	  public TextBox add(TextBox t){
	    //clear();
	    t.Bms = Bms;
	    t.applet = applet;
	    tab k = states.get(0);
	    t.parentCanvas = true;
	    k.textboxes.add(t);
	    return t;
	  };
	  
	  public TextArea add(TextArea t){
	    //clear();
	    t.Bms = Bms;
	    t.applet = applet;
	    tab k = states.get(0);
	    t.parentCanvas = true;
	    k.textareas.add(t);
	    return t;
	  };
	  
	  public String add(String s){
	    //clear();
	    tab k = states.get(0);
	    //k.textBlocks.add(s);
	    return s;
	  };
	  
	  public Table_ add(Table_ t){
	    //clear();
	    t.Bms = Bms;
	    t.applet = applet;
	    t.parentCanvas = true;
	    tab k = states.get(0);
	    k.tables.add(t);
	    return t;
	  };
	  
	  public sliderBox add(sliderBox s){
	    //clear();
		s.setBms(Bms);
	    s.parentCanvas = true;
	    tab k = states.get(0);
	    k.sliderBoxes.add(s);
	    if(s.tooltip!=null) {
	    	s.tooltip.setBms(Bms);
	    }
//	    for (int i=0; i<k.sliderBoxes.size(); i++) {
//	        sliderBox s1 = k.sliderBoxes.get(i);
//	        s1.x = x+s1.bx;
//	        s1.y = y+s1.by;
//	    }
	    s.setRadius(r1,r2,r3,r4);
	    return s;
	  };
	  
	  toggleMenu add(toggleMenu s){
	    //clear();
	    s.Bms = Bms;
	    s.applet = applet;
	    s.parentTab = this;
	    tab k = states.get(0);
	    k.toggleMenus.add(s);
	    for (int i=0; i<k.toggleMenus.size(); i++) {
	      toggleMenu s1 = k.toggleMenus.get(i);
	      s1.x = x+s1.bx;
	      s1.y = y+s1.by;
	    }
	    return s;
	  };
	  
	  PVector getMouse(){
	    return new PVector(applet.mouseX-x,applet.mouseY-y);
	  };
	  
	  PVector getMouse2(){
	    return new PVector(applet.mouseX-x,applet.mouseY-y);
	  };
	  
	  PVector getMouse(PVector m){
	    return new PVector(m.x-x,m.y-y);
	  };
	  
	  public void clear(){
	    textboxes = new ArrayList<TextBox>();
	    grids = new ArrayList<Grid>();
	    textareas = new ArrayList<TextArea>();
	    //windows = new ArrayList<Window>();
	    buttons = new ArrayList<Button>();
	    images = new ArrayList<PImage>();
	    temp_images = new ArrayList<PImage>();
	    menus = new ArrayList<Menu>();
	    dmenus = new ArrayList<Dropdown>();
	    tables = new ArrayList<Table_>();
	    links = new ArrayList<String>();
	    textBlocks = new ArrayList<textBlock>();
	    //processedText = new ArrayList<vectorText>();
	    temp_text = new ArrayList<String[]>();
	    temp_tables = new ArrayList<processing.data.Table>();
	  };
	  
	  public void logic() {
	    if(!mdown){
	      drag = false;
	      if(Bms.currentObject == this)Bms.currentObject = null;
	    }
	    if (navigator!=null)navigator.displayTabs();
	    if (scrollable) {
	      sliderh.mouse = getMouse();
	      if (!sliderh.mdown) {
	        //sliderv.mouseFunctions();
	        //sliderv.set(-h+20, h-20, this, "window.y");
	      }
	      //sliderv.mouse = getMouse();
	      if (!sliderv.mdown) {
	        //sliderh.set(-w+10, w-10, this, "window.x");
	        //sliderh.mouseFunctions();
	      }
	    }
	    if(draggable&&Bms.sliderObject==null){
	      
	      drawDragBox();
	      if(!sliderv.mdown&&!sliderh.mdown&&title.pos(getMouse())&&applet.mousePressed&&!mdown&&!drag&&Bms.currentObject==null){
	        
	        Bms.currentObject = this;
	        
	        if(title.label!=null)Bms.currentMouseObject = title.label;
	        //applet.println("tab",Bms.currentMouseObject);
	        mdown = true;
	        docked = false;
	        docking = false;
	        dx = x - applet.mouseX;
	        dy = y - applet.mouseY;
	        drag = true;
	      }
	      
	      // if(mdown)drag = true;
	      // else {
	      //   drag = false;
	      //   // Bms.currentObject = null;
	      //   // Bms.currentMouseObject = null;
	      // }
	      
	      if(drag){
	        x = applet.mouseX+dx;
	        y = applet.mouseY+dy;

	        if(parentDock!=null&&parentDock.pos(new PVector(x,y))&&!docking){
	          docking = true;
	          parentDock.loc = itemLabel;
	          parentDock.currentObject = this;
	        }
	      }

	      if(!applet.mousePressed&&docking&&!docked){
	        //applet.println("docked");
	        docked = true;
	        docking = false;
	        x = bx;
	        y = by;
	      }
	      
	      if(!applet.mousePressed)mdown = false;
	    }
	  };
	  
	  public void drawSliderBoxes(){

	    for (int i=states.get(state).sliderBoxes.size()-1;i>-1; i--) {
	      
	      sliderBox s = null;
	      if(states.get(state).sliderBoxes.get(i)!=null){
	        s = states.get(state).sliderBoxes.get(i);
	        
	        if(scrollable&&hscroll&&sliderh!=null)s.x = s.bx - sliderh.value;
	        if(scrollable&&vscroll&&sliderv!=null)s.y = s.by - sliderv.value;
	        
	        s.mouse = getMouse();
	        s.parentTab = this;
	        s.draw(canvas);
	      }
	    }
	  };

	  public void drawSliderBoxes_(){
	    if(applet.mousePressed)PApplet.println("hh");
	    for (int i=states.get(state).sliderBoxes.size()-1;i>-1; i--) {
	      
	      sliderBox s = states.get(state).sliderBoxes.get(i);
	      if(scrollable&&vscroll&&sliderv!=null)s.menu.y = s.menu.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)s.x = s.bx - sliderh.value;
	      s.mouse = getMouse();
	      s.parentTab = this;
	      s.draw();
	    }
	  };

	  public void drawSliderBoxes(PGraphics canvas){
	      
	    if(canvas!=null){
	      
	      canvas.beginDraw();
	      for (int i=states.get(state).sliderBoxes.size()-1;i>-1; i--) {
	        
	        sliderBox s = states.get(state).sliderBoxes.get(i);
	        if(scrollable&&vscroll&&sliderv!=null)s.y = s.by - sliderv.value;
	        if(scrollable&&hscroll&&sliderh!=null)s.x = s.bx - sliderh.value;
	        s.mouse = getMouse();
	        s.parentTab = this;
	        s.draw(canvas);
	      }
	      canvas.endDraw();
	      }
	  };
	  
	  public void drawToggleMenus(){

	    for (int i=states.get(state).toggleMenus.size()-1;i>-1; i--) {
	      
	      toggleMenu s = null;
	      if(states.get(state).toggleMenus.get(i)!=null){
	        s = states.get(state).toggleMenus.get(i);
	        if(s.parentTab == null)s.parentTab = this;
	        if(scrollable&&vscroll&&sliderv!=null)s.menu.y = s.by - sliderv.value;
	        if(scrollable&&vscroll&&sliderv!=null)s.y = s.by - sliderv.value;

	        s.menu.mouse = getMouse();
	        if(s.menu.parentTab == null)s.menu.parentTab = this;

	        s.draw(canvas);
	      }
	    }
	  };

	  public void drawTextBlocks(){
	    for (int i=states.get(state).textBlocks.size()-1;i>-1; i--) {
	        
	      textBlock t = states.get(state).textBlocks.get(i);
	      //t.mouse = getMouse();
	      //t.parentTab = this;
	      if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
	      t.draw(canvas);
	    }


	  };

	  public void drawTextBlocks(boolean b){
	    canvas.beginDraw();
	    for (int i=states.get(state).textBlocks.size()-1;i>-1; i--) {
	        
	      textBlock t = states.get(state).textBlocks.get(i);
	      //t.mouse = getMouse();
	      //t.parentTab = this;
	      if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
	      t.draw(canvas);
	    }
	    canvas.endDraw();

	  };
	  
	  public void drawDragBox(){
	    
	    canvas.fill(0);
	    canvas.noStroke();
	    if(border)canvas.stroke(255);
	    canvas.rect(x,y-5,w,5,r1,r2,r3,r4);
	    
	  };
	  
	  public void drawSlider() {
	    //canvas.beginDraw();
	    canvas.fill(0);
	    //canvas.ellipse(getMouse().x,getMouse().y,20,20);
	    //canvas.endDraw();
	    //image(canvas,x,y);
	    if(vscroll&&sliderv!=null){
	      sliderv.mouse = getMouse();
	      //if(posTab(getMouse())||sliderv.mdown)
	      if(!sliderh.mdown)sliderv.mouseFunctions(mouse);
	      sliderv.draw(canvas);
	    }
	    if(hscroll&&sliderh!=null){
	      sliderh.mouse = getMouse();
	      //if(posTab(getMouse())||sliderh.mdown)
	      if(!sliderv.mdown)sliderh.mouseFunctions(mouse);
	      sliderh.draw(canvas);
	    }

	  };

	  public void drawText() {
	  };

	  public void drawDmenu() {
	    boolean k = false;
	    int id = -1;
	    for (int i=states.get(state).dmenus.size()-1;i>-1; i--) {
	      Dropdown d = states.get(state).dmenus.get(i);
	      
	      if(parentTab==null)d.mouse = getMouse();
	      else d.mouse = getMouse(mouse);
	      if(applet.mousePressed&&!dmdown&&d.toggle==1){
	        id=i;
	        dmdown = true;
	      }
	      if(scrollable&&vscroll&&sliderv!=null)d.y = d.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)d.x = d.bx - sliderh.value;
	      if(d.toggle==1&&id!=i)d.toggle=0;
	      d.displayDropdown(canvas);
	      dmdown = false;
	    };
	  };

	  public void scrolllogic() {
	  };

	  public void drawTitle() {
	    canvas.fill(255);
	    PVector m = getMouse();
	    if(states.size()>0&&state<states.size()&&state>-1){
	      if(states.get(state).title!=null){
	    	Button b = states.get(state).title;
	    	b.mouse = m;
	    	
	        b.draw(canvas);
	      }}
	    
	    if(states.size()>0&&state<states.size()&&states.get(state).title!=null)states.get(state).title.toggle2(this, "toggle",mouse);
	  };
	  
	  public void drawTitle(PGraphics scene) {
	    scene.fill(255);
	    if(states.size()>0&&state<states.size()&&state>-1){
	      if(states.get(state).title!=null){
	        states.get(state).title.mouse = mouse;
	        states.get(state).title.draw(scene);
	      }}
	    
	    if(states.size()>0&&state<states.size()&&states.get(state).title!=null)states.get(state).title.toggle2(this, "toggle",mouse);
	  };
	  
	  public void drawBorder() {
	    if (border) {
	      canvas.stroke(255, 200);
	      canvas.noFill();
	      canvas.rect(0, 0, w, h,r1,r2,r3,r4);
	    }
	    canvas.noStroke();
	  };

	  public void boundingBox() {
	    if(canvas!=null){
	      canvas.noStroke();
	      canvas.fill(255);
	      
	      canvas.rect(0, 0, w, h,r1,r2,r3,r4);
	      canvas.fill(Bms.tabcol);
	      if(localTheme)canvas.fill(tabcol);
	      canvas.rect(0, 0, w, h,r1,r2,r3,r4);
	    }
	  };
	  
	  //important
	  public void getFile(){
	    //PImage t_img ;
	    
	    //Image t_Img;
	    //String[] t_text;
	    //processing.data.Table t_table;
	    
	    //ArrayList<Integer> k = new ArrayList<Integer>();
	    
	    //if(file.length()>0){
	    //  if(file.endsWith("jpg")|| file.endsWith("jpeg") ||file.endsWith("png")||file.endsWith("bmp")||
	    //      file.endsWith("gif")||file.endsWith("JPG")||file.endsWith("JPEG")||file.endsWith("PNG")||
	    //      file.endsWith("BMP")||file.endsWith("GIF")){
	            
	    //      t_img = loadImage(file);
	    //      if(!temp_images.contains(t_img))temp_images.add(t_img);
	    //      //if(!temp_images.contains(t_img))images.add(t_img);
	    //      Image img = new Image(t_img);
	    //      if(!processedImages.contains(img))processedImages.add(img);
	    //  }
	      
	    //  if(file.endsWith("txt")){
	            
	    //      t_text = loadStrings(file);
	    //      if(!temp_text.contains(t_text))temp_text.add(t_text);
	    //  }
	      
	    //  if(file.endsWith("csv")){
	            
	    //      t_table = loadTable(file);
	    //      if(!temp_tables.contains(t_table))temp_tables.add(t_table);
	    //  }
	      
	    //  if(file.endsWith("doc")){
	            
	    //      t_text = WordStream(file);
	    //      if(!temp_text.contains(t_text))temp_text.add(t_text);
	    //  }
	    //  if(file.endsWith("mp3")|| file.endsWith("aiff") ||file.endsWith("ogg")||file.endsWith("flac")||
	    //      file.endsWith("m4a")||file.endsWith("flac")){
	            
	    //      t_table = loadTable(file);
	    //      if(!temp_tables.contains(t_table))temp_tables.add(t_table);
	    //  }
	    //}
	  };
	  
	  boolean posTab() {
	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	  };
	  
	  boolean posTab(PVector m) {
	    return mouse!=null&&mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;
	  };
	  
	  boolean posTabd() {
	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y-5&&applet.mouseY<y;
	  };
	  
	  boolean posTabd(PVector m) {
	    return m.x>x&&m.x<x+w&&m.y>y-5&&m.y<y;
	  };
	  
	  public void drawImages(){
	    
	    for (int i=0; i<states.get(state).images.size(); i++) {
	      PImage p = states.get(state).images.get(i);
	      canvas.image(p,x,y);
	    }
	    
	  };

	  public void drawDynamicImages(ArrayList<PImage> p,int k){
	    
	    tab t = states.get(state);
	    if(t.toggle&&t.visible){
	      canvas.beginDraw();
	      int borderSize = 4;
	      for (int i=p.size()-1;i>-1; i--) {
	        PImage p1 = p.get(i);
	        canvas.stroke(0);
	        canvas.strokeWeight(borderSize);
	        
	        canvas.noFill();
	        if(i==0){
	          canvas.rect((p1.width+20) * i-borderSize*2 - p1.width+20-35,40-borderSize*2,p1.width+borderSize*4,p1.height+borderSize*4);
	        }
	        canvas.stroke(255,0,0);
	        //float x = 
	        if(0==k-i){
	          canvas.rect((p1.width+20) * i-borderSize,40-borderSize*2,p1.width+borderSize*4,p1.height+borderSize*4);
	        }
	        canvas.stroke(0);
	        canvas.rect((p1.width+20) * i-borderSize-p1.width+20-35,40-borderSize,p1.width+borderSize*2,p1.height+borderSize*2);
	        canvas.image(p1,(p1.width+20) * i-p1.width+20-35,40);
	      }
	      //if(t.scrollable)t.drawSlider();
	      canvas.endDraw();
	      applet.image(canvas,x,y);
	    }
	  };

	  public void drawButtons() {
	    applet.fill(255);

	    for (int i=0; i<buttons.size(); i++) {
	      
	      Button b = buttons.get(i);
	      if(scrollable&&vscroll&&sliderv!=null)b.y = b.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)b.x = b.bx - sliderh.value;
	      b.mouse =  mouse;
	      b.parentCanvas = true;
	      b.draw(canvas);
	      b.highlight(mouse);
	      b.self_click2(mouse);
	    }
	  };
	  
	  public void drawButtons(PGraphics scene) {
	    scene.fill(255);

	    for (int i=0; i<buttons.size(); i++) {
	      Button b = buttons.get(i);
	      b.mouse =  mouse;
	      b.parentCanvas = true;
	      b.draw(scene);
	      b.self_click2(mouse);
	      
	      if(scrollable&&vscroll&&sliderv!=null)b.y = b.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)b.x = b.bx - sliderh.value;
	    }
	  };

	  public void drawMenus() {
	    for (int i=0; i<menus.size(); i++) {
	      
	      Menu m = menus.get(i);
	      
	      m.setParentCanvas(canvas);
	      if(scrollable&&vscroll&&sliderv!=null)m.y = m.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)m.x = m.bx - sliderh.value;
	      m.mouse = getMouse();
	      m.setMouse();
	      m.draw(canvas);
	      
	      if(m.toggleBar) {
	    	  for(int j=0;j<m.items.size();j++){
	    		  Button b = m.items.get(j);
	    	      b.self_toggle(m.mouse);
    	      }
	       }
	    }
	  };

	  public void drawTextboxes() {
	    for (int i=0; i<textboxes.size(); i++) {
	      TextBox t = textboxes.get(i);
	      if(parentTab==null)t.mouse = mouse;
	      else t.mouse = getMouse(mouse);
	      if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
	      t.draw(canvas);
	    }
	  };

	  public void drawTextareas() {
	    for (int i=0; i<textareas.size(); i++) {
	      TextArea t = textareas.get(i);
	      if(parentTab==null)t.mouse = getMouse();
	      else t.mouse = getMouse(mouse);
	      if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
	      t.toggle=1;
	      t.draw(canvas);
	    }
	  };

	  public void drawWindows() {
	    for (int i=0; i<windows.size(); i++) {
	      Window w = windows.get(i);
	      //w.toggle = true;
	      
	      w.displayGrid();
	    }
	  };

	  public void drawTable_s() {
	    for (int i=0; i<tables.size(); i++) {
	      Table_ t = tables.get(i);
	      if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
	      if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
	      t.draw();
	      
	    }
	  };

	  public void displayInnerTabs() {
	    for (int i=0; i<tabs.size(); i++) {
	      tab t = tabs.get(i);
	      t.displayTabs();
	    }
	  };
	  
	  
	  public void setRadius(float a){
	    r1 = a;
	    r2 = a;
	    r3 = a;
	    r4 = a;
	    
	    if(title!=null)title.r1 = a;
	    if(title!=null)title.r2 = a;
	    
	    for (int i=0; i<dmenus.size(); i++) {
	      Dropdown d = dmenus.get(i);
	      d.setRadius(a);
	    }
	    
	    for (int i=0; i<menus.size(); i++) {
	      Menu d = menus.get(i);
	      d.setRadius(a);
	    }
	    
	    for (int i=0; i<sliderBoxes.size(); i++) {
	      sliderBox s = sliderBoxes.get(i);
	      s.menu.setRadius(a);
	    }
	    
	    for (int i=0; i<toggleMenus.size(); i++) {
	      toggleMenu s = toggleMenus.get(i);
	      s.menu.setRadius(a);
	    }
	  };
	  
	  public void setRadius(float a,float b,float c,float d){
	    r1 = a;
	    r2 = b;
	    r3 = c;
	    r4 = d;
	    
	    if(title!=null) {
	    	title.r1 = a;
	    	title.r2 = b;
	    	title.r3 = c;
	    	title.r4 = d;
	    }
	    
	    for (int i=0; i<dmenus.size(); i++) {
	      Dropdown d1 = dmenus.get(i);
	      d1.setRadius(a,b,c,d);
	    }
	    
	    for (int i=0; i<menus.size(); i++) {
	      Menu m = menus.get(i);
	      m.setRadius(a,b,c,d);
	    }
	    
	    for (int i=0; i<sliderBoxes.size(); i++) {
	      sliderBox s = sliderBoxes.get(i);
	      s.menu.setRadius(a,b,c,d);
	    }
	    
	    for (int i=0; i<toggleMenus.size(); i++) {
	      toggleMenu t = toggleMenus.get(i);
	      t.menu.setRadius(a,b,c,d);
	    }
	  };
	  
	  public void setAlignment(String s){
	    
	    if(s=="CENTER"||s=="center"||s=="Center"){
	      if(title!=null){
	        title.txoff = (title.w-applet.textWidth(title.label))/2;
	        
	      }
	      
	      for (int i=0; i<windows.size(); i++) {
	        Window w = windows.get(i);
	        //w.x = 5
	      }
	      
	      for (int i=0; i<tables.size(); i++) {
	        Table_ t = tables.get(i);
	        t.x = 5;
	        //(b.w-applet.textWidth(b.label))/2-((b.w-applet.textWidth(b.label))/2)/2
	        t.x = (w-t.w)/2;
	      }
	      for (int i=0; i<textboxes.size(); i++) {
	        TextBox t = textboxes.get(i);
	        t.x = (w-t.w)/2;
	      }
	      
	      for (int i=0; i<textareas.size(); i++) {
	        TextArea t = textareas.get(i);
	        t.x = (w-t.w)/2;
	      }
	      
	      for (int i=0; i<menus.size(); i++) {
	        Menu m = menus.get(i);
	        m.x = 5;
	        m.x = (w-m.w)/2;
	      }
	      
	      for (int i=0; i<buttons.size(); i++) {
	        Button b = buttons.get(i);
	        b.x = (w-b.w)/2;
	      }
	      
	      for (int i=0; i<sliderBoxes.size(); i++) {
	        sliderBox s1 = sliderBoxes.get(i);
	        s1.x = (w-s1.w)/2;
	        s1.menu.x = (w-s1.w)/2;
	      }
	      
	      for (int i=0; i<toggleMenus.size(); i++) {
	        toggleMenu s1 = toggleMenus.get(i);
	        s1.x = (w-s1.w)/2-s1.menu.items.get(0).rx;
	        //s1.menu.x = (w-s1.w)/2;
	      }
	      
	      for (int i=0; i<dmenus.size(); i++) {
	        Dropdown d = dmenus.get(i);
	        d.x = (w-d.w)/2;
	        d.txoff = d.w/2-applet.textWidth(d.label)/2;
	        for (int j=0; j<dmenus.get(i).items.size(); j++) {
	          Button d1 = dmenus.get(i).items.get(j);
	          d1.x = (w-d1.w)/2;
	          d1.txoff = (d1.w-applet.textWidth(d1.label))/2;
	        }
	      }
	    }
	    
	    if(s=="RIGHT"||s=="right"||s=="Right"){
	      
	      if(title!=null){
	        title.txoff = (title.w-applet.textWidth(title.label))/2-((title.w-applet.textWidth(title.label))/2)/2;
	        
	      }
	      
	      for (int i=0; i<windows.size(); i++) {
	        Window w = windows.get(i);
	        //w.x = 5
	      }
	      
	      for (int i=0; i<tables.size(); i++) {
	        Table_ t = tables.get(i);
	        t.x = 5;
	        t.x = (w-t.w)/2-((w-t.w)/2)/2;
	      }
	      for (int i=0; i<textboxes.size(); i++) {
	        TextBox t = textboxes.get(i);
	        t.x = (w-t.w)/2-((w-t.w)/2)/2;
	      }
	      
	      for (int i=0; i<textareas.size(); i++) {
	        TextArea t = textareas.get(i);
	        t.x = 5;
	        t.x = (w-t.w)-((w-t.w))/4;
	      }
	      
	      for (int i=0; i<menus.size(); i++) {
	        Menu m = menus.get(i);
	        m.x = 5;
	        m.x = (w-m.w)-((w-m.w))/4;
	      }
	      
	      for (int i=0; i<buttons.size(); i++) {
	        Button b = buttons.get(i);
	        b.x = (w-b.w)-((w-b.w))/4;
	      }
	      
	      for (int i=0; i<sliderBoxes.size(); i++) {
	        sliderBox s1 = sliderBoxes.get(i);
	        s1.x = (w-s1.w)-((w-s1.w))/4;
	      }
	      
	      for (int i=0; i<toggleMenus.size(); i++) {
	        toggleMenu s1 = toggleMenus.get(i);
	        s1.x = (w-s1.w)-((w-s1.w))/4-s1.menu.items.get(0).rx;
	        //s1.menu.x = (w-s1.w)/2;
	      }
	      
	      for (int i=0; i<dmenus.size(); i++) {
	        Dropdown d = dmenus.get(i);
	        d.x = (w-d.w)-((w-d.w))/4;
	        //for (int j=0; j<dmenus.get(i).items.size(); j++) {
	        //  Button d1 = dmenus.get(i).items.get(j);
	        //  d1.x = (w-d1.w)-((w-d1.w))/4;
	        //}
	      }
	    }
	    
	    if(s=="LEFT"||s=="left"||s=="Left"){
	      
	      if(title!=null){
	        title.txoff = 5;
	        
	      }
	      
	      for (int i=0; i<windows.size(); i++) {
	        Window w = windows.get(i);
	        w.x = 5;
	      }
	      
	      for (int i=0; i<tables.size(); i++) {
	        Table_ t = tables.get(i);
	        t.x = 5;
	      }
	      for (int i=0; i<textboxes.size(); i++) {
	        TextBox t = textboxes.get(i);
	        t.x = 5;
	      }
	      
	      for (int i=0; i<textareas.size(); i++) {
	        TextArea t = textareas.get(i);
	        t.x = 5;
	      }
	      
	      for (int i=0; i<menus.size(); i++) {
	        Menu m = menus.get(i);
	        m.x = 5;
	      }
	      
	      for (int i=0; i<buttons.size(); i++) {
	        Button b = buttons.get(i);
	        b.x = 5;
	      }
	      
	      for (int i=0; i<sliderBoxes.size();i++){
	        sliderBox s1 = sliderBoxes.get(i);
	        s1.x = 5;
	      }
	      
	      for (int i=0; i<toggleMenus.size(); i++) {
	        toggleMenu s1 = toggleMenus.get(i);
	        s1.x = 5-s1.menu.items.get(0).rx;
	        //s1.menu.x = (w-s1.w)/2;
	      }
	      
	      for (int i=0; i<dmenus.size(); i++) {
	        Dropdown d = dmenus.get(i);
	        d.x = 5;
	        d.txoff = 0;
	        for (int j=0; j<dmenus.get(i).items.size(); j++) {
	          Button d1 = dmenus.get(i).items.get(j);
	          d1.x = 5;
	          d1.txoff = 0;
	        }
	      }
	    }
	  };
	  
	  public void selfToggle(int i,int j){
	    if(i<menus.size()&&j<menus.get(i).items.size()){
	      PVector mouse = getMouse();
	      menus.get(i).self_toggle(j,mouse);
	      
	    }else {
	      
	      if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found");}
	      else if(applet.mousePressed)PApplet.println("tab: button or menu not found");
	      
	    }
	      
	  };
	  
	  public void toggle(int i) {
		  if(i<=buttons.size())buttons.get(i).self_toggle(mouse);
	  };
	  
	  public void toggle(int i,int j) {
		  if(i<=menus.size()&&j<menus.get(i).items.size())menus.get(i).items.get(j).self_toggle(mouse);
	  };
	  
	  public void toggle(int i,Object o,String s) {
		  if(i<=buttons.size())buttons.get(i).toggle2(o,s);
	  };
	  
	  public void toggle(int i,int j,Object o,String s) {
		  if(i<=menus.size()&&j<menus.get(i).items.size())menus.get(i).toggle2(j,o,s);
	  };
	  
	  public void toggleAllButtons(){
		  for(int i=0;i<buttons.size();i++) {
			  buttons.get(i).self_toggle(mouse);
		  }
		  
	  };
	  
	  public void toggleMenuButtons(int i){
		 if(i<menus.size()) {
			 menus.get(i).toggleAll();
		 }
	  };
		
	  public void toggleAllMenuButtons(){
		 for(int i=0;i<menus.size();i++) {
			 menus.get(i).toggleAll(mouse);
		 }
	  };
	  
	  public boolean getToggle(int i,int j){
	    
	    if(i<menus.size()&&j<menus.get(i).items.size()){
	      
	      Button b = menus.get(i).items.get(j);
	      
	      if(b.toggle==1)return true;
	      else return false;
	      
	    }else {
	      
	      if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found.");}
	      else if(applet.mousePressed)PApplet.println("tab: button or menu not found.");
	      return false;
	      
	    }
	  };
	  
	  public boolean getToggleMenu(int i,int j){
	    
	    if(i<toggleMenus.size()&&j<toggleMenus.get(i).menu.items.size()){
	      
	      Button b = toggleMenus.get(i).menu.items.get(j);
	      
	      if(b.toggle==1)return true;
	      else return false;
	      
	    }else {
	      
	      if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found.");}
	      else if(applet.mousePressed)PApplet.println("tab: button or menu not found.");
	      return false;
	      
	    }
	  };
	  
	  public float getValue(int i,int j){
	    if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size()){
	      
	      Slider b = sliderBoxes.get(i).menu.sliders.get(j);
	      
	      return b.value;
	      
	    }else {
	      
	      if(title!=null)if(applet.mousePressed){PApplet.println("tab",title.label,"slider or sliderBox not found.");}
	      else if(applet.mousePressed)PApplet.println("tab: slider or sliderBox not found.");
	      return -99999;
	      
	    }
	  };
	  
	  public void setValue(int i,int j,float start,float end){
	    if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size()){
	      
	      Slider b = sliderBoxes.get(i).menu.sliders.get(j);
	      
	      b.set(start,end);
	      
	    }else {
	      
	      if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"slider or sliderBox not found.");}
	      else if(applet.mousePressed)PApplet.println("tab: slider or sliderBox not found.");
	      
	    }
	  };
	  
	  public void setValue(int i,int j,float start,float end,Object o,String field){
	    if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size()){
	      
	      Slider b = sliderBoxes.get(i).menu.sliders.get(j);
	      
	      b.set(start,end);
	      
	    }else {
	      
	      if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"slider or sliderBox not found.");}
	      else if(applet.mousePressed)PApplet.println("tab: slider or sliderBox not found.");
	      
	    }
	  };
	  
	  public void setIntValue(int i,int j,int start,int end){
	    if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size()){
	      
	      Slider b = sliderBoxes.get(i).menu.sliders.get(j);
	      
	       b.setint(start,end);
	      
	    }else {
	      
	      if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"slider or sliderBox not found.");}
	      else if(applet.mousePressed)PApplet.println("tab: slider or sliderBox not found.");
	      
	    }
	  };
	  
	  public void set(int i,float a,float b) {
		  if(i<sliders.size())sliders.get(i).set(a,b);
	  };
	  
	  public void setInt(int i,int a,int b) {
		  if(i<sliders.size())sliders.get(i).setint(a,b);
	  };
	  
	  public void set(int i,float a,float b,Object o,String s) {
		  if(i<sliders.size())sliders.get(i).set(a,b,0,s);
	  };
	  
	  public void setInt(int i,int a,int b,Object o,String s) {
		  if(i<sliders.size())sliders.get(i).setint(a,b,o,s);
	  };
	  
	  public void set(int i,int j,float a,float b) {
		  if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).set(a,b);
	  };
	  
	  public void setInt(int i,int j,int a,int b) {
		  if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).setint(a,b);
	  };
	  
	  public void set(int i,int j,float a,float b,Object o,String s) {
		  if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).set(a,b,0,s);
	  };
	  
	  public void setInt(int i,int j,int a,int b,Object o,String s) {
		  if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).setint(a,b,o,s);
	  };
	  
	  public void setBorder() {
		  border = true;
	  };
	  
	  public void setAllBorder(boolean k) {
		  border = true;
		  for(int i=0;i<buttons.size();i++) {
			  Button b = buttons.get(i);
			  b.border = true;
		  }
		  for(int i=0;i<sliders.size();i++) {
			  Slider s = sliders.get(i);
			  s.border = true;
		  }
		  for(int i=0;i<sliderBoxes.size();i++) {
			  sliderBox s = sliderBoxes.get(i);
			  s.setAllBorder(k);
		  }
		  for(int i=0;i<menus.size();i++) {
			  Menu m = menus.get(i);
			  m.setAllBorder(k);
		  }
		  for(int i=0;i<dmenus.size();i++) {
			  Dropdown d = dmenus.get(i);
			  d.setAllBorder(k);
		  }
		  for(int i=0;i<states.size();i++) {
			  tab b = states.get(i);
			  b.setAllBorder(k);
		  }
	  };
	  
	  public void setTransparency(float a) {
		  transparency = a;
	  };
	  
	  
	  public void setAllTransparency(float a) {
		  transparency = a;
		  for(int i=0;i<buttons.size();i++) {
			  Button b = buttons.get(i);
			  b.transparency = a;
		  }
		  for(int i=0;i<sliders.size();i++) {
			  Slider b = sliders.get(i);
			  b.transparency = a;
		  }
		  for(int i=0;i<menus.size();i++) {
			  Menu m = menus.get(i);
			  m.setAllTransparency(a);
		  }
		  for(int i=0;i<dmenus.size();i++) {
			  Dropdown d = dmenus.get(i);
			  d.setAllTransparency(a);
		  }
		  for(int i=0;i<sliderBoxes.size();i++) {
			  sliderBox b = sliderBoxes.get(i);
			  b.setAllTransparency(a);
		  }
		  for(int i=0;i<states.size();i++) {
			  tab b = states.get(i);
			  b.setAllTransparency(a);
		  }
	  };
	  
	  public void setvScroll(float a,float b) {
		  sliderv.set(a,b);
	  };
	  
	  public void sethScroll(float a,float b) {
		  sliderh.set(a,b);
	  };
};
