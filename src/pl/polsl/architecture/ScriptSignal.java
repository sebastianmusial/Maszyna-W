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
 *
 * @author Tomasz Rzepka
 */
public class ScriptSignal extends Signal {
    static ScriptEngine engine;
    
    static {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.getContext().setAttribute("AK", 10, ScriptContext.GLOBAL_SCOPE);
        System.out.println("Klucze:");
        for(String key : engine.getBindings(ScriptContext.GLOBAL_SCOPE).keySet())
            System.out.println(key);
        System.out.println("Koniec kluczy");
    }
    
    
    String functionString;
    Function<Object, Object> functionObject;

    public ScriptSignal(DataSource source, DataTarget target, String function) {
        super(source, target);
        this.functionString = "function(x) " + function;
        // <ScriptSignal script="AK+x"
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void activate() throws Exception {
        functionObject = (Function<Object,Object>)engine.eval(String.format("new java.util.function.Function(%s)", functionString));
        getTarget().setValue((Integer)functionObject.apply(getSource().getValue()));
    }
    
}
