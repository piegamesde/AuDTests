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
				{ HashTable.HASH_FUNCTION_DIVISION, 2, "6GL7VJF0N" },
				{ HashTable.HASH_FUNCTION_FOLDING, 2, "DI5OI13UB" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 2, "813HC06VV" },
				{ HashTable.HASH_FUNCTION_DIVISION, 5, "VF38KROU9" },
				{ HashTable.HASH_FUNCTION_FOLDING, 5, "WFPKP2REA" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 5, "FCDLFTPZW" },
				{ HashTable.HASH_FUNCTION_DIVISION, 9, "95A98BH79" },
				{ HashTable.HASH_FUNCTION_FOLDING, 9, "WRYA4V2QZ" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 9, "F1JRARC3K" },
				{ HashTable.HASH_FUNCTION_DIVISION, 10, "GL18P02KE" },
				{ HashTable.HASH_FUNCTION_FOLDING, 10, "V6ZEU3Y25" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 10, "3TE7Y9YEF" },
				{ HashTable.HASH_FUNCTION_DIVISION, 11, "IVMVJPKW2" },
				{ HashTable.HASH_FUNCTION_FOLDING, 11, "UYSIOY4Z2" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 11, "XWYP0XNM5" },
				{ HashTable.HASH_FUNCTION_DIVISION, 50, "696A2FGXG" },
				{ HashTable.HASH_FUNCTION_FOLDING, 50, "U6A990WU4" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 50, "S7X970V2P" },
				{ HashTable.HASH_FUNCTION_DIVISION, 101, "MCDBYEGO0" },
				{ HashTable.HASH_FUNCTION_FOLDING, 101, "U50B82S1C" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 101, "GG0EBRD5J" },
				{ HashTable.HASH_FUNCTION_DIVISION, 497, "1P93BKQUN" },
				{ HashTable.HASH_FUNCTION_FOLDING, 497, "YK6UD36T0" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 497, "GPEFLF63J" },
				{ HashTable.HASH_FUNCTION_DIVISION, 999, "GRIPHJK8Y" },
				{ HashTable.HASH_FUNCTION_FOLDING, 999, "1HTYDQMMR" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 999, "SYDR6BA7Y" },
				{ HashTable.HASH_FUNCTION_DIVISION, 1001, "ZJGL4JI1J" },
				{ HashTable.HASH_FUNCTION_FOLDING, 1001, "XWYQ1NFD1" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 1001, "PI2V73KBP" },
				{ HashTable.HASH_FUNCTION_DIVISION, 2048, "9I5HPFIVM" },
				{ HashTable.HASH_FUNCTION_FOLDING, 2048, "9GSZ578BY" },
				{ HashTable.HASH_FUNCTION_MIDSQUARE, 2048, "Q58FQV2T7" }
		});
	}

	@Test
	public void hashTest() throws IOException {
		HashTable table = new HashTable(capacity, algorithm, "linear_probing");
		assertEquals(0, table.hash(key));
	}
}
