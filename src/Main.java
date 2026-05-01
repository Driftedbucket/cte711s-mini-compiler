import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] program = {
            "BEGIN",
            "INTEGER A, B, C, E, M, N, G, H, I, a, c",
            "INPUT A, B, C",
            "LET B = A */ M",
            "LET G = a + c",
            "temp = <s%**h - j / w +d +*$&;",
            "M = A/B+C",
            "N = G/H-I+a*B/c",
            "WRITE M",
            "WRITEE F;",
            "END"
        };

        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        SemanticAnalyzer semantic = new SemanticAnalyzer();

        System.out.println("===========================================");
        System.out.println("     MINI COMPILER - CTE711S GROUP WORK   ");
        System.out.println("     Phase 1: Lexical Analysis (Lexer)     ");
        System.out.println("     Phase 2: Syntax Analysis (Parser)     ");
        System.out.println("     Phase 3: Semantic Analysis            ");
        System.out.println("===========================================");

        int lineNumber = 0;

        for (String line : program) {
            lineNumber++;
            System.out.println("\n###########################################");
            System.out.println("INPUT LINE: " + line);
            System.out.println("-------------------------------------------");

          
            List<Token> tokens = lexer.tokenize(line);
            System.out.println("TOKENS PRODUCED:");
            for (Token t : tokens) {
                System.out.println("   " + t);
            }

            
            parser.parse(line, tokens);

            System.out.println("SEMANTIC ANALYSIS:");

            if (line.startsWith("INTEGER")) {
                String varsPart = line.replace("INTEGER", "").trim();
                List<String> vars = Arrays.asList(varsPart.split(","));
                semantic.registerDeclarations(vars);

           } else if (line.startsWith("LET") || line.contains("=") || line.startsWith("WRITE")) {
    boolean hasLexicalError = tokens.stream()
        .anyMatch(t -> t.toString().contains("ERROR_LEXICAL"));

    if (hasLexicalError) {
        System.out.println("   (Skipping semantic check — lexical error detected)");
    } else {
        String[] words = line.replaceAll("[^a-zA-Z,\\s]", " ").trim().split("\\s+");
        for (String word : words) {
            if (word.equals("LET") || word.equals("WRITE") || word.equals("INPUT")
                    || word.equals("BEGIN") || word.equals("END")
                    || word.equals("INTEGER") || word.isEmpty()) {
                continue;
            }
            semantic.checkVariableUsage(word, lineNumber);
        }
    }
            } else {
                System.out.println("   (No semantic check needed for this line)");
            }
        }

        System.out.println("\n===========================================");
        System.out.println("           COMPILATION COMPLETE            ");
        System.out.println("===========================================");
    }
}