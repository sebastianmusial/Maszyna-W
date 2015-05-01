package pl.polsl.i18n.keywords;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Dawid Poloczek
 * @version 1.0
 */
public class KeywordsLanguage {
	/** Collection of translated keywords. */
	Map<Keywords, String> keywords = new HashMap<>();
	
	/**
	 * Translated keywords names getter.
	 * @param keyword ID of a keyword
	 * @return Translated name of the keyword with ID <i>keyword</i>.
	 */
	public String getKeyword(Keywords keyword) {
		return keywords.get(keyword);
	}
	
	/**
	 * Translated keywords names setter.
	 * @param keyword ID of a keyword.
	 * @param value translated name of a keyword
	 */
	public void setKeyword(Keywords keyword, String value) {
		keywords.put(keyword, value);
	}
}
