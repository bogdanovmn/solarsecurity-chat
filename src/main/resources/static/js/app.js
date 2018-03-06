var stompClient = null;

function userLogin() {
	connect();
	$("#loginBlock").hide();
	$("#chatBlock").show();
	$("#userNameGreeting").text(
		$("#loginName").val()
	);
}

function userLogout() {
	sendUserLogout();
	disconnect();
	$("#loginBlock").show();
	$("#chatBlock").hide();
	$("#userNameGreeting").text("");
}

function connect() {
	var socket = new SockJS('/chat-ws');
	stompClient = Stomp.over(socket);
	stompClient.connect(
		{},
		function (frame) {
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/new-message', function (message) {
				showNewMessage(JSON.parse(message.body));
			});
			stompClient.subscribe('/topic/refresh-users-list', function (message) {
				showUsersListChange(JSON.parse(message.body));
			});
			sendUserLogin()
		},
		function (error) {
			console.log('Connect error: ' + error);
		}
	);
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}

function sendUserLogout() {
	stompClient.send(
		"/app/user-logout",
		{},
		JSON.stringify(
			{
				'name': $("#userNameGreeting").text()
			}
		)
	);
}

function sendUserLogin() {
	stompClient.send(
		"/app/user-login",
		{},
		JSON.stringify(
			{
				'name': $("#userNameGreeting").text(),
				'colorId': $("#userColorId").val()
			}
		)
	);
}

function sendUserPost() {
	stompClient.send(
		"/app/user-post",
		{},
		JSON.stringify(
			{
				'userName': $("#userNameGreeting").text(),
				'text': $("#userPost").val()
			}
		)
	);
}

function showNewMessage(chatMessage) {
	$("#messages").append("<span class='color" + chatMessage.colorId + "'>" + chatMessage.text + "</span><br/>");
	$("#messages").scrollTop(1E10)
}

function showUsersListChange(data) {
	showNewMessage(data.chatMessage);

	var usersHtml = $("#activeUsers");
	var users = data.activeUsers;

	usersHtml.text("");

	$.each(users, function(key, user) {
		usersHtml.append("<span class='color" + user.colorId + "'>" + user.name + "</span><br/>");
	});
}

$(window).bind("beforeunload", function() {
	sendUserLogout()
});

$(function () {
	$("#loginButton").click(function() { userLogin(); });
	$("#logoutButton").click(function() { userLogout(); });
	$("#sendMessageButton").click(function() { sendUserPost(); });
});