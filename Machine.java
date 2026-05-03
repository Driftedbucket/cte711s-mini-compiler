
public class Machine {

    public String generate(String asmCode) {
        String[] lines = asmCode.split("\n");
        StringBuilder binary = new StringBuilder();

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith(";")) continue;

            // Simple opcode mapping to binary prefixes
            String binLine;
            if      (line.startsWith("LOAD"))  binLine = "0001 " + toBin(line);
            else if (line.startsWith("STORE")) binLine = "0010 " + toBin(line);
            else if (line.startsWith("ADD"))   binLine = "0011 " + toBin(line);
            else if (line.startsWith("SUB"))   binLine = "0100 " + toBin(line);
            else if (line.startsWith("MUL"))   binLine = "0101 " + toBin(line);
            else if (line.startsWith("DIV"))   binLine = "0110 " + toBin(line);
            else                               binLine = "1111 " + toBin(line);

            binary.append("    ").append(binLine).append("\n");
        }

        return binary.toString();
    }
    
    // Converts each character in the instruction to its 8-bit ASCII binary
    private String toBin(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
            sb.append(" ");
        }
        return sb.toString();
    }
}
