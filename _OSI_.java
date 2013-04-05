package _osi_;


import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import javax.swing.*;
/**
 *
 * @author Andrew Holman
 * CSC 567 - Spring 2013
 */
public class _OSI_ extends JFrame implements Messages, ActionListener{
    
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 800;
    Point p = new Point(70, 350);
    Point q = new Point(70, 700);
    public int port = 0;
    int[] route = new int[11];
    boolean[] canSend = new boolean[11];
    BinaryDivision BD = new BinaryDivision();
    
    private JButton startB, exitB, rB;
    private JLabel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    private JLabel m1frame, m2frame, m1crc, m2crc, crc1, crc2, IO, instruct;
    private JTextField msgIn;
    private JRadioButton port2, port3;
    private ButtonGroup group = new ButtonGroup();
    
    SineWave sw1 = new SineWave(30, 10, 50);
    SineWave sw2 = new SineWave(30, 10, 10);
    
    ImageIcon OSI_L = new ImageIcon("OSI.png");
    ImageIcon OSI_R = new ImageIcon("OSI.png");
    ImageIcon net = new ImageIcon("ass3png.jpg");
    
    private _OSI_.StartButtonHandler sbHandler;
    private _OSI_.ExitButtonHandler ebHandler;
    private _OSI_.rbButtonHandler rbHandler;
    private _OSI_.port2ButtonHandler p2Handler;
    private _OSI_.port3ButtonHandler p3Handler;
    
    public _OSI_(){
        String s;
        int ind = 0;
        
        m1frame = new JLabel("M1: ", SwingConstants.LEFT);
        crc1 = new JLabel("CRC-1: ", SwingConstants.LEFT);
        m2frame = new JLabel("M2: ", SwingConstants.LEFT);
        crc2 = new JLabel("CRC-2:", SwingConstants.LEFT);
        m1crc = new JLabel ("Message 1 CRC: ", SwingConstants.LEFT);
        m2crc = new JLabel ("Message 2 CRC: ", SwingConstants.LEFT);
        IO = new JLabel("", SwingConstants.LEFT);
        instruct = new JLabel("Type a message, then click send."
                + "  Mouse over the message to see status.", SwingConstants.LEFT);
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p1 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p1.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p2 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p2.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p3 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p3.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p4 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p4.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p5 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p5.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p6 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p6.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p7 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p7.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p8 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p8.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p9 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p9.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p10 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p10.getText());
        s = Integer.toString(1 + (int)(Math.random() * ((7 - 1) + 1)));
        p11 = new JLabel(s, SwingConstants.LEFT);
        route[ind++] = Integer.parseInt(p11.getText());
        
        
        startB = new JButton("Send Message.");
        startB.setSize(20, 20);
        sbHandler = new _OSI_.StartButtonHandler();
        startB.addActionListener(sbHandler);
        
        rB = new JButton("Randomize weights");
        rB.setSize(20, 20);
        rbHandler = new _OSI_.rbButtonHandler();
        rB.addActionListener(rbHandler);
        
        port2 = new JRadioButton("Send to Host 2");
        port2.addActionListener(p2Handler);
        port3 = new JRadioButton("Send to Host 3");
        port3.addActionListener(p3Handler);
        group.add(port2);
        group.add(port3);
        
        msgIn = new JTextField(30);
        
        exitB = new JButton("X");
        ebHandler = new _OSI_.ExitButtonHandler();
        exitB.addActionListener(ebHandler);
        
        sw1.setSize(300, 100);
        sw2.setSize(300, 100);
        
        JLabel osil = new JLabel (OSI_L);
        JLabel osir = new JLabel (OSI_R);
        JLabel netL = new JLabel(net);
        
        
        setTitle("OSI Seven Layer Model");
        SpringLayout nlayout = new SpringLayout();
        Container npane = getContentPane();
        npane.setLayout(nlayout);
        
        npane.add(osil);
        npane.add(osir);
        
        npane.add(msgIn);
        npane.add(port2);
        npane.add(port3);
        npane.add(p1);
        npane.add(p2);
        npane.add(p3);
        npane.add(p4);
        npane.add(p5);
        npane.add(p6);
        npane.add(p7);
        npane.add(p8);
        npane.add(p9);
        npane.add(p10);
        npane.add(p11);
        
