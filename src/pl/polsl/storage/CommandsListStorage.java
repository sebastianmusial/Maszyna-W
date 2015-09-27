package pl.polsl.storage;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	@NamedQuery(name = "CommandsListStorage.findByUserIfPublic", query = "SELECT c FROM CommandsListStorage c WHERE c.user = :user or c.isPublic = 1"),
	@NamedQuery(name = "CommandsListStorage.getStandardList", query = "SELECT c FROM CommandsListStorage c WHERE c.user.login = 'admin'")
})
public class CommandsListStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long commandListID;

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

	public Long getCommandListID() {
		return this.commandListID;
	}

	public void setCommandListID(Long commandListID) {
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

	public Object serialize() {
		SerializableCommandList scl = new SerializableCommandList();
		scl.name = getName();
		scl.id = getCommandListID();
		return scl;
	}
	
	public Object serializeCommands() {
		List<CommandStorage> commands = getCommands();
		return commands.stream().map((c) -> {
			SerializableCommand sc = new SerializableCommand();
			sc.name = c.getCommandName();
			sc.code = c.getCommandCode();
			return sc;
		}).toArray();
	}
	
	private class SerializableCommand {
		public String name;
		public String code;
	}
	
	private class SerializableCommandList {
		public String name;
		public Long id;
	}
}