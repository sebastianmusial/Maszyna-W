package pl.polsl.storage;

import javax.persistence.*;

@Entity
@Table(name = "Commands")
public class StoredCommand {
	@Id @GeneratedValue
	@Column(name = "commandId")
	public Integer id;
	
	@Column(name = "commandName")
	public String name;
	
	@Column(name = "commandDefinition")
	public String definition;
	
	@Column(name = "commandListId")
	public Integer commandListId;
	
	@Column(name = "indexInCommandList")
	public Integer index;
}
