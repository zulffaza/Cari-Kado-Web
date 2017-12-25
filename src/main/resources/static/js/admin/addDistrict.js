var baseUrl = "http://localhost:8080/";

var onCountriesChange = function (districtId) {
    var url = baseUrl + "dashboard/admin/district/add";

    if (districtId !== undefined)
        url += "/" + districtId;

    window.location.href = url + "?countryId=" + $('#countries').find(":selected").val();
};

var onProvincesChange = function (districtId) {
    var url = baseUrl + "dashboard/admin/district/add";

    if (districtId !== undefined)
        url += "/" + districtId;

    window.location.href = url + "?countryId=" + $('#countries').find(":selected").val() +
        "&provinceId=" + $('#provinces').find(":selected").val();
};