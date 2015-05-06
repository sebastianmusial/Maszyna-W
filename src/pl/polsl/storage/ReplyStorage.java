package pl.polsl.storage;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Reply database table.
 * 
 */
@Entity
@Table(name="Reply")
@NamedQuery(name="ReplyStorage.findAll", query="SELECT r FROM ReplyStorage r")
public class ReplyStorage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String replyID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Lob
	private String replyText;

	//bi-directional many-to-one association to TopicStorage
	@ManyToOne
	@JoinColumn(name="topicID")
	private TopicStorage topic;

	//bi-directional many-to-one association to UserStorage
	@ManyToOne
	@JoinColumn(name="userID")
	private UserStorage user;

	public ReplyStorage() {
	}

	public String getReplyID() {
		return this.replyID;
	}

	public void setReplyID(String replyID) {
		this.replyID = replyID;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReplyText() {
		return this.replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public TopicStorage getTopic() {
		return this.topic;
	}

	public void setTopic(TopicStorage topic) {
		this.topic = topic;
	}

	public UserStorage getUser() {
		return this.user;
	}

	public void setUser(UserStorage user) {
		this.user = user;
	}

}