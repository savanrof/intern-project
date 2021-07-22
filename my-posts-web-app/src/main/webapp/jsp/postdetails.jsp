<%@ include file = "header.jsp" %>
<link rel="stylesheet" href="/css/postdetails-style.css">
<!-- Content -->

	<div>
        <div class="contentBox">
            <div class="title">
				<h2>${post.getTitle()}</h2>
			</div>
			<div class="content">
				<article>${post.getContent()}</article>
			</div>
			<div class="information">
				<p>Created by ${post.getAuthor().getUsername()} on ${post.getCreatedDate()}</p>
			</div>
        </div>
        <hr/>
    </div>
<%@ include file = "footer.jsp" %>