export function clearForm(baseId) {
    let form = document.getElementById(baseId + "-form");
    if (form) {
        form.querySelectorAll("input").forEach(input => {
            input.value = "";
            let describedBy = input.getAttribute("aria-describedby");
            let description = document.getElementById(describedBy);
            if (description) {
                description.classList.add("pf-u-display-none");
            }
        });
    }
}

export function validateForm(baseId) {
    let valid = true;
    let form = document.getElementById(baseId + "-form");
    if (form) {
        form.querySelectorAll("input[required]").forEach(input => {
            let describedBy = input.getAttribute("aria-describedby");
            let description = document.getElementById(describedBy);
            if (description) {
                if (!input.value) {
                    description.classList.remove("pf-u-display-none");
                    valid = false;
                } else {
                    description.classList.add("pf-u-display-none");
                }
            }
        });
    }
    return valid;
}
