package pl.polsl.runner;

public enum DefaultCommand {
	STOP(0),
	ADD(1),
	SUBTRACT(2),
	LOAD(3),
	SAVE(4),
	BRANCH(5),
	BRANCH_ZERO(6),
	BRANCH_SIGN(7);
	
	public final Integer CODE;
	
	private DefaultCommand(Integer code) {
		CODE = code;
	}
}
