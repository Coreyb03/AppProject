import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Tracker extends JFrame implements ActionListener
{
    public final static int ONE_SECOND = 1000;
    TimeZone timezone = TimeZone.getDefault();
    Calendar cal = Calendar.getInstance();

    public static void main(String[] args) throws FileNotFoundException {
        
        EventQueue.invokeLater(() -> {
            Tracker tracker;
            try {
                tracker = new Tracker();
                tracker.setVisible(true);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
             
            
        });
        
    }
    
    public Tracker()throws FileNotFoundException{
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

    public void initUI() throws FileNotFoundException {
        File file = new File("data.csv");
        
        Random random = new Random();
        
        Scanner scan = new Scanner(file);
        String[] parameters = new String[2];
        
        while( scan.hasNextLine()) {
            parameters = scan.nextLine().split(",");
            
        }
        scan.close();
        
        // if erroring check data file!!! it should look like ----- 12,12
        
        PrintWriter output = new PrintWriter(file);
        
        JLabel startDate = new JLabel("Current time", SwingConstants.LEFT);
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
        trackedEvent.setInterval(Integer.parseInt(parameters[0]));
        eventButton.addActionListener((event) -> intervalField.setEditable(!intervalField.isEditable()));
        
    
        progressBar.setMinimum(0);
        progressBar.setValue(Integer.parseInt(parameters[1]));
        progressBar.setMaximum(trackedEvent.getInterval() * 2); //timer seems 2 times as fast

        JLabel progressBarTitle = new JLabel("Counting down to - " + trackedEvent.getInterval(),SwingConstants.CENTER);
        progressBarTitle.setFont(new Font("Serif", Font.BOLD, 12));

        // PrintWriter output = new PrintWriter(file);
       

        Timer timer = new Timer(ONE_SECOND, new ActionListener() {
            
            
            
            @Override
            public void actionPerformed(ActionEvent event)  {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run(){
                        // uncomment for a light show!
                        // int randInt = random.nextInt(10); 
                        // updateBackgroud(randInt, startTextBox);


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

        timer.addActionListener((Event) -> {
            try {
                outputUpdate(output, progressBar, trackedEvent, file);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        setTitle("Tracker");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       
       
        output.close();
        
        
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
            button.addActionListener((event) -> trackedEvent.setInterval(Integer.parseInt(field.getText( ))));            
            field.setBackground(Color.cyan);

        }
    }

    public static void outputUpdate(PrintWriter output, JProgressBar progressBar, Event event, File file) throws FileNotFoundException{
        output = new PrintWriter(file);
        output.println(event.getInterval() + "," + progressBar.getValue());
        output.close();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // 
           
        
    }

    public void updateBackgroud(int i, JTextField field){
        if (i == 0){
            field.setBackground(Color.BLACK);
        } else if (i == 1){
            field.setBackground(Color.BLUE);
        } else if (i == 2){
            field.setBackground(Color.red);
        } else if (i == 3){
            field.setBackground(Color.green);
        } else if (i == 5){
            field.setBackground(Color.yellow);
        } else if (i == 6){
            field.setBackground(Color.white);
        } else if (i == 7){
            field.setBackground(Color.pink);
        } else if (i == 8){
            field.setBackground(Color.gray);
        } else if (i == 9){
            field.setBackground(Color.magenta);
        }
    }
}
