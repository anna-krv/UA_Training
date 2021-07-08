package test;

import app.controller.*;
import app.model.Model;
import app.model.entity.Note;
import app.view.TextConstant;
import app.view.View;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestApp {
    private static NoteBuilderFromInput noteBuilder;
    private static UtilityController utilityController;
    private static Model model;
    private static View view;

    @BeforeClass
    public static void setUp() throws FileNotFoundException {
        model = new Model();
        view = new View();
        utilityController = new UtilityController(view, new Scanner(new File("src//test//contact.txt")));
        noteBuilder = new NoteBuilderFromInput(view, new Scanner(new File("src//test//contact.txt")));
    }

    @Test
    public void checkWordTest() {
        Assert.assertTrue(utilityController.check("Anna-Maria", PatternContainer.WORD_PATTERN_EN));
    }

    @Test
    public void checkNotWordTest() {
        Assert.assertFalse(utilityController.check("I_s_aa_c", PatternContainer.WORD_PATTERN_EN));
    }

    @Test
    public void checkWordUATest() {
        Assert.assertTrue(utilityController.check("Олександра", PatternContainer.WORD_PATTERN_UA));
    }

    @Test
    public void checkNotWordUATest() {
        Assert.assertFalse(utilityController.check("!Валентина", PatternContainer.WORD_PATTERN_UA));
    }


    @Test
    public void checkNicknameTest() {
        Assert.assertTrue(utilityController.check("sir_isaac43", PatternContainer.NICKNAME_PATTERN));
    }

    @Test
    public void checkNotNicknameTest() {
        Assert.assertFalse(utilityController.check("wrong*symbol", PatternContainer.NICKNAME_PATTERN));
    }


    @Test
    public void checkCommentTest() {
        Assert.assertTrue(utilityController.check("This is a simple comment.", PatternContainer.COMMENT_PATTERN));
    }

    @Test
    public void checkCommentUATest() {
        Assert.assertTrue(utilityController.check("Це простий коментар.", PatternContainer.COMMENT_PATTERN));
    }

    @Test
    public void checkNotCommentTest() {
        Assert.assertFalse(utilityController.check("x".repeat(501),
                PatternContainer.COMMENT_PATTERN));
    }

    @Test
    public void checkGroupTest() {
        for (String groupName : new String[]{"Family", "Friends", "Coworkers", "other"}) {
            Assert.assertTrue(utilityController.check(groupName, PatternContainer.GROUP_PATTERN));
        }
    }

    @Test
    public void checkNotGroupTest() {
        Assert.assertFalse(utilityController.check("FamilyAndOthers", PatternContainer.GROUP_PATTERN));
    }

    @Test
    public void checkPhoneTest() {
        Assert.assertTrue(utilityController.check("+380981111111", PatternContainer.PHONE_PATTERN));
    }

    @Test
    public void checkPhoneLongTest() {
        Assert.assertFalse(utilityController.check("+380123456789123456789", PatternContainer.PHONE_PATTERN));
    }

    @Test
    public void checkNotPhoneTest() {
        Assert.assertFalse(utilityController.check("+380 77 989 6 54", PatternContainer.PHONE_PATTERN));
    }

    @Test
    public void checkSecondPhoneTest() {
        Assert.assertTrue(utilityController.check("", PatternContainer.SECOND_PHONE_PATTERN));
    }

    @Test
    public void checkNotSecondPhoneTest() {
        Assert.assertFalse(utilityController.check("+380 77 989 6 54", PatternContainer.SECOND_PHONE_PATTERN));
    }

    @Test
    public void checkEmailTest() {
        Assert.assertTrue(utilityController.check("isaac-newton43@cam.ac.uk", PatternContainer.EMAIL_PATTERN));
    }

    @Test
    public void checkNotEmailTest() {
        Assert.assertFalse(utilityController.check("isaac-newton@cam", PatternContainer.EMAIL_PATTERN));
    }

    @Test
    public void checkSkypeTest() {
        Assert.assertTrue(utilityController.check("isaac2512newton", PatternContainer.SKYPE_PATTERN));
    }

    @Test
    public void checkNotSkypeTest() {
        Assert.assertFalse(utilityController.check("i*am*isaac*", PatternContainer.SKYPE_PATTERN));
    }

    @Test
    public void checkPostalCodeTest() {
        Assert.assertTrue(utilityController.check("AB3 CD4", PatternContainer.POSTAL_CODE_PATTERN));
    }

    @Test
    public void checkNotPostalCodeTest() {
        Assert.assertFalse(utilityController.check("(595 35)", PatternContainer.POSTAL_CODE_PATTERN));
    }

    @Test
    public void checkWordComboENTest() {
        Assert.assertTrue(utilityController.check("San  Francisco", PatternContainer.WORD_COMBO_PATTERN_EN));
    }

    @Test
    public void checkNotWordComboENTest() {
        Assert.assertFalse(utilityController.check("some city from far far away \t too long to be a name",
                PatternContainer.WORD_COMBO_PATTERN_EN));
    }

    @Test
    public void checkWordComboUATest() {
        Assert.assertTrue(utilityController.check("Сан Франциско", PatternContainer.WORD_COMBO_PATTERN_UA));
    }

    @Test
    public void checkNotWordComboUATest() {
        Assert.assertFalse(utilityController.check("якась дуже довга назва занадто довга",
                PatternContainer.WORD_COMBO_PATTERN_UA));
    }

    @Test
    public void checkNumberTest() {
        Assert.assertTrue(utilityController.check("221", PatternContainer.NUMBER_PATTERN));
    }

    @Test
    public void checkNotNumberTest() {
        Assert.assertFalse(utilityController.check("023", PatternContainer.NUMBER_PATTERN));
    }

    @Test
    public void getCompoundNameTest() {
        Assert.assertEquals("Newton I.",
                Note.getCompoundName("Newton", "Isaac"));
    }

    @Test
    public void getNameTest() {
        utilityController.setScannerToFile("src\\test\\name.txt");
        Assert.assertEquals("Isaac", utilityController.inputValue(TextConstant.FIRST_NAME,
                PatternContainer.WORD_PATTERN_EN));
    }

    @Test
    public void getNicknameTest() {
        utilityController.setScannerToFile("src\\test\\nickname.txt");
        Assert.assertEquals("sir_isaac43", utilityController.inputValue(TextConstant.LOGIN,
                PatternContainer.NICKNAME_PATTERN));
    }

    @Test
    public void getGroupTest() {
        utilityController.setScannerToFile("src\\test\\group.txt");
        Assert.assertTrue("FAMILY".equalsIgnoreCase(
                utilityController.inputValue(TextConstant.GROUP, PatternContainer.GROUP_PATTERN
                )));
    }

    @Test
    public void getNoteNotNullTest() {
        noteBuilder.inputNote();
        Assert.assertNotNull(noteBuilder.getNote());
    }
}
