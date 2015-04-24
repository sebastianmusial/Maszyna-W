package pl.polsl.i18n.keyWords;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Dawid Poloczek
 * @version 1.0
 */
public class KeyWordsLanguage {

	Map<Integer, String> keyWords = new HashMap<Integer, String>();
	
	/**
	 * Translated key words names getter.
	 * @param keyWordId ID of a key word
	 * @return Translated name of the word key with ID <i>keyWordId</i>.
	 */
	public String getKeyWord(Integer keyWordId) {
		return keyWords.get(keyWordId);
	}
	
	/**
	 * Translated key words names setter.
	 * @param keyWordId ID of a key word.
	 * @param name translated name of a key word
	 */
	public void setKeyWord(Integer keyWordId, String name) {
		keyWords.put(keyWordId, name);
	}
}
