import 'package:juno_client/config/security/storage.security.dart';
import 'package:juno_client/domain/seession/SESSION.dart';
import 'package:juno_client/domain/models/JnError.dart';
import 'package:juno_client/infraestructure/controllers/AuthController.dart';
import 'package:juno_client/infraestructure/services/AuthService.dart';

class AuthAdapter {
  AuthController? _port;
  final AuthService _service = AuthService();
  final JnTokenStorageRepository _tokenStorage = JnTokenStorageRepository();
  final SESSION _session = SESSION.instance;

  AuthAdapter() {
    _port = AuthController(
      tokenStorage: _tokenStorage,
      authService: _service,
      session: _session,
    );
  }

  Future<String?> login({String email = '', String password = ''}) async {
    try {
      JnError? response = await _port!.login(email, password);

      if (response == null) {
        return null;
      }

      if (response?.code == null) {
        return null;
      }

      return response.message;
    } catch (ex) {
      print(ex);
      rethrow;
    }
  }

  Future<String?> register(final Map<String, dynamic> params) async {
    try {
        String? errMessage;
        JnError? response = await _port!.register(params);

        if (response == null) {
          return null;
        } else {
          errMessage = response.message;
        }

        return errMessage;
    } catch (ex) {
      print(ex);
      rethrow;
    }
  }

  Future<bool> loginByToken(final String refreshToken) async {
    try {
      return await _port!.loginByToken(refreshToken)
      .then((bool isLoading) => isLoading);
    } catch (ex) {
      print(ex);
      throw Exception(ex);
    }
  }
}
