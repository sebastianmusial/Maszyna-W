package pl.polsl.storage;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ProgramsLibrary database table.
 * 
 */
@Entity
@Table(name="ProgramsLibrary")
@NamedQuery(name="ProgramsLibraryStorage.findAll", query="SELECT p FROM ProgramsLibraryStorage p")
public class ProgramsLibraryStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String programLibraryID;

	//bi-directional many-to-many association to ProgramStorage
	@ManyToMany(mappedBy="programsLibraries")
	private List<ProgramStorage> programs;

	//bi-directional many-to-one association to UserStorage
	@ManyToOne
	@JoinColumn(name="userID")
	private UserStorage user;

	public ProgramsLibraryStorage() {
	}

	public String getProgramLibraryID() {
		return this.programLibraryID;
	}

	public void setProgramLibraryID(String programLibraryID) {
		this.programLibraryID = programLibraryID;
	}

	public List<ProgramStorage> getPrograms() {
		return this.programs;
	}

	public void setPrograms(List<ProgramStorage> programs) {
		this.programs = programs;
	}

	public UserStorage getUser() {
		return this.user;
	}

	public void setUser(UserStorage user) {
		this.user = user;
	}

}