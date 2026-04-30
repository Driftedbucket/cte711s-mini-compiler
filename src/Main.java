import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // The program from the assignment — each line is a string
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

        System.out.println("===========================================");
        System.out.println("     MINI COMPILER - CTE711S GROUP WORK   ");
        System.out.println("     Phase 1: Lexical Analysis (Lexer)     ");
        System.out.println("     Phase 2: Syntax Analysis (Parser)     ");
        System.out.println("===========================================");

        for (String line : program) {
            System.out.println("\n###########################################");
            System.out.println("INPUT LINE: " + line);
            System.out.println("-------------------------------------------");

            // Phase 1 — Tokenize
            List<Token> tokens = lexer.tokenize(line);
            System.out.println("TOKENS PRODUCED:");
            for (Token t : tokens) {
                System.out.println("   " + t);
            }

            // Phase 2 — Parse
            parser.parse(line, tokens);
        }

        System.out.println("\n===========================================");
        System.out.println("           COMPILATION COMPLETE            ");
        System.out.println("===========================================");
    }
}