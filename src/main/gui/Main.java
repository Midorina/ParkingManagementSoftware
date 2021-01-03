package main.gui;

import main.db.SQLite;
import main.parking_lot.ParkedVehicle;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
// test
public class Main {
    public ArrayList<ParkedVehicle> parkedCars = new ArrayList<ParkedVehicle>();
    public ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();

    private JFrame frmCarParking;
    private JTextField tfcarNmbr;
    private JTextField tfplcNmbr;
    private JTextField tfDuration;
    private JTextField tfDate;
    private JTextField tfTime;

    private SQLite db;

    private final String[] slotNames = {
            "A1", "A2", "A3", "A4", "A5", "A-D",
            "B1", "B2", "B3", "B4", "B5", "B-D",
            "C1", "C2", "C3", "C4", "C5", "C-D",
    };

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
        // file stuff
        try {
            db = new SQLite("parking_lot.db");
        } catch (Exception e) {
            showError(e.getMessage());
            return;
        }

        frmCarParking = new JFrame();
        frmCarParking.getContentPane().setBackground(Color.WHITE);
        frmCarParking.setTitle("Car Parking Management System");
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

        JLabel labelus = new JLabel("Programmed by Busra DEDEOGLU and Yigit EGEMEN");
        labelus.setFont(new Font("Sitka Text", Font.ITALIC, 20));
        labelus.setForeground(Color.BLACK);
        labelus.setBounds(122, 40, 740, 40);
        frmCarParking.getContentPane().add(labelus);

        JLabel lbldis = new JLabel("DISABLED PARKING ONLY: A-D,B-D,C-D");
        lbldis.setFont(new Font("Sitka Text", Font.BOLD, 13));
        lbldis.setForeground(SystemColor.text);
        lbldis.setBounds(50, 380, 360, 20);
        frmCarParking.getContentPane().add(lbldis);

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

        ActionListener chekboxListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBox _currentCheckbox = (JCheckBox) e.getSource();
                // make the selected one red
                _currentCheckbox.setBackground(Color.RED);
                // move its name to the SLOT field
                tfplcNmbr.setText(_currentCheckbox.getText());

