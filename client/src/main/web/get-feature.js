import hljs from "highlight.js";
import {clearForm, validateForm} from "./form.js";

const BASE_ID = "get-feature";
const GET_FEATURE_ENDPOINT = "http://localhost:8080/get-feature";

function sample(event) {
    event.preventDefault();

    let a = document.getElementById(BASE_ID + "-sample");
    document.getElementById(BASE_ID + "-lat").value = a.dataset.lat;
    document.getElementById(BASE_ID + "-lon").value = a.dataset.lon;
}

function submit() {
    if (validateForm(BASE_ID)) {
        let lat = parseInt(document.getElementById(BASE_ID + "-lat").value, 10);
        let lon = parseInt(document.getElementById(BASE_ID + "-lon").value, 10);
        fetch(GET_FEATURE_ENDPOINT + "/" + lat + "/" + lon)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    return {status: response.status, statusText: response.statusText};
                }
            })
            .then(json => setResult(json))
            .catch(e => setResult({error: e}));
    }
}

function hideResult() {
    let element = document.getElementById(BASE_ID + "-result");
    if (element) {
        element.classList.add("pf-u-display-none");
    }
}

function setResult(json) {
    let element = document.getElementById(BASE_ID + "-result");
    if (element) {
        element.classList.remove("pf-u-display-none");
        let code = element.firstElementChild;
        if (code) {
            code.innerText = JSON.stringify(json, null, "  ");
            hljs.highlightBlock(code);
        }
    }
}

document.addEventListener("DOMContentLoaded", (event) => {
    document.getElementById(BASE_ID + "-sample").addEventListener("click", sample);
    document.getElementById(BASE_ID + "-submit").addEventListener("click", submit);
    document.getElementById(BASE_ID + "-clear").addEventListener("click", () => {
        clearForm(BASE_ID);
        hideResult()
    });
});
