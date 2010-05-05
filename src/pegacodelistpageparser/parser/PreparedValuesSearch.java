package pegacodelistpageparser.parser;

import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 *
 * @author fullerp
 */
public class PreparedValuesSearch extends AbstractRegexSearchMap {
    Logger logger = Logger.getLogger(PreparedValuesSearch.class.getName());

    @Override
    void beforeSearch(String searchStr) {
        regex = "index\\s=\\s(\\d+)\\s--\\svalue\\s=\\s(.+)";
    }

    @Override
    Map Process(Matcher matcher, String searchStr, Map oldResult) {
        Integer key;
        String value;

        // Extract the first group match
        if (matcher.groupCount() == 2){
            // Get the Key/Value matches
            key = Integer.parseInt(searchStr.substring(matcher.start(1), matcher.end(1)));
            value = searchStr.substring(matcher.start(2), matcher.end(2));

            // Add them to the map
            oldResult.put(key, value );
        }

        return oldResult;
    }

    @Override
    void afterSearch(Map result) {
        // Do nothing
    }
}