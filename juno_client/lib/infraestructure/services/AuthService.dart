import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart';
import 'package:juno_client/domain/models/Token.dart';
import 'package:juno_client/domain/models/Usuario.dart';
import 'package:juno_client/infraestructure/repositories/AuthRepository.dart';

class AuthService {
  final _url_login = '/login';
  final AuthRepository repository = AuthRepository();
  
  Future<Token> login(final String email, final String password) async {

    final resp = await repository.login(_url_login, email, password);


    if (resp.statusCode == 200) {
      // capturamos el body 
      final Map<String, dynamic> responseJson = jsonDecode(resp.body).orElseThrow(() => Exception("La captura del body no es correcta"));
      return Token.fromJson(responseJson);
    } else {
      throw Exception('Error al obtener ek token');
    }
  } 

}