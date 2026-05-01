import java.util.ArrayList;

public class Icr {

    private String newTempVariable(){}
    private String generate(){}
    private String parseExpression(String expression, ArrayList<String> icrlines){
        expression=expression.trim();

            int splitAt=-1;
            for(int i=0; i<expression.length(); i++){
                if(expression.charAt(i)=='*'||expression.charAt(i)=='/'){
                    splitAt=i;
                }
            }
            if(splitAt==-1){
                //no + or - pass dowen to term(hanldes * and /)
                return parseTerm(expression, icrlines);
            }

            String leftExpr = expression.substring(0, splitAt).trim();
            char operator = expression.charAt(splitAt);
            String rightExpr = expression.substring(splitAt+1).trim();

            String leftResult = parseTerm(leftExpr, icrlines);
            String rightResult = parseTerm(rightExpr, icrlines);

            String temp = newTempVariable();
            icrlines.add(temp+" = "+leftResult+" "+operator+" "+rightResult);
            return temp;
    }

    private String parseTerm(String expression, ArrayList<String> icrlines){
        expression=expression.trim();

            int splitAt=-1;
            for(int i=0; i<expression.length(); i++){
                if(expression.charAt(i)=='*'||expression.charAt(i)=='/'){
                    splitAt=i;
                }
            }
            if(splitAt==-1){
                return expression.trim();
            }

            String leftExpr = expression.substring(0, splitAt).trim();
            char operator = expression.charAt(splitAt);
            String rightExpr = expression.substring(0, splitAt).trim();
            
            String leftResult = leftExpr.isEmpty()?leftExpr:parseTerm(leftExpr, icrlines);
            String rightResult = rightExpr.trim();
            
            String temp = newTempVariable();
            icrlines.add(temp+"="+leftResult+" "+operator+""+rightResult);
            return temp;
    }
}
