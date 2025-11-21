/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poe;
import javax.swing.JOptionPane;
/**
 *
 * @author RC_Student_Lab
 */

public class POE {

   
    public static void main(String[] args) {
        
        // Create the Message object (contains all logic and JSON loading)
        messages obj = new messages();

        // -------------------------
        // USER BASIC INFORMATION
        // -------------------------

        String name = JOptionPane.showInputDialog(null,
                "Enter your Name:",
                "Name Input",
                JOptionPane.QUESTION_MESSAGE);

        if (name == null) return;

        String surname = JOptionPane.showInputDialog(null,
                "Enter your Surname:",
                "Surname Input",
                JOptionPane.QUESTION_MESSAGE);

        if (surname == null) return;

        // -------------------------
        // USERNAME VALIDATION
        // -------------------------

        String username;
        do {
            username = JOptionPane.showInputDialog(null,
                    "Enter your username (must contain _ and be 5 characters or less):",
                    "Username Input",
                    JOptionPane.QUESTION_MESSAGE);

            if (username == null) return;

        } while (!obj.checkUserName(username));

        JOptionPane.showMessageDialog(null, "Username captured successfully");

        // -------------------------
        // PASSWORD VALIDATION
        // -------------------------

        String password;
        do {
            password = JOptionPane.showInputDialog(null,
                    "Enter your password:\n"
                    + "- At least 8 characters\n"
                    + "- Upper & lower case\n"
                    + "- Digit\n"
                    + "- Special character",
                    "Password Input",
                    JOptionPane.QUESTION_MESSAGE);

            if (password == null) return;

        } while (!obj.checkPasswordComplexity(password));

        JOptionPane.showMessageDialog(null, "Password successfully captured");

        // -------------------------
        // CELLPHONE NUMBER VALIDATION
        // -------------------------

        String phone;
        do {
            phone = JOptionPane.showInputDialog(null,
                    "Enter your cellphone number in international format (+27XXXXXXXXX):",
                    "Phone Number Input",
                    JOptionPane.QUESTION_MESSAGE);

            if (phone == null) return;

        } while (!obj.checkCellphoneNumber(phone));

        JOptionPane.showMessageDialog(null, "Cellphone number captured successfully");

        // -------------------------
        // INITIALIZE LOGIN DATA
        // -------------------------

        obj.Login(name, surname, username, password, phone);

        // -------------------------
        // REGISTER USER
        // -------------------------

        obj.registerUser(username, password, phone);

        // -------------------------
        // LOGIN + MENU (inside Message class)
        // -------------------------

        obj.loginUser();
    }
}
    
    

