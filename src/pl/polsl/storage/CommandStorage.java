package pl.polsl.storage;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Commands database table.
 * 
 */
@Entity
@Table(name="Commands")
@NamedQuery(name="CommandStorage.findAll", query="SELECT c FROM CommandStorage c")
public class CommandStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String commandID;

	private byte arguments;

	@Lob
	private String commandCode;

	private int commandIndex;

	private String commandName;

	//bi-directional many-to-one association to CommandsListStorage
	@ManyToOne
	@JoinColumn(name="commandListID")
	private CommandsListStorage commandsList;

	public CommandStorage() {
	}

	public String getCommandID() {
		return this.commandID;
	}

	public void setCommandID(String commandID) {
		this.commandID = commandID;
	}

	public byte getArguments() {
		return this.arguments;
	}

	public void setArguments(byte arguments) {
		this.arguments = arguments;
	}

	public String getCommandCode() {
		return this.commandCode;
	}

	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}

	public int getCommandIndex() {
		return this.commandIndex;
	}

	public void setCommandIndex(int commandIndex) {
		this.commandIndex = commandIndex;
	}

	public String getCommandName() {
		return this.commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public CommandsListStorage getCommandsList() {
		return this.commandsList;
	}

	public void setCommandsList(CommandsListStorage commandsList) {
		this.commandsList = commandsList;
	}

}