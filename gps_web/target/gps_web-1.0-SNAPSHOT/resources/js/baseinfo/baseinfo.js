/**
 * 运维管理
 */
window.admin = {
    layout: function () {
        $("#layout").ligerLayout({
            space: 0,
            topHeight: 100,
            leftWidth: 282,
            allowTopResize: true
        });
        var height = $(".l-layout-center").height();
        admin.pages = $("#pages").ligerTab({
            height: height - 27,
            changeHeightOnResize: true,
            showSwitch: true,
            ShowSwitchInTab: true
        });

        admin.catalog = $("#catalog").ligerTree({
            url: 'baseinfo/menus',
            idFieldName: 'id',
            parentIDFieldName: 'pid',
            checkbox: false,
            needCancel: false,
            isExpand: true,
            onSelect: function (node) {
                if (node.data.leaf === false)
                    return;
                admin.pages.addTabItem({
                    tabid: node.data.id,
                    url: node.data.url,
                    text: node.data.text,
                    showClose: true,
                    height: height - 27,

                });
            }
        });
    }
};
$(function () {
    $('#btnBaseInfo').addClass("select");
    admin.layout();
    console.log("ddddd");
    // alert("dddd");
});