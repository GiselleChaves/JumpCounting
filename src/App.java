import java.math.BigInteger;

public class App {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("--------------------------------------------------------");
            System.out.println(" Erro. Forneça a entrada do caminho (ex: m10101100111m).");
            System.out.println("--------------------------------------------------------");
            return;
        }

        // The input path is the first argument
        String path = args[0];  

        BigInteger resultRecursiveJumps = JumpCounting.recursiveJumps(path, 0, false); 
        BigInteger resultRecursiveMemorizationJumps = JumpCounting.calculateWays(path); 
        BigInteger resultIterativeJumps = JumpCounting.iterativeJumps(path); 

        System.out.println("Recursão simples: " + resultRecursiveJumps);
        System.out.println("Recursão memorizada: " + resultRecursiveMemorizationJumps);
        System.out.println("Sem recursão: " + resultIterativeJumps);
    }
}
