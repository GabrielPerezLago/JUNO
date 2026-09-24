import 'dart:convert';

import 'package:http/http.dart';
import 'package:juno_client/domain/models/Token.dart';
import 'package:juno_client/infraestructure/managers/token.manager.dart';
import 'package:juno_client/infraestructure/repositories/AuthRepository.dart';

class AuthService {
  static final _url_login = '/auth/signin';
  static final _endpoint_registrer = '/auth/signup';
  static final _endpoint_login_token  = '/auth/signin/token';

  final AuthRepository repository = AuthRepository();

  Future<TokenRespManager> login(
    final String email,
    final String password,
  ) async {
    try {
      return repository
          .login(_url_login, email, password)
          .then((Response resp) {
            if (resp.statusCode == 200) {
              return TokenRespManager(
                args: jsonDecode(resp.body),
                type: TokenRespType.TOKEN,
              );
            } else if (resp.statusCode == 401) {
              return TokenRespManager(
                args: jsonDecode(resp.body),
                type: TokenRespType.ERROR,
              );
            } else {
              throw Exception("Error en el Login");
            }
          })
          .catchError((ex) {
            print(ex);
            throw Exception(ex);
          });
    } catch (ex) {
      print(ex);
      rethrow;
    }
  }

  Future<TokenRespManager> registerService(final Map<String, dynamic> params) async {
    try {
      return await repository
      .register(_endpoint_registrer, params)
      .then((Response resp) {
        if (resp.statusCode == 200) {
            return TokenRespManager(args: jsonDecode(resp.body), type: TokenRespType.TOKEN);
        } else if (resp.statusCode == 401) {
          return TokenRespManager(args: jsonDecode(resp.body), type: TokenRespType.ERROR);
        } else {
          throw Exception("Error al registrar el usuario");
        }
      });
    } catch (ex) {
      print(ex);
      rethrow;
    }
  }


  Future<Token> loginByToken(final String refreshToken) async {
      return await repository.loginByToken(_endpoint_login_token, refreshToken)
      .then((Response resp) {
          if (resp.statusCode == 200) {
              return _jsonToToken(resp.body);
          } else {
            throw Exception('Error al logearse con el token');
          }
      })
      .catchError((ex) => throw Exception(ex));
  }

  Token _jsonToToken(String respBody) {
    return Token.fromJson(jsonDecode(respBody));
  }
}
