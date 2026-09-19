class JnError {

  String? error;
  int? code;
  String? message;

  JnError({
    this.message,
    this.error,
    this.code
  });

  static JnError fromErrorResponse(Map<String, dynamic> json) => JnError(
    message: json['message'],
    code: json['statusCode'],
    error: json['error']
  );




}