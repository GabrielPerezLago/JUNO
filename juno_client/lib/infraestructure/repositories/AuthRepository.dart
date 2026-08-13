import 'package:http/http.dart';
import 'package:juno_client/infraestructure/repositories/implements/HttpReqRepository.dart';

class AuthRepository  {

  Future<Response> login(final String endpoint, final String email,final String password) async {
      return await HttpReqRepository.POST_QUERY(
        endpoint,
        {
          'email': email,
          'password': password
        }
      );
  }


}