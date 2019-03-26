import hljs from "highlight.js";

export function clearResult(baseId) {
    let pre = document.getElementById(baseId + "-result");
    if (pre) {
        pre.classList.add("pf-u-display-none");
        while (pre.firstChild) {
            pre.removeChild(pre.firstChild);
        }
    }
}

export function hideResult(baseId) {
    let pre = document.getElementById(baseId + "-result");
    if (pre) {
        pre.classList.add("pf-u-display-none");
    }
}

export function appendResult(baseId, json) {
    let pre = document.getElementById(baseId + "-result");
    if (pre) {
        pre.classList.remove("pf-u-display-none");
        let code = document.createElement("code");
        code.innerText = JSON.stringify(json, null, "  ");;
        code.classList.add("json");
        hljs.highlightBlock(code);
        pre.appendChild(code);
        code.scrollIntoView();
    }
}

export function setResult(baseId, json) {
    let pre = document.getElementById(baseId + "-result");
    if (pre) {
        pre.classList.remove("pf-u-display-none");
        let code = pre.firstElementChild;
        if (code) {
            code.innerText = JSON.stringify(json, null, "  ");
            hljs.highlightBlock(code);
        }
    }
}