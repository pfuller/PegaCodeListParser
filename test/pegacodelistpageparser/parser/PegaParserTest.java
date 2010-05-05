/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pegacodelistpageparser.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fullerp
 */
public class PegaParserTest {
    private PegaParser testParser;
    private String preparedPage = "";
    private String codeListPage = "";

    public PegaParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        // Add files to PegaCodeListPageParser/test
        preparedPage = readFileAsString("pyPreparedValues.txt");
        codeListPage = readFileAsString("code-pega-List.txt");

        testParser = new PegaParser(preparedPage, codeListPage);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class PegaParser.
     */
    @Test
    public void testParse() {
/*
        System.out.println("Prepared Page:");
        System.out.println(testParser.preparedPage);
        System.out.println("----END ------ Prepared Page:");

        System.out.println("list page:");
        System.out.println(testParser.codeListPage);
        System.out.println("----END ------ list page:");
*/
        testParser.parse();
    }


   /**
     * @param filePath      name of file to open. The file can reside
     *                      anywhere in the classpath
     */
    private String readFileAsString(String filePath) {
        System.out.println("file is: " + filePath);

        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader().getResourceAsStream(filePath)));

        try {
            char[] buf = new char[1024];
            int numRead = 0;

            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }

            return fileData.toString();

        } catch (java.io.IOException e){
            System.out.println("IO Error occured while accessing " + filePath);
            throw new RuntimeException("IO Error occured while accessing " + filePath, e);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (java.io.IOException e){
                System.out.println("IO Error occured while accessing " + filePath);
                throw new RuntimeException("IO Error occured while accessing " + filePath, e);
            }
        }
    }
}