package pegacodelistpageparser.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Class that retrives the table name from pxSQLStatementPost in
 * the code-pega-list page
 * 
 * @author fullerp
 */
public class TableSearch extends AbstractRegexSearchString {
    Logger logger = Logger.getLogger(TableSearch.class.getName());

    @Override
    void beforeSearch(final String searchStr) {
        // Set regular expression
        regex = "pxSQLStatementPost.+FROM\\s(.+)\\sWHERE.+";
    }

    @Override
    String Process(Matcher matcher, final String searchStr, String oldResult) {
        String result = "";

        // Extract the first group match
        if (matcher.groupCount() > 0){
            result = searchStr.substring(matcher.start(1), matcher.end(1));
        }
        
        return result;
    }

    @Override
    void afterSearch(String result) {
        // Do nothing
    }
}
