/**
 * 
 */
package pl.polsl.i18n.keywords;

/**
 * @author Dawid Poloczek
 * @version 1.0
 */
public class DefaultKeywordsLanguage extends KeywordsLanguage {
	/**
	 * Constructs default keyword language.
	 */
	public DefaultKeywordsLanguage(){
		setKeyword(Keywords.COMMAND, "COMMAND");
		setKeyword(Keywords.IF, "IF");
		setKeyword(Keywords.THEN, "THEN");
		setKeyword(Keywords.ELSE, "ELSE");
		setKeyword(Keywords.END, "END");
		setKeyword(Keywords.ARGUMENTS, "ARGUMENTS");
	}
}
