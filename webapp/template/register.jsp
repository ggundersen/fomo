<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="partial/common_includes.html" %>
		<link rel="stylesheet" type="text/css" href="style/css/form.css">
	</head>
	<body>
		<%@include file="partial/navbar.html" %>
		<div class="container content">
			<h3>Register</h3>
			<form action="register" method="post">
				<label>Email</label>
				<input type="text" name="email">
				<label>Password</label>
				<input type="text" name="password">
				<label>Repeat password</label>
				<input type="text" name="password-repeat">
				<button type="submit">Register</button>
			</form>
		</div>
		<%@include file="partial/footer.html" %>
	</body>
</html>