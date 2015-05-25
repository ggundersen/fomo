window.onload = function() {
	
	var MAX_TIME_TO_RSVP = 60,
		timer = document.getElementById('timer');

	console.log(timer);
	var id = setInterval(function() {
		timer.innerHTML = MAX_TIME_TO_RSVP;
		MAX_TIME_TO_RSVP--;
		if (MAX_TIME_TO_RSVP === -1) {
			var link = document.getElementById('inviteUrl');
			link.innerHTML = '';
			clearInterval(id);
		}
	}, 1000);
}