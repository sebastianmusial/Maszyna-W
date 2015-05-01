package pl.polsl.runner;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.MemoryCell;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.runner.command.Command;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;
import pl.polsl.servlet.WMachineServletBase;

/**
 * Run command depending on W Machine state.
 */
@WebServlet("/CommandRunner")
public class CommandRunner extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WMachine machine = getCurrentWMachine(request.getSession());
		if(machine.isStopped())
			return;
		
		CommandList commandList = new DefaultCommandList();
		Register A = machine.getRegister(AvailableRegisters.MEMORY_ADDRESS.ID);
		Memory memory = machine.getMemory();
		MemoryCell cell = memory.getCell(A.peekValue());
		Command command = commandList.get(cell.getOpCode());
		
		try {
			command.run(machine);
			response.sendRedirect("WMachineState?action=restore");
		}
		catch(Exception ex) {
			// TODO wycofać zmiany wprowadzone w tym takcie
		}
	}
}
