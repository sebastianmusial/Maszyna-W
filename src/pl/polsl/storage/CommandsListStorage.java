package pl.polsl.storage;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the CommandsLists database table.
 * 
 */
@Entity
@Table(name = "CommandsLists")
@NamedQueries({ 
	@NamedQuery(name = "CommandsListStorage.findAll", query = "SELECT c FROM CommandsListStorage c"),
	@NamedQuery(name = "CommandsListStorage.findByUser", query = "SELECT c FROM CommandsListStorage c WHERE c.user = :user"),
	@NamedQuery(name = "CommandsListStorage.findByUserIfPublic", query = "SELECT c FROM CommandsListStorage c WHERE c.user = :user or c.isPublic = 1")
})
public class CommandsListStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String commandListID;

	private byte isPublic;

	private String name;

	// bi-directional many-to-one association to CommandStorage
	@OneToMany(mappedBy = "commandsList")
	private List<CommandStorage> commands;

	// bi-directional many-to-one association to UserStorage
	@ManyToOne
	@JoinColumn(name = "userID")
	private UserStorage user;

	public CommandsListStorage() {
	}

	public String getCommandListID() {
		return this.commandListID;
	}

	public void setCommandListID(String commandListID) {
		this.commandListID = commandListID;
	}

	public byte getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(byte isPublic) {
		this.isPublic = isPublic;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CommandStorage> getCommands() {
		return this.commands;
	}

	public void setCommands(List<CommandStorage> commands) {
		this.commands = commands;
	}

	public CommandStorage addCommand(CommandStorage command) {
		getCommands().add(command);
		command.setCommandsList(this);

		return command;
	}

	public CommandStorage removeCommand(CommandStorage command) {
		getCommands().remove(command);
		command.setCommandsList(null);

		return command;
	}

	public UserStorage getUser() {
		return this.user;
	}

	public void setUser(UserStorage user) {
		this.user = user;
	}

}