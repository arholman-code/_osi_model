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

    public void move(int phaseShift) {
       phase -= phaseShift;
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

    public void draw(Graphics g, int x, int y, int length) 
    {   for (int i=1; i<=length; i++)
        {  Point p1 = new Point(x+i, y+getY(i));
           Point p2 = new Point(x+i+1, y+getY(i+1));
           g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}


