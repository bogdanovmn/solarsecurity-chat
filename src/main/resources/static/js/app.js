var stompClient = null;

function userLogin() {
	connect();
	// sendUserLogin();
	$("#loginBlock").hide();
	$("#chatBlock").show();
	$("#userPost").data("user-name", $("#loginName").val())
}

function userLogout() {
	sendUserLogout()
	disconnect();
	$("#loginBlock").show();
	$("#chatBlock").hide();
	$("#userPost").data("user-name", "")
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
				'name': $("#userPost").data("user-name").val()
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
				'name': $("#loginName").val(),
				'colorId': $("#userColorId").val()
			}
		)
	);
}

function sendUserPost() {
	var userPost = $("#userPost");

	stompClient.send(
		"/app/user-post",
		{},
		JSON.stringify(
			{
				'userName': userPost.data("user-name"),
				'text': userPost.val()
			}
		)
	);
}

function showNewMessage(message) {
	$("#messagesBlock").append("<p class='color" + message.colorId + "'>" + message.text + "</p>");
}

$(function () {
	$("#loginButton").click(function() { userLogin(); });
	$("#logoutButton").click(function() { userLogout(); });
	$("#sendMessageButton").click(function() { sendMessage(); });
});