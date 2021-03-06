package ru.unn.agile.BitField.viewmodel;

import javafx.beans.property.*;
import ru.unn.agile.BitField.model.BitField;


public class ViewModel {
    private static final int LENGTH_BIT_FIELD = 8;

    private BitField bitFieldA = new BitField(LENGTH_BIT_FIELD);
    private BitField bitFieldB = new BitField(LENGTH_BIT_FIELD);
    private BitField bitFieldResult = new BitField(LENGTH_BIT_FIELD);

    private final StringProperty bitFieldStringA = new SimpleStringProperty();
    private final StringProperty chooseBitA = new SimpleStringProperty();
    private final StringProperty textErrorA = new SimpleStringProperty();

    private final StringProperty bitFieldStringB = new SimpleStringProperty();
    private final StringProperty chooseBitB = new SimpleStringProperty();
    private final StringProperty textErrorB = new SimpleStringProperty();

    private final StringProperty resultText = new SimpleStringProperty();

    public ViewModel() {
        bitFieldStringA.set(bitFieldA.toString());
        bitFieldStringB.set(bitFieldB.toString());
    }

    // Methods

    public void setBitFieldStringA(final String bitField) {
        if (!canInputBitFieldCur(bitField, textErrorA)) {
            return;
        }

        String correctBitField = correctionBitField(bitField);

        bitFieldA = BitField.fromString(correctBitField);
        bitFieldStringA.set(bitFieldA.toString());
    }

    public void setBitFieldStringB(final String bitField) {
        if (!canInputBitFieldCur(bitField, textErrorB)) {
            return;
        }

        String correctBitField = correctionBitField(bitField);

        bitFieldB = BitField.fromString(correctBitField);
        bitFieldStringB.set(bitFieldB.toString());
    }

    public void setBitFieldBitA(final String numOfBit) {
        setBitFieldBitCur(numOfBit, bitFieldA, bitFieldStringA);
    }

    public void setBitFieldBitB(final String numOfBit) {
        setBitFieldBitCur(numOfBit, bitFieldB, bitFieldStringB);
    }

    public void clearBitFieldBitA(final String numOfBit) {
        clearBitFieldBitCur(numOfBit, bitFieldA, bitFieldStringA);
    }

    public void clearBitFieldBitB(final String numOfBit) {
        clearBitFieldBitCur(numOfBit, bitFieldB, bitFieldStringB);
    }

    public void getBitFieldBitA(final String numOfBit) {
        getBitFieldBitCur(numOfBit, bitFieldA, chooseBitA);
    }

    public void getBitFieldBitB(final String numOfBit) {
        getBitFieldBitCur(numOfBit, bitFieldB, chooseBitB);
    }

    public void logicNotA() {
        BitField field = new BitField(bitFieldA);
        bitFieldA = field.not();

        bitFieldStringA.set(bitFieldA.toString());
    }

    public void logicNotB() {
        BitField field = new BitField(bitFieldB);
        bitFieldB = field.not();

        bitFieldStringB.set(bitFieldB.toString());
    }

    private String correctionBitField(final String bitField) {
        String correctBitField = new String();
        int lenBitField = bitField.length();

        for (int i = 0; i < LENGTH_BIT_FIELD - lenBitField; i++) {
            correctBitField += "0";
        }

        correctBitField += bitField;
        return correctBitField;
    }

    private boolean canInputBitFieldCur(final String bitField, final StringProperty textErrorCur) {
        if ("".equals(bitField)) {
            textErrorCur.set("Text Field is Empty");
            return false;
        }

        if (!bitField.matches("[01]+")) {
            textErrorCur.set("Only 0 and 1");
            return false;
        }

        int lenBitField = bitField.length();
        if (lenBitField > LENGTH_BIT_FIELD) {
            textErrorCur.set("Length of BitField must be less or equal 8");
            return false;
        }

        textErrorCur.set("");
        return true;
    }

    public void setBitFieldBitCur(final String numOfBit, final BitField bitFieldCur,
                                  final StringProperty bitFieldStringCur) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        bitFieldCur.setBit(numOfBitInt);

        bitFieldStringCur.set(bitFieldCur.toString());
    }

    public void clearBitFieldBitCur(final String numOfBit, final BitField bitFieldCur,
                                    final StringProperty bitFieldStringCur) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        bitFieldCur.clrBit(numOfBitInt);

        bitFieldStringCur.set(bitFieldCur.toString());
    }

    public void getBitFieldBitCur(final String numOfBit, final BitField bitFieldCur,
                                  final StringProperty chooseBitCur) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        int chooseBit = bitFieldCur.getBit(numOfBitInt);

        chooseBitCur.set(Integer.toString(chooseBit));
    }

    public void logicAAndB() {
        bitFieldResult = new BitField(bitFieldA);
        bitFieldResult = bitFieldA.and(bitFieldB);

        resultText.set(bitFieldResult.toString());
    }

    public void logicAOrB() {
        bitFieldResult = new BitField(bitFieldA);
        bitFieldResult = bitFieldA.or(bitFieldB);

        resultText.set(bitFieldResult.toString());
    }

    public void logicAXorB() {
        bitFieldResult = new BitField(bitFieldA);
        bitFieldResult = bitFieldA.xor(bitFieldB);

        resultText.set(bitFieldResult.toString());
    }

    // Property Getters Fields

    public StringProperty bitFieldStringAProperty() {
        return bitFieldStringA;
    }

    public StringProperty bitFieldStringBProperty() {
        return bitFieldStringB;
    }

    public StringProperty chooseBitAProperty() {
        return chooseBitA;
    }

    public StringProperty chooseBitBProperty() {
        return chooseBitB;
    }

    public StringProperty textErrorAProperty() {
        return textErrorA;
    }

    public StringProperty textErrorBProperty() {
        return textErrorB;
    }

    public StringProperty resultTextProperty() {
        return resultText;
    }

    // Getters Fields

    public final String getBitFieldStringA() {
        return bitFieldStringA.get();
    }

    public final String getBitFieldStringB() {
        return bitFieldStringB.get();
    }

    public final String getChooseBitA() {
        return chooseBitA.get();
    }

    public final String getChooseBitB() {
        return chooseBitB.get();
    }

    public final String getTextErrorA() {
        return textErrorA.get();
    }

    public final String getTextErrorB() {
        return textErrorB.get();
    }

    public final String getResultText() {
        return resultText.get();
    }
}

