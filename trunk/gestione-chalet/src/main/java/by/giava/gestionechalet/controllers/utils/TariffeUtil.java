package by.giava.gestionechalet.controllers.utils;

import java.util.HashMap;
import java.util.Map;

import by.giava.gestionechalet.model.Tariffa;

public class TariffeUtil {

	public static Float calcola(Integer numGiorni, Map<Integer, Float> tariffe) {
		Float result = new Float(0);
		Integer[] valori = { 1, 2, 3, 4, 5, 7, 30 };

		for (int i = valori.length - 1; i >= 0; i--) {
			if (numGiorni >= valori[i]) {
				Integer resto = numGiorni - valori[i];
				Float costoMax = tariffe.get(valori[i]);
				Float costoEx = tariffe.get(0);
				result = costoMax + (costoEx * resto);
				System.out.println("RESULT INIT: " + result);
				return result;
			}
		}
		return result;
	}

	public static Float calcolaTariffa(Integer numGiorni, Tariffa tariffa) {
		System.out.println("NUM GG: " + numGiorni);
		Map<Integer, Float> tariffeA = new HashMap<Integer, Float>();
		tariffeA.put(1, tariffa.getD1());
		tariffeA.put(2, tariffa.getD2());
		tariffeA.put(3, tariffa.getD3());
		tariffeA.put(4, tariffa.getD4());
		tariffeA.put(5, tariffa.getD5());
		tariffeA.put(7, tariffa.getD7());
		tariffeA.put(30, tariffa.getD30());
		tariffeA.put(0, tariffa.getD1ex());
		return calcola(numGiorni, tariffeA);
	}

	public static Float calcolaExtra(Integer numGiorni, Tariffa tariffa) {
		return numGiorni * tariffa.getD1ex();
	}

	public static Float calcolaKmExtra(Float km, Tariffa tariffa) {
		return km * tariffa.getCostoKm();
	}
}
