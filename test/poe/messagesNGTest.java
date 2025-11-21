/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poe;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_Lab
 */
public class messagesNGTest {
    
    public messagesNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of Login method, of class messages.
     */
    @Test
    public void testLogin() {
        System.out.println("Login");
        String fname = "unity";
        String lname = "dube";
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String phone = "+27123456789";
        messages instance = new messages();
        instance.Login(fname, lname, username, password, phone);
        
    }

    /**
     * Test of checkUserName method, of class messages.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "kyl_1";
        messages instance = new messages();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(result, expResult);
        
    }

    /**
     * Test of checkPasswordComplexity method, of class messages.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String pw = "Ch&&sec@ke99!";
        messages instance = new messages();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(pw);
        assertEquals(result, expResult);
        
    }

    /**
     * Test of checkCellphoneNumber method, of class messages.
     */
    @Test
    public void testCheckCellphoneNumber() {
        System.out.println("checkCellphoneNumber");
        String num = "+27123456789";
        messages instance = new messages();
        boolean expResult = false;
        boolean result = instance.checkCellphoneNumber(num);
        assertEquals(result, expResult);
        
    }

    /**
     * Test of registerUser method, of class messages.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String u = "kyl_1";
        String p = "Ch&&sec@ke99!";
        String c = "+27123456789";
        messages instance = new messages();
        instance.registerUser(u, p, c);
        
    }

    /**
     * Test of loadStoredMessagesFromJSON method, of class messages.
     */
    @Test
    public void testLoadStoredMessagesFromJSON() {
        System.out.println("loadStoredMessagesFromJSON");
        messages instance = new messages();
        instance.loadStoredMessagesFromJSON();
        
    }

    /**
     * Test of loginUser method, of class messages.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        messages instance = new messages();
        instance.loginUser();
        
    }

    /**
     * Test of sendMessage method, of class messages.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        messages instance = new messages();
        instance.sendMessage();
        
    }

    /**
     * Test of generateMessageID method, of class messages.
     */
    @Test
    public void testGenerateMessageID() {
        System.out.println("generateMessageID");
        messages instance = new messages();
        String expResult = "<message id>";
        String result = instance.generateMessageID();
        assertEquals(result, expResult);
        
    }

    /**
     * Test of createHash method, of class messages.
     */
    @Test
    public void testCreateHash() {
        System.out.println("createHash");
        String id = "Auto generated";
        int num = 0;
        String msg = "";
        messages instance = new messages();
        String expResult = "<message hash>";
        String result = instance.createHash(id, num, msg);
        assertEquals(result, expResult);
        
    }

    /**
     * Test of messageManagement method, of class messages.
     */
    @Test
    public void testMessageManagement() {
        System.out.println("messageManagement");
        messages instance = new messages();
        instance.messageManagement();
        
    }

    /**
     * Test of searchByID method, of class messages.
     */
    @Test
    public void testSearchByID() {
        System.out.println("searchByID");
        messages instance = new messages();
        instance.searchByID();
       
    }

    /**
     * Test of deleteByHash method, of class messages.
     */
    @Test
    public void testDeleteByHash() {
        System.out.println("deleteByHash");
        messages instance = new messages();
        instance.deleteByHash();
        
    }

    /**
     * Test of showSendersRecipients method, of class messages.
     */
    @Test
    public void testShowSendersRecipients() {
        System.out.println("showSendersRecipients");
        messages instance = new messages();
        instance.showSendersRecipients();
        
    }

    /**
     * Test of showLongestMessage method, of class messages.
     */
    @Test
    public void testShowLongestMessage() {
        System.out.println("showLongestMessage");
        messages instance = new messages();
        instance.showLongestMessage();
        
    }

    /**
     * Test of showMessagesToRecipient method, of class messages.
     */
    @Test
    public void testShowMessagesToRecipient() {
        System.out.println("showMessagesToRecipient");
        messages instance = new messages();
        instance.showMessagesToRecipient();
        
    }

    /**
     * Test of fullReport method, of class messages.
     */
    @Test
    public void testFullReport() {
        System.out.println("fullReport");
        messages instance = new messages();
        instance.fullReport();
        
    }

    /**
     * Test of printSentMessages method, of class messages.
     */
    @Test
    public void testPrintSentMessages() {
        System.out.println("printSentMessages");
        messages instance = new messages();
        instance.printSentMessages();
        
    }
    
}
