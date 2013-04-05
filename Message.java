package _osi_;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author Andrew Holman
 */
public class Message extends JPanel{
    
    int x = 70, y = 240, height = 12, width = 12;
    Dimension d = new Dimension(6, 6);
    int lvl = 0;
    int track = 0;
    int wgt_cpy[] = new int[11];
    ArrayList<Point> arqP = new ArrayList<Point>();
    ArrayList<Integer> path = new ArrayList<Integer>();
    public boolean[] canSend = new boolean[11];
    Ellipse2D msg = new Ellipse2D.Double(x, y, height, width);
    Ellipse2D arq = new Ellipse2D.Double(1, 1, 0, 0);
    String input, crc, rem;
    public int phys_head = 0, source = 01;
    StringBuilder bin = new StringBuilder();
    StringBuilder bin_copy = new StringBuilder();
    BinaryDivision BD = new BinaryDivision();
    boolean ec = false, arrived = false;
    String[] frame = {
        input, "Presentation header:Data+Padding", "Session header:Data+Padding",
        "Transport header:Data+Padding", "Network header:Data+Padding",
        "DLL header:" + phys_head + ":" + source + ":Data+Padding:1011",
        "Preamble:" + phys_head + ":" + source + ":Data+Padding:1011"
    
    };
    
    public Message(){
        boolean an = true;
        int delay = 1000;
        arqP.clear();
        ActionListener taskPerformer = new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent evt) {
                Point p = new Point();
                
                if(msg.getX() == 70 && msg.getY() < 530){
                    msg.setFrame(msg.getX(), msg.getY()+35, height, width);
                }
                else if(msg.getX() == 70 && msg.getY() >= 530){
                    msg.setFrame(msg.getX()+200, msg.getY(), height, width);
                }
                else if(track == 0){
                    if(path.get(track) == 1){
                        if(msg.getX() < 370){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+1]), 
                                    msg.getY()-(37-2*wgt_cpy[track+1]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 370){
                            if(!arqP.isEmpty()){
                                arq.setFrame(arqP.get(arqP.size() - 1), d);
                                arqP.remove(arqP.size()-1);
                            }
                            else{
                                arqP.clear();
                                d.setSize(0, 0);
                                arq.setFrame(p, d);
                                track++;
                            }
                        }
                    }
                    else if(path.get(track) == 2){
                        if(msg.getX() < 370){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() > 370){
                            if(!arqP.isEmpty()){
                                arq.setFrame(arqP.get(arqP.size() - 1), d);
                                arqP.remove(arqP.size()-1);
                            }
                            else{
                                arqP.clear();
                                d.setSize(0, 0);
                                arq.setFrame(p, d);
                                track++;
                            }
                        }
                    }
                }
                else if(track == 1){
                    d.setSize(6, 6);
                        if(path.get(track) == 5){
                            if(msg.getX() < 830){
                                msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+1]), 
                                    msg.getY(), height, width);
                                p.setLocation(msg.getX(), msg.getY());
                                arqP.add(p);
                            }
                            else if(msg.getX() >= 830){
                                if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                            }
                        }
                        else if (path.get(track) == 3){
                            //go down to right
                            if(msg.getY() < 550){
                                msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                                p.setLocation(msg.getX(), msg.getY());
                                arqP.add(p);
                            }
                            else if(msg.getY() >= 550){
                                if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                            }
                        }
                        else if(path.get(track) == 4){
                            //go up and to the right
                            if(msg.getY() < 550){
                                msg.setFrame(msg.getX()+(37-2*wgt_cpy[track+2]),
                                    msg.getY()-(42-2*wgt_cpy[track+2]), height, width);
                                p.setLocation(msg.getX(), msg.getY());
                                arqP.add(p);
                            }
                            else if(msg.getY() >= 550){
                                if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                            }
                        }
                        else if(path.get(track) == 7){
                            if(msg.getX() < 830){
                                msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+1]), 
                                    msg.getY(), height, width);
                                p.setLocation(msg.getX(), msg.getY());
                                arqP.add(p);
                            }
                            else if(msg.getX() >= 830){
                                if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                            }
                        }
                }
                else if(track == 2){
                    d.setSize(6, 6);
                    if(path.get(track) == 6){
                        //move to the right
                        if(msg.getX() < 750){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+1]), 
                                    msg.getY() - 2, height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 750){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    else if(path.get(track) == 3){
                        //move up and to the left
                        if(msg.getX() > 400){
                            msg.setFrame(msg.getX()-(27-2*wgt_cpy[track+2]),
                                    msg.getY()-(42-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() <= 400){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    else if(path.get(track) == 4){
                        //move down and to the left
                        if(msg.getY() < 700){
                            msg.setFrame(msg.getX()-(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getY() >= 700){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    else if(path.get(track) == 8){
                        //move down and to the left
                        if(msg.getY() < 530){
                            msg.setFrame(msg.getX()-(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getY() >= 530){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    else if(path.get(track) == 9){
                        if(msg.getY() > 550){
                            msg.setFrame(msg.getX()-(23-2*wgt_cpy[track+2]),
                                    msg.getY()-(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getY() <= 550){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    else if(path.get(track) == 10){
                        //move down to the right
                        if(msg.getX() < 900){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 900){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    msg.setFrame(1220, 530, height, width);
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track = 7;
                                }
                        }
                    }
                    else if(path.get(track) == 11){
                        //move up and to the right
                        if(msg.getX() < 950){
                            msg.setFrame(msg.getX()+(37-2*wgt_cpy[track+2]),
                                    msg.getY()-(42-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 950){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    msg.setFrame(1220, 530, height, width);
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track = 7;
                                }
                        }
                    }
                }
                else if (track == 3){
                    d.setSize(6, 6);
                    if(path.get(track) == 5){
                        if(msg.getX() < 830){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+1]), 
                                    msg.getY(), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 830){
                                if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                            }
                        }
                    if(path.get(track) == 7){
                        if(msg.getX() < 850){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+1]), 
                                    msg.getY(), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 850){
                                if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                            }
                        }
                    if(path.get(track) == 8){
                        if(msg.getX() < 730 || msg.getY() > 400){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+2]),
                                    msg.getY()-(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 730 || msg.getY() > 400){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    if(path.get(track) == 9){
                        if(msg.getX() < 850){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 850){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                }
                else if(track == 4){
                    d.setSize(6, 6);
                    if(path.get(track) == 8){
                        if(msg.getX() > 730){
                            msg.setFrame(msg.getX()-(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() <= 730){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    // work on this
                    else if(path.get(track) == 9){
                        if(msg.getX() > 730){
                            msg.setFrame(msg.getX()-(27-2*wgt_cpy[track+2]),
                                    msg.getY()-(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() <= 730){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    else if(path.get(track) == 10){
                        //down and to the right
                        if(msg.getX() < 950){
                            msg.setFrame(msg.getX()+(32-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 950){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    msg.setFrame(1220, 530, height, width);
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track = 7;
                                }
                        }
                    }
                    else if(path.get(track) == 11){
                        //up and to the right
                        if(msg.getX() < 950){
                            msg.setFrame(msg.getX()+(37-2*wgt_cpy[track+2]),
                                    msg.getY()-(42-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 950){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    msg.setFrame(1220, 530, height, width);
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track = 7;
                                }
                        }
                    }
                }
                else if(track == 5){
                    d.setSize(6, 6);
                    //WORK ON THIS
                    if(path.get(track) == 8){
                        //move up to the right
                        if(msg.getX() < 850){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+2]),
                                    msg.getY()-(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 850){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                    else if(path.get(track) == 9){
                        //move down to the right
                        if(msg.getX() < 850){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 850){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track++;
                                }
                        }
                    }
                }
                else if(track == 6){
                    d.setSize(6, 6);
                    if(path.get(track) == 10){
                        if(msg.getX() < 950){
                            msg.setFrame(msg.getX()+(27-2*wgt_cpy[track+2]),
                                    msg.getY()+(37-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 950){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    msg.setFrame(1220, 530, height, width);
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track = 7;
                                }
                        }
                    }
                    else if(path.get(track) == 11){
                        if(msg.getX() < 950){
                            msg.setFrame(msg.getX()+(37-2*wgt_cpy[track+2]),
                                    msg.getY()-(42-2*wgt_cpy[track+2]), height, width);
                            p.setLocation(msg.getX(), msg.getY());
                            arqP.add(p);
                        }
                        else if(msg.getX() >= 950){
                            if(!arqP.isEmpty()){
                                    arq.setFrame(arqP.get(arqP.size() - 1), d);
                                    arqP.remove(arqP.size()-1); 
                                }
                                else{
                                    msg.setFrame(1220, 530, height, width);
                                    arqP.clear();
                                    d.setSize(0, 0);
                                    arq.setFrame(p, d);
                                    track = 7;
                                }
                        }
                    }
                }
                else if(track == 7){
                    //work on this
                    if(msg.getY() >= 260){
                        msg.setFrame(msg.getX(), msg.getY() - 32, height, width);
                        if(msg.getY() < 420){
                            ec = checkSum();
                        }
                    }
                    else if(msg.getY() <= 260){
                        arrived = true;
                        msg.setFrame(0, 0, 0, 0);
                    }
                }
        }
        };
        
        final Timer t = new Timer(delay, taskPerformer);
        if(an){
            t.start();
        }
}
       
    
    public void render(Graphics2D g2) {
        if(phys_head == 2){
                g2.setColor(Color.red);
                g2.fill(msg);
                g2.fill(arq);
            }
        else if(phys_head == 3){
                g2.setColor(Color.blue);
                g2.fill(msg);
                g2.fill(arq);
            }
        else if (phys_head == 0){
            g2.setColor(Color.orange);
            g2.fill(msg);
            g2.fill(arq);
        }
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);
        render(g2);
    }
    
    public void setInput(String s){
        input = s;
    }
    
    public void setPhys(int i){
        phys_head = i;
    }
    
    public void setBin(StringBuilder s){
        bin = s;
        bin_copy = new StringBuilder(s.substring(0, s.length()));
    }
    
    public boolean checkSum (){
        rem = BD.getRemainder(bin_copy.toString(), "1011");
        if(crc.matches(rem)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void dijkstra(int[] s){
        System.arraycopy(s, 0, wgt_cpy, 0, 11);
        if(s[0] <= s[1]){
            path.add(1);
            if(s[4] <= s[2]){
                path.add(5);
                if(s[9] <= s[7]){
                    path.add(10);
                }
                else{
                    path.add(8);
                    path.add(9);
                    path.add(11);
                }
            }
            else if(s[2] < s[4]){
                path.add(3);
                if(s[5] <= s[3]){
                    path.add(6);
                    if(s[7] <= s[8]){
                        path.add(8);
                        path.add(10);
                    }
                    else{
                        path.add(9);
                        path.add(11);
                    }
                }
                else{
                    path.add(4);
                    path.add(7);
                    if(s[10] <= s[8]){
                        path.add(11);
                    }
                    else{
                        path.add(9);
                        path.add(8);
                        path.add(10);
                    }
                }
            }
        }
        else{
            path.add(2);
            if(s[3] <= s[6]){
                path.add(4);
                if(s[2]<=s[5]){
                    path.add(3);
                    path.add(5);
                    if(s[9] <= s[7]){
                        path.add(10);
                    }
                    else{
                        path.add(8);
                        path.add(9);
                        path.add(11);
                    }
                }
                else{
                    path.add(6);
                    if(s[7] <= s[8]){
                        path.add(8);
                        path.add(10);
                    }
                    else{
                        path.add(9);
                        path.add(11);
                    }
                }
            }
            else{
                path.add(7);
                if(s[8] <= s[10]){
                    path.add(9);
                    path.add(8);
                    path.add(10);
                }
                else{
                    path.add(11);
                }
            }
        }
        for(int j = 0; j < path.size(); j++){
            System.out.print(path.get(j) + " ");
        }
    }
    
    public boolean getSend(int i){
        return canSend[i];
    }
    
    public void setSend(boolean[] b){
        System.arraycopy(b, 0, canSend, 0, b.length);
    }
}
    
    
    