                // make other red checkboxes green
                for (JCheckBox _otherCheckbox : parkingSpots) {
                    if (_otherCheckbox != _currentCheckbox && _otherCheckbox.getBackground() != Color.GREEN) {
                        _otherCheckbox.setBackground(Color.GREEN);
                    }
                }
            }
        };

        //all the parking slots by using checkboxes
        int xState = 6;
        int yState = 7;

        int xGap = 58;
        int yGap = 104;

        for (int i = 1; i <= slotNames.length; i++) {
            ParkingSpot spot = new ParkingSpot(slotNames[i - 1]);

            spot.setBackground(Color.GREEN);
            spot.addActionListener(chekboxListener);
            spot.setBounds(xState, yState, 50, 80);

            panel.add(spot);
            parkingSpots.add(spot);

            if (i % 6 == 0) {
                xState = 6;
                yState += yGap;
            } else {
                xState += xGap;
            }
        }

        ActionListener doneListener = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // todo: overhaul
                ParkedVehicle temp = new ParkedVehicle();
                tfcarNmbr.setText("");
                tfplcNmbr.setText("");
                tfTime.setText("");
                tfDuration.setText("");
                tfDate.setText("");
                try {
                    // todo: add to db
                    ;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        JTextPane pnCarNmbr = new JTextPane();
        pnCarNmbr.setBackground(Color.BLACK);
        pnCarNmbr.setForeground(SystemColor.text);
        pnCarNmbr.setFont(new Font("Sitka Text", Font.BOLD, 13));
        pnCarNmbr.setText("CAR NUMBER:");
        pnCarNmbr.setBounds(386, 95, 109, 25);
        frmCarParking.getContentPane().add(pnCarNmbr);

        tfcarNmbr = new JTextField();
        tfcarNmbr.setBackground(SystemColor.inactiveCaptionBorder);
        tfcarNmbr.setColumns(10);
        tfcarNmbr.setBounds(510, 95, 100, 25);
        frmCarParking.getContentPane().add(tfcarNmbr);

        JTextPane pnPlcNmbr = new JTextPane();
        pnPlcNmbr.setForeground(Color.WHITE);
        pnPlcNmbr.setBackground(Color.BLACK);
        pnPlcNmbr.setFont(new Font("Sitka Text", Font.BOLD, 13));
        pnPlcNmbr.setText("SLOT:");
        pnPlcNmbr.setBounds(386, 135, 46, 25);
        frmCarParking.getContentPane().add(pnPlcNmbr);

        tfplcNmbr = new JTextField();
        tfplcNmbr.setBackground(SystemColor.inactiveCaptionBorder);
        tfplcNmbr.setColumns(10);
        tfplcNmbr.setBounds(510, 135, 40, 25);
        frmCarParking.getContentPane().add(tfplcNmbr);

        JTextPane pnTime = new JTextPane();
        pnTime.setBackground(Color.BLACK);
        pnTime.setForeground(Color.WHITE);
        pnTime.setFont(new Font("Sitka Text", Font.BOLD, 13));
        pnTime.setText("FROM:");
        pnTime.setBounds(386, 175, 46, 25);
        frmCarParking.getContentPane().add(pnTime);

        JTextPane pnEndTime = new JTextPane();
        pnEndTime.setForeground(Color.WHITE);
        pnEndTime.setBackground(Color.BLACK);
        pnEndTime.setFont(new Font("Sitka Text", Font.BOLD, 13));
        pnEndTime.setText("TO:");
        pnEndTime.setBounds(386, 215, 30, 25);
        frmCarParking.getContentPane().add(pnEndTime);

        JTextPane pnDate = new JTextPane();
        pnDate.setBackground(Color.BLACK);
        pnDate.setForeground(Color.WHITE);
        pnDate.setFont(new Font("Sitka Text", Font.BOLD, 13));
        pnDate.setText("DATE:");
        pnDate.setBounds(386, 255, 46, 25);
        frmCarParking.getContentPane().add(pnDate);

        JTextPane txtpnTotalCost = new JTextPane();
        txtpnTotalCost.setForeground(Color.WHITE);
        txtpnTotalCost.setBackground(Color.BLACK);
        txtpnTotalCost.setFont(new Font("Sitka Text", Font.BOLD, 13));
        txtpnTotalCost.setText("COST:");
        txtpnTotalCost.setBounds(386, 295, 46, 25);
        frmCarParking.getContentPane().add(txtpnTotalCost);

        JTextField txtTkPer = new JTextField();
        txtTkPer.setText("5$ per hour");
        txtTkPer.setBackground(SystemColor.inactiveCaptionBorder);
        txtTkPer.setColumns(10);
        txtTkPer.setBounds(510, 295, 75, 25);
        frmCarParking.getContentPane().add(txtTkPer);

        final JButton donebtn = new JButton("Done");
        donebtn.addActionListener(doneListener);

        donebtn.setFont(new Font("Sitka Text", Font.PLAIN, 12));
        donebtn.setBounds(510, 338, 89, 23);
        frmCarParking.getContentPane().add(donebtn);

        tfTime = new JTextField();
        tfTime.setBackground(SystemColor.inactiveCaptionBorder);
        tfTime.setForeground(SystemColor.desktop);
        tfTime.setBounds(510, 175, 75, 25);
        frmCarParking.getContentPane().add(tfTime);
        tfTime.setColumns(10);

        tfDuration = new JTextField();
        tfDuration.setBackground(SystemColor.inactiveCaptionBorder);
        tfDuration.setForeground(SystemColor.desktop);
        tfDuration.setBounds(510, 215, 75, 25);
        frmCarParking.getContentPane().add(tfDuration);
        tfDuration.setColumns(10);

        tfDate = new JTextField();
        tfDate.setBackground(SystemColor.inactiveCaptionBorder);
        tfDate.setBounds(510, 255, 75, 25);
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
        rdtime1.setBounds(485, 177, 21, 23);
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
        rdtime2.setBounds(485, 217, 21, 23);
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
        rddate.setBounds(485, 257, 21, 23);
        frmCarParking.getContentPane().add(rddate);


        //for background
        JLabel labelback = new JLabel("");
        labelback.setIcon(new ImageIcon("C:\\Users\\BD\\Desktop\\parking.jpg"));
        labelback.setBounds(0, 79, 745, 324);
        frmCarParking.getContentPane().add(labelback);

    }

    void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}