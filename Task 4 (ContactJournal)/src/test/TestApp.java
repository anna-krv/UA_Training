package test;

import app.controller.Note;
import app.controller.InputNoteController;
import app.controller.PatternContainer;
import app.model.Model;
import app.view.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Scanner;

public class TestApp {
    private static InputNoteController inputNoteController;
    private static Model model;
    private static View view;

    @BeforeClass
    public static void setUp() {
        model = new Model();
        view = new View();
        inputNoteController = new InputNoteController(view, new Scanner(System.in));
    }

    @Before
    public void setScanner() {
        inputNoteController.setScannerToFile("src//test//contact.txt");
    }

    @Test
    public void checkWordTest() {
        Assert.assertTrue(inputNoteController.check("Anna-Maria", PatternContainer.WORD_PATTERN_EN));
    }

    @Test
    public void checkNotWordTest() {
        Assert.assertFalse(inputNoteController.check("I_s_aa_c", PatternContainer.WORD_PATTERN_EN));
    }

    @Test
    public void checkWordUATest() {
        Assert.assertTrue(inputNoteController.check("Олександра", PatternContainer.WORD_PATTERN_UA));
    }

    @Test
    public void checkNotWordUATest() {
        Assert.assertFalse(inputNoteController.check("!Валентина", PatternContainer.WORD_PATTERN_UA));
    }


    @Test
    public void checkNicknameTest() {
        Assert.assertTrue(inputNoteController.check("sir_isaac43", PatternContainer.NICKNAME_PATTERN));
    }

    @Test
    public void checkNotNicknameTest() {
        Assert.assertFalse(inputNoteController.check("wrong*symbol", PatternContainer.NICKNAME_PATTERN));
    }


    @Test
    public void checkCommentTest() {
        Assert.assertTrue(inputNoteController.check("This is a simple comment.", PatternContainer.COMMENT_PATTERN));
    }

    @Test
    public void checkCommentUATest() {
        Assert.assertTrue(inputNoteController.check("Це простий коментар.", PatternContainer.COMMENT_PATTERN));
    }

    @Test
    public void checkNotCommentTest() {
        Assert.assertFalse(inputNoteController.check("x".repeat(501),
                PatternContainer.COMMENT_PATTERN));
    }

    @Test
    public void checkGroupTest() {
        for (String groupName : new String[]{"Family", "Friends", "Coworkers", "other"}) {
            Assert.assertTrue(inputNoteController.check(groupName, PatternContainer.GROUP_PATTERN));
        }
    }

    @Test
    public void checkNotGroupTest() {
        Assert.assertFalse(inputNoteController.check("FamilyAndOthers", PatternContainer.GROUP_PATTERN));
    }

    @Test
    public void checkPhoneTest() {
        Assert.assertTrue(inputNoteController.check("+380981111111", PatternContainer.PHONE_PATTERN));
    }

    @Test
    public void checkPhoneLongTest() {
        Assert.assertFalse(inputNoteController.check("+380123456789123456789", PatternContainer.PHONE_PATTERN));
    }

    @Test
    public void checkNotPhoneTest() {
        Assert.assertFalse(inputNoteController.check("+380 77 989 6 54", PatternContainer.PHONE_PATTERN));
    }

    @Test
    public void checkSecondPhoneTest() {
        Assert.assertTrue(inputNoteController.check("", PatternContainer.SECOND_PHONE_PATTERN));
    }

    @Test
    public void checkNotSecondPhoneTest() {
        Assert.assertFalse(inputNoteController.check("+380 77 989 6 54", PatternContainer.SECOND_PHONE_PATTERN));
    }

    @Test
    public void checkEmailTest() {
        Assert.assertTrue(inputNoteController.check("isaac-newton43@cam.ac.uk", PatternContainer.EMAIL_PATTERN));
    }

    @Test
    public void checkNotEmailTest() {
        Assert.assertFalse(inputNoteController.check("isaac-newton@cam", PatternContainer.EMAIL_PATTERN));
    }

    @Test
    public void checkSkypeTest() {
        Assert.assertTrue(inputNoteController.check("isaac2512newton", PatternContainer.SKYPE_PATTERN));
    }

    @Test
    public void checkNotSkypeTest() {
        Assert.assertFalse(inputNoteController.check("i*am*isaac*", PatternContainer.SKYPE_PATTERN));
    }

    @Test
    public void checkPostalCodeTest() {
        Assert.assertTrue(inputNoteController.check("AB3 CD4", PatternContainer.POSTAL_CODE_PATTERN));
    }

    @Test
    public void checkNotPostalCodeTest() {
        Assert.assertFalse(inputNoteController.check("(595 35)", PatternContainer.POSTAL_CODE_PATTERN));
    }

    @Test
    public void checkWordComboENTest() {
        Assert.assertTrue(inputNoteController.check("San  Francisco", PatternContainer.WORD_COMBO_PATTERN_EN));
    }

    @Test
    public void checkNotWordComboENTest() {
        Assert.assertFalse(inputNoteController.check("some city from far far away \t too long to be a name",
                PatternContainer.WORD_COMBO_PATTERN_EN));
    }

    @Test
    public void checkWordComboUATest() {
        Assert.assertTrue(inputNoteController.check("Сан Франциско", PatternContainer.WORD_COMBO_PATTERN_UA));
    }

    @Test
    public void checkNotWordComboUATest() {
        Assert.assertFalse(inputNoteController.check("якась дуже довга назва занадто довга",
                PatternContainer.WORD_COMBO_PATTERN_UA));
    }

    @Test
    public void checkNumberTest() {
        Assert.assertTrue(inputNoteController.check("221", PatternContainer.NUMBER_PATTERN));
    }

    @Test
    public void checkNotNumberTest() {
        Assert.assertFalse(inputNoteController.check("023", PatternContainer.NUMBER_PATTERN));
    }

    @Test
    public void getCompoundNameTest() {
        Assert.assertEquals("Newton I.", Note.getCompoundName("Newton", "Isaac"));
    }

    @Test
    public void getNameTest() {
        inputNoteController.setScannerToFile("src\\test\\name.txt");
        Assert.assertEquals("Isaac", inputNoteController.inputValue(inputNoteController.FIRST_NAME,
                PatternContainer.WORD_PATTERN_EN));
    }

    @Test
    public void getNicknameTest() {
        inputNoteController.setScannerToFile("src\\test\\nickname.txt");
        Assert.assertEquals("sir_isaac43", inputNoteController.inputValue(inputNoteController.LOGIN,
                PatternContainer.NICKNAME_PATTERN));
    }

    @Test
    public void getGroupTest() {
        inputNoteController.setScannerToFile("src\\test\\group.txt");
        Assert.assertTrue("FAMILY".equalsIgnoreCase(
                inputNoteController.inputValue(inputNoteController.GROUP, PatternContainer.GROUP_PATTERN
                )));
    }

    @Test
    public void getAddressTest() {
        inputNoteController.setScannerToFile("src\\test\\address.txt");
        Assert.assertNotNull(inputNoteController.getAddress());
    }

    @Test
    public void addNoteDoesSucceed() {
        Assert.assertNotNull(inputNoteController.getNote());
    }

}
