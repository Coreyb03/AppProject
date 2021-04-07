import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.util.ArrayList;

public class Tracker extends JFrame implements ActionListener
{
    public final static int ONE_SECOND = 1000;
    TimeZone timezone = TimeZone.getDefault();
    Calendar cal = Calendar.getInstance();

    public static void main(String[] args) throws Exception {

    
        
       

        EventQueue.invokeLater(() -> {
            Tracker tracker = new Tracker();
            tracker.setVisible(true);  
            
        });

    }

    public Tracker(){
        initUI();
    }

    private void createLayout(JComponent... args){
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        pane.setBackground(Color.orange);

        gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createParallelGroup().addComponent(args[0]).addComponent(args[1]).addComponent(args[2]).addComponent(args[3]).addComponent(args[5]).addComponent(args[4]));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(args[0]).addComponent(args[1]).addComponent(args[2]).addGroup(gl.createParallelGroup().addComponent(args[3])).addComponent(args[5]).addComponent(args[4]));
    }

    public void initUI() {
        
        

        ArrayList<String> comboList = new ArrayList<String>();
        comboList.add("HII");

        
        JLabel startDate = new JLabel("Current Date", SwingConstants.LEFT);
        JTextField startTextBox = new JTextField();
        
        startTextBox.setEditable(false);

        startDate.setFont(new Font("serif" , Font.BOLD, 20));
        startDate.setSize(100,100);
        startTextBox.setBackground(Color.green);
        startTextBox.setSize(100,100);
        startTextBox.setPreferredSize(startTextBox.getSize());
        updateText(startTextBox);
        startTextBox.setFont(new Font("Serif",Font.BOLD,20));

        JButton eventButton = new JButton("Set interval (int only and in seconds)");
        eventButton.setBackground(Color.magenta);
        eventButton.setSize(100, 10);
        

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBackground(Color.red);
       
        
        JTextField intervalField = new JTextField();
        intervalField.setEditable(false);
        intervalField.setBackground(Color.blue);

        Event trackedEvent = new Event();
        
        eventButton.addActionListener((event) -> intervalField.setEditable(!intervalField.isEditable()));
        
        
        progressBar.setMinimum(0);
        progressBar.setMaximum(trackedEvent.getInterval() * 2); //timer seems 2 times as fast

        JLabel progressBarTitle = new JLabel("Counting down to " + trackedEvent.getInterval(),SwingConstants.CENTER);
        progressBarTitle.setFont(new Font("Serif", Font.BOLD, 12));


        Timer timer = new Timer(ONE_SECOND, new ActionListener(){
            
            
            
            @Override
            public void actionPerformed(ActionEvent event) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        
                        cal = Calendar.getInstance();
                        startTextBox.setText(cal.getTime().toString());

                        progressBar.setValue(progressBar.getValue() + 1);

                        progressBarTitle.setText("Counting down to - " + trackedEvent.getInterval());
                        startTextBox.revalidate();
                        startTextBox.repaint();
                    }
                });
            }
        });
        eventButton.addActionListener((Event) -> enterEvent(intervalField,eventButton,trackedEvent,progressBar,timer));
        ActionListener[] task = timer.getActionListeners();
        timer.start();
        timer.setRepeats(true);
        timer.addActionListener((task[0]));

        setTitle("Tracker");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        
        createLayout(startDate,startTextBox,eventButton,intervalField,progressBar,progressBarTitle
        );

    }


    public void updateText(JTextField field){
        field.setText(cal.getTime().toString());
    }

    public static void enterEvent(JTextField field, JButton button, Event trackedEvent, JProgressBar progressBar, Timer timer){
        if(field.isEditable()){
            button.addActionListener((event) -> trackedEvent.setInterval(Integer.parseInt(field.getText())));            
            progressBar.setValue(0);
            field.setBackground(Color.BLUE);
            progressBar.setMaximum(trackedEvent.getInterval() * 3);
        } else {
            // field.setText("What is the Interval? (In Days)");
            // button.setText(Integer.toString(trackedEvent.getInterval()));
            button.addActionListener((event) -> trackedEvent.setInterval(Integer.parseInt(field.getText())));            
            field.setBackground(Color.cyan);

        }
    }

}
