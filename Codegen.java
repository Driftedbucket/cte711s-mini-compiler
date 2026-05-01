
public class Codegen {
    
    public String generate(String icrOutput){
        String[] icrlines = icrOutput.split("\n");
        StringBuilder assemblyCode = new StringBuilder();

        for(String icrline:icrlines){
            icrline=icrline.trim();
            if(icrline.isEmpty()) continue;

            String[] parts = icrline.split("=",2);
            if(parts.length<2)continue;

            String dest = parts[0].trim();
            String expr = parts[1].trim();
    }
}
}
