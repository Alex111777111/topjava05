/**
 * Created by Maria on 31.01.2016.
 */
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;
function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize()
    });
    return false;
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
                    "mData": "description"
                },
                {
                    "mData": "calories"
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
                    "desc"
                ]
            ],
            "exceedRow": function (row, data, dataIndex) {
                if (data.exceed) {
                    $(row).css("text-color", "green")
                }
                else {
                    $(row).css("text-color", "red")
                }
            },
            "initComplete": makeEditable
        });
        $('#filter').submit(function () {
            updateTable();
            return false;
        });
        makeEditable();
        init();
    },
    function init() {
    });