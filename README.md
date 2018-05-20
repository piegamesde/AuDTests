# Mehr Tests für AuD

Da man nie genug Tests kriegen kann. (Und die bereits vorhandenen Tests vorsichtig ausgedrückt _suboptimal_ sind)

## Verwendung:

Einfach `lab.HashTable` mit eigener Implementierung überschreiben. Dabei muss sichergestellt werden, dass es eine Methode `public int hash(String key)` gibt, ansonsten funktionieren die Tests nicht. Die Methode sollte ungefähr so aussehen (letztendlich kommt es aber auf die Funktionalität an):

```
	/**
	 * Hashes a given key according to the specified algorithm.
	 *
	 * @param key the key of the entry to hash, as of {@link Entry#getKey}
	 * @return The hash value as int calculated according to the specified algorithm. It is sometimes called "home address" or "index"
	 */
	public int hash(String key) {
		switch (algorithm) {
			case "division":
				return hashDivision(key);
			// ...
		}
	}
```

## Weitere Hinweise:

- Die Tests sollten in folgender Reihenfolge bearbeitet werden: `HashTests1`, `HashTests2`, `HashTests3`, `DotTests1`, `DotTests2`, `AllTests`.
- Bei `DotTests2` gibt es drei Tests pro Konfiguration, mit jeweils 100, 1000 und 10.000 Elementen. Bei suboptimalen Implementierungen können diese sehr lange dauern. Deshalb lassen sie sich im Kopf der Klasse einzeln ein- und ausschalten.
- Die `AllTestSuite` scheint nicht auf allen Systemen zu funktionieren. Alternativ kann man mit einem Rechtsklick auf das `frame`-Paket -> "Run As" -> "JUnit Test" alle Tests starten. 