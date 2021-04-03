package windowsGui;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;
import processing.opengl.PShader;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class BMScontrols{
	  PApplet applet;
	  int Mcount;
	  public BMScamera camera;
	  public int maxFolderSize = 100,mouseButton;
	  HashMap<Object,String> booleans = new HashMap<Object,String>();
	  public int col , bgcol, bcol, tcol, fcol, hcol,toggleCol,tabcol,sliderbgcol;
	  float r1,r2,r3,r4;
	  public boolean updated,autoSave,globalDown,debug,borders,terrain3d,showGrid,showBoundaries,showCam;
	  public String clipBoard;
	  String currentMouseObject;
	  Object currentObject;
	  Menu menuObject,file,reset,resetDialogue,shapes;
	  public Window fmenu;
	  Slider sliderObject;
	  //objectSelected;
	  Dropdown dropDownObject;
	  String[] Lines;
	  public Button yes,no;
	  public Dock dock;
	  tab resetDialogueTab,themeSettings;
	  public float autoSaveTimeout = 30,transparency;
	  
	  public ArrayList<Entity> entities = new ArrayList<Entity>();
	  public ArrayList<Quad> qgrid = new ArrayList<Quad>();
	  public ArrayList<Boundary> boundaries = new ArrayList<Boundary>();
	  
	  public ArrayList<Button> buttons = new ArrayList<Button>();
	  public ArrayList<Menu> menus = new ArrayList<Menu>();
	  public ArrayList<Dropdown> dmenus = new ArrayList<Dropdown>();
	  public ArrayList<radioMenu> radioMenus = new ArrayList<radioMenu>();
	  public ArrayList<toggleMenu> toggleMenus = new ArrayList<toggleMenu>();
	  
	  public ArrayList<Slider> sliders = new ArrayList<Slider>();
	  public ArrayList<sliderBox> sliderBoxes = new ArrayList<sliderBox>();
	  
	  public ArrayList<Grid> grids = new ArrayList<Grid>();
	  public ArrayList<Table_> tables = new ArrayList<Table_>();
	  
	  public ArrayList<PImage> images = new ArrayList<PImage>();
	  public ArrayList<String> links = new ArrayList<String>();
	  
	  public ArrayList<textBlock> textBlocks = new ArrayList<textBlock>();
	  public ArrayList<TextBox> textBoxes = new ArrayList<TextBox>();
	  public ArrayList<TextArea> textAreas = new ArrayList<TextArea>();
	  
	  public ArrayList<Scene> scenes = new ArrayList<Scene>();
	  public ArrayList<Window> windows = new ArrayList<Window>();
	  public ArrayList<tab> tabs = new ArrayList<tab>();
	  
	  Boundary bb;
	  public Window main;
	  fileInput File,Folder;
	  Menu menu;
	  String Location;
	  //String[] cameras = Capture.list();
	  String[] files;
	  
	  //Capture Cam;
	  Dropdown dd;
	  
	  public BMScontrols(PApplet applet) {
		  this.applet = applet;
		  initColors();
	  };
	  
	  public BMScontrols(PApplet applet,boolean k) {
		  this.applet = applet;
		  initColors();
		  begin();
	  };
	  
	  public BMScontrols(PApplet applet,boolean k,boolean k1) {
		  this.applet = applet;
		  initColors();
		  begin();
		  createCamera();
	  };
	  
	  public void initColors(){
		col = applet.color(0, 255, 73);
		bgcol = applet.color(255);
		bcol = applet.color(255);
		tcol = applet.color(255); 
		fcol = applet.color(0, 255, 73);
		hcol = applet.color(0, 255, 73,100);
		toggleCol = applet.color(55, 84, 63);
        tabcol = applet.color(0, 150, 255);
        sliderbgcol = applet.color(255);
	  };

		public void add(Object o,String globalVar,boolean localVar){
		    
		    booleans.put(o,globalVar);
		  
		};

		public void draw(){
		  // fill(255);
		  
		  // text("hello",100,100);
		};

		public void begin(){
//	      PApplet.println("bms",applet);
		  File = new fileInput(this);
		  Folder = new fileInput(true,this);
		  setupDock();
		  if(debug)PApplet.println("dock");
		  setupReset();
		  if(debug)PApplet.println("reset");
		  setupWindows();
		  if(debug)PApplet.println("windows");
		  setupMenus();
		  if(debug)PApplet.println("menus");
		  setupTabs();
		  if(debug)PApplet.println("menus");
		};
		
		public void setupTabs() {
			  themeSettings = new tab(0,200,200,400,"Theme",this);
			  String [] s1 = {"red","green","blue"};
			  float [] v1 = {52, 235, 225};
			  sliderBox sl2 = new sliderBox(50,40,90,90,10,s1,v1,this);
			  sl2.menu.draggable = false;
			  sl2.tooltip = null;
			  sl2.setPieSquare();
			  sl2.setStart(0);
			  sl2.setEnd(255);
			  themeSettings.setvScroll();
			  themeSettings.draggable = true;
			  themeSettings.add(sl2);
			  dock.add(themeSettings);
			  add(themeSettings);
		};


		public void setupDock(){
		  dock = new Dock(0,applet.height -22,applet.width,24,this);
		  
		};


		public void setupWindows(){
	      //applet.println("begin window");
	      Boundary b = new Boundary(this);
		  if(debug)PApplet.println("setupWindows 0");
		  main = new Window(23,20,applet.width-46,applet.height-90-20,this);
//		  main = new Window(applet);
		  add(b);
		  if(debug)PApplet.println("setupWindows 1");
		  main.fill = false;
		  main.border = false;
		  Boundary b1 = new Boundary(main.x+1,main.y+1,main.w-2,main.h-2,4,this);
		  if(debug)PApplet.println("setupWindows 2");
		  b1.cohesionBoundary = true;
		  b1.avoidBoundary = true;
		  b1.state = 1;
		  b1.visible = false;
		  main.Boundaries.add(b1);
		  if(debug)PApplet.println("setupWindows 3");
		  fmenu = new Window(200,200,200,200,"C:\\Users\\paul goux\\",this);
		  if(debug)PApplet.println("setupWindows 4");
		  fmenu.setRadius(10);
		  PApplet.println("setupWindows 4.1");
		  fmenu.rapidAccess = true;
		  PApplet.println("setupWindows 4.2");
		  windows.add(fmenu);
		  PApplet.println("setupWindows 4.3");
		  dock.add(fmenu);
		  PApplet.println("setupWindows 4.4");
		  PApplet.println("setupWindows 5");
		  PApplet.println(fmenu);
		};
		
		
		public void mousePressed() {
//			long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK;
//
//		    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
//		        public void eventDispatched(AWTEvent e) {
//		            PApplet.println(e.paramString()+"-"+e.getSource());
//		        }
//		    }, eventMask);
			
			//PApplet.MouseEvent e;
        };
		

		public void setupMenus(){
			String [] flabels = {"Settings","Camera","Window","Reset"};
			file = new Menu(20,0,50,70,"File",flabels,0,this);
			  
			menus.add(file);
			  
			  //----------------------file -----------------------------------
			if(file!=null){
				String []glabels = {"Terrain","Img","Cam","Video","Audio","Text"};
				file.items.get(0).submenu  = new Menu(file.items.get(0).x+file.items.get(0).w,file.items.get(0).y,70,glabels,0,this);
				file.setLink(0);
			}
		};


		public void setupReset(){
		  resetDialogue = new Menu(applet.width/2 - 136,applet.height/2 - 22,275,45,"Are you sure you want to exit?",this);
		  
		  resetDialogue.visible = true;
		  resetDialogue.highlightable = false;
		  resetDialogue.free = true;
		  yes = new Button(applet.width/2 - 136 ,applet.height/2 +2,resetDialogue.w/2,20,"Yes",this);
		  yes.border = false;
		  yes.togglebox = true;
		  yes.Bms = this;
		  yes.applet = applet;
		  no = new Button(yes.x + yes.w,applet.height/2 +2,resetDialogue.w/2,20,"No",this);
		  no.Bms = this;
		  no.applet = applet;
		  no.border = false;
		  no.togglebox = true;
		  resetDialogue.add(yes);
		  resetDialogue.add(no);
		  resetDialogue.setBms(this);
		};
		
		public void setupReset2(){
			  resetDialogueTab = new tab(applet.width/2 - 136,applet.height/2 - 22,275,45,"Are you sure you want to exit?",this);
			  
			  resetDialogue.highlightable = false;
			  resetDialogue.free = true;
			  yes = new Button(applet.width/2 - 136 ,applet.height/2 +2,resetDialogue.w/2,20,"Yes",this);
			  yes.border = false;
			  yes.togglebox = true;
			  yes.Bms = this;
			  yes.applet = applet;
			  no = new Button(yes.x + yes.w,applet.height/2 +2,resetDialogue.w/2,20,"No",this);
			  no.Bms = this;
			  no.applet = applet;
			  no.border = false;
			  no.togglebox = true;
			  
			  resetDialogue.items.add(yes);
			  resetDialogue.items.add(no);
			  yes.parent = resetDialogue;
			  no.parent = resetDialogue;
			};


		public void loadImg(){
		    
		};

		public void displayWindows(){
		  for(int i=0;i<windows.size();i++){
		    
		    Window r = windows.get(i);
		    r.displayGrid();
		    
		  }
		};
		
		public void displayGrids(){
//		  if(debug&&applet.mousePressed)PApplet.println("bms displayGrid",grids.get(0));
		  for(int i=0;i<grids.size();i++){
		    
		    Grid g = grids.get(i);
		    g.grid_functions();
		    
		  }
		};
		
		public void displayBoundaries(){
		  if(debug&&applet.mousePressed)PApplet.println("bms displayGrid",boundaries.get(0));
		  for(int i=0;i<boundaries.size();i++){
		    
			Boundary b = boundaries.get(i);
		    if(b.type==-1)b.draw();
		    else b.draw2();
		    
		  }
		};
		
		public void createCamera() {
			camera = new BMScamera(this,1440,720);
		};

		public void run(){
		  toggle(0,0,themeSettings,"toggle");
		  toggle(0,2,fmenu,"open");
		  
		  if(camera!=null&&camera.settings!=null)toggle(0,1,camera.settings,"toggle");
		  themeFunctions();
	      applet.background(bgcol);
		  globalLogic();
		  
		  displayButtons();
		  mainFunctions();
		  if(showGrid)displayGrids();
		  if(showBoundaries)displayBoundaries();
		  displayTextBoxes();
		  sliderBoxFunctions();
		  sliderFunctions();
		  displayDropDowns();
		  displayTabs();
		  
		  for(Menu menu : menus)menu.click();
		  
		  dock.logic();
		  
		  if(camera!=null&&getToggle(0,1)){
			  camera.displayCam();
			  camera.camLogic();
		  }
//		  else if(camera!=null&&camera.cam.isFlashEnabled())camera.cam.disableFlash();
		  
		  menuFunctions();
		  displayWindows();
		  dock.drawItems();
		  
		};
		
		public void displayDropDowns() {
			for(int i=0;i<dmenus.size();i++) {
				Dropdown d = dmenus.get(i);
				d.applet = applet;
				d.displayDropdown();
			}
		};
		
		public void run(int i){
		  toggle(0,0,themeSettings,"toggle");
		  toggle(0,2,fmenu,"open");
		  themeSettings.setvScroll(0, 200);
		  if(camera!=null&&camera.settings!=null)toggle(0,1,camera.settings,"toggle");
		  themeFunctions();
	      applet.background(bgcol);
	      if(debug)applet.println("bms display 01");
		  globalLogic();
		  if(debug)applet.println("bms display 2");
		  if(showGrid)displayGrids();
		  if(debug)applet.println("bms display 3");
		  
		  if(showBoundaries)displayBoundaries();
		  if(debug)applet.println("bms display 04");
		  displayButtons();
		  if(debug)applet.println("bms display 05");
		  mainFunctions();
		  if(debug)applet.println("bms display 06");
		  displayTextBoxes();
		  if(debug)applet.println("bms display 07");
		  sliderBoxFunctions();
		  if(debug)applet.println("bms display 08");
		  sliderFunctions();
		  if(debug)applet.println("bms display 09");
		  displayDropDowns();
		  if(debug)applet.println("bms display 10");
		  displayTabs();
		  if(debug)applet.println("bms display 11");
		  for(Menu menu : menus)menu.click();
		  
		  dock.logic();
		  
		  if(camera!=null&&getToggle(0,1)){
			  camera.displayCam();
			  camera.camLogic();
		  }
//			  else if(camera!=null&&camera.cam.isFlashEnabled())camera.cam.disableFlash();
		  
		  menuFunctions();
		  if(debug)applet.println("bms display 13");
		  displayWindows();
		  if(debug)applet.println("bms display 14");
		  dock.drawItems();
		  if(debug)applet.println("bms display 16");
		  mouseButton = i;
		};

		public void toggleMenus(){
		  for(int i=0;i<toggleMenus.size();i++){
		    
		    toggleMenu r = toggleMenus.get(i);
		    r.draw();
		    
		  }
		};

		public void radioMenus(){
		  for(int i=0;i<radioMenus.size();i++){
		    
		    radioMenu r = radioMenus.get(i);
		    r.draw();
		    
		  }
		};
		
		public void toggleAllButtons(){
		  for(int i=0;i<buttons.size();i++) {
			  buttons.get(i).self_toggle();
		  }
		  
		};
		
		public void toggleMenuButtons(int i){
		  if(i<menus.size())menus.get(i).toggleAll();
		};
		
		public void toggleAllMenuButtons(){
		  for(int i=0;i<menus.size();i++) {
			  menus.get(i).toggleAll();
		  }
		};
		
	    public void toggle(int i){
		  if(i<=buttons.size())buttons.get(i).self_toggle();
		};

		public void toggle(int i,PVector m){
		  if(i<=buttons.size())buttons.get(i).self_toggle(m);
		};

		public void toggle(int i,int j){
				    
			if(i<menus.size()&&j<menus.get(i).items.size()){
			  
			  menus.get(i).self_toggle(j);
			}else {
			  PApplet.println("BMS: button or menu not found..");
			}
		};
  
		public void toggle(int i,int j,PVector m){
				    
			if(i<menus.size()&&j<menus.get(i).items.size()){
					      
			  menus.get(i).self_toggle(j,m);
			}else {
			  PApplet.println("BMS: button or menu not found..");
			}
		};
			  
		public void selfToggle(int i){
		  if(i<=buttons.size())
		  buttons.get(i).self_toggle();
		};

		public void selfToggle(int i,PVector m){
		  if(i<=buttons.size())
		  buttons.get(i).self_toggle(m);
		};

		public void selfToggle(int i,int j){
		    
		    if(i<menus.size()&&j<menus.get(i).items.size()){
		      
		      menus.get(i).self_toggle(j);
		    }else {
		      PApplet.println("BMS: button or menu not found..");
		    }
		  };
		  
		  public void selfToggle(int i,int j,PVector m){
		    
		    if(i<menus.size()&&j<menus.get(i).items.size()){
		      
		      menus.get(i).self_toggle(j,m);
		    }else {
		      PApplet.println("BMS: button or menu not found..");
		    }
		  };
		  
		  public void toggle(int i,Object o,String s){
		    if(i<=buttons.size())buttons.get(i).toggle2(o,s);
		  };

		  public void toggle(int i,int j,Object o,String s){
		    if(i<=menus.size()&&j<menus.get(i).items.size())menus.get(i).toggle2(j,o,s);
		  };

		public void displayButtons(){
		  for(int i=0;i<buttons.size();i++){
		    Button b = buttons.get(i);
		    
		    b.draw();
		    b.highlight();
		  };
		};
		
		
		public void themeFunctions() {
			if(themeSettings!=null) {
				Menu m1 = themeSettings.sliderBoxes.get(0).menu;
			    Slider r = m1.sliders.get(0);
			    Slider g = m1.sliders.get(1);
			    Slider b = m1.sliders.get(2);
			    bgcol = applet.color(r.value,g.value,b.value);
			}
		};
		
		public void mainFunctions(){
		  
		  fmenu.displayGrid();
		};

		public void displayTabs(){
		  for(int i=0;i<tabs.size();i++){
		    tab t = tabs.get(i);
		    
		    t.displayTab();
		  };
		};


		public void displayTextBoxes(){
//			if(applet.mousePressed)applet.println("bms displayTextBoxes");
//		  for(int i=0;i<textAreas.size();i++){
//			TextArea t = textAreas.get(i);
//		    t.draw();
//		  }
		  
		  for(int i=0;i<textBoxes.size();i++){
			    TextBox t = textBoxes.get(i);
			    t.draw();
		  }
		};
		
		public void displayTextBlocks(){
			for(int i=0;i<textBlocks.size();i++){
				textBlock t = textBlocks.get(i);
			    t.draw();
			  }
		};

		public void manageClipBoard(){
		  //if(getTextFromClipboard ()!=clipBoard)clipBoard = getTextFromClipboard ();
		};

		public void click(){
		  buttons();
		};

		public void reload(){
		  if(buttons.get(0).toggle==1&&!updated){
		    applet.frameCount = -1;
		    updated = true;
		  }
		};

		public void buttons(){
		  
		  file.self_toggle(6);
		  yes.self_toggle();
		  no.self_toggle();
		  String [] gridm = {"forward","backward","pause"};
		  Menu grid = file.items.get(2).submenu;
		  Menu attractor = file.items.get(5).submenu;
		  
		  

		  //----------Shapes------------------------
		  String [] shapes_bool = {"toggle","connectedlines","circle","square","tri","bezier","none"};
		  
		  // for(int i=0;i<shapes_bool.length;i++){
		  //   shapes.sptoggle(i,main,shapes_bool[i],shapes_bool);
		  // }
		  //shapes.sptoggle(main,shapes_bool);
		  shapes.sptoggle(0,main,"toggle",shapes_bool);
		  shapes.sptoggle(1,main,"connectedlines",shapes_bool);
		  shapes.sptoggle(2,main,"circle",shapes_bool);
		  shapes.sptoggle(3,main,"square",shapes_bool);
		  shapes.sptoggle(4,main,"tri",shapes_bool);
		  shapes.sptoggle(5,main,"poligon",shapes_bool);
		  shapes.sptoggle(6,main,"complex",shapes_bool);
		  shapes.sptoggle(7,main,"bezier",shapes_bool);
		  shapes.sptoggle(8,main,"spline",shapes_bool);
		  shapes.sptoggle(9,main,"none",shapes_bool);
		  shapes.sptoggle(11,main,"path",shapes_bool);
		  
		  String [] ld = {"draw","edit"};
		  
		  main.pallete.toggle(0,main,"fill");
		  main.pallete.toggle(1,main,"gravity");
		  main.pallete.toggle(2,main,"friction");
		  main.pallete.toggle(3,main,"velocity");
		  main.pallete.toggle(4,main,"connect");
		  main.pallete.toggle(5,main,"amendBoundary");
		  main.pallete.toggle(6,main,"amendInnerBoundary");
		  main.pallete.toggle(7,main,"amendAvoidance");
		  main.pallete.toggle(8,main,"amendCohesion");
		  main.pallete.toggle(9,main,"followBoundary");
		  main.pallete.toggle(10,main,"follow");
		  main.pallete.toggle(11,main,"dashed");
		  main.pallete.toggle(12,main,"border");
		  main.pallete.toggle(13,main,"amendAvoidance");
		  main.pallete.toggle(14,main,"showgrid");
		  main.pallete.toggle(15,main,"hidemenu");
		  main.pallete.sptoggle(16,main,"edit",ld);
		  main.pallete.toggle(17,main,"reset");
		  //main.complexsub.toggle(0,main,"cfinish");
		  
		};

		public void sliderBoxFunctions(){
		  for(int i=0;i<sliderBoxes.size();i++){
		    
		    sliderBox s = sliderBoxes.get(i);
		    if(s.visible)s.draw();
		    //s.menu.sliderFunctions();
		    
		  }
		};

		public void sliderFunctions(){
		  for(int i=0;i<sliders.size();i++){
		    
		    Slider s = sliders.get(i);
		    s.draw();
		    s.mouseFunctions();
		    if(i==0)s.set(0,20);
		    if(i==1)s.setint(0,6);
		    //s.tooltip.draw();
		    //for(int i=0
		    
		    //s.menu.sliderFunctions();
		    //if(applet.mousePressed)applet.println("sliders",sliderBoxes.size());
		    
		  }
		};


		public void menuFunctions(){
		  
		  Mcount = 0;
		  //if(applet.mousePressed)applet.println("m size",menus.size());
		  for(int i=0;i<menus.size();i++){
		    
		    Menu m = menus.get(i);
		    m.draw();
		    
		  //   if(m.parent==null&&(m.pos()||m.toggle==1)){Mcount++;}
		  //   else if(m.parent!=null&&m.items.size()>0&&m.parent.toggle==1){
		  //     for(int j=0;j<m.items.size();j++){
		  //       Button b = m.items.get(j);
		  //       if(b.pos())Mcount++;
		  //     }}
		  //     else if(m.sliders.size()>0&&m.parent.toggle==1){
		  //     for(int j=0;j<m.sliders.size();j++){
		  //       Slider b = m.sliders.get(j);
		  //       if(b.pos())Mcount++;
		  //     }
		  // }
		  
		    
		  }
		};

		public void boundariesNscenes(){
		  
		  //------------------------------------------------------
		  //Boundaries--------------------------------------------
		};


		public void autoSave(){
		  
		  if(autoSave){
		    saveText();
		  }
		  
		};

		public void load(){
		  
		};

		public void saveText(){
		  
		  for(int i=0;i<textBoxes.size();i++){
		    TextBox t = textBoxes.get(i);
		    
		    t.save();
		  }
		  
		  for(int i=0;i<textAreas.size();i++){
		    TextArea t = textAreas.get(i);
		    
		    t.save();
		  }
		};

		public void saveSliderBox(){
		  
		  for(int i=0;i<sliderBoxes.size();i++){
		    sliderBox s = sliderBoxes.get(i);
		    
		    s.save();
		  }
		};

		public void saveButtons(){
		  
		  for(int i=0;i<buttons.size();i++){
		    Button b = buttons.get(i);
		    
		    b.save();
		  }
		};

		public void saveMenu(){
		  
		  for(int i=0;i<menus.size();i++){
		    Menu m = menus.get(i);
		    
		    m.save();
		  }
		  
		  for(int i=0;i<textAreas.size();i++){
		    TextArea t = textAreas.get(i);
		    
		    t.save();
		  }
		};


		public void saveTabs(){
		  
		  for(int i=0;i<tabs.size();i++){
		    tab t = tabs.get(i);
		    
		    t.save();
		  }
		  
		  for(int i=0;i<tabs.size();i++){
		    tab t = tabs.get(i);
		    
		    t.save();
		  }
		};

		public void saveImgp(){
		  
		  for(int i=0;i<textBoxes.size();i++){
		    TextBox t = textBoxes.get(i);
		    
		    t.save();
		  }
		};

	public void save(){
	  
	};

	public void start(Object o,boolean localVar){
	    Field field = null;
	    
	    String s = "hello";
	    
	    try{
	      //field = o.getClass().getfield(s);
	      field = applet.getClass().getField(s); 
	      }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }}
	    
	    public void eventListener(){
	  
	};

	public void toggle(Object o,String globalVar,boolean localVar){
	    Field field = null;
	    
	    try{
	      
	      field = applet.getClass().getField(globalVar);
	      
	      
	    }catch (NullPointerException e) {
	    }catch (NoSuchFieldException e) {
	    }
	  
	};

	public void globalLogic(){
	  //if(dropDownObject!=null)applet.println(dropDownObject.label);
	  if(dropDownObject!=null&&!dropDownObject.dclick){
	    dropDownObject = null;
	  }
	  if(applet.mousePressed) globalDown = true;
	  else {
	    currentMouseObject = null;
	    currentObject = null;
	    globalDown = false;
	  }

	};

	public boolean getToggle(int i){
	    
	    if(i<buttons.size()){
	      Button b = buttons.get(i);
	      
	      if(b.toggle==1)return true;
	      else return false;
	    }else {
	      PApplet.println("BMS: menu not found");
	      return false;
	    }
	  };

	  public boolean getToggle(int i,int j){
	    
	      if(i<menus.size()&&j<menus.get(i).items.size()){
	      
	      Button b = menus.get(i).items.get(j);
	      
	      if(b.toggle==1)return true;
	      else return false;
	      
	    }else {
	      
	      PApplet.println("BMS: button or menu not found.");
	      return false;
	      
	    }
	  };

	  public Button add(int i,Button b){
	    //clear();
	    b.Bms = this;
	    b.applet = applet;
	    buttons.add(b);
	    return b;
	  };
	  
	  public Menu add(int i,Menu m){
	    //clear();
	    m.Bms = this;
	    m.applet = applet;
	    menus.add(m);
	    return m;
	  };
	  
	  public Dropdown add(int i,Dropdown d){
	    //clear();
	    d.Bms = this;
	    d.applet = applet;
	    dmenus.add(d);
	    return d;
	  };
	  
	  public TextBox add(int i,TextBox t){
	    //clear();
	    t.Bms = this;
	    t.applet = applet;
	    textBoxes.add(t);
	    return t;
	  };
	  
	  public TextArea add(int i,TextArea t){
	    //clear();
	    t.Bms = this;
	    t.applet = applet;
	    textAreas.add(t);
	    return t;
	  };

	  public textBlock add(int i,textBlock t){
	    //clear();
	    t.Bms = this;
	    t.applet = applet;
	    textBlocks.add(t);
	    return t;
	  };
	  
	  public String add(int i,String s){
	    //clear();
	    // tab k = states.get(i);
	    // textblock.add(s);
	    return s;
	  };
	  
	  public Table_ add(int i,Table_ t){
	    //clear();
	    t.Bms = this;
	    t.applet = applet;
	    tables.add(t);
	    return t;
	  };
	  
	  public Button add(Button b){
	    //clear();
	    b.Bms = this;
	    b.applet = applet;
	    buttons.add(b);
	    return b;
	  };
	  
	  public Menu add(Menu m){
	    //clear();
	    m.Bms = this;
	    m.applet = applet;
//		    for(int i=0;i<m.items.size();i++) {
//		    	Button b1 = m.items.get(i);
//		    	b1.setBms(this);
//		    }
//		    for(int i=0;i<m.items.size();i++) {
//		    	Slider b1 = m.sliders.get(i);
//		    	b1.applet = applet;
//		    	b1.Bms = this;
//		    	b1.initColors();
//		    }
	    menus.add(m);
	    return m;
	  };
	  
	  public Dropdown add(Dropdown d){
	    //clear();
	    d.setBms(this);
	    dmenus.add(d);
	    return d;
	  };
	  
	  public TextBox add(TextBox t){
	    //clear();
	    t.Bms = this;
	    t.applet = applet;
	    textBoxes.add(t);
	    return t;
	  };
	  
	  public TextArea add(TextArea t){
	    //clear();
	    t.Bms = this;
	    t.applet = applet;
	    textAreas.add(t);
	    return t;
	  };
	  
	  public String add(String s){
	    //clear();
	    //textBlocks.add(s);
	    return s;
	  };
	  
	  public Table_ add(Table_ t){
	    //clear();
	    t.Bms = this;
	    t.applet = applet;
	    tables.add(t);
	    
	    return t;
	  };
	  
	  public sliderBox add(sliderBox s){
	    //if(s.tooltip!=null) tooltip.setBms(this);
	    s.setBms(this);
	    sliderBoxes.add(s);
	    
	      return s;
	  };
	  
	  public tab add(tab t){
	    tabs.add(t);
	    if(t.title!=null)PApplet.println("bms",t.title.label);
	      return t;
	  };
	  
	  public Window add(Window s){
	    //clear();
	    s.Bms = this;
	    s.applet = applet;
	    windows.add(s);
	    return s;
	  };
	  
	  public Grid add(Grid s){
	    //clear();
	    s.Bms = this;
	    s.applet = applet;
	    grids.add(s);
	    dock.add(s.sliderSettings);
	    return s;
	  };
	  
	  public Boundary add(Boundary b){
	    //clear();
	    b.Bms = this;
	    b.applet = applet;
	    boundaries.add(b);
	    return b;
	  };
		  
	  public void setRadius(float a){
		    r1 = a;
		    r2 = a;
		    r3 = a;
		    r4 = a;
		    if(dock!=null)dock.setRadius(a);
		    if(fmenu!=null)fmenu.setRadius(a);
		    
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
		      if(s.tooltip!=null)s.tooltip.setRadius(a);
		    }
		    
		    for (int i=0; i<toggleMenus.size(); i++) {
		      toggleMenu s = toggleMenus.get(i);
		      s.menu.setRadius(a);
		    }
		    
		    for (int i=0; i<tabs.size(); i++) {
		      tab t = tabs.get(i);
		      t.setRadius(a);
		    }
		    for (int i=0; i<textBlocks.size(); i++) {
		      textBlock t = textBlocks.get(i);
		      t.setRadius(a);
		    }
		    for (int i=0; i<textAreas.size(); i++) {
		      TextArea t = textAreas.get(i);
		      t.setRadius(a);
		    }
		    for (int i=0; i<textBoxes.size(); i++) {
		      TextBox t = textBoxes.get(i);
		      t.setRadius(a);
		    }
	  };
	  
	  public void setRadius(float a,float b,float c,float d){
		    r1 = a;
		    r2 = b;
		    r3 = c;
		    r4 = d;
		    
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
		      if(s.tooltip!=null)s.tooltip.setRadius(a,b,c,d);
		    }
		    
		    for (int i=0; i<toggleMenus.size(); i++) {
		      toggleMenu t = toggleMenus.get(i);
		      t.menu.setRadius(a,b,c,d);
		    }
		    
		    for (int i=0; i<tabs.size(); i++) {
		      tab t = tabs.get(i);
		      t.setRadius(a);
		    }
		    
		    for (int i=0; i<textBlocks.size(); i++) {
		      textBlock t = textBlocks.get(i);
		      t.setRadius(a,b,c,d);
		    }
		    for (int i=0; i<textAreas.size(); i++) {
		      TextArea t = textAreas.get(i);
		      t.setRadius(a,b,c,d);
		    }
		    for (int i=0; i<textBoxes.size(); i++) {
		      TextBox t = textBoxes.get(i);
		      t.setRadius(a,b,c,d);
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
	  
	  
	  public void setAllBorder(boolean k) {
			  for(int i=0;i<buttons.size();i++) {
				  Button b = buttons.get(i);
				  b.border = k;
			  }
			  for(int i=0;i<sliders.size();i++) {
				  Slider s = sliders.get(i);
				  s.border = k;
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
			  for(int i=0;i<tabs.size();i++) {
				  tab b = tabs.get(i);
				  b.setAllBorder(k);
			  }
	  };
	  
	  
	  public void setTransparency(float a) {
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
		  for(int i=0;i<tabs.size();i++) {
			  tab b = tabs.get(i);
			  b.setAllTransparency(a);
		  }
	  };
	  
	  public void setLabel(int i,String s) {
		  if(i<buttons.size())buttons.get(i).label = s;
	  };
	  
	  public void setMenuLabel(int i,String s) {
		  if(i<menus.size())menus.get(i).label = s;
	  };
	  
	  public void setTabLabel(int i,String s) {
		  if(i<tabs.size()&&tabs.get(i).title!=null)tabs.get(i).title.label = s;
	  };
	  
	  public void removeTabLabel(int i,String s) {
		  if(i<tabs.size()&&tabs.get(i).title!=null)tabs.get(i).title.label = "";
	  };
	  
	  public void setTabCol(int i,int c) {
		  if(i<tabs.size()&&tabs.get(i).title!=null) {
			  tabs.get(i).tabcol = c;
			  tabs.get(i).localTheme = true;
		  }
	  };
	  
	  public void setDropdownLabel(int i,String s) {
		  if(i<dmenus.size()&&dmenus.get(i).title!=null)tabs.get(i).title.label = s;
	  };
	  
	  public void setTabvScroll(int i,float a,float b) {
		  if(tabs.size()>i&&tabs.get(i).sliderv!=null)tabs.get(i).sliderv.set(a,b);
	  };
	  
	  public void setTabhScroll(int i,float a,float b) {
		  if(tabs.size()>i&&tabs.get(i).sliderh!=null)tabs.get(i).sliderh.set(a,b);
	  };
	  
	  public void setShader(PShader shader) {
		  camera.shader = shader;
	  };
	  
	  public void readCam() {
		  camera.read();
	  };
	  
	  public tab getTab(int i) {
		  tab t = null;
		  if(i<tabs.size())t =  tabs.get(i);
		  return t;
	  };
	  
	  public Menu getMenu(int i) {
		  Menu t = null;
		  if(i<menus.size())t =  menus.get(i);
		  return t;
	  };
	  
	  public Dropdown getdMenu(int i) {
		  Dropdown t = null;
		  if(i<dmenus.size())t =  dmenus.get(i);
		  return t;
	  };
	  
	  public Button getButtons(int i) {
		  Button t = null;
		  if(i<tabs.size())t =  buttons.get(i);
		  return t;
	  };
	  
	  public Table_ getTable(int i) {
		  Table_ t = null;
		  if(i<tables.size())t =  tables.get(i);
		  return t;
	  };
	  
	  public TextBox getTextBox(int i) {
		  TextBox t = null;
		  if(i<textBoxes.size())t =  textBoxes.get(i);
		  return t;
	  };
	  
	  public TextArea getTextArea(int i) {
		  TextArea t = null;
		  if(i<textAreas.size())t =  textAreas.get(i);
		  return t;
	  };
		  
};