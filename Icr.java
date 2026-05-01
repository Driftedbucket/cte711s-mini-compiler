import java.util.ArrayList;

public class Icr {

    private int count=0;

    private String newTempVariable(){
         count++;
         return "t"+count;
    }
    private String generate(String line){
        //split LET keyword fi rpresent so we see LET G = A + b as G = A + b
            line=line.trim().replace("LET","");

            //split on the = to get the variable annd expression
            // M=A+B
            //parts = ["M","A+B"]
            String[] parts = line.split("=",2);
            if(parts.length<2){
                return "Cannot generate ICR for: "+line;
            }

            String variable = parts[0].trim();
            String expression = parts[1].trim();

            ArrayList<String> icrlines = new ArrayList<>();
            count =0;

            //parse expr left to right, respecting * abnd / before (+ and -)
            String result = parseExpression(expression, icrlines);

            icrlines.add(variable+" = "+result);

            StringBuilder sb = new StringBuilder();
            for(String icrline: icrlines){
                sb.append("    ").append(icrline).append("\n");
            }
            return sb.toString();
    }
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
