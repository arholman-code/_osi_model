/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _osi_;

import java.util.ArrayList;

/**
 *
 * @author arholman
 */
public interface Messages {
    ArrayList<Message> msgs = new ArrayList();
    
    public void addMsg(Message m);
    public Message getMsg(int i);
    
}
