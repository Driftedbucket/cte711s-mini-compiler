package parser;

import lexer.Token;
import lexer.TokenType;
import java.util.*;

public class Parser {

    public void parse(String originalLine, List<Token> tokens) {
        System.out.println("\n--- Syntax Analysis for: \"" + originalLine + "\"");

        boolean hasError = false;

        // CHECK 1: Semicolon at end of line
        String trimmed = originalLine.trim();
        if (trimmed.endsWith(";")) {
            System.out.println("  [SYNTAX ERROR] Semicolon ';' at end of line is not allowed.");
            hasError = true;
        }

        // CHECK 2: Two consecutive operators (e.g. */, +*, -/)
        for (int i = 0; i < tokens.size() - 1; i++) {
            Token current = tokens.get(i);
            Token next = tokens.get(i + 1);

            if (current.type == TokenType.OPERATOR && next.type == TokenType.OPERATOR) {
                System.out.println("  [SYNTAX ERROR] Combined operators '"
                    + current.value + next.value
                    + "' are not allowed.");
                hasError = true;
            }
        }

        // CHECK 3: Report any errors flagged by the Lexer
        for (Token token : tokens) {
            switch (token.type) {
                case ERROR_LEXICAL:
                    System.out.println("  [LEXICAL ERROR] " + token.value);
                    hasError = true;
                    break;
                case ERROR_SYNTAX:
                    System.out.println("  [SYNTAX ERROR] " + token.value);
                    hasError = true;
                    break;
                case ERROR_SEMANTIC:
                    System.out.println("  [SEMANTIC ERROR] " + token.value);
                    hasError = true;
                    break;
                default:
                    break;
            }
        }

        if (!hasError) {
            System.out.println("  [OK] Line is valid — no errors detected.");
        }
    }
}