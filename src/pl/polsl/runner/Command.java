package pl.polsl.runner;

public class Command {
	/** Operation code - command ID */
	private Integer code;
	
	/** Command name - used in program. */
	private String name;
	
	public Command(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getName() { return name; }
	public Integer getCode() { return code; }
}
