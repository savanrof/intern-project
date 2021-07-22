<%@ include file="header.jsp"%>
<link rel="stylesheet" href="/css/createpost-style.css">
<div>
	<div class="contentBox">
		<div class="formBox">
			<h2>Create new Post</h2>
			<form method='POST' action='createpost'>
				<div class="inputBox">
					<span>Title</span> <input type="text" name="title">
				</div>
				<div class="inputBox">
					<span>Description</span> <input type="text" name="description">
				</div>
				<div class="inputBox">
					<span>Content</span>
					<textarea type="text" name="content" rows="13"></textarea>
				</div>
				<div class="inputBox">
					<input type="submit" value="Create Post" name="">
				</div>
			</form>
		</div>
	</div>
</div>
<hr />
<%@ include file = "footer.jsp" %>