drop view if exists view_posts;
create view view_posts(
		id ,
		title ,
		text ,
		category ,
		branch_id,
		department_id,
		user_name ,
		created_date ,
		updated_date
) as select
		posts.id ,
		posts.title ,
		posts.text ,
		posts.category ,
		users.branch_id,
		users.department_id,
		users.name ,
		posts.created_date ,
		posts.updated_date
from posts left join users on posts.user_id = users.id;

drop view if exists view_comments;
create view view_comments(
		id ,
		text ,
		post_id ,
		user_name ,
		created_date ,
		updated_date
) as select
		comments.id ,
		comments.text ,
		comments.post_id ,
		users.name ,
		comments.created_date ,
		comments.updated_date
from comments left join users on comments.user_id = users.id;

commit;