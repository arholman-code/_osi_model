/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _osi_;

import java.awt.*;
import javax.swing.JPanel;


public class SineWave extends JPanel{

    public int wavelength, amplitude, phase;


    public SineWave(int w, int a, int p) {
       wavelength = w;
       amplitude = a;
       phase = p;
    }

    public void advance(int phaseIncrement) {
       phase -= phaseIncrement;
       if(phase >= 360) {
            phase %= 360;
        }
       if(phase <    0) {
            phase += 360;
        } 
    }

    public SineWave copy() {
       return new SineWave(wavelength, amplitude, phase);
    }

    public int getY(int x) {
       double degreesPerPixel = 360.0 / wavelength;
       double degrees = (-phase+180) + x*degreesPerPixel;
       return (int)(amplitude*Math.sin(degrees*Math.PI/180.0));
    }


    public void draw(Graphics g, int x, int y, int length) 
    {   for (int i=1; i<=length; i++)
        {  Point p1 = new Point(x+i, y+getY(i));
           Point p2 = new Point(x+i+1, y+getY(i+1));
           g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public int getPhase(float length) 
    {   return (int)(360*(length/wavelength) + phase);
    }

    public  float getWaveNum(int x) {
       return (float)(x/wavelength);
    }

  
}


