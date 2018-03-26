/**
 * 统计分析
 */
window.statistics = {
    pages: null,
    catalog: null,
    catalogData: null
};
$(function () {
    $("#layout").ligerLayout({
        space: 0,
        topHeight: 80,
        leftWidth: 200,
        allowTopResize: false
    });
    $('#btnStatistics').addClass("select");

    var height = $(".l-layout-center").height();
    statistics.pages = $("#pages").ligerTab({
        height: height,
        changeHeightOnResize: true,
        showSwitch: true,
        ShowSwitchInTab: true
    });
    $.post("statistics/menus", function (data) {
        statistics.catalogData = data;
        statistics.catalog = $("#catalog").ligerTree({
            data: statistics.catalogData,
            idFieldName: 'id',
            parentIDFieldName: 'pid',
            checkbox: false,
            isExpand: true,
            needCancel: false,
            onSelect: function (note) {
                if (note.data.leaf === false)
                    return;
                statistics.pages.addTabItem({
                    tabid: note.data.id,
                    url: note.data.url,
                    text: note.data.text,
                    showClose: true,
                    height: height - 27
                });
            }
        });
    });
});