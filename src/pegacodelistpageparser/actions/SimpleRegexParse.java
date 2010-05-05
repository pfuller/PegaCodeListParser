package pegacodelistpageparser.actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fullerp
 */
public class SimpleRegexParse {
    public String doParse(String searchString, String regexPtn){
        String returnVal = "";

        // Compile the Regular Expression Search
        Pattern pattern = Pattern.compile(regexPtn);

        // Run the search over the Key Value
        Matcher matcher = pattern.matcher(searchString);

        // get 1st match
        //if (matcher.find()) return matcher.group(); else return "";

        boolean found = false;

        //pxSQLStatementPost.+FROM\s(.+)

        while (matcher.find()) {
            returnVal += "found: '" + matcher.group() +
                        "' starting at: " + matcher.start() +
                        " ending at: " + matcher.end() + "\n";

            returnVal += "Group Count is " + matcher.groupCount() + "\n";

            for (int i=1;i <= matcher.groupCount();i++){
                returnVal += searchString.substring(matcher.start(i), matcher.end(i)) + "\n";
            }
           
            found = true;
        }

        if(!found){
            returnVal = "No match found.";
        }

        return returnVal;
    }
}
