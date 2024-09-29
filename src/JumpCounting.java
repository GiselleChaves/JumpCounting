import java.math.BigInteger;

public class JumpCounting {

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
}
