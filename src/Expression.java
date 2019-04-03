import java.rmi.activation.ActivationGroup_Stub;
import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;

interface Expression {
    String interpret();
}

class StringExpression implements Expression {
    String value;

    public StringExpression(String value) {
        this.value = value;
    }

    public String interpret() {
        return value;
    }
}

class InsertExpression {
    Expression right;

    public InsertExpression(Expression right) {
        this.right = right;
        Interpret.myHashSet.insert(right.interpret());
    }
}

class RemoveExpression {
    Expression right;
    public RemoveExpression(Expression right) {
        this.right = right;
        Interpret.myHashSet.remove(right.interpret());
    }
}

class CountExpression { 
    Expression right;
    public CountExpression(Expression right) {
        this.right = right;
        ExpressionUtils util = new ExpressionUtils();
        System.out.println("Answer: " + util.calculateExpression(right.interpret()));

       // Interpret.myHashSet.remove(right.interpret());
    }
}


class ForExpression {
    Expression right;
    public ForExpression(Expression right) {
        this.right = right;
        int pos = 0;
        while(right.interpret().charAt(pos) != ' '){
            pos ++;}

        ExpressionUtils util = new ExpressionUtils();
        String indexStr = right.interpret().substring(0, pos);
        int index = util.calculateExpression(indexStr).intValue();

        String last = right.interpret().substring(pos+2, right.interpret().length()-1);
        String[] expMass = last.split("; ");
        Contex subContex = new Contex();
        for (int i = 1; i <= index; i ++){
            for (int j = 0; j < expMass.length; j++){
                if (expMass[j].contains("insert")) {
                    int code = (int) 'A' + (int) (Math.random() * 25);
                    String randomStr = " " + (char) code + ".";
                    subContex.evaluate(expMass[j]+ randomStr);
                } else {
                    subContex.evaluate(expMass[j]);
                }
            }
        }

        // Interpret.myHashSet.remove(right.interpret());
    }
}

class PrintinExpression {
    Expression right;

    public PrintinExpression(Expression right) {
        this.right = right;
        Interpret.myHashSet.printin(right.interpret());
    }
}

class PrintsetExpression {
    public PrintsetExpression() {
        Interpret.myHashSet.printset();
    }
}

class Contex {
    public void evaluate(String s) {
        int pos = 0;
        while (pos < s.length()) {
            if (!Character.isLetter(s.charAt(pos))|| pos == s.length() -1) {
                String operator = s.substring(0, pos);
                if (operator.equals("printse")) {
                    new PrintsetExpression();
                } else {
                    Expression right = new StringExpression(s.substring(pos + 1, s.length()));
                    switch (operator) {
                        case "insert":
                            new InsertExpression(right);
                            break;
                        case "remove":
                            new RemoveExpression(right);
                            break;
                        case "printin":
                            new PrintinExpression(right);
                            break;
                        case "for":
                            new ForExpression(right);
                            break;
                        case "count":
                            new CountExpression(right);
                            break;
                    }
                }
                pos++;
            }
         else{
             pos++;
            }
        }
        }
}

