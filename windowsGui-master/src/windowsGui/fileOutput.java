package windowsGui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import processing.core.PApplet;

class fileOutput {
	  PApplet applet;
	  BMScontrols Bms;
	  public PrintWriter output;
	  public boolean save, onMouseUp, mdown, debug, append, appendFile, match,append2,overWrite;
	  int counter, counter2;
	  public File file;
	  public String location, filePath,folderPath = "";

	  public fileOutput() {
	    appendFile = true;
	  };
	  
	  public fileOutput(BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
	    appendFile = true;
	  };

	  public fileOutput(boolean a) {
	    overWrite = true;
	    appendFile = true;
	  };
	  
	  public fileOutput(boolean a,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
	    overWrite = true;
	    appendFile = true;
	  };

	  public fileOutput(String location) {
	    //appendFile = true;
	    checkLocation(location);
	    open();
	  };
	  
	  public fileOutput(String location, boolean m) {
	    appendFile = true;
	    checkLocation(location);
	    open();
	    file = applet.dataFile(location);
	    filePath = file.getPath();
	  };

	  public void checkLocation(String location){
	    int count = 0;
	    for(int i=location.length()-1;i>-1;i--){
	      char c = location.charAt(i);

	      if(c=='\\'){
	        folderPath = location.substring(0,i);
	        this.location = location.substring(i,location.length());
	        count ++;
	        break;
	      }
	    }
	    if(count==0)this.location = location;

	    String s1 = folderPath.replace("\\","");
	    String s2 = this.location.replace("\\","");



	    PApplet.println("checkLocation: " + s1 + "\\" + counter + "\\" + s2);
	  };

	  public void update(String folder, String file,int counter ){
	    //filePath = folder + "\\" + file;
	    this.folderPath = folder +"\\";
	    this.location = file;
	    this.counter = counter;
	    appendFile = false;
	    overWrite = true;
	  };

	  public void saveData() {
	    if (mdown()) {
	      checkFile( location, append);
	    }
	    if (mdown)
	      output.println(applet.mouseX + ",+ " + applet.mouseY);
	    close();
	  };

	  public void open() {
	    checkFile(location, append);
	  };

	  public void write(String s) {
	    // use this one to append the file created
	    if(output!=null){
	      output.println(s);
	      if(debug)PApplet.println("successful write",s);
	      output.flush(); // Writes the remaining data to the file
	      //output.close(); // Finishes the file
	    }
	    else PApplet.println("Create Save File");
	  };

	  public void write(float f) {
	    output.println(PApplet.str(f));
	  };

	  public void write_(String s) {
	    //use this one to overwrite file
	    //output.write(s);
	    output.println(s);
	    if(output!=null){
	      if(debug)PApplet.println("successful overwrite",s);
	      output.flush(); // Writes the remaining data to the file
	      output.close(); // Finishes the file
	    }else PApplet.println("failed",location,folderPath);
	  };

	  public void write(String []s) {
	    String s1 = "";
	    for (int i=0; i<s.length; i++) {
	      s1 += s[i];
	    }
	    if(s1!=null&&output!=null)output.println(s1);
	  };

	  public void close() {
	    if(output!=null){
	      output.flush(); // Writes the remaining data to the file
	      output.close(); // Finishes the file
	    }else PApplet.println(location,folderPath);
	  };

	  boolean onMouseUp() {
	    boolean k = false;
	    if (pos()&&!applet.mousePressed&&onMouseUp) {
	      onMouseUp = false;
	    } else if (pos()&&applet.mousePressed&&!onMouseUp) {
	      output.println(counter);
	      onMouseUp = true;
	      k = false;
	    } else if (onMouseUp&&!applet.mousePressed) {
	      k = true;
	      onMouseUp = false;
	      counter ++;
	    }

	    return k;
	  };

	  boolean mdown() {
	    boolean k= false;
	    if (mdown)k = false;
	    if (applet.mousePressed&&!mdown) {
	      mdown = true;
	      k = true;
	    }
	    if (!applet.mousePressed)mdown = false;    
	    return k;
	  };

	  boolean pos() {
	    return applet.mouseX>0&&applet.mouseX<applet.width&&applet.mouseY>0&&applet.mouseY<applet.height;
	  };

	  public void checkFile(String location, boolean append) {
	    if (appendFile) {

	      file = applet.dataFile(folderPath + "/" + location);
	      filePath = file.getPath();
	      filePath = filePath.replace(location, "");
	      String[] list = null;
	      if(debug)PApplet.println("checkFile");
	      if (listNames(filePath)!=null&&!match) {
	        PApplet.println(filePath);
	        
	        list = listNames(filePath);
	        boolean b = false;
	        for(int j=Bms.maxFolderSize;j>-1;j--){
	          //applet.println(j);
	          if(b)break;
	          counter = j;
	          for (int i=list.length-1;i>-1; i--) {

	            int n = Integer.parseInt(list[i]);
	            if (j == n){
	              //applet.println(j + ", " + list[i]);
	              counter = j;
	              b = true;
	              break;
	            }}}
	          match = true;
	          if(!b)counter = -1;
	          if(counter>=0)counter = counter + 1;
	          else counter = 0;
	          
	          //applet.println(counter);
	      }
	      else{
	       file = applet.dataFile(folderPath + "/" + location);
	       filePath = file.getPath();
	       filePath = filePath.replace(location, ""); 
	      }
	      file = applet.dataFile(folderPath + "/" + counter + location);
	      filePath = file.getPath();
	      //appendFile = false;
	      //append = true;
	    }
	    if(file!=null&&(!file.exists()&&!append2)) {
	      //output = createWriter("/data/" + folderPath + "/" + counter + location);
	      //applet.println("folderpath " + folderPath + "\\" + counter+"\\" + location);
	    }
	    //overWrite||
	    if(output == null){
	      output = applet.createWriter("/data/" + folderPath + "/" + counter + "/"+ location);
	      //applet.println("new folderpath " + folderPath + counter + "\\" + location);
	      file = applet.dataFile(folderPath + "/" + counter + "/" + location);
	      filePath = file.getPath();
	      filePath = filePath.replace(location, ""); 
	    }
	    if (debug) PApplet.println(filePath);
	    try {

	      FileWriter fw = new FileWriter(file, true);///true = append
	      BufferedWriter bw = new BufferedWriter(fw);
	      output = new PrintWriter(bw);
	    }
	    catch(IOException ioe) {
	      PApplet.println("Exception ");
	      ioe.printStackTrace();
	      PApplet.println(filePath);
	    }
	  };

	String[] listNames(String dir) {
	  
	  if(dir==null)return null;
	  File file  = new File(dir);
	  if (file.isDirectory()) {
	    String names[] = file.list();
	    return names;
	  } else {
	    // If it's not a directory
	    return null;
	  }
	};

	int totalFiles(String dir) {
	  File file  = new File(dir);
	  if (file.isDirectory()) {
	    String names[] = file.list();
	    return names.length;
	  } else {
	    // If it's not a directory
	    return -1;
	  }
	};

	String getFileExtension(File file) {
	  String fileName = file.getName();
	  if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
	  return fileName.substring(fileName.lastIndexOf(".")+1);
	  else return null;
	};
};
