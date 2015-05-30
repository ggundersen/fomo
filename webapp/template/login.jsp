<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="partial/common_includes.html" %>
		<link rel="stylesheet" type="text/css" href="/fomo/style/css/login.css">
	</head>
	<body>
		<%@include file="partial/navbar.html" %>
		<div class="container content">
			<h3>Login</h3>
			<form action="login" method="post">
				<label>Username</label>
				<input type="text">
				<label>Password</label>
				<input type="text">
				<button type="submit">Login</button>
			</form>
		</div>
		<%@include file="partial/footer.html" %>
	</body>
</html>