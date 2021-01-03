package main.gui;

import main.parking_lot.carpark;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<carpark> car = new ArrayList<carpark>();
    public static HashMap<carpark, Boolean> hm = new HashMap<carpark, Boolean>();

    private JFrame frmCarParking;
    private JTextField tfName;
    private JTextField tfcellNmbr;
    private JTextField tfcarNmbr;
    private JTextField tfplcNmbr;
    private JTextField tfDuration;
    private JTextField tfDate;
    private JTextField tfTime;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    //private String carnmbr, name, cell;


    //launch the application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frmCarParking.paint(null);
                    window.frmCarParking.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //to create the application
    public Main() {
        initialize();
    }

    //initializing the contents of the frame
    private void initialize() {
        frmCarParking = new JFrame();
        frmCarParking.getContentPane().setBackground(Color.WHITE);
        frmCarParking.setTitle("PROJECT");
        frmCarParking.setBounds(100, 100, 761, 442);
        frmCarParking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCarParking.getContentPane().setLayout(null);

        JLabel label = new JLabel("CAR PARKING MANAGEMENT SYSTEM");
        label.setForeground(new Color(128, 0, 0));
        label.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 27));
        label.setBounds(0, 0, 752, 44);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        frmCarParking.getContentPane().add(label);

        JLabel labelus = new JLabel("Programmed by B��ra DEDEO�LU and Yi�it EGEMEN");
        labelus.setFont(new Font("Sitka Text", Font.ITALIC, 20));
        labelus.setForeground(Color.BLACK);
        labelus.setBounds(122, 40, 740, 40);
        frmCarParking.getContentPane().add(labelus);

        JLabel lblNewLabel_1 = new JLabel("THANK YOU AND DRIVE SAFELY!");
        lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 20));
        lblNewLabel_1.setForeground(SystemColor.text);
        lblNewLabel_1.setBounds(386, 370, 360, 30);
        frmCarParking.getContentPane().add(lblNewLabel_1);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.activeCaption);
        panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, new Color(192, 192, 192), Color.LIGHT_GRAY));
        panel.setBounds(0, 79, 352, 324);
        frmCarParking.getContentPane().add(panel);
        panel.setLayout(null);


        //all the parking slots by using checkboxes
        final JCheckBox A_1 = new JCheckBox("A1");
        A_1.setBackground(Color.GREEN);
        A_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == A_1) {
                    A_1.setBackground(Color.RED);
                    String s = "A1";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(A_1);
        A_1.setBounds(6, 7, 50, 80);
        panel.add(A_1);

        final JCheckBox A_2 = new JCheckBox("A2");
        A_2.setBackground(Color.GREEN);
        A_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == A_2) {
                    A_2.setBackground(Color.RED);
                    String s = "A2";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(A_2);
        A_2.setBounds(64, 7, 50, 80);
        panel.add(A_2);

        final JCheckBox A_3 = new JCheckBox("A3");
        A_3.setBackground(Color.GREEN);
        A_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == A_3) {
                    A_3.setBackground(Color.RED);
                    String s = "A3";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(A_3);
        A_3.setBounds(122, 7, 50, 80);
        panel.add(A_3);

        final JCheckBox A_4 = new JCheckBox("A4");
        A_4.setBackground(Color.GREEN);
        A_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == A_4) {
                    A_4.setBackground(Color.RED);
                    String s = "A4";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(A_4);
        A_4.setBounds(180, 7, 50, 80);
        panel.add(A_4);

        final JCheckBox A_5 = new JCheckBox("A5");
        A_5.setBackground(Color.GREEN);
        A_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == A_5) {
                    A_5.setBackground(Color.RED);
                    String s = "A5";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(A_5);
        A_5.setBounds(238, 7, 50, 80);
        panel.add(A_5);

        final JCheckBox priv_a = new JCheckBox("DP");
        priv_a.setBackground(Color.GREEN);
        priv_a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == priv_a) {
                    priv_a.setBackground(Color.RED);
                    String s = "D-A";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(priv_a);
        priv_a.setBounds(296, 7, 50, 80);
        panel.add(priv_a);

        final JCheckBox B_1 = new JCheckBox("B1");
        B_1.setBackground(Color.GREEN);
        B_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == B_1) {
                    B_1.setBackground(Color.RED);
                    String s = "B1";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(B_1);
        B_1.setBounds(6, 111, 50, 80);
        panel.add(B_1);

        final JCheckBox B_2 = new JCheckBox("B2");
        B_2.setBackground(Color.GREEN);
        B_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == B_2) {
                    B_2.setBackground(Color.RED);
                    String s = "B2";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(B_2);
        B_2.setBounds(64, 111, 50, 80);
        panel.add(B_2);

        final JCheckBox B_3 = new JCheckBox("B3");
        B_3.setBackground(Color.GREEN);
        B_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == B_3) {
                    B_3.setBackground(Color.RED);
                    String s = "B3";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(B_3);
        B_3.setBounds(122, 111, 50, 80);
        panel.add(B_3);

        final JCheckBox B_4 = new JCheckBox("B4");
        B_4.setBackground(Color.GREEN);
        B_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == B_4) {
                    B_4.setBackground(Color.RED);
                    String s = "B4";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(B_4);
        B_4.setBounds(180, 111, 50, 80);
        panel.add(B_4);

        final JCheckBox B_5 = new JCheckBox("B5");
        B_5.setBackground(Color.GREEN);
        B_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == B_5) {
                    B_5.setBackground(Color.RED);
                    String s = "B5";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(B_5);
        B_5.setBounds(238, 111, 50, 80);
        panel.add(B_5);

        final JCheckBox priv_b = new JCheckBox("DP");
        priv_b.setBackground(Color.GREEN);
        priv_b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == priv_b) {
                    priv_b.setBackground(Color.RED);
                    String s = "D-B";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(priv_b);
        priv_b.setBounds(296, 111, 50, 80);
        panel.add(priv_b);

        final JCheckBox C_1 = new JCheckBox("C1");
        C_1.setBackground(Color.GREEN);
        C_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == C_1) {
                    C_1.setBackground(Color.RED);
                    String s = "C1";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(C_1);
        C_1.setBounds(6, 215, 50, 80);
        panel.add(C_1);

        final JCheckBox C_2 = new JCheckBox("C2");
        C_2.setBackground(Color.GREEN);
        C_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == C_2) {
                    C_2.setBackground(Color.RED);
                    String s = "C2";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(C_2);
        C_2.setBounds(64, 215, 50, 80);
        panel.add(C_2);

        final JCheckBox C_3 = new JCheckBox("C3");
        C_3.setBackground(Color.GREEN);
        C_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == C_3) {
                    C_3.setBackground(Color.RED);
                    String s = "C3";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(C_3);
        C_3.setBounds(122, 215, 50, 80);
        panel.add(C_3);

        final JCheckBox C_4 = new JCheckBox("C4");
        C_4.setBackground(Color.GREEN);
        C_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == C_4) {
                    C_4.setBackground(Color.RED);
                    String s = "C4";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(C_4);
        C_4.setBounds(180, 215, 50, 80);
        panel.add(C_4);

        final JCheckBox C_5 = new JCheckBox("C5");
        C_5.setBackground(Color.GREEN);
        C_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == C_5) {
                    C_5.setBackground(Color.RED);
                    String s = "C5";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(C_5);
        C_5.setBounds(238, 215, 50, 80);
        panel.add(C_5);

        final JCheckBox priv_c = new JCheckBox("DP");
        priv_c.setBackground(Color.GREEN);
        priv_c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == priv_c) {
                    priv_c.setBackground(Color.RED);
                    String s = "D-C";
                    tfplcNmbr.setText(s);
                }
            }
        });
        buttonGroup.add(priv_c);
        priv_c.setBounds(296, 215, 50, 80);
        panel.add(priv_c);


        JTextPane pnCarNmbr = new JTextPane();
        pnCarNmbr.setBackground(Color.BLACK);
        pnCarNmbr.setForeground(SystemColor.text);
        pnCarNmbr.setFont(new Font("Sitka Text", Font.BOLD, 11));
        pnCarNmbr.setText("CAR NUMBER:");
        pnCarNmbr.setBounds(386, 87, 89, 20);
        frmCarParking.getContentPane().add(pnCarNmbr);

        tfcarNmbr = new JTextField();
        tfcarNmbr.setBackground(SystemColor.inactiveCaptionBorder);
        tfcarNmbr.setColumns(10);
        tfcarNmbr.setBounds(496, 87, 178, 20);
        frmCarParking.getContentPane().add(tfcarNmbr);

        JTextPane pnName = new JTextPane();
        pnName.setBackground(Color.BLACK);
        pnName.setForeground(Color.WHITE);
        pnName.setFont(new Font("Sitka Text", Font.BOLD, 11));
        pnName.setText("NAME:");
        pnName.setBounds(386, 115, 46, 20);
        frmCarParking.getContentPane().add(pnName);

        tfName = new JTextField();
        tfName.setBackground(SystemColor.inactiveCaptionBorder);
        tfName.setBounds(496, 115, 178, 20);
        frmCarParking.getContentPane().add(tfName);
        tfName.setColumns(10);

        JTextPane pnCellNmbr = new JTextPane();
        pnCellNmbr.setBackground(Color.BLACK);
        pnCellNmbr.setForeground(Color.WHITE);
        pnCellNmbr.setFont(new Font("Sitka Text", Font.BOLD, 11));
        pnCellNmbr.setText("CELL No:");
        pnCellNmbr.setBounds(386, 143, 61, 20);
        frmCarParking.getContentPane().add(pnCellNmbr);

        tfcellNmbr = new JTextField();
        tfcellNmbr.setBackground(SystemColor.inactiveCaptionBorder);
        tfcellNmbr.setColumns(10);
        tfcellNmbr.setBounds(496, 143, 178, 20);
        frmCarParking.getContentPane().add(tfcellNmbr);

        JTextPane pnPlcNmbr = new JTextPane();
        pnPlcNmbr.setForeground(Color.WHITE);
        pnPlcNmbr.setBackground(Color.BLACK);
        pnPlcNmbr.setFont(new Font("Sitka Text", Font.BOLD, 11));
        pnPlcNmbr.setText("SLOT:");
        pnPlcNmbr.setBounds(386, 174, 67, 20);
        frmCarParking.getContentPane().add(pnPlcNmbr);

        tfplcNmbr = new JTextField();
        tfplcNmbr.setBackground(SystemColor.inactiveCaptionBorder);
        tfplcNmbr.setColumns(10);
        tfplcNmbr.setBounds(496, 174, 67, 20);
        frmCarParking.getContentPane().add(tfplcNmbr);

        JTextPane pnTime = new JTextPane();
        pnTime.setBackground(Color.BLACK);
        pnTime.setForeground(Color.WHITE);
        pnTime.setFont(new Font("Sitka Text", Font.BOLD, 11));
        pnTime.setText("FROM:");
        pnTime.setBounds(386, 205, 77, 20);
        frmCarParking.getContentPane().add(pnTime);

        JTextPane pnEndTime = new JTextPane();
        pnEndTime.setForeground(Color.WHITE);
        pnEndTime.setBackground(Color.BLACK);
        pnEndTime.setFont(new Font("Sitka Text", Font.BOLD, 11));
        pnEndTime.setText("TO:");
        pnEndTime.setBounds(386, 235, 73, 20);
        frmCarParking.getContentPane().add(pnEndTime);

        JTextPane pnDate = new JTextPane();
        pnDate.setBackground(Color.BLACK);
        pnDate.setForeground(Color.WHITE);
        pnDate.setFont(new Font("Sitka Text", Font.BOLD, 11));
        pnDate.setText("DATE:");
        pnDate.setBounds(386, 266, 46, 20);
        frmCarParking.getContentPane().add(pnDate);

        JTextPane txtpnTotalCost = new JTextPane();
        txtpnTotalCost.setForeground(Color.WHITE);
        txtpnTotalCost.setBackground(Color.BLACK);
        txtpnTotalCost.setFont(new Font("Sitka Text", Font.BOLD, 11));
        txtpnTotalCost.setText("COST:");
        txtpnTotalCost.setBounds(386, 297, 53, 20);
        frmCarParking.getContentPane().add(txtpnTotalCost);

        JTextField txtTkPer = new JTextField();
        txtTkPer.setText("5$ per hour");
        txtTkPer.setBackground(SystemColor.inactiveCaptionBorder);
        txtTkPer.setColumns(10);
        txtTkPer.setBounds(496, 297, 100, 20);
        frmCarParking.getContentPane().add(txtTkPer);

        final JButton donebtn = new JButton("Done");
        donebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    FileWriter fw = new FileWriter("C:\\Users\\BD\\Desktop\\parking.txt");
                    fw.write("Name - Car ID - Slot - Phone Number\n");
                    BufferedWriter bw = new BufferedWriter(fw);
                    String carnumber = tfcarNmbr.getText();
                    String name = tfName.getText();
                    String cellnum = tfcellNmbr.getText();
                    String slot = tfplcNmbr.getText();
                    bw.write(name + " " + carnumber + " " + slot + " " + cellnum);
                    //fw.write(name + " " +carnumber+ " " +slot+ " "+cellnum);
                    bw.newLine();
                    bw.close();
                } catch (Exception e) {
                    System.out.println("Exception catched");
                }

                Object obj = arg0.getSource();

                if (obj == donebtn) {
                    carpark temp = new carpark(tfcarNmbr.getText(), tfName.getText(), tfcellNmbr.getText(), tfplcNmbr.getText(), tfTime.getText(), tfDuration.getText(), tfDate.getText());
                    if (hm.get(temp) == null) {
                        car.add(temp);
                        hm.put(temp, true);
                    }
                    tfcarNmbr.setText("");
                    tfName.setText("");
                    tfcellNmbr.setText("");
                    tfplcNmbr.setText("");
                    tfTime.setText("");
                    tfDuration.setText("");
                    tfDate.setText("");
                    try {
                        PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\BD\\Desktop\\parking1.txt")));
                        bw.print("Car Number - Name - Cell Number - Slot - From - To - Date\n");
                        for (main.parking_lot.carpark carpark : car) {
                            bw.println(carpark.carnum + "\t" + carpark.name + "\t" + carpark.cellno + "\t" + carpark.placeno + "\t" + carpark.startTime + "\t" + carpark.duration + "\t" + carpark.date);
                        }
                        bw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        donebtn.setFont(new Font("Sitka Text", Font.PLAIN, 11));
        donebtn.setBounds(535, 338, 89, 23);
        frmCarParking.getContentPane().add(donebtn);

        tfTime = new JTextField();
        tfTime.setBackground(SystemColor.inactiveCaptionBorder);
        tfTime.setForeground(SystemColor.desktop);
        tfTime.setBounds(496, 205, 106, 20);
        frmCarParking.getContentPane().add(tfTime);
        tfTime.setColumns(10);

        tfDuration = new JTextField();
        tfDuration.setBackground(SystemColor.inactiveCaptionBorder);
        tfDuration.setForeground(SystemColor.desktop);
        tfDuration.setBounds(496, 235, 85, 20);
        frmCarParking.getContentPane().add(tfDuration);
        tfDuration.setColumns(10);

        tfDate = new JTextField();
        tfDate.setBackground(SystemColor.inactiveCaptionBorder);
        tfDate.setBounds(496, 266, 106, 20);
        frmCarParking.getContentPane().add(tfDate);
        tfDate.setColumns(10);


        //For-starttime
        final JRadioButton rdtime1 = new JRadioButton("");
        rdtime1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == rdtime1) {
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    Date date = new Date();
                    String b = dateFormat.format(date);
                    tfTime.setText(b);
                }
            }
        });
        rdtime1.setBackground(SystemColor.activeCaptionText);
        rdtime1.setBounds(469, 205, 21, 23);
        frmCarParking.getContentPane().add(rdtime1);


        //For-endtime
        final JRadioButton rdtime2 = new JRadioButton("");
        rdtime2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == rdtime2) {
                    SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm");
                    Date date1 = new Date();
                    String time = dateformat.format(date1);
                    tfDuration.setText(time);
                }
            }
        });
        rdtime2.setBackground(SystemColor.activeCaptionText);
        rdtime2.setBounds(469, 235, 21, 23);
        frmCarParking.getContentPane().add(rdtime2);


        //For-date
        final JRadioButton rddate = new JRadioButton("");
        rddate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == rddate) {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String c = dateFormat.format(date);
                    tfDate.setText(c);
                }
            }
        });
        rddate.setBackground(SystemColor.activeCaptionText);
        rddate.setBounds(469, 263, 21, 23);
        frmCarParking.getContentPane().add(rddate);


        //for background
        JLabel labelback = new JLabel("");
        labelback.setIcon(new ImageIcon("C:\\Users\\BD\\Desktop\\parking.jpg"));
        labelback.setBounds(0, 79, 745, 324);
        frmCarParking.getContentPane().add(labelback);

    }
}