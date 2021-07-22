<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "header.jsp" %>
	<!-- Main Content -->
	<div class="blog-list">
		<div class="content-rows">

			<!-- Content -->

			<c:forEach items="${listPosts}" var="post">
				<artical class="posts">
					<a href='/postdetails?id=${post.getId()}'>
						<h2 class='post-preview'>${post.getTitle()}</h2>
						<h3 class='post-subtitle'>${post.getDescription()}</h3>
					</a>
				<p class="infor">Posted by ${post.getAuthor().getFirstName()}
					${post.getAuthor().getLastName()} on ${post.getCreatedDate()} · 8 mins
					read.</p>
				<div style="display: inline-flex; float: right; margin-right: 30px;">
					<form action="postedit" method='GET'>
						<input type='hidden' name='id' value='${post.getId()}' /> <input
							type='submit' value='Edit' style="margin: 10px" />
					</form>
					<form action='postdetails' method='GET'>
						<input type='hidden' name='id' value='${post.getId()}' /> <input
							type='submit' value='Details' style="margin: 10px" />
						</form>
				</div>
				</artical>
				<hr>
			</c:forEach>

			<a class="viewall-btn" href='/searchpost'>View All Posts →</a>
		</div>
	</div>
	<hr>
	    
<%@ include file = "footer.jsp" %>
