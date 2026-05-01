
public class Codeopt {
    public String optimise(String asmCode){

        String[] lines = asmCode.split("\n");
        StringBuilder optimised = new StringBuilder();

        String prevStore = "";  

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("LOAD") && prevStore != "") {
                String loadSrc = line.replace("LOAD  R1,", "").trim();
                if (loadSrc.equals(prevStore)) {
                    optimised.append("    ; [optimised away: ").append(line).append("]\n");
                    continue;
                }
            }

            optimised.append("    ").append(line).append("\n");

            
            if (line.startsWith("STORE")) {
                prevStore = line.replace("STORE R1,", "").trim();
            } else {
                prevStore = "";
            }
        }
                return optimised.toString();

    }
}
