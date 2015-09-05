package pl.polsl.storage;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name = "Users")
@NamedQueries({ 
		@NamedQuery(name = "UserStorage.findAll", query = "SELECT u FROM UserStorage u"),
		@NamedQuery(name = "UserStorage.findByName", query = "SELECT u FROM UserStorage u WHERE u.login = :login")
})
public class UserStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userID;

	private String emailAddress;

	private String login;

	private String password;

	private short privilegesLevel;

	// bi-directional many-to-one association to CommandsListStorage
	@OneToMany(mappedBy = "user")
	private List<CommandsListStorage> commandsLists;

	// bi-directional many-to-one association to ProgramStorage
	@OneToMany(mappedBy = "user")
	private List<ProgramStorage> programs;

	// bi-directional many-to-one association to ProgramsLibraryStorage
	@OneToMany(mappedBy = "user")
	private List<ProgramsLibraryStorage> programsLibraries;

	// bi-directional many-to-one association to ReplyStorage
	@OneToMany(mappedBy = "user")
	private List<ReplyStorage> replies;

	// bi-directional many-to-one association to TopicStorage
	@OneToMany(mappedBy = "user")
	private List<TopicStorage> topics;

	public UserStorage() {
	}

	public String getUserID() {
		return this.userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public short getPrivilegesLevel() {
		return this.privilegesLevel;
	}

	public void setPrivilegesLevel(short privilegesLevel) {
		this.privilegesLevel = privilegesLevel;
	}

	public List<CommandsListStorage> getCommandsLists() {
		return this.commandsLists;
	}

	public void setCommandsLists(List<CommandsListStorage> commandsLists) {
		this.commandsLists = commandsLists;
	}

	public CommandsListStorage addCommandsList(CommandsListStorage commandsList) {
		getCommandsLists().add(commandsList);
		commandsList.setUser(this);

		return commandsList;
	}

	public CommandsListStorage removeCommandsList(
			CommandsListStorage commandsList) {
		getCommandsLists().remove(commandsList);
		commandsList.setUser(null);

		return commandsList;
	}

	public List<ProgramStorage> getPrograms() {
		return this.programs;
	}

	public void setPrograms(List<ProgramStorage> programs) {
		this.programs = programs;
	}

	public ProgramStorage addProgram(ProgramStorage program) {
		getPrograms().add(program);
		program.setUser(this);

		return program;
	}

	public ProgramStorage removeProgram(ProgramStorage program) {
		getPrograms().remove(program);
		program.setUser(null);

		return program;
	}

	public List<ProgramsLibraryStorage> getProgramsLibraries() {
		return this.programsLibraries;
	}

	public void setProgramsLibraries(
			List<ProgramsLibraryStorage> programsLibraries) {
		this.programsLibraries = programsLibraries;
	}

	public ProgramsLibraryStorage addProgramsLibrary(
			ProgramsLibraryStorage programsLibrary) {
		getProgramsLibraries().add(programsLibrary);
		programsLibrary.setUser(this);

		return programsLibrary;
	}

	public ProgramsLibraryStorage removeProgramsLibrary(
			ProgramsLibraryStorage programsLibrary) {
		getProgramsLibraries().remove(programsLibrary);
		programsLibrary.setUser(null);

		return programsLibrary;
	}

	public List<ReplyStorage> getReplies() {
		return this.replies;
	}

	public void setReplies(List<ReplyStorage> replies) {
		this.replies = replies;
	}

	public ReplyStorage addReply(ReplyStorage reply) {
		getReplies().add(reply);
		reply.setUser(this);

		return reply;
	}

	public ReplyStorage removeReply(ReplyStorage reply) {
		getReplies().remove(reply);
		reply.setUser(null);

		return reply;
	}

	public List<TopicStorage> getTopics() {
		return this.topics;
	}

	public void setTopics(List<TopicStorage> topics) {
		this.topics = topics;
	}

	public TopicStorage addTopic(TopicStorage topic) {
		getTopics().add(topic);
		topic.setUser(this);

		return topic;
	}

	public TopicStorage removeTopic(TopicStorage topic) {
		getTopics().remove(topic);
		topic.setUser(null);

		return topic;
	}

}