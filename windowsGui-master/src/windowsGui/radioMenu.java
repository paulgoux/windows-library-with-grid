package windowsGui;

public class radioMenu{
	  
	public float x,w,h,y;
	  public Menu menu;
	  
	  public radioMenu(float x,float y,float w,String []s){
	    
	    menu = new Menu(x,y,w,s,true);
	    for(int i=0;i<menu.items.size();i++){
	      Button b = menu.items.get(i);
	      b.radio = true;
	    };
	  };
	  
	  public void draw(){
	    menu.draw();
	    for(int i=0;i<menu.items.size();i++){
	      Button b = menu.items.get(i);
	      b.self_toggle();
	    };
	  };
	};
