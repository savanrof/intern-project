<%@ include file="header.jsp"%>
<link rel="stylesheet" href="/css/createpost-style.css">
<div>
	<div class="contentBox">
		<div class="formBox">
			<h2>Post edit</h2>
			<form method='PUT' action='postupdate'>
				<div class="inputBox">
					<span>Title</span> <input required type="text" name="newTitle"
						value="${post.getTitle()}">
				</div>
				<div class="inputBox">
					<span>Description</span> <input required type="text" name="newDescription"
						value="${post.getDescription()}">
				</div>
				<div class="inputBox">
					<span>Content</span>
					<textarea required type="text" name="newContent" rows="13">${post.getContent()}</textarea>
				</div>
				<div class="inputBox">
					<input type='hidden' name='id' value='${post.getId()}' />
					<input
						type="submit" value="Update Post">
				</div>
			</form>
		</div>
	</div>
</div>
<hr />


<%@ include file="footer.jsp"%>