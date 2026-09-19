import 'package:juno_client/config/security/storage.security.dart';
import 'package:juno_client/domain/seession/SESSION.dart';
import 'package:juno_client/domain/models/JnError.dart';
import 'package:juno_client/domain/models/Usuario.dart';
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

  Future<JnError?> login({String email = '', String password = ''}) async {
    try {
      JnError? response = await _port?.login(email, password);

      if (response == null) {
        return null;
      }

      if (response?.code == null) {
        return null;
      }

      return response;
    } catch (ex) {
      print(ex);
      throw Exception(ex);
    }
  }

  Future<String?> register(final Usuario user) async {
    String? errMessage;
    bool? response = await _port?.register(user.toMap());

    if (response!) {
      return null;
    } else {
      errMessage = 'Algún campo no es valido';
    }

    return errMessage;
  }

  Future<bool> loginByToken(final String refreshToken) async {
    try {
      return await _port?.loginByToken(refreshToken)
      .then((bool isLoading) => isLoading);
    } catch (ex) {
      print(ex);
      throw Exception(ex);
    }
  }
}
