import hljs from "highlight.js";

export function clearResult() {
    let result = document.getElementById("grpc-result");
    if (result) {
        while (result.firstChild) {
            result.removeChild(result.firstChild);
        }
    }
}

export function addResult(json) {
    let result = document.getElementById("grpc-result");
    if (result) {
        let code = document.createElement("code");
        code.innerText = JSON.stringify(json, null, "  ");;
        code.classList.add("json");
        hljs.highlightBlock(code);
        result.appendChild(code);
        code.scrollIntoView();
    }
}
