import 'package:intl/intl.dart';

class DateTimeUtils {

  static final _format = DateFormat('dd.MM.yyyy');

   static DateTime? parseStringToDate(final String? date) {
    if (date == null || date.trim() == '') return null;
    return _format.parse(date);
   }
}