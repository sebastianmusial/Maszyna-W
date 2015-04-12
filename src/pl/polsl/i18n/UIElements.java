package pl.polsl.i18n;

/**
 * List of UI elements which are language sensitive
 * @author Dawid Poloczek
 * @version 1.0
 */
public enum UIElements {
	/** Site header label */
	SITE_HEADER(1),
	/** Simulator tab label */
	SIMULATOR_TAB(2),
	/** Guest book label */
	GUEST_BOOK_TAB(3),
	/** User navigation drop box */
	USER_MENU_DROP_BOX(4),
	/** Register label of user navigation drop box */
	USER_REGISTER_TAB(5),
	/** Log on label of user navigation drop box */
	USER_LOGIN_TAB(6),
	/** Manual control check box */
	MANUAL_CONTROL_CHECK_BOX(7),
	
	TRACKING_LEVEL_PANEL(8),
	
	TRACKING_LEVEL_LOW_OPTION(9),
	
	TRACKING_LEVEL_MEDIUM_OPTION(10),
	
	TRACKING_LEVEL_HIGH_OPTION(11),
	
	TYPES_AND_COMPONENTS_PANEL(12),
	
	TYPE_W_RADIO_BUTTON(13),
	
	TYPE_W_PLUS_RADIO_BUTTON(14),
	
	TYPE_L_RADIO_BUTTON(15),
	
	TYPE_EW_RADIO_BUTTON(16),
	
	BUS_CONNECTION_CHECK_BOX(17),
	
	INC_DEC_ACCUMULATOR_CHECK_BOX(18),
	
	LOGICAL_OPERATIONS_ALU_CHECK_BOX(19),
	
	EXTENDED_ARYTHMETICAL_ALU_OPERATIONS_CHECK_BOX(20),
	
	STACK_MANAGEMENT_CHECK_BOX(21),
	
	X_REGISTER_CHECK_BOX(22),
	
	Y_REGISTER_CHECK_BOX(23),
	
	INPUT_OUTPUT_CHECK_BOX(24),
	
	ADDITIONAL_MARKS_CHECK_BOX(25),
	
	ARCHITECTURE_TAB(26),
	
	ADDRESS_BIT_COUNT_INPUT(27),
	
	CODE_BIT_COUNT_INPUT(28),
	
	ADDRESSES_TAB(29),
	
	ADRESS_LABEL(30),
	
	INTERRUPT_LABEL(31),
	
	ADDRESS_INPUT_LABEL(32),
	
	ADDRESS_OUTPUT_LABEL(33),
	
	FORUM_TAB(34),
	
	LOGOUT_TAB(35);
	
	/** ID of UI element */
	public final Integer ID;
	
	/**
	 * Constructs enum value
	 * @param id
	 */
	private UIElements(Integer id){
		this.ID = id;
	}
}
