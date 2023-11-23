# Projekt: Implementacja Struktury Ściany w Javie

## Analiza Implementacji

### Zgodność z Interfejsami
- Implementacja interfejsu `Structure` w klasie `Wall`, realizująca metody `findBlockByColor`, `findBlocksByMaterial` i `count`.
- Obsługa `CompositeBlock`, umożliwiająca efektywne przetwarzanie złożonych struktur bloków.

### Unikanie Powielania Kodu
- Użycie predykatów w metodach `findInCompositeBlockColor` i `findInCompositeBlockMaterial`, co umożliwiło unikanie duplikacji kodu. To podejście pozwala na jednolitą metodę przeszukiwania bloków.

### Efektywność
- Rozwiązanie efektywne dla prostych struktur, z możliwością optymalizacji dla złożonych, zagnieżdżonych struktur w przyszłości.

### Czytelność i Modułowość
- Staranne podejście do nazewnictwa metod i struktury kodu, sprzyjające łatwemu zrozumieniu i rozbudowie.

### Testowanie
- Implementacja testów jednostkowych, potwierdzających poprawność działania kodu, pokrywających różne scenariusze.

## Klasa `Wall`

### Krótki Opis
Klasa `Wall` implementuje interfejs `Structure`, reprezentując strukturę złożoną z bloków (`Block`). Umożliwia wyszukiwanie bloków na podstawie koloru i materiału oraz liczenie całkowitej liczby bloków, obsługując zarówno pojedyncze, jak i złożone bloki (`CompositeBlock`).

### Szczegółowy Opis Metod
- `findBlockByColor`: Zwraca pierwszy blok pasujący do podanego koloru, obsługując również złożone bloki.
- `findBlocksByMaterial`: Zwraca listę bloków wykonanych z określonego materiału, uwzględniając pojedyncze i złożone bloki.
- `count`: Oblicza całkowitą liczbę bloków w strukturze, wliczając bloki w złożonych blokach.
- `findInCompositeBlockColor` i `findInCompositeBlockMaterial`: Metody pomocnicze do przeszukiwania złożonych bloków.
- `countCompositeBlock`: Liczy bloki w ramach złożonego bloku.

## Klasa `WallTest`

### Krótki Opis
Klasa `WallTest` zawiera zestaw testów jednostkowych dla klasy `Wall`, sprawdzających poprawność implementacji metod `findBlockByColor`, `findBlocksByMaterial`, oraz `count`. Testy obejmują scenariusze dla bloków prostych i złożonych.

### Opis Metod Testowych
- `setUp`: Inicjalizuje `Wall` z różnymi blokami przed każdym testem.
- `testFindBlockByColor`: Weryfikuje poprawność znajdowania bloku o określonym kolorze.
- `testFindBlocksByMaterial`: Testuje znajdowanie bloków z danego materiału.
- `testCount`: Sprawdza poprawność liczenia całkowitej liczby bloków.
