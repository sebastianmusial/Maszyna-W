package pl.polsl.storage;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Topics database table.
 * 
 */
@Entity
@Table(name="Topics")
@NamedQuery(name="TopicStorage.findAll", query="SELECT t FROM TopicStorage t")
public class TopicStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String topicID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String topicName;

	//bi-directional many-to-one association to ReplyStorage
	@OneToMany(mappedBy="topic")
	private List<ReplyStorage> replies;

	//bi-directional many-to-one association to CategoryStorage
	@ManyToOne
	@JoinColumn(name="categoryID")
	private CategoryStorage category;

	//bi-directional many-to-one association to UserStorage
	@ManyToOne
	@JoinColumn(name="userID")
	private UserStorage user;

	public TopicStorage() {
	}

	public String getTopicID() {
		return this.topicID;
	}

	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<ReplyStorage> getReplies() {
		return this.replies;
	}

	public void setReplies(List<ReplyStorage> replies) {
		this.replies = replies;
	}

	public ReplyStorage addReply(ReplyStorage reply) {
		getReplies().add(reply);
		reply.setTopic(this);

		return reply;
	}

	public ReplyStorage removeReply(ReplyStorage reply) {
		getReplies().remove(reply);
		reply.setTopic(null);

		return reply;
	}

	public CategoryStorage getCategory() {
		return this.category;
	}

	public void setCategory(CategoryStorage category) {
		this.category = category;
	}

	public UserStorage getUser() {
		return this.user;
	}

	public void setUser(UserStorage user) {
		this.user = user;
	}

}