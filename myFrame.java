/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebrablocks;

/**
 *
 * @author group3
 */
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionFormat;

public class myFrame extends javax.swing.JFrame {

    private int x, y;
    private int operation = 0; // 0 for empty, 1 for add, 2 for multiply and 3 for division.
    private int operation2 = 0; // 0 for empty, 1 for add, 2 for multiply and 3 for division.
    int variable = 0;           // Counts the number of variables in the user input.
    boolean operationChecked = false; // It lets us know when an operation check box is checked.
    boolean isFraction;     // It lets us know if user input has a fraction.
    private int aFrac = 0; // Counter to see if / was entered twice in block 1.
    private int bFrac = 0; // Counter to see if / was entered twice in block 2.
    private int cFrac = 0; // Counter to see if / was entered twice in block 3.
    private int one = 1;
    private int two = 2;
    private int three = 3;
    private int four = 4;
    private int positionOne = 20;
    private int positionTwo = 278;
    private int positionThree = 536;
    private int positionFour = 794;
    private int positionY = 48;
    private boolean validMove = false;
    private int coefficient = 0;
    private String theOperation = "";
    private String theOperation2 = "";
    private move moveMe;
    private swap swapMe;

    /**
     * Creates new form myFrame
     */
    public myFrame() {

        initComponents();
        block1.setTempX(positionOne);
        block1.setTempY(positionY);
        block2.setTempX(positionTwo);
        block2.setTempY(positionY);
        block3.setTempX(positionThree);
        block3.setTempY(positionY);
        block4.setTempX(positionFour);
        block4.setTempY(positionY);
        block1.setMyPosition(positionOne);
        block2.setMyPosition(positionTwo);
        block3.setMyPosition(positionThree);
        block4.setMyPosition(positionFour);
        block1.setBlockNum(one);
        block2.setBlockNum(two);
        block3.setBlockNum(three);
        block4.setBlockNum(four);
        isFraction = false;
        block1.initialX = block1.getX();
        block1.initialY = block1.getY();
        block2.initialX = block2.getX();
        block2.initialY = block2.getY();
        block3.initialX = block3.getX();
        block3.initialY = block3.getY();
        block4.initialX = block4.getX();
        block4.initialY = block4.getY();
        block1.initialColor = block1.getBackground();
        block2.initialColor = block2.getBackground();
        block3.initialColor = block3.getBackground();
        block4.initialColor = block4.getBackground();
        block1.setIsLeft(true);
        block2.setIsLeft(true);
        block3.setIsLeft(false);
        block4.setIsLeft(false);
        block4.setVisible(false);
        // block4.setEnabled(false);
        textFieldB1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldB1.setText(null); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldB1.getText().isEmpty()) {
                    textFieldB1.setText("Block1"); // fill the text field  with Block1 when it loses focus and text field is left blank
                }
            }
        });
        textFieldB2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldB2.setText(null); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldB2.getText().isEmpty()) {
                    textFieldB2.setText("Block2"); // Fill the text field with Block2 when it loses focus and text field is left blank
                }
            }
        });
        textFieldB3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldB3.setText(null); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldB3.getText().isEmpty()) {
                    textFieldB3.setText("Block3"); // Fill the text field with Block3 when it loses focus and text field is left blank
                }
            }
        });
        //textFieldB3.setVisible(false); // makes the text field invisible. The input is handled by an input message box;
        textFieldB3.setEnabled(false);
    }

    /**
     * ***method to check if the drag drop on top of a block is valid
     *
     ****
     * @param one
     * @param two
     * @param oper
     */
    /*class move2 extends SwingWorker<Void, Void> {
         String test = "0";
         protected Block one;
         protected Block two;
         protected String oper;

        @Override
        protected Void doInBackground() throws Exception {
            block4.setVisible(true);
            jLabelB4.setText(jLabelB2.getText());
            block2.setVisible(false);
            jLabelOperator2.setText("+");
            jLabelOperator.setText("");
            return null;
        }

        @Override
        protected void done() {
            swap(one, block1);
            if (two.getBlockNum() == 3 && two.getMyPosition() == positionThree){
                if (one.getBlockNum() == 2) {
                    if (one.getMyPosition() == positionTwo){
                        swap(one, block1);
                    }
                }
            }
        }  
    }*/
    
    public class move extends SwingWorker<Integer, Void> {
    protected Block one;
    protected Block two;
    protected String sOperation;
    protected String sOperation2;

    @Override
    protected synchronized Integer doInBackground() throws Exception {
        String value = "";
        String oTemp = "";
        String sTemp = "";
        Fraction fraction;
        FractionFormat format = new FractionFormat();
        int iNeg = -1;
        int temp = 0;
        boolean isNeg = false;
        boolean becameFrac = false;
        Integer toSwap = 0;
        String evaluator = "";
        int numFrac = 0;
        int denoFrac = 1;
        String updateOperation = "";

        if (one.getImAVariable()) {
            // code will change if we decide to add coefficient.
        } else {
            if (one.isFrac) {
                if (one.getNumerator() > -1 || one.getDenominator() > -1) {
                    isNeg = false;
                } else {
                    isNeg = true;
                }
            } else {
                if (one.myValue > -1) {
                    isNeg = false;
                } else {
                    isNeg = true;
                }
            }
        }

        if (one.isFrac) {
            if (jLabelOperator2.getText() == "") {
                evaluator = sOperation;
            }
            else if (jLabelOperator.getText() == "") {
                evaluator = sOperation2;
            }
            switch (evaluator) {
                case "add":
                    if (isNeg) {
                        temp = one.getNumerator() * iNeg;
                        fraction = new Fraction(temp, one.getDenominator());
                        value = format.format(fraction);
                    }
                    else {
                        temp = one.getNumerator() * iNeg;
                        fraction = new Fraction(temp, one.getDenominator());
                        value = format.format(fraction);
                    }
                    oTemp = "+";
                    updateOperation = "add";
                    break;
                case "divide":
                    if (one.getMyPosition() == positionTwo || one.getMyPosition() == positionFour) {
                        fraction = new Fraction(one.getNumerator(), one.getDenominator());
                        value = format.format(fraction);
                    } else {
                        fraction = new Fraction(one.getDenominator(), one.getNumerator());
                        value = format.format(fraction);
                    }
                    oTemp = "*";
                    updateOperation = "multiply";
                    break;
                case "multiply":
                    fraction = new Fraction(one.getDenominator(), one.getNumerator());
                    value = format.format(fraction);
                    oTemp = "*";
                    updateOperation = "multiply";
                    break;
            }
        } else {
            if (jLabelOperator2.getText().isEmpty()) {
                evaluator = sOperation;
            }
            if (jLabelOperator.getText().isEmpty()) {
                evaluator = sOperation2;
            }
            switch (evaluator) {
                case "add":
                    if (isNeg) {
                        temp = one.getMyValue() * iNeg;
                        sTemp = Integer.toString(temp);
                        value = sTemp;
                    }
                    else {
                        temp = one.getMyValue() * iNeg;
                        sTemp = Integer.toString(temp);
                        value = sTemp;
                    }
                    oTemp = "+";
                    updateOperation = "add";
                    break;
                case "divide":
                    if (one.getMyPosition() == positionTwo || one.getMyPosition() == positionFour) {
                        temp = one.getMyValue();
                        sTemp = Integer.toString(temp);
                        value = sTemp;
                    } else {
                        temp = one.getMyValue();
                        fraction = new Fraction(1, temp);
                        value = format.format(fraction);
                        becameFrac = true;
                    }
                    oTemp = "*";
                    updateOperation = "multiply";
                    break;
                case "multiply":
                    temp = one.getMyValue();
                    fraction = new Fraction(1, temp);
                    becameFrac = true;
                    oTemp = "*";
                    updateOperation = "multiply";
                    break;
            }
        }
        
        if (one.getBlockNum() == 1) {
            if (two.getBlockNum() == 3 && two.getMyPosition() == positionThree) {
                block4.setBackground(block1.getBackground());
                jLabelB4.setText(value);
                block4.setVisible(true);
                jLabelOperator.setText("");
                jLabelOperator2.setText(oTemp);
                theOperation2 = updateOperation;
                theOperation = "";
                one.setVisible(false);
                if (becameFrac) {
                    
                }
                else {
                    block4.setMyValue(temp);
                }
            }
            else if (two.getBlockNum() == 4 && two.getMyPosition() == positionThree) {
                block3.setBackground(block1.getBackground());
                jLabelB3.setText(value);
                block3.setVisible(true);
                jLabelOperator.setText("");
                jLabelOperator2.setText(oTemp);
                theOperation2 = updateOperation;
                theOperation = "";
                one.setVisible(false);
                if (becameFrac) {
                    
                }
                else {
                    block3.setMyValue(temp);
                }
            }
            if (one.getMyPosition() == positionTwo) {
                toSwap = 1;
            }
        }
        else if (one.getBlockNum() == 2) {
            if (two.getBlockNum() == 3 && two.getMyPosition() == positionThree) {
                block4.setBackground(block2.getBackground());
                jLabelB4.setText(value);
                block4.setVisible(true);
                //block4.setFocusable(false);
                jLabelOperator.setText("");
                jLabelOperator2.setText(oTemp);
                theOperation2 = updateOperation;
                theOperation = "";
                 one.setVisible(false);
                 if (becameFrac) {
                    
                }
                else {
                    block4.setMyValue(temp);
                }
                 //System.out.println(SwingUtilities.isEventDispatchThread() + " doInBackground");     for testing
            }
            else if (two.getBlockNum() == 4 && two.getMyPosition() == positionThree) {
                block3.setBackground(block2.getBackground());
                jLabelB3.setText(value);
                block3.setVisible(true);
                jLabelOperator.setText("");
                jLabelOperator2.setText(oTemp);
                theOperation2 = updateOperation;
                theOperation = "";
                one.setVisible(false);
                if (becameFrac) {
                    
                }
                else {
                    block3.setMyValue(temp);
                }
            }
            if (one.getMyPosition() == positionTwo) {
                toSwap = 1;
            }
        }
        else if (one.getBlockNum() == 3) {
            if (two.getBlockNum() == 2 && two.getMyPosition() == positionTwo) {
                block1.setBackground(block3.getBackground());
                jLabelB1.setText(value);
                block1.setVisible(true);
                jLabelOperator.setText(oTemp);
                jLabelOperator2.setText("");
                theOperation = updateOperation;
                theOperation2 = "";
                one.setVisible(false);
                if (becameFrac) {
                    
                }
                else {
                    block1.setMyValue(temp);
                }
            }
            else if (two.getBlockNum() == 1 && two.getMyPosition() == positionTwo) {
                block2.setBackground(block3.getBackground());
                jLabelB2.setText(value);
                block2.setVisible(true);
                jLabelOperator.setText(oTemp);
                jLabelOperator2.setText("");
                theOperation = updateOperation;
                theOperation2 = "";
                one.setVisible(false);
                if (becameFrac) {
                    
                }
                else {
                    block2.setMyValue(temp);
                }
            }
            if (one.getMyPosition() == positionThree) {
                toSwap = 3;
            }
        }
        else if (one.getBlockNum() == 4) {
            if (two.getBlockNum() == 2 && two.getMyPosition() == positionTwo) {
                block1.setBackground(block4.getBackground());
                jLabelB1.setText(value);
                block1.setVisible(true);
                jLabelOperator.setText(oTemp);
                jLabelOperator2.setText("");
                theOperation = updateOperation;
                theOperation2 = "";
                one.setVisible(false);
                if (becameFrac) {
                    
                }
                else {
                    block1.setMyValue(temp);
                }
            }
            else if (two.getBlockNum() == 1 && two.getMyPosition() == positionTwo) {
                block2.setBackground(block4.getBackground());
                jLabelB2.setText(value);
                block2.setVisible(true);
                jLabelOperator.setText(oTemp);
                jLabelOperator2.setText("");
                theOperation = updateOperation;
                theOperation2 = "";
                one.setVisible(false);
                if (becameFrac) {
                    
                }
                else {
                    block2.setMyValue(temp);
                }
            }
            if (one.getMyPosition() == positionThree) {
                toSwap = 3;
            }
        }
        return toSwap;
    }

    @Override
    protected synchronized void done() {
        try {
            Integer swapThese = get();
            if (swapThese == 1) {
                //swap(block1, block2);
                swapMe = new swap();
                swapMe.blockOne = block1;
                swapMe.blockTwo = block2;
                swapMe.execute();
            }
            if (swapThese == 3) {
                //swap(block3, block4);
                swapMe = new swap();
                swapMe.blockOne = block3;
                swapMe.blockTwo = block4;
                swapMe.execute();
            }
        } catch (InterruptedException ex) {
            
        } catch (ExecutionException ex) {
            
        }
        //System.out.println(SwingUtilities.isEventDispatchThread() + " done()");    for testing
    }
        
    }

    public boolean isValidMove(Block first, Block second, int tp, int btm) {
        boolean valid = false;
        int top = tp;
        int bottom = btm;
        if (first.isLeft() == second.isLeft()) {
            if (first.getIsFrac() != second.getIsFrac()) {
                valid = false;
            } else {
                valid = true;
            }
        } else if (first.isLeft() != second.isLeft()) {
            if (!block4.isVisible() || !block3.isVisible()) {
                if (top == 3 || top == 4) {
                    valid = false;
                } else {
                    if (first.imAVariable) {
                        if (operation == 3 && first.getMyPosition() == 2) {
                            valid = true;
                        } else {
                            valid = false;
                        }
                    } else {
                        valid = true;
                    }
                }

            } else if (!block1.isVisible() || !block2.isVisible()) {
                if (top == 2 || top == 1) {
                    valid = false;
                } else {
                    if (first.imAVariable) {
                        if (operation2 == 3 && first.getMyPosition() == 4) {
                            valid = true;
                        } else {
                            valid = false;
                        }
                    } else {
                        valid = true;
                    }
                }
            }
        }

        return valid;
    }

     class swap extends SwingWorker <Void, Void> {
        protected Block blockOne;
        protected Block blockTwo;
    
        @Override
        protected synchronized Void doInBackground() throws Exception {
            if (blockOne.getMyPosition() == positionOne) {
            blockOne.setLocation(positionTwo, positionY);
            blockOne.setMyPosition(positionTwo);
            blockOne.setTempX(positionTwo);
            blockOne.setTempY(positionY);
            blockTwo.setLocation(positionOne, positionY);
            blockTwo.setMyPosition(positionOne);
            blockTwo.setTempX(positionOne);
            blockTwo.setTempY(positionY);
        } else if (blockOne.getMyPosition() == positionTwo) {
            blockOne.setLocation(positionOne, positionY);
            blockOne.setMyPosition(positionOne);
            blockOne.setTempX(positionOne);
            blockOne.setTempY(positionY);
            blockTwo.setLocation(positionTwo, positionY);
            blockTwo.setMyPosition(positionTwo);
            blockTwo.setTempX(positionTwo);
            blockTwo.setTempY(positionY);
        } else if (blockOne.getMyPosition() == positionThree) {
            blockOne.setLocation(positionFour, positionY);
            blockOne.setMyPosition(positionFour);
            blockOne.setTempX(positionFour);
            blockOne.setTempY(positionY);
            blockTwo.setLocation(positionThree, positionY);
            blockTwo.setMyPosition(positionThree);
            blockTwo.setTempX(positionThree);
            blockTwo.setTempY(positionY);
        } else if (blockOne.getMyPosition() == positionFour) {
            blockOne.setLocation(positionThree, positionY);
            blockOne.setMyPosition(positionThree);
            blockOne.setTempX(positionThree);
            blockOne.setTempY(positionY);
            blockTwo.setLocation(positionFour, positionY);
            blockTwo.setMyPosition(positionFour);
            blockTwo.setTempX(positionFour);
            blockTwo.setTempY(positionY);
        }
            return null;
        }
    }

    public static synchronized String myFraction(Block one, Block two, String operation) {
        Fraction fracOne = new Fraction(one.numerator, one.denominator);
        Fraction fracTwo = new Fraction(two.numerator, two.denominator);
        Fraction fracAnswer;
        FractionFormat format = new FractionFormat();
        String value = "";

        switch (operation) {
            case "add":
                fracAnswer = fracOne.add(fracTwo);
                value = format.format(fracAnswer);
                break;
            case "muiltiply":
                fracAnswer = fracOne.multiply(fracTwo);
                value = format.format(fracAnswer);
                break;
            case "divide":
                fracAnswer = fracOne.divide(fracTwo);
                value = format.format(fracAnswer);
                break;
        }

        return value;
    }

    public static String[] splitNum(String nAndD) {
        String[] nd = nAndD.split("/", 2);
        return nd;
    }

    public static String fraction() {
        String value = "";
        return value;
    }

    public static int add(String sNum1, String sNum2) {
        int value = 0;
        int num1 = 0;
        int num2 = 0;
        num1 = Integer.parseInt(sNum1);
        num2 = Integer.parseInt(sNum2);
        value = num1 + num2;
        return value;
    }

    public static int multiply(String sNum1, String sNum2) {
        int value = 0;
        int num1 = 0;
        int num2 = 0;
        num1 = Integer.parseInt(sNum1);
        num2 = Integer.parseInt(sNum2);
        value = num1 * num2;
        return value;
    }

    /* public static String divide(String sNum1, String sNum2) {
        String value = "";
        String deNum = sNum1;
        String nuNum = sNum2;
        //boolean isDeci2 = false;
        value = deNum + "/" + nuNum;
        return value;
    }*/
    public synchronized void reset() {
        theOperation = "";
        theOperation2 = "";
        block1.setMyVariableValue("");
        block2.setMyVariableValue("");
        block3.setMyVariableValue("");
        block4.setMyVariableValue("");
        block1.setImAVariable(false);
        block2.setImAVariable(false);
        block3.setImAVariable(false);
        block4.setImAVariable(false);
        validMove = false;
        aFrac = 0;
        bFrac = 0;
        cFrac = 0;
        isFraction = false;
        variable = 0;
        operation = 0;
        operation2 = 0;
        operationChecked = false;
        jLabelOperator.setText("");
        jLabelOperator2.setText("");
        block1.setBackground(block1.initialColor);
        block2.setBackground(block2.initialColor);
        block3.setBackground(block3.initialColor);
        jLabelB1.setText("Block1");
        jLabelB2.setText("Block2");
        jLabelB3.setText("Block3");
        jLabelB4.setText("Block4");
        jCheckBoxAdd.setSelected(false);
        jCheckBoxMultiply.setSelected(false);
        jCheckBoxDivide.setSelected(false);
        textFieldB1.setText("Block1");
        textFieldB2.setText("Block2");
        textFieldB3.setText("Block3");
        block1.setVisible(true);
        block2.setVisible(true);
        block3.setVisible(true);
        block4.setVisible(false);
        //block1.setEnabled(true);
        //block2.setEnabled(true);
        //block3.setEnabled(true);
        //block4.setEnabled(false);
        jCheckBoxAdd.setEnabled(true);
        jCheckBoxMultiply.setEnabled(true);
        jCheckBoxDivide.setEnabled(true);
        textFieldB1.setEnabled(true);
        textFieldB2.setEnabled(true);
        textFieldB3.setEnabled(false);
        jButtonEnter.setEnabled(true);
        block1.setIsFrac(false);
        block2.setIsFrac(false);
        block3.setIsFrac(false);
        block4.setIsFrac(false);
        block1.setLocation(positionOne, positionY);
        block2.setLocation(positionTwo, positionY);
        block3.setLocation(positionThree, positionY);
        block4.setLocation(positionFour, positionY);
        block1.setMyPosition(positionOne);
        block2.setMyPosition(positionTwo);
        block3.setMyPosition(positionThree);
        block4.setMyPosition(positionFour);
    }

    public synchronized boolean isValidInput(String[] arr) {
        int b1 = 0;
        int b2 = 1;
        int b3 = 2;
        int num;
        String sNum;
        boolean valid = true;
        int denom = 1;

        if (variable == 0) {
            try {                                       // checks for integers
                num = Integer.parseInt(arr[b1].toString());
                if (num == (int) num) {
                    block1.setMyValue(num);
                }

            } catch (NumberFormatException e) {
                char[] inputB1 = arr[b1].toCharArray();
                for (int i = 0; i < inputB1.length; i++) {
                    try {
                        sNum = Character.toString(inputB1[i]);
                        num = Integer.parseInt(sNum);
                        coefficient++;
                    } catch (NumberFormatException ex) {
                        variable++;
                        if (inputB1[i] == '/') {
                            aFrac++;
                            variable--;
                        }

                    }
                }
                if (variable > 1) {
                    valid = false;
                } else {
                    if (aFrac > 1) {
                        valid = false;
                    } else if (aFrac == 1 && variable < 1) {
                        String[] nd = splitNum(textFieldB1.getText());
                        int numerator = 0;
                        int denominator = 1;
                        if (nd[denominator] == "0") {
                            denom = 0;
                        } else {
                            block1.setNumerator(Integer.parseInt(nd[numerator]));
                            block1.setDenominator(Integer.parseInt(nd[denominator]));
                            block1.setIsFrac(true);
                            isFraction = true;
                            coefficient = 0;
                        }

                    } else {
                        valid = false;
                    }
                    if (coefficient == 0) {
                        block1.setImAVariable(true);
                        block1.setMyVariableValue(textFieldB1.getText());
                    } else {
                        valid = false;
                    }

                }

            }
            try {                                       // checks for integers
                num = Integer.parseInt(arr[b2].toString());
                if (num == (int) num) {
                    block2.setMyValue(num);
                }
            } catch (NumberFormatException e) {
                char[] inputB2 = arr[b2].toCharArray();
                for (int i = 0; i < inputB2.length; i++) {
                    try {
                        sNum = Character.toString(inputB2[i]);
                        num = Integer.parseInt(sNum);
                        coefficient++;
                    } catch (NumberFormatException ex) {
                        variable++;
                        if (inputB2[i] == '/') {
                            bFrac++;
                            variable--;
                        }
                    }
                }
                if (variable > 1) {
                    valid = false;
                } else {
                    if (bFrac > 1) {
                        valid = false;
                    } else if (bFrac == 1 && aFrac == 1 && variable < 1 || bFrac == 1 && aFrac < 1 && variable < 1) {
                        String[] nd = splitNum(textFieldB2.getText());
                        int numerator = 0;
                        int denominator = 1;
                        if (nd[denominator] == "0") {
                            denom = 0;
                        } else {
                            block2.setNumerator(Integer.parseInt(nd[numerator]));
                            block2.setDenominator(Integer.parseInt(nd[denominator]));
                            block2.setIsFrac(true);
                            isFraction = true;
                            coefficient = 0;
                        }
                    } else {
                        valid = false;
                    }
                    if (coefficient == 0) {
                        block2.setImAVariable(true);
                        block2.setMyVariableValue(textFieldB2.getText());
                    } else {
                        valid = false;
                    }

                }

            }
        } else if (variable == 1) {
            try {
                num = Integer.parseInt(arr[b3].toString());
                if (num == (int) num) {
                    block3.setMyValue(num);
                }
            } catch (NumberFormatException e) {
                //variable++;
                char[] cNum = textFieldB3.getText().toCharArray();
                for (int i = 0; i < textFieldB3.getText().length(); i++) {
                    if (cNum[i] == '/') {
                        cFrac++;
                        if (cNum[0] == '/' || cNum[cNum.length - 1] == '/') {
                            valid = false;
                            cFrac++;
                            break;
                        }
                    } else {
                        coefficient++;
                        valid = false;
                    }
                }
                if (cFrac > 1) {
                    valid = false;
                } else {
                    valid = true;
                }

            }
        }

        if (variable == 1 && cFrac > 1 || coefficient > 0 || denom == 0) {
            valid = false;
        } else if (variable > 1) {
            valid = false;
        } else if (variable == 1 && aFrac < 1 && bFrac < 1 && coefficient == 0) {
            valid = true;
        }

        return valid;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        textFieldB1 = new java.awt.TextField();
        textFieldB2 = new java.awt.TextField();
        textFieldB3 = new java.awt.TextField();
        jCheckBoxAdd = new javax.swing.JCheckBox();
        jCheckBoxMultiply = new javax.swing.JCheckBox();
        block1 = new algebrablocks.Block();
        jLabelB1 = new javax.swing.JLabel();
        block2 = new algebrablocks.Block();
        jLabelB2 = new javax.swing.JLabel();
        block3 = new algebrablocks.Block();
        jLabelB3 = new javax.swing.JLabel();
        blockEqualsSign = new algebrablocks.Block();
        jLabelEqualSign = new javax.swing.JLabel();
        blockOperatorSign = new algebrablocks.Block();
        jLabelOperator = new javax.swing.JLabel();
        blockOperatorSign2 = new algebrablocks.Block();
        jLabelOperator2 = new javax.swing.JLabel();
        block4 = new algebrablocks.Block();
        jLabelB4 = new javax.swing.JLabel();
        jCheckBoxDivide = new javax.swing.JCheckBox();
        jButtonEnter = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jLabelByGroup3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algebra Blocks");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 227, 954, 10));

        textFieldB1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textFieldB1.setMaximumSize(new java.awt.Dimension(52, 20));
        textFieldB1.setMinimumSize(new java.awt.Dimension(52, 20));
        textFieldB1.setText("Block1");
        getContentPane().add(textFieldB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 332, -1, -1));

        textFieldB2.setMaximumSize(new java.awt.Dimension(52, 20));
        textFieldB2.setMinimumSize(new java.awt.Dimension(52, 20));
        textFieldB2.setName(""); // NOI18N
        textFieldB2.setText("Block2");
        getContentPane().add(textFieldB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 332, -1, -1));

        textFieldB3.setMaximumSize(new java.awt.Dimension(52, 20));
        textFieldB3.setMinimumSize(new java.awt.Dimension(52, 20));
        textFieldB3.setText("Block3");
        getContentPane().add(textFieldB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 332, -1, -1));

        jCheckBoxAdd.setText("Add");
        jCheckBoxAdd.setToolTipText("");
        jCheckBoxAdd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxAddItemStateChanged(evt);
            }
        });
        jCheckBoxAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxAddMouseClicked(evt);
            }
        });
        getContentPane().add(jCheckBoxAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 271, -1, -1));

        jCheckBoxMultiply.setText("Multiply");
        jCheckBoxMultiply.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMultiplyItemStateChanged(evt);
            }
        });
        jCheckBoxMultiply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMultiplyMouseClicked(evt);
            }
        });
        getContentPane().add(jCheckBoxMultiply, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 271, -1, -1));

        block1.setBackground(new java.awt.Color(204, 255, 255));
        block1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        block1.setAutoscrolls(true);
        block1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                block1MouseDragged(evt);
            }
        });
        block1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                block1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                block1MouseReleased(evt);
            }
        });

        jLabelB1.setFont(new java.awt.Font("Tahoma", 0, 33)); // NOI18N
        jLabelB1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelB1.setText("Block1");

        javax.swing.GroupLayout block1Layout = new javax.swing.GroupLayout(block1);
        block1.setLayout(block1Layout);
        block1Layout.setHorizontalGroup(
            block1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelB1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        block1Layout.setVerticalGroup(
            block1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabelB1)
                .addGap(36, 36, 36))
        );

        getContentPane().add(block1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 48, -1, -1));

        block2.setBackground(new java.awt.Color(255, 204, 255));
        block2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        block2.setAutoscrolls(true);
        block2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                block2MouseDragged(evt);
            }
        });
        block2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                block2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                block2MouseReleased(evt);
            }
        });

        jLabelB2.setFont(new java.awt.Font("Tahoma", 0, 33)); // NOI18N
        jLabelB2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelB2.setText("Block2");

        javax.swing.GroupLayout block2Layout = new javax.swing.GroupLayout(block2);
        block2.setLayout(block2Layout);
        block2Layout.setHorizontalGroup(
            block2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelB2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        block2Layout.setVerticalGroup(
            block2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabelB2)
                .addGap(36, 36, 36))
        );

        getContentPane().add(block2, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 48, -1, -1));

        block3.setBackground(new java.awt.Color(204, 204, 255));
        block3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        block3.setAutoscrolls(true);
        block3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                block3MouseDragged(evt);
            }
        });
        block3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                block3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                block3MouseReleased(evt);
            }
        });

        jLabelB3.setFont(new java.awt.Font("Tahoma", 0, 33)); // NOI18N
        jLabelB3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelB3.setText("Block3");

        javax.swing.GroupLayout block3Layout = new javax.swing.GroupLayout(block3);
        block3.setLayout(block3Layout);
        block3Layout.setHorizontalGroup(
            block3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelB3, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        block3Layout.setVerticalGroup(
            block3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block3Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabelB3)
                .addGap(36, 36, 36))
        );

        getContentPane().add(block3, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 48, -1, -1));

        blockEqualsSign.setBackground(new java.awt.Color(0, 0, 0));
        blockEqualsSign.setToolTipText("");
        blockEqualsSign.setOpaque(false);

        jLabelEqualSign.setBackground(new java.awt.Color(0, 0, 0));
        jLabelEqualSign.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelEqualSign.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEqualSign.setText("=");
        jLabelEqualSign.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelEqualSign.setMaximumSize(new java.awt.Dimension(94, 40));
        jLabelEqualSign.setMinimumSize(new java.awt.Dimension(94, 40));
        jLabelEqualSign.setPreferredSize(new java.awt.Dimension(94, 40));

        javax.swing.GroupLayout blockEqualsSignLayout = new javax.swing.GroupLayout(blockEqualsSign);
        blockEqualsSign.setLayout(blockEqualsSignLayout);
        blockEqualsSignLayout.setHorizontalGroup(
            blockEqualsSignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blockEqualsSignLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEqualSign, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
        );
        blockEqualsSignLayout.setVerticalGroup(
            blockEqualsSignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blockEqualsSignLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabelEqualSign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        getContentPane().add(blockEqualsSign, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 48, -1, -1));

        blockOperatorSign.setBackground(new java.awt.Color(0, 0, 0));
        blockOperatorSign.setOpaque(false);

        jLabelOperator.setBackground(new java.awt.Color(0, 0, 0));
        jLabelOperator.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelOperator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOperator.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelOperator.setMaximumSize(new java.awt.Dimension(94, 40));
        jLabelOperator.setMinimumSize(new java.awt.Dimension(94, 40));
        jLabelOperator.setPreferredSize(new java.awt.Dimension(94, 40));

        javax.swing.GroupLayout blockOperatorSignLayout = new javax.swing.GroupLayout(blockOperatorSign);
        blockOperatorSign.setLayout(blockOperatorSignLayout);
        blockOperatorSignLayout.setHorizontalGroup(
            blockOperatorSignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blockOperatorSignLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelOperator, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        blockOperatorSignLayout.setVerticalGroup(
            blockOperatorSignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blockOperatorSignLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabelOperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        getContentPane().add(blockOperatorSign, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 48, -1, -1));

        blockOperatorSign2.setBackground(new java.awt.Color(0, 0, 0));
        blockOperatorSign2.setOpaque(false);

        jLabelOperator2.setBackground(new java.awt.Color(0, 0, 0));
        jLabelOperator2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelOperator2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOperator2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelOperator2.setMaximumSize(new java.awt.Dimension(94, 40));
        jLabelOperator2.setMinimumSize(new java.awt.Dimension(94, 40));
        jLabelOperator2.setPreferredSize(new java.awt.Dimension(94, 40));

        javax.swing.GroupLayout blockOperatorSign2Layout = new javax.swing.GroupLayout(blockOperatorSign2);
        blockOperatorSign2.setLayout(blockOperatorSign2Layout);
        blockOperatorSign2Layout.setHorizontalGroup(
            blockOperatorSign2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blockOperatorSign2Layout.createSequentialGroup()
                .addComponent(jLabelOperator2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addContainerGap())
        );
        blockOperatorSign2Layout.setVerticalGroup(
            blockOperatorSign2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blockOperatorSign2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabelOperator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        getContentPane().add(blockOperatorSign2, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 48, -1, -1));

        block4.setBackground(new java.awt.Color(204, 255, 204));
        block4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        block4.setAutoscrolls(true);
        block4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                block4MouseDragged(evt);
            }
        });
        block4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                block4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                block4MouseReleased(evt);
            }
        });

        jLabelB4.setFont(new java.awt.Font("Tahoma", 0, 33)); // NOI18N
        jLabelB4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelB4.setText("Block4");

        javax.swing.GroupLayout block4Layout = new javax.swing.GroupLayout(block4);
        block4.setLayout(block4Layout);
        block4Layout.setHorizontalGroup(
            block4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelB4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        block4Layout.setVerticalGroup(
            block4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block4Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabelB4)
                .addGap(36, 36, 36))
        );

        getContentPane().add(block4, new org.netbeans.lib.awtextra.AbsoluteConstraints(794, 48, -1, -1));

        jCheckBoxDivide.setText("Divide");
        jCheckBoxDivide.setToolTipText("");
        jCheckBoxDivide.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxDivideItemStateChanged(evt);
            }
        });
        jCheckBoxDivide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxDivideMouseClicked(evt);
            }
        });
        getContentPane().add(jCheckBoxDivide, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 271, -1, -1));

        jButtonEnter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonEnter.setText("Enter");
        jButtonEnter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEnterMouseClicked(evt);
            }
        });
        getContentPane().add(jButtonEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 322, 99, 42));

        jButtonReset.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonReset.setText("Reset");
        jButtonReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonResetMouseClicked(evt);
            }
        });
        getContentPane().add(jButtonReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 322, 99, 42));

        jLabelByGroup3.setText("By: group 3");
        getContentPane().add(jLabelByGroup3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 390, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void block1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_block1MousePressed

    private void block1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block1MouseDragged
        int newX = block1.getX() + evt.getX() - x;
        int newY = block1.getY() + evt.getY() - y;
        if (operation == 1 || operation == 2 || operation == 3) {
            block1.setLocation(newX, newY);
        }
    }//GEN-LAST:event_block1MouseDragged

    private void block2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_block2MousePressed

    private void block2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block2MouseDragged
        int newX = block2.getX() + evt.getX() - x;
        int newY = block2.getY() + evt.getY() - y;
        if (operation == 1 || operation == 2 || operation == 3) {
            block2.setLocation(newX, newY);
        }
    }//GEN-LAST:event_block2MouseDragged

    private void block3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block3MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_block3MousePressed

    private void block3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block3MouseDragged
        int newX = block3.getX() + evt.getX() - x;
        int newY = block3.getY() + evt.getY() - y;
        if (operation == 1 || operation == 2 || operation == 3) {
            block3.setLocation(newX, newY);
        }
    }//GEN-LAST:event_block3MouseDragged

    private void jCheckBoxAddItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxAddItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jLabelOperator.setText("+");
            jCheckBoxMultiply.setSelected(false);
            jCheckBoxDivide.setSelected(false);
            operation = 1;
            operationChecked = true;
            theOperation = "add";
        } else if (evt.getStateChange() == 0) {
            jLabelOperator.setText(" ");
            operation = 0;
            operationChecked = false;
        }
    }//GEN-LAST:event_jCheckBoxAddItemStateChanged

    private void jCheckBoxMultiplyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMultiplyItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jLabelOperator.setText("x");
            jCheckBoxAdd.setSelected(false);
            jCheckBoxDivide.setSelected(false);
            operation = 2;
            operationChecked = true;
            theOperation = "multiply";
        } else if (evt.getStateChange() == 0) {
            jLabelOperator.setText(" ");
            operation = 0;
            operationChecked = false;
        }
    }//GEN-LAST:event_jCheckBoxMultiplyItemStateChanged

    private void jCheckBoxAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxAddMouseClicked
        if (!jCheckBoxAdd.isSelected()) {
            jLabelOperator.setText(" ");
            operation = 0;
        }
    }//GEN-LAST:event_jCheckBoxAddMouseClicked

    private void jCheckBoxMultiplyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMultiplyMouseClicked
        if (!jCheckBoxMultiply.isSelected()) {
            jLabelOperator.setText(" ");
            operation = 0;
        }
    }//GEN-LAST:event_jCheckBoxMultiplyMouseClicked

    private void block1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block1MouseReleased
        if (operation == 1 || operation == 2 || operation == 3) {
            if (block1.getBounds().intersects(block2.getBounds())) {
                //swap(block1, block2);
                swapMe = new swap();
                swapMe.blockOne = block1;
                swapMe.blockTwo = block2;
                swapMe.execute();
                //JOptionPane.showMessageDialog(null, "merge between block1 and block2!"); for testing
            } else if (block1.getBounds().intersects(block3.getBounds())) {
                validMove = isValidMove(block1, block3, block1.getBlockNum(), block3.getBlockNum());
                if (validMove) {
                    //move(block1, block3, theOperation);
                    moveMe = new move();
                    moveMe.one = block1;
                    moveMe.two = block3;
                    moveMe.sOperation = theOperation;
                    moveMe.sOperation2 = theOperation2;
                    moveMe.execute();
                } else {
                    block1.setLocation(block1.getTempX(), block1.getTempY());
                    JOptionPane.showMessageDialog(null, "Don't move the variable, keep it isolated!");
                }

            } else if (block1.getBounds().intersects(block4.getBounds())) {
                validMove = isValidMove(block1, block4, block1.getBlockNum(), block4.getBlockNum());
                if (validMove) {
                    //move(block1, block4, theOperation);
                    moveMe = new move();
                    moveMe.one = block1;
                    moveMe.two = block4;
                    moveMe.sOperation = theOperation;
                    moveMe.sOperation2 = theOperation2;
                    moveMe.execute();
                } else {
                    block1.setLocation(block1.getTempX(), block1.getTempY());
                    JOptionPane.showMessageDialog(null, "Don't move the variable, keep it isolated!");
                }

                //JOptionPane.showMessageDialog(null, "merge between block1 and block4!"); for testing
            } else {
                block1.setLocation(block1.getTempX(), block1.getTempY());
                JOptionPane.showMessageDialog(null, "block1 resets when it is dragged out of its allowed boundary!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please check one of the operation boxes!");
        }

    }//GEN-LAST:event_block1MouseReleased

    private void block2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block2MouseReleased
        if (operation == 1 || operation == 2 || operation == 3) {
            if (block2.getBounds().intersects(block1.getBounds())) {
                //swap(block2, block1);
                swapMe = new swap();
                swapMe.blockOne = block2;
                swapMe.blockTwo = block1;
                swapMe.execute();
                //JOptionPane.showMessageDialog(null, "merge between block2 and block1!"); for testing
            } else if (block2.getBounds().intersects(block3.getBounds())) {
                validMove = isValidMove(block2, block3, block2.getBlockNum(), block3.getBlockNum());
                if (validMove) {
                    //swap(block2, block1);
                    //move(block2, block3, theOperation);
                    moveMe = new move();
                    moveMe.one = block2;
                    moveMe.two = block3;
                    moveMe.sOperation = theOperation;
                    moveMe.sOperation2 = theOperation2;
                    moveMe.execute();
                   // System.out.println(SwingUtilities.isEventDispatchThread() + " keyreleased");    for testing
                    
                } else {
                    block2.setLocation(block2.getTempX(), block2.getTempY());
                    JOptionPane.showMessageDialog(null, "Don't move the variable, keep it isolated!");
                }
                //JOptionPane.showMessageDialog(null, "merge between block2 and block3!"); for testing
            } else if (block2.getBounds().intersects(block4.getBounds())) {
                validMove = isValidMove(block2, block4, block2.getBlockNum(), block4.getBlockNum());
                if (validMove) {
                    //move(block2, block4, theOperation);
                    moveMe = new move();
                    moveMe.one = block2;
                    moveMe.two = block4;
                    moveMe.sOperation = theOperation;
                    moveMe.sOperation2 = theOperation2;
                    moveMe.execute();
                } else {
                    block2.setLocation(block2.getTempX(), block2.getTempY());
                    JOptionPane.showMessageDialog(null, "Don't move the variable, keep it isolated!");
                }
                //JOptionPane.showMessageDialog(null, "merge between block2 and block4!"); for testing
            } else {
                block2.setLocation(block2.getTempX(), block2.getTempY());
                JOptionPane.showMessageDialog(null, "block2 resets when it is dragged out of its allowed boundary!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please check one of the operation boxes!");
        }

    }//GEN-LAST:event_block2MouseReleased

    private void block3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block3MouseReleased
        if (operation == 1 || operation == 2 || operation == 3) {
            if (block3.getBounds().intersects(block2.getBounds())) {
                validMove = isValidMove(block3, block2, block3.getBlockNum(), block2.getBlockNum());
                if (validMove) {
                    //move(block3, block2, theOperation);
                    moveMe = new move();
                    moveMe.one = block3;
                    moveMe.two = block2;
                    moveMe.sOperation = theOperation;
                    moveMe.sOperation2 = theOperation2;
                    moveMe.execute();
                } else {
                    block3.setLocation(block3.getTempX(), block3.getTempY());
                    JOptionPane.showMessageDialog(null, "Don't move the variable, keep it isolated!");
                }
                //JOptionPane.showMessageDialog(null, "merge between block3 and block2!"); for testing
            } else if (block3.getBounds().intersects(block1.getBounds())) {
                validMove = isValidMove(block3, block1, block3.getBlockNum(), block1.getBlockNum());
                if (validMove) {
                    //move(block3, block1, theOperation);
                    moveMe = new move();
                    moveMe.one = block3;
                    moveMe.two = block1;
                    moveMe.sOperation = theOperation;
                    moveMe.sOperation2 = theOperation2;
                    moveMe.execute();
                } else {
                    block3.setLocation(block3.getTempX(), block3.getTempY());
                    JOptionPane.showMessageDialog(null, "Don't move the variable, keep it isolated!");
                }
                //JOptionPane.showMessageDialog(null, "merge between block3 and block1!"); for testing
            } else if (block3.getBounds().intersects(block4.getBounds())) {
                //swap(block3, block4);
                swapMe = new swap();
                swapMe.blockOne = block3;
                swapMe.blockTwo = block4;
                swapMe.execute();
                //JOptionPane.showMessageDialog(null, "merge between block3 and block4!"); for testing
            } else {
                block3.setLocation(block3.getTempX(), block3.getTempY());
                JOptionPane.showMessageDialog(null, "block3 resets when it is dragged out of its allowed boundary!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please check one of the operation boxes!");
        }

    }//GEN-LAST:event_block3MouseReleased

    private void block4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block4MouseDragged
        int newX = block4.getX() + evt.getX() - x;
        int newY = block4.getY() + evt.getY() - y;
        if (operation == 1 || operation == 2 || operation == 3) {
            block4.setLocation(newX, newY);
        }
    }//GEN-LAST:event_block4MouseDragged

    private void block4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block4MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_block4MousePressed

    private void block4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_block4MouseReleased
        if (operation == 1 || operation == 2 || operation == 3) {
            if (block4.getBounds().intersects(block3.getBounds())) {
                //swap(block4, block3);
                swapMe = new swap();
                swapMe.blockOne = block4;
                swapMe.blockTwo = block3;
                swapMe.execute();
                //JOptionPane.showMessageDialog(null, "merge between block4 and block3!"); for testing
            } else if (block4.getBounds().intersects(block2.getBounds())) {
                validMove = isValidMove(block4, block2, block4.getBlockNum(), block2.getBlockNum());
                if (validMove) {
                    //move(block4, block2, theOperation);
                    moveMe = new move();
                    moveMe.one = block4;
                    moveMe.two = block2;
                    moveMe.sOperation = theOperation;
                    moveMe.sOperation2 = theOperation2;
                    moveMe.execute();
                } else {
                    block4.setLocation(block4.getTempX(), block4.getTempY());
                    JOptionPane.showMessageDialog(null, "Don't move the variable, keep it isolated!");
                }
                //JOptionPane.showMessageDialog(null, "merge between block4 and block2!"); for testing
            } else if (block4.getBounds().intersects(block1.getBounds())) {
                validMove = isValidMove(block4, block1, block4.getBlockNum(), block1.getBlockNum());
                if (validMove) {
                    //move(block4, block1, theOperation);
                    moveMe = new move();
                    moveMe.one = block4;
                    moveMe.two = block1;
                    moveMe.sOperation = theOperation;
                    moveMe.sOperation2 = theOperation2;
                    moveMe.execute();
                } else {
                    block4.setLocation(block4.getTempX(), block4.getTempY());
                    JOptionPane.showMessageDialog(null, "Don't move the variable, keep it isolated!");
                }
                //JOptionPane.showMessageDialog(null, "merge between block4 and block1!"); for testing
            } else {
                block4.setLocation(block4.getTempX(), block4.getTempY());
                JOptionPane.showMessageDialog(null, "block4 resets when it is dragged out of its allowed boundary!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please check one of the operation boxes!");
        }

    }//GEN-LAST:event_block4MouseReleased

    private void jCheckBoxDivideItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxDivideItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jLabelOperator.setText("/");
            jCheckBoxAdd.setSelected(false);
            jCheckBoxMultiply.setSelected(false);
            operation = 3;
            operationChecked = true;
            theOperation = "divide";
        } else if (evt.getStateChange() == 0) {
            jLabelOperator.setText(" ");
            operation = 0;
            operationChecked = false;
        }
    }//GEN-LAST:event_jCheckBoxDivideItemStateChanged

    private void jCheckBoxDivideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxDivideMouseClicked
        if (!jCheckBoxDivide.isSelected()) {
            jLabelOperator.setText(" ");
            operation = 0;
        }
    }//GEN-LAST:event_jCheckBoxDivideMouseClicked

    private void jButtonResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonResetMouseClicked
        reset();
    }//GEN-LAST:event_jButtonResetMouseClicked

    private void jButtonEnterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnterMouseClicked
        int b1 = 0;
        int b2 = 1;
        int b3 = 2;
        String textB3 = "";
        boolean goodToGo = false;
        String[] textFieldArr = new String[3];
        textFieldArr[b1] = textFieldB1.getText();
        textFieldArr[b2] = textFieldB2.getText();
        if (operationChecked) {
            if (variable == 0) {
                goodToGo = isValidInput(textFieldArr);
                if (goodToGo) {
                    if (variable == 1) {
                        //textB3 = JOptionPane.showInputDialog(null, "Please enter a value for Block3.");
                        textB3 = JOptionPane.showInputDialog("test box");
                        //textFieldB3.setEnabled(true);
                        textFieldB3.setText(textB3);
                        textFieldArr[b3] = textFieldB3.getText();
                        goodToGo = isValidInput(textFieldArr);
                        if (goodToGo) {
                            jLabelB1.setText(textFieldB1.getText());
                            jLabelB2.setText(textFieldB2.getText());
                            jLabelB3.setText(textB3);
                            block3.setMyValue(Integer.parseInt(textB3));
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid input, please try agaoin.");
                            reset();
                        }
                        /**
                         * ******************need to include code for fraction
                         * and no variable***********************
                         */
                    } else if (variable == 0 && jCheckBoxAdd.isSelected()) {    // This is for Addition
                        if (isFraction) {
                            if (block1.isFrac == true && block2.isFrac == true) {
                                Fraction first = new Fraction(block1.getNumerator(), block1.getDenominator());
                                Fraction two = new Fraction(block2.getNumerator(), block2.getDenominator());
                                Fraction result = first.add(two);
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            } else if (block1.isFrac == true && block2.isFrac == false) {
                                Fraction first = new Fraction(block1.getNumerator(), block1.getDenominator());
                                Fraction result = first.add(Integer.parseInt(textFieldB2.getText()));
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            } else if (block1.isFrac == false && block2.isFrac == true) {
                                Fraction two = new Fraction(block2.getNumerator(), block2.getDenominator());
                                Fraction result = two.add(Integer.parseInt(textFieldB1.getText()));
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            }
                        } else {
                            jLabelB1.setText(textFieldB1.getText());
                            jLabelB2.setText(textFieldB2.getText());
                            int intB3 = add(textFieldB1.getText(), textFieldB2.getText());
                            jLabelB3.setText(Integer.toString(intB3));  // Int to String 
                            block3.setMyValue(intB3);

                        }

                    } else if (variable == 0 && jCheckBoxMultiply.isSelected()) {   // 
                        if (isFraction) {
                            if (block1.isFrac == true && block2.isFrac == true) {
                                Fraction first = new Fraction(block1.getNumerator(), block1.getDenominator());
                                Fraction two = new Fraction(block2.getNumerator(), block2.getDenominator());
                                Fraction result = first.multiply(two);
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            } else if (block1.isFrac == true && block2.isFrac == false) {
                                Fraction first = new Fraction(block1.getNumerator(), block1.getDenominator());
                                Fraction result = first.multiply(Integer.parseInt(textFieldB2.getText()));
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            } else if (block1.isFrac == false && block2.isFrac == true) {
                                Fraction two = new Fraction(block2.getNumerator(), block2.getDenominator());
                                Fraction result = two.multiply(Integer.parseInt(textFieldB1.getText()));
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            }
                        } else {
                            jLabelB1.setText(textFieldB1.getText());
                            jLabelB2.setText(textFieldB2.getText());
                            int intB3 = multiply(textFieldB1.getText(), textFieldB2.getText());
                            jLabelB3.setText(Integer.toString(intB3));  // In to String
                        }

                    } else if (variable == 0 && jCheckBoxDivide.isSelected()) {
                        if (isFraction) {
                            if (block1.isFrac == true && block2.isFrac == true) {
                                Fraction first = new Fraction(block1.getNumerator(), block1.getDenominator());
                                Fraction two = new Fraction(block2.getNumerator(), block2.getDenominator());
                                Fraction result = first.divide(two);
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            } else if (block1.isFrac == true && block2.isFrac == false) {
                                Fraction first = new Fraction(block1.getNumerator(), block1.getDenominator());
                                Fraction result = first.divide(Integer.parseInt(textFieldB2.getText()));
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            } else if (block1.isFrac == false && block2.isFrac == true) {
                                Fraction two = new Fraction(block2.getNumerator(), block2.getDenominator());
                                Fraction one = new Fraction(Integer.parseInt(textFieldB1.getText()), 1);
                                Fraction result = one.divide(two);
                                FractionFormat theFormat = new FractionFormat();
                                String value = theFormat.format(result);
                                jLabelB1.setText(textFieldB1.getText());
                                jLabelB2.setText(textFieldB2.getText());
                                jLabelB3.setText(value);
                            }
                        } else {
                            Fraction divide = new Fraction(Integer.parseInt(textFieldB1.getText()), Integer.parseInt(textFieldB2.getText()));
                            FractionFormat div = new FractionFormat();
                            jLabelB1.setText(textFieldB1.getText());
                            jLabelB2.setText(textFieldB2.getText());
                            jLabelB3.setText(div.format(divide));
                            block3.setNumerator(Integer.parseInt(textFieldB1.getText()));
                            block3.setDenominator(Integer.parseInt(textFieldB2.getText()));
                            block3.setIsFrac(true);
                        }
                    }

                } else {
                    reset();
                    JOptionPane.showMessageDialog(null, "invalid user input, please try again.");
                }
            }
            if (goodToGo) {
                jCheckBoxAdd.setEnabled(false);
                jCheckBoxMultiply.setEnabled(false);
                jCheckBoxDivide.setEnabled(false);
                textFieldB1.setEnabled(false);
                textFieldB2.setEnabled(false);
                textFieldB3.setEnabled(false);
                jButtonEnter.setEnabled(false);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Please check an operation box!");
        }


    }//GEN-LAST:event_jButtonEnterMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(myFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(myFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(myFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(myFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            myFrame frame;

            public void run() {
                frame = new myFrame();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private volatile algebrablocks.Block block1;
    private volatile algebrablocks.Block block2;
    private volatile algebrablocks.Block block3;
    private volatile algebrablocks.Block block4;
    private algebrablocks.Block blockEqualsSign;
    private algebrablocks.Block blockOperatorSign;
    private algebrablocks.Block blockOperatorSign2;
    private javax.swing.JButton jButtonEnter;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JCheckBox jCheckBoxAdd;
    private javax.swing.JCheckBox jCheckBoxDivide;
    private javax.swing.JCheckBox jCheckBoxMultiply;
    private javax.swing.JLabel jLabelB1;
    private javax.swing.JLabel jLabelB2;
    private javax.swing.JLabel jLabelB3;
    private javax.swing.JLabel jLabelB4;
    private javax.swing.JLabel jLabelByGroup3;
    private javax.swing.JLabel jLabelEqualSign;
    private javax.swing.JLabel jLabelOperator;
    private javax.swing.JLabel jLabelOperator2;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.TextField textFieldB1;
    private java.awt.TextField textFieldB2;
    private java.awt.TextField textFieldB3;
    // End of variables declaration//GEN-END:variables
}
