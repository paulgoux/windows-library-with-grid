package windowsGui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;
import processing.core.PApplet;

public class fileInput{
	  BMScontrols Bms;
	  PApplet applet;
	  String value,location;
	  boolean click = false,folder,counted,fileSelect;
	  float x,y,w,h;
	  Window window;
	  String [] values;
	  ArrayList<String>fileNames = new ArrayList<String>();
	  HashMap<String, ArrayList<Integer>> extensions = new HashMap<String, ArrayList<Integer>>();

	  public fileInput(){
	  };

	  public fileInput(boolean b){
	    folder = true;
	  };

	  public fileInput(Button b,boolean a){
	    
	    x = b.x;
	    y = b.y;
	    w = b.w;
	    h = b.h;

	    folder = true;
	  };
	  
	  public fileInput(Button b,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
	    x = b.x;
	    y = b.y;
	    w = b.w;
	    h = b.h;

	    folder = true;
	  };

	  public fileInput(Button b,boolean a,Object o){
	    
	    x = b.x;
	    y = b.y;
	    w = b.w;
	    h = b.h;

	    folder = true;
	  };
	  
	  public fileInput(BMScontrols bms){
		  Bms = bms;
		  applet = bms.applet;
		  window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
	  };

	  public fileInput(boolean b,BMScontrols bms){
		Bms = bms;
        applet = bms.applet;
	    folder = true;
	    window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
	    window.launchable = false;
	  };

	  public fileInput(Button b,boolean a,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
	    
	    x = b.x;
	    y = b.y;
	    w = b.w;
	    h = b.h;

	    folder = true;
	    window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
	    window.launchable = false;
	  };

	  public fileInput(Button b,boolean a,Object o,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
	    
	    x = b.x;
	    y = b.y;
	    w = b.w;
	    h = b.h;

	    folder = true;
	    window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
	    window.launchable = false;
	  };

	  public String getFile(){
	    
	    String s = value;
	    value = null;
	    return s;
	  };

	  public void setLink(Button b){
	    x = b.x;
	    y = b.y;
	    w = b.w;
	    h = b.h;
	  };

	  public String getFolder(){
	    String s = value;
	    value = null;
	    return s;
	  };

	  public void saveLocation(String location){
	    this.location = location;
	  };

	  public void listen(){
	    if(!folder){
	      if(click()){
//	        selectInput("Select a file to process:", "fileSelected");
	        value = Bms.File.value;
	        fileSelect = true;
	        Bms.File.value = null;
	      }}else {
	        if(click()){
//	          selectFolder("Select a file to process:", "folderSelected");
	          fileSelect = true;
	      }}
	       if(fileSelect&&Bms.Location!=null)applet.println("Location: " + Bms.Location);

	  };

	  public void listen1(){
	    click();
	    if(!folder){
	      if(click){
//	        selectInput("Select a file to process:", "fileSelected");
	        value = Bms.File.value;
	        Bms.File.value = null;
	      }}else {
	        if(click){
//	          selectFolder("Select a file to process:", "folderSelected");
	          value = Bms.File.value;
	          Bms.File.value = null;
	          values = fileUtils.listNames(value);
	      }}
	  };
	 
	  public void listen_(){
	    if(!folder) {
//	    	selectInput("Select a file to process:", "fileSelected");
	    }
	    else {
//	    	selectInput("Select a file to process:", "folderSelected");
	    }
	  };

	  public void listExt() {

	    if(values!=null&&!counted){
	      for(int i=0;i<values.length;i++){

	        File f = new File(values[i]);

	        String type = fileUtils.getFileExtension(f);
	        if(!extensions.containsKey(type)){
	          ArrayList<Integer> n = new ArrayList<Integer>();
	          n.add(i);
	          extensions.put(type,n);
	        }else extensions.get(type).add(i);
	      }
	    counted = true;
	    }
	  };

	  public void getTextFiles(){


	  };

	  public void getImageFiles(){


	  };

	  public void getVideoFiles(){


	  };

	  public void getAudioFiles(){


	  };

	  public String []getFolder(String location) {

	    String []names = fileUtils.listNames(location);

	    if(names!=null&&!counted){
	      for(int i=0;i<names.length;i++){

	        File f = new File(names[i]);

	        String type = fileUtils.getFileExtension(f);
	        String[] m = applet.match(names[i], "ubyte");

	        if(m==null){
	          if(!extensions.containsKey(type)){
	            ArrayList<Integer> n = new ArrayList<Integer>();
	            n.add(i);
	            extensions.put(type,n);
	            fileNames.add(names[i]);
	          }else{
	            extensions.get(type).add(i);
	            fileNames.add(names[i]);
	        }}
	        else{
	          if(!extensions.containsKey("ubyte")){
	            ArrayList<Integer> n = new ArrayList<Integer>();
	            n.add(i);
	            extensions.put("ubyte",n);
	            fileNames.add(names[i]);
	          }else{
	            extensions.get(type).add(i);
	            fileNames.add(names[i]);
	        }}}}

	      String []s = new String[fileNames.size()];

	      for(int i=0;i<fileNames.size();i++){
	        s[i] = fileNames.get(i);
	      }
	      return s;
	  };

	  public boolean click(){
	    boolean k = false;
	      if (pos()&&applet.mousePressed&&!click){
	        click = true;
	        k = false;
	      }else if(click&&!applet.mousePressed){
	        k = true;
	        click = false;
	      }
	    return k;
	  };
	    
    public  boolean pos(){
	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
    };

	  public void fileSelected(File selection){
	  if(selection != null)Bms.File.value = selection.getAbsolutePath();
	};

	public void folderSelected(File selection) {
	  if(selection != null){
	    Bms.File.value = selection.getAbsolutePath();
	    Bms.Location = selection.getAbsolutePath();
	  }
	};
	
	public void selectInput() {
		
	};
	
	public void selectFolder() {
		
	};
};
