package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.EventObject;

public class Controller {
    //all the fxml fields are declared using the scene builder controller skeleton
    @FXML
    private Label lblAns;

    @FXML
    private Button btnAC;

    @FXML
    private Button btnNg;

    @FXML
    private Button btnPct;

    @FXML
    private Button btnDiv;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Button btnMult;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btnSub;

    @FXML
    private Button btn1;

    @FXML
    private Button btn6;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btnPlus;

    @FXML
    private Button btn0;

    @FXML
    private Button btnDec;

    @FXML
    private Button btnEquals;

    //declare/initialize the operands, and operator variables, these will be set to an empty space initially which will be used later to see if it is assigned yet
    String operand1=" ";
    String operand2=" ";
    String operator=" ";

    //this method calculate is an action event tied to all the numeric and operator buttons that performs a different function based on what button was clicked/what the operands and operator currently are
    @FXML
    public void calculate(ActionEvent event) {
        //I create an event object clicked which is tied to the event, or the button clicked, I use this so I can have the current button clicked be referenced by
        //this object clicked instead of having to make a separate action event for each button
        EventObject clicked = event;
        //I use ((Button)clicked.getSource()) with clicked being the event object to get the button last clicked that has this method as its onAction, I'll refer to this code as the button clicked
        //This first if statement checks for if the equals button was clicked and makes sure that there are numbers and an operator available to be used to make the calculation
        //If the button clicked is the equals button (if the button's text is "=") and both operands as well as the operator arent empty (meaning that there are currently two numbers and an operator available to be used for calculation)
        if(((Button)clicked.getSource()).getText().equals("=") && !operand1.equals(" ") && !operand2.equals(" ") && !operator.equals(" ")){
            //run the check method which checks what the operator is and performs the necessary calculations
            check();
        }
        //the next if statement is similar to the last except it checks if there is already an operator, if an operator was clicked, and that the button isn't equals, this is so the calculator can dynamically calculate expressions like
        //8+9 -2 where the result of 8+9 is displayed once the - is clicked
        //if the button clicked isnt =, and both operands and the operator currently have values and the button clicked is one of the four operators
        if(!((Button)clicked.getSource()).getText().equals("=") && !operand1.equals(" ") && !operand2.equals(" ") && !operator.equals(" ") && (((Button)clicked.getSource()).getText().equals("+") || ((Button)clicked.getSource()).getText().equals("-") || ((Button)clicked.getSource()).getText().equals("X") || ((Button)clicked.getSource()).getText().equals("/"))){
            //run check again to perform the appropriate calculations
            check();
        }
        //checks if the button clicked was one of the four operators
        if(((Button)clicked.getSource()).getText().equals("+") || ((Button)clicked.getSource()).getText().equals("-") || ((Button)clicked.getSource()).getText().equals("X") || ((Button)clicked.getSource()).getText().equals("/")){
            //if it was, and if the operator is currently unassigned/empty
            if(operator.equals(" "))
                //then set the operator to the text of the button clicked
                operator=((Button)clicked.getSource()).getText();
        }
        //if the operator is empty and the button clicked wasn't any of the operators or =
        if(operator.equals(" ") && !((Button)clicked.getSource()).getText().equals("+") && !((Button)clicked.getSource()).getText().equals("-") && !((Button)clicked.getSource()).getText().equals("X") && !((Button)clicked.getSource()).getText().equals("/") && !((Button)clicked.getSource()).getText().equals("=")){
            //this means that currently the calculator is clear and that the button clicked must be for operand1
            //since the operands are empty strings, you can just add the text of the button to it (this also allows the calculator to have multiple digit operators and decimals in each operand if the . is clicked since it passes the checks)
            operand1 += ((Button)clicked.getSource()).getText();
            //set the label to operand1 for the user to view
            lblAns.setText(operand1);
        }
        //else if operand1 and the operator both have values and the button clicked wasn't any of the operators or =
        else if(!operand1.equals(" ") && !operator.equals(" ") && !((Button)clicked.getSource()).getText().equals("+") && !((Button)clicked.getSource()).getText().equals("-") && !((Button)clicked.getSource()).getText().equals("X") && !((Button)clicked.getSource()).getText().equals("/") && !((Button)clicked.getSource()).getText().equals("=")){
            //this means the button must be for operand2 since there is already an operand1 and operator
            //since the operands are empty strings, you can just add the text of the button to it (this also allows the calculator to have multiple digit operators and decimals in each operand if the . is clicked since it passes the checks)
            operand2 += ((Button)clicked.getSource()).getText();
            //set the label to operand2 for the user to view
            lblAns.setText(operand2);
        }
    }

