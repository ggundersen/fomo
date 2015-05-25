window.onload = function() {
	
	var MAX_TIME_TO_RSVP = 60*1000,
		endTime = new Date(new Date().getTime() + MAX_TIME_TO_RSVP).getTime(),
		timer = document.getElementById('timer'),
		countdown, id;

	id = setInterval(function() {
		countdown = Math.round((endTime - new Date().getTime()) / 1000);
		timer.innerHTML = countdown;
		console.log(countdown);
		if (countdown === 0) {
			var link = document.getElementById('inviteUrl');
			link.innerHTML = '';
			clearInterval(id);
		}
	}, 1000);
}