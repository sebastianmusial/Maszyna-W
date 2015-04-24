/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.NonVolatileDataStorage;
import pl.polsl.architecture.data.Data;

/**
 * W Machine register. It's configured by bit count for data word.
 * Implements DataSource and DataTarget interfaces. In one tact
 * one can set only one value in the register. Next call of setValue
 * function will cause exception to be thrown. Calling getValue will
 * never cause exception to be thrown.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class Register extends NonVolatileDataStorage {
    /**
     * Constructor with bit count as parameter. Constructs register
     * configured to contain bitCount long data word.
     * @param data data instance to be stored
     */
    public Register(Data data) {
    	super(data);
    }
}
