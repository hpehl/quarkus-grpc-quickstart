import {codeElement} from "./result.js";

const BASE_ID = "route-chat";
const ROUTE_CHAT_ENDPOINT = "ws://localhost:8080/route-chat";

var socket;
var connected = false;

function connect() {
    return new Promise((resolve, reject) => {
        socket = new WebSocket(ROUTE_CHAT_ENDPOINT);
        socket.onmessage = (m) => appendOut(JSON.parse(m.data));
        socket.onerror = (e) => appendOut({error: e});
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

function clearResult() {
    let result = document.getElementById(BASE_ID + "-result");
    if (result) {
        result.classList.add("pf-u-display-none");
    }
    let input = document.getElementById(BASE_ID + "-result-input");
    if (input) {
        while (input.firstChild) {
            input.removeChild(input.firstChild);
        }
    }
    let output = document.getElementById(BASE_ID + "-result-output");
    if (output) {
        while (output.firstChild) {
            output.removeChild(output.firstChild);
        }
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
                message: randomString(10)
            };
            appendInput(routeNote);
            socket.send(JSON.stringify(routeNote));
        }, delay());
    }
}

function appendInput(json) {
    let result = document.getElementById(BASE_ID + "-result");
    if (result) {
        result.classList.remove("pf-u-display-none");
    }
    let input = document.getElementById(BASE_ID + "-result-input");
    if (input) {
        let code = codeElement(json);
        input.appendChild(code);
        code.scrollIntoView();
    }
}

function appendOut(json) {
    let result = document.getElementById(BASE_ID + "-result");
    if (result) {
        result.classList.remove("pf-u-display-none");
    }
    let output = document.getElementById(BASE_ID + "-result-output");
    if (output) {
        let code = codeElement(json);
        output.appendChild(code);
        code.scrollIntoView();
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
