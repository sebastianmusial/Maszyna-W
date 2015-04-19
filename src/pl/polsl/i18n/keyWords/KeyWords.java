package pl.polsl.i18n.keyWords;

/**
 * 
 * @author Dawid Poloczek
 * @version 1.0
 */
public enum KeyWords {

	COMMAND(1),
	
	IF(2),
	
	THEN(3),
	
	IF_NOT(4),
	
	END(5);
	
	public final Integer ID;
	
	private KeyWords(Integer ID){
		this.ID = ID;
	}
}
