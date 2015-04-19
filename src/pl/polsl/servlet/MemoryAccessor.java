package pl.polsl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.MemoryCell;
import pl.polsl.runner.CommandList;
import pl.polsl.runner.DefaultCommandList;

/**
 * Allow to access memory from client side.
 * @author Tomasz Rzepka
 * @version 1.0
 */
@WebServlet("/MemoryAccessor")
public class MemoryAccessor extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Current WMachine instance. */
	private WMachine machine;
	
	/** Current output writer. */
	private PrintWriter out;
	
	/** Current Memory instance. */
	private Memory memory;
	
	/**
	 * Command list used to obtain command names.
	 * TODO replace with list got from settings
	 */
	private CommandList commands = new DefaultCommandList();
	
	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		machine = getCurrentWMachine(request.getSession());
		memory = machine.getMemory();
		out = response.getWriter();
		switch(request.getParameter("action")) {
			case "get":
				String index = request.getParameter("index");
				if(index == null || index.equals(""))
					getAllCells(request, response);
				else
					getSingleCell(request, response);
				break;
				
			case "preview":
				getPreview(request, response);
				break;
				
			case "set":
				setSingleCell(request, response);
				break;
				
			default:
				response.setContentType("text/plain");
		}
	}
	
	/**
	 * Return in response preview (indexes 0-7) of memory cell values.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void getPreview(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> resp = new LinkedList<>();
		for(int i = 0; i < 8; ++i) {
			resp.add(cellAsMap(memory.getCell(i)));
		}
		new Gson().toJson(resp, out);
	}
	
	/**
	 * Return in response all memory cell values.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void getAllCells(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> resp = new LinkedList<>();
		for(MemoryCell cell : memory.getCells()) {
			resp.add(cellAsMap(cell));
		}
		new Gson().toJson(resp, out);
	}
	
	/**
	 * Return in response single memory cell value.
	 * @param request servlet request
     * @param response servlet response
	 */
	protected void getSingleCell(HttpServletRequest request, HttpServletResponse response) {
		Integer index = Integer.parseInt(request.getParameter("index"));
		new Gson().toJson(cellAsMap(memory.getCell(index)), out);
	}
	
	/**
	 * Set single memory cell value and return masked value in response.
	 * @param request servlet request
     * @param response servlet response
	 */
	protected void setSingleCell(HttpServletRequest request, HttpServletResponse response) {
		Integer index = Integer.parseInt(request.getParameter("index"));
		Integer value = Integer.parseInt(request.getParameter("value"));
		MemoryCell cell = memory.getCell(index);
		cell.replaceValue(value);
		new Gson().toJson(cellAsMap(cell), out);
	}
	
	/**
	 * Convert memory cell to map with two keys: value and text.
	 * @param cell cell to be converted
	 * @return Map with two keys: value (for value stored in cell)
	 * and text (interpretation as command with argument).
	 */
	protected Map<String, Object> cellAsMap(MemoryCell cell) {
		Map<String, Object> cellMap = new HashMap<>();
		String command = commands.getNameByCode(cell.getOpCode());
		String address = cell.getAddress().toString();
		cellMap.put("value", cell.peekValue());
		cellMap.put("text", command + " " + address);
		return cellMap;
	}
}