        npane.add(instruct);
        npane.add(m1frame);
        npane.add(m2frame);
        npane.add(m1crc);
        npane.add(crc1);
        npane.add(m2crc);
        npane.add(crc2);
        npane.add(sw1);
        npane.add(sw2);
        npane.add(startB);
        npane.add(exitB);
        npane.add(rB);
        npane.add(netL);
        //Button placement
        nlayout.putConstraint(SpringLayout.WEST, startB, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, startB, 10, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, exitB, 1200, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, exitB, 10, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, rB, 200, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, rB, 10, SpringLayout.NORTH, npane);
        //Label placement
        nlayout.putConstraint(SpringLayout.WEST, netL, 125, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, netL, 240, SpringLayout.NORTH, npane);
        //PNG-JPG placement
        nlayout.putConstraint(SpringLayout.WEST, osil, 100, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, osil, 200, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, osir, 1000, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, osir, 200, SpringLayout.NORTH, npane);
        //Input area placement
        nlayout.putConstraint(SpringLayout.WEST, msgIn, 380, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, msgIn, 10, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, instruct, 400, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, instruct, 40, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, port2, 450, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, port2, 60, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, port3, 650, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, port3, 60, SpringLayout.NORTH, npane);
        //Message display (sine wave, crc calc, etc.
        nlayout.putConstraint(SpringLayout.WEST, m1frame, 100, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, m1frame, 50, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, sw1, 100, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, sw1, 100, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, m1crc, 100, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, m1crc, 150, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, crc1, 100, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, crc1, 170, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, m2frame, 950, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, m2frame, 50, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, sw2, 950, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, sw2, 100, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, m2crc, 950, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, m2crc, 150, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, crc2, 950, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, crc2, 170, SpringLayout.NORTH, npane);
        //Randomized values placement, for Dijkstra's calculation
        nlayout.putConstraint(SpringLayout.WEST, p1, 330, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p1, 430, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p3, 460, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p3, 430, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p8, 800, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p8, 430, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p10, 930, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p10, 430, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p2, 330, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p2, 615, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p4, 465, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p4, 615, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p9, 800, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p9, 615, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p11, 930, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p11, 615, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p7, 600, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p7, 700, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p6, 600, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p6, 500, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, p5, 600, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, p5, 350, SpringLayout.NORTH, npane);
        //Set main JFrame size, close operation, visibility
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int delay = 1000;
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Graphics g = getGraphics();
                _OSI_.super.paintComponents(g);
                for(int i = 0; i < msgs.size(); i++){
                    for(int j = 0; j < 11; j++){
                        canSend[j] = msgs.get(i).getSend(j);
                    }
                    msgs.get(i).paint(g);
                    if(msgs.get(i).phys_head == 2){
                        int amp = 0, xloc = 100, yloc = 150, len = 50;
                        if(msgs.get(i).lvl < 6){
                            m1frame.setText(msgs.get(i).frame[msgs.get(i).lvl]);
                            msgs.get(i).lvl++;
                        }
                        else{
                            m1frame.setText("Preamble:0"+msgs.get(i).phys_head+":01:'"
                                + msgs.get(i).input + "' + Padding:1011"); 
                        }
                        for(int x = 0; x < 4; x++){
                            SineWave sw1 = new SineWave(30, amp, 0);
                            if(msgs.get(i).bin.length() == 0){
                                break;
                            }
                            if(msgs.get(i).bin.charAt(0) == '1'){
                                sw1.amplitude = 10;
                                g.setColor(Color.red);
                                msgs.get(i).bin.deleteCharAt(0);
                                sw1.draw(g, xloc, yloc, len);
                                xloc += len;
                            }
                            else if(msgs.get(i).bin.charAt(0) == '0'){
                                sw1.amplitude = 0;
                                g.setColor(Color.red);
                                msgs.get(i).bin.deleteCharAt(0);
                                sw1.draw(g, xloc, yloc, len);
                                xloc += len;
                            }
                        }
                        if(msgs.get(i).ec == true){
                            crc1.setText("CRC1 : " + msgs.get(i).ec + " Syndrome = 000");
                        }
                        msgs.get(i).setSend(canSend);
                        if(msgs.get(i).arrived == true){
                            msgs.get(i).msg.setFrame(0, 0, 0, 0);
                            msgs.remove(i);
                        }
                        
                    }
                    else if(msgs.get(i).phys_head == 3){
                        int amp = 0, xloc = 950, yloc = 150, len = 50;
                        if(msgs.get(i).lvl < 6){
                            m2frame.setText(msgs.get(i).frame[msgs.get(i).lvl]);
                            msgs.get(i).lvl++;
                        }
                        else{
                            m2frame.setText("Preamble:0"+msgs.get(i).phys_head+":01:'"
                                + msgs.get(i).input + "' + Padding:1011");
                        }
                        for(int x = 0; x < 4; x++){
                            SineWave sw2 = new SineWave(30, amp, 0);
                            if(msgs.get(i).bin.length() == 0){
                                break;
                            }
                            if(msgs.get(i).bin.charAt(0) == '1'){
                                sw2.amplitude = 10;
                                g.setColor(Color.blue);
                                msgs.get(i).bin.deleteCharAt(0);
                                sw2.draw(g, xloc, yloc, len);
                                xloc += len;
                            }
                            else if(msgs.get(i).bin.charAt(0) == '0'){
                                sw2.amplitude = 0;
                                g.setColor(Color.blue);
                                msgs.get(i).bin.deleteCharAt(0);
                                sw2.draw(g, xloc, yloc, len);
                                xloc += len;
                            }
                            
                        }
                        if(msgs.get(i).ec == true){
                            crc2.setText("CRC2 : " + msgs.get(i).ec + " Syndrome = 000");
                        }
                        msgs.get(i).setSend(canSend);
                        if(msgs.get(i).arrived == true){
                            msgs.get(i).msg.setFrame(0, 0, 0, 0);
                            msgs.remove(i);
                        }
                    }
                }
            }
        };
        final Timer t = new Timer(delay, taskPerformer);
        t.start();
    }

    @Override
    public void addMsg(Message m) {
        msgs.add(m);
    }

    @Override
    public Message getMsg(int i) {
        return msgs.get(i);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        super.repaint();
    }

    public class StartButtonHandler implements ActionListener
	{
            @Override
		public void actionPerformed(ActionEvent e)
		{
                    if(port2.isSelected()){
                        Graphics g = getGraphics();
                        int cycle = 0;
                        String text = msgIn.getText();
                        Message ms = new Message();
                        ms.setSend(canSend);
                        ms.dijkstra(route);
                        ms.setInput(text);
                        BigInteger crc = new BigInteger("1011");
                        if(port2.isSelected()){
                            ms.setPhys(2);
                        }
                        if(port3.isSelected()){
                            ms.setPhys(3);
                        }
                        addMsg(ms);
                        byte[] bytes = text.getBytes();
                        StringBuilder binary = new StringBuilder();
                        StringBuilder crcDiv = new StringBuilder();

                        for (byte b : bytes){
                            int val = b;
                            for (int i = 0; i < 8; i++){
                                binary.append((val & 128) == 0 ? 0 : 1);
                                crcDiv.append((val & 128) == 0 ? 0 : 1);
                                val <<= 1;
                                cycle++;
                            }
                            binary.append("");
                        }
                        crcDiv.append("000");
                        msgIn.setText("");
                        IO.setText(binary.toString());
                        ms.setBin(binary);
                        ms.bin_copy = crcDiv;

                        BigInteger ge = new BigInteger(crcDiv.toString());
                        System.out.println(text + " to binary: " + binary);
                        System.out.println(text + " to binary: " + ge + " "+ crc
                                + " ");

                        group.clearSelection();
                        String s = BD.getRemainder(ge.toString(),crc.toString());
                        System.out.println(s);
                        ms.crc = s;
                        if(ms.phys_head == 2){
                            m1crc.setText(ms.crc);
                        }
                        else if(ms.phys_head == 3){
                            m2crc.setText(ms.crc);
                        }
                        ms.paint(g);
                    }
                    else if(port3.isSelected()){
                        Graphics g = getGraphics();
                        int cycle = 0;
                        String text = msgIn.getText();
                        Message ms = new Message();
                        ms.setSend(canSend);
                        ms.dijkstra(route);
                        BigInteger crc = new BigInteger("1011");
                        if(port2.isSelected()){
                            ms.setPhys(2);
                        }
                        if(port3.isSelected()){
                            ms.setPhys(3);
                        }
                        ms.input = text;
                        addMsg(ms);
                        byte[] bytes = text.getBytes();
                        StringBuilder binary = new StringBuilder();
                        StringBuilder crcDiv = new StringBuilder();

                        for (byte b : bytes){
                            int val = b;
                            for (int i = 0; i < 8; i++){
                                binary.append((val & 128) == 0 ? 0 : 1);
                                crcDiv.append((val & 128) == 0 ? 0 : 1);
                                val <<= 1;
                                cycle++;
                            }
                            binary.append("");
                        }
                        crcDiv.append("000");
                        msgIn.setText("");
                        IO.setText(binary.toString());
                        ms.setBin(binary);
                        ms.bin_copy = crcDiv;

                        BigInteger ge = new BigInteger(crcDiv.toString());
                        System.out.println(text + " to binary: " + binary);
                        System.out.println(text + " to binary: " + ge + " "+ crc
                                + " ");

                        group.clearSelection();


                        String s = BD.getRemainder(ge.toString(),crc.toString());
                        System.out.println(s);
                        ms.crc = s;
                        if(ms.phys_head == 2){
                            m1crc.setText(ms.crc);
                        }
                        else if(ms.phys_head == 3){
                            m2crc.setText(ms.crc);
                        }
                        ms.paint(g);
                    }
                    else if(port2.isSelected() == false && port3.isSelected() == false){
                        Graphics g = getGraphics();
                        Message ms = new Message();
                        msgIn.setText("");
                        ms.setPhys(0);
                        addMsg(ms);
                        group.clearSelection();
                        ms.paint(g);
                    }
                    else{
                        String s = "Message not sent.  Currently sending"
                                + " packets to that router.  Wait for ACK"
                                + " to return and then retry.";
                        JOptionPane.showMessageDialog( null, s,
                        "Message State",JOptionPane.OK_CANCEL_OPTION);
                    }
                    
		}
            
	}
    
    public class ExitButtonHandler implements ActionListener
	{
            @Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
    
    public class rbButtonHandler implements ActionListener
	{
            @Override
		public void actionPerformed(ActionEvent e)
		{
                    String s;
                    int ind = 0;
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p1.setText(s);
                    route[ind++] = Integer.parseInt(p1.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p2.setText(s);
                    route[ind++] = Integer.parseInt(p2.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p3.setText(s);
                    route[ind++] = Integer.parseInt(p3.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p4.setText(s);
                    route[ind++] = Integer.parseInt(p4.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p5.setText(s);
                    route[ind++] = Integer.parseInt(p5.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p6.setText(s);
                    route[ind++] = Integer.parseInt(p6.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p7.setText(s);
                    route[ind++] = Integer.parseInt(p7.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p8.setText(s);
                    route[ind++] = Integer.parseInt(p8.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p9.setText(s);
                    route[ind++] = Integer.parseInt(p9.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p10.setText(s);
                    route[ind++] = Integer.parseInt(p10.getText());
                    s = Integer.toString(1 + (int) (Math.random() * ((7 - 1) + 1)));
                    p11.setText(s);
                    route[ind++] = Integer.parseInt(p11.getText());
		}
	}
    
    public class port2ButtonHandler implements ActionListener
	{
            @Override
		public void actionPerformed(ActionEvent e)
		{
			port = 2;
		}
	}
    
    public class port3ButtonHandler implements ActionListener
	{
            @Override
		public void actionPerformed(ActionEvent e)
		{
			port = 3;
		}
	}
    
    public static void main(String[] args) {
        _OSI_ test = new _OSI_();
    }
}
