
$(document).ready(function () {
    let token = localStorage.getItem("userToken");
    if (!token) {
        $("#dash").hide();
    }
});

$("#logout").click(function () {
    localStorage.removeItem("userToken")
    $("#dash").hide()
})

$("#login").click(function () {
    const user = $("#username").val();
    const psw = $("#password").val().trim();
    console.log(user, psw)
    // (console.log("test"))
    fetch('https://dummyjson.com/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            username: user,
            password: psw,
            expiresInMins: 5,
        }),
        // credentials: 'include' // Include cookies (e.g., accessToken) in the request
    })
        .then(res => {
            console.log(res.status)
            if (res.status == 400) $(".text-error").text("Errore, username o password errati!")
            return res.json()
        })
        .then(data => {
            console.log(data)
            if (data.status == 200) {
                localStorage.setItem("userToken", data.accessToken);
                $("#dash").show(500);
                $("#username").val("");
                $("#password").val("");
            } else {
                
            }
        })
})

// localStorage.removeItem("userToken"); logout