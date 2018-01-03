var baseUrl = "http://localhost:8080/";

var onCountriesChange = function (userId) {
    var url = baseUrl + "dashboard/admin/user/add";

    if (userId !== undefined)
        url += "/" + userId;

    window.location.href = url + "?countryId=" + $('#user-address-country').find(":selected").val();
};

var onProvincesChange = function (userId) {
    var url = baseUrl + "dashboard/admin/user/add";

    if (userId !== undefined)
        url += "/" + userId;

    window.location.href = url + "?countryId=" + $('#user-address-country').find(":selected").val() +
        "&provinceId=" + $('#user-address-province').find(":selected").val();
};

var onCitiesChange = function (userId) {
    var url = baseUrl + "dashboard/admin/user/add";

    if (userId !== undefined)
        url += "/" + userId;

    window.location.href = url + "?countryId=" + $('#user-address-country').find(":selected").val() +
        "&provinceId=" + $('#user-address-province').find(":selected").val() +
        "&cityId=" + $('#user-address-city').find(":selected").val();
};

var onDistrictsChange = function (userId) {
    var url = baseUrl + "dashboard/admin/user/add";

    if (userId !== undefined)
        url += "/" + userId;

    window.location.href = url + "?countryId=" + $('#user-address-country').find(":selected").val() +
        "&provinceId=" + $('#user-address-province').find(":selected").val() +
        "&cityId=" + $('#user-address-city').find(":selected").val() +
        "&districtId=" + $('#user-address-district').find(":selected").val();
};