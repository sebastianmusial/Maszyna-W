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
public interface DataSource extends WMachineComponent {
    public Integer getValue() throws Exception;
}
