package lexer;

import java.util.*;

public class Lexer {

    private static final Set<String> KEYWORDS = new HashSet<>(
        Arrays.asList("BEGIN", "END", "INTEGER", "LET", "INPUT", "WRITE")
    );

    private static final Set<Character> OPERATORS = new HashSet<>(
        Arrays.asList('+', '-', '*', '/')
    );

    private static final Set<Character> VALID_SYMBOLS = new HashSet<>(
        Arrays.asList('=', ',')
    );

    private static final Set<Character> SEMANTIC_ERROR_CHARS = new HashSet<>(
        Arrays.asList('%', '$', '&', '<', '>', ';')
    );

    public List<Token> tokenize(String line) {
        List<Token> tokens = new ArrayList<>();
        String trimmed = line.trim();
        String[] parts = trimmed.split("\\s+");

        for (String part : parts) {
            if (!part.isEmpty()) {
                tokens.addAll(tokenizePart(part));
            }
        }

        return tokens;
    }

    private List<Token> tokenizePart(String part) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder wordBuffer = new StringBuilder();

        for (int i = 0; i < part.length(); i++) {
            char ch = part.charAt(i);

            if (Character.isLetter(ch)) {
                wordBuffer.append(ch);

            } else {
                if (wordBuffer.length() > 0) {
                    tokens.add(classifyWord(wordBuffer.toString()));
                    wordBuffer.setLength(0);
                }

                if (OPERATORS.contains(ch)) {
                    tokens.add(new Token(TokenType.OPERATOR, String.valueOf(ch)));

                } else if (VALID_SYMBOLS.contains(ch)) {
                    tokens.add(new Token(TokenType.SYMBOL, String.valueOf(ch)));

                } else if (SEMANTIC_ERROR_CHARS.contains(ch)) {
                    tokens.add(new Token(TokenType.ERROR_SEMANTIC,
                        "Semantic Error: illegal symbol '" + ch + "'"));

                } else if (Character.isDigit(ch)) {
                    tokens.add(new Token(TokenType.ERROR_SYNTAX,
                        "Syntax Error: digit '" + ch + "' is not allowed"));

                } else {
                    tokens.add(new Token(TokenType.ERROR_SYNTAX,
                        "Syntax Error: unknown character '" + ch + "'"));
                }
            }
        }

        if (wordBuffer.length() > 0) {
            tokens.add(classifyWord(wordBuffer.toString()));
        }

        return tokens;
    }

    private Token classifyWord(String word) {
        if (KEYWORDS.contains(word)) {
            return new Token(TokenType.KEYWORD, word);
        }

        if (word.matches("[A-Z]")) {
            return new Token(TokenType.IDENTIFIER, word);
        }

        if (word.matches("[a-z]+")) {
            return new Token(TokenType.IDENTIFIER, word);
        }

        if (word.matches("[A-Z]{2,}")) {
            return new Token(TokenType.ERROR_LEXICAL,
                "Lexical Error: '" + word + "' is not a valid keyword (possible misspelling)");
        }

        return new Token(TokenType.IDENTIFIER, word);
    }
}