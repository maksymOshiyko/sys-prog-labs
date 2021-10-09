package main;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordsFinderTest {
    private String resourcesPath;
    private WordsFinder instance;


    @BeforeAll
    void setResourcesDirectory() {
        this.resourcesPath = "test/resources/";
    }

    @BeforeEach
    public void setUp() {
        this.instance = new WordsFinder();
    }

    @Test
    @DisplayName("Wrong file name")
    public void wrongFileName() {
        // arrange
        String fileName = "wrongFileName.txt";

        // assert
        assertThrows(NoSuchFileException.class, () -> this.instance.getContentOfFile(
                this.resourcesPath + fileName
        ));
    }

    @Test
    @DisplayName("Valid file name")
    public void validFileName() throws IOException {
        // arrange
        String fileName = "testtext.txt";

        // act
        String fileContent = this.instance.getContentOfFile(this.resourcesPath + fileName);

        // assert
        assertNotNull(fileContent);
    }

    @Test
    @DisplayName("Count max consonants chain count ")
    public void getMaxConsonantsChainCount() {
        // arrange
        String word = "getMaxConsonantsChainCount";

        // act
        int count = this.instance.getMaxConsonantsChainCount(word);

        // assert
        assertEquals(5, count);
    }

    @Test
    @DisplayName("Get words with max consonants count")
    public void getWordsWithMaxConsonantsCount() throws IOException {
        // arrange
        String fileName = "testtext.txt";

        // act
        ArrayList<String> words = this.instance.getWordsWithMaxConsonantsCount(this.resourcesPath + fileName);

        // assert
        assertNotNull(words);
        assertEquals(1, words.size());
        assertEquals("COUNTRY", words.get(0));
    }
}