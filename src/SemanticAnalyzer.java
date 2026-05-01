import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SemanticAnalyzer {

    private Set<String> declaredVariables = new HashSet<>();

    // Call this when you see: INTEGER A, B, C, ...
    public void registerDeclarations(List<String> variables) {
        for (String var : variables) {
            declaredVariables.add(var.trim());
        }
        System.out.println("Declared variables: " + declaredVariables);
    }

    // Call this when checking expressions like: LET G = a + c
    public void checkVariableUsage(String variable, int lineNumber) {
        if (!declaredVariables.contains(variable)) {
            System.out.println("SEMANTIC ERROR at line " + lineNumber +
                ": Variable '" + variable + "' was not declared.");
        } else {
            System.out.println("Variable '" + variable + "' is valid.");
        }
    }
}