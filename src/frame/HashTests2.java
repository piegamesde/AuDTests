package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import lab.Constants;
import lab.HashTable;

@RunWith(value = Parameterized.class)
public class HashTests2 {

	public static final int[]	CAPACITIES	= { 2, 5, 9, 10, 11, 50, 101, 497, 999, 1001, 2048 };

	@Parameter(value = 0)
	public String				algorithm;

	@Parameter(value = 1)
	public int					capacity;

	@Parameter(value = 2)
	public String				key;

	@Parameters(name = "{index}: hash({0}, {2})=0")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ Constants.HASH_FUNCTION_DIVISION, 2, "Q07DLKQG8" },
				{ Constants.HASH_FUNCTION_FOLDING, 2, "E4ZZRMY97" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 2, "NX60DS9QF" },
				{ Constants.HASH_FUNCTION_DIVISION, 5, "JH7S7X5W9" },
				{ Constants.HASH_FUNCTION_FOLDING, 5, "86QR8U3VA" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 5, "Y7OSV4P47" },
				{ Constants.HASH_FUNCTION_DIVISION, 9, "YLNTEGN3F" },
				{ Constants.HASH_FUNCTION_FOLDING, 9, "0798W25NV" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 9, "1GKZBNZEX" },
				{ Constants.HASH_FUNCTION_DIVISION, 10, "P70XZ1WHS" },
				{ Constants.HASH_FUNCTION_FOLDING, 10, "F9RN39KGH" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 10, "PKBL02XAR" },
				{ Constants.HASH_FUNCTION_DIVISION, 11, "89232WMW8" },
				{ Constants.HASH_FUNCTION_FOLDING, 11, "NJGXAG9Z8" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 11, "GV87E6DV6" },
				{ Constants.HASH_FUNCTION_DIVISION, 50, "6DID2R1BV" },
				{ Constants.HASH_FUNCTION_FOLDING, 50, "5PPFFM99R" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 50, "8PN2E8WTQ" },
				{ Constants.HASH_FUNCTION_DIVISION, 101, "C1KLT5EU5" },
				{ Constants.HASH_FUNCTION_FOLDING, 101, "ZJJHURJBQ" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 101, "IFLTD3G62" },
				{ Constants.HASH_FUNCTION_DIVISION, 497, "R7UD65ZIK" },
				{ Constants.HASH_FUNCTION_FOLDING, 497, "5HM91WXLG" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 497, "MBTYKCL1Q" },
				{ Constants.HASH_FUNCTION_DIVISION, 999, "1HA5CELNE" },
				{ Constants.HASH_FUNCTION_FOLDING, 999, "CMLF6FW07" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 999, "7YUEK62N1" },
				{ Constants.HASH_FUNCTION_DIVISION, 1001, "HORK79ABB" },
				{ Constants.HASH_FUNCTION_FOLDING, 1001, "78KHPLJJL" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 1001, "TL7LJSYTF" },
				{ Constants.HASH_FUNCTION_DIVISION, 2048, "ZZTWDWU3I" },
				{ Constants.HASH_FUNCTION_FOLDING, 2048, "SLUFL6TY6" },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 2048, "I5YRVFSVQ" }
		});
	}

	@Test
	public void hashTest() throws IOException {
		HashTable table = new HashTable(capacity, algorithm, "linear_probing");
		assertEquals(0, table.hash(key));
	}
}
