/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture.components;


/**
 * Interface of data source. If component of W Machine should be able
 * to pass value to a data target when accurate signal is activated then
 * it must implement this interface.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public interface DataSource extends WMachineComponent {
	/**
	 * Function to get value saved in this W Machine component.
	 * @return Value saved in this W Machine component.
	 * @throws Exception when error occurs
	 */
    public Integer getValue() throws Exception;
}
