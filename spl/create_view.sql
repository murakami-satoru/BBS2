drop view if exists view_posts;
drop view if exists view_comments;
drop view if exists count_post_by_branch;
drop view if exists count_comment_by_branch;
drop view if exists count_post_by_user;
drop view if exists count_comment_by_user;
drop view if exists view_users;

create view view_posts(
		id ,
		title ,
		text ,
		category ,
		branch_id,
		department_id,
		user_id ,
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
		users.id ,
		users.name ,
		posts.created_date ,
		posts.updated_date
from posts left join users on posts.user_id = users.id;


create view view_comments(
		id ,
		text ,
		post_id ,
		branch_id,
		department_id,
		user_id ,
		user_name ,
		created_date ,
		updated_date
) as select
		comments.id ,
		comments.text ,
		comments.post_id ,
		users.branch_id,
		users.department_id,
		users.id ,
		users.name ,
		comments.created_date ,
		comments.updated_date
from comments left join users on comments.user_id = users.id;

create view count_post_by_branch (
	id ,
	name,
	count
) as select
	branches.id,
	branches.name ,
	count(*)
from  posts
inner join users on users.id = posts.user_id
inner join branches on users.branch_id = branches.id
group by branch_id;

create view count_comment_by_branch (
	id ,
	name,
	count
) as select
	branches.id,
	branches.name,
	count(*)
from  comments
inner join users on users.id = comments.user_id
inner join posts on comments.post_id = posts.id
inner join branches on users.branch_id = branches.id
group by branch_id;

create view count_post_by_user (
	id ,
	name,
	count
) as select
	users.id,
	users.name ,
	count(*)
from  posts
inner join users on users.id = posts.user_id
inner join branches on users.branch_id = branches.id
group by id;

create view count_comment_by_user (
	id ,
	name,
	count
) as select
	users.id,
	users.name ,
	count(*)
from  comments
inner join users on users.id = comments.user_id
inner join posts on comments.post_id = posts.id
inner join branches on users.branch_id = branches.id
group by id;

create view view_users (
	user_id ,
	login_id ,
	password ,
	user_name ,
	branch_id ,
	branch_name ,
	department_id ,
	department_name ,
	is_locked ,
	updated_date

) as select
	users.id ,
	users.login_id ,
	users.password ,
	users.name ,
	users.branch_id ,
	branches.name ,
	users.department_id ,
	departments.name ,
	users.is_locked ,
	users.updated_date
from users
inner join branches on users.branch_id = branches.id
inner join departments on users.department_id = departments.id;


commit;