    //this method is tied to the AC button and clears all fields
    @FXML
    public void allClear(ActionEvent event) {
        //set both operands and the operator back to empty strings
        operand1=" ";
        operand2=" ";
        operator=" ";
        //set the label that displays to the user to blank
        lblAns.setText("");
    }
    //this method is tied to the negate button (+/-) and turns the current number to its opposite (positive to negative and vice versa)
    @FXML
    public void negate(ActionEvent actionEvent) {
        //if the current number (lblAns) is operand1
        if(lblAns.getText().equals(operand1)){
            //set it to its opposite by multiplying it by -1 (parse to a double for the calculation then get the string value)
            operand1 = String.valueOf(Double.parseDouble(operand1)*-1);
            //set lblAns to the new operand1
            lblAns.setText(operand1);
        }
        //if the current number (lblAns) is operand2
        if(lblAns.getText().equals(operand2)){
            //set it to its opposite by multiplying it by -1 (parse to a double for the calculation then get the string value)
            operand2 = String.valueOf(Double.parseDouble(operand2)*-1);
            //set lblAns to the new operand2
            lblAns.setText(operand2);
        }
    }
    //this method is tied to the percent button (%) and turns the current number to a percent (EX: 50 is turned to 0.5)
    @FXML
    public void percent(ActionEvent actionEvent) {
        //if the current number (lblAns) is operand1
        if(lblAns.getText().equals(operand1)){
            //turn it to a percent by multiplying it by 0.01 (parse to a double for the calculation then get the string value)
            operand1 = String.valueOf(Double.parseDouble(operand1)*0.01);
            //set lblAns to the new operand1
            lblAns.setText(operand1);
        }
        //if the current number (lblAns) is operand2
        if(lblAns.getText().equals(operand2)){
            //turn it to a percent by multiplying it by 0.01 (parse to a double for the calculation then get the string value)
            operand2 = String.valueOf(Double.parseDouble(operand2)*0.01);
            //set lblAns to the new operand2
            lblAns.setText(operand2);
        }
    }

    //this method check is used twice in the calculate method and is used when both operands and the operator have values, meaning they are able to make a calculation
    public void check(){
        //I keep operand1 as the answer since you can then use it to continue doing calculations based off the answer afterwards
        //if the current operator is +
        if(operator.equals("+")){
            //set operand1 to the answer of the calculation, in this case operand1 + operand2 (used parseDouble)
            operand1 = String.valueOf(Double.parseDouble(operand1)+Double.parseDouble(operand2));
            //set the label to operand1 since that is the answer
            lblAns.setText(operand1);
            //set operand2 and operator back to blank now that the calculation is complete
            operand2 = " ";
            operator = " ";
        }
        //if the current operator is -
        else if(operator.equals("-")){
            //set operand1 to the answer of the calculation, in this case operand1 - operand2 (used parseDouble)
            operand1 = String.valueOf(Double.parseDouble(operand1)-Double.parseDouble(operand2));
            //set the label to operand1 since that is the answer
            lblAns.setText(operand1);
            //set operand2 and operator back to blank now that the calculation is complete
            operand2 = " ";
            operator = " ";
        }
        //if the current operator is -
        else if(operator.equals("X")){
            //set operand1 to the answer of the calculation, in this case operand1 * operand2 (used parseDouble)
            operand1 = String.valueOf(Double.parseDouble(operand1)*Double.parseDouble(operand2));
            //set the label to operand1 since that is the answer
            lblAns.setText(operand1);
            //set operand2 and operator back to blank now that the calculation is complete
            operand2 = " ";
            operator = " ";
        }
        else if(operator.equals("/")){
            //set operand1 to the answer of the calculation, in this case operand1 / operand2 (used parseDouble)
            operand1 = String.valueOf(Double.parseDouble(operand1)/Double.parseDouble(operand2));
            //set the label to operand1 since that is the answer
            lblAns.setText(operand1);
            //set operand2 and operator back to blank now that the calculation is complete
            operand2 = " ";
            operator = " ";
        }
    }

}
