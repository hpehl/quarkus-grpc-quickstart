import {clearForm, validateForm} from "./form.js";
import {appendResult, clearResult} from "./result.js";

const BASE_ID = "list-features";
const LIST_FEATURES_ENDPOINT = "ws://localhost:8080/list-features";

var socket;
var connected = false;

function sample(event) {
    event.preventDefault();

    let a = document.getElementById(BASE_ID + "-sample");
    document.getElementById(BASE_ID + "-lo-lat").value = a.dataset.loLat;
    document.getElementById(BASE_ID + "-lo-lon").value = a.dataset.loLon;
    document.getElementById(BASE_ID + "-hi-lat").value = a.dataset.hiLat;
    document.getElementById(BASE_ID + "-hi-lon").value = a.dataset.hiLon;
}

function submit() {
    if (validateForm(BASE_ID)) {
        let loLat = parseInt(document.getElementById(BASE_ID + "-lo-lat").value, 10);
        let loLon = parseInt(document.getElementById(BASE_ID + "-lo-lon").value, 10);
        let hiLat = parseInt(document.getElementById(BASE_ID + "-hi-lat").value, 10);
        let hiLon = parseInt(document.getElementById(BASE_ID + "-hi-lon").value, 10);
        let payload = JSON.stringify({
            lo: {latitude: loLat, longitude: loLon},
            hi: {latitude: hiLat, longitude: hiLon}
        });
        if (!connected) {
            connect().then((message) => {
                clearResult(BASE_ID);
                socket.send(payload);
            });
        } else {
            clearResult(BASE_ID);
            socket.send(payload);
        }
    }
}

function connect() {
    return new Promise((resolve, reject) => {
        socket = new WebSocket(LIST_FEATURES_ENDPOINT);
        socket.onmessage = (m) => appendResult(BASE_ID, JSON.parse(m.data));
        socket.onerror = (e) => appendResult(BASE_ID, {error: e});
        socket.onopen = () => {
            connected = true;
            resolve("Connected to " + LIST_FEATURES_ENDPOINT);
        };
    })
}

document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById(BASE_ID + "-sample").addEventListener("click", sample);
    document.getElementById(BASE_ID + "-submit").addEventListener("click", submit);
    document.getElementById(BASE_ID + "-clear").addEventListener("click", () => {
        clearForm(BASE_ID)
        clearResult(BASE_ID);
    });
});
