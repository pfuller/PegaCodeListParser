package pegacodelistpageparser.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fullerp
 */
public class PegaParser {
    // Immutable inputs
    public final String preparedPage;
    public final String codeListPage;
    private String tableName;
    private String preSQL;
    private Map<Integer, String> preparedValues;

    /**
     * Constructor that stores the inputs as final
     *
     * @param preparedPageIn
     * @param codeListPageIn
     */
    public PegaParser(String preparedPageIn, String codeListPageIn){
       preparedPage = preparedPageIn;
       codeListPage = codeListPageIn;
    }

    /**
     * Parses the inputs
     *
     * @return Parsed SQL
     */
    public String parse(){
        String parsedSQL = "";

        // Get Table from code-pega-list from pxSQLStatementPost line
        tableName = new TableSearch().search(codeListPage);

        // Get Sql from code-pega-list from pxSQLStatementPre line
        preSQL = new PreSQLSearch().search(codeListPage);

        // Assign pre to parsed
        parsedSQL = preSQL;

        // Get the prepared values
        preparedValues = new PreparedValuesSearch().search(preparedPage);

        // Get match string  eg. UnallocatedWorkItemsView.pyPreparedValues
        String PageVariable = new ReplacementTextSearch().search(parsedSQL);

        // Get substitute values from pyPreparedValues and place in map
        for(Map.Entry<Integer, String> entry : preparedValues.entrySet()){
            String replacement = "'" + entry.getValue() + "'";

            // Compile the Regular Expression Search
            Pattern pattern = Pattern.compile(
                    "\\W" + PageVariable + "\\(" + entry.getKey() +"\\)\\}");

            // Run the search over the Key Value
            Matcher matcher = pattern.matcher(parsedSQL);

            parsedSQL = matcher.replaceAll(replacement);
        }


        // Replace the table name in the SQL
        Pattern pattern = Pattern.compile("\\{CLASS:\\S+\\}");
        Matcher matcher = pattern.matcher(parsedSQL);
        parsedSQL = matcher.replaceAll(tableName);
            
        return parsedSQL;
    }
}
