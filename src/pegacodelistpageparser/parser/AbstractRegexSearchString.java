package pegacodelistpageparser.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author fullerp
 */
public abstract class AbstractRegexSearchString {
    // Variable to enable setting of the expresseion
    String regex;

    /**
     * Searches using the implemented process
     *
     * @param properties The properties file to search
     * @param results    The Connections that were found
     */
    public String search(String searchStr) {
        String resultStr = "";
        final String searchStrVal = searchStr;

        // Pre-processing, may want to change regex here for anonymous implementations
        beforeSearch(searchStrVal);

        // Output current search
        Logger.getLogger(AbstractRegexSearchString.class.getName()).log(Level.FINE, "Expression regex is: " + regex);

        // Compile the Regular Expression Search
        Pattern pattern = Pattern.compile(regex);

        // Run the search over the Key Value
        Matcher matcher = pattern.matcher(searchStrVal);

        // Process when we have a match
        while (matcher.find()) {
            resultStr = Process(matcher, searchStrVal, resultStr);
        }

        // provide method for post search activities
        afterSearch(resultStr);

        return resultStr;
    }

    /**
     *
     * @param matcher
     * @param searchStr
     * @param oldResult
     * @return The new result to be used
     */
    abstract String Process(Matcher matcher, final String searchStr, String oldResult);

    abstract void beforeSearch(final String searchStr);

    abstract void afterSearch(String result);
}
