/**
 * 修改驾驶员
 */
function validateCreate() {
    var v = $('form').validate();

    var result = v.checkForm();
    v.showErrors();
    return result;
}

function validateEdit() {
    var v = $('form').validate();

    var result = v.checkForm();
    v.showErrors();
    return result;
}

function resetLigerui() {
    $('#driverEditor #drivingLicenseExpiryDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#driverEditor #drivingLicenseExpiryDate").val().replace(' 00:00:00', '')
    });

    $("#driverEditor #idType").ligerComboBox({
        width: 150,
        slide: false,
        selectBoxWidth: 150,
        selectBoxHeight: 240,
        valueField: 'name',
        textField: 'name',
        initValue: $('#driverEditor #idType').val(),
        initText: $('#driverEditor #idType').val(),
        url: '../dictionary/list',
        parms: {
            kind: 13,
            grid: false
        }
    });
}