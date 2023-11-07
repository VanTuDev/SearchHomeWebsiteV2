
// Js input datePicker
document.addEventListener("DOMContentLoaded", function () {
  var fromInput = document.getElementById("input_from");
  var toInput = document.getElementById("input_to");

  var viOptions = {
    format: 'DD/MM/YYYY', // Định dạng ngày tháng Việt Nam
    i18n: {
      previousMonth: 'Tháng trước',
      nextMonth: 'Tháng sau',
      months: ['Tháng 1',
               'Tháng 2',
               'Tháng 3',
               'Tháng 4',
               'Tháng 5',
               'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
      weekdays: ['Chủ Nhật', 'Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7'],
      weekdaysShort: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7']
    }
  };

  var fromPicker = new Pikaday(Object.assign({
    field: fromInput,
    yearRange: [moment().year(), moment().year() + 10],
    minDate: new Date(),
    maxDate: moment().add(10, 'years').toDate()
  }, viOptions));

  var toPicker = new Pikaday(Object.assign({
    field: toInput,
    yearRange: [moment().year(), moment().year() + 10],
    minDate: new Date(),
    maxDate: moment().add(10, 'years').toDate()
  }, viOptions));
});


