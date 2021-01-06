package main.gui;

import main.db.SQLite;
import main.parking_lot.Vehicle;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ParkingLot {
    public static final double feePerMinute = 0.1;
    public static final Font FONT = new Font("Sitka Text", Font.BOLD, 13);
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy");
    public static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    private final String[] slotNames = {
            "A1", "A2", "A3", "A4", "A5", "A-D",
            "B1", "B2", "B3", "B4", "B5", "B-D",
            "C1", "C2", "C3", "C4", "C5", "C-D",
    };

    public ArrayList<Vehicle> parkedCars = new ArrayList<>();
    public ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
    public ArrayList<JTextPane> textPanes = new ArrayList<>();
    public ArrayList<JTextField> textFields = new ArrayList<>();

    private JFrame frmCarParking;
    private JTextField tfLicensePlate;
    private JTextField tfSlotCode;
    private JButton donebtn;

    private SQLite db;

    //to create the application
    public ParkingLot() {
        initialize();
    }

    //launch the application
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ParkingLot window = new ParkingLot();
                window.frmCarParking.paint(null);
                window.frmCarParking.setVisible(true);
            } catch (Exception e) {
                showError(e.getMessage(), true);
            }
        });
    }

    static void showError(String msg, boolean exitAfterwards) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
        if (exitAfterwards) {
            System.exit(-1);
        }
    }

    static void showSuccess(String msg) {
        JOptionPane.showMessageDialog(null, new JTextArea(msg));
    }

    //initializing the contents of the frame
    private void initialize() {
        // file stuff
        try {
            db = new SQLite("parking_lot.db");
        } catch (Exception e) {
            showError(e.getMessage(), true);
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
        lbldis.setFont(FONT);
        lbldis.setForeground(SystemColor.text);
        lbldis.setBounds(50, 380, 360, 20);
        frmCarParking.getContentPane().add(lbldis);

        JLabel lblNewLabel_1 = new JLabel("THANK YOU AND DRIVE SAFELY!");
        lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 20));
        lblNewLabel_1.setForeground(SystemColor.text);
        lblNewLabel_1.setBounds(386, 370, 360, 30);
        frmCarParking.getContentPane().add(lblNewLabel_1);

        addParkingSpots();
        parkedCars = db.getParkedCars(this);
        updateParkingSpots(parkingSpots.get(0), false);

        ActionListener doneListener = unnecessary_arg -> parkButtonAction();

        // LICENSE PLATE text
        addTextPane("License Plate:", 386, 95, 109, 25, false);
        // LICENSE PLATE field
        tfLicensePlate = addTextField(null, 510, 95, 100, 25, false);

        // SLOT text
        addTextPane("Slot:", 386, 135, 46, 25, false);
        // SLOT field
        tfSlotCode = addTextField(null, 510, 135, 40, 25, false);

        // DONE button
        donebtn = new JButton("Park");
        donebtn.addActionListener(doneListener);
        donebtn.setFont(new Font("Sitka Text", Font.PLAIN, 12));
        donebtn.setBounds(510, 338, 89, 23);
        frmCarParking.getContentPane().add(donebtn);

        // background
        JLabel labelback = new JLabel();
        labelback.setIcon(new ImageIcon("assets/bg.jpg"));
        labelback.setBounds(0, 79, 745, 324);
        frmCarParking.getContentPane().add(labelback);

        //fee changing //need
        /*JTextPane pnmin = new JTextPane();
        pnmin.setBackground(Color.BLACK);
        pnmin.setForeground(Color.WHITE);
        pnmin.setFont(new Font("Sitka Text", Font.BOLD, 13));
        pnmin.setText("fee-min:");
        pnmin.setBounds(386, 305, 65, 25);
        frmCarParking.getContentPane().add(pnmin);
        // field
        JTextField tfmin = new JTextField();
        tfmin.setBackground(SystemColor.inactiveCaptionBorder);
        tfmin.setColumns(10);
        tfmin.setBounds(460, 305, 30, 25);
        frmCarParking.getContentPane().add(tfmin);
        String feemin = tfmin.getText();
        //int feehour = Integer.parseInt(feemin);

            JTextPane pnmin1 = new JTextPane();
            pnmin1.setBackground(Color.BLACK);
            pnmin1.setForeground(Color.WHITE);
            pnmin1.setFont(new Font("Sitka Text", Font.BOLD, 13));
            pnmin1.setText("fee-hour:");
            pnmin1.setBounds(500, 305, 65, 25);
            frmCarParking.getContentPane().add(pnmin1);
        //int feehour1 = feehour * 60;
       // int feehour2 = Integer.toString(feehour1);
            JTextField tfmin1 = new JTextField();
            tfmin1.setBackground(SystemColor.inactiveCaptionBorder);
            tfmin1.setColumns(10);
            tfmin1.setBounds(570, 305, 30, 25);
            frmCarParking.getContentPane().add(tfmin1);*/

    }

    private void addParkingSpots() {
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.activeCaption);
        panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, new Color(192, 192, 192), Color.LIGHT_GRAY));
        panel.setBounds(0, 79, 352, 324);
        panel.setLayout(null);
        frmCarParking.add(panel);

        ActionListener chekboxListener = e -> updateParkingSpots((ParkingSpot) e.getSource(), false);

        // all the parking slots by using checkboxes
        int xState = 6;
        int yState = 7;

        int xGap = 58;
        int yGap = 104;

        for (int i = 1; i <= slotNames.length; i++) {
            String slotCode = slotNames[i - 1];

            ParkingSpot spot = new ParkingSpot(slotCode);

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
    }


    void updateParkingSpots(ParkingSpot updatedCheckbox, boolean untickUpdated) {
        if (untickUpdated)
            updatedCheckbox.setSelected(false);

        for (ParkingSpot spot : parkingSpots) {
            // untick the rest
            if (spot != updatedCheckbox) {
                spot.setSelected(false);
            }

            // fix colors and titles
            if (!spot.isFree()) {
                spot.setText(spot.getParkedVehicle().getLicensePlate());
                spot.setBackground(Color.RED);
            } else {
                spot.setText(spot.getSpotCode());
                spot.setBackground(Color.GREEN);
            }

            // if selected
            if (updatedCheckbox.isSelected()) {
                // move its name to the SLOT field
                tfSlotCode.setText(updatedCheckbox.getSpotCode());

                // make the selected one gray if empty
                if (updatedCheckbox.isFree()) {
                    updatedCheckbox.setBackground(Color.GRAY);
                    updatedCheckbox.setText(updatedCheckbox.getSpotCode());
                    donebtn.setText("Park");

                    // remove parked fields
                    removeParkedVehicleInfo();
                } else {
                    Vehicle parkedVehicle = updatedCheckbox.getParkedVehicle();

                    // move license plate too if parked
                    tfLicensePlate.setText(parkedVehicle.getLicensePlate());

                    // start time
                    addTextPane("Entry Date:", 386, 220, 96, 20, true);
                    // start time field
                    addTextField(parkedVehicle.getEntryDateString(), 510, 220, 200, 20, true).setEditable(false);

                    // current time
                    addTextPane("Current Date", 386, 260, 96, 20, true);
                    // current time field
                    addTextField(LocalDateTime.now().format(dateFormatter), 510, 260, 200, 20, true).setEditable(false);

                    // current fee
                    addTextPane("Current Fee", 386, 300, 96, 20, true);
                    // current fee field
                    addTextField(decimalFormat.format(parkedVehicle.getCurrentFee()), 510, 300, 50, 20, true);

                    donebtn.setText("Unpark");
                    updatedCheckbox.setBackground(new Color(114, 1, 1));
                }
            } else {
                removeParkedVehicleInfo();
            }
        }
    }

    public void parkButtonAction() {
        String licensePlate = tfLicensePlate.getText();
        String slotCode = tfSlotCode.getText();

        if (licensePlate.equals("") || slotCode.equals("")) {
            showError("License Plate and Slot can not be empty!", false);
            return;
        }

        // find the spot
        ParkingSpot actualSpotObj = getParkingSpotWithCode(slotCode);
        if (actualSpotObj == null) {
            showError("Invalid slot code!", false);
            return;
        }

        Vehicle vehicle;
        try {
            // unpark
            if (!actualSpotObj.isFree()) {
                vehicle = actualSpotObj.getParkedVehicle();
                double fee = vehicle.unparkAndGetFee(db);

                updateParkingSpots(vehicle.getParkedSpot(), true);
                showSuccess("License Plate:\t\t" + vehicle.getLicensePlate() +
                        "\nEntry Time:\t\t" + vehicle.getEntryDateString() +
                        "\nDeparture Time:\t" + vehicle.getDepartureDateString() +
                        "\nTotal Park Time:\t" + vehicle.getDurationUntilDeparture().toMinutes() + " minute(s)." +
                        "\nThe fee:\t\t $" + fee);
            }
            // park new car
            else {
                vehicle = db.insertNewCar(licensePlate, actualSpotObj);
                actualSpotObj.park(vehicle);

                updateParkingSpots(vehicle.getParkedSpot(), true);
                showSuccess("Vehicle is parked.");
            }
        } catch (Exception e) {
            showError(e.getMessage(), false);
            return;
        }

        tfLicensePlate.setText("");
        tfSlotCode.setText("");
    }

    public ParkingSpot getParkingSpotWithCode(String code) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSpotCode().equals(code))
                return spot;
        }
        return null;
    }

    public JTextPane addTextPane(String str, int x, int y, int width, int height, boolean removeWhenUpdated) {
        JTextPane pane = new JTextPane();
        pane.setForeground(Color.WHITE);
        pane.setBackground(Color.BLACK);
        pane.setFont(FONT);
        pane.setText(str);
        pane.setBounds(x, y, width, height);

        frmCarParking.add(pane);
        if (removeWhenUpdated)
            textPanes.add(pane);

        return pane;
    }

    public JTextField addTextField(String str, int x, int y, int width, int height, boolean removeWhenUpdated) {
        JTextField field = new JTextField(str);

        field.setForeground(Color.BLACK);
        field.setBackground(SystemColor.inactiveCaptionBorder);
        field.setColumns(10);
        field.setBounds(x, y, width, height);

        frmCarParking.add(field);
        if (removeWhenUpdated)
            textFields.add(field);

        return field;
    }

    public void removeParkedVehicleInfo() {
        for (Component comp :
                frmCarParking.getContentPane().getComponents()) {
            if (textFields.contains(comp) || textPanes.contains(comp)) {
                frmCarParking.remove(comp);
                frmCarParking.revalidate();
                frmCarParking.repaint();

                if (comp instanceof JTextField)
                    textFields.remove(comp);
                if (comp instanceof JTextPane)
                    textPanes.remove(comp);
            }

        }
    }

}