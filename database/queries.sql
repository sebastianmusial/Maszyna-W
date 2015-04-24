select u.login 
from Topics t, Reply r, Users u
where t.topicID = r.topicID and u.userID = r.userID and r.replyID = 
( 
	select max(r1.replyID) 
	from Reply r1, Topics t1
	where r1.topicID = t1.topicID
);