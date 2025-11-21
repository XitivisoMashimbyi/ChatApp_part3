/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author RC_Student_Lab
 */
public class messages {
   
    // ---------------------------
    // PUBLIC INSTANCE VARIABLES
    // ---------------------------

    public String Name;
    public String Surname;
    public String registeredUserName;
    public String registeredPassword;
    public String registeredCellphoneNumber;
    public String userName;
    public String userPassword;
    public String CellphoneNumber;

    public ArrayList<String> sentMessages = new ArrayList<>();
    public ArrayList<String> disregardedMessages = new ArrayList<>();
    public ArrayList<String> storedMessages = new ArrayList<>();
    public ArrayList<String> recipients = new ArrayList<>();
    public ArrayList<String> messageHashes = new ArrayList<>();
    public ArrayList<String> messageIDs = new ArrayList<>();

    public Random random = new Random();

    // JSON FILE PATH
    public String jsonFilePath = "stored_messages.json";

    // ---------------------------
    // LOGIN & REGISTRATION LOGIC
    // ---------------------------

    public void Login(String fname, String lname, String username, String password, String phone) {
        Name = fname;
        Surname = lname;
        userName = username;
        userPassword = password;
        CellphoneNumber = phone;
    }

    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Username must contain '_' and be ≤ 5 characters.", "ERROR", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean checkPasswordComplexity(String pw) {
        if (pw.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*_+=-]).{8,}$")) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Password must be 8+ chars with upper, lower, digit, special char.", "ERROR", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean checkCellphoneNumber(String num) {
        if (num.matches("^\\+[0-9]{11}$")) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Phone number must use international format +27XXXXXXXXX.", "ERROR", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void registerUser(String u, String p, String c) {
        if (checkUserName(u) && checkPasswordComplexity(p) && checkCellphoneNumber(c)) {
            registeredUserName = u;
            registeredPassword = p;
            registeredCellphoneNumber = c;
            JOptionPane.showMessageDialog(null, "Registration successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed.");
        }
    }

    // ---------------------------
    // JSON LOADING AUTOMATICALLY
    // ---------------------------

    public void loadStoredMessagesFromJSON() {

    try {
        if (!Files.exists(Paths.get(jsonFilePath))) {
            return;
        }

        String jsonText = Files.readString(Paths.get(jsonFilePath)).trim();

        // Remove outer brackets [   ]
        jsonText = jsonText.substring(1, jsonText.length() - 1).trim();

        // Split objects by "}," safely
        String[] items = jsonText.split("\\},\\s*\\{");

        for (String item : items) {

            item = item.replace("{", "").replace("}", "").trim();

            String[] fields = item.split(",");

            String msg = "";
            String rec = "";
            String id = "";
            String hash = "";

            for (String field : fields) {
                String[] pair = field.split(":", 2);
                String key = pair[0].replace("\"", "").trim();
                String value = pair[1].replace("\"", "").trim();

                switch (key) {
                    case "message" -> msg = value;
                    case "recipient" -> rec = value;
                    case "id" -> id = value;
                    case "hash" -> hash = value;
                }
            }

            // Add loaded messages as sent messages
            sentMessages.add(msg);
            recipients.add(rec);
            messageIDs.add(id);
            messageHashes.add(hash);
        }

        JOptionPane.showMessageDialog(null, "Stored messages loaded WITHOUT org.json!");

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error loading JSON file (manual mode).");
    }
}

    
    public void loginUser() {

    if (registeredUserName == null) {
        JOptionPane.showMessageDialog(null, "Please register first.");
        return;
    }

    while (true) {

        userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null) return; // user pressed Cancel

        userPassword = JOptionPane.showInputDialog("Enter password:");
        if (userPassword == null) return; // user pressed Cancel

        if (userName.equals(registeredUserName) && userPassword.equals(registeredPassword)) {

            // AUTO-LOAD JSON STORED MESSAGES
            loadStoredMessagesFromJSON();

            JOptionPane.showMessageDialog(null, "Welcome " + Name + " " + Surname + "!");

            while (true) {
                String[] options = {
                    "Send Message",
                    "Message Management",
                    "Exit"
                };

                int choice = JOptionPane.showOptionDialog(null, "Menu", "Chatbot",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);

                switch (choice) {
                    case 0 -> sendMessage();
                    case 1 -> messageManagement();
                    case 2 -> System.exit(0);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null,
                "Username or Password is incorrect. Please try again.");
        }
    }
}

    
    // ---------------------------
    // MESSAGE SENDING
    // ---------------------------

    public void sendMessage() {

        String recipient;
        do {
            recipient = JOptionPane.showInputDialog("Enter recipient phone:");
            if (recipient == null) return;
        } while (!checkCellphoneNumber(recipient));

        int count;
        try {
            count = Integer.parseInt(JOptionPane.showInputDialog("Number of messages:"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid number.");
            return;
        }

        for (int i = 0; i < count; i++) {
            String msg = JOptionPane.showInputDialog("Message " + (i + 1));
            if (msg == null) return;

            if (msg.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message must be ≤ 250 chars.");
                i--;
                continue;
            }

            String[] options = {"Send", "Store", "Disregard"};
            int action = JOptionPane.showOptionDialog(null, "Choose action:",
                    "Message Action", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String id = generateMessageID();

            switch (action) {
                case 0 -> {
                    sentMessages.add(msg);
                    recipients.add(recipient);
                    messageIDs.add(id);
                    messageHashes.add(createHash(id, sentMessages.size(), msg));
                    JOptionPane.showMessageDialog(null, "Message sent!");
                }
                case 1 -> storedMessages.add(msg);
                case 2 -> disregardedMessages.add(msg);
            }
        }

        printSentMessages();
    }

    // ---------------------------
    // UTILITIES
    // ---------------------------

    public String generateMessageID() {
        String id;
        do {
            id = "" + (1 + random.nextInt(9)) + String.format("%09d", random.nextInt(1_000_000_000));
        } while (messageIDs.contains(id));
        return id;
    }

    public String createHash(String id, int num, String msg) {
        String[] w = msg.split(" ");
        String first = w[0];
        String last = (w.length > 1) ? w[w.length - 1] : w[0];
        return id.substring(0, 2) + ":" + num + ":" + (first + last).toUpperCase();
    }

    // ---------------------------
    // MESSAGE MANAGEMENT
    // ---------------------------

    public void messageManagement() {

        String[] menu = {
            "Search by ID",
            "Delete by Hash",
            "Show Senders/Recipients",
            "Longest Message",
            "Messages to Recipient",
            "Full Report",
            "Back"
        };

        int choice = JOptionPane.showOptionDialog(null, "Message Management",
                "Management", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, menu, menu[0]);

        switch (choice) {
            case 0 -> searchByID();
            case 1 -> deleteByHash();
            case 2 -> showSendersRecipients();
            case 3 -> showLongestMessage();
            case 4 -> showMessagesToRecipient();
            case 5 -> fullReport();
        }
    }

    public void searchByID() {
        String id = JOptionPane.showInputDialog("Enter message ID:");
        int pos = messageIDs.indexOf(id);

        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "ID not found.");
            return;
        }

        JOptionPane.showMessageDialog(null,
                """
                Message: %s
                Recipient: %s
                Hash: %s
                """.formatted(sentMessages.get(pos), recipients.get(pos), messageHashes.get(pos)));
    }

    public void deleteByHash() {
        String hash = JOptionPane.showInputDialog("Enter hash:");
        int pos = messageHashes.indexOf(hash);

        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "Hash not found.");
            return;
        }

        sentMessages.remove(pos);
        recipients.remove(pos);
        messageIDs.remove(pos);
        messageHashes.remove(pos);

        JOptionPane.showMessageDialog(null, "Message deleted.");
    }

    public void showSendersRecipients() {
        StringBuilder sb = new StringBuilder("*** SENDERS & RECIPIENTS ***\n");

        for (int i = 0; i < sentMessages.size(); i++) {
            sb.append("Message ").append(i + 1).append("\n")
                    .append("Sender: ").append(registeredUserName).append("\n")
                    .append("Recipient: ").append(recipients.get(i)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void showLongestMessage() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages.");
            return;
        }

        String longest = Collections.max(sentMessages, Comparator.comparingInt(String::length));

        JOptionPane.showMessageDialog(null, "Longest message:\n" + longest);
    }

    public void showMessagesToRecipient() {
        String target = JOptionPane.showInputDialog("Enter recipient number:");

        StringBuilder sb = new StringBuilder("*** Messages to " + target + " ***\n");

        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(target)) {
                sb.append(sentMessages.get(i)).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void fullReport() {
        StringBuilder sb = new StringBuilder("*** FULL SENT MESSAGE REPORT ***\n");

        for (int i = 0; i < sentMessages.size(); i++) {
            sb.append("Message ").append(i + 1).append("\n")
                    .append("ID: ").append(messageIDs.get(i)).append("\n")
                    .append("Hash: ").append(messageHashes.get(i)).append("\n")
                    .append("Recipient: ").append(recipients.get(i)).append("\n")
                    .append("Message: ").append(sentMessages.get(i)).append("\n")
                    .append("--------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void printSentMessages() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }

        fullReport();
    }
}


