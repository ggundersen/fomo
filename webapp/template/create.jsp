<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="partial/common_includes.html" %>
		<link rel="stylesheet" type="text/css" href="/fomo/style/css/create.css">
	</head>
	<body>
		<%@include file="partial/navbar.html" %>
		<div class="container content">
			<section>
				<a href="${url}" target="_blank">Example invite</a>
			</section>
			<section>
				<h1>Create event</h1>
				<form>
					<table>
						<tr>
							<td>Name</td>
							<td><input type="text"></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><input type="text"></td>
						</tr>
						<tr>
							<td>Time</td>
							<td><input type="text"></td>
						</tr>
						<tr>
							<td>Location</td>
							<td><input type="text"></td>
						</tr>
					</table>
				</form>
			</section>
		</div>
	</body>
</html>