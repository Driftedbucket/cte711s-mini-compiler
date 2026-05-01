public class Syntax{
    public String analyse(String line){

        /*we're going to be checking for 3 errorrs:
        1. semicolons at hte end of a line is not allowed
        2. digits 0-9 are not allowed
        3. 2 operators following each other arent allowed */

        //error 1 -> semicolon at the end
        if(line.trim().endsWith(";")){
            return "SYNTAX ERROR: Semicolon ';' is not allowed at the end of a line";
        }

        //error 2 -> digits 0-9
        for(char ch:line.toCharArray()){
            if(Character.isDigit(ch)){
                return "SYNTAX ERROR: Numbers are not allowed. Found digit '"+ch+"'.";
            }
        }

        //eror 3 -> two operators in a row 
        //all possible combinations 4^4=16
        String[] badCombos = {"*/", "/*", "+-", "-+", "*+", "+*", "-/", "/-", "**", "//", "-*"} ;
        for(String combo: badCombos){
            if(line.contains(combo)){
                return "SYNTAX ERROR: Invalid operator combination '"+combo+"' found.";
            }
        }
        return "OK";
    }
}