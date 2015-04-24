SELECT 
  CONCAT(t.topicID,'') AS topicID, 
  CONCAT(t.userID,'') AS authorID, 
  CONCAT(t.topicName,'') AS topicName, 
  CONCAT(t.DATE,'') AS topicDate, 
  CONCAT(u.login,'') AS topicAuthor, 
  COUNT(DISTINCT r.replyID) AS postCount, 
  CONCAT(u2.login,'') AS lastUser, 
  CONCAT(r2.DATE, '') AS lastDate
FROM Topics AS t,
 Users AS u,
 Reply AS r,
 Users AS u2,
 Reply AS r2
WHERE t.userID = u.userID AND
 t.topicID = r.topicID AND
 t.topicID = r2.topicID AND
 u2.userID = r2.userID AND
 r2.replyID = 
 (
    SELECT MAX(r3.replyID)
    FROM Reply r3
    WHERE r3.topicID = t.topicID
  )
GROUP BY topicID
ORDER BY r2.DATE DESC;