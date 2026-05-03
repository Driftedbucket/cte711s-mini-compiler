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

    }
}
