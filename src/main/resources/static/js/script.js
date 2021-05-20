window.addEventListener('DOMContentLoaded', function () {

        nameInput = document.getElementsByName("name").item(0)
        emailInput = document.getElementsByName("email").item(0)
        password = document.getElementsByName("password1").item(0)
        let passwordRepeat = document.getElementsByName("password2").item(0)
        nameInput.addEventListener('input', function () {
            if (nameInput.value == "") {
                document.querySelector(".btn").disabled = true
            } else {
                document.querySelector(".btn").disabled = false
            }
        })
        emailInput.addEventListener('input', function () {
            if (emailInput.value == "") {
                document.querySelector(".btn").disabled = true
            } else {
                document.querySelector(".btn").disabled = false
            }
        })
        password.addEventListener('input', function () {
            if (password.value == "") {
                document.querySelector(".btn").disabled = true
            } else {
                document.querySelector(".btn").disabled = false
            }
        })
        passwordRepeat.addEventListener('input', () => {
            if (password.value !== passwordRepeat.value) {
                passwordRepeat.style.borderColor = 'red'
                document.querySelector(".btn").disabled = true
            } else {
                passwordRepeat.style.borderColor = '#ced4da'
                document.querySelector(".btn").disabled = false
            }
        })
    }
)