import com.ghorbel.asma.blablacartest.exception.FileParserException;
import com.ghorbel.asma.blablacartest.exception.IllegalPositionException;
import com.ghorbel.asma.blablacartest.util.InputFileParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class InputFileParserTest {
    @Test
    public void testParsingFileWithSuccess() throws IOException, FileParserException, IllegalPositionException {
        InputFileParser inputFileParser = new InputFileParser("src/test/resources/test.txt");
        inputFileParser.getMowerMoverList();
        Assert.assertTrue(inputFileParser.getMowerMoverList().size() == 2);
    }

    @Test
    public void testParsingFileWithEmptyFile() {
        try {
            InputFileParser inputFileParser = new InputFileParser("src/test/resources/empty.txt");
            Assert.fail("FileParserException should be raised");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof FileParserException);
        }
    }

    @Test
    public void testParsingFileWithMowerNegativePosition() {
        try {
            InputFileParser inputFileParser = new InputFileParser("src/test/resources/negative-mower.txt");
            Assert.fail("IllegalPositionException should be raised");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalPositionException);
        }
    }

    @Test
    public void testParsingFileWithMowerPositionOutsideLawn() {
        try {
            InputFileParser inputFileParser = new InputFileParser("src/test/resources/mower-outside-lawn.txt");
            Assert.fail("IllegalPositionException should be raised");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalPositionException);
        }
    }
}
