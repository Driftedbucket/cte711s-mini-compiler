public class Lexer{

    String[] keywords = {"BEGIN", "END", "INTEGER", "INPUT", "LET", "WRITE"};

    public String analyse(String inputline){
        String[] words = inputline.trim().split("\\s+|,|=");
        
        for(String word:words){
            if(word.isEmpty()) continue;

            if(word.equals(word.toUpperCase()) && word.length()>1 && !isOperatorOrSymbol(word)){
                if(!isValidkeyword(word)){
                    return "LEXICAL ERROR: '"+word+"' is not a valid keyword. Check your spelling!";
                }
            }
        }
        return "OK";
    }

    private boolean isValidkeyword(String word){
        for(String w : keywords){
            if(w.equals(word)) return true;
        }
        return false;
    }
    private boolean isOperatorOrSymbol(String word){
        return word.matches("[+\\-*=;%$&<>]+");
    }
}