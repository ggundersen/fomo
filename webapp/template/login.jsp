<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="partial/common_includes.html" %>
		<link rel="stylesheet" type="text/css" href="style/css/form.css">
	</head>
	<body>
		<%@include file="partial/navbar.html" %>
		<div class="container content">
			<h3>Login</h3>
			<form action="/fomo/login" method="post">
				<label>Email</label>
				<input type="text" name="email">
				<label>Password</label>
				<input type="text" name="password">
				<button type="submit">Login</button>
			</form>
		</div>
		<%@include file="partial/footer.html" %>
	</body>
</html>