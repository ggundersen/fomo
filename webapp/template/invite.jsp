<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html" %>
		<link rel="stylesheet" type="text/css" href="/fomo/style/css/invite.css">
		<script type="text/javascript" src="../view/Timer.js"></script>
	</head>
	<body>
		<div id="content">
			<h1 class="text-center">fomo</h1>
			<div id="timer" class="text-center">${timeLeft}</div>
			<div class="text-center">
				<p>Will you attend:</p>
				<dl>
					<dt>Event:</dt><dd>${name}</dd>
					<dt>Host:</dt><dd>${host}</dd>
					<dt>Time:</dt><dd>${time}</dd>
					<dt>Location:</dt><dd>${location}</dd>
					<dt>Details</dt><dd>${description}</dd>
				</dl>
				<p id="inviteUrl">
					<!-- JS should handle this link, first POSTING to the server -->
					<a href="localhost:8080/fomo/thanks" target="_blank">YES</a>
				</p>
			</div>
		</div>
	</body>
</html>