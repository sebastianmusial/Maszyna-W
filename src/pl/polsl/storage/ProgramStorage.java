package pl.polsl.storage;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Programs database table.
 * 
 */
@Entity
@Table(name="Programs")
@NamedQuery(name="ProgramStorage.findAll", query="SELECT p FROM ProgramStorage p")
public class ProgramStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String programID;

	private byte isPublic;

	@Lob
	private String programCode;

	private String programName;

	//bi-directional many-to-many association to ProgramsLibraryStorage
	@ManyToMany
	@JoinTable(
		name="ProgramMMLibrary"
		, joinColumns={
			@JoinColumn(name="programID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="programLibraryID")
			}
		)
	private List<ProgramsLibraryStorage> programsLibraries;

	//bi-directional many-to-one association to UserStorage
	@ManyToOne
	@JoinColumn(name="userID")
	private UserStorage user;

	public ProgramStorage() {
	}

	public String getProgramID() {
		return this.programID;
	}

	public void setProgramID(String programID) {
		this.programID = programID;
	}

	public byte getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(byte isPublic) {
		this.isPublic = isPublic;
	}

	public String getProgramCode() {
		return this.programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<ProgramsLibraryStorage> getProgramsLibraries() {
		return this.programsLibraries;
	}

	public void setProgramsLibraries(List<ProgramsLibraryStorage> programsLibraries) {
		this.programsLibraries = programsLibraries;
	}

	public UserStorage getUser() {
		return this.user;
	}

	public void setUser(UserStorage user) {
		this.user = user;
	}

}