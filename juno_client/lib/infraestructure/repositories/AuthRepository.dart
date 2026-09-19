import 'package:http/http.dart';
import 'package:juno_client/infraestructure/repositories/implements/HttpRespository.dart';

class AuthRepository extends HttpRespository {
  Future<Response> login(final String endpoint,final String email,final String password) async {
    return await super.POST(endpoint)
    .QUERY({
      'email': email,
      'password': password,
    });
  }

  Future<Response> register(
    final String endpoint,
    final Map<String, dynamic> params,
  ) async {
    return await super.POST(endpoint).BODY(params);
  }

  Future<Response> loginByToken(final String endpoint, String refreshToken) async {
    return await super
    .POST(endpoint)
    .HADERS({
      'Authorization': 'Bearer $refreshToken',
    });
  }
}
