import hljs from "highlight.js";

export function codeElement(json) {
    let element = document.createElement("code");
    element.innerText = JSON.stringify(json, null, "  ");;
    element.classList.add("json");
    hljs.highlightBlock(element);
    return element;
}
