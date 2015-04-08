/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture.components;

/**
 * Interface for every W Machine component, such as registers,
 * buses, memory.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public interface WMachineComponent {
	/**
	 * Function called every tact before any signal is activated. 
	 */
    public void nextTact();
}
