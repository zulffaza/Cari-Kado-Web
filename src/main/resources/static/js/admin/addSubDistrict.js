var baseUrl = "http://localhost:8080/";

var onCountriesChange = function (subDistrictId) {
    var url = baseUrl + "dashboard/admin/sub-district/add";

    if (subDistrictId !== undefined)
        url += "/" + subDistrictId;

    window.location.href = url + "?countryId=" + $('#countries').find(":selected").val();
};

var onProvincesChange = function (subDistrictId) {
    var url = baseUrl + "dashboard/admin/sub-district/add";

    if (subDistrictId !== undefined)
        url += "/" + subDistrictId;

    window.location.href = url + "?countryId=" + $('#countries').find(":selected").val() +
        "&provinceId=" + $('#provinces').find(":selected").val();
};

var onCitiesChange = function (subDistrictId) {
    var url = baseUrl + "dashboard/admin/sub-district/add";

    if (subDistrictId !== undefined)
        url += "/" + subDistrictId;

    window.location.href = url + "?countryId=" + $('#countries').find(":selected").val() +
        "&provinceId=" + $('#provinces').find(":selected").val() +
        "&cityId=" + $('#cities').find(":selected").val();
};