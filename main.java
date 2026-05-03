public class main {
    
    public static void main(String[] args) {
        
        String[] sourceLines = {
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

        boolean[] isValidLine = {
            false,  // 1: BEGIN
            false,  // 2: INTEGER
            false,  // 3: INPUT
            false,  // 4: LET B = A */ M
            true,   // 5: LET G = a + c      <-- valid
            false,  // 6: temp = <s%**h...
            true,   // 7: M = A/B+C           <-- valid
            true,   // 8: N = G/H-I+a*B/c    <-- valid
            false,  // 9: WRITE M
            false,  // 10: WRITEE F;
            false   // 11: END
        };

        Lexer lexical = new Lexer();
        Syntax syntax = new Syntax();
        Semantic semantic = new Semantic();
        Icr icr = new Icr();
        Codegen codegen  = new Codegen();
        Codeopt optimise = new Codeopt();
        Machine target   = new Machine();

        System.out.println("========================================");
        System.out.println("|||  PART 1: Compiling line by line  |||");
        System.out.println("========================================\n");

        for (int i = 0; i < sourceLines.length; i++) {
            String line    = sourceLines[i];
            int    lineNum = i + 1;
            boolean valid  = isValidLine[i];

            System.out.println("--- Line " + lineNum + ": " + line);

            
            String lexResult = lexical.analyse(line);
            System.out.println("  [Stage 1 - Lexical]   " + lexResult);
            if (!lexResult.equals("OK")) { System.out.println(); continue; }

            
            String synResult = syntax.analyse(line);
            System.out.println("  [Stage 2 - Syntax]    " + synResult);
            if (!synResult.equals("OK")) { System.out.println(); continue; }

            
            String semResult = semantic.analyse(line);
            System.out.println("  [Stage 3 - Semantic]  " + semResult);
            if (!semResult.equals("OK")) { System.out.println(); continue; }

            //check for any errors so far, only valid lines will pass to conitue to stage 4  
            if (!valid) {
                System.out.println("  [No errors found — line passes error check only]\n");
                continue;
            }

            
            String icrResult = icr.generate(line);
            System.out.println("  [Stage 4 - ICR]\n" + icrResult);

            
            String asmResult = codegen.generate(icrResult);
            System.out.println("  [Stage 5 - Code Gen]\n" + asmResult);

            
            String optResult = optimise.optimise(asmResult);
            System.out.println("  [Stage 6 - Optimised]\n" + optResult);

            /* 
            String binResult = target.generate(optResult);
            System.out.println("  [Stage 7 - Binary]\n" + binResult); */
        }


    }
}
