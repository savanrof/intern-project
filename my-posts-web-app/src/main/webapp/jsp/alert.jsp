<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible"
		content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Access Denied</title>
	</head>
	<body>
		<div>
			<h1>You cannot edit if you are not creater of ${postName} </h1>
		</div>
		<a href="<c:url value="/home" />">Home</a>
	</body>
</html>