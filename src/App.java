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

        BigInteger result = JumpCounting.recursiveJumps(path, 0, false); 

        System.out.println("Quantidade de maneiras de cruzar o rio: " + result);
    }
}
