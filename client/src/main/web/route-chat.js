import {addResult, clearResult} from "./result.js";

const BASE_ID = "route-chat";
const ROUTE_CHAT_ENDPOINT = "ws://localhost:8080/route-chat";

var socket;
var connected = false;

function connect() {
    return new Promise((resolve, reject) => {
        socket = new WebSocket(ROUTE_CHAT_ENDPOINT);
        socket.onmessage = (m) => addResult(JSON.parse(m.data));
        socket.onerror = (e) => addResult({error: e});
        socket.onopen = () => {
            connected = true;
            resolve("Connected to " + ROUTE_CHAT_ENDPOINT);
        };
    });
}

function submit() {
    if (!connected) {
        connect().then((_) => {
            clearResult();
            send();
        });
    } else {
        clearResult();
        send();
    }
}

function send() {
    let requests = parseInt(document.getElementById(BASE_ID + "-requests").value, 10);
    for (let i = 0; i < requests; i++) {
        setTimeout(() => {
            var routeNote = {
                location: {
                    latitude: 1 + randomInt(2),
                    longitude: 1 + randomInt(2)
                },
                message: randomString(5)
            };
            socket.send(JSON.stringify(routeNote));
            addResult(routeNote);
        }, delay());
    }
}

function delay() {
    return 333 + randomInt(333);
}

function randomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

function randomString(length) {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for (var i = 0; i < length; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

document.addEventListener("DOMContentLoaded", (event) => {
    document.getElementById(BASE_ID + "-requests").addEventListener("input", event => {
        document.getElementById(BASE_ID + "-requests-out").value = event.currentTarget.value;
    });
    document.getElementById(BASE_ID + "-submit").addEventListener("click", submit);
    document.getElementById(BASE_ID + "-clear").addEventListener("click", () => {
        clearResult();
    });
});
