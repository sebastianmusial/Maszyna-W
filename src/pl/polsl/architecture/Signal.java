/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture;

/**
 *
 * @author Tomasz Rzepka
 */
public class Signal {
    private DataSource source;
    private DataTarget target;
    
    public Signal(DataSource source, DataTarget target)
    {
        this.source = source;
        this.target = target;
    }
    
    public void activate() throws Exception
    {
        if(getSource() == null || getTarget() == null)
            return;
        getTarget().setValue(getSource().getValue());
    }

    /**
     * @return the source
     */
    public DataSource getSource() {
        return source;
    }

    /**
     * @return the target
     */
    public DataTarget getTarget() {
        return target;
    }
}
