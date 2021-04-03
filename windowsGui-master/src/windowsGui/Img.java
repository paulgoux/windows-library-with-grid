package windowsGui;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PConstants;

public class Img {
	  BMScontrols Bms;
	  PApplet applet;
	  float Mean = 0,Variance,VarianceR,VarianceG,VarianceB,VarianceBR;
	  PImage img, mean,mean_,meanGx,meanGy,blurX,blurY, threshold, variance,varianceR,varianceG,varianceB,
	  		 varianceBR, kMeans, kNearest,sobel, sobelx, sobely,sobel2, sobel2x, sobel2y, sobelMax,sobelMin,sobelG,
	  		 gradientB, blur,combined;
	  
	  int [][]SobelH = {{-1, -9, -1}, 
	                    {0, 0, 0}, 
	                    {1, 9, 1}};

	  int [][]SobelV = {{-1, 0, 1}, 
	                    {-9, 0, 9}, 
	                    {-1, 0, 1}};
	                    
	  int [][]SobelH_ = {{-2, -1, 0}, 
	                    {-1, 0, 1}, 
	                    {0, 1, 2}};

	  int [][]SobelV_ = {{0, 1, 2}, 
	                    {-1, 0, 1}, 
	                    {-2, -1, 0}};
	                    
	  int [][]edgev = {{-1, -1, -1}, 
	                   {0, 0, 0}, 
	                   {1, 1, 1}};

	  int [][]edgeh = {{-1, 0, 1}, 
	                   {-1, 0, 1}, 
	                   {-1, 0, 1}};

	  int [][]LapLacian = {{0, 1, 0}, 
	                      {-1, 4, -1}, 
	                      {0, 1, 0}};

	  int [][]LapLacianD = {{-1, -1, -1}, 
	                        {-1, 8, -1}, 
	                        {-1, -1, -1}};
	                        
	  int [][]edge = {{-1, 1, -1}, 
	                  {-1, 0, -1}, 
	                  {-1, -1, -1}};
	                  
	  int [][]meanX = {{1,1,1}, 
	                   {0,0,0}, 
	                   {1,1,1}};

	  int [][]meanY = {{1,1,1}, 
	                   {2,0,2}, 
	                   {1,0,1}};
	                   
	  int [][]neighbours;
	  float [][]gradient;
	  Img(String s) {
	    img = applet.loadImage(s);
	    neighbours = new int[img.width][img.height];
	    gradient = new float[img.width][img.height];
	  };

	  public Img(PImage p,BMScontrols bms){
	    Bms = bms;
	    applet = bms.applet;
	    img = p;
	  };

	  public Img(int [] array,int w,int h,BMScontrols bms){
		  Bms = bms;
		  applet = bms.applet;
	      img = new PImage(w, h, PConstants.ARGB);
	      img.pixels = array;
	    
	  };

	  public  Img(int w,int h,BMScontrols bms){
		  Bms = bms;
		  applet = bms.applet;
	      img = new PImage(w, h, PConstants.ARGB);
	    
	  };

	  Img(){
	    
	  };
	  
	  public void set(PImage img){
	    this.img = img;
	  };

	  public void threshold(float a) {
	    threshold = new PImage(img.width, img.height, PConstants.RGB);
	    img.loadPixels();
	    threshold.loadPixels();
	    if (mean==null) {
	      for (int i=0; i<img.width; i++) {
	        for (int j=0; j<img.height; j++) {
	          int p = i + j * img.width;
	          float b = applet.brightness(img.pixels[p]);
	          if (b>a)b = 0;
	          threshold.pixels[p] = applet.color(255-b);
	        }
	      }
	    } else {
	      //for (int i=0; i<mean.width; i++) {
	      //  for (int j=0; j<mean.height; j++) {
	      //    int p = i + j * mean.width;
	      //    float b = applet.brightness(mean.pixels[p]);
	      //    //if (b>a)b = 0;
	      //    threshold.pixels[p] = applet.color(b);
	      //  }
	      //}
	    }

	    threshold.updatePixels();

	    threshold.loadPixels();
	    for (int i=0; i<img.pixels.length; i++) {
	      float b = applet.brightness(mean.pixels[i]);
	      //applet.println(b);
	      //if (b<a)b=0;
	      b = 255;

	      threshold.pixels[i] = applet.color(applet.random(b));
	      //threshold.pixels[i] = applet.color(b);
	    }
	    threshold.updatePixels();
	  };

	  public void threshold(float a, PImage im) {
	    threshold = new PImage(im.width, im.height,PConstants.RGB);
	    threshold.loadPixels();
	    im.loadPixels();

	    for (int i=0; i<im.width; i++) {
	      for (int j=0; j<im.height; j++) {
	        
	        int p = i + j * im.width;
	        float b = applet.brightness(im.pixels[p]);
	        boolean k = getTMin(i,j,im,a);
	        //b = 255;
	        if (k)b = 0;
	        else b = 255;
	  
	        threshold.pixels[p] = applet.color((b));
	    }}
	    threshold.updatePixels();
	  };
	  
	  boolean getTMin(int x, int y,PImage im,float t) {
	    
	    float []min = new float[2];
	    min[0] = 255;
	    
	    boolean k = false;
	    int p = x + y * im.width;
	    
	    for (int i=x-1; i<=x+1; i++) {
	      for (int j=y-1; j<=y+1; j++) {
	        
	        int p1 = i+j*im.width;
	        
	        if(p1>0&&p1<im.pixels.length&&p1!=p){
	          
	          float c = applet.brightness(im.pixels[p1]);
	          
	          if(min[0]>c){
	            min[0] = c;
	            min[1] = p1;
	          }}}
	    }
	    
	    int p1 = (int)min[1];
	    
	    float c = applet.brightness(im.pixels[p]);
	    float c2 = applet.brightness(im.pixels[p1]);
	    float t2 = 30;
	    //applet.println(min[0]);
	    float d = PApplet.abs((c)-c2);
	    
	    //if(c<=c2&&c<t||c2<t&&d<t2)k = true;
	    //if(c<t&&(c<=c2)||c2<t&&(d<t2)||c2<t&&d<t2)k = true;
	    //if(c<t&&(c>=c2)^c>t&&(d>t2)||c2<t&&d<t2)k = true;
	    if(c2<c){
	      if(c2<t+t2)k = true;
	    }
	    else{
	      //if(c<t)k = true;
	    }
	    //applet.println(c,k,t,t+t2);
	    //applet.println(c,c2,c<t,d<t2,t,k,d);
	    //applet.println(d<t2&&c2>c,c2<c,c,c2,k);
	    return k;
	    
	  };

	  public void mean() {

	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    float mean_ = 0;
	    img.loadPixels();
	    for (int i=0; i<img.pixels.length; i++) {
	      float b = applet.brightness(img.pixels[i]);
	      mean_ += b;
	    }

	    mean_ /= img.pixels.length;

	    mean.loadPixels();
	    for (int i=0; i<img.pixels.length; i++) {
	      float b = applet.brightness(img.pixels[i]);
	      float a = mean_ - b;
	      mean.pixels[i] = applet.color(255-a);
	    }

	    mean.updatePixels();
	  };

	  public void mean(float k) {

	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    Mean = 0;
	    img.loadPixels();
	    for (int i=0; i<img.pixels.length; i++) {
	      float b = applet.brightness(img.pixels[i]);
	      Mean += b;
	    }

	    Mean /= img.pixels.length;
	    //Mean = k + Mean;

	    mean.loadPixels();
	    for (int i=0; i<img.pixels.length; i++) {
	      float b = applet.brightness(img.pixels[i]);
	      float a = Mean - b;
	      mean.pixels[i] = applet.color(255-a);
	    }

	    mean.updatePixels();
	  };

