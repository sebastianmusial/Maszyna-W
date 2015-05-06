package pl.polsl.storage;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Categories database table.
 * 
 */
@Entity
@Table(name="Categories")
@NamedQuery(name="CategoryStorage.findAll", query="SELECT c FROM CategoryStorage c")
public class CategoryStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String categoryID;

	private String categoryName;

	//bi-directional many-to-one association to TopicStorage
	@OneToMany(mappedBy="category")
	private List<TopicStorage> topics;

	public CategoryStorage() {
	}

	public String getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<TopicStorage> getTopics() {
		return this.topics;
	}

	public void setTopics(List<TopicStorage> topics) {
		this.topics = topics;
	}

	public TopicStorage addTopic(TopicStorage topic) {
		getTopics().add(topic);
		topic.setCategory(this);

		return topic;
	}

	public TopicStorage removeTopic(TopicStorage topic) {
		getTopics().remove(topic);
		topic.setCategory(null);

		return topic;
	}

}