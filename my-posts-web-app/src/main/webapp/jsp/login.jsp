<%@ include file="header.jsp"%>
<link rel="stylesheet" href="/css/login-style.css">
<div>
	<div class="contentBox">
		<div class="formBox">
			<h2>Login</h2>
			
			<form action="login" method='POST'>
				<div class="inputBox">
					<span>Username</span> <input type="text" name="username" required>
				</div>
				<div class="inputBox">
					<span>Password</span> <input type="password" name="password"
						required>
				</div>
				<div class="remember-me">
					<label><input type="checkbox" name=""> Remember me</label>
				</div>
				<div class="inputBox">
					<input type="submit" value="Sign in" name="">
				</div>
				<div class="inputBox">
					<p>
						Don't have an account? <a href="<c:url value="/registration"/>">Sign
							up</a>
					</p>
				</div>
				<div>
				<table align="center">
					<tr>
						<td style="font-style: italic; color: red;">${message}</td>
					</tr>
				</table>
			</div>
			</form>
		</div>
	</div>
	<hr />
</div>
<%@ include file="footer.jsp"%>