var baseUrl = "http://localhost:8080/";

var onCountriesChange = function (cityId) {
    var url = baseUrl + "dashboard/admin/city/add";

    if (cityId !== undefined)
        url += "/" + cityId;

    window.location.href = url + "?countryId=" + $('#countries').find(":selected").val();
};