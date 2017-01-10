function initDateTimePicker(id, format, locale, viewMode) {
    var options = {
        'locale': locale,
        'format': format,
        'icons': {
            time: 'fa fa-clock-o',
            date: 'fa fa-calendar',
            up: 'fa fa-chevron-up',
            down: 'fa fa-chevron-down',
            previous: 'fa fa-chevron-left',
            next: 'fa fa-chevron-right',
            today: 'fa fa-dot-circle-o',
            clear: 'fa fa-trash',
            close: 'fa fa-times'
        },
        'viewMode': viewMode,
        'sideBySide': true,
        'widgetParent': '#' + id + ' .widget'
    };
    $('#' + id + ' input').datetimepicker(options);
    $('#' + id + ' button').click(function () {
        $('#' + id + ' input').data('DateTimePicker').toggle();
    });
    $('#' + id + ' input').on('dp.change', function () {
        $('#' + id + ' input').trigger('change');
    });
}
