/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture;

import java.util.function.Function;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Signal configured by script describing operation performed
 * when signal is activated.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class ScriptSignal extends Signal {
	// TODO: engine and static {...} goes to WMachine class (and are not static!)
    static ScriptEngine engine;
    
    static {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.getContext().setAttribute("AK", 10, ScriptContext.GLOBAL_SCOPE);
        System.out.println("Klucze:");
        for(String key : engine.getBindings(ScriptContext.GLOBAL_SCOPE).keySet())
            System.out.println(key);
        System.out.println("Koniec kluczy");
    }
    
    /** Text descibing operation performed by script. */
    private String functionString;
    
    /** Object representing script function. */
    private Function<Object, Object> functionObject;

    /**
     * Construct script signal. source and target are the same
     * as in super class.
     * @param source - source of data.
     * @param target - target for data.
     * @param function - operation to be performed, e.g. "AK+x"
     * will add value stored in accumulator and value stored in
     * data source and pass result to data target.
     */
    public ScriptSignal(DataSource source, DataTarget target, String function) {
        super(source, target);
        this.functionString = "function(x) " + function;
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
        getTarget().setValue((Integer)functionObject.apply(getSource().getValue()));
    }
    
}
