
public class Semantic {
    public class Semantic{

    public String analyse(String line){
        
        char[] forbidden={'%','$','&','<','>'};

        for(char badChar:forbidden){
            if(line.indexOf(badChar) != -1){
                return "SEMANTIC ERROR: Illegal symbol '"+ badChar+"' found in line. Try again!";
            }
        }
        return "OK";
    }
}
}
