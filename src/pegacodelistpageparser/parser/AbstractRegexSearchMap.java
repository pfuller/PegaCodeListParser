package pegacodelistpageparser.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author fullerp
 */
public abstract class AbstractRegexSearchMap {
    // Variable to enable setting of the expresseion
    String regex;

    /**
     * Searches using the implemented process
     *
     * @param properties The properties file to search
     * @param results    The Connections that were found
     */
    public Map search(String searchStr) {
        Map resultMap = new HashMap<Integer, String>();
        final String searchStrVal = searchStr;

        // Pre-processing, may want to change regex here for anonymous implementations
        beforeSearch(searchStrVal);

        // Output current search
        Logger.getLogger(AbstractRegexSearchMap.class.getName()).log(Level.FINE, "Expression regex is: " + regex);

        // Compile the Regular Expression Search
        Pattern pattern = Pattern.compile(regex);

        // Run the search over the Key Value
        Matcher matcher = pattern.matcher(searchStrVal);

        // Process when we have a match
        while (matcher.find()) {
            resultMap = Process(matcher, searchStrVal, resultMap);
        }

        // provide method for post search activities
        afterSearch(resultMap);

        return resultMap;
    }

    /**
     *
     * @param matcher
     * @param searchStr
     * @param oldResult
     * @return The new result to be used
     */
    abstract Map Process(Matcher matcher, final String searchStr, Map oldResult);

    abstract void beforeSearch(final String searchStr);

    abstract void afterSearch(Map result);
}
