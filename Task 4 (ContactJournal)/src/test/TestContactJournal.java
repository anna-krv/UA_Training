package test;

import contactJournal.Contact;
import contactJournal.Controller;
import contactJournal.Model;
import contactJournal.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestContactJournal {
    private static Controller controller;
    private static Model model;
    private static View view;

    @BeforeClass
    public static void setUp() {
        model = new Model();
        view = new View();
        controller = new Controller(model, view);
    }

    @Before
    public void setScanner() {
        controller.setScannerToFile("src//test//contact.txt");
    }

    @Test
    public void checkWordTest() {
        Assert.assertTrue(controller.check("Anna-Maria", controller.WORD_PATTERN));
    }

    @Test
    public void checkNotWordTest() {
        Assert.assertFalse(controller.check("I_s_aa_c", controller.WORD_PATTERN));
    }


    @Test
    public void checkNicknameTest() {
        Assert.assertTrue(controller.check("sir_isaac43", controller.NICKNAME_PATTERN));
    }

    @Test
    public void checkNotNicknameTest() {
        Assert.assertFalse(controller.check("wrong*symbol", controller.NICKNAME_PATTERN));
    }


    @Test
    public void checkCommentTest() {
        Assert.assertTrue(controller.check("This is a simple comment.", controller.COMMENT_PATTERN));
    }

    @Test
    public void checkNotCommentTest() {
        Assert.assertFalse(controller.check("x".repeat(501),
                controller.COMMENT_PATTERN));
    }

    @Test
    public void checkGroupTest() {
        for (String groupName : new String[]{"Family", "Friends", "Coworkers", "other"}) {
            Assert.assertTrue(controller.check(groupName, controller.GROUP_PATTERN));
        }
    }

    @Test
    public void checkNotGroupTest() {
        Assert.assertFalse(controller.check("FamilyAndOthers", controller.GROUP_PATTERN));
    }

    @Test
    public void checkPhoneTest() {
        Assert.assertTrue(controller.check("+380981111111", controller.PHONE_PATTERN));
    }

    @Test
    public void checkPhoneLongTest() {
        Assert.assertFalse(controller.check("+380123456789123456789", controller.PHONE_PATTERN));
    }

    @Test
    public void checkNotPhoneTest() {
        Assert.assertFalse(controller.check("+380 77 989 6 54", controller.PHONE_PATTERN));
    }

    @Test
    public void checkSecondPhoneTest() {
        Assert.assertTrue(controller.check("", controller.SECOND_PHONE_PATTERN));
    }

    @Test
    public void checkNotSecondPhoneTest() {
        Assert.assertFalse(controller.check("+380 77 989 6 54", controller.SECOND_PHONE_PATTERN));
    }

    @Test
    public void checkEmailTest() {
        Assert.assertTrue(controller.check("isaac-newton43@cam.ac.uk", controller.EMAIL_PATTERN));
    }

    @Test
    public void checkNotEmailTest() {
        Assert.assertFalse(controller.check("isaac-newton@cam", controller.EMAIL_PATTERN));
    }

    @Test
    public void checkSkypeTest() {
        Assert.assertTrue(controller.check("isaac2512newton", controller.SKYPE_PATTERN));
    }

    @Test
    public void checkNotSkypeTest() {
        Assert.assertFalse(controller.check("i*am*isaac*", controller.SKYPE_PATTERN));
    }

    @Test
    public void checkPostalCodeTest() {
        Assert.assertTrue(controller.check("AB3 CD4", controller.POSTAL_CODE_PATTERN));
    }

    @Test
    public void checkNotPostalCodeTest() {
        Assert.assertFalse(controller.check("(595 35)", controller.POSTAL_CODE_PATTERN));
    }

    @Test
    public void checkWordComboTest() {
        Assert.assertTrue(controller.check("San  Francisco", controller.WORD_COMBO_PATTERN));
    }

    @Test
    public void checkNotWordComboTest() {
        Assert.assertFalse(controller.check("some city from far far away \t too long to be a name",
                controller.WORD_COMBO_PATTERN));
    }

    @Test
    public void checkNumberTest() {
        Assert.assertTrue(controller.check("221", controller.NUMBER_PATTERN));
    }

    @Test
    public void checkNotNumberTest() {
        Assert.assertFalse(controller.check("023", controller.NUMBER_PATTERN));
    }

    @Test
    public void getNameTest() {
        controller.setScannerToFile("src\\test\\name.txt");
        Assert.assertEquals("Isaac", controller.getField("name", controller.WORD_PATTERN));
    }

    @Test
    public void getCompoundNameTest() {
        Assert.assertEquals("Newton I.", Contact.getCompoundName("Newton", "Isaac"));
    }

    @Test
    public void getNicknameTest() {
        controller.setScannerToFile("src\\test\\nickname.txt");
        Assert.assertEquals("sir_isaac43", controller.getField("nickname", controller.NICKNAME_PATTERN));
    }

    @Test
    public void getGroupTest() {
        controller.setScannerToFile("src\\test\\group.txt");
        Assert.assertTrue("FAMILY".equalsIgnoreCase(
                controller.getField("group", controller.GROUP_PATTERN
                )));
    }

    @Test
    public void getAddressTest() {
        controller.setScannerToFile("src\\test\\address.txt");
        Assert.assertNotNull(controller.getAddress());
    }

    @Test
    public void getContact() {
        Assert.assertNotNull(controller.getContact());
    }

}
