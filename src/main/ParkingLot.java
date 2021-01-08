package main;

import main.db.SQLite;

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
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public static final String[] slotNames = {
            "A1", "A2", "A3", "A4", "A5", "A-D",
            "B1", "B2", "B3", "B4", "B5", "B-D",
            "C1", "C2", "C3", "C4", "C5", "C-D",
    };

    private final ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
    private final ArrayList<JTextPane> toBeRemovedPanes = new ArrayList<>();
    private final ArrayList<JTextField> toBeRemovedFields = new ArrayList<>();

    private JFrame mainFrame;
    private JButton doneButton;
    private JTextField licensePlateField;
    private JTextField slotCodeField;

    private SQLite db;

    public ParkingLot() {
        initialize();
    }

    // launch the app
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ParkingLot window = new ParkingLot();
                window.mainFrame.paint(null);
                window.mainFrame.setVisible(true);
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
        JTextArea comp = new JTextArea(msg);
        comp.setBackground(new Color(238, 238, 238));
        JOptionPane.showMessageDialog(null, comp);
    }

    private void initialize() {
        try {
            // db connection
            db = new SQLite("parking_lot.db");
        } catch (Exception e) {
            showError(e.getMessage(), true);
        }

        // main frame
        mainFrame = new JFrame();
        mainFrame.getContentPane().setBackground(Color.WHITE);
        mainFrame.setTitle("Car Parking Management System");
        mainFrame.setBounds(100, 100, 761, 442);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        ImageIcon img = new ImageIcon("assets/car.png");
        mainFrame.setIconImage(img.getImage());

        JLabel label = new JLabel("CAR PARKING MANAGEMENT SYSTEM");
        label.setForeground(new Color(128, 0, 0));
        label.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 27));
        label.setBounds(0, 0, 752, 44);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        mainFrame.add(label);

        JLabel labelus = new JLabel("Programmed by Büşra DEDEOĞLU and Yiğit EGEMEN");
        labelus.setFont(new Font("Sitka Text", Font.ITALIC, 20));
        labelus.setForeground(Color.BLACK);
        labelus.setBounds(122, 40, 740, 40);
        mainFrame.add(labelus);

        JLabel lbldis = new JLabel("DISABLED PARKING ONLY: A-D,B-D,C-D");
        lbldis.setFont(FONT);
        lbldis.setForeground(SystemColor.text);
        lbldis.setBounds(50, 380, 360, 20);
        mainFrame.getContentPane().add(lbldis);

        JLabel lblNewLabel_1 = new JLabel("THANK YOU AND DRIVE SAFELY!");
        lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 20));
        lblNewLabel_1.setForeground(SystemColor.text);
        lblNewLabel_1.setBounds(386, 370, 360, 30);
        mainFrame.getContentPane().add(lblNewLabel_1);

        addParkingSpotsToGUI();
        db.assignParkedCarsToSpots(this);
        updateParkingSpots(parkingSpots.get(0), false);

        ActionListener doneListener = unnecessary_arg -> parkButtonAction();

        // LICENSE PLATE text
        addTextPane("License Plate:", 386, 95, 96, 20, false);
        // LICENSE PLATE field
        licensePlateField = addTextField(null, 510, 95, 100, 20, false);

        // SLOT text
        addTextPane("Slot:", 386, 135, 96, 20, false);
        // SLOT field
        slotCodeField = addTextField(null, 510, 135, 40, 20, false);

        // DONE button
        doneButton = new JButton("Park");
        doneButton.addActionListener(doneListener);
        doneButton.setFont(new Font("Sitka Text", Font.PLAIN, 12));
        doneButton.setBounds(510, 338, 89, 23);
        mainFrame.getContentPane().add(doneButton);

        // background
        JLabel labelback = new JLabel();
        labelback.setIcon(new ImageIcon("assets/bg.jpg"));
        labelback.setBounds(0, 79, 745, 324);
        mainFrame.getContentPane().add(labelback);
    }

    private void addParkingSpotsToGUI() {
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.activeCaption);
        panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, new Color(192, 192, 192), Color.LIGHT_GRAY));
        panel.setBounds(0, 79, 352, 324);
        panel.setLayout(null);
        mainFrame.add(panel);

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

    public ParkingSpot getParkingSpotWithCode(String code) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSpotCode().equals(code))
                return spot;
        }
        return null;
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
                slotCodeField.setText(updatedCheckbox.getSpotCode());

                // make the selected one gray if empty
                if (updatedCheckbox.isFree()) {
                    updatedCheckbox.setBackground(Color.GRAY);
                    updatedCheckbox.setText(updatedCheckbox.getSpotCode());
                    doneButton.setText("Park");

                    // remove parked fields
                    removeParkedVehicleInfo();
                } else {
                    Vehicle parkedVehicle = updatedCheckbox.getParkedVehicle();

                    // move license plate too if parked
                    licensePlateField.setText(parkedVehicle.getLicensePlate());

                    // start time
                    addTextPane("Entry Date:", 386, 220, 96, 20, true);
                    // start time field
                    addTextField(parkedVehicle.getEntryDateString(), 510, 220, 200, 20, true).setEditable(false);

                    // current time
                    addTextPane("Current Date:", 386, 260, 96, 20, true);
                    // current time field
                    addTextField(LocalDateTime.now().format(dateFormatter), 510, 260, 200, 20, true).setEditable(false);

                    // current fee
                    addTextPane("Current Fee:", 386, 300, 96, 20, true);
                    // current fee field
                    addTextField(decimalFormat.format(parkedVehicle.getCurrentFee()), 510, 300, 50, 20, true);

                    doneButton.setText("Unpark");
                    updatedCheckbox.setBackground(new Color(114, 1, 1));
                }
            } else {
                removeParkedVehicleInfo();
            }
        }
    }

    public void parkButtonAction() {
        String licensePlate = licensePlateField.getText();
        String slotCode = slotCodeField.getText();

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
                        "\nThe fee:\t\t $" + decimalFormat.format(fee));
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

        licensePlateField.setText("");
        slotCodeField.setText("");
    }

    public void addTextPane(String str, int x, int y, int width, int height, boolean removeWhenUpdated) {
        JTextPane pane = new JTextPane();
        pane.setForeground(Color.WHITE);
        pane.setBackground(Color.BLACK);
        pane.setFont(FONT);
        pane.setText(str);
        pane.setBounds(x, y, width, height);

        mainFrame.add(pane);
        if (removeWhenUpdated)
            toBeRemovedPanes.add(pane);
    }

    public JTextField addTextField(String str, int x, int y, int width, int height, boolean removeWhenUpdated) {
        JTextField field = new JTextField(str);

        field.setForeground(Color.BLACK);
        field.setBackground(SystemColor.inactiveCaptionBorder);
        field.setColumns(10);
        field.setBounds(x, y, width, height);

        mainFrame.add(field);
        if (removeWhenUpdated)
            toBeRemovedFields.add(field);

        return field;
    }

    public void removeParkedVehicleInfo() {
        for (Component comp : toBeRemovedFields)
            mainFrame.remove(comp);
        for (Component comp : toBeRemovedPanes)
            mainFrame.remove(comp);

        toBeRemovedFields.clear();
        toBeRemovedPanes.clear();
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}