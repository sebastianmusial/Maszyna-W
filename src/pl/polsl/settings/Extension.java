package pl.polsl.settings;

/**
 * Informative enum containing all available extensions.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public enum Extension {
	/** Connection between address bus and data bus. */
	BUS_CONNECTION,
	/** Incrementation and decrementation of the accumulator. */
	ACCUMULATOR_MODIFIERS,
	/** Logical operations in arithmetic logic unit. */
	ALU_LOGICAL_OPERATIONS,
	/** Additional arithmetic operations in arithmetic logic unit. */
	ALU_ARITHMETIC_OPERATIONS,
	/** Stack pointer register and output from program counter to data bus. */
	STACK,
	/** Additional data register. */
	DATA_REGISTER_X,
	/** Additional data register. */
	DATA_REGISTER_Y,
	/** I/O operation support. */
	INPUT_OUTPUT,
	/** Flag register. */
	FLAGS
}
