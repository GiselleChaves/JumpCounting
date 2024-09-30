import java.math.BigInteger;
import java.util.Arrays;

public class JumpCounting {

  //Recursive Counter Solution
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

    // If the chacracter in the position is equal 0 return 0 
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

  // Unique method that combines initialization logic and recursion with memorization
  public static BigInteger calculateWays(String path) {
    int pathLength = path.length();
    BigInteger[][] memo = new BigInteger[pathLength][2]; // Matriz para memorização [posição][lastJumpWas3]
    for (int i = 0; i < pathLength; i++) {
        Arrays.fill(memo[i], null); // Inicializa a matriz com null
    }

    return recursiveJumpsWithMemorization(path, 0, false, memo, pathLength); // Chama a função recursiva com memorização
  }


  // Recursive + Memorization solution
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

  // Função Iterativa
  public static BigInteger iterativeJumps(String path) {
    int pathLength = path.length(); 

    //Variables for value swaps
    BigInteger value1 = BigInteger.ONE, value2 = BigInteger.ZERO, value3 = BigInteger.ZERO, 
    value4 = BigInteger.ZERO, value5 = BigInteger.ZERO, result = BigInteger.ZERO;

    // Loop to process the jump path
    for (int i = 0; i < pathLength - 1; i++) {
      if (path.charAt(i + 1) == '1' || path.charAt(i + 1) == 'm') { // If the next position is a rock
        if (i < 5 || value3.equals(BigInteger.ZERO)) {
          result = value3.add(value2).add(value1); // Case where n3 has not been updated yet
        } else {
          result = value5.add(value4).add(value2).add(value1); // Case where a jump of 3 meters has already been made
        }

        // Updating variable values
        value5 = value4;
        value4 = value3;
        value3 = value2;
        value2 = value1;
        value1 = result;

          
      } else if (path.charAt(i + 1) == '0') { // If the next position is a hole

        value5 = value4;
        value4 = value3;
        value3 = value2;
        value2 = value1;
        value1 = BigInteger.ZERO;
      }
    }
    return value1;
  }
}
