/**
 * 
 */
package pl.polsl.parser;

/**
 * @author Dawid Poloczek
 *
 */
public class LineWithLabel extends SetOfSignals {

	String label;
	
	protected void setLabel(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}

}
