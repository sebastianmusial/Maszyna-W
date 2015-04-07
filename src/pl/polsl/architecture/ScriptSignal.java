/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture;

import java.util.function.Function;
import javax.script.ScriptEngine;

/**
 * Signal configured by script describing operation performed
 * when signal is activated.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class ScriptSignal extends Signal {
	/** Script engine used to evaluate out value. */
	private ScriptEngine engine;
	
    /** Text descibing operation performed by script. */
    private String functionString;
    
    /** Object representing script function. */
    private Function<Object, Object> functionObject;

    /**
     * Construct script signal. source and target are the same
     * as in super class.
     * @param source - source of data.
     * @param target - target for data.
     * @param function - operation to be performed, e.g. "ACCUMULATOR+x"
     * will add value stored in accumulator and value stored in
     * data source and pass result to data target.
     * @param engine - script engine to perform operation specified by <i>function</i>.
     */
    public ScriptSignal(DataSource source, DataTarget target, String function, ScriptEngine engine) {
        super(source, target);
        this.functionString = "function(x) " + function;
        this.engine = engine;
        // <ScriptSignal script="AK+x"
    }
    
    /**
     * Overriden function from super class.
     * Value read from data source is passed to script and
     * result is passed to data target.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void activate() throws Exception {
        functionObject = (Function<Object,Object>)engine.eval(String.format("new java.util.function.Function(%s)", functionString));
        Object value = functionObject.apply(getSource().getValue());
        try {
        	getTarget().setValue((Integer)value);
        }
        catch(ClassCastException ex) {
        	getTarget().setValue(((Double)value).intValue());
        }
    }
    
}
