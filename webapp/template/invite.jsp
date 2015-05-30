<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="partial/common_includes.html" %>
		<link rel="stylesheet" type="text/css" href="/fomo/style/css/invite.css">
		<script type="text/javascript" src="../view/Timer.js"></script>
	</head>
	<body>
		<div class="content">
			<div class="timer text-center">${timeLeft}</div>
			<div class="text-center">
				<dl>
					<dt>Event</dt><dd>${name}</dd>
					<dt>Host</dt><dd>${host}</dd>
					<dt>Time</dt><dd>${time}</dd>
					<dt>Location</dt><dd>${location}</dd>
					<dt>Details</dt><dd>${description}</dd>
				</dl>
				<!-- JS should handle this link, first POSTING to the server -->
				<a class="rsvp" href="/fomo/thanks/${eventId}" target="_blank">YES</a>
			</div>
		</div>
	</body>
</html>