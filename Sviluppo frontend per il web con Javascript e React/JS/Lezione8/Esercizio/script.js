$(document).ready(function () {
    console.log("Ready up to go!")

    $("#titolo-principale").css("color", "blue")
    $("li").css("font-weight", "bold")
    $(".evidenziato").css("background-color", "yellow")
    $("#lista-oggetti li:last").css("color", "red")

    $("#btn-nascondi").click(function () {
        $("#legenda").hide(500)
    })
    // shift alt f format
    $("#btn-mostra").click(function () {
        $("#legenda").show(500)
    })
    $("#lista-oggetti").append("<li>Nuovo Task aggiunto alla fine</li>")
    $(".contenitore").after("<p>--- Fine della sezione lista ---</p>")
    $("#btn-pulisci").click(function () {
        $("#lista-oggetti").empty()
        $(".descrizione").remove()
    })

    $("#btn-modifica").click(function () {
        console.log($("#lista-oggetti li:first").html())
        $("#legenda").html("<h3>Attenzione!</h3>")
        $(this).text("Fatto!")
    })
});