	  public void mean(int a) {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    mean.loadPixels();
	    mean_ = new PImage(img.width, img.height,PConstants.RGB);
	    mean_.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        //float b = applet.brightness(img.pixels[p]);

	        float []mn = getNeighboursMean(i, j, a);
	        float m = mn[0];
	        //applet.println(mean_);
	        float a1 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        float a2 = applet.red(img.pixels[p]);
	        float a3 = applet.green(img.pixels[p]);
	        float a4 = applet.blue(img.pixels[p]);

	        float a5 = applet.brightness(img.pixels[p]);
	        //applet.println(mean_ - b);

	        //img.pixels[p] = applet.color(b);
	        //float k = mean_*mean_*mean_*mean_*mean_ -(-mean_ -a1)*(-mean_ +a1)*(-mean_ -a2)*(-mean_ +a2)*(-mean_ -a3)*(-mean_ +a3)*(-mean_ -a4)*(-mean_ +a4)*(-mean_ -a5)*(-mean_ +a5);
	        //mean.pixels[p] = applet.color((255)-k);
	        //if ((mean_ -b)<20)
	        //mean.pixels[p] = applet.color(255-(mean_ -(-mean_ -a1)));
	        //mean.pixels[p] = applet.color(255-(mean_*mean_ -(-mean_ -a1)));
	        mean_.pixels[p] = applet.color(255-(m - a5));
	        mean.pixels[p] = applet.color(255-(m - a5)*25);
	        //mean.pixels[p] = applet.color(0);
	        //mean.pixels[p] = applet.color(applet.random(255));
	        //else mean.pixels[p] = applet.color(255);
	      }
	    }
	    mean.updatePixels();
	  };
	  
	  public void mean(int a,PImage img) {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    mean.loadPixels();
	    mean_ = new PImage(img.width, img.height,PConstants.RGB);
	    mean_.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        //float b = applet.brightness(img.pixels[p]);

	        float []mn = getNeighboursMean(i, j, a,img);
	        float m = mn[0];
	        //applet.println(mean_);
	        float a1 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        float a2 = applet.red(img.pixels[p]);
	        float a3 = applet.green(img.pixels[p]);
	        float a4 = applet.blue(img.pixels[p]);

	        float a5 = applet.brightness(img.pixels[p]);
	        //applet.println(mean_ - b);

	        //img.pixels[p] = applet.color(b);
	        //float k = mean_*mean_*mean_*mean_*mean_ -(-mean_ -a1)*(-mean_ +a1)*(-mean_ -a2)*(-mean_ +a2)*(-mean_ -a3)*(-mean_ +a3)*(-mean_ -a4)*(-mean_ +a4)*(-mean_ -a5)*(-mean_ +a5);
	        //mean.pixels[p] = applet.color((255)-k);
	        //if ((mean_ -b)<20)
	        //mean.pixels[p] = applet.color(255-(mean_ -(-mean_ -a1)));
	        //mean.pixels[p] = applet.color(255-(mean_*mean_ -(-mean_ -a1)));
	        mean_.pixels[p] = applet.color(255-(m - a5));
	        mean.pixels[p] = applet.color(255-(m - a5)*25);
	        //mean.pixels[p] = applet.color(0);
	        //mean.pixels[p] = applet.color(applet.random(255));
	        //else mean.pixels[p] = applet.color(255);
	      }
	    }
	    mean.updatePixels();
	  };
	  
	  public void mean(int a,float mult) {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    mean.loadPixels();
	    mean_ = new PImage(img.width, img.height,PConstants.RGB);
	    mean_.loadPixels();
	    meanGx = new PImage(img.width, img.height,PConstants.RGB);
	    meanGx.loadPixels();
	    meanGy = new PImage(img.width, img.height,PConstants.RGB);
	    meanGy.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        //float b = applet.brightness(img.pixels[p]);

	        float []mn = getNeighboursMean(i, j, a);
	        float m = mn[0];
	        float mx = mn[1];
	        float my = mn[2];
	        //applet.println(mean_);
	        float a1 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        float a2 = applet.red(img.pixels[p]);
	        float a3 = applet.green(img.pixels[p]);
	        float a4 = applet.blue(img.pixels[p]);

	        float a5 = applet.brightness(img.pixels[p]);
	        //applet.println(mean_ - b);

	        //img.pixels[p] = applet.color(b);
	        //float k = mean_*mean_*mean_*mean_*mean_ -(-mean_ -a1)*(-mean_ +a1)*(-mean_ -a2)*(-mean_ +a2)*(-mean_ -a3)*(-mean_ +a3)*(-mean_ -a4)*(-mean_ +a4)*(-mean_ -a5)*(-mean_ +a5);
	        //mean.pixels[p] = applet.color((255)-k);
	        //if ((mean_ -b)<20)
	        //mean.pixels[p] = applet.color(255-(mean_ -(-mean_ -a1)));
	        //mean.pixels[p] = applet.color(255-(mean_*mean_ -(-mean_ -a1)));
	        mean_.pixels[p] = applet.color(255-(m - a5));
	        mean.pixels[p] = applet.color(255-(m - a5)*mult);
	        meanGx.pixels[p] = applet.color(mx);
	        meanGy.pixels[p] = applet.color(my);
	        //mean.pixels[p] = applet.color(0);
	        //mean.pixels[p] = applet.color(applet.random(255));
	        //else mean.pixels[p] = applet.color(255);
	      }
	    }
	    mean.updatePixels();
	  };
	  
	  public void mean(int a,PImage img,float mult) {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    mean.loadPixels();
	    mean_ = new PImage(img.width, img.height,PConstants.RGB);
	    mean_.loadPixels();
	    meanGx = new PImage(img.width, img.height,PConstants.RGB);
	    meanGx.loadPixels();
	    meanGy = new PImage(img.width, img.height,PConstants.RGB);
	    meanGy.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        //float b = applet.brightness(img.pixels[p]);

	        float []mn = getNeighboursMean(i, j, a,img);
	        float m = mn[0];
	        float mx = mn[1];
	        float my = mn[2];
	        //applet.println(mean_);
	        float a1 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        float a2 = applet.red(img.pixels[p]);
	        float a3 = applet.green(img.pixels[p]);
	        float a4 = applet.blue(img.pixels[p]);

	        float a5 = applet.brightness(img.pixels[p]);
	        //applet.println(mean_ - b);

	        //img.pixels[p] = applet.color(b);
	        //float k = mean_*mean_*mean_*mean_*mean_ -(-mean_ -a1)*(-mean_ +a1)*(-mean_ -a2)*(-mean_ +a2)*(-mean_ -a3)*(-mean_ +a3)*(-mean_ -a4)*(-mean_ +a4)*(-mean_ -a5)*(-mean_ +a5);
	        //mean.pixels[p] = applet.color((255)-k);
	        //if ((mean_ -b)<20)
	        //mean.pixels[p] = applet.color(255-(mean_ -(-mean_ -a1)));
	        //mean.pixels[p] = applet.color(255-(mean_*mean_ -(-mean_ -a1)));
	        mean_.pixels[p] = applet.color(255-(m - a5));
	        mean.pixels[p] = applet.color(255-(m - a5)*mult);
	        meanGx.pixels[p] = applet.color(mx);
	        meanGy.pixels[p] = applet.color(my);
	        //mean.pixels[p] = applet.color(0);
	        //mean.pixels[p] = applet.color(applet.random(255));
	        //else mean.pixels[p] = applet.color(255);
	      }
	    }
	    meanGx.updatePixels();
	    meanGy.updatePixels();
	  };

	  public void mean_(int a) {
	    mean = new PImage(img.width, img.height,PConstants.RGB);

	    mean.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;

	        float []mean_ = getNeighboursMean(i, j, a);
	        float m = mean_[0];
	        //applet.println(mean_);
	        //float b = applet.brightness(img.pixels[p]);
	        //applet.println(mean_ - b);

	        //img.pixels[p] = applet.color(b);
	        //if ((mean_ -b)<20)
	        float k = m-((m)/(b)*(m)/(b)) *((m)*(b)/(m));
	        //k = m*m - (m-b)*(m-b);
	        //applet.println(k);
	        //k = m - b;
	        float t1 = (k);
	        //k = (m*m -t1*t1);
	        float t = 2.0f;
	        //k = 2.0 / (1.0 + applet.exp(-2.0 * k)) - 1.0;
	        k = (float) (t/ (1.0 + PApplet.exp(-t * (k))) - 1.0);

	        mean.pixels[p] = applet.color(k*255);
	        //mean

	        //mean.pixels[p] = applet.color(b);
	        //else mean.pixels[p] = applet.color(255);
	      }
	    }
	    mean.updatePixels();
	  };


	  public void meanR(int a) {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = applet.brightness(img.pixels[p]);
	        img.pixels[p] = applet.color(b);
	      }
	    }
	  };

	  public void meanG(int a) {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = applet.brightness(img.pixels[p]);
	        img.pixels[p] = applet.color(b);
	      }
	    }
	  };

	  public void meanB(int a) {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = applet.brightness(img.pixels[p]);
	        img.pixels[p] = applet.color(b);
	      }
	    }
	  };

	  public void meanRGB() {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    float mean_ = 0;
	    img.loadPixels();
	    for (int i=0; i<img.pixels.length; i++) {
	      float b = applet.brightness(img.pixels[i]);
	      mean_ += b;
	    }

	    mean_ /= img.pixels.length;
	    mean_ = 150 +mean_;

	    mean.loadPixels();
	    for (int i=0; i<img.pixels.length; i++) {
	      float r = applet.red(img.pixels[i]);
	      float g = applet.green(img.pixels[i]);
	      float b = applet.blue(img.pixels[i]);
	      float br = applet.brightness(img.pixels[i]);
	      float a = mean_ - (r+g+b+br)/4;
	      mean.pixels[i] = applet.color(255-a);
	    }

	    mean.updatePixels();
	  };

	  public void localMean() {
	    mean = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = applet.brightness(img.pixels[p]);
	      }
	    }
	  };

	  public void kMeans() {
	    kMeans = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        //float b = map(applet.brightness(img.pixels[p]),0,255,0,100);
	        float b = applet.brightness(img.pixels[p]);
	        img.pixels[p] = applet.color(b);
	      }
	    }
	  };

	  public void kNearest(float a) {
	    variance = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = applet.brightness(img.pixels[p]);

	        img.pixels[p] = applet.color(b);
	      }
	    }
	  };

	  public void variance() {
	    variance = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float var = getNeighboursVar(0, 0, 0);
	        float a1 = applet.red(img.pixels[p]);
	        float a2 = applet.green(img.pixels[p]);
	        float a3 = applet.blue(img.pixels[p]);
	        float a4 = applet.brightness(img.pixels[p]);

	        float a = var*var*var*var - ((((var)) + (var - a2))*(((var)) + (var + a3))*(((var)) - (var + a4))*(((var)) + (var + a1)))-255;
	        variance.pixels[p] = applet.color(255-a);
	      }
	    }
	  };

	  public void variance(int a) {
	    variance = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();
	    variance.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float var = getNeighboursVar(i, j, a);
	        //float a1 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        float a1 = (applet.red(mean.pixels[p]) + applet.green(mean.pixels[p]) + applet.blue(mean.pixels[p]) + applet.brightness(mean.pixels[p]))/4;
	        float a2 = applet.red(mean.pixels[p]);
	        float a3 = applet.green(mean.pixels[p]);
	        float a4 = applet.blue(mean.pixels[p]);
	        float a5 = applet.brightness(mean.pixels[p]);


	        float k = var*4;
	        float r = PApplet.sqrt((var + ( var - a1)) * a1 + (var + ( var - a2)) * a2 + (var + ( var - a3)) * a3 + (var + ( var - a4)) * a4);
	        //float r = var*var*var - (var + a1)*(var - a1)*(var + a2)*(var - a2)*(var + a3)*(var - a3)*(var + a4)*(var - a4)*(var + a5)*(var - a5);
	        //float r = var*var*var*var*var*var*var*var*var - (var + a1)*(var - a1)*(var + a2)*(var - a2)*(var + a3)*(var - a3)*(var + a4)*(var - a4)*(var + a5)*(var - a5);
	        //float r = var*var*var*var*var*var*var*var*var - (-var + a1)*(-var - a1)*(-var + a2)*(-var - a2)*(-var + a3)*(-var - a3)*(-var + a4)*(-var - a4)*(-var + a5)*(-var - a5);
	        //applet.println(k);
	        //r = ((var-applet.brightness(mean.pixels[p]))*20);
	        //r = var;
	        r = (var*var-(var - a5)*(var + a5));
	        //r = var+a1;
	        //applet.println(r);
	        //applet.println(var,r);
	        variance.pixels[p] = applet.color(r);
	      }
	    }
	    variance.updatePixels();
	  };
	  
	  public void variance(int a, PImage imgg) {
	    variance = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();
	    variance.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float var = getNeighboursVar(i, j, a,imgg);
	        //float a1 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        float a1 = (applet.red(mean.pixels[p]) + applet.green(mean.pixels[p]) + applet.blue(mean.pixels[p]) + applet.brightness(mean.pixels[p]))/4;
	        float a2 = applet.red(img.pixels[p]);
	        float a3 = applet.green(img.pixels[p]);
	        float a4 = applet.blue(img.pixels[p]);
	        float a5 = applet.brightness(img.pixels[p]);
	        float a6 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        float a7 = (applet.red(imgg.pixels[p]) + applet.green(imgg.pixels[p]) + applet.blue(imgg.pixels[p]) + applet.brightness(imgg.pixels[p]))/4;
	        float a8 = 0, a9 = 0;
	        //if(mean==null)a1 = a6;
	        if(sobel2!=null)a8 = (applet.red(sobel2.pixels[p]) + applet.green(sobel2.pixels[p]) + applet.blue(sobel2.pixels[p]) + applet.brightness(sobel2.pixels[p]))/4;


	        float k = var*4;
	        //float r = applet.sqrt((var + ( var - a1)) * a1 + (var + ( var - a2)) * a2 + (var + ( var - a3)) * a3 + (var + ( var - a4)) * a4);
	        //float r = var*var*var - (var + a1)*(var - a1)*(var + a2)*(var - a2)*(var + a3)*(var - a3)*(var + a4)*(var - a4)*(var + a5)*(var - a5);
	        //float r = var*var*var*var*var*var*var*var*var - (var + a1)*(var - a1)*(var + a2)*(var - a2)*(var + a3)*(var - a3)*(var + a4)*(var - a4)*(var + a5)*(var - a5);
	        //float r = var*var*var*var*var*var*var*var*var - (-var + a1)*(-var - a1)*(-var + a2)*(-var - a2)*(-var + a3)*(-var - a3)*(-var + a4)*(-var - a4)*(-var + a5)*(-var - a5);
	        //applet.println(k);
	        //r = ((var-applet.brightness(mean.pixels[p]))*20);
	        //r = var;
	        //float r = applet.sqrt(var*var-(var - a1)*(var - a6)+a5*a5);
	        float r = PApplet.sqrt(var + (a1));
	        //float r = (var/2 + (a8+a7)/2 );
	        // for combined sobel;
	        //float r = (var/2 + (a7+a1)/2.5 );
	        //float r = (var -a1);
	        //blur filters-----------------------------------
	        //float r = applet.sqrt((var * var - ( var - a1)*(var + a1))-a1*a1);
	        //float r = applet.sqrt((var * var - ( var - a1)*(var - a1)))-a1*a1;
	        //-------------------------------------
	        
	        //r/=10;
	        //if(r<200)r=0;
	        //else r = 255;
	        //else r = 255;
	        //
	        //r += a6;
	        //float r = (var - a1);
	        //applet.println(r);
	        //applet.println(var,r);
	        variance.pixels[p] = applet.color((r));
	      }
	    }
	    variance.updatePixels();
	  };

	  public void variance(int a, int n) {
	    variance = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();
	    //variance.loadPixels();
	    Variance = 0;
	    for (int i=0; i<img.pixels.length; i++) {
	      float a1 = (applet.red(img.pixels[i]) + applet.green(img.pixels[i]) + applet.blue(img.pixels[i]) + applet.brightness(img.pixels[i]))/4;
	      float a2 = (applet.red(mean.pixels[i]) + applet.green(mean.pixels[i]) + applet.blue(mean.pixels[i]) + applet.brightness(mean.pixels[i]))/4;
	      //float a2 = (applet.red(threshold.pixels[i]) + applet.green(threshold.pixels[i]) + applet.blue(threshold.pixels[i]) + applet.brightness(threshold.pixels[i]))/4;
	      //a2 = applet.brightness(mean.pixels[i]);
	      //applet.println("mean " + i + " " + a2);
	      Variance += (a2 - a1);
	      //float a1 = applet.red(img.pixels[p]);
	      //float a2 = applet.green(img.pixels[p]);
	      //float a3 = applet.blue(img.pixels[p]);
	      //float a4 = applet.brightness(img.pixels[p]);

	      //float k = var*4;
	      //float r = (var + ( var - a1)) * a1 + (var + ( var - a2)) * a2 + (var + ( var - a3)) * a3 + (var + ( var - a4)) * a4;
	      ////applet.println(k);
	      ////applet.println(var,r);
	      //variance.pixels[p] = applet.color(255-(k-r));
	    }
	    //variance.updatePixels();
	    Variance /= img.pixels.length;
	    //Variance = applet.sqrt(Variance);
	    //var = applet.abs(var);
	    PApplet.println("Variance " + Variance);
	    variance.loadPixels();
	    img.loadPixels();
	    PApplet.println(Variance);
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float m = applet.brightness(mean.pixels[p]);
	        //float a1 = applet.red(img.pixels[p]);
	        float a1 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        //float a1 = (applet.red(mean.pixels[p]) + applet.green(mean.pixels[p]) + applet.blue(mean.pixels[p]) + applet.brightness(mean.pixels[p]))/4;
	        float a2 = applet.red(img.pixels[p]);
	        float a3 = applet.green(img.pixels[p]);
	        float a4 = applet.blue(img.pixels[p]);
	        float a5 = applet.brightness(img.pixels[p]);

	        float k = Variance*4;
	        //float r = applet.sqrt(Variance*Variance - a5*a5);
	        float r = Variance*Variance - (Variance - a1)*(Variance + a2);
	        //float b = Variance*Variance*Variance*Variance -((((Variance+a1)) + (Variance - a2))*(((Variance)) + (Variance + a3))*(((Variance)) + (Variance + a4))*((Variance) + (Variance + a1)))-255;
	        //applet.println(r);
	        ////applet.println(Variance,r);
	        variance.pixels[p] = applet.color(r);
	      }
	    }
	    variance.updatePixels();
	  };

	  public void variance2(int a) {
	    variance = new PImage(img.width, img.height,PConstants.RGB);
	    variance.loadPixels();
	    varianceR = new PImage(img.width, img.height,PConstants.RGB);
	    varianceR.loadPixels();
	    varianceG = new PImage(img.width, img.height,PConstants.RGB);
	    varianceG.loadPixels();
	    varianceB = new PImage(img.width, img.height,PConstants.RGB);
	    varianceB.loadPixels();
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float []v = getNeighboursVar2(i, j, a);
	        float a1 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	        //float a1 = (applet.red(mean.pixels[p]) + applet.green(mean.pixels[p]) + applet.blue(mean.pixels[p]) + applet.brightness(mean.pixels[p]))/4;
	        float a2 = applet.red(img.pixels[p]);
	        float a3 = applet.green(img.pixels[p]);
	        float a4 = applet.blue(img.pixels[p]);
	        float a5 = applet.brightness(img.pixels[p]);
	        
	        float var = v[0];

	        float k = var*4;
	        //float r = (var + ( var - a1)) * a1 + (var + ( var - a2)) * a2 + (var + ( var - a3)) * a3 + (var + ( var - a4)) * a4;
	        //float r = var*var*var - (var + a1)*(var - a1)*(var + a2)*(var - a2)*(var + a3)*(var - a3)*(var + a4)*(var - a4)*(var + a5)*(var - a5);
	        //float r = var*var*var*var*var*var*var*var*var - (var + a1)*(var - a1)*(var + a2)*(var - a2)*(var + a3)*(var - a3)*(var + a4)*(var - a4)*(var + a5)*(var - a5);
	        //float r = var*var*var*var*var*var*var*var*var - (-var + a1)*(-var - a1)*(-var + a2)*(-var - a2)*(-var + a3)*(-var - a3)*(-var + a4)*(-var - a4)*(-var + a5)*(-var - a5);
	        //applet.println(k);
	        //applet.println(var,r);
	        variance.pixels[p] = applet.color((k));
	      }
	    }
	    variance.updatePixels();
	  };

	  public void kNearest() {
	    variance = new PImage(img.width, img.height,PConstants.RGB);
	    img.loadPixels();

	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = applet.brightness(img.pixels[p]);

	        img.pixels[p] = applet.color(b);
	      }
	    }
	  };
	  
	  public void combine(PImage a,PImage b){
	    combined = new PImage(img.width, img.height,PConstants.RGB);
	    combined.loadPixels();
	    for (int i=0; i<img.pixels.length; i++) {
	      
	      float b1 = applet.brightness(a.pixels[i]);
	      float b2 = applet.brightness(b.pixels[i]);
	      
	      if(b1<b2)combined.pixels[i] = applet.color(b1);
	      else combined.pixels[i] = applet.color(b2);
	      
	    }
	    combined.updatePixels();
	  };

	  public void blur(int a) {
	    blur = new PImage(img.width, img.height,PConstants.RGB);
	    //sobelG = new PImage(img.width, img.height,applet.RGB);
	    blur.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float mean = getNeighboursBlur(i, j, a);
	        float b = applet.brightness(img.pixels[p]);
	        //blur.pixels[p] = applet.color(255-(mean*(mult2)-b));
	        blur.pixels[p] = applet.color(mean);
	        //sobelG.pixels[p] = applet.color((gradient[i][j]*100));
	        //applet.println(gradient[i][j],applet.green(sobelG.pixels[p]));
	      }
	    }
	    blur.updatePixels();
	    
	  };
	  
	  public void blur(int a,PImage img) {
	    blur = new PImage(img.width, img.height,PConstants.RGB);
	    //sobelG = new PImage(img.width, img.height,applet.RGB);
	    blur.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float mean = getNeighboursBlur(i, j, a,img);
	        float b = applet.brightness(img.pixels[p]);
	        //blur.pixels[p] = applet.color(255-(mean*(mult2)-b));
	        blur.pixels[p] = applet.color(mean);
	        //sobelG.pixels[p] = applet.color((gradient[i][j]*100));
	        //applet.println(gradient[i][j],applet.green(sobelG.pixels[p]));
	      }
	    }
	    blur.updatePixels();
	    
	  };
	  
	  public void blurS(int a) {
	    blur = new PImage(img.width, img.height,PConstants.RGB);
	    blurX = new PImage(img.width, img.height,PConstants.RGB);
	    blurY = new PImage(img.width, img.height,PConstants.RGB);
	    blur.loadPixels();
	    blurX.loadPixels();
	    blurY.loadPixels();
	    //gradientB = new PImage(img.width, img.height,applet.RGB);
	    //gradientB.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = applet.brightness(img.pixels[p]);
	        float bx = getNeighboursBlurX(i, j, a);
	        float by = getNeighboursBlurY(i, j, a);
	        blur.pixels[p] = applet.color(((bx+by)/2));
	      }
	    }
	    blur.updatePixels();
	    blurX.updatePixels();
	    blurY.updatePixels();
	  };
	  
	  public void blurS(int a,PImage img) {
	    blur = new PImage(img.width, img.height,PConstants.RGB);
	    blurX = new PImage(img.width, img.height,PConstants.RGB);
	    blurY = new PImage(img.width, img.height,PConstants.RGB);
	    blur.loadPixels();
	    blurX.loadPixels();
	    blurY.loadPixels();
	    //gradientB = new PImage(img.width, img.height,applet.RGB);
	    //gradientB.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float b = applet.brightness(img.pixels[p]);
	        float bx = getNeighboursBlurX(i, j, a,img);
	        float by = getNeighboursBlurY(i, j, a,img);
	        blur.pixels[p] = applet.color(((bx+by)/2));
	      }
	    }
	    blur.updatePixels();
	    blurX.updatePixels();
	    blurY.updatePixels();
	  };
	  
	  float getNeighboursBlur(int x, int y,int a){
	    float mean = 0;
	    int count = 0;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	          mean += applet.brightness(img.pixels[p]);
	          count++;
	      }}
	    }
	    mean /= count;
	    return mean;
	  };
	  
	  float getNeighboursBlur(int x, int y,int a,PImage img){
	    float mean = 0;
	    int count = 0;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	          mean += applet.brightness(img.pixels[p]);
	          count++;
	      }}
	    }
	    mean /= count;
	    return mean;
	  };

	  float getNeighboursBlurX(int x, int y,int a){
	    float mean = 0;
	    int count = 0;
	    int count2 = 0;
	    for (int i=x-a; i<=x+a; i++) {
	        int p = (i) + y * img.width;
	        count2++;
	        int n = (y-a+count);
	        float k = (a-PApplet.abs(n-y));
	        k = (float) (2.0 / (1.0 + PApplet.exp((float) (-2.0 * k))) - 1.0);
	        //applet.println(k);
	        //k = 1;
	        int p1 = (i) + (n) * img.width;
	        if (p<img.pixels.length&&p>0&&p1>0&&p1<img.pixels.length) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	           mean += (applet.brightness(img.pixels[p])+applet.brightness(img.pixels[p1])*k)/2;
	           count++;
	      }
	    }
	    int p = x + y * img.width;
	    blurX.pixels[p] = applet.color(mean);
	    return mean/count;
	  };
	  
	  float getNeighboursBlurX(int x, int y,int a,PImage img){
	    float mean = 0;
	    int count = 0;
	    int count2 = 0;
	    for (int i=x-a; i<=x+a; i++) {
	        int p = (i) + y * img.width;
	        count2++;
	        int n = (y-a+count);
	        float k = (a-PApplet.abs(n-y));
	        k = (float) (2.0 / (1.0 + PApplet.exp((float) (-2.0 * k))) - 1.0);
	        //applet.println(k);
	        //k = 1;
	        int p1 = (i) + (n) * img.width;
	        if (p<img.pixels.length&&p>0&&p1>0&&p1<img.pixels.length) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	           mean += (applet.brightness(img.pixels[p])+applet.brightness(img.pixels[p1])*k)/(2);
	           count++;
	      }
	    }
	    int p = x + y * img.width;
	    blurX.pixels[p] = applet.color(mean);
	    return mean/count;
	  };
	  
	  float getNeighboursBlurY(int x, int y,int a){
	    float mean = 0;
	    int count = 0;
	    int count2 = 0;
	    //prapplet.color("h");
	      for (int j=y-a; j<=y+a; j++) {
	        int p = x + (j) * img.width;
	        int n = (x-a+count);
	        float k = (a-PApplet.abs(n-x));
	        k = (float) (2.0 / (1.0 + PApplet.exp((float) (-2.0 * k))) - 1.0);
	        //k = 1;
	        int p1 = (n) + (j) * img.width;
	        
	        if (p<img.pixels.length&&p>0&&p1>0&&p1<img.pixels.length) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	           mean += (applet.brightness(img.pixels[p])+ applet.brightness(img.pixels[p1])*k)/2;
	           count++;
	      }
	    }
	    int p = x + y * img.width;
	    blurX.pixels[p] = applet.color(mean);
	    return mean/count;
	    //return applet.sqrt(mean*mean);
	  };
	  
	  float getNeighboursBlurY(int x, int y,int a,PImage img){
	    float mean = 0;
	    int count = 0;
	    int count2 = 0;
	    //prapplet.color("h");
	      for (int j=y-a; j<=y+a; j++) {
	        int p = x + (j) * img.width;
	        int n = (x-a+count);
	        float k = (a-PApplet.abs(n-x));
	        k = (float) (2.0 / (1.0 + PApplet.exp((float) (-2.0 * k))) - 1.0);
	        //k = 1;
	        int p1 = (n) + (j) * img.width;
	        
	        if (p<img.pixels.length&&p>0&&p1>0&&p1<img.pixels.length) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	           mean += (applet.brightness(img.pixels[p])+applet.brightness(img.pixels[p1])*k)/2;
	           count++;
	      }
	    }
	    int p = x + y * img.width;
	    blurX.pixels[p] = applet.color(mean);
	    return mean/count;
	    //return applet.sqrt(mean*mean);
	  };

	  public void getNeighboursAv(int x, int y) {
	    for (int i=x-1; i<=x+1; i++) {
	      for (int j=y-1; j<=y+1; j++) {
	      }
	    }
	  };

	  float[] getNeighboursMean(int x, int y, int a) {
	    float mean = 0;
	    int count = 0;
	    int count2 = 0;
	    float mx = 0;
	    float my = 0;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          mean += applet.brightness(img.pixels[p]);
	          count++;
	          if (applet.brightness(img.pixels[p])>10)count2++;
	          
	          int x1 = 0 + i - x + 1;
	          int y1 = 0 + j - y + 1;
	          if(x1>=0&&x1<3&&y1>=0&&y1<3){
	            mx += meanX[x1][y1];
	            my += meanY[x1][y1];
	          }
	        }
	      }
	    }
	    mean /= count;
	    mx /= count;
	    my /= count;
	    float []val = {mean,mx,my};
	    return val;
	  };
	  
	  float []getNeighboursMean(int x, int y, int a,PImage img) {
	    float mean = 0;
	    int count = 0;
	    int count2 = 0;
	    float mx = 0;
	    float my = 0;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          mean += applet.brightness(img.pixels[p]);
	          count++;
	          int x1 = 0 + i - x + 1;
	          int y1 = 0 + j - y + 1;
	          //applet.println(x1);
	          
	          if(x1>=0&&x1<3&&y1>=0&&y1<3){
	            mx += meanX[x1][y1] * applet.brightness(img.pixels[p]);
	            my += meanY[x1][y1] * applet.brightness(img.pixels[p]);
	            //applet.println(
	            //count++;
	          }
	        }
	      }
	    }
	    
	    mean /= count;
	    mx /= count;
	    my /= count;
	    //applet.println(mx,my,count2);
	    float []val = {mean,mx,my};
	    return val;
	  };

	  float getNeighboursMean_(int x, int y, int a) {
	    float mean = 0;
	    int count = 0;
	    int count2 = 0;
	    int p1 = x + y * img.width;
	    float b1 = (applet.red(img.pixels[p1])+applet.green(img.pixels[p1])+applet.blue(img.pixels[p1])+applet.brightness(img.pixels[p1]))/4;
	    float k = 40;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	          if (PApplet.abs(b1-b)<k
	            //if(applet.abs(applet.red(img.pixels[p1])-applet.red(img.pixels[p]))<k
	            //  ||applet.abs(applet.green(img.pixels[p1])-applet.red(img.pixels[p]))>k
	            //  ||applet.abs(applet.blue(img.pixels[p1])-applet.red(img.pixels[p]))<k
	            ) {
	            //if(true){
	            mean += applet.brightness(img.pixels[p]);
	            //mean += b;
	            count2++;
	          } else mean += 15;
	          //else mean -=20;
	          count++;
	        }
	      }
	    }
	    //if (count2<(a*2*a*2)/(a*4)) mean = 1;
	    if (mean<k*(a*2*a*2)) mean = 0;
	    //if(count2>8) mean = 0;
	    return mean/count;
	  };

	  float getNeighboursVar(int x, int y, int a) {
	    float variance = 0;
	    int count = 0;
	    int count2 = 0;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          float a1 = 0;
	          if(threshold==null){
	            a1 = (applet.red(mean.pixels[p]) + applet.green(mean.pixels[p]) + applet.blue(mean.pixels[p]) + applet.brightness(mean.pixels[p]))/4;
	            a1 = applet.brightness(mean.pixels[p]);
	          }else a1 = (applet.red(threshold.pixels[p]) + applet.green(threshold.pixels[p]) + applet.blue(threshold.pixels[p]) + applet.brightness(threshold.pixels[p]))/4;
	          float a2 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	          //variance += applet.brightness(threshold.pixels[p]) - applet.brightness(img.pixels[p]);
	          variance += a2-a1;

	          count++;
	        }
	      }
	    }
	    return PApplet.sqrt((variance*variance)/count);
	  };

	  float getNeighboursVar(int x, int y, int a,PImage mean) {
	    float variance = 0;
	    int count = 0;
	    int count2 = 0;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          float a1 = 0;
	            a1 = (applet.red(mean.pixels[p]) + applet.green(mean.pixels[p]) + applet.blue(mean.pixels[p]) + applet.brightness(mean.pixels[p]))/4;
	            a1 = applet.brightness(mean.pixels[p]);
	          float a2 = (applet.red(img.pixels[p]) + applet.green(img.pixels[p]) + applet.blue(img.pixels[p]) + applet.brightness(img.pixels[p]))/4;
	          //variance += applet.brightness(threshold.pixels[p]) - applet.brightness(img.pixels[p]);
	          variance += a2-a1;

	          count++;
	        }
	      }
	    }
	    return PApplet.sqrt((variance*variance)/count);
	  };
	  
	  float []getNeighboursVar2(int x, int y, int a) {
	    float variance = 0;
	    float varianceR = 0;
	    float varianceG = 0;
	    float varianceB = 0;
	    int count = 0;
	    int count2 = 0;
	    float a1r = 0,a1g = 0,a1b = 0,a1br = 0;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          
	          
	          float a2r = applet.red(img.pixels[p]);
	          float a2g = applet.green(img.pixels[p]);
	          float a2b = applet.blue(img.pixels[p]);
	          float a2br = applet.red(img.pixels[p]);
	          
	          varianceR += a1r-a2r;
	          varianceG += a1g-a2g;
	          varianceB += a1b-a2b;
	          
	          variance += a1br-a2br;

	          count++;
	        }
	      }
	    }
	    variance /= count;
	    varianceR /= count;
	    varianceG /= count;
	    varianceB /= count;
	    
	    float []val = {variance,varianceR,varianceG,varianceB};
	    return val;
	  };

	  public void getNeighbours2Min(int x, int y, int a, int b) {
	    for (int i=x-a; i<=x+b; i++) {
	      for (int j=y-a; j<=y+b; j++) {
	      }
	    }
	  };

	  public void getNeighbours2Min(int x, int y, int a) {
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	      }
	    }
	  };
	  
	  public void sobel(int a,float mult) {
	    sobel = new PImage(img.width, img.height, PConstants.ARGB);
	    sobel.loadPixels();
	    sobelx = new PImage(img.width, img.height,PConstants.RGB);
	    sobelx.loadPixels();
	    sobely = new PImage(img.width, img.height,PConstants.RGB);
	    sobely.loadPixels();
	    sobelG = new PImage(img.width, img.height,PConstants.RGB);
	    sobelG.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {

	        int p = i + j * img.width;
	        float[] val = getSobel(i, j);
	        float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	        float r = applet.red(img.pixels[p]);
	        float g = applet.green(img.pixels[p]);
	        float b1 = applet.blue(img.pixels[p]);
	        float b2 = applet.brightness(img.pixels[p]);
	        //applet.println(val.length);
	        float v = val[0];
	        //applet.println(val[3]);

	        //float k = ((v/b)*(v)/(b)) *((b/v)*(v))-((v)/(b));
	        //k = (b*b - (b-v)*(b+v));
	        //k = (v*v - (v-b)*(v+b));
	        
	        //applet.println(k,b);
	        //k = applet.abs(v-b);
	        //k = v-b;
	        float k = v * mult;
	        float t = 2.0f;
	        //k = 2.0 / (1.0 + applet.exp(-2.0 * k)) - 1.0;
	        //k = (t/ (1.0 + applet.exp(-t * (k))) - 1.0)*val[0]*mult;
	        //sobel.pixels[p] = applet.color(255);
	        //applet.println(k);
	        //if(k>val[0])
	        //if(k<7)
	        //sobel.pixels[p] = applet.color(k);
	        //if(255-(k-b2)<255&&val[3]>6)
	        //if(val[3]<8)
	        //if(255-(k-b2)<255)
	        //sobel.pixels[p] = applet.color(255);
	        if(a==0)sobel.pixels[p] = applet.color(255-(k-(b2)));
	        //sobel.pixels[p] = applet.color(0);
	        if(a==1)sobel.pixels[p] = applet.color(255-k);
	        if(a==2)sobel.pixels[p] = applet.color(b2-k);
	        if(a==3)sobel.pixels[p] = applet.color(k);
	        if(a==4)sobel.pixels[p] = applet.color(r - k,g - k,b1 - k,b2);
	        if(a==5)sobel.pixels[p] = applet.color(r - (k-(r)),g - (k-(g)),b1 - (k-(b1)),b2 -(k-(b2)));
	        if(a==6)sobel.pixels[p] = applet.color(255 - (k-(r)),255 - (k-(g)),255 - (k-(b1)),255 -(k-(b2)));
	        if(a==7){
	          float rng = applet.random(200);
	          float rng1 = applet.random(100);
	          if(255-(k-(b2))<100)sobel.pixels[p] = applet.color(0);
	          else sobel.pixels[p] = applet.color(255-(k-b2),0);
	        }
	        if(a==8){
	          float rng = applet.random(200);
	          float rng1 = applet.random(100);
	          if(255-(k-(b2))<100)sobel.pixels[p] = applet.color(0,rng);
	          else sobel.pixels[p] = applet.color(255-(k-b2),0);
	        }
	        if(a==9){
	          float rng = applet.random(200);
	          float rng1 = applet.random(100);
	          if(255-(k-(b2))<100)sobel.pixels[p] = applet.color(r-rng1,g-rng1,b1-rng1,rng);
	          else sobel.pixels[p] = applet.color(255-(k-b2),0);
	        }
	        //else 
	        sobelx.pixels[p] = applet.color(val[1]);
	        sobely.pixels[p] = applet.color(val[2]);
	        sobelG.pixels[p] = applet.color(0,val[1],val[2]);
	        //applet.println(val[4]);
	        //if(val[4]>0)applet.println("val " + applet.brightness(sobelG.pixels[p]),val[4]);

	        //color col
	      }
	    }
	    sobel.updatePixels();
	    sobelx.updatePixels();
	    sobely.updatePixels();
	    //sobelG.updatePixels();
	  };

	  public void sobel(PImage img,int a) {
	    sobel = new PImage(img.width, img.height,PConstants.RGB);
	    sobel.loadPixels();
	    sobelx = new PImage(img.width, img.height,PConstants.RGB);
	    sobelx.loadPixels();
	    sobely = new PImage(img.width, img.height,PConstants.RGB);
	    sobely.loadPixels();
	    sobelG = new PImage(img.width, img.height,PConstants.RGB);
	    sobelG.loadPixels();
	    img.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {

	        int p = i + j * img.width;
	        float[] val = getSobel(i, j);
	        float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	        float r = applet.red(img.pixels[p]);
	        float g = applet.green(img.pixels[p]);
	        float b1 = applet.blue(img.pixels[p]);
	        float b2 = applet.brightness(img.pixels[p]);
	        //applet.println(val.length);
	        float v = val[0];
	        //applet.println(val[3]);
	        float k = v;
	        
	        if(a==0)sobel.pixels[p] = applet.color(255-(k-(b2)));
	        //sobel.pixels[p] = applet.color(0);
	        if(a==1)sobel.pixels[p] = applet.color(255-k);
	        if(a==2)sobel.pixels[p] = applet.color(b2-k);
	        if(a==3)sobel.pixels[p] = applet.color(k);
	        if(a==4)sobel.pixels[p] = applet.color(applet.random(255));
	        //sobel.pixels[p] = applet.color(((b2)-k));
	        //sobel.pixels[p] = applet.color(0);
	        //sobel.pixels[p] = applet.color(k);
	        //sobel.pixels[p] = applet.color(k-r,k-g,k-b1);
	        //sobel.pixels[p] = applet.color((k-r),(k-g),(k-b1));
	        //sobel.pixels[p] = applet.color(r-k,g-k,b1-k);
	        //float s = applet.brightness(sobel.pixels[p]);
	        //if(s>250)sobel.pixels[p] = applet.color(0);
	        //applet.println(sobel.pixels[p]);
	        //if(k<200)sobel.pixels[p] = applet.color(img.pixels[p]);
	        //sobel.pixels[p] = applet.color(val[0]);
	        //else sobel.pixels[p] = applet.color(255);
	        sobelx.pixels[p] = applet.color(val[1]);
	        sobely.pixels[p] = applet.color(val[2]);
	        sobelG.pixels[p] = applet.color(0,0,0,val[4]);
	        
	        //applet.println(applet.brightness(sobelG.pixels[p]));

	        //color col
	      }
	    }
	    sobel.updatePixels();
	    sobelx.updatePixels();
	    sobely.updatePixels();
	    sobelG.updatePixels();
	  };

	  float []getSobel(int x, int y) {
	    float val = 0;
	    float vy = 0;
	    float vx = 0;
	    float vd = 0;
	    float hd = 0;
	    float eh = 0;
	    float ev =0;
	    img.loadPixels();
	    int count = 0;
	    int count2 = 0;
	    int p1 = x + y * img.width;
	    //float b1 = (applet.red(img.pixels[p1])+applet.green(img.pixels[p1])+applet.blue(img.pixels[p1])+applet.brightness(img.pixels[p1]))/4;
	    float k = 40;
	    int z = 1;
	    for (int i=x-z; i<=x+z; i++) {
	      for (int j=y-z; j<=y+z; j++) {

	        int p = i + j * img.width;
	        if (p>0&&p<img.pixels.length) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p]))/3;
	          //float b1 = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p]))/3;
	          if (PApplet.abs(applet.red(img.pixels[p1])-applet.red(img.pixels[p]))<k
	            ||PApplet.abs(applet.green(img.pixels[p1])-applet.green(img.pixels[p]))<k
	            ||PApplet.abs(applet.blue(img.pixels[p1])-applet.blue(img.pixels[p]))<k
	            ||applet.brightness(img.pixels[p1])-applet.brightness(img.pixels[p])<k
	            ) {
	            
	            count2 ++;
	            
	            int x1 = 0 + i - x + 1;
	            int y1 = 0 + j - y + 1;
	            
	            float col = applet.brightness(img.pixels[p]);
	            // if(applet.green(img.pixels[p])>col)col = applet.green(img.pixels[p]);
	            // if(applet.blue(img.pixels[p])>col)col = applet.blue(img.pixels[p]);
	            col = b;
	            float v = SobelH[x1][y1] * col;
	            float h = SobelV[x1][y1] * col;
	            
	            float vd_ = SobelH_[x1][y1] * col;
	            float hd_ = SobelV_[x1][y1] * col;
	            float eh_ = SobelV[x1][y1] * col;
	            float ev_ = SobelH[x1][y1] * col;
	            
	            //v = LapLacian[x1][y1] * col;
	            //h = LapLacianD[x1][y1] * col;

	            //applet.println(col);
	            vx += (v);
	            
	            vy += (h);
	            //applet.println(col,v,vx,h,vy);
	            vd += vd_;
	            hd += hd_;
	            ev += ev_;
	            eh += eh_;
	            }

	          //neighbours[x][y] 
	          count ++;
	        }
	      }
	    }

	    vx/= count;
	    vy/= count;
	    vd/= count;
	    hd/= count;
	    //val = applet.sqrt(vx*vx + vy*vy + vd*vd + hd*hd + ev*ev + eh*eh);
	    //val = applet.sqrt(vx*vx + vy*vy + vd*vd + hd*hd);
	    val =  PApplet.sqrt(vx*vx + vy*vy);
	    //val = applet.sqrt((vx+vy)/2*(vx+vy)/2);
	    //applet.println(vy,vx);
	    float [] sob = {val, vx, vy, count2,PApplet.atan2(vy,vx)};
	    //float [] sob = {val, (vx+vd+ev)/3, (vy+hd+eh)/3, count2,applet.atan2((vy+hd+eh)/3,(vx+vd+ev)/3)};
	    //gradient[x][y] = applet.atan2(vy,vx);
	    img.updatePixels();
	    return sob;
	  };

	  float []getSobel(int x, int y, PImage img) {
	    float val = 0;
	    float vy = 0;
	    float vx = 0;
	    img.loadPixels();
	    int count = 0;
	    for (int i=x-1; i<=x+1; i++) {
	      for (int j=y-1; j<=y+1; j++) {

	        int p = i + j * img.width;
	        if (p>0&&p<img.pixels.length) {
	          int x1 = 0 + i - x + 1;
	          int y1 = 0 + j - y + 1;

	          float col = applet.brightness(img.pixels[p]);
	          col = (applet.red(img.pixels[p])+applet.blue(img.pixels[p])+applet.green(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	          float v = SobelH[x1][y1] * col;
	          float h = SobelV[x1][y1] * col;

	          //applet.println(col);
	          vx += v;
	          vy += h;

	          count ++;
	        }
	      }
	    }

	    vx/= count;
	    vy/= count;

	    val = PApplet.sqrt(vx*vx + vy*vy);
	    //applet.println(count);
	    float [] sob = {val, vx, vy, count};
	    return sob;
	  };
	  
	  public void sobel2(int a,float mult2) {
	    sobel2 = new PImage(img.width, img.height,PConstants.RGB);
	    sobel2.loadPixels();
	    sobel2x = new PImage(img.width, img.height,PConstants.RGB);
	    sobel2x.loadPixels();
	    sobel2y = new PImage(img.width, img.height,PConstants.RGB);
	    sobel2y.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float []mean = getSobel2(i, j, a);
	        float b = applet.brightness(img.pixels[p]);
	        b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	        float k = mean[0]*(mult2);
	        if(k>0)k = 255;
	        //applet.println(mean[1]);
	        sobel2.pixels[p] = applet.color(255-(k-b));
	        sobel2.pixels[p] = applet.color(255-(k));
	        //sobel2.pixels[p] = applet.color(b-(k));
	        //sobel2.pixels[p] = applet.color((k));
	        sobel2x.pixels[p] = applet.color(mean[1]);
	        sobel2y.pixels[p] = applet.color(mean[2]);
	        //sobel2.pixels[p] = applet.color(mean);
	        //sobelG.pixels[p] = applet.color((gradient[i][j]*100));
	        //applet.println(gradient[i][j],applet.green(sobelG.pixels[p]));
	      }
	    }
	    sobel2.updatePixels();
	    
	  };
	  
	  public void sobel2(int a, float mult2,int c) {
	    sobel2 = new PImage(img.width, img.height, PConstants.ARGB);
	    sobel2.loadPixels();
	    sobel2x = new PImage(img.width, img.height,PConstants.RGB);
	    sobel2x.loadPixels();
	    sobel2y = new PImage(img.width, img.height,PConstants.RGB);
	    sobel2y.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {
	        int p = i + j * img.width;
	        float []mean = getSobel2(i, j, a);
	        float b = applet.brightness(img.pixels[p]);
	        float k = mean[0]*(mult2);
	        if(k>0)k=255;
	        //applet.println(mean[1]);
	        if(c==0)sobel2.pixels[p] = applet.color(255-(k-b));
	        if(c==1)sobel2.pixels[p] = applet.color(255-(k));
	        if(c==2)sobel2.pixels[p] = applet.color(255-(k)-b);
	        if(c==3)sobel2.pixels[p] = applet.color(b-k);
	        if(c==4)sobel2.pixels[p] = applet.color(k);
	        if(c==7){
	          
	          if(255-(k)<10)sobel2.pixels[p] = applet.color(255-(k),255);
	          else sobel2.pixels[p] = applet.color(255-(k),0);
	        }
	        if((255-k<0))PApplet.println("sobel :" + (255-k<0));
	        //
	        //sobel2x.pixels[p] = applet.color(255-(mean[1]*(mult2)-b));
	        //sobel2y.pixels[p] = applet.color(255-(mean[2]*(mult2)-b));
	        sobel2x.pixels[p] = applet.color((mean[1]));
	        sobel2y.pixels[p] = applet.color((mean[2]));
	        
	        
	        //sobel2.pixels[p] = applet.color(mean);
	        //sobelG.pixels[p] = applet.color((gradient[i][j]*100));
	        //applet.println(gradient[i][j],applet.green(sobelG.pixels[p]));
	      }
	    }
	    sobel2.updatePixels();
	    
	  };
	  
	  float []getSobel2(int x, int y,int a){
	    float mean = 0;
	    float meany = 0;
	    float meana = 0;
	    float meanb = 0;
	    int count = 0;
	    for (int i=x-a; i<=x+a; i++) {
	      for (int j=y-a; j<=y+a; j++) {
	        int p = i + j * img.width;
	        if (p<img.pixels.length&&p>0) {
	          float b = (applet.red(img.pixels[p])+applet.green(img.pixels[p])+applet.blue(img.pixels[p])+applet.brightness(img.pixels[p]))/4;
	          float c = (i-x);
	          float d = (j-y);
	          float e = x - i;
	          float f = y - j;
	          //if(c==0)c=1;
	          meany += applet.brightness(img.pixels[p])*(c+d);
	          mean += applet.brightness(img.pixels[p])*(e+f);
	          meana += applet.brightness(img.pixels[p])*(c+f);
	          meanb += applet.brightness(img.pixels[p])*(e+d);
	          //mean += (applet.brightness(img.pixels[p])*(c+d) + applet.brightness(img.pixels[p])*(e+f))/2;
	          //mean += applet.brightness(img.pixels[p])*(c+d);
	          count++;
	      }}
	    }
	    //return applet.sqrt((mean/count)*mean/count+meany/count*meany/count);
	    //gradient[x][y] = applet.atan2((meany+meana)/2,(mean+meanb)/2);
	    float val = PApplet.sqrt((mean/count)*mean/count+meany/count*meany/count+((meana/count)*meana/count+meanb/count*meanb/count));
	    float valx = (mean + meana)/2;
	    
	    float valy = (meany + meanb)/2;
	    valx = mean;
	    valy = meany;
	    float [] sob = {val,valx,valy};
	    return sob;
	    //return applet.sqrt(((meana/count)*meana/count+meanb/count*meanb/count));
	    //return applet.sqrt((meany/count)*meany/count+meanb/count*meanb/count);
	  };
	 
	  
	  public void sobelMax(float t){
	    sobelMax = new PImage(img.width, img.height, PConstants.ARGB);
	    sobelMax.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {

	        int p = i + j * img.width;
	        
	        boolean max = getNeighboursMax(i,j,t);
	        if(!max)sobelMax.pixels[p] = applet.color(255,0);
	        else sobelMax.pixels[p] = combined.pixels[p];
	        
	        
	      }}
	  };
	  
	  boolean getNeighboursMax(int x, int y,float t) {
	    
	    float []max = new float[7];
	    max[0] = 255;
	    
	    boolean k = false;
	    int p = x + y * img.width;
	    //float myGradient = gradient[x][y];
	    //float myGradient_ = applet.atan2(sobely.pixels[p],sobelx.pixels[p]);
	    
	    for (int i=x-1; i<=x+1; i++) {
	      for (int j=y-1; j<=y+1; j++) {
	        
	        int p1 = i+j*sobel.width;
	        
	        if(p1>0&&p1<sobel.pixels.length){
	          float c = 0;
	          if(combined==null)c = applet.brightness(sobel.pixels[p1]);
	          else c = applet.brightness(combined.pixels[p1]);
	          if(max[0]>c){
	            max[0] = c;
	            max[3] = p1;
	          }}}
	    }
	    int p1 = (int)max[3];
	    boolean k2 = false;
	    float c = applet.brightness(combined.pixels[p]);
	    float c2 = applet.brightness(combined.pixels[p1]);
	    //float t = 30;
	    float t2 = PApplet.radians(45);
	    float t3 = PApplet.radians(10);
	    float d1 = PApplet.abs(max[0]-(255-(c)));
	    float d2 = PApplet.abs(PApplet.atan2(sobely.pixels[p] - sobely.pixels[p1],sobelx.pixels[p] - sobelx.pixels[p1]));
	    //applet.println(d2);
	    float d3 = PApplet.abs(max[0]-c);
	    //if(d2<t2||c2<=255-c)k = true;
	    if(c<t)k = true;
	    //applet.println(max[0],applet.brightness(combined.pixels[p]),x,y);
	    //if(max[0]<=applet.brightness(combined.pixels[p])||k2)k=true;
	    
	    // keep this one for cartoons
	    //if((max[0]<=applet.brightness(combined.pixels[p]))||d1>t&&max[0]<=applet.brightness(combined.pixels[p]))k = true;
	    return k;
	  };
	  
	  public void sobelMax(PImage sobel,float t){
	    sobelMax = new PImage(img.width, img.height, PConstants.ARGB);
	    sobelMax.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {

	        int p = i + j * img.width;
	        
	        boolean max = getNeighboursMax(i,j,sobel,t);
	        sobelMax.pixels[p] = applet.color(255,0);
	        if( max)sobelMax.pixels[p] = applet.color(0);
	        //else if(max&&applet.brightness(sobel.pixels[p])<255-t)sobelMax.pixels[p] = applet.color(0);
	      }}
	  };
	  
	  boolean getNeighboursMax(int x, int y,PImage sobel,float t) {
	    
	    float []min = new float[2];
	    min[0] = 255;
	    
	    boolean k = false;
	    int p = x + y * img.width;
	    //float myGradient = gradient[x][y];
	    
	    for (int i=x-1; i<=x+1; i++) {
	      for (int j=y-1; j<=y+1; j++) {
	        
	        int p1 = i+j*sobel.width;
	        
	        if(p1>0&&p1<sobel.pixels.length){
	          float c = 0;
	          c = applet.brightness(sobel.pixels[p1]);
	          if(min[0]>c){
	            min[0] = c;
	            min[1] = p1;
	          }}}
	    }
	    int p1 = (int)min[1];
	    boolean k2 = false;
	    float c = applet.brightness(sobel.pixels[p]);
	    float c1 = applet.brightness(sobel.pixels[p1]);
	    float t1 = PApplet.radians(45);
	    float t2 = 30;
	    float d = PApplet.abs(c1-(255-(c)));
	    //applet.println(d2);
	    float d1 = PApplet.abs(c1-c);
	    float g = PApplet.atan2(applet.green(sobel2y.pixels[p]), applet.blue(sobel2x.pixels[p]));
	    float g1 = PApplet.atan2(applet.green(sobel2y.pixels[p1]), applet.blue(sobel2x.pixels[p1]));
	    PApplet.println(g,g1);
	    float d2 = PApplet.abs(g-g1);
	    float d3 = PApplet.abs((g-PConstants.PI)-g1);
	    //applet.println(d3,d1,c,c2,min[0]);
	    //if(d3<t)k = true;
	    //applet.println(g,g1,g-g1,t1);
	    //if(c<t&&c<=c1||d2<t&&d<t2)k = true;
	    if(c>c1&&c1<t&&d2<t1||d3<t1)k = true;

	    // for(int i=0;i<8;i++){
	    //   float theta = applet.radians(45)*i;
	    //   float theta2 = applet.radians(45)*(i+1);

	    //   if(g>theta&&g<theta2){
	    //     if(g2>theta-applet.PI&&g2<theta2-applet.PI||g2>theta+applet.PI&&g2<theta2+applet.PI)
	    //   }
	    // }
	    //applet.println(min[0],applet.brightness(combined.pixels[p]),x,y);
	    //if(min[0]<=applet.brightness(combined.pixels[p])||k2)k=true;
	    
	    // keep this one for cartoons
	    //if((min[0]<=applet.brightness(combined.pixels[p]))||d1>t&&min[0]<=applet.brightness(combined.pixels[p]))k = true;
	    return k;
	  };
	  
	  // used with mean--------------------------------
	  public void sobelMax(PImage sobel,float t,float t1){
	    sobelMax = new PImage(img.width, img.height,PConstants.RGB);
	    sobelMax.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {

	        int p = i + j * img.width;
	        
	        boolean max = getNeighboursMax(i,j,sobel,t,t1);
	        //sobelMax.pixels[p] = applet.color(255);
	        if( max)sobelMax.pixels[p] = (255);
	        //else if(max&&applet.brightness(sobel.pixels[p])<255-t)sobelMax.pixels[p] = applet.color(0);
	      }}
	  };
	  
	  boolean getNeighboursMax(int x, int y,PImage sobel,float t,float t2) {
	    
	    float []min = new float[2];
	    min[0] = 255;
	    
	    boolean k = true;
	    int p = x + y * img.width;
	    //float g = applet.atan2((meanGy.pixels[p]),(meanGx.pixels[p]));
	    float g = PApplet.atan2((sobel2x.pixels[p]),(sobel2y.pixels[p]));
	    //float g = applet.atan2((blurX.pixels[p]),(blurY.pixels[p]));
	    //else if(sobel2
	    float c = applet.brightness(sobel.pixels[p]);
	    //float t2 = 30;
	    for (int i=x-1; i<=x+1; i++) {
	      for (int j=y-1; j<=y+1; j++) {
	        
	        int p1 = i+j*sobel.width;
	        
	        if(p1>0&&p1<sobel.pixels.length){
	          float c1 = 0;
	          float g1 = PApplet.atan2(y-j,x-i);
	          float d1 = PApplet.abs(g-g1);
	          float d2 = PApplet.abs((g-PConstants.PI)-g1);
	          float t1 = PApplet.radians(45);
	          if(combined==null)c1= applet.brightness(sobel.pixels[p1]);
	          else c1= applet.brightness(combined.pixels[p1]);
	          if(((d1<t1||d2<t1)&&((c1<c&&c1<t)))||((d1<t1||d2<t1)&&((c1<c&&c1<t+t2)))){
	            min[0] = c1;
	            min[1] = p1;
	            k = false;
	          }}}
	    }
	    int p1 = (int)min[1];
	    boolean k2 = false;
	    //float c = applet.brightness(sobel.pixels[p]);
	    float c1 = applet.brightness(sobel.pixels[p1]);
	    float t1 = PApplet.radians(180);
	    
	    float d = PApplet.abs(c1-(255-(c)));
	    //applet.println(d2);
	    float d1 = PApplet.abs(c1-c);
	    float g1 = PApplet.atan2(applet.green(sobel2x.pixels[p]), applet.blue(sobel2y.pixels[p]));
	    //float g1 = applet.atan2((meanGy.pixels[p1]), (meanGy.pixels[p1]));
	    //float g1 = applet.atan2((blurX.pixels[p1]),(blurY.pixels[p1]));
	    float d2 = PApplet.abs(g-g1);
	    float d3 = PApplet.abs((g-PConstants.PI)-g1);
	    float d4 = PApplet.abs(c-t);
	    //applet.println(d3,d1,c,c2,min[0]);
	    //if(d4<t)k = true;
	    //applet.println(g,g1,g-g1,t1);
	    //if(c<t&&c<=c1||d2<t&&d<t2)k = true;
	    //if(c)k = true;
	    //if(!k&&(d3<t2)&&d4<t2)k = false;
	    // for(int i=0;i<8;i++){
	    //   float theta = applet.radians(45)*i;
	    //   float theta2 = applet.radians(45)*(i+1);

	    //   if(g>theta&&g<theta2){
	    //     if(g2>theta-applet.PI&&g2<theta2-applet.PI||g2>theta+applet.PI&&g2<theta2+applet.PI)
	    //   }
	    // }
	    //applet.println(min[0],applet.brightness(combined.pixels[p]),x,y);
	    //if(min[0]<=applet.brightness(combined.pixels[p])||k2)k=true;
	    
	    // keep this one for cartoons
	    //if((min[0]<=applet.brightness(combined.pixels[p]))||d1>t&&min[0]<=applet.brightness(combined.pixels[p]))k = true;
	    return k;
	  };
	  
	  
	  public void sobelMin(){
	    sobelMin = new PImage(img.width, img.height,PConstants.RGB);
	    sobelMin.loadPixels();
	    for (int i=0; i<img.width; i++) {
	      for (int j=0; j<img.height; j++) {

	        int p = i + j * img.width;
	        
	        boolean min = getNeighboursMin(i,j);
	        if(!min)sobelMin.pixels[p] = (255);
	        else sobelMin.pixels[p] = sobel.pixels[p];
	      }}
	  };
	  
	  boolean getNeighboursMin(int x, int y) {
	    
	    float []max = new float[3];
	    max[0] = 0;
	    boolean k = false;
	    int p = x + y * img.width;
	    float myGradient = applet.brightness(sobelG.pixels[p]);
	    for (int i=x-1; i<=x+1; i++) {
	      for (int j=y-1; j<=y+1; j++) {
	        
	        int p1 = i+j*sobel.width;
	        
	        if(p1>0&&p1<sobel.pixels.length&&p1!=p){
	        float cGradient = applet.brightness(sobelG.pixels[p1]);
	        
	        //if(cGradient==-1/myGradient||cGradient==applet.PI-(-1/myGradient)){
	        //if(cGradient==myGradient){
	          float c = applet.brightness(sobel.pixels[p1]);
	          if(max[0]>c){
	            max[0] = c;
	            max[1] = i;
	            max[2] = j;
	          }
	        //}
	      }}
	    }
	    //applet.println(max[0],applet.brightness(blur.pixels[x+y*sobel.width]));
	    int p2 = (int)max[1] + (int)max[2] * sobelG.width;
	    //if(p2
	    //applet.println((int)max[0],(int)max[1],x,y);
	    float cGradient = applet.brightness(sobelG.pixels[p]);
	    //if(max[0]>=applet.brightness(blur.pixels[x+y*sobel.width])||(cGradient==-1/myGradient||cGradient==applet.PI-(-1/myGradient)))k=true;
	    boolean k2 = false;
	    float r = applet.red(blur.pixels[p]);
	    float g = applet.green(blur.pixels[p]);
	    float b = applet.blue(blur.pixels[p]);
	    float b1 = applet.brightness(blur.pixels[p]);
	    float r1 = applet.red(blur.pixels[p2]);
	    float g1 = applet.green(blur.pixels[p2]);
	    float b2 = applet.blue(blur.pixels[p2]);
	    float b3 = applet.brightness(blur.pixels[p2]);
	    float t = 0;
	    if(PApplet.abs(r-r1)<t||PApplet.abs(g-g1)<t||PApplet.abs(b-b2)<t||PApplet.abs(b1-b3)<t)k2 = true;
	    
	    //if(max[0]<=applet.brightness(blur.pixels[x+y*sobel.width])||(cGradient==-1/myGradient||cGradient==applet.PI-(-1/myGradient))||k2)k=true;
	    if(max[0]<=applet.brightness(blur.pixels[x+y*sobel.width])&&(cGradient!=-1/myGradient&&cGradient!=PConstants.PI-(-1/myGradient)))k=true;
	    //if(max[0]<=applet.brightness(blur.pixels[x+y*sobel.width])||(cGradient==myGradient))k=true;
	    //if(max[0]<=applet.brightness(blur.pixels[x+y*sobel.width]))k=true;
	    return k;
	  };
	};
