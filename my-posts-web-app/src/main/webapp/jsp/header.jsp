<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Java Web</title>
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/nav-add.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
	<!-- Header -->
	<header class="masthead">
		<div class="overlay"></div>
		<nav class="navbar">
			<div class="container">
				<a class="navbar-brand" href="#"><img
					src="images/ocean_logo.png" /></a>
				<div class="navbar-collapse">
					<ul class="navbar-nav">
						<li><a href="<c:url value="/home"/>">Home</a></li>
						<li><a href="#">About</a></li>
						<li><a href="<c:url value="/home"/>">Posts</a></li>
						<li><a href="#">Contact</a></li>
						<li><a href="<c:url value="/login"/>">Login</a></li>
						<li><a href="<c:url value="/logout"/>">Logout</a></li>
					</ul>
					<div class="search-bar">
						<form method="GET" action="/searchpost">
							<input class="search-input" type="text" name="searchTitle"
								placeholder="Search...">
							
							<button class="search-btn" type="submit">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
						</form>
					</div>
				</div>
			</div>
		</nav>
		<div class="page-heading">
			<div class="container">
				<h1>Ocean Nguyen</h1>
				<span class="subheading">Engineer/Video creater</span>
			</div>
		</div>
	</header>
	<div id="add-nav"><a href="/createpost"><div><i  class="fa fa-plus-circle fa-2x"></i></div></a></div>