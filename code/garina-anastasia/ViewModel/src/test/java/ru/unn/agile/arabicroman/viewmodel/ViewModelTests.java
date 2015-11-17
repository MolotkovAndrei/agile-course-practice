package ru.unn.agile.arabicroman.viewmodel;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ru.unn.agile.arabicroman.viewmodel.ArabicRomanConverterViewModel;

public class ViewModelTests {
    private ArabicRomanConverterViewModel viewModel;


    @Before
    public void setUp() {
        viewModel = new ArabicRomanConverterViewModel();
    }

    @Test
    public void canSetDefaultValues() {
        assertFalse(viewModel.isConvertButtonEnabled());
        assertTrue(viewModel.isConvertedNumberArabic());
    }

    @Test
    public void whenNumberEnteredConvertButtonEnabled() {
        viewModel.setInputNumber("1");

        assertTrue(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void whenArabicNumberEmptyConvertButtonDisabled() {
        viewModel.setInputNumber("1");
        viewModel.setInputNumber("");

        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canConvertValidArabicNumber() {
        viewModel.setInputNumber("1");
        viewModel.convert();

        assertEquals("I", viewModel.getOutputNumber());
    }

    @Test
    public void canChangeConvertingDirection() {
        viewModel.reverseConvertingDirection();

        assertFalse(viewModel.isConvertedNumberArabic());
    }

    @Test
    public void canChangeConvertingDirectionTwice() {
        viewModel.reverseConvertingDirection();
        viewModel.reverseConvertingDirection();

        assertTrue(viewModel.isConvertedNumberArabic());
    }

    @Test
    public void whenRomanNumberEmptyConvertButtonDisabled() {
        viewModel.reverseConvertingDirection();
        viewModel.setInputNumber("X");
        viewModel.setInputNumber("");

        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void convertButtonEnabledWhenRomanNumberNotEmpty() {
        viewModel.reverseConvertingDirection();
        viewModel.setInputNumber("X");

        assertTrue(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canConvertValidRomanNumber() {
        viewModel.reverseConvertingDirection();
        viewModel.setInputNumber("X");
        viewModel.convert();

        assertEquals("10", viewModel.getOutputNumber());
    }



}
