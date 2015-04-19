/**
 * 
 */
package pl.polsl.parser;

/**
 * @author Dawid Poloczek
 * @version 1.0
 */
public class ConditionStatement implements ITact {
	
	private String condition;
	
	private String ifTrue;
	
	private String ifFalse;

	public void setCondition(String condition){
		this.condition = condition;
	}
	
	public void setFalseLabel(String label){
		this.ifFalse = label;
	}
	
	public void setTrueLabel(String label){
		this.ifTrue = label;
	}
	
	public String getCondition(){
		return this.condition;
	}
	
	public String getFalseLabel() {
		return this.ifFalse;
	}

	public String getTrueLabel() {
		return this.ifTrue;
	}
	
}
