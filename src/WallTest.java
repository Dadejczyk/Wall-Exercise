import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class WallTest {

    private Wall wall;

    // Prosta implementacja interfejsu Block
    private class SimpleBlock implements Block {
        private String color;
        private String material;

        public SimpleBlock(String color, String material) {
            this.color = color;
            this.material = material;
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public String getMaterial() {
            return material;
        }
    }

    // Prosta implementacja interfejsu CompositeBlock
    private class SimpleCompositeBlock extends SimpleBlock implements CompositeBlock {
        private List<Block> blocks;

        public SimpleCompositeBlock(String color, String material, List<Block> blocks) {
            super(color, material);
            this.blocks = blocks;
        }

        @Override
        public List<Block> getBlocks() {
            return blocks;
        }
    }

    @Before
    public void setUp() {
        wall = new Wall();
        // Dodawanie zwykłych bloków
        wall.blocks.add(new SimpleBlock("czerwony", "drewno"));
        wall.blocks.add(new SimpleBlock("niebieski", "metal"));

        // Dodawanie złożonego bloku
        List<Block> compositeBlocks = new ArrayList<>();
        compositeBlocks.add(new SimpleBlock("zielony", "plastik"));
        compositeBlocks.add(new SimpleBlock("żółty", "drewno"));
        wall.blocks.add(new SimpleCompositeBlock("wielokolorowy", "mieszany", compositeBlocks));
    }

    @Test
    public void testFindBlockByColor() {
        // załóżmy, że istnieje blok o kolorze "czerwony"
        Optional<Block> foundBlock = wall.findBlockByColor("czerwony");
        assertTrue(foundBlock.isPresent());
        assertEquals("czerwony", foundBlock.get().getColor());
    }

    @Test
    public void testFindBlocksByMaterial() {
        // załóżmy, że istnieje przynajmniej jeden blok o materiale "drewno"
        List<Block> blocks = wall.findBlocksByMaterial("drewno");
        assertFalse(blocks.isEmpty());
        assertEquals("drewno", blocks.get(0).getMaterial());
    }

    @Test
    public void testCount() {
        // jeśli dodaliśmy np. 5 bloków
        int count = wall.count();
        assertEquals(5, count);
    }
}
