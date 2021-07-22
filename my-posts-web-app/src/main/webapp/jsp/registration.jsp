<%@ include file="header.jsp"%>
<link rel="stylesheet" href="/css/registration-style.css">
<div class="content">
	<div class="contentBox">
		<div class="formBox">
			<h2>Sign up</h2>
			<table align="center">
				<tr>
					<td style="font-style: italic; color: red;">${message}</td>
				</tr>
			</table>
			<form action="registration" method="POST">
				<div class="inputBox">
					<span>First Name</span> <input type="text" name="firstname"
						required>
				</div>
				<div class="inputBox">
					<span>Last Name</span> <input type="text" name="lastname" required>
				</div>
				<div class="inputBox">
					<span>Email</span> <input type="text" name="email" required>
				</div>
				<div class="inputBox">
					<span>Birthdate</span> <input type="date" name="birthdate" required>
				</div>
				<div class="inputBox">
					<span>Username</span> <input type="text" name="username" required>
				</div>
				<div class="inputBox">
					<span>Password</span> <input type="password" name="password"
						required>
				</div>
				<div class="inputBox">
					<input type="submit" value="Sign up" name="">
				</div>

				<div class="inputBox">
					<p>
						Already have account? <a href="<c:url value="/login"/>">Sign
							in</a>
					</p>
				</div>
			</form>
		</div>
	</div>

	<hr />

</div>
<%@ include file="footer.jsp"%>
