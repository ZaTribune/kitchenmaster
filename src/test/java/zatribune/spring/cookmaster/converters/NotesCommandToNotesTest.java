package zatribune.spring.cookmaster.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zatribune.spring.cookmaster.commands.CategoryCommand;
import zatribune.spring.cookmaster.commands.NotesCommand;
import zatribune.spring.cookmaster.data.entities.Category;
import zatribune.spring.cookmaster.data.entities.Notes;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    private NotesCommandToNotes converter;
    private final String id="0x122564";
    private final String description="a dummy notes description";

    @BeforeEach
    void setUp() {
        converter=new NotesCommandToNotes();
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void testNullObject(){
        assertNull(converter.convert(null));
    }


    @Test
    void convert() {
        NotesCommand input=new NotesCommand();
        input.setId(id);
        input.setDescription(description);

        Notes output=converter.convert(input);

        assertNotNull(output);
        assertEquals(id,output.getId());
        assertEquals(description,output.getDescription());
    }
}