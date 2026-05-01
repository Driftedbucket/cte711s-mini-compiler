
public class Codegen {
    
    public String generate(String icrOutput){
        String[] icrlines = icrOutput.split("\n");
        StringBuilder assemblyCode = new StringBuilder();

        for(String icrline:icrlines){
            icrline=icrline.trim();
            if(icrline.isEmpty()) continue;

            String[] parts = icrline.split("=",2);
            if(parts.length<2)continue;

            String dest = parts[0].trim();
            String expr = parts[1].trim();

             //simple expression M = t2
            if(!expr.contains("+") && !expr.contains("-") && !expr.contains("*") && !expr.contains("/")){
                assemblyCode.append("    LOAD R1, ").append(expr).append("\n");
                assemblyCode.append("    STORE R1, ").append(dest).append("\n");
            }else{
                //split on the operator
                String operator;
                String[] operands;
            
                if(expr.contains("+")){
                    operator="ADD";
                    operands=expr.split("\\+");
                }else if(expr.contains("-")){
                    operator="SUB";
                    operands=expr.split("-");
                }else if(expr.contains("*")){
                    operator="MUL";
                    operands=expr.split("\\*");
                }else{
                    operator="DIV";
                    operands=expr.split("/");
                }
    }
    
}
}
