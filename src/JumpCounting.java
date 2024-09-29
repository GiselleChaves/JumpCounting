import java.math.BigInteger;
import java.util.Arrays;

public class JumpCounting {

  //Solução de contador recursivo
  public static BigInteger recursiveJumps(String path, int pos, boolean lastJumpWas3) {
    int pathLength = path.length();

    // Count the number of ways to cross from this position
    BigInteger ways = BigInteger.ZERO;

    // Base case: If already reached the last stone (or final margin)
    if (pos == pathLength - 1) { 
      return BigInteger.ONE; // Reached the final margin
    }

    // If the current position has no stone or is out of bounds, cannot continue
    if (pos >= pathLength) { 
      return BigInteger.ZERO;
    }

    if (path.charAt(pos) == '0') {
      return BigInteger.ZERO;
    }

    // Try to jump 1 meter ahead
    ways = ways.add(recursiveJumps(path, pos + 1, false));

    // Try to jump 2 meters ahead
    ways = ways.add(recursiveJumps(path, pos + 2, false));

    // Try to jump 3 meters ahead, only if the last jump was not 3 meters
    if (!lastJumpWas3) {
      ways = ways.add(recursiveJumps(path, pos + 3, true));
    }

    return ways;
  }

  // Método único que combina a lógica de inicialização e a recursão com memorização
  public static BigInteger calculateWays(String path) {
    int pathLength = path.length();
    BigInteger[][] memo = new BigInteger[pathLength][2]; // Matriz para memorização [posição][lastJumpWas3]
    for (int i = 0; i < pathLength; i++) {
        Arrays.fill(memo[i], null); // Inicializa a matriz com null
    }

    return recursiveJumpsWithMemorization(path, 0, false, memo, pathLength); // Chama a função recursiva com memorização
}


// Solução Recursiva + Memorização
private static BigInteger recursiveJumpsWithMemorization(String path, int pos, boolean lastJumpWas3, BigInteger[][] memo, int pathLength) {

    // Contar o número de maneiras de cruzar a partir dessa posição
    BigInteger ways = BigInteger.ZERO;

    // Caso base: Se já atingiu a última pedra (ou margem final)
    if (pos == pathLength - 1) { 
      return BigInteger.ONE; // Chegou à margem final
    }

    // If the current position has no stone or is out of bounds, cannot continue
    if (pos >= pathLength || path.charAt(pos) == '0') { 
      return BigInteger.ZERO;
    }

    // Convert boolean to index (0 for false, 1 for true)
    int lastJumpWas3Index = lastJumpWas3 ? 1 : 0;

    // Se já calculamos essa posição com o estado atual de lastJumpWas3, retornamos o resultado armazenado
    if (memo[pos][lastJumpWas3Index] != null) {
      return memo[pos][lastJumpWas3Index];
    }

    // Tentar pular 1 metro à frente
    ways = ways.add(recursiveJumpsWithMemorization(path, pos + 1, false, memo, pathLength));

    // Tentar pular 2 metros à frente
    ways = ways.add(recursiveJumpsWithMemorization(path, pos + 2, false, memo, pathLength));

    // Tentar pular 3 metros à frente, somente se o último pulo não foi de 3 metros
    if (!lastJumpWas3) {
      ways = ways.add(recursiveJumpsWithMemorization(path, pos + 3, true, memo, pathLength));
    }

    // Armazena o resultado calculado (memoização) para essa posição e estado de lastJumpWas3
    memo[pos][lastJumpWas3Index] = ways;
    return ways;
  }

  //SOLUÇÃO ITERATIVA
  public static BigInteger iterativeJumps(String path) {

    int pathLength = path.length();

    if (pathLength == 0) {
      return BigInteger.ZERO; // Retorna 0 se não houver caminho
    }

    if (path.charAt(0) == '0') {
        return BigInteger.ZERO; // Não há maneira de começar se o primeiro é um buraco
    }

    if (pathLength == 1) { 
      return BigInteger.ONE; // Reached the final margin
    }
    
    BigInteger[] ways = new BigInteger[pathLength]; // Array para armazenar o número de maneiras
    Arrays.fill(ways, BigInteger.ZERO); // Inicializa todos os elementos com 0

    // A primeira posição (margem inicial) é uma maneira válida
    ways[0] = BigInteger.ONE; // Há uma maneira de estar na margem inicial


    for (int pos = 0; pos < pathLength; pos++) {
    // Se estamos em uma posição que não é um buraco
      if (path.charAt(pos) != '0') {
        // Pular 1 metro à frente
        if (pos + 1 < pathLength && path.charAt(pos + 1) != '0') {
          System.out.println("Trying to jump 1 meter from position: " + pos);
            System.out.println("Position + 1: " + (pos + 1) + ", Value: " + path.charAt(pos + 1));
            System.out.println("Jumping 1 meter is valid from position: " + pos);
            System.out.println(ways[pos]);
            ways[pos + 1] = ways[pos + 1].add(ways[pos]);
        } else {
            System.out.println("Jumping 1 meter is NOT valid from position: " + pos);
        }
        // Pular 2 metros à frente
        if (pos + 2 < pathLength && path.charAt(pos + 2) != '0') {
          System.out.println("Trying to jump 2 meters from position: " + pos);
            System.out.println("Position + 2: " + (pos + 2) + ", Value: " + path.charAt(pos + 2));
            System.out.println("Jumping 2 meters is valid from position: " + pos);
            System.out.println(ways[pos]);
            ways[pos + 2] = ways[pos + 2].add(ways[pos]);
        } else {
            System.out.println("Jumping 2 meters is NOT valid from position: " + pos);
        }
        // Pular 3 metros à frente, somente se o último pulo não foi de 3 metros
        if (pos + 3 < pathLength && path.charAt(pos + 3) != '0') {
          if (pos < 2 || (path.charAt(pos - 1) != '0' && path.charAt(pos - 2) != '0')) {
            System.out.println("Trying to jump 3 meters from position: " + pos);
            System.out.println("Position + 3: " + (pos + 3) + ", Value: " + path.charAt(pos + 3));
            System.out.println("Jumping 3 meters is valid from position: " + pos);
            System.out.println(ways[pos]);
            ways[pos + 3] = ways[pos + 3].add(ways[pos]);
        } else {
            System.out.println("Jumping 3 meters is NOT valid from position: " + pos);
        }
      }
    }
  }
    
    // O resultado é o número de maneiras de chegar à última posição (margem final)
    return ways[pathLength - 1];
  }
}

