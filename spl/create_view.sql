drop view if exists view_posts;
create view view_posts(
		id ,
		title ,
		text ,
		category ,
		user_name ,
		created_date ,
		updated_date
) as select 
		posts.id ,
		posts.title , 
		posts.text ,
		posts.category ,
		users.name ,
		posts.created_date ,
		posts.updated_date
from posts left join users on posts.user_id = users.id;

drop view if exists view_comments;
create view view_comments(
		id ,
		text ,
		user_name ,
		created_date ,
		updated_date
) as select 
		comments.id ,
		comments.text ,
		users.name ,
		comments.created_date ,
		comments.updated_date
from comments left join users on comments.user_id = users.id;

commit;