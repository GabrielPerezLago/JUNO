import 'package:juno_client/domain/models/Usuario.dart';

class Token {

  late String _token;
  late String _refreshToken;

  Token(
    final String token, 
    final String refreshToken
  ) {
    _token = token;
    _refreshToken = token;
  }

  get getToken => _token;
  get getRefreshToken => _refreshToken;
  void setToken(String token) => _token = token; 
  void setRefreshToken(String refreshToken) => _refreshToken = refreshToken;

  factory Token.fromJson(Map<String, dynamic> json) {
    return Token(json['token'], json['refreshToken']);
  }



}