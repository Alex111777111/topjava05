/**
 * Created by Maria on 31.01.2016.
 */

var ajaxUrl = 'ajax/admin/meals/';
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        updateTableByData(data);
    });
}


$(function () {
    datatableApi = $('#datatable').DataTable({
        "sAjaxSource": ajaxUrl,
        "sAjaxDataProp": "",
        "bPaginate": false,
        "bInfo": false,
        "aoColumns": [
            {
                "mData": "dateTime",
                "mRender": function (date, type, row) {
                    if (type == 'display') {
                        var dateObject = new Date(date);
                        return '<span>' + dateObject.toISOString().substring(0, 10) + '</span>';
                    }
                    return date;
                }
            },
            {
                "mData": "description",
                "mRender": function (data, type, row) {
                    /*if (type == 'display') {
                     return '<a href="mailto:' + data + '">' + data + '</a>';
                     }*/
                    return data;
                 }

            },

            {
                "mData": "calories",
                "mRender": function (data, type, row) {
                    /* if (type == 'display') {
                     return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="enable($(this),' + row.id + ');"/>';
                     }*/
                    return data;
                }
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderEditBtn
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderDeleteBtn
            }
        ],
        "aaSorting": [
            [
                0,
                "desk"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).css("text-decoration", "line-through");
            }
        },
        "initComplete": makeEditable
    });
});