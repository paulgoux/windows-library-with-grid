import template.library.*;

HelloLibrary hello;

public void setup() {
  size(400,400);
  smooth();
  
  hello = new HelloLibrary(this);
  
  PFont font = createFont("",40);
  textFont(font);
}

public void draw() {
  background(0);
  fill(255);
  text(hello.sayHello(), 40, 200);
}