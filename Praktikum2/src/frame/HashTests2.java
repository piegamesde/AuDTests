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

	public static final int[]	CAPACITIES	= { 2, 5, 9, 10, 11, 50, 101, 497, 1001, 5000, 9867 };

	@Parameter(value = 0)
	public String				algorithm;

	@Parameter(value = 1)
	public int					capacity;

	@Parameter(value = 2)
	public String[]				keys;

	@Parameters(name = "hash({2})=0 with {0} and capacity {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ Constants.HASH_FUNCTION_FOLDING, 2, new String[] { "8E926", "YZI1C", "0TTXG" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 2, new String[] { "7951D", "GJOJJ", "CEW0Q" } },
				{ Constants.HASH_FUNCTION_FOLDING, 5, new String[] { "LSB4B", "37J3F", "WZV5M" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 5, new String[] { "SUZZZ", "E1WEM", "XF4Q6" } },
				{ Constants.HASH_FUNCTION_FOLDING, 9, new String[] { "4XZZZ", "7BWPE", "FWWRT" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 9, new String[] { "R82G7", "1YS0G", "BAIWO" } },
				{ Constants.HASH_FUNCTION_FOLDING, 10, new String[] { "HOCJ8", "X1D3J", "8T4XV", "Y3SBD" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 10, new String[] { "KPZZZ", "KRWQD", "ZHKP4" } },
				{ Constants.HASH_FUNCTION_FOLDING, 11, new String[] { "0BK2C", "QLCHK", "5ASQU", "O414H" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 11, new String[] { "GYHCA", "6O8ML", "6L2CR" } },
				{ Constants.HASH_FUNCTION_FOLDING, 50, new String[] { "6E1VA", "33G2L", "PG2DT" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 50, new String[] { "QZURC", "NA2DK", "3Y8AV" } },
				{ Constants.HASH_FUNCTION_FOLDING, 101, new String[] { "HDTZZ", "GJUEE", "IY854" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 101, new String[] { "R8ZK9", "MMPYH", "VL69R" } },
				{ Constants.HASH_FUNCTION_FOLDING, 497, new String[] { "FEBY5", "9CU5I", "NT3BT" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 497, new String[] { "7ZRPE", "0WA9M", "LIIYT" } },
				{ Constants.HASH_FUNCTION_FOLDING, 1001, new String[] { "YH2UZ", "JK0UI", "K8FIA" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 1001, new String[] { "4S4D5", "XVTSI", "DID3T" } },
				{ Constants.HASH_FUNCTION_FOLDING, 5000, new String[] { "INU30", "SJA5I", "BVIHO" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 5000, new String[] { "MMS05", "OYUUH", "VHD4R" } },
				{ Constants.HASH_FUNCTION_FOLDING, 9867, new String[] { "LUYW6", "TKRQC", "5WA5M" } },
				{ Constants.HASH_FUNCTION_MIDSQUARE, 9867, new String[] { "EZ3N6", "UXU1P", "EFFOY" } }
		});
	}

	@Test
	public void hashTestZero() throws IOException {
		HashTable table = new HashTable(capacity, algorithm, "linear_probing");
		for (String key : keys)
			assertEquals(0, table.hash(key));
	}
}
