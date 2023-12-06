package processing.sketches;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class Main extends PApplet {
    static final int DETAIL = 25;
    float zoom = 0.5f;

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        background(255);
        colorMode(HSB);
        Mandelbrot();
    }

//    public Cnum ZnPlusOne(Cnum Zn, Cnum C) {
//          //Compute Zn+1 = Zn^2 + C
//    }

//    public float getCnumMagnitude(Cnum cnum) {
//
//    }

    public void Mandelbrot() {
        noStroke();
        PVector size = new PVector(width/2.0f, height/2.0f);

        //push and pop matrix are necessary for coordinate transformations (begin and end transformation).
        //In this case we are using translate to shift the coordinates
        pushMatrix();
            //Center the canvas so that (0,0) is in the center of the window
            translate(size.x, size.y);

            //We iterate through every pixel on the screen
            for (float i = -size.x; i < size.x; i++) {
                for (float j = -size.y; j < size.y; j++) {
                    //We'll count the number of Zn+1 iterations (pass) before it diverges to infinity
                    //If the number of iterations surpasses DETAIL then we assume it never diverges
                    //If the magnitude of Zn (mag) passes 3 then we can assume it diverges
                    float pass = 0;
                    float mag = 0;
                    Cnum C = new Cnum((1.0f/zoom)*i/size.x, (1.0f/zoom)*j/size.y);
                    Cnum Zn = new Cnum();
                    while(pass < DETAIL && mag < 3) {
                        pass++;

                        //Get the next iteration using our helper function
                        Zn = ZnPlusOne(Zn, C);
                        mag = getCnumMagnitude(Zn);
                    }
                    //The color of the pixel depends on the number of passes it made it through before
                    //divergence
                    fill(color(187*(pass/DETAIL),255,255));
                    rect(i, j, 1, 1);
                }
            }
        popMatrix();
    }

    public static void main(String... args) {
        PApplet.main("processing.sketches.Main");
    }
}
