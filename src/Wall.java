import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

interface Structure {
    // zwraca dowolny element o podanym kolorze
    Optional<Block> findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List<Block> findBlocksByMaterial(String material);
    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

interface Block {
    String getColor();
    String getMaterial();
}

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}



public class Wall implements Structure {
    List<Block> blocks; // pole przechowujące listę bloków w ścianie.
    public Wall() {
        this.blocks = new ArrayList<>(); // Inicjalizacja listy
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) { // Iteracja przez wszystkie bloki w ścianie.
            if (block.getColor().equals(color)) { // Sprawdza, czy kolor bloku zgadza się z szukanym kolorem.
                return Optional.of(block); // Zwraca blok, jeśli kolor się zgadza.
            }
            if (block instanceof CompositeBlock) { // Sprawdza, czy blok jest CompositeBlock.
                Optional<Block> found = findInCompositeBlockColor((CompositeBlock) block, b -> b.getColor().equals(color)); // Szuka bloku o danym kolorze w złożonym bloku.
                if (found.isPresent()) { // Jeśli znaleziono blok, zwraca go.
                    return found;
                }
            }
        }
        return Optional.empty(); // Zwraca pusty Optional, jeśli nie znaleziono bloku.
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>(); // Lista na wyniki.
        for (Block block : blocks) { // Iteracja przez wszystkie bloki.
            if (block.getMaterial().equals(material)) { // Sprawdza, czy materiał bloku zgadza się z szukanym.
                result.add(block); // Dodaje blok do wyników, jeśli się zgadza.
            }
            if (block instanceof CompositeBlock) { // Sprawdza, czy blok jest CompositeBlock.
                // Dodaje do wyników wszystkie bloki z złożonego bloku, które mają szukany materiał.
                result.addAll(findInCompositeBlockMaterial((CompositeBlock) block, b -> b.getMaterial().equals(material)));
            }
        }
        return result; // Zwraca listę znalezionych bloków.
    }

    @Override
    public int count() {
        int count = 0; // Inicjalizuje licznik bloków.
        for (Block block : blocks) { // Iteracja przez każdy blok.
            count++; // Inkrementuje licznik dla każdego bloku.
            if (block instanceof CompositeBlock) { // Sprawdza, czy blok jest CompositeBlock.
                count += countCompositeBlock((CompositeBlock) block); // Dodaje do licznika liczbę bloków w złożonym bloku.
            }
        }
        return count; // Zwraca całkowitą liczbę bloków.
    }

    private Optional<Block> findInCompositeBlockColor(CompositeBlock compositeBlock, Predicate<Block> condition) {
        for (Block block : compositeBlock.getBlocks()) { // Iteracja przez bloki w złożonym bloku.
            if (condition.test(block)) { // Sprawdza blok pod kątem spełnienia warunku.
                return Optional.of(block); // Zwraca blok, jeśli spełnia warunek.
            }
        }
        return Optional.empty(); // Zwraca pusty Optional, jeśli żaden blok nie spełnia warunku.
    }

    private List<Block> findInCompositeBlockMaterial(CompositeBlock compositeBlock, Predicate<Block> condition) {
        List<Block> result = new ArrayList<>(); // Lista na wyniki.
        for (Block block : compositeBlock.getBlocks()) { // Iteracja przez bloki w złożonym bloku.
            if (condition.test(block)) { // Sprawdza blok pod kątem spełnienia warunku.
                result.add(block); // Dodaje blok do wyników, jeśli spełnia warunek.
            }
        }
        return result; // Zwraca listę bloków spełniających warunek.
    }

    private int countCompositeBlock(CompositeBlock compositeBlock) {
        int count = 0; // Inicjalizuje licznik bloków w złożonym bloku.
        for (Block block : compositeBlock.getBlocks()) { // Iteracja przez bloki w złożonym bloku.
            count++; // Inkrementuje licznik dla każdego bloku.
        }
        return count; // Zwraca liczbę bloków w złożonym bloku.
    }
}