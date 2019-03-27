function showNavigation(hash) {
    if (hash === null || hash === "" || hash === "#") {
        return;
    }

    document.querySelectorAll(".pf-c-nav__link").forEach(element => {
        if (element.getAttribute("href") === hash) {
            element.classList.add("pf-m-current");
        } else {
            element.classList.remove("pf-m-current");
        }
    });
    var anchor = document.querySelector(hash);
    document.querySelectorAll(".pf-c-page__main .pf-c-page__main-section").forEach(element => {
        if (element.contains(anchor)) {
            element.classList.remove("pf-u-display-none");
        } else {
            element.classList.add("pf-u-display-none");
        }
    });
}

window.addEventListener("hashchange", (event) => {
    showNavigation(new URL(event.newURL).hash);
});
document.addEventListener("DOMContentLoaded", (event) => {
    showNavigation(location.hash);
});
