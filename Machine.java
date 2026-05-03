
public class Machine {

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
