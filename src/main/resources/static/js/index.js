baseUrl = "http://localhost:8080/";

$(document).ready(function () {
    $('#base_icon, #base_text')
        .mouseover(function () {
            $('#base_icon').attr('src', baseUrl + 'assets/ic_icon_open.png');
        })
        .mouseout(function () {
            $('#base_icon').attr('src', baseUrl + 'assets/ic_icon_close.png');
        });
});