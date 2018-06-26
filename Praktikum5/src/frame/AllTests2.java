package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import lab.MaxFlow;

/**
 * MaxFlowTestCase.java
 */

/**
 *
 */
public class AllTests2 {

	private Duration timeout = Duration.ofMillis(1000);

	////////////////// ************************************************************[new
	////////////////// tests]
	@Test
	public final void testFindMaxFlow_Iksburg7_A_to_E() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "E" };
			assertEquals(21, testMaxFlow("Iksburg7", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testEdges_Iksburg7_A_E() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "E" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg7").findResidualNetwork(sources, destinations);

			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*B"), "Edge A->B was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*C"), "Edge A->C was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*D"), "Edge A->D was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "B[ ]*->[ ]*C"), "Edge B->C was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "B[ ]*->[ ]*E"), "Edge B->E was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "C[ ]*->[ ]*E"), "Edge C->E was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "D[ ]*->[ ]*E"), "Edge D->E was wrongly bolded!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg7_AC_to_E() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "C" };
			String[] destinations = { "E" };
			int maxFlow = testMaxFlow("Iksburg7", sources, destinations);
			assertEquals(23, maxFlow, "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg7_AB_to_ED() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "B" };
			String[] destinations = { "E", "D" };
			int maxFlow = testMaxFlow("Iksburg7", sources, destinations);
			assertEquals(26, maxFlow, "MaxFlow not correct!");
		});
	}

	@Test
	public final void testSourcesAndSinks_Iksburg7_AB_to_ED() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "B" };
			String[] destinations = { "E", "D" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg7").findResidualNetwork(sources, destinations);
			assertTrue(areSourcesSinksMarkedRight(flowGraph, sources, destinations),
					"Sources and/or sinks are not marked correctly!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg5_A_to_E() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "E" };
			assertEquals(15, testMaxFlow("Iksburg5", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg5_C_to_E() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "C" };
			String[] destinations = { "E" };
			assertEquals(16, testMaxFlow("Iksburg5", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg5_AC_to_DE() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "C" };
			String[] destinations = { "D", "E" };
			assertEquals(28, testMaxFlow("Iksburg5", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg5_no_source() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X" };
			String[] destinations = { "E" };
			int x = testMaxFlow("Iksburg5", sources, destinations);
			assertEquals(-1, x, "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg5_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "Y" };
			assertEquals(-2, testMaxFlow("Iksburg5", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg5_no_source_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X" };
			String[] destinations = { "Y" };
			assertEquals(-3, testMaxFlow("Iksburg5", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg5_no_path() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "E" };
			String[] destinations = { "A" };
			assertEquals(-4, testMaxFlow("Iksburg5", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg5_source_identical_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "B" };
			String[] destinations = { "B" };
			assertEquals(Integer.MAX_VALUE, testMaxFlow("Iksburg5", sources, destinations), "MaxFlow not correct!");
		});
	}

	/////////////////////// ---------------------- test file 6

	@Test
	public final void testFindMaxFlow_Iksburg6_HI_to_J() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "H", "I" };
			String[] destinations = { "J" };
			assertEquals(55, testMaxFlow("Iksburg6", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testCuts_Iksburg6_HI_J() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "H", "I" };
			String[] destinations = { "J" };
			String[] cut = new String[] { "H[ ]*->[ ]*J", "I[ ]*->[ ]*J" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg6").findResidualNetwork(sources, destinations);

			assertEquals(55, cutFlow(flowGraph, cut), "The cut is different from max flow!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_CJ_to_G() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "C", "J" };
			String[] destinations = { "G" };
			int maxFlow = testMaxFlow("Iksburg6", sources, destinations);
			assertEquals(13, maxFlow, "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_D_to_H() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "D" };
			String[] destinations = { "H" };
			assertEquals(10, testMaxFlow("Iksburg6", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testCuts_Iksburg6_D_H() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "D" };
			String[] destinations = { "H" };
			String[] cut = new String[] { "G[ ]*->[ ]*H" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg6").findResidualNetwork(sources, destinations);

			assertEquals(10, cutFlow(flowGraph, cut), "The cut is different from max flow!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_A_to_E() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "E" };
			assertEquals(15, testMaxFlow("Iksburg6", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_A_to_J() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "J" };
			assertEquals(14, testMaxFlow("Iksburg6", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_no_source() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X" };
			String[] destinations = { "E" };
			int x = testMaxFlow("Iksburg6", sources, destinations);
			assertEquals(-1, x, "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "Z" };
			assertEquals(-2, testMaxFlow("Iksburg6", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_no_source_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X" };
			String[] destinations = { "Y" };
			assertEquals(-3, testMaxFlow("Iksburg6", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_no_path() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "E" };
			String[] destinations = { "A" };
			assertEquals(-4, testMaxFlow("Iksburg6", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg6_source_identical_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "B" };
			String[] destinations = { "B" };
			assertEquals(Integer.MAX_VALUE, testMaxFlow("Iksburg6", sources, destinations), "MaxFlow not correct!");
		});
	}

	////////////////// ************************************************************[new
	////////////////// tests]

	@Test
	public final void testFindMaxFlow_Iksburg1_A_to_F() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "F" };
			assertEquals(140, testMaxFlow("Iksburg1", sources, destinations), "MaxFlow not correct!");
		});
	}

	// new test
	@Test
	public final void testFindMaxFlow_Iksburg1_B_to_F() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "F" };
			assertEquals(140, testMaxFlow("Iksburg1", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg1_no_source() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X" };
			String[] destinations = { "F" };
			assertEquals(-1, testMaxFlow("Iksburg1", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg1_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "Y" };
			assertEquals(-2, testMaxFlow("Iksburg1", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg1_no_source_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X" };
			String[] destinations = { "Y" };
			assertEquals(-3, testMaxFlow("Iksburg1", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg1_no_path() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "F" };
			String[] destinations = { "A" };
			assertEquals(-4, testMaxFlow("Iksburg1", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg1_source_identical_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "B" };
			String[] destinations = { "B" };
			assertEquals(Integer.MAX_VALUE, testMaxFlow("Iksburg1", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg2_A_to_H() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "H" };
			assertEquals(240, testMaxFlow("Iksburg2", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg2_no_source() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X" };
			String[] destinations = { "H" };
			assertEquals(-1, testMaxFlow("Iksburg2", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg2_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "Y" };
			assertEquals(-2, testMaxFlow("Iksburg2", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg2_no_source_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X" };
			String[] destinations = { "Y" };
			assertEquals(-3, testMaxFlow("Iksburg2", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg2_no_path() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "H" };
			String[] destinations = { "A" };
			assertEquals(-4, testMaxFlow("Iksburg2", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg2_source_identical_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "F" };
			String[] destinations = { "F" };
			assertEquals(Integer.MAX_VALUE, testMaxFlow("Iksburg2", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg3_AB_to_FG() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "B" };
			String[] destinations = { "F", "G" };
			assertEquals(30, testMaxFlow("Iksburg3", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg3_no_source() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X", "Y" };
			String[] destinations = { "F", "G" };
			assertEquals(-1, testMaxFlow("Iksburg3", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg3_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "B" };
			String[] destinations = { "X", "Y" };
			assertEquals(-2, testMaxFlow("Iksburg3", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg3_no_source_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "X", "Y" };
			String[] destinations = { "Z", "W" };
			assertEquals(-3, testMaxFlow("Iksburg3", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg3_no_path() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "F" };
			String[] destinations = { "B" };
			assertEquals(-4, testMaxFlow("Iksburg3", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testFindMaxFlow_Iksburg3_source_identical_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "B" };
			String[] destinations = { "A", "B" };
			assertEquals(Integer.MAX_VALUE, testMaxFlow("Iksburg3", sources, destinations), "MaxFlow not correct!");
		});
	}

	// new test
	@Test
	public final void testFindMaxFlow_Iksburg4_Luisenplatz_Kantplatz_to_ILPlatz() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "Luisenplatz", "Kantplatz" };
			String[] destinations = { "Ilse_Langner_Platz" };
			assertEquals(42, testMaxFlow("Iksburg4", sources, destinations), "MaxFlow not correct!");
		});
	}

	// new test
	@Test
	public final void testFindMaxFlow_Iksburg4_no_source() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "Someplatz" };
			String[] destinations = { "Ilse_Langner_Platz" };
			assertEquals(-1, testMaxFlow("Iksburg4", sources, destinations), "MaxFlow not correct!");
		});
	}

	// new test
	@Test
	public final void testFindMaxFlow_Iksburg4_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "Luisenplatz" };
			String[] destinations = { "Someplatz" };
			assertEquals(-2, testMaxFlow("Iksburg4", sources, destinations), "MaxFlow not correct!");
		});
	}

	// new test
	@Test
	public final void testFindMaxFlow_Iksburg4_no_source_no_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "XPlatz", "YPlatz" };
			String[] destinations = { "ZPlatz", "WPlatz" };
			assertEquals(-3, testMaxFlow("Iksburg4", sources, destinations), "MaxFlow not correct!");
		});
	}

	// new test
	@Test
	public final void testFindMaxFlow_Iksburg4_no_path() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "Marktplatz" };
			String[] destinations = { "Luisenplatz" };
			assertEquals(-4, testMaxFlow("Iksburg4", sources, destinations), "MaxFlow not correct!");
		});
	}

	// new test
	@Test
	public final void testFindMaxFlow_Iksburg4_source_identical_dest() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "Luisenplatz", "Kantplatz" };
			String[] destinations = { "Luisenplatz", "Kantplatz" };
			assertEquals(Integer.MAX_VALUE, testMaxFlow("Iksburg4", sources, destinations), "MaxFlow not correct!");
		});
	}

	@Test
	public final void testSourcesAndSinks_Iksburg1_A_to_F() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "F" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg1").findResidualNetwork(sources, destinations);

			assertTrue(areSourcesSinksMarkedRight(flowGraph, sources, destinations),
					"Sources and/or sinks are not marked correctly!");
		});
	}

	@Test
	public final void testSourcesAndSinks_Iksburg2_A_to_H() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "H" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg2").findResidualNetwork(sources, destinations);

			assertTrue(areSourcesSinksMarkedRight(flowGraph, sources, destinations),
					"Sources and/or sinks are not marked correctly!");
		});
	}

	@Test
	public final void testSourcesAndSinks_Iksburg3_AB_to_FG() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "B" };
			String[] destinations = { "F", "G" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg3").findResidualNetwork(sources, destinations);

			assertTrue(areSourcesSinksMarkedRight(flowGraph, sources, destinations),
					"Sources and/or sinks are not marked correctly!");
		});
	}

	// new test
	@Test
	public final void testSourcesAndSinks_Iksburg4_Luisenplatz_Kantplatz_to_ILPlatz() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "Luisenplatz", "Kantplatz" };
			String[] destinations = { "Ilse_Langner_Platz" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg4").findResidualNetwork(sources, destinations);

			assertTrue(areSourcesSinksMarkedRight(flowGraph, sources, destinations),
					"Sources and/or sinks are not marked correctly!");
		});
	}

	@Test
	public final void testEdges_Iksburg1_A_F() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "F" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg1").findResidualNetwork(sources, destinations);

			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*B"), "Edge A->B was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*C"), "Edge A->C was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "B[ ]*->[ ]*D"), "Edge B->D was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "B[ ]*->[ ]*E"), "Edge B->E was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "C[ ]*->[ ]*D"), "Edge C->D was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "C[ ]*->[ ]*E"), "Edge C->E was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "D[ ]*->[ ]*F"), "Edge D->F was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "E[ ]*->[ ]*F"), "Edge E->F was wrongly bolded!");
		});
	}

	@Test
	public final void testEdges_Iksburg2_A_H() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "H" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg2").findResidualNetwork(sources, destinations);

			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*B"), "Edge A->B was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*C"), "Edge A->C was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*D"), "Edge A->D was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "B[ ]*->[ ]*E"), "Edge B->E was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "B[ ]*->[ ]*F"), "Edge B->F was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "C[ ]*->[ ]*E"), "Edge C->E was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "C[ ]*->[ ]*F"), "Edge C->F was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "C[ ]*->[ ]*G"), "Edge C->G was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "D[ ]*->[ ]*F"), "Edge D->F was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "D[ ]*->[ ]*G"), "Edge D->G was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "E[ ]*->[ ]*H"), "Edge E->H was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "F[ ]*->[ ]*H"), "Edge F->H was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "G[ ]*->[ ]*H"), "Edge G->H was wrongly bolded!");
		});
	}

	@Test
	public final void testEdges_Iksburg3_AB_FG() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "B" };
			String[] destinations = { "F", "G" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg3").findResidualNetwork(sources, destinations);

			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*C"), "Edge A->C was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "A[ ]*->[ ]*D"), "Edge A->D was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "B[ ]*->[ ]*D"), "Edge B->D was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "B[ ]*->[ ]*E"), "Edge B->E was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "C[ ]*->[ ]*F"), "Edge C->F was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "D[ ]*->[ ]*C"), "Edge D->C was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "D[ ]*->[ ]*F"), "Edge D->F was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "D[ ]*->[ ]*G"), "Edge D->G was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "D[ ]*->[ ]*E"), "Edge D->E was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "E[ ]*->[ ]*G"), "Edge E->G was wrongly bolded!");
		});
	}

	// new test
	@Test
	public final void testEdges_Iksburg4_Luisenplatz_Kantplatz_to_ILPlatz() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "Luisenplatz", "Kantplatz" };
			String[] destinations = { "Ilse_Langner_Platz" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg4").findResidualNetwork(sources, destinations);

			assertTrue(isEdgeMarkedRight(flowGraph, "Luisenplatz[ ]*->[ ]*Taunus_Platz"),
					"Edge Luisenplatz -> Taunus_Platz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Luisenplatz[ ]*->[ ]*Schlossgartenplatz"),
					"Edge Luisenplatz  -> Schlossgartenplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Kantplatz[ ]*->[ ]*Schlossgartenplatz"),
					"Edge Kantplatz -> Schlossgartenplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Kantplatz[ ]*->[ ]*Ernst_Ludwigsplatz"),
					"Edge Kantplatz  -> Ernst_Ludwigsplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Kantplatz[ ]*->[ ]*Marktplatz"),
					"Edge Kantplatz  -> Marktplatz->F was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Taunus_Platz[ ]*->[ ]*Friedensplatz"),
					"Edge Taunus_Platz -> Friedensplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Taunus_Platz[ ]*->[ ]*Kopernikusplatz"),
					"Edge Taunus_Platz -> Kopernikusplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Schlossgartenplatz[ ]*->[ ]*Taunus_Platz"),
					"Edge Schlossgartenplatz -> Taunus_Platz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Schlossgartenplatz[ ]*->[ ]*Kennedyplatz"),
					"Edge Schlossgartenplatz -> Kennedyplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Ernst_Ludwigsplatz[ ]*->[ ]*Marktplatz"),
					"Edge Ernst_Ludwigsplatz -> Marktplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Ernst_Ludwigsplatz[ ]*->[ ]*Kennedyplatz"),
					"Edge Ernst_Ludwigsplatz -> Kennedyplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Friedensplatz[ ]*->[ ]*Marktplatz"),
					"Edge Friedensplatz -> Marktplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Friedensplatz[ ]*->[ ]*Platz_der_deutschen_Einheit"),
					"Edge Friedensplatz -> Platz_der_deutschen_Einheit was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Marktplatz[ ]*->[ ]*Ilse_Langner_Platz"),
					"Edge Marktplatz -> Ilse_Langner_Platz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Kennedyplatz[ ]*->[ ]*Ilse_Langner_Platz"),
					"Edge Kennedyplatz -> Ilse_Langner_Platz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Platz_der_deutschen_Einheit[ ]*->[ ]*Marktplatz"),
					"Edge Platz_der_deutschen_Einheit -> Marktplatz was wrongly bolded!");
			assertTrue(isEdgeMarkedRight(flowGraph, "Kopernikusplatz[ ]*->[ ]*Ilse_Langner_Platz"),
					"Edge Kopernikusplatz -> Ilse_Langner_Platz was wrongly bolded!");
		});
	}

	@Test
	public final void testCuts_Iksburg1_A_F() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "F" };
			String[] cut1 = new String[] { "A[ ]*->[ ]*B", "A[ ]*->[ ]*C" };
			String[] cut2 = new String[] { "D[ ]*->[ ]*F", "E[ ]*->[ ]*F" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg1").findResidualNetwork(sources, destinations);

			assertEquals(140, cutFlow(flowGraph, cut1), "The cut1 is different from max flow!");

			assertEquals(140, cutFlow(flowGraph, cut2), "The cut2 is different from max flow!");
		});
	}

	@Test
	public final void testCuts_Iksburg2_A_H() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A" };
			String[] destinations = { "H" };
			String[] cut1 = new String[] { "A[ ]*->[ ]*B", "A[ ]*->[ ]*D", "A[ ]*->[ ]*C" };
			String[] cut2 = new String[] { "B[ ]*->[ ]*E", "B[ ]*->[ ]*F", "C[ ]*->[ ]*E", "C[ ]*->[ ]*F",
					"C[ ]*->[ ]*G", "D[ ]*->[ ]*F", "D[ ]*->[ ]*G" };
			String[] cut3 = new String[] { "B[ ]*->[ ]*E", "C[ ]*->[ ]*E", "F[ ]*->[ ]*H", "G[ ]*->[ ]*H" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg2").findResidualNetwork(sources, destinations);

			assertEquals(240, cutFlow(flowGraph, cut1), "The cut1 is different from max flow!");

			assertEquals(240, cutFlow(flowGraph, cut2), "The cut2 is different from max flow!");

			assertEquals(240, cutFlow(flowGraph, cut3), "The cut3 is different from max flow!");
		});
	}

	@Test
	public final void testCuts_Iksburg3_AB_FG() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "A", "B" };
			String[] destinations = { "F", "G" };
			String[] cut1 = new String[] { "A[ ]*->[ ]*C", "A[ ]*->[ ]*D", "B[ ]*->[ ]*D", "B[ ]*->[ ]*E" };
			String[] cut2 = new String[] { "A[ ]*->[ ]*C", "D[ ]*->[ ]*C", "D[ ]*->[ ]*F", "D[ ]*->[ ]*G",
					"E[ ]*->[ ]*G" };
			String[] cut3 = new String[] { "C[ ]*->[ ]*F", "D[ ]*->[ ]*F", "D[ ]*->[ ]*G", "E[ ]*->[ ]*G" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg3").findResidualNetwork(sources, destinations);

			assertEquals(30, cutFlow(flowGraph, cut1), "The cut1 is different from max flow!");

			assertEquals(30, cutFlow(flowGraph, cut2), "The cut2 is different from max flow!");

			assertEquals(30, cutFlow(flowGraph, cut3), "The cut3 is different from max flow!");
		});
	}

	// new test
	@Test
	public final void testCuts_Iksburg4_Luisenplatz_Kantplatz_to_ILPlatz() {
		assertTimeoutPreemptively(timeout, () -> {
			String[] sources = { "Luisenplatz", "Kantplatz" };
			String[] destinations = { "Ilse_Langner_Platz" };
			String[] cut1 = new String[] { "Kopernikusplatz[ ]*->[ ]*Ilse_Langner_Platz",
					"Marktplatz[ ]*->[ ]*Ilse_Langner_Platz", "Kennedyplatz[ ]*->[ ]*Ilse_Langner_Platz" };
			String[] cut2 = new String[] { "Taunus_Platz[ ]*->[ ]*Kopernikusplatz",
					"Taunus_Platz[ ]*->[ ]*Friedensplatz", "Kantplatz[ ]*->[ ]*Marktplatz",
					"Kennedyplatz[ ]*->[ ]*Ilse_Langner_Platz", "Ernst_Ludwigsplatz[ ]*->[ ]*Marktplatz" };
			String[] cut3 = new String[] { "Luisenplatz[ ]*->[ ]*Taunus_Platz",
					"Luisenplatz[ ]*->[ ]*Schlossgartenplatz", "Kantplatz[ ]*->[ ]*Schlossgartenplatz",
					"Kantplatz[ ]*->[ ]*Marktplatz", "Kantplatz[ ]*->[ ]*Ernst_Ludwigsplatz" };
			ArrayList<String> flowGraph = new MaxFlow("Iksburg4").findResidualNetwork(sources, destinations);

			assertEquals(42, cutFlow(flowGraph, cut1), "The cut1 is different from max flow!");
			assertEquals(42, cutFlow(flowGraph, cut2), "The cut2 is different from max flow!");
			assertEquals(42, cutFlow(flowGraph, cut3), "The cut3 is different from max flow!");
		});
	}

	private int cutFlow(final ArrayList<String> graph, final String[] cut) {
		int usedEdgeCapacity = 0;
		int cutFlow = 0;

		for (String edge : cut) {
			for (String graphElement : graph) {
				if (graphElement.matches(".*" + edge + ".*")) {
					Scanner s = new Scanner(graphElement);
					String diff = s.findInLine("\\\".*?\\\"");
					s.close();
					String[] edgeLabel = diff.split("\"|-");
					usedEdgeCapacity = Integer.valueOf(edgeLabel[2].trim()).intValue();
				}
			}
			cutFlow += usedEdgeCapacity;
		}
		return cutFlow;
	}

	private boolean isEdgeMarkedRight(final ArrayList<String> graph, final String edge) {
		int maxEdgeCapacity = 0;
		int usedEdgeCapacity = 0;
		boolean isBold = false;

		// calculate edge
		for (String graphElement : graph) {
			if (graphElement.matches(".*" + edge + ".*")) {
				Scanner s = new Scanner(graphElement);
				String diff = s.findInLine("\\\".*?\\\"");
				s.close();
				String[] edgeLabel = diff.split("\"|-");
				maxEdgeCapacity = Integer.valueOf(edgeLabel[1].trim()).intValue();
				usedEdgeCapacity = Integer.valueOf(edgeLabel[2].trim()).intValue();
			}
		}

		// verify if edge is bold or not
		for (String graphElement : graph) {
			if (graphElement.matches(".*" + edge + ".*")) {
				if (graphElement.matches(".*style[ ]*=[ ]*bold.*")) {
					isBold = true;
				} else
					isBold = false;
			}

		}

		if (((maxEdgeCapacity != usedEdgeCapacity) && (isBold)) || ((maxEdgeCapacity == usedEdgeCapacity) && (!isBold)))
			return true;
		else
			return false;
	}

	/*
	 * private boolean matchEdge(final ArrayList<String> graph, final String edge,
	 * final int maxValue, final int actValue) { for (String graphElement : graph) {
	 * if(graphElement.matches(".*"+edge+".*")) { return
	 * graphElement.matches(".*\"[ ]*"+maxValue+"[ ]*-[ ]*"+actValue+"[ ]*\".*"); }
	 * } return false; }
	 */

	private boolean areSourcesSinksMarkedRight(final ArrayList<String> graph, final String[] sources,
			final String[] destinations) {
		boolean correct = true;
		int sourceMatches = 0;
		int destinationMatches = 0;

		for (String source : sources) {
			correct = false;
			for (String graphElement : graph) {
				if (graphElement.matches(".*=[ ]*doublecircle.*")) {
					if (graphElement.matches(".*" + source + ".*")) {
						sourceMatches++;
						correct = true;
					}
				}
			}
			if (!correct)
				return false;
		}
		for (String destination : destinations) {
			correct = false;
			for (String graphElement : graph) {
				if (graphElement.matches(".*=[ ]*circle.*")) {
					if (graphElement.matches(".*" + destination + ".*")) {
						destinationMatches++;
						correct = true;
					}
				}
			}
			if (!correct)
				return false;
		}

		return (sourceMatches == sources.length) && (destinationMatches == destinations.length);
	}

	private int testMaxFlow(final String filename, final String[] sources, final String[] destinations) {
		MaxFlow mFlow = new MaxFlow(filename);
		return mFlow.findMaxFlow(sources, destinations);
	}

